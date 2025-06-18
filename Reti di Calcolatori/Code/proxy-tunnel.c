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

    if (!strcmp(method, "GET")) {
      int i = 0;

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

      int t;
      char buffer[2000];
      while ((t = read(sock3, buffer, 2000)) > 0)
        write(sock2, buffer, t);

      close(sock3);
    } else if (!strcmp("CONNECT", method)) {
      int i = 0;
      char* hostname = url;

      for (; url[i] != ':' && url[i]; i++)
        ;

      if (url[i] == ':') {
        url[i++] = 0;
      } else {
        perror("Parse error, expected ':'\n");
        return 1;
      }

      char* port = url + i;

      printf("hostname: %s, port: %s\n", hostname, port);

      struct hostent* he = gethostbyname(hostname);

      int sock3;

      if ((sock3 = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("Creazione socket 3 fallita!\n");
        return 1;
      }

      struct sockaddr_in server;
      server.sin_family = AF_INET;
      server.sin_port = htons((unsigned short)atoi(port));
      server.sin_addr.s_addr = *(unsigned int*)he->h_addr;

      if (connect(sock3, (struct sockaddr*)&server,
                  sizeof(struct sockaddr_in)) == -1) {
        perror("Connect to server fallita");
        return 1;
      }
      char* response = "HTTP/1.1 200 Established\r\n\r\n";
      write(sock2, response, strlen(response));

      int pid;
      if (!(pid = fork())) {
        // child
        char request[2000];
        int t;

        while ((t = read(sock2, request, 2000)) > 0)
          write(sock3, request, t);
      } else {
        // parent

        char request[2000];
        int t;

        while ((t = read(sock3, request, 2000)) > 0)
          write(sock2, request, t);

        kill(pid, SIGTERM);
        close(sock3);
      }
    } else {
      char* response_501 = "HTTP/1.0 501 Not Implemented\r\n\r\n";
      write(sock2, response_501, strlen(response_501));
    }

    close(sock2);
    exit(1);
  }

  close(sock);
}
