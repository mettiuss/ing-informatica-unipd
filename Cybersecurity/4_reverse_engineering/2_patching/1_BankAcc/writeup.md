# Writeup

Grazie al software IDA sono riuscito a disassemblare il file e nella funzione main
ho trovato le istruzioni:
```asm
cmp     [rbp+var_2C], eax
jnz      short loc_401EE3
```
queste 2 istruzioni coppia confrontano la pass generata con quella inserita dall'utente
e a seconda del risultato di `cmp`, `jnz` decide a che branch saltare.

Ho patchato il file invertendo il comportamento di `jnz short loc_401EE3` negando l'istruzione
cosi' il codice diventa
```asm
cmp     [rbp+var_2C], eax
jz      short loc_401EE3
```
In questo modo inserendo la pass sbagliata riesco ad accedere e ad ottenere la flag

I Bytes modificati sono mostrati in `patch.diff`

# Flag
```plain
Flag{P00r_45_DuCk}
```
