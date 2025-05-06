#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

unsigned short invert_byte_order(unsigned short in)
{
    unsigned short out;
    unsigned char* p_in = (unsigned char*)&in;
    unsigned char* p_out = (unsigned char*)&out;

    p_out[1] = p_in[0];
    p_out[0] = p_in[1];

    return out;
}

int main()
{
    // creating a file descriptor
    int sock;
    if ((sock = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("Socket fallito");
        return 1;
    }

    if (sock == -1) {
        perror("Socket fallito");
        return 1;
    }

    struct sockaddr_in server;

    server.sin_family = AF_INET;
    // connecting to HTTP port
    // sin_port expects a big-endian int, while we verified
    // that C on this machine uses little-endian.
    server.sin_port = invert_byte_order(80);

    unsigned char* p = (unsigned char*)&server.sin_addr.s_addr;
    // google ip is 216.58.213.4
    p[0] = 216;
    p[1] = 58;
    p[2] = 213;
    p[3] = 4;

    // connecting the socket to the google server
    if (connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) == -1) {
        perror("Connect fallito");
        return 1;
    }

    // making the request
    char* request = "GET /\r\n";
    write(sock, request, strlen(request));

    char body[100000];
    int n = 1, x;
    for (x = 0; 0 < (n = read(sock, body + x, 999999 - x)); x += n)
        ;
    if (n == -1) {
        perror("Read fallita");
        return 1;
    }

    body[x] = 0;

    printf("%s\n", body);
    printf("Tot bytes = %d\n", x);
}
