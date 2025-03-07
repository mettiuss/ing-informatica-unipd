#include <stdio.h>

char a, b;
char *p;

int main(){
    a = 'A';
    b = 'B';

    printf("%c e %c\n", a, b);      // Stampa caratteri salvati in a e b
    printf("%d e %d\n", a, b);      // Stampa codici ASCII dei caratteri salvati in a e b
    printf("%X\n", &a);             // Stampa indirizzo di a
    
    p = &a;                         // p punta a a
    *p = 10;                        // Modifica il valore di a
    printf("%d\n", a);              // Stampa codice ASCII del carattere salvato in a

    *(p+1) = 12;                    // Salvo 12 nella cella successiva a p
    printf("%d\n", b);              // Stampa codice ASCII del carattere salvato in b
    
    return 0;
}