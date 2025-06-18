#include <netinet/in.h>
#include <stdio.h>
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
    perror("Socket fallito");
    return 1;
  }

  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_port = invert_byte_order(80);
  unsigned char* p = (unsigned char*)&server.sin_addr.s_addr;

  p[0] = 216;
  p[1] = 58;
  p[2] = 213;
  p[3] = 4;

  if (connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) ==
      -1) {
    perror("Connect fallita");
    return 1;
  }

  char* request = "GET / HTTP/1.0\r\n\r\n";  // GET /CRLF

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

  for (int i = 0; i < j; i++) {
    printf("Nome: %s -> Valore: %s\n", h[i].name, h[i].value);
  }

  return 0;
}
