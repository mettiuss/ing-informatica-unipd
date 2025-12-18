
# Write Up

Analizzando il codice sorgente della pagina, noto che il developer ha dimenticato
di eliminare un commento prima di pushare il sito in production.

Questo commento è:
`<!-- Development test account: user: JohnsTestUser, pass: AT3stAccountForT3sting -->`
e contiene al suo interno le credenziali usate dall'utente test.
>Development test account:
> * user: `JohnTestUser`
> * pass: `AtestAccount`

Provando ad utilizzare queste credenziali, l'accesso va a buon fine

Tornando sulla homepage visualizzo i post in base al ruolo `user`.
Noto che il ruolo utilizzato per il check dei post da mostrare è salvato all'interno dei cookie
reperibili in `Ispeziona -> Application -> Cookie`.
Provo a modificare il cookie relativo al ruolo `user -> admin`, aggiorno la pagina.

Ora mi vengono mostrati tutti i post disponibili, il primo post contiene la flag.

# Flag

```plain
flag{C00ki3s_c4n_b33_ch4ng3d_?}
```