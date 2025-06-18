#include <netdb.h>
#include <netinet/in.h>
#include <signal.h>
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
    perror("socket fallito");
    return 0;
  }

  int yes = 1;
  if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int))) {
    perror("setsockopt fallito");
    return 1;
  }

  struct sockaddr_in local;
  local.sin_family = AF_INET;
  local.sin_port = htons(8045);
  local.sin_addr.s_addr = 0;

  // binding the socket
  if (bind(sock, (struct sockaddr*)&local, sizeof(struct sockaddr_in)) == -1) {
    perror("Bind fallito");
    return 1;
  }

  // listening to the socket
  if (listen(sock, 5) == -1) {
    perror("Listen fallito");
    return 1;
  }

  struct sockaddr_in remote;
  remote.sin_family = AF_INET;
  socklen_t remote_length = sizeof(struct sockaddr_in);

  while (1) {
    int sock2;

    if ((sock2 = accept(sock, (struct sockaddr*)&remote, &remote_length)) ==
        -1) {
      perror("accept fallita");
      return 1;
    }

    if (fork())
      continue;

    do {
      printf("request\n");
      char raw_headers[100000];
      struct header h[100];

      int j = 0;
      h[j].name = raw_headers;
      for (int i = 0; read(sock2, raw_headers + i, 1) > 0; i++) {
        if ((raw_headers[i] == ':') && !h[j].value && j > 0) {
          raw_headers[i] = 0;
          h[j].value = raw_headers + i + 1;
        }
        if ((raw_headers[i - 1] == '\r') && (raw_headers[i] == '\n')) {
          raw_headers[i - 1] = 0;

          h[++j].name = raw_headers + i + 1;

          if (h[j - 1].name[0] == 0)
            break;
        }
      }

      int i = 0;
      char* request_line = h[0].name;
      printf("%s\n", request_line);
      char* method = request_line;

      for (; request_line[i] != ' '; i++)
        ;
      request_line[i] = 0;

      char* url = request_line + i + 1;

      for (; request_line[i] != ' '; i++)
        ;
      request_line[i] = 0;

      char* version = request_line + i + 1;

      i = 0;

      char* schema = url;

      for (; strncmp(url + i, "://", 3) && url[i]; i++)
        ;

      if (url[i] == 0) {
        printf("Parse error, expected '://'");
        exit(1);
      }

      url[i] = 0;

      i = i + 3;

      char* hostname = url + i;

      for (; url[i] != '/' && url[i]; i++)
        ;

      if (url[i]) {
        url[i++] = 0;
      } else {
        printf("Parse error, expected '://'");
        exit(1);
      }

      char* filename = url + i;
      printf("schema: %s hostname: %s filename: %s\n", schema, hostname,
             filename);

      struct hostent* he = gethostbyname(hostname);

      int sock3;

      if ((sock3 = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        printf("Socket Fallita");
        exit(1);
      }

      struct sockaddr_in server;
      server.sin_family = AF_INET;
      server.sin_port = htons(80);
      server.sin_addr.s_addr = *(unsigned int*)(he->h_addr);

      if (-1 == connect(sock3, (struct sockaddr*)&server,
                        sizeof(struct sockaddr_in))) {
        printf("Connect Fallita");
        exit(1);
      }

      char request[1000];
      sprintf(request,
              "GET /%s HTTP/1.1\r\nHost:%s\r\nConnection:close\r\n\r\n",
              filename, hostname);
      write(sock3, request, strlen(request));

      char data[100000];

      j = 0;
      for (; read(sock3, data + j, 1) > 0; j++) {
        char* connection_header = "Connection: ";
        if (!strncmp(data + j - strlen(connection_header), connection_header,
                     strlen(connection_header))) {
          break;
        }
      }
      data[j] = 0;
      sprintf(data, "%s%s", data,
              "Keep-Alive\r\nKeep-Alive:timeout=15, max=200");
      char test[10];
      read(sock3, test, strlen("close"));

      write(sock2, data, strlen(data));
      int t;
      char buffer[2000];
      while ((t = read(sock3, buffer, 2000)) > 0)
        write(sock2, buffer, t);

      close(sock3);

    } while (1);

    close(sock2);
    exit(1);
  }

  close(sock);
}