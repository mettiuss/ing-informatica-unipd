package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;

// TODO
/**
 * Test suite per la classe ListAdapter.
 * Verifica il corretto funzionamento della classe ListAdapter e ne verifica la
 * conformità all'interfaccia List di Java 2 SE 1.4.2.
 * 
 * <br>
 * Utilizza JUnit 4.13.2 e può essere complata con il comando:
 * <br>
 * <code>javac -d bin -cp "junit/*" myTest/* myAdapter/*</code>
 * <br>
 * e può essere eseguita con il comando:
 * <br>
 * <code>java -cp "bin:junit/*" org.junit.runner.JUnitCore myTest.ListAdapterTester</code>
 * 
 * <br>
 * <br>
 * <b>Design della Test Suite</b>
 * <ul>
 * <li><b>Implementazione dell'interfaccia</b></li>
 * <li><b>Verifica dei Costruttori</b></li>
 * <li><b>Verifica del metodo checkOutOfBounds</b> su upper e lower bounds</li>
 * <li><b>Verifica del metodo checkOutOfBoundsforAdd</b> su upper e lower
 * bounds</li>
 * <li><b>Verifica del metodo getIndex</b> tramite il metodo get()</li>
 * <li><b>Verifica del metodo startIndex</b> tramite il metodo contains()</li>
 * <li><b>Verifica del metodo add</b> su lista vuota e verifica espansione
 * struttura dati</li>
 * <li><b>Verifica del metodo get</b></li>
 * <li><b>Verifica del metodo iterator</b></li>
 * <li><b>Verifica del metodo add</b></li>
 * <li><b>Verifica del metodo add at index</b></li>
 * <li><b>Verifica del metodo addall</b>con verifica che gli elementi vengano
 * inseriti in ordine al termine della lista</li>
 * <li><b>Verifica del metodo addall at index</b></li>
 * <li><b>Verifica del metodo isEmpty</b></li>
 * <li><b>Verifica del metodo clear</b></li>
 * <li><b>Verifica del metodo containsAll</b> con verifica del caso
 * NullPointer</li>
 * <li><b>Verifica del metodo indexOf e lastIndexOf</b> in caso di elemento
 * esistente o meno nella lista</li>
 * <li><b>Verifica del metodo listIterator e listIterator at index</b> con
 * verifica del caso out of bounds</li>
 * <li><b>Verifica del metodo remove at index</b> con verifica del caso out of
 * bounds</li>
 * <li><b>Verifica del metodo remove</b> con verifica nel caso di duplicati e
 * del caso in cui
 * non esiste l'elemento</li>
 * <li><b>Verifica del metodo removeAll</b> con verifica nel caso di elementi
 * duplicati e nel
 * caso in cui non esiste alcun elemento</li>
 * <li><b>Verifica del metodo set at index</b>con verifica nel caso out of
 * bounds</li>
 * <li><b>Verifica del metodo subList</b> con verifica degli indici out of
 * bounds</li>
 * <li><b>Verifica del metodo toArray</b></li>
 * <li><b>Verifica del metodo toArray con array</b> con verifica del tipo a
 * runtime</li>
 * </ul>
 * 
 * @author Matteo Cuzzolin (2066701)
 */
public class ListAdapterTester {
    /**
     * lista che viene inizializzata prima di ogni test
     */
    private ListAdapter list;

    /**
     * Costruttore di default
     */
    public ListAdapterTester() {
    }

    /**
     * Inizializza la variabile list come una lista vuota di tipo ListAdapter
     * 
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        list = new ListAdapter();
    }

    /**
     * Verifica che la classe ListAdapter implementi l'interfaccia HList
     * <p>
     * <b>Design</b> Il test è motivato dal verificare che ListAdapter sia
     * un'implementazione di HList
     * <br>
     * <b>Description</b> Istanziamo ListAdapter e lo assegnamo ad una variabile di
     * tipo HList, se non insorgono eccezioni il test ha successo.
     * <br>
     * <b>Pre-Condition</b> Nessuna Pre Condizione
     * <br>
     * <b>Post-Condition</b> La variabile contiene un oggetto ListAdapter
     * <br>
     * <b>Expected Results</b> Nessuna eccezione si è verificata
     * </p>
     */
    @Test
    public void implementsHListInterface() {
        HList _ = new ListAdapter();
    }

    /**
     * Verifica del costruttore ListAdapter(int) ritorni un'ecccezione quando il
     * valore è negativo
     * <p>
     * <b>Design</b> Verifica che il costruttore ListAdapter(int) ritorni
     * un'ecccezione
     * quando il valore inserito è negativo
     * <br>
     * <b>Description</b> Istanziamo una nuova lista specificando una capacità
     * negativa e verifichiamo che venga lanciato un errore di tipo
     * {@link IllegalArgumentException}
     * <br>
     * <b>Pre-Condition</b> Nessuna Pre Condizione
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Viene lanciata un'eccezione di tipo
     * {@link IllegalArgumentException}
     * </p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void NegativeCapacityListThrowsIllegalArgumentException() {
        ListAdapter _ = new ListAdapter(-1);
    }

    /**
     * Verifica del costruttore ListAdapter(HCollection)
     * <p>
     * <b>Design</b> Verifica che il costruttore ListAdapter(HCollection) istanzi
     * correttamente un nuovo oggetto di tipo ListAdapter quando gli viene fornito
     * un oggetto di tipo ListAdapter
     * <br>
     * <b>Description</b> Istanziamo un oggetto ListAdapter, inseriamo in esso un
     * elemento, andiamo poi a creare un nuovo oggetto ListAdapter usando il primo
     * oggetto e verifichiamo che l'elemento sia ancora nella lista grazie al metodo
     * get()
     * <br>
     * <b>Pre-Condition</b> Un oggetto ListAdapter con un elemento
     * <br>
     * <b>Post-Condition</b> Il primo oggetto non viene modificato
     * <br>
     * <b>Expected Results</b> Un altro oggetto ListAdapter con lo stesso elemento
     * </p>
     */
    @Test
    public void collectionConstructor() {
        list.add("A");

        ListAdapter list2 = new ListAdapter(list);
        assertEquals("A", list.get(0));
        assertEquals("A", list2.get(0));
    }

    /**
     * Verifica del costruttore ListAdapter(HCollection)
     * <p>
     * <b>Design</b> Verifica che il costruttore ListAdapter(HCollection) lanci
     * l'eccezione NullPointerException quando gli viene fornito un puntatore a null
     * <br>
     * <b>Description</b> Istanziamo un oggetto ListAdapter a null, andiamo poi a
     * creare un nuovo oggetto ListAdapter usando il primo
     * oggetto e verifichiamo che venga lanciata l'eccezione NullPointerException
     * <br>
     * <b>Pre-Condition</b> Un oggetto ListAdapter istanziato a null
     * <br>
     * <b>Post-Condition</b> Il primo oggetto non viene modificato
     * <br>
     * <b>Expected Results</b> Viene lanciata l'ecccesione NullPointerException
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void collectionConstructorThrowsNullPointerException() {
        ListAdapter list1 = null;

        ListAdapter _ = new ListAdapter(list1);
    }

    /**
     * Verifica del funzionamento del metodo startIndex()
     * <p>
     * <b>Design</b> Il test verifica che il metodo startIndex() ritorni il valore 0
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota che contiene uno
     * specifico elemento in posizione 0 e altri elementi diversi successivamente,
     * chiamiamo la funzione contains() con il primo elemento e verifichiamo che
     * ritorni true.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e conforme alle specifiche
     * richieste
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata contains() ritorna true
     * </p>
     */
    @Test()
    public void startIndex() {
        list.add("A");
        list.add("B");
        list.add("B");

        assertTrue(list.contains("A"));
    }

    /**
     * Verifica del funzionamento del metodo getIndex()
     * <p>
     * <b>Design</b> Il test verifica che il metodo getIndex() ritorni lo stesso
     * indice di input
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo get(0) e
     * verifichiamo che venga ritornato il primo elemento.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata get() ritorna il primo elemento
     * </p>
     */
    @Test()
    public void getIndex() {
        list.add("A");

        assertEquals("A", list.get(0));
    }

    /**
     * Verifica del funzionamento del metodo size()
     * <p>
     * <b>Design</b> Il test verifica che il metodo size() su una lista vuota
     * ritorni zero
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota, chiamiamo il metodo
     * size() e verifichiamo che il risultato sia zero
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> La lista rimane vuota e di lunghezza 0
     * <br>
     * <b>Expected Results</b> Il metodo size() ritorna il valore 0
     * </p>
     */
    @Test
    public void emptyListHasSizeZero() {
        assertEquals(0, list.size());
    }

    /**
     * Verifica del funzionamento del metodo size()
     * <p>
     * <br>
     * <b>Design</b> Il test verifica che il metodo size() su una lista di 1
     * elemento ritorni il valore 1
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, inseriamo un oggetto,
     * chiamiamo il metodo
     * size() e verifichiamo che il risultato sia uno
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene esattamente un
     * elemento
     * <br>
     * <b>Post-Condition</b> La lista contiene un elemento
     * <br>
     * <b>Expected Results</b> Il metodo size() ritorna il valore 1
     * </p>
     */
    @Test
    public void oneElementListHasSizeOne() {
        list.add("A");

        assertEquals(1, list.size());
    }

    /**
     * Verifica del funzionamento del metodo add() e get()
     * <p>
     * <b>Design</b> Il test verifica che il metodo add() su una lista vuota
     * aggiunga l'elemento e incrementi la dimensione della lista ad uno
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, inseriamo un oggetto,
     * chiamiamo il metodo get(0) e verifichiamo che l'oggetto sia quello inserito,
     * chiamiamo poi il metodo
     * size() e verifichiamo che il risultato sia uno
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> La lista contiene un elemento
     * <br>
     * <b>Expected Results</b> Il metodo get(0) ritorna l'elemento inserito e il
     * metodo size() ritorna il valore 1
     * </p>
     */
    @Test
    public void addElementAndGet() {
        list.add("A");

        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    /**
     * Verifica del funzionamento del metodo add()
     * <p>
     * <b>Design</b> Il test verifica che il metodo add() aumenti la capacità della
     * lista quando si prova ad aggiungere un ulteriore elemento
     * <br>
     * <b>Description</b> Istanziamo una nuova lista di capacità 1, inseriamo poi 2
     * elementi e verifichiamo che entrambi siano presenti nella lista
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> La lista contiene 2 elementi
     * <br>
     * <b>Expected Results</b> Il metodo get(0) ritorna l'elemento inserito e il
     * metodo size() ritorna il valore 1
     * </p>
     */
    @Test
    public void listExpandsOnAdd() {
        ListAdapter list = new ListAdapter(1);

        list.add("A");
        list.add("B");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    /**
     * Verifica del funzionamento del metodo checkOutOfBounds()
     * <p>
     * <b>Design</b> Il test verifica che il metodo checkOutOfBounds(int)
     * lanci un'eccezione quando si richiede un indice negativo
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, chiamiamo il metodo get(), il
     * quale a sua volta chiama checkOutOfBounds(int) e verifichiamo che venga
     * lanciata
     * l'eccezione {@link IndexOutOfBoundsException}
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il risultato di checkOutOfBounds(-1) lancia
     * l'eccezione
     * {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getLowerIndexOutOfBounds() {
        list.get(-1);
    }

    /**
     * Verifica del funzionamento del metodo checkOutOfBounds()
     * <p>
     * <b>Design</b> Il test verifica che il metodo checkOutOfBounds(int)
     * lanci un'eccezione quando si richiede un indice >= size()
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, chiamiamo il metodo get(), che
     * a sua volta chiama il metodo checkOutOfBounds(int) e verifichiamo che venga
     * lanciata l'eccezione {@link IndexOutOfBoundsException}
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il risultato di checkOutOfBounds(5) lancia
     * l'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getHigherIndexOutOfBounds() {
        list.get(5);
    }

    /**
     * Verifica del funzionamento del metodo iterator()
     * <p>
     * <b>Design</b> Il test verifica che il metodo iterator() su una lista non
     * vuota ritorni un iteratore della lista.
     * <br>
     * <b>Description</b> Istanziamo una nuova lista di lunghezza 10, chiamiamo il
     * metodo iterator() e verifichiamo che gli elementi di iterator siano gli
     * stessi della lista.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene 10 elementi
     * <br>
     * <b>Post-Condition</b> La lista è ancora un'istanza valida e contiene i suoi
     * 10 elementi
     * <br>
     * <b>Expected Results</b> Le chiamate next() ritornano gli elementi della lista
     * </p>
     */
    @Test
    public void iteratorReturnsElementsOfList() {
        for (int i = 0; i < 10; i++) {
            list.add(Math.random());
        }

        HIterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            assertEquals(list.get(i), it.next());
            i++;
        }
    }

    /**
     * Verifica del funzionamento del metodo add(int, Object)
     * <p>
     * <b>Design</b> Il test verifica che il metodo add() su una lista non
     * vuota aggiunga l'elemento all'indice specificato.
     * <br>
     * <b>Description</b> Istanziamo una nuova lista di lunghezza 10, inseriamo un
     * oggetto con add(int, Object) e verifichiamo che la lista contenga tutti gli
     * elementi precedenti più quello aggiunto all'indirizzo corretto
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene 10 elementi
     * <br>
     * <b>Post-Condition</b> La lista è ancora un'istanza valida e contiene 11
     * elementi
     * <br>
     * <b>Expected Results</b> La lista contiene 11 elementi, l'ordine degli
     * elementi è stato mantenuti ed è stato aggiunto l'oggetto richiesto nella
     * posizione richiesta
     * </p>
     */
    @Test
    public void addAtIndex() {
        int index = 5;
        Object element = 5;

        ListAdapter originalList = new ListAdapter();
        for (int i = 0; i < 10; i++) {
            Object obj = Math.random();
            list.add(obj);
            originalList.add(obj);
        }

        list.add(index, element);

        int j = 0;
        for (int i = 0; i < 11; i++) {
            if (i == index) {
                assertEquals(element, list.get(i));
            } else {
                assertEquals(originalList.get(j), list.get(i));
                j++;
            }
        }
    }

    /**
     * Verifica del funzionamento del metodo checkOutOfBoundsforAdd()
     * <p>
     * <b>Design</b> Il test verifica che il metodo checkOutOfBoundsforAdd(int)
     * lanci un'eccezione quando si richiede un indice negativo
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, chiamiamo il metodo add(int,
     * Object), il quale chiama il metodo checkOutOfBoundsforAdd(int) e verifichiamo
     * che venga lanciata l'eccezione {@link IndexOutOfBoundsException}
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il metodo checkOutOfBoundsforAdd(-1) lancia
     * l'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexLowerIndexOutOfBounds() {
        list.add(-1, null);
    }

    /**
     * Verifica del funzionamento del metodo checkOutOfBoundsforAdd()
     * <p>
     * <b>Design</b> Il test verifica che il metodo checkOutOfBoundsforAdd(int)
     * vuota lanci un'eccezione quando si richiede un indice >= size()
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, chiamiamo il metodo add(int,
     * Object), il quale chiama il metodo checkOutOfBoundsforAdd(int) e verifichiamo
     * che venga lanciata l'eccezione {@link IndexOutOfBoundsException}
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il metodo checkOutOfBoundsforAdd(5) lancia
     * l'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexHigherIndexOutOfBounds() {
        list.add(5, null);
    }

    /**
     * Verifica del funzionamento del metodo addAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo addAll(HCollection) inserisca
     * tutti gli elementi della collezione su una lista vuota
     * <br>
     * <b>Description</b> Istanziamo una lista vuota e una lista con un elemento.
     * Chiamiamo poi addAll(listapiena) sulla lista vuota
     * <br>
     * <b>Pre-Condition</b> Due liste, una vuota ed una con un elemento
     * <br>
     * <b>Post-Condition</b> La lista con un elemento mantiene quell'elemento
     * <br>
     * <b>Expected Results</b> Sulla lista vuota si trova l'elemento della lista
     * piena.
     * </p>
     */
    @Test
    public void addAllAddsElement() {
        ListAdapter list2 = new ListAdapter();
        list2.add("A");

        list.addAll(list2);
        assertEquals("A", list.get(0));
        assertEquals("A", list2.get(0));
    }

    /**
     * Verifica del funzionamento del metodo addAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo addAll(HCollection) inserisca
     * tutti gli elementi della collezione alla fine della lista.
     * <br>
     * <b>Description</b> Istanziamo due liste con un elemento. Inseriamo gli
     * elementi dell'una sull'altra. Ci aspettiamo che l'ordine degli elementi sia
     * corretto.
     * <br>
     * <b>Pre-Condition</b> Due liste, entrambe con due elementi
     * <br>
     * <b>Post-Condition</b> Una delle due liste rimane invariata
     * <br>
     * <b>Expected Results</b> Una delle due liste contiene tutti gli elementi,
     * ponendo prima i suoi originali e dopo quelli aggiunti dall'altra lista
     * </p>
     */
    @Test
    public void addAllAddsElementAtEnd() {
        ListAdapter list2 = new ListAdapter();
        list.add("A");
        list2.add("B");

        list.addAll(list2);
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("B", list2.get(0));
        assertEquals(2, list.size());
        assertEquals(1, list2.size());
    }

    /**
     * Verifica del funzionamento del metodo addAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo addAll(HCollection) inserisca
     * tutti gli elementi della collezione mantenendo il loro ordine.
     * <br>
     * <b>Description</b> Istanziamo due liste una vuota ed una con due elementi,
     * inseriamo gli elementi della seconda sulla prima.
     * <br>
     * <b>Pre-Condition</b> Due liste, una vuota ed una con più di un elemento
     * <br>
     * <b>Post-Condition</b> Una delle due liste rimane invariata
     * <br>
     * <b>Expected Results</b> Una delle due liste contiene tutti gli elementi,
     * mantenedo l'ordine della lista iniziale
     * </p>
     */
    @Test
    public void addAllAddsElementWithOrder() {
        ListAdapter list2 = new ListAdapter();
        list2.add("A");
        list2.add("B");

        list.addAll(list2);
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    /**
     * Verifica del funzionamento del metodo AddAll(int, HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo AddAll(int, HCollection)
     * inserisca tutti gli elementi della collezione su una lista
     * <br>
     * <b>Description</b> Due liste con un elemento. Chiamiamo poi lista1.addAll(0,
     * lista2) e verifichiamo che gli elementi vengano correttamente inseriti
     * all'indirizzo specificato
     * <br>
     * <b>Pre-Condition</b> Due liste ogniuna con un elemento
     * <br>
     * <b>Post-Condition</b> Una lista rimane invariata
     * <br>
     * <b>Expected Results</b> Una lista ha 2 elementi, il primo dell'altra lista,
     * il secondo è quello presente inizialmente
     * </p>
     */
    @Test
    public void addAllAtIndexAddsElement() {
        ListAdapter list2 = new ListAdapter();
        list.add("A");
        list2.add("B");

        list.addAll(0, list2);
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
        assertEquals("B", list2.get(0));
        assertEquals(2, list.size());
        assertEquals(1, list2.size());
    }

    /**
     * Verifica del funzionamento del metodo AddAll(int, HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo AddAll(int, HCollection)
     * inserisca
     * tutti gli elementi della collezione mantenendo il loro ordine.
     * <br>
     * <b>Description</b> Istanziamo due liste una con un elemento ed una con due
     * elementi, inseriamo gli elementi della seconda sulla prima.
     * <br>
     * <b>Pre-Condition</b> Due liste, una con un elemento ed una con più di un
     * elemento
     * <br>
     * <b>Post-Condition</b> Una delle due liste rimane invariata
     * <br>
     * <b>Expected Results</b> Una delle due liste contiene tutti gli elementi,
     * mantenedo l'ordine della lista iniziale
     * </p>
     */
    @Test
    public void addAllAtIndexAddsElementWithOrder() {
        ListAdapter list2 = new ListAdapter();
        list.add("A");
        list2.add("B");
        list2.add("C");

        list.addAll(0, list2);
        assertEquals("B", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals("A", list.get(2));
        assertEquals("B", list2.get(0));
        assertEquals("C", list2.get(1));
        assertEquals(3, list.size());
        assertEquals(2, list2.size());
    }

    /**
     * Verifica del funzionamento del metodo isEmpty()
     * <p>
     * <b>Design</b> Il test verifica che il metodo isEmpty() su una lista vuota
     * ritorni true
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota, chiamiamo il metodo
     * isEmpty()
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata ed è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane vuota
     * <br>
     * <b>Expected Results</b> La chiamata isEmpty() ritorna true
     * </p>
     */
    @Test
    public void isEmptyOnEmpty() {
        assertTrue(list.isEmpty());
    }

    /**
     * Verifica del funzionamento del metodo isEmpty()
     * <p>
     * <b>Design</b> Il test verifica che il metodo isEmpty() su una lista non vuota
     * ritorni false
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo il metodo
     * isEmpty()
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata isEmpty() ritorna false
     * </p>
     */
    @Test
    public void isEmptyOnNonEmpty() {
        list.add(null);

        assertFalse(list.isEmpty());
    }

    /**
     * Verifica del funzionamento del metodo clear()
     * <p>
     * <b>Design</b> Il test verifica che il metodo clear() rimuova tutti gli
     * elementi da una lista non vuota
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo il metodo
     * clear() e verifichiamo che la lista sia poi vuota con isEmpty()
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La lista è vuota
     * <br>
     * <b>Expected Results</b> La chiamata isEmpty() ritorna true
     * </p>
     */
    @Test
    public void clearEmptiesList() {
        list.add(5);

        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
    }

    /**
     * Verifica del funzionamento del metodo contains()
     * <p>
     * <b>Design</b> Il test verifica che il metodo contains() ritorni true quando
     * l'elemento è presente nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo il metodo
     * contains() con un elemento presente e verifichiamo ritorni true
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata contains() ritorna true
     * </p>
     */
    @Test
    public void containsReturnsTrue() {
        list.add("A");

        assertTrue(list.contains("A"));
    }

    /**
     * Verifica del funzionamento del metodo contains()
     * <p>
     * <b>Design</b> Il test verifica che il metodo contains() ritorni false quando
     * l'elemento non è presente nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo il metodo
     * contains() con un elemento non presente e verifichiamo ritorni false
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata contains() ritorna false
     * </p>
     */
    @Test
    public void containsReturnsFalse() {
        list.add("A");

        assertFalse(list.contains("B"));
    }

    /**
     * Verifica del funzionamento del metodo containsAll()
     * <p>
     * <b>Design</b> Il test verifica che il metodo contains() ritorni true quando
     * tutti gli elementi sono presenti nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo il metodo
     * containsAll() con elementi presenti nella lista e verifichiamo che ritorni
     * true
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata containsAll() ritorna true
     * </p>
     */
    @Test
    public void containsAllReturnsTrue() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        ListAdapter container = new ListAdapter();
        container.add("A");
        container.add("B");

        assertTrue(list.containsAll(container));
    }

    /**
     * Verifica del funzionamento del metodo containsAll()
     * <p>
     * <b>Design</b> Il test verifica che il metodo containsAll() ritorni false
     * quando un elemento non è presente nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota, chiamiamo il metodo
     * containsAll() con una collezione contenente anche elementi non presenti nella
     * lsita e verifichiamo ritorni false
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata containsAll() ritorna false
     * </p>
     */
    @Test
    public void containsAllReturnsFalse() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        ListAdapter container = new ListAdapter();
        container.add("A");
        container.add("F");

        assertFalse(list.containsAll(container));
    }

    /**
     * Verifica del funzionamento del metodo containsAll()
     * <p>
     * <b>Design</b> Il test verifica che il metodo containsAll() con parametro null
     * ritorni l'eccezione NullPointerException
     * <br>
     * <b>Description</b> Istanziamo una nuova lista ed invochiamo il metodo
     * containsAll(null)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata containsAll() lancia l'eccezione
     * NullPointerEception
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void containsAllNullPointerException() {
        list.containsAll(null);
    }

    /**
     * Verifica del funzionamento del metodo equals().
     * <p>
     * <b>Design</b> Il test verifica che equals ritorni vero se entrambe le liste
     * sono vuote.
     * <br>
     * <b>Description</b> Istanziamo due liste vuote e verifichiamo che equals
     * ritorni true.
     * <br>
     * <b>Pre-Condition</b> Entrambe le liste sono istanziate e vuote.
     * <br>
     * <b>Post-Condition</b> Le due liste non sono state modificate.
     * <br>
     * <b>Expected Results</b> Il metodo equals ritorna vero.
     * </p>
     */
    @Test
    public void equalsEmptyLists() {
        ListAdapter list2 = new ListAdapter();

        assertTrue(list.equals(list2));
    }

    /**
     * Verifica del funzionamento del metodo equals().
     * <p>
     * <b>Design</b> Il test verifica che equals ritorni true quando le liste
     * sono uguali.
     * <br>
     * <b>Description</b> Istanziamo due liste con gli stessi elementi e
     * verifichiamo che equals ritorni vero.
     * <br>
     * <b>Pre-Condition</b> Entrambe le liste sono instanziate e contengono gli
     * stessi elementi.
     * <br>
     * <b>Post-Condition</b> Le due liste non sono state modificate.
     * <br>
     * <b>Expected Results</b> Il metodo equals ritorna vero.
     * </p>
     */
    @Test
    public void equalsSameContentLists() {
        list.add("A");
        list.add("B");

        ListAdapter list2 = new ListAdapter();
        list2.add("A");
        list2.add("B");

        assertTrue(list.equals(list2));
    }

    /**
     * Verifica del funzionamento del metodo equals().
     * <p>
     * <b>Design</b> Il test verifica che equals ritorni falso se le liste hanno
     * dimensioni diverse.
     * <br>
     * <b>Description</b> Istanziamo due liste di dimensioni diverse e verifichiamo
     * che equals ritorni false.
     * <br>
     * <b>Pre-Condition</b> Le liste sono instanziate e hanno dimensioni diverse.
     * <br>
     * <b>Post-Condition</b> Le due liste sono invariate
     * <br>
     * <b>Expected Results</b> Il metodo equals ritorna falso.
     * </p>
     */
    @Test
    public void equalsDifferentSizeLists() {
        list.add("A");

        ListAdapter list2 = new ListAdapter();
        list2.add("A");
        list2.add("B");

        assertFalse(list.equals(list2));
    }

    /**
     * Verifica del funzionamento del metodo equals().
     * <p>
     * <b>Design</b> Il test verifica che equals ritorni false se le liste
     * contengono elementi diversi, pur essendo della stessa lunghezza.
     * <br>
     * <b>Description</b> Istanziamo due liste di uguale lunghezza ma elementi
     * diversi e verifichiamo che equals ritorni falso.
     * <br>
     * <b>Pre-Condition</b> Le liste contengono elementi diversi e sono della stessa
     * dimensione
     * <br>
     * <b>Post-Condition</b> Le due liste sono invariate
     * <br>
     * <b>Expected Results</b> Il metodo equals ritorna false.
     * </p>
     */
    @Test
    public void equalsDifferentContentLists() {
        list.add("A");

        ListAdapter list2 = new ListAdapter();
        list2.add("B");

        assertFalse(list.equals(list2));
    }

    /**
     * Verifica del funzionamento del metodo equals().
     * <p>
     * <b>Design</b> Il test verifica che equals ritorni false quando una lista
     * viene confrontata con null.
     * <br>
     * <b>Description</b> Instanziamo una lista e la confrontiamo con null,
     * verifichiamo che equals ritorni false.
     * <br>
     * <b>Pre-Condition</b> Una lista instanziata
     * <br>
     * <b>Post-Condition</b> La lista non viene modificata
     * <br>
     * <b>Expected Results</b> Il metodo equals ritorna false.
     * </p>
     */
    @Test
    public void equalsNull() {
        assertFalse(list.equals(null));
    }

    /**
     * Verifica del funzionamento del metodo equals().
     * <p>
     * <b>Design</b> Il test verifica che equals ritorni false se viene confrontato
     * con un oggetto di un'altra classe.
     * <br>
     * <b>Description</b> Instanziamo una lista e la confrontiamo con un oggetto di
     * un'altra classe, verifichiamo che equals ritorni false.
     * <br>
     * <b>Pre-Condition</b> Una lista instanziata e un oggetto di un tipo diverso
     * <br>
     * <b>Post-Condition</b> La stringa e l'oggetto non vengono modifcati
     * <br>
     * <b>Expected Results</b> Il metodo equals ritorna false.
     * </p>
     */
    @Test
    public void equalsDifferentClassObject() {
        String str = "A String Object";

        assertFalse(list.equals(str));
    }

    /**
     * Verifica del funzionamento del metodo hashCode().
     * <p>
     * <b>Design</b> Il test verifica che hashCode ritorni lo stesso valore per due
     * liste uguali.
     * <br>
     * <b>Description</b> Istanziamo due liste con gli stessi elementi e
     * verifichiamo che hashCode ritorni lo stesso valore.
     * <br>
     * <b>Pre-Condition</b> Le liste sono instanziate e contengono gli stessi
     * elementi.
     * <br>
     * <b>Post-Condition</b> Le due liste non sono state modificate.
     * <br>
     * <b>Expected Results</b> Il metodo hashCode ritorna lo stesso valore per
     * entrambe le liste.
     * </p>
     */
    @Test
    public void hashCodeSameContentLists() {
        list.add("A");
        list.add("B");

        ListAdapter list2 = new ListAdapter();
        list2.add("A");
        list2.add("B");

        assertEquals(list.hashCode(), list2.hashCode());
    }

    /**
     * Verifica del funzionamento del metodo hashCode().
     * <p>
     * <b>Design</b> Il test verifica che hashCode ritorni valori diversi per liste
     * con contenuti diversi.
     * <br>
     * <b>Description</b> Istanziamo due liste con elementi diversi e verifichiamo
     * che hashCode ritorni valori differenti.
     * <br>
     * <b>Pre-Condition</b> Le liste sono instanziate e contengono elementi diversi.
     * <br>
     * <b>Post-Condition</b> Le due liste non sono state modificate.
     * <br>
     * <b>Expected Results</b> Il metodo hashCode ritorna valori differenti per le
     * due liste.
     * </p>
     */
    @Test
    public void hashCodeDifferentContentLists() {
        list.add("A");

        ListAdapter list2 = new ListAdapter();
        list2.add("B");

        assertNotEquals(list.hashCode(), list2.hashCode());
    }

    /**
     * Verifica del funzionamento del metodo hashCode().
     * <p>
     * <b>Design</b> Il test verifica che hashCode ritorni un valore consistente per
     * una lista immutata.
     * <br>
     * <b>Description</b> Istanziamo una lista, calcoliamo il suo hashCode, lo
     * ricalcoliamo e verifichiamo che non cambi.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non viene modificata.
     * <br>
     * <b>Post-Condition</b> La lista non è stata modificata.
     * <br>
     * <b>Expected Results</b> Il metodo hashCode ritorna lo stesso valore per la
     * lista non modificata.
     * </p>
     */
    @Test
    public void hashCodeConsistent() {
        list.add("A");
        list.add("B");

        int hashCode1 = list.hashCode();
        int hashCode2 = list.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    /**
     * Verifica del funzionamento del metodo indexOf()
     * <p>
     * <b>Design</b> Il test verifica che il metodo indexOf() ritorni il corretto
     * indice dell'oggetto nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota ed invochiamo il
     * metodo indexOf() su un elemento di cui conosciamo l'indice, così da poter
     * verificare la correttezza.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata indexOf() ritorna il corretto indice
     * dell'elemento
     * </p>
     */
    @Test
    public void indexOfExistingElement() {
        for (int i = 0; i < 10; i++) {
            list.add(Math.random());
        }

        assertEquals(5, list.indexOf(list.get(5)));
    }

    /**
     * Verifica del funzionamento del metodo indexOf()
     * <p>
     * <b>Design</b> Il test verifica che il metodo indexOf() ritorni -1 quando non
     * viene trovato l'elemento
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota ed invochiamo il metodo
     * indexOf() con un elemento qualsiasi
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata indexOf() ritorna -1
     * </p>
     */
    @Test
    public void indexOfNonExisting() {
        assertEquals(-1, list.indexOf("A"));
    }

    /**
     * Verifica del funzionamento del metodo lastindexOf()
     * <p>
     * <b>Design</b> Il test verifica che il metodo lastindexOf() ritorni il
     * corretto
     * indice dell'oggetto nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota ed invochiamo il
     * metodo lastindexOf() su un elemento di cui conosciamo l'indice, così da poter
     * verificare la correttezza.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata lastindexOf() ritorna il corretto indice
     * dell'elemento
     * </p>
     */
    @Test
    public void lastindexOfExistingElement() {
        for (int i = 0; i < 10; i++) {
            list.add("A");
        }

        assertEquals(9, list.lastIndexOf(list.get(5)));
    }

    /**
     * Verifica del funzionamento del metodo lastindexOf()
     * <p>
     * <b>Design</b> Il test verifica che il metodo lastindexOf() ritorni -1 quando
     * non
     * viene trovato l'elemento
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota ed invochiamo il metodo
     * lastindexOf() con un elemento qualsiasi
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata lastindexOf() ritorna -1
     * </p>
     */
    @Test
    public void lastindexOfNonExisting() {
        assertEquals(-1, list.lastIndexOf("A"));
    }

    /**
     * Verifica del funzionamento del metodo listIterator()
     * <p>
     * <b>Design</b> Il test verifica che il metodo listIterator() ritorni un
     * iteratore valido di tipo HListIterator
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota e invochiamo il metodo
     * listIterator().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata listIterator() ritorna un iteratore di
     * tipo HListIterator
     * </p>
     */
    @Test
    public void listIterator() {
        HListIterator _ = list.listIterator();
    }

    /**
     * Verifica del funzionamento del metodo listIterator(int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo listIterator() ritorni un
     * iteratore valido di tipo HListIterator all'indice specificato
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e invochiamo il
     * metodo
     * listIterator(index) e verifichiamo che nextIndex() sia uguale a index
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata listIterator() ritorna un iteratore di
     * tipo HListIterator inizializzato all'indice specificato
     * </p>
     */
    @Test
    public void listIteratorIndex() {
        list.add("A");
        list.add("B");
        HListIterator it = list.listIterator(1);
        assertEquals(1, it.nextIndex());
    }

    /**
     * Verifica del funzionamento del metodo remove(int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo remove() rimuova l'elemento
     * all'indice specificato e ritorni l'elemento rimosso
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo remove(int)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> L'elemento all'indice specificato è stato rimosso
     * <br>
     * <b>Expected Results</b> La chiamata remove() ritorna l'elemento rimosso e
     * l'elemento non è più presente nella lista
     * </p>
     */
    @Test
    public void removeElementAtIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.remove(1));
        assertFalse(list.contains("B"));
        assertEquals(2, list.size());
    }

