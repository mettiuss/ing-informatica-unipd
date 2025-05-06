#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

char encodebase64char(char in)
{
    if (in <= 25) {
        return in + 'A';
    }
    if (in >= 26 && in <= 51) {
        return in + ('a' - 26);
    }
    if (in >= 51 && in <= 61) {
        return in + ('0' - 52);
    }
    if (in == 62) {
        return '+';
    }
    if (in == 63) {
        return '/';
    }
    return in;
}

char* base64encode(char* in)
{
    int length = 0;
    for (; in[length] != '\n'; length++)
        ;

    int added = 0;

    for (; length % 3 != 0; length++) {
        added++;
        in[length] = 0;
        in[length + 1] = '\n';
    }

    char* encoded = malloc((length / 3) * 4 + 1);

    int j = 0;

    for (int i = 0; i < length / 3; i++) {
        encoded[j++] = encodebase64char(in[i * 3] >> 2);
        encoded[j++] = encodebase64char(((in[i * 3 + 1] & 0xf0) >> 4) + ((in[i * 3] & 0x03) << 4));
        encoded[j++] = encodebase64char(((in[i * 3 + 1] & 0x0f) << 2) + ((in[i * 3 + 2] & 0xc0) >> 6));
        encoded[j++] = encodebase64char(in[i * 3 + 2] & 0x3f);
    }

    for (; added >= 0; added--)
        encoded[j - added] = '=';

    encoded[j++] = '\n';

    return encoded;
}

int main()
{
    char buf[1024];
    read(0, &buf, sizeof(buf));
    char* res = base64encode(buf);
    printf("%s", res);
    free(res);
}