
# Write Up

Tramite una SQL injection è possibile bypassare il sistema di verifica della password,
al posto della password inserisco la stringa:
    `' OR '1'='1`

In questo modo la query riesce a prendere un utente dal db, indipendentemente dallo username
o dalla password che ho messo.

Dopo aver passato il login compare una pagina con un codice QR,
il quale è solamente un troll, in quanto rimanda ad un video troll su youtube.

Controllando i cookie del sito, noto che dopo il login è stato impostato uno UID:
`ZW5jcnlwdENURntpX0g0dDNfaW5KM2M3aTBuNX0%3D`, che una volta decodato da base64 mi
ritorna la flag: `encryptCTF{i_H4t3_inJ3c7i0n5}7.`

# Flag
```plain
encryptCTF{i_H4t3_inJ3c7i0n5}7.
```