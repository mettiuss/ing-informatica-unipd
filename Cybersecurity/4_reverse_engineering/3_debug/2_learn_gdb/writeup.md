# Writeup

Eseguendo il programma vedo che la flag viene salvata nella viariabile
`flag_buf`.
Usando un tool per il debug (gdb) posiziono un breakpoint prima di exit()
cosi' sono sicuro che la variabile e' stata caricata in memoria.

Tramite un apposito comando di gdb `p` posso stampare le variabili salvate in
memoria, nel caso di `flag_buf` e' necessario un cast:
=> `p (char*)flag_buf`

# Flag
```plain
picoCTF{gDb_iS_sUp3r_u53fuL_a6c61d82}
```