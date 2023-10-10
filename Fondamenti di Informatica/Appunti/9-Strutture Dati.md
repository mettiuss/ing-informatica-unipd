Definizione: contenitori che organizzano i dati in un formato specifico

Nel scegliere una struttura dati va trovato un compromesso tra queste esigenze:
- Tempo di costruzione della struttura
- tempo di accesso
- occupazione di spazio di memoria
Conoscendo il funzionamento e l'impelementazione delle varie stutture dati ci dà la possibilità di scegliere meglio

- Strutture dati
	- Lineari: array, liste contcatenate
	- non lineari: non le vedremo

- Strutture dati astratte (Abstract data type)
	- Pila, coda (es. undo/redo)
	- Mappa, dizionario
	- Tabella, hashtable
	- insieme

Quasi tutte queste strutture possono essere implementate utilizzando un array, le strutture dati sono una costruzione di una classe che ne controlla l'accesso 
In java useremo le interfacce per descrivere il comportamento della classe


### Contenitore
Alla base di tutti i data type abbiamo il contenitore, il quale contiene solo pochissimi metodi e che verrà poi esteso in base alle necessità

```java
public interface Container {
	boolean isEmpty();
	void makeEmpty(); 
}
```

### Pila
Last In - First out
possiamo rappresentare la pila in verticale o in orizzontale

`push` inserisce un elemento in cima alla pila
`pop` rimuove l'ultimo elemento aggiunto (e lo restituisce)
`top` restituisce l'ultimo elemento

```java
public interface Stack extends Container {
	void push(Object obj);
	Object pop();
	Object top();
}
```

Se viene chiamato pop() su una pila vuota va lanciata un'eccezzione (IllegalStateException può essere usata, oppure ne possiamo creare una nuova)

### Coda
First In, First Out
si aggiunge dalla fine e si toglie dall'inizio

```java
public interface Queue extends Container {
	void enqueue(Object obj);
	Object dequeue();
	Object getFront();
}
```

### deque
pila + coda

```java
public interface Deque extends Container {
	void addFirst(Object obj);
	void addLast(Object obj);
	Object removeFirst() throws EmptyDequeException;
	Object removeLast() throws EmptyDequeException;
	Object getFirst() throws EmptyDequeException;
	Object getLast() throws EmptyDequeException;
}
```

### Mappa
Come dict di python

```java
public interface Map extends Container { 
	/** restituisce il valore al quale la chiave specificata e’ associata, o null se
	questa mappa non contiene associazione per la chiave. */ 
	Object get(Object key); 
	
	/** rimuove l’associazione di chiave specificata se questa e’ presente nella
	mappa. Restituisce il valore associato (o null se non c’era associazione) */
	Object remove(Object key);
	
	/** Inserisce l’associazione di chiave e valore specificati in questa mappa. Se la
	mappa gia’ conteneva un’associazione con questa chiave (altrimenti null), il
	vecchio valore e’ sostituito con il valore specificato */
	Object put(Object key, Object value);
	
	/** restituisce un array contenente le chiavi di tutte le associazioni presenti
	nella mappa */
	Object[] keys();
}
```
