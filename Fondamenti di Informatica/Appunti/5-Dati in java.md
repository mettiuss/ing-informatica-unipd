# Notazione Binaria

**Tipi di dato in Java**

-   Tipi primitivi
    -   float (32 bit) double (64 bit) byte (8 bit) short (16 bit) int (32 bit) long (64 bit) char (8 bit) int e long possono essere anche unsigned facendo rappresentare interi più grandi
    -   La libreria standard java.lang ci permette di sapere i valori minimi e massimi dei vari tipi (byte, Byte.MIN_VALUE, Byte.MAX_VALUE)
    -   JVM non segnala gli errori di overflow, il risultato risulta semplicemente errato, errore di tipo logico
    -   L’unica operazione aritmetica che genera un errore è la divisione per zero (dividendo però un float otteniamo +/-infinito)
    -   Per assegnare un valore ad una variabile long bisogna aggiungere “L” alla fine `long l = 1345845486748064820L;`
-   Riferimenti ad un oggetto

**Le variabili**
Spazi di memoria, identificati da un _**nome**,_ che possono conservare _**valori**_ di un _determinato **tipo**_

-   Attributi
    -   visibilità: public, protected, private
    -   tipi particolari: static final
-   Si usa il camel case, variabili e metodi iniziano con la minuscola e le classi iniziano con la maiuscola
-   La dichiarazione riserva lo spazio nella memoria, ne fissa nome e tipo e definisce lo scope (in quale blocco può essere usata)
-   Prima di usare una determinata variabile è necessario inizializzarla

**Commenti**
// o / \* \* /

**Costanti**
Basta usare la keyword `final` prima del tipo di variabile

-   Questo aumenta la leggibilità
-   Per convenzione il nome della variabile finale dovrebbe essere tutto maiuscolo
-   Il vantaggio è che se lo stesso dato va usato più volte nello stesso programma lo possiamo modificare facilmente

I _literals_ sono i valori costanti (valori scritti direttamenta dall’utente nel programma), vengono sempre interpretati in int32, a meno che non venga specificato in modo diverso:

-   Se voglio assegnare un valore long ad una variabile long devo specificarlo aggiungendo `L` alla fine del numero `long numerone = 43877374218992932L`
-   Stessa cosa per i float `float number = 2.35f`
-   Per i char dobbiamo usare gli apici singoli (’), mentre per le stringhe quelli doppi (")

**Casting**
L’operazione di assegnare valori fra variabili di tipo diverso
Non è possibile assegnare un int in un byte, infatti si rischierebbe di tagliarne il contenuto, per questo otteniamo un errore dal compilatore, questo può essere sovrascritto in questo modo:
`byte i = (byte)variabile_int`
