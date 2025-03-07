#include <stdio.h>

char *p;
short int a;
short int b;
short int *q;

int main() {
    a = 0x00F1;                 // 241
    b = 0xF032;                 // -4046

    p = &a;                     // p punta ad a, tipo di p: char
    q = &a;                     // q punta ad a, tipo di q: short int

    printf("%d\n", *p);         // Stampa -15 su convenzione Big Endian, 0 su Little Endian
    printf("%d\n", *q);         // Stampa 241
    printf("%X\n", *(p+1));     // Stampa 0 su convenzione Big Endian, -15 su Little Endian
    printf("%X\n", *(q+1));     // Stampa 0xF032
}