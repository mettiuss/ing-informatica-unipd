# Writeup

Il programma `no_rop` utilizza la funzione `gets()` per prendere un input,
una funzione deprecata in quanto non verifica i bound di una stringa, dunque
puo' portare ad abusi di buffer overflow.

Il buffer della stringa password ha come dimensione 8 Byte, inserendo come pass
una stringa di >8 caratteri, e' possibile, tramite un overflow, sovrascrivere
la memoria dedicata alla variabile pass, es:
```plain
input <- "aaaaaaaab"
buff = "aaaaaaaa"
pass = 98
=> if (pass) = true
```

In questo modo ottengo la flag

# Flag
```plain
Flag={hello_world_pwn}
```