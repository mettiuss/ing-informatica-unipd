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

struct enviroment {
  char* buf;
  char** env;
  int i;
  int c;
};

void add_env(struct enviroment* env, char* env_key, char* env_value) {
  sprintf(env->buf + env->c, "%s=%s", env_key, env_value);
  env->env[env->i++] = env->buf + env->c;
  env->c += (strlen(env_value) + strlen(env_key) + 2);
  env->env[env->i] = NULL;
}

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
  server.sin_addr.s_addr = 0;

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
    struct enviroment env;
    char b[1000];
    char* e[100];
    env.buf = b;
    env.env = e;
    env.i = env.c = 0;

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

    int length = 0;
    for (int i = 0; i < j; i++) {
      printf("Nome: %s -> Valore: %s\n", h[i].name, h[i].value);
      if (!strcmp(h[i].name, "Content-Length")) {
        length = atoi(h[i].value);
      }

      if (!strcmp(h[i].name, "Content-Type")) {
        add_env(&env, "CONTENT_TYPE", h[i].value + 1);
      }
    }

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

    add_env(&env, "METHOD", method);

    char* filename = url + 1;
    FILE* fin;

    if (!strncmp(url, "/cgi/", 5)) {  // CGI
      if (!strcmp(method, "GET")) {
        int i;
        for (i = 0; filename[i] && (filename[i] != '?'); i++)
          ;
        if (filename[i] == '?') {
          filename[i] = 0;
          add_env(&env, "QUERY_STRING", filename + i + 1);
        }

        add_env(&env, "CONTENT_LENGTH", "0");
      } else if (!strcmp(method, "POST")) {
        char tmp[10];
        sprintf(tmp, "%d", length);
        add_env(&env, "CONTENT_LENGTH", tmp);
      } else {
        char* response_501 = "HTTP/1.0 501 Not Implemented\r\n\r\n";
        write(sock2, response_501, strlen(response_501));
        close(sock2);
        continue;
      }

      printf("%s\n", filename);

      if ((fin = fopen(filename, "rt")) == NULL) {
        char* response_404 = "HTTP/1.0 404 Not Found\r\n\r\n";
        write(sock2, response_404, strlen(response_404));
      } else {
        char* response_200 = "HTTP/1.0 200 OK\r\n\r\n";
        write(sock2, response_200, strlen(response_200));
        fclose(fin);

        char fullname[200];

        sprintf(fullname,
                "/Users/m/Developer/ing-informatica-unipd/Reti di "
                "Calcolatori/Code/webServer/%s",
                filename);

        char* myargv[10];
        myargv[0] = fullname;
        myargv[1] = NULL;

        int pid;
        if (!(pid = fork())) {
          dup2(sock2, 1);
          dup2(sock2, 0);
          if (-1 == execve(fullname, myargv, env.env)) {
            perror("execve");
            exit(1);
          }
        }
        waitpid(pid, NULL, 0);
      }
    } else if (!strcmp(method, "GET")) {  // NOT CGI
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
    } else {  // NOT IMPLEMENTED
      char* response_501 = "HTTP/1.0 501 Not Implemented\r\n\r\n";
      write(sock2, response_501, strlen(response_501));
    }

    close(sock2);
  }

  close(sock);

  return 0;
}
