#include <stdio.h>
#include <time.h>

struct esempio {
    char c;
    char d;
    short int x;
    short int y;
};

struct problema {
    short int x;
    char c;
    short int y;
    char d;
};

int main() {
    struct esempio v;
    v.c = 'A';
    v.d = 10;
    v.x = 1024;
    v.y = 1;

    clock_t start = clock();
    printf("%c %c %d %d\n", v.c, v.d, v.x, v.y);
    clock_t end = clock();
    printf("Tempo di esecuzione: %f\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("%x %x %x %x\n", &v.c, &v.d, &v.x, &v.y);

    struct problema w;
    w.c = 'A';
    w.d = 10;
    w.x = 1024;
    w.y = 1;

    start = clock();
    printf("%c %c %d %d\n", w.c, w.d, w.x, w.y);
    end = clock();
    printf("Tempo di esecuzione: %f\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("%x %x %x %x\n", &w.x, &w.c, &w.y, &w.d);
}