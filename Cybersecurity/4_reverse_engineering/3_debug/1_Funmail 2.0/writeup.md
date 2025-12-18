# Writeup

Tramite il comando strings provo a stampare tutte le stringhe salvate nel file
`funmail2`, trovando cosi' la password `more-secure-password`.
Provando a fare il login con le credenziali corrette, il programma lancia un errore.

Provo a deassemblare il programma con IDA e scopro che il lancio dell'errore e'
forzato, dunque il programma non chiama mail la funzione `showEmails`.

Tramite debugging con GDB, provo a jumpare forzatamente alla funzione showEmails con
i comandi:
```bash
gdb -q  ./funmail2
break main
run
jump showEmails
```

A questo punto sono entrato nella funzione showEmails e continuando con l'esecuzione
del programma, faccio stampare la mail e trovo cosi' la flag.

# Flag
```plain
TUCTF{l0c4l_<_r3m073_3x3cu710n}
```