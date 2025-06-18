#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char* argv[], char* env[]) {
  int length = 0;

  for (int i = 0; env[i]; i++) {
    char* key = env[i];
    int j;
    for (j = 0; env[i][j] != '='; j++)
      ;

    env[i][j] = 0;

    char* value = env[i] + j + 1;

    if (!strcmp(key, "CONTENT_LENGTH"))
      length = atoi(value);
  }

  char* buffer = malloc(length);
  int t;

  printf("body:\n");
  for (int i = 0; i < length && (t = read(0, buffer + i, length - i)); i += t)
    ;

  buffer[length] = 0;
  printf("%s", buffer);

  printf("environment:\n");
  for (int i = 0; env[i]; i++)
    printf("%s---> %s\n", env[i], env[i] + strlen(env[i]) + 1);
}
