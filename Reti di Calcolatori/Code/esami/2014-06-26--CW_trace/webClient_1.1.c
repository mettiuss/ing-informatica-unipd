#include <netdb.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

struct header {
  char* name;
  char* value;
};

int main() {
  int sock;

  if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
    perror("Socket fallito");
    return 1;
  }

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_port = htons(80);
  struct hostent* he = gethostbyname("www.webtrace.it");
  server.sin_addr.s_addr = *(unsigned int*)(he->h_addr);

  if (connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) ==
      -1) {
    perror("Connect fallita");
    return 1;
  }

  char* request = "TRACE / HTTP/1.1\r\nHost: www.webtrace.it\r\n\r\n";

  write(sock, request, strlen(request));

  char raw_headers[100000];
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

  int content_length = 100;
  char chunked = 0;
  for (int i = 0; i < j; i++) {
    printf("Nome: %s -> Valore: %s\n", h[i].name, h[i].value);

    if (!strcmp(h[i].name, "Content-Length")) {
      content_length = atoi(h[i].value);
      printf("Found content-length %d\n", content_length);
    }

    if (!strcmp(h[i].name, "Transfer-Encoding") &&
        !strcmp(h[i].value, " chunked")) {
      chunked = 1;
      printf("Body is chunked\n");
    }
  }

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
    } while (chunk_length > 0);

    printf("\n");
  } else {
    char body[content_length];

    int n;
    for (int x = 0; (n = read(sock, body + x, content_length - x)) > 0; x += n)
      ;

    body[content_length] = 0;

    printf("%s\n", body);
  }
  return 0;
}
