#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

struct headers {
    char* name;
    char* value;
};

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
    int sock = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in server;
    server.sin_family = AF_INET;
    // connecting to HTTP port
    // sin_port expects a big-endian int, while we verified
    // that C on this machine uses little-endian.
    server.sin_port = invert_byte_order(80);

    unsigned char* p = (unsigned char*)&server.sin_addr.s_addr;
    p[0] = 216;
    p[1] = 58;
    p[2] = 213;
    p[3] = 4; // google ip is 216.58.213.4

    // connecting the socket to the google server
    if (connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in)) == -1) {
        perror("Connect fallito");
        return 1;
    }

    // making the request
    char* request = "GET /\r\n";
    write(sock, request, strlen(request));

    // reading the headers
    char raw_headers[100000];
    struct headers h[100];

    char firstseparator = 1;
    int j;
    h[j = 0].name = raw_headers;
    for (int i = 0; read(sock, raw_headers + i, 1) > 0; i++) {
        if ((raw_headers[i] == ':') && firstseparator) {
            // replaces ":" with char 0, ending the name
            raw_headers[i] = 0;

            // starts the value from the next character
            h[j].value = raw_headers + i + 1;

            firstseparator = 0;
        }
        if ((raw_headers[i - 1] == '\r') && (raw_headers[i] == '\n')) {
            // replaces ":" with char 0, ending the name
            raw_headers[i - 1] = 0;
            // if the previous header name is empty, then we found two
            // consecutive CRLF, the body starts now
            if (h[j].name[0] == 0)
                break;
            // starts the name of the next header from the next character
            h[++j].name = raw_headers + i + 1;

            firstseparator = 1;
        }
    }

    // reading the body
    char body[1000000];
    int n = 1, x;
    for (x = 0; (n = read(sock, body + x, 999999 - x)) > 0; x += n)
        ;
    body[x] = 0;
    // printing the headers
    for (int i = 0; i < j; i++)
        printf("Nome: %s -> Valore: %s\n", h[i].name, h[i].value);

    // printing the body
    printf("%s\n", body);
    printf("Tot byes = %d\n", x);

    return 0;
}