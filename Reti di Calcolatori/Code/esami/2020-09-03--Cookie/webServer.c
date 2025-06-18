#include <netinet/in.h>
#include <netinet/ip.h> /* superset of previous */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h> /* See NOTES */
#include <unistd.h>

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
  FILE* fin;
  int primiduepuntidopocrlf;
  char *filename, *eseguibile;
  char comando[1000];
  char ch;
  int i, j;
  char* response_404 = "HTTP/1.1 404 Not Found\r\n\r\n";
  char* response_200 = "HTTP/1.1 200 OK\r\nConnection:close\r\n\r\n";
  char* response_200_cookie =
      "HTTP/1.1 200 OK\r\nConnection:close\r\nSet-Cookie: F1=1\r\n\r\n";
  char* response_401 = "HTTP/1.1 401 Unauthorized\r\n\r\n";
  char* response_200_no_cookie =
      "HTTP/1.1 200 OK\r\nConnection:close\r\nSet-Cookie: F1=0\r\n\r\n";

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

    char* cookies = "";

    for (i = 0; i < j; i++) {
      printf("Nome: %s ---> Valore:  %s\n", h[i].n, h[i].v);
      if (sonouguali(h[i].n, "Content-Length")) {
        len = numero(h[i].v);
        printf("header riconosciuto: %s, valore %d\n", h[i].n, len);
      }
      if (sonouguali(h[i].n, "Cookie")) {
        cookies = h[i].v;
      }
    }

    char cookie_enabled = 0;
    int j = 0;
    for (; cookies[j]; j++) {
      if (cookies[j] == '1' && cookies[j - 1] == 'F')
        break;
    }

    if (cookies[j + 2] == '1')
      cookie_enabled = 1;

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
      if (!strcmp(filename, "file2.html")) {
        if (cookie_enabled) {
          write(s2, response_200_no_cookie, strlen(response_200_no_cookie));
        } else {
          write(s2, response_401, strlen(response_401));
        }
      } else if (!strcmp(filename, "file1.html")) {
        write(s2, response_200_cookie, strlen(response_200_cookie));
      } else {
        write(s2, response_200, strlen(response_200));
      }

      if (!(!strcmp(filename, "file2.html") && !cookie_enabled))
        while (EOF != (ch = fgetc(fin))) {
          write(s2, &ch, 1);
        }
    }
    close(s2);
  }
}