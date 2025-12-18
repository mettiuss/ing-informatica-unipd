# writeup

Debuggando con GDB il file, ho posizionato un breakpoint sul main
per poi disassemblare il main stesso.
Ho posizionato un breakpoint su entrambe le chiamate a funzione di strlen,
dopo 32 cicli while la Flag e' stata composta e sul registro
`rdi - 0x6010e0` ho trovato la flag

# Flag
```plain
SharifCTF{b70c59275fcfa8aebf2d5911223c6589}
```