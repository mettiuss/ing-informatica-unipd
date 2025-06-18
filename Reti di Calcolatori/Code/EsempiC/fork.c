#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
int main(int argc, char* argv[], char** env)
{
    if (fork() == 0) {
        // child
        execve("./test", argv, env);
        printf("here is child\n"); // this will not print
    } else {
        // parent
        printf("here is dad\n");
    }
}
