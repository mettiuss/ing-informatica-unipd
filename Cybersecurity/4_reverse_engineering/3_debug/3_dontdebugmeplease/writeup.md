# writeup

Il programma `dontdebugmeplase` e' protetto dal debugging tramite gdb,
esso sfrutta il fatto che un programma e' debuggabile tramite un solo programma
alla volta, dunque se lo eseguo tramite gdb ottengo: `there's already a debugger`.

Deassemblando il programma con IDA, not che nella funzione `init` c'e' un check:
```asm
cmp     rax, 0FFFFFFFFFFFFFFFFh
jnz     short loc_86D
```
questa controlla se c'e' un debugger attivo, in caso positivo termina il programma.

Eseguo un patching e inverto questo check, in modo che se attivo il debugger, il programma
possa continuare ad eseguire.

Ora che posso eseguire il programma con gdb e svolgo i seguenti passaggi:
1. `break main`: Posiziono un break nel main in modo da farmare l'esecuzione
2. `disassemble main`: Disassemblo la funzione main in modo da avere una panoramica sulle funzioni che eseguira'
    - Noto che viene effettuato un check sulla lunghezza dell'input, < 7 stoppa il programma
    - Trovo una call a `strcmp`: `0x00005555554008d6 <+102>:	call   0x5555554006f0 <strcmp@plt>`
3. `break *0x00005555554008d6`: Posiziono un break sul strcmp
4. `continue`: Continuo l'esecuzione fino a strcmp
5. Inserisco un input con piu' di 7 caratteri
6. `info registers`: Stampo i valori dei registri quando il programma e' fermo alla strcmp
7. `x/s <indirizzo>`: Stampo il valore associato all'indirizzo salvato nel registro della flag

Cosi' con `x/s <indirizzo_rsi>` ottengo il valore della flag.


# Flag
```plain
SPRITZ{d38U99in9_iS_v3ry_4nn0yIn9.}
```