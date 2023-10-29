# Strutture dati

### Vettori (array)

Sono definiti da un indirizzo iniziale (V), una lunghezza di ciascun elemento(L) e il numero di elementi (N)

```
.bss
V:   .skip N*L
```

Il primo elemento è in V, il secondo è in V+L, il terzo è in V+2L, .....

Quindi V[i] = V + (i \* L), 0 <= i < L.
Gli indirizzi degli elementi partono da zero

_esempio_
Copiare in R3 il valore di V[i], L = 4, i è in R1

```
LDR R0, =V
MOV R1, #i
LSL R1, R1, #2      @ Logic shift left, moltiplica per 4 (2^2) R1
LDR R3, [R0, R1]    @ Copia in R3 il contenuto dell'indirizzo R0 + R1
```

Oppure

```
LDR R0, =V
MOV R1, #i
LDR R3, [R0, R1, LSL #2] @ Non modifica R0 e R1, con il ! aggiorno R0
```

Oppure

```
LDR R0, =V
MOV R1, #i
MOV R2, #L
MUL R1, R1, R2
LDR R3, [R0, R1]
```

### Matrici

Un vettore di vettori, immaginiamo una matrice di `m` righe e `n` colonne. Vettori di lunghezza L
Rappresento ora M[riga, colonna]
In memoria:
M[0, 0]
M[0, 1]
...
M[0, n - 1]
M[1, 0]
...
M[m - 1, n - 1]

Per inizializzare:

```
.bss
M: m*n*L
```

Per ottenere l'elemento
M[i, j] = M + (i _ n _ L) + (j _ L) = M + (i _ n + j) \* L

```
MOV R1, #i
MOV R2, #j
LDR R4, =M
MOV R3, #n
MUL R3, R1, R3
ADD R3, R3, R2
LSL R3, R3, #2
LDR R0, [R4, R3]
```

### Matrice con array ausiliario di puntatori

Per accedere più facilmente, senza dover fare la moltiplicazione, posso usare un array di puntatori
Ogni elemento i dell'array punta all'indirizzo dell'i-esima riga

Per dichiararlo:

```
.bss
M: .skip m*n*L
.data
P: .word M, M+n*L, M+2*n*L, ...
```

Per accedere:

```
MOV R1, #i
MOV R2, #j
LDR R4, =P
LSL R3, R1, #2
LDR R3, [R4, R3]           @ Accedo a P in R4 + R3, R3 ora ha l'indirizzo della colonna i nella matrice
ADD R3, R3, R2, LSL #2
STR R0, [R3]               @ Accedo alla matrice
```

### Matrice con array ausiliario di offset

Uguale a quanto visto prima ma

```
.data
P: .word 0, n*L, 2*n*L
```

### Stack

Una struttura last in first out dotato di due operazioni, PUSH e POP
Per realizzarla serve uno STACK POINTER (SP)
SP decresce con il push e punta all'ultimo elemento inserito

Per dichiararlo:

```
.bss
		.skip 4096
stack:  .skip 4

.text
.global main
main:   LDR SP, =stack

push:   SUB SP, #4
		STR R0, [SP]

pop:    LDR R0, [SP]
		ADD SP, #4
```

### Liste concatenate

Due valori consecutivi in memoria, un puntatore e un valore
Un elemento di HEAD senza valore, con un puntatore verso il primo valore
Un elemento finale con puntatore NIL e l'ultimo valore della lista

Permette di aggiungere o rimuovere elementi dal mezzo della lista facilmente
