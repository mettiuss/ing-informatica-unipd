
# Write Up

Mettendo dei break point sulla funzione che esegue il check sulla password, noto che Data = "Invalid Username".
Effettuo la stessa operazione sulla funzione check dell'utente per trovare lo username e trovo:
`c5644ca91d1307779ed493c4dedfdcb7` che decriptato da md5 mi da `tideade`

Ora rieffettuo l'operazione sulla password e trovo:
`ZmxhZ3tzZDkwSjBkbkxLSjFsczlISmVkfQ==`, decodificata da base64: `flag{sd90J0dnLKJ1ls9HJed}`

# Flag

```plain
flag{sd90J0dnLKJ1ls9HJed}
```