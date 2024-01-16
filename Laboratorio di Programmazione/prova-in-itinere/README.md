# Prova in itinere - Laboratorio di programmazione

Programma scritto da Mattia Boscolo Meneguolo, Matteo Cuzzolin e Giacomo Dal Poz.

## Eseguire i Test

Per eseguire i test già creati è sufficiente eseguire questi programmi da terminale sulla cartella del progetto

```bash
  cmake build .
```

```bash
./build/Test
```

L'output di un test eseguito con successo dovrebbe essere

```
Date tester:
Successo! Data invalida per giorno trovata
Successo! Data invalida per giorno trovata
Testing output:
1/1/0
30/12/1950

Book tester:
Il libro Another Book è stato prestato
Il libro Another Book è stato ritirato
Successo! operator== okay
Testing output:
Title: Title1
Author: Francesca Rossi
ISBN: __CODICEISBN1
Copyrigth®: 1/1/0
Disponibilità: Disponibile

Bookshelf tester:
Posizione 1: Book1Title
Posizione 1: Book1Title
Posizione 2: Book2Title
Metto Book1 al posto di Book2
Posizione 2: Book1Title
```
