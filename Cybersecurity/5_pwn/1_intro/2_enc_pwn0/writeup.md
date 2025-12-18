# Writeup

Il programma `pwn0` utilizza la funzione `gets()` per prendere un input,
una funzione deprecata in quanto non verifica i bound di una stringa, dunque
puo' portare ad abusi di buffer overflow.

Il buffer della stringa buffer ha come dimensione 64 Byte, inserendo in input
una stringa di >64 caratteri, e' possibile, tramite un overflow, sovrascrivere
la memoria dedicata alla variabile josh, es:
```plain
input <- "a" * 64 + "H!gh"
buffer = "a" * 64
josh = "H!gh"
=> if(memcmp(josh,"H!gh",4)==0) = true
```

In questo modo ottengo la flag

# Flag
```plain
encryptCTF{L3t5_R4!53_7h3_J05H}
```