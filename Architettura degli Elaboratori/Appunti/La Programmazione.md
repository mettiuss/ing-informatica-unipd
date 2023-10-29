# La Programmazione

### Programmazione in hardware

È possibile scrivere le istruzioni con l'hardware, in questo si è molto più efficienti nell'esecuzione, ma al costo della modificabilità
Le schede grafiche funzionano in questo modo

### Programmazione in software

Vengono implementate in hardware una famiglia di funzioni logiche elementari, con segnali di controllo per attivarle o disattivarle al bisogno
Questo ha prestazioni nettamente inferiori alla programmazione in hardware, ma ci permette di avere computer che possono eseguire più di un programma

L'elaboratore in grado di svolgere i programmi in modo universale segue l'

### Architettura di Von Neumann

-   `Main Memory`
    divisa in locazioni, ogniuna accessibile da un indirizzo
-   `Bus`
    Data Bus usato per trasmettere i dati
    Address Bus indica la sorgente/destinazione dei dati nel data bus
    Control Bus invia segnali di controllo (INTERRUPT)
    collega i dispositivi di I/O, la memoria e la CPU
-   `CPU`
    -   `Execution Unit`
        Contiene la `ALU` (elaborazioni algebriche)
        e la `Control Unit` che a sua volta contiene il `Program Counter` e `Instruction Register` le quali contengono rispettivamente l'istruzione successiva e una copia dell'istruzione corrente
    -   `MAR` e `MBR`
        Memory Address Register e Memory Buffer Register per aiutare la lettura/scrittura in memoria
    -   `I/O AR` e `I/O BR`
        I/O address registers
    -   `AC`
        Accumulatore per mantenere in memoria i dati temporanei

La CPU esegue queste semplici operazioni in loop:

1. Recupera la prossima istruzione dal PC
2. Salva l'istruzione nel Istruction Register
3. Incrementa il PC
4. Esegue l'istruzione
5. Ritrona a 1.
