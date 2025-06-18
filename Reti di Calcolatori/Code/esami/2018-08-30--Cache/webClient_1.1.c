#include <netdb.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <time.h>
#include <unistd.h>

// flips two bytes around in an unsigned short (16 bit)
//
// this is to convert the number between big and little endian
unsigned short invert_byte_order(unsigned short in) {
  unsigned short out;

  unsigned char* p_in = (unsigned char*)&in;
  unsigned char* p_out = (unsigned char*)&out;

  for (int i = 0; i < sizeof(in); i++) {
    p_out[i] = p_in[sizeof(in) - i - 1];
  }

  return out;
}

void char_replace(char* in) {
  for (int i = 0; in[i] != 0; i++) {
    if (in[i] == '/')
      in[i] = '_';
  }
}

struct header {
  char* name;
  char* value;
};

struct headers {
  struct header* header_list;
  int length;
};

struct headers read_headers(int sock) {
  char raw_headers[100000];
  struct headers hs;
  struct header h[100];

  int j = 0;
  h[j].name = raw_headers;
  char first_separator = 1;

  for (int i = 0; read(sock, raw_headers + i, 1) > 0; i++) {
    if ((raw_headers[i] == ':') && first_separator) {
      raw_headers[i] = 0;
      h[j].value = raw_headers + i + 1;

      first_separator = 0;
    }
    if ((raw_headers[i - 1] == '\r') && (raw_headers[i] == '\n')) {
      raw_headers[i - 1] = 0;
      h[++j].name = raw_headers + i + 1;

      if (h[j - 1].name[0] == 0)
        break;

      first_separator = 1;
    }
  }
  hs.header_list = h;
  hs.length = j;
  return hs;
}

int main() {
  char* hostname = "www.example.com";
  char path[100];
  sprintf(path, "/");
  int sock;

  if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
    perror("Socket fallito");
    return 1;
  }

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_port = invert_byte_order(80);
  struct hostent* he = gethostbyname(hostname);
  server.sin_addr.s_addr = *(unsigned int*)(he->h_addr);

  if (connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) ==
      -1) {
    perror("Connect fallita");
    return 1;
  }

  char request[100];

  sprintf(request, "GET %s HTTP/1.1\r\nHost: %s\r\n\r\n", path, hostname);

  write(sock, request, strlen(request));

  FILE* cached;

  char filename[100];

  char_replace(path);

  sprintf(filename, "cache/%s%s", hostname, path);

  if ((cached = fopen(filename, "rt")) != NULL) {
    char cached_date[100];
    char c;
    int i = 0;
    while ((c = fgetc(cached)) != '\n') {
      cached_date[i] = c;
      i++;
    }
    cached_date[i] = 0;

    // checking if valid
    char* request = "HEAD / HTTP/1.1\r\nHost: www.example.com\r\n\r\n";
    write(sock, request, strlen(request));

    struct headers hs = read_headers(sock);

    char* last_modified;

    for (int i = 0; i < hs.length; i++) {
      if (!strcmp(hs.header_list[i].name, "Last-Modified")) {
        last_modified = hs.header_list[i].value + 1;
      }
    }

    char* format = "%a, %d %b %Y %T GMT";  // man 3 strptime
    struct tm* httpTime = malloc(sizeof(struct tm));
    strptime(last_modified, format, httpTime);
    time_t epochRemote = mktime(httpTime);

    if (last_modified && atoi(cached_date) > epochRemote) {
      printf("cache hit\n");
      while ((c = fgetc(cached)) != EOF)
        printf("%c", c);

      return 0;
    }

    free(httpTime);
  }

  struct headers hs = read_headers(sock);

  int content_length = 100;
  char chunked = 0;
  for (int i = 0; i < hs.length; i++) {
    printf("Nome: %s -> Valore: %s\n", hs.header_list[i].name,
           hs.header_list[i].value);

    if (!strcmp(hs.header_list[i].name, "Content-Length")) {
      content_length = atoi(hs.header_list[i].value);
      printf("Found content-length %d\n", content_length);
    }

    if (!strcmp(hs.header_list[i].name, "Transfer-Encoding") &&
        !strcmp(hs.header_list[i].value, " chunked")) {
      chunked = 1;
      printf("Body is chunked\n");
    }
  }

  cached = fopen(filename, "w+");

  time_t epochNow = time(NULL);

  fprintf(cached, "%lu\n", (unsigned long)epochNow);

  if (chunked) {
    int chunk_length = 0;
    char crlf[2];

    do {
      char raw_body[100];

      for (int i = 0; read(sock, raw_body + i, 1); i++) {
        if (raw_body[i - 1] == '\r' && raw_body[i] == '\n') {
          raw_body[i - 1] = 0;
          break;
        }
      }

      chunk_length = strtol(raw_body, NULL, 16);

      char response[chunk_length];
      int n;
      for (int x = 0; (n = read(sock, response + x, chunk_length - x)) > 0;
           x += n)
        ;
      read(sock, crlf, 2);

      response[chunk_length] = 0;
      printf("%s", response);
      fwrite(response, sizeof(response[0]), strlen(response), cached);
    } while (chunk_length > 0);

    printf("\n");
  } else {
    char body[content_length];

    int n;
    for (int x = 0; (n = read(sock, body + x, content_length - x)) > 0; x += n)
      ;

    body[content_length] = 0;

    printf("%s\n", body);
    fwrite(body, sizeof(body[0]), strlen(body), cached);
  }
  fwrite("\n", sizeof(char), 1, cached);
  fclose(cached);
  return 0;
}
