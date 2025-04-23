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

int equals(char* s1, char* s2)
{
    // handling null
    if (s1 == 0 || s2 == 0) {
        return s1 == s2;
    }

    int i = 0;
    for (i = 0; s1[i] && s2[i]; i++) {
        if (s1[i] != s2[i])
            return 0; // stringhe diverse
    }
    if (s1[i] == s2[i])
        return 1; // stringe uguali
    else
        return 0; // stringhe di lunghezze diverse
}

int str2int(char* s)
{
    int i, risultato = 0;
    // skipping all non-numbers
    for (i = 0; s[i] && (s[i] < '0' || s[i] > '9'); i++)
        ;

    for (; s[i] >= '0' && s[i] <= '9'; i++) {
        risultato = risultato * 10 + s[i] - '0';
    }
    return risultato;
}

int hex2int(char* s)
{
    int i, risultato = 0;
    // skipping all non-numbers
    for (i = 0; s[i] && !((s[i] >= '0' && s[i] <= '9') || (s[i] >= 'a' && s[i] <= 'f')); i++)
        ;

    for (; (s[i] >= '0' && s[i] <= '9') || (s[i] >= 'a' && s[i] <= 'f'); i++) {
        if (s[i] >= '0' && s[i] <= '9')
            risultato = risultato * 16 + s[i] - '0';
        else
            risultato = risultato * 16 + s[i] - 'a' + 10;
    }
    return risultato;
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
    char* request = "GET /ciccio HTTP/1.1\r\n\r\n";
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
            // replaces ":" with char 0, ending the value
            raw_headers[i - 1] = 0;

            // if the previous header name is empty, then we found two
            // consecutive CRLF, the body starts from here
            if (h[j].name[0] == 0)
                break;

            // starts the name of the next header from the next character
            h[++j].name = raw_headers + i + 1;

            firstseparator = 1;
        }
    }

    int body_length = 100; // default value in case none is provided
    int is_chunked = 0;

    // printing the headers
    for (int i = 0; i < j; i++) {
        printf("Nome: %s -> Valore:  %s\n", h[i].name, h[i].value);

        if (equals("Content-Length", h[i].name)) {
            body_length = str2int(h[i].value);
            printf("header riconosciuto: %s, valore %d\n", h[i].value, body_length);
        }

        if (equals("Transfer-Encoding", h[i].name) && equals(" chunked", h[i].value)) {
            is_chunked = 1;
        }
    }

    // reading the body
    int x;
    if (is_chunked) {
        int chunk_size;
        char crlf[2];
        do {
            char chunk_size_raw[100];
            int i;
            for (i = 0; read(sock, chunk_size_raw + i, 1) > 0; i++) {
                if ((chunk_size_raw[i - 1] == '\r') && (chunk_size_raw[i] == '\n')) {
                    chunk_size_raw[i - 1] = 0;
                    break;
                }
            }

            chunk_size = hex2int(chunk_size_raw);

            char body[chunk_size];
            int n = 1;
            for (int y = 0; (n = read(sock, body + y, chunk_size - y)) > 0; y += n)
                ;
            read(sock, crlf, 2);
            if (n == -1) {
                perror("read fallita");
                return 1;
            }
            x = x + chunk_size;
            body[x] = 0;
            printf("%s", body);
        } while (chunk_size > 0);
        printf("\n");
    } else {
        char body[body_length];
        int n = 1;
        for (x = 0; (n = read(sock, body + x, body_length - x)) > 0; x += n)
            ;
        if (n == -1) {
            perror("read fallita");
            return 1;
        }
        body[x] = 0;
        printf("%s\n", body);
    }

    printf("Tot bytes = %d\n", x);

    return 0;
}
