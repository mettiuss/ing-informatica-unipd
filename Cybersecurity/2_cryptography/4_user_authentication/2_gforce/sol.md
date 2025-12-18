
# Write Up

Tramite wireshark effettuo una ricerca testuale tra i pacchetti
cercando una possibile stringa base64.
La ricerca è: frame contains "=="

Trovo una stringa base64 in un pacchetto che è:
`SU5TQXtjMTgwN2EwYjZkNzcxMzI3NGQ3YmYzYzY0Nzc1NjJhYzQ3NTcwZTQ1MmY3N2I3ZDIwMmI4MWUxNDkxNzJkNmE3fQ==`

La converto tramite il programma sol.py e ottengo la flag

# Flag

```plain
INSA{c1807a0b6d7713274d7bf3c6477562ac47570e452f77b7d202b81e149172d6a7}
```