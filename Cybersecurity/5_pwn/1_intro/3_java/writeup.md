# Writeup

Per ottenere il valore della flag devo trovare il modo di eseguire il comando
`cat flag.txt`.
Nel programma `java`, non che nella funzione è presente l'avvio di una shell
```c
void bash() {
    printf("Opening bash shell...\n");
    sleep(3);
    printf("are you sure you don't prefer java?\n");
    if(rand() != 328347) return;
    // if only there was a way to jump here...
    execlp("/bin/sh", "/bin/sh", NULL);
}
```
tuttavia essa viene avviata solo se `rand()` genera un numero casuale uguale a `328347`.

Sfrutto il pwning e inserisco al posto come linguaggio preferito il valore:
`"java" + "a"*28 + IndirizzoFunzione("bash") + OffsetInternoFunzioneBash`
vedi programma sover.py.
Cosi' facendo, grazie ad un buffer overflow ho sovrascritto il valore dell'indirizzo di return,
in modo che all'uscita dell'if dedicato all'input `java`, il programma viene reindirizzato a
`execlp("/bin/sh", "/bin/sh", NULL);`

Questo mi permette di avviare una bash e quindi di poter inviare il comando
`cat flag.txt`

# Flag
```plain
flag{this_is_a_flag}
```