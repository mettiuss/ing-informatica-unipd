#include <stdio.h>

char *p;
char a[5];

int main() {
    a[0] = 'c';
    a[1] = 'i';
    a[2] = 'a';
    a[3] = 'o';

    p = &a[0];                      // p punta a a[0]
    printf("%c\n", *(p+2));         // Stampa carattere puntato da p+2 = a[2]
    printf("%s\n", a);              // È un loop che stampa tutti i caratteri dell'array, finché non trova il terminatore 0
}