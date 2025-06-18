#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

struct headers {
  struct header* list;
  int length;
};

struct header {
  char* name;
  char* value;
};

int initialize_sock() {
  int sock;

  if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
    perror("Socket fallito");
    return 1;
  }

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_port = htons(80);
  unsigned char* p = (unsigned char*)&server.sin_addr.s_addr;

  p[0] = 88;
  p[1] = 80;
  p[2] = 187;
  p[3] = 84;

  if (connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) ==
      -1) {
    perror("Connect fallita");
    return 1;
  }
  return sock;
}

struct headers read_headers(int sock) {
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

  struct headers hs;
  hs.list = h;
  hs.length = j;
  return hs;
}

int main() {
  int sock = initialize_sock();

  char request[200];
  sprintf(request, "HEAD /image.jpg HTTP/1.1\r\nHost: 88.80.187.84\r\n\r\n");

  write(sock, request, strlen(request));

  struct headers head = read_headers(sock);

  close(sock);

  int content_length;
  for (int i = 0; i < head.length; i++) {
    if (!strcmp(head.list[i].name, "Content-Length")) {
      content_length = atoi(head.list[i].value);
    }
  }

  FILE* fin = fopen("./image.jpg", "w+");

  int bytes_transfered = 0;
  int chunk = 0;
  for (int chunk = 0; bytes_transfered < content_length; chunk++) {
    printf("%d-%d\n", chunk * 100000, (chunk + 1) * 100000 - 1);

    sock = initialize_sock();
    char body[100000];
    sprintf(request,
            "GET /image.jpg HTTP/1.1\r\nHost: 88.80.187.84\r\nRange: "
            "bytes=%d-%d\r\n\r\n",
            chunk * 100000, (chunk + 1) * 100000 - 1);

    write(sock, request, strlen(request));

    head = read_headers(sock);

    int request_transfer;
    for (int i = 0; i < head.length; i++) {
      if (!strcmp(head.list[i].name, "Content-Length")) {
        request_transfer = atoi(head.list[i].value);
      }
    }

    int n;
    for (int x = 0; (n = read(sock, body + x, request_transfer - x)) > 0;
         x += n)
      ;

    fwrite(body, sizeof(char), request_transfer, fin);
    bytes_transfered += request_transfer;

    close(sock);
  }

  fclose(fin);

  return 0;
}
