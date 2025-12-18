
# Write Up

Creo uno script python che provi a bruteforzare password e useragent
cercando di soddisfare i 2 if:

- Quando faccio md5($_) + $_[0] == md5($ua), php converte le 2 stringhe in 0 se il primo carattere è una lettera
  mentre se la stringa comincia con un numero, essa viene convertita in int.
   => Cerco una pass e uno user agent in cui md5(...) comincia per charattere e pass[0] è un carattere

- Nel secondo if: $_[0] == md5($_[0] . $flag)[0] mi serve una pass il cui primo carattere è unguale
  al primo carattere dell'md5 della concatenazione tra il primo carattere della pass e la flag

Avvio il bruteforce con python, creando pass e user-agent che soddisfano la prima condizione e
testo finché non si soddisfa anche la seconda. Questo accade per:
    `{pass: b0, user-agent: b0}`


# Flag

```plain
35c3_password_saltf1sh_30_seconds_max
```