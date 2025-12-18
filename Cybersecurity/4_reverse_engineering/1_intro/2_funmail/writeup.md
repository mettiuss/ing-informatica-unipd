# Writeup

Deassemblando il programma `funmail` con IDA, riesco a trovare il punto
del codice in cui viene controllata la password, da cui scopro che la pass
per accedere al servizio mail e' `this-password-is-a-secret-to-everyone!`

Inserendo quindi le credenziali:
- `john galt`
- `this-password-is-a-secret-to-everyone!`
riesco a leggere le mail dell'utente, cosi' posso leggere la flag
`TUCTF{d0n7_h4rdc0d3_p455w0rd5}`

# Flag
```plain
TUCTF{d0n7_h4rdc0d3_p455w0rd5}
```