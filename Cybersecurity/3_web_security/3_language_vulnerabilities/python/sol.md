
# Write Up

Sopra il form della pagina noto che è stato erroneamente caricato il backend
della webapp.
Scaricando e leggendo il source code noto che il modo in cui viene mostrato il mio
indirizzo IP quando carico un IP non whitelistato, non è sicuro.

Se al posto del mio indirizzo IP inserisco `%(allowed_flag)s`,
il backend al posto di ritornare al frontend la stringa inserita,
scrive in chiaro la flag.

# Flag

```plain
INSA{Y0u_C@n_H@v3_fUN_W1Th_pYth0n}
```