#include <netinet/in.h>
#include <netinet/ip.h> /* superset of previous */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h> /* See NOTES */
#include <unistd.h>

char alphabet[64] =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    "abcdefghijklmnopqrstuvwxyz"
    "0123456789+/";

char* base64encode(char* in, char* out) {
  unsigned char s[4];
  int j = 0, i = 0;
  for (; i < strlen(in) / 3; i++) {
    s[0] = alphabet[in[i * 3] >> 2];
    s[1] = alphabet[((in[i * 3] & 0x3) << 4) | (in[i * 3 + 1] >> 4)];
    s[2] = alphabet[((in[i * 3 + 1] & 0xF) << 2) | (in[i * 3 + 2] >> 6)];
    s[3] = alphabet[in[i * 3 + 2] & 0x3F];
    strncpy(out + j, s, 4);
    j += 4;
  }

  if (strlen(in) % 3 == 2) {
    s[0] = alphabet[in[i * 3] >> 2];
    s[1] = alphabet[((in[i * 3] & 0x3) << 4) | (in[i * 3 + 1] >> 4)];
    s[2] = alphabet[(in[i * 3 + 1] & 0xF) << 2];
    s[3] = '=';
  } else if (strlen(in) % 3 == 1) {
    s[0] = alphabet[in[i * 3] >> 2];
    s[1] = alphabet[(in[i * 3] & 0x3) << 4];
    s[2] = s[3] = '=';
  }

  strncpy(out + j, s, 4);
  out[j + 4] = 0;
  return out;
}

unsigned short int invert_byte_order(unsigned short a) {
  unsigned char *p, t;
  p = (unsigned char*)&a;
  t = *p;
  *p = *(p + 1);
  *(p + 1) = t;
  return a;
}

struct headers {
  char* n;
  char* v;
};

struct headers h[100];
int sonouguali(char* s1, char* s2) {
  int i;
  for (i = 0; s1[i] && s2[i]; i++) {
    if (s1[i] != s2[i])
      return 0;  // Stringhe diverse
  }
  if (s1[i] == s2[i])
    return 1;  // Stringhe uguali
  else
    return 0;  // Sottostringa uguale
}

int numero(char* s) {  // es. s = "1473"
  int i;
  int risultato = 0;
  for (i = 0; s[i] && (s[i] < '0' || s[i] > '9'); i++)
    ;  // Scorro valori non cifre e non terminatore
  if (s[i] == 0)
    return 0;  // Se e' terminatore : valore totale 0
  for (; s[i] >= '0' && s[i] <= '9'; i++)
    risultato = risultato * 10 + s[i] - '0';
  return risultato;
}

int main() {
  char* exp_string_char = "Matteo:2066701";
  char exp_string[200];
  base64encode(exp_string_char, exp_string);
  FILE* fin;
  int primiduepuntidopocrlf;
  char *filename, *eseguibile;
  char comando[1000];
  char ch;
  int i, j;
  char* response_404 =
      "HTTP/1.1 404 Not Found\r\nConnection:close\r\nContent-Length:0\r\n\r\n";
  char* response_200 = "HTTP/1.1 200 OK\r\nConnection:close\r\n\r\n";
  char* response_403 =
      "HTTP/1.1 401 "
      "Unauthorized\r\nConnection:close\r\nWWW-Authenticate:basic\r\n\r\n";
  char head[10000];
  char entity_body[1000000];
  char *method, *url, *ver;
  char* requestline;
  unsigned char* p;  // 216.58.213.4
  struct sockaddr_in server;
  struct sockaddr_in remote;
  int len;
  int n = 1, x = 1, s = 0;
  int s2;
  int yes = 1;
  FILE* f;
  if (-1 == (s = socket(AF_INET, SOCK_STREAM, 0))) {
    perror("Socket fallita");
    return 1;
  }

  if (-1 == setsockopt(s, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int))) {
    perror("Setsockopt fallita");
    return 1;
  }

  server.sin_family = AF_INET;
  server.sin_port = invert_byte_order(6701);
  p = (unsigned char*)&server.sin_addr.s_addr;
  p[0] = 0;
  p[1] = 0;
  p[2] = 0;
  p[3] = 0;

  if (-1 == bind(s, (struct sockaddr*)&server, sizeof(struct sockaddr_in))) {
    perror("Bind fallita");
    return 1;
  }
  if (-1 == listen(s, 5)) {
    perror("Listen falllita");
    return 1;
  }
  remote.sin_family = AF_INET;
  len = sizeof(struct sockaddr_in);
  while (1) {
    if (-1 == (s2 = accept(s, (struct sockaddr*)&remote, &len))) {
      perror("Accept Fallita");
      return 1;
    }
    j = 0;
    h[j].n = head;
    for (i = 0; read(s2, head + i, 1) > 0; i++) {
      if (head[i] == ':' && primiduepuntidopocrlf) {
        h[j].v = head + i + 1;
        head[i] = 0;
        primiduepuntidopocrlf = 0;
      }
      if ((head[i] == '\n') && (head[i - 1] == '\r')) {
        primiduepuntidopocrlf = 1;
        head[i - 1] = 0;
        if (h[j].n[0] == 0)
          break;
        h[++j].n = head + i + 1;
      }
    }

    char* authorization = "";
    for (i = 0; i < j; i++) {
      printf("Nome: %s ---> Valore:  %s\n", h[i].n, h[i].v);
      if (sonouguali(h[i].n, "Content-Length")) {
        len = numero(h[i].v);
        printf("header riconosciuto: %s, valore %d\n", h[i].n, len);
      }
      if (sonouguali(h[i].n, "Authorization")) {
        authorization = h[i].v;
      }
    }

    if (strncmp("Basic", authorization + 1, 5) ||
        strcmp(authorization + 7, exp_string)) {
      authorization = "";
    }

    requestline = h[0].n;
    method = requestline;
    for (i = 0; requestline[i] != ' '; i++)
      ;
    requestline[i] = 0;
    url = requestline + i + 1;
    for (; requestline[i] != ' '; i++)
      ;
    requestline[i] = 0;
    ver = requestline + i + 1;
    printf("metodo = %s, url = %s, ver %s\n", method, url, ver);
    filename = url + 1;
    if (!strncmp(url + 1, "gateway/", 8)) {
      eseguibile = url + 9;
      sprintf(comando, "%s > output\n", eseguibile);
      printf("Eseguo comando: %s", comando);
      if (system(comando) == -1) {
        perror("system fallita");
        return 1;
      }
      filename = comando;
      strcpy(filename, "output");
    }

    if (NULL == (fin = fopen(filename, "rt")))
      write(s2, response_404, strlen(response_404));
    else {
      if (!strcmp(authorization, "")) {
        write(s2, response_403, strlen(response_403));
      } else {
        write(s2, response_200, strlen(response_200));
        while (EOF != (ch = fgetc(fin))) {
          write(s2, &ch, 1);
        }
      }
    }
    close(s2);
  }
}