#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
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

struct header {
  char* name;
  char* value;
};

int main() {
  int sock;

  if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
    perror("socket fallito");
    return 1;
  }

  int yes = 1;
  if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int))) {
    perror("setsockopt fallito");
    return 1;
  }

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_port = invert_byte_order(8055);

  unsigned char* p = (unsigned char*)&server.sin_addr;

  p[0] = 0;
  p[1] = 0;
  p[2] = 0;
  p[3] = 0;

  // binding the socket
  if (bind(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) == -1) {
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

    char raw_headers[100000];
    struct header h[100];

    int j = 0;
    h[j].name = raw_headers;
    char first_separator = 1;

    for (int i = 0; read(sock2, raw_headers + i, 1) > 0; i++) {
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

    for (int i = 0; i < j; i++)
      printf("Nome: %s -> Valore: %s\n", h[i].name, h[i].value);

    int i = 0;
    char* request_line = h[0].name;
    char* method = request_line;

    for (; request_line[i] != ' '; i++)
      ;
    request_line[i] = 0;

    char* url = request_line + i + 1;

    for (; request_line[i] != ' '; i++)
      ;
    request_line[i] = 0;

    char* version = request_line + i + 1;

    printf("Method: %s\nURL: %s\nVersion: %s\n", method, url, version);

    char* filename = url + 1;

    if (!strncmp(url, "/gateway/", 9)) {
      char* eseguibile = url + 9;
      char comando[1000];
      sprintf(comando, "%s > output\n", eseguibile);

      if (system(comando) == -1) {
        perror("system fallita");
        return 1;
      }

      filename = "output";
    }

    FILE* fin;
    if ((fin = fopen(filename, "rt")) == NULL) {
      char* response_404 = "HTTP/1.0 404 Not Found\r\n\r\n";
      write(sock2, response_404, strlen(response_404));
    } else {
      char* response_200 = "HTTP/1.0 200 OK\r\n\r\n";
      write(sock2, response_200, strlen(response_200));

      char c;
      while ((c = fgetc(fin)) != EOF)
        write(sock2, &c, 1);

      fclose(fin);
    }

    close(sock2);
  }

  close(sock);

  return 0;
}
