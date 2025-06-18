#include <stdio.h>
#include <stdlib.h>
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

int main() {
  char buf[1024];
  read(0, &buf, sizeof(buf));
  char res[1024];
  base64encode(buf, res);
  printf("%s", res);
  free(res);
}