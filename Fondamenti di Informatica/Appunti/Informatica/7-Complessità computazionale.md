#algoritmo 
# Complessità computazionale

Vedendo la ricorsione, abbiamo notato una netta differenza tra il metodo ricorsivo ed un ciclo while, possiamo ora ragionare sulle differenze prestazionali tra vari algortimi.

Un algoritmo si può analizzare
- Sperimentalmente
- Teoricamente
	- Fare attenzione ai tempi esterni all'algoritmo, es. caricare la JVM richiede una certa quantità di tempo
	- si può usare il metodo `System.currentTimeMillis()` che restituisce il timestamp attuale

Le #prestazioni si misurano in tempo impiegato e memoria occupata
spesso ridurre il tempo significa aumentare la quantità di memoria e vice versa.

L'analisi di complessità può farci scegliere un algoritmo piuttosto che un altro

Esprimiamo la complessità in n^b, per calcolare b usiamo il log-ratio (slide 13)

Anziché scrivere ogni singolo algoritmo può essere sufficiente farne una stima qualitativa
- semplificare le operazioni primitive (assegnazioni di variabili), operazioni aritmetiche, operazioni di confronto, lettura/scrittura ad una cella di un array. 
  Queste hanno tutte tempo costante per i nostri scopi e possiamo non contarle nel calcolo
- Indichiamo con n la lunghezza dell'input, indipendentemente dal suo tipo

### Classi di Complessità
Polinomiali: problemi considerati "facili" che richiedono un tempo ragionevole
Non Polinomiali: poblemi esponenziali che richiedono una gran quantità di tempo per raggiungere una soluzione