    /**
     * Verifica del funzionamento del metodo remove(Object)
     * <p>
     * <b>Design</b> Il test verifica che il metodo remove() rimuova la prima
     * occorrenza dell'oggetto specificato
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, aggiungiamo elementi e
     * invochiamo il metodo remove(Object) con un oggetto presente nella lista
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non è vuota
     * <br>
     * <b>Post-Condition</b> La prima occorrenza dell'oggetto specificato viene
     * rimossa
     * <br>
     * <b>Expected Results</b> La chiamata remove() ritorna true e l'oggetto non è
     * più presente nella lista
     * </p>
     */
    @Test
    public void removeExistingElement() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertTrue(list.remove("B"));
        assertFalse(list.contains("B"));
        assertEquals(2, list.size());
    }

    /**
     * Verifica del funzionamento del metodo remove(Object)
     * <p>
     * <b>Design</b> Il test verifica che il metodo remove() rimuova solo la prima
     * occorrenza dell'oggetto specificato quando ci sono duplicati nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e contenente almeno
     * un duplicato invochiamo il metodo remove(Object) con l'oggetto duplicato
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata, non vuota e contiene duplicati
     * <br>
     * <b>Post-Condition</b> La prima occorrenza dell'oggetto specificato è stata
     * rimossa, le altre occorrenze rimangono
     * <br>
     * <b>Expected Results</b> La chiamata remove() ritorna true e solo la prima
     * occorrenza dell'oggetto specificato è stata rimossa
     * </p>
     */
    @Test
    public void removeFirstOccurrenceOfDuplicateElement() {
        list.add("A");
        list.add("B");
        list.add("B");
        list.add("C");

        assertTrue(list.remove("B"));
        assertTrue(list.contains("B"));
        assertEquals(3, list.size());
    }

    /**
     * Verifica del funzionamento del metodo remove(Object)
     * <p>
     * <b>Design</b> Il test verifica che il metodo remove() ritorni false quando
     * l'oggetto specificato non è presente nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota e
     * invochiamo il metodo remove(Object) con un oggetto non presente nella lista
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata ed è vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata remove() ritorna false
     * </p>
     */
    @Test
    public void removeNonExistingElement() {
        assertFalse(list.remove("A"));
        assertEquals(0, list.size());
    }

    /**
     * Verifica del funzionamento del metodo remove(Object)
     * <p>
     * <b>Design</b> Il test verifica che il metodo remove() rimuova correttamente
     * anche un elemento null
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, aggiungiamo l'elemento null e
     * invochiamo il metodo remove(null)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene un elemento null
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata remove() rimuove correttamente l'elemento
     * </p>
     */
    @Test()
    public void removeNullElement() {
        list.add(null);

        list.remove(null);

        assertEquals(0, list.size());
    }

    /**
     * Verifica del funzionamento del metodo removeAll(HCollection c)
     * <p>
     * <b>Design</b> Il test verifica che il metodo removeAll() rimuova tutti gli
     * elementi della collezione specificata dalla lista
     * <br>
     * <b>Description</b> Istanziamo due liste non vuote e invochiamo il metodo
     * list1.removeAll(list2). list2 possiede alcuni elementi di list1
     * <br>
     * <b>Pre-Condition</b> le due liste sono istanziate
     * <br>
     * <b>Post-Condition</b> Tutti gli elementi di list1 presenti su list2 sono
     * rimossi
     * <br>
     * <b>Expected Results</b> La chiamata removeAll() ritorna true e gli elementi
     * specificati non sono più presenti nella lista1
     * </p>
     */
    @Test
    public void removeAllExistingElements() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        HCollection collection = new ListAdapter();
        collection.add("B");
        collection.add("C");

        assertTrue(list.removeAll(collection));
        assertFalse(list.contains("B"));
        assertFalse(list.contains("C"));
        assertEquals(2, list.size());
    }

    /**
     * Verifica del funzionamento del metodo removeAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo removeAll() ritorni false quando
     * nessuno degli elementi della collezione specificata è presente nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo removeAll(HCollection) con una collezione contenente
     * elementi non presenti nella lista
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata removeAll() ritorna false
     * </p>
     */
    @Test
    public void removeAllNonExistingElements() {
        list.add("A");
        list.add("B");
        list.add("C");

        HCollection collection = new ListAdapter();
        collection.add("D");
        collection.add("E");

        assertFalse(list.removeAll(collection));
        assertEquals(3, list.size());
    }

    /**
     * Verifica del funzionamento del metodo removeAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo removeAll() rimuova
     * correttamente tutti gli elementi duplicati della collezione specificata dalla
     * lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e con elementi
     * duplicati. Invochiamo il metodo removeAll(HCollection)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene elementi duplicati
     * <br>
     * <b>Post-Condition</b> Tutte le occorrenze degli elementi specificati sono
     * rimosse dalla lista
     * <br>
     * <b>Expected Results</b> La chiamata removeAll() ritorna true
     * </p>
     */
    @Test
    public void removeAllDuplicateElements() {
        list.add("A");
        list.add("B");
        list.add("B");
        list.add("C");
        list.add("C");

        HCollection collection = new ListAdapter();
        collection.add("B");
        collection.add("C");

        assertTrue(list.removeAll(collection));
        assertFalse(list.contains("B"));
        assertFalse(list.contains("C"));
        assertEquals(1, list.size());
    }

    /**
     * Verifica del funzionamento del metodo removeAll(HCollection c)
     * <p>
     * <b>Design</b> Il test verifica che il metodo removeAll() ritorni false quando
     * viene invocato su una lista vuota
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota e invochiamo il metodo
     * removeAll()
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata removeAll() ritorna false
     * </p>
     */
    @Test
    public void removeAllEmptyList() {
        HCollection collection = new ListAdapter();

        assertFalse(list.removeAll(collection));
        assertEquals(0, list.size());
    }

    /**
     * Verifica del funzionamento del metodo removeAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo removeAll() lanci l'eccezione
     * NullPointerException quando la collezione specificata è null
     * <br>
     * <b>Description</b> Istanziamo una nuova lista vuota e invochiamo il metodo
     * removeAll(null)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata removeAll() lancia NullPointerException
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void removeAllNullPointerException() {
        list.removeAll(null);
    }

    /**
     * Verifica del funzionamento del metodo retainAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo retainAll() mantenga solo gli
     * elementi nella lista che sono contenuti nella collezione specificata
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo retainAll() con una collezione contenente
     * alcuni elementi presenti nella lista
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> Solo gli elementi della collezione specificata sono
     * rimasti nella lista
     * <br>
     * <b>Expected Results</b> La chiamata retainAll() ritorna true
     * </p>
     */
    @Test
    public void retainAllExistingElements() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        HCollection collection = new ListAdapter();
        collection.add("A");
        collection.add("B");

        assertTrue(list.retainAll(collection));
        assertTrue(list.contains("A"));
        assertTrue(list.contains("B"));
        assertFalse(list.contains("C"));
        assertFalse(list.contains("D"));
        assertEquals(2, list.size());
    }

    /**
     * Verifica del funzionamento del metodo retainAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo retainAll() ritorni false quando
     * la lista già contiene solo gli elementi presenti nella collezione specificata
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo retainAll(HCollection) con la lista stessa
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata retainAll() ritorna false
     * </p>
     */
    @Test
    public void retainAllNoModification() {
        list.add("A");

        assertFalse(list.retainAll(list));
        assertTrue(list.contains("A"));
        assertEquals(1, list.size());
    }

    /**
     * Verifica del funzionamento del metodo retainAll(HCollection)
     * <p>
     * <b>Design</b> Il test verifica che il metodo retainAll(null) lanci
     * un'eccezione
     * NullPointerException
     * <br>
     * <b>Description</b> Istanziamo una nuova lista e invochiamo il metodo
     * retainAll(null)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata retainAll() lancia NullPointerException
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void retainAllThrowsNullPointerException() {
        list.retainAll(null);
    }

    /**
     * Verifica del funzionamento del metodo set(int, Object)
     * <p>
     * <b>Design</b> Il test verifica che il metodo set() sostituisca correttamente
     * l'elemento alla posizione specificata
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo set() con un nuovo elemento
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> L'elemento alla posizione specificata viene sostituito
     * con il nuovo elemento
     * <br>
     * <b>Expected Results</b> La chiamata set() ritorna l'elemento precedentemente
     * alla posizione specificata
     * </p>
     */
    @Test
    public void setElement() {
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.set(1, "D"));
        assertEquals("A", list.get(0));
        assertEquals("D", list.get(1));
        assertEquals("C", list.get(2));
    }

    /**
     * Verifica del funzionamento del metodo subList(int, int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo subList() ritorni una vista
     * della porzione specificata della lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo subList() con indici validi
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La sublist contiene gli elementi compresi tra gli
     * indici specificati
     * <br>
     * <b>Expected Results</b> La chiamata subList() ritorna una sublist contenente
     * gli elementi specificati
     * </p>
     */
    @Test
    public void subListValidIndices() {
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        HList subList = list.subList(1, 3);
        assertEquals(2, subList.size());
        assertEquals("B", subList.get(0));
        assertEquals("C", subList.get(1));
    }

    /**
     * Verifica del funzionamento del metodo subList(int, int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo subList() lanci un'eccezione
     * IndexOutOfBoundsException quando fromIndex è negativo
     * <br>
     * <b>Description</b> Istanziamo una nuova lista e invochiamo il metodo
     * subList() con un fromIndex negativo
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata subList() lancia
     * IndexOutOfBoundsException
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListNegativeFromIndex() {
        list.add("A");
        list.add("B");

        list.subList(-1, 1);
    }

    /**
     * Verifica del funzionamento del metodo subList(int, int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo subList() lanci un'eccezione
     * IndexOutOfBoundsException quando toIndex è maggiore della dimensione della
     * lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista e invochiamo il metodo
     * subList() con un toIndex maggiore della dimensione della lista
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata subList() lancia
     * IndexOutOfBoundsException
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListToIndexGreaterThanSize() {
        list.add("A");
        list.add("B");

        list.subList(0, 3);
    }

    /**
     * Verifica del funzionamento del metodo subList(int, int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo subList() lanci un'eccezione
     * IndexOutOfBoundsException quando fromIndex è maggiore di toIndex
     * <br>
     * <b>Description</b> Istanziamo una nuova lista e invochiamo il metodo
     * subList() con fromIndex maggiore di toIndex
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene elementi
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata subList() lancia
     * IndexOutOfBoundsException
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListFromIndexGreaterThanToIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        list.subList(2, 1);
    }

    /**
     * Verifica del funzionamento del metodo subList(int, int)
     * <p>
     * <b>Design</b> Il test verifica che il metodo subList() ritorni una sublist
     * vuota quando fromIndex è uguale a toIndex
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, aggiungiamo elementi e
     * invochiamo il metodo subList(0, 0)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La sublist è vuota
     * <br>
     * <b>Expected Results</b> La chiamata subList() ritorna una sublist vuota
     * </p>
     */
    @Test
    public void subListFromIndexEqualsToIndex() {
        list.add("A");
        list.add("B");

        ListAdapter sub = list.subList(0, 0);
        assertEquals(0, sub.size());
    }

    /**
     * Verifica del funzionamento del metodo toArray()
     * <p>
     * <b>Design</b> Il test verifica che il metodo toArray() ritorni un array
     * contenente tutti gli elementi della lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e
     * invochiamo il metodo toArray()
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e contiene gli elementi aggiunti
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata toArray() ritorna un array contenente
     * tutti gli elementi della lista nell'ordine corretto
     * </p>
     */
    @Test
    public void toArray() {
        list.add("A");
        list.add("B");
        list.add("C");

        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }

    /**
     * Verifica del funzionamento del metodo toArray(Object[])
     * <p>
     * <b>Design</b> Il test verifica che il metodo toArray() ritorni un
     * array contenente tutti gli elementi della lista
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota e un array di
     * dimensione maggiore o uguale alla lista. Invochiamo il metodo toArray() con
     * l'array
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota e l'array è di
     * dimensione maggiore o ugugale
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata toArray() ritorna un array dello stesso
     * tipo dell'array iniziale contenente tutti gli elementi della lista
     * nell'ordine corretto
     * </p>
     */
    @Test
    public void toArrayWithSufficientArray() {
        list.add("A");
        list.add("B");

        String[] array = new String[list.size()];
        Object[] resultArray = list.toArray(array);
        assertSame(array, resultArray);
        assertTrue(resultArray instanceof String[]);
        assertEquals("A", resultArray[0]);
        assertEquals("B", resultArray[1]);
    }

    /**
     * Verifica del funzionamento del metodo toArray(Object[])
     * <p>
     * <b>Design</b> Il test verifica che il metodo toArray() ritorni un
     * nuovo array quando l'array passato non è sufficientemente grande
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuoto e
     * invochiamo il metodo toArray() con un array di dimensione
     * insufficiente
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota e l'array è istanziato
     * con dimensione inferiore alla lista
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata toArray() ritorna un nuovo
     * array contenente tutti gli elementi della lista nell'ordine corretto
     * </p>
     */
    @Test
    public void toArrayWithInsufficientArray() {
        list.add("A");
        list.add("B");

        String[] array = new String[1];
        Object[] resultArray = list.toArray(array);
        assertNotSame(array, resultArray);
        assertEquals("A", resultArray[0]);
        assertEquals("B", resultArray[1]);
    }

    /**
     * Verifica del funzionamento del metodo toArray(Object[])
     * <p>
     * <b>Design</b> Il test verifica che il metodo toArray() lanci
     * NullPointerException quando l'array passato è null
     * <br>
     * <b>Description</b> Istanziamo una nuova lista e invochiamo il metodo
     * toArray(null)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata toArray() lancia
     * NullPointerException
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void toArrayThrowsNullPointerException() {
        list.toArray(null);
    }

    /**
     * Verifica del funzionamento del metodo startIndex()
     * <p>
     * <b>Design</b> Il test verifica che il metodo startIndex() ritorni 0;
     * <br>
     * <b>Description</b> Istanziamo una nuova lista e invochiamo il metodo
     * toArray(null)
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata toArray() lancia
     * NullPointerException
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void startIndexReturns0() {
        list.toArray(null);
    }

}
