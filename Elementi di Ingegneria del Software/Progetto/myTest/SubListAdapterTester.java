package myTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;

/**
 * Test suite per la classe SubListAdapter.
 * Verifica il corretto funzionamento della classe SubListAdapter e ne verifica
 * la conformità all'interfaccia List di Java 2 SE 1.4.2.
 * 
 * <br>
 * Utilizza JUnit 4.13.2 e può essere complata con il comando:
 * <br>
 * <code>javac -d bin -cp "junit/*" myTest/* myAdapter/*</code>
 * <br>
 * e può essere eseguita con il comando:
 * <br>
 * <code>java -cp "bin:junit/*" org.junit.runner.JUnitCore myTest.SubListAdapterTester</code>
 * 
 * <br>
 * <br>
 * <b>Design della Test Suite</b>
 * <ul>
 * <li><b>Verifica del metodo size()</b> nel caso di sottolista vuota o
 * piena</li>
 * <li><b>Verifica del metodo changeSize()</b> tramite il metodo size()</li>
 * <li><b>Verifica del metodo getIndex()</b> tramite il metodo get()</li>
 * <li><b>Verifica del metodo startIndex()</b> tramite il metodo contains()</li>
 * </ul>
 * 
 * @author Matteo Cuzzolin (2066701)
 */
public class SubListAdapterTester {
    /**
     * lista che viene inizializzata prima di ogni test
     */
    private ListAdapter list;

    /**
     * Costruttore di default
     */
    public SubListAdapterTester() {
    }

    /**
     * Inizializza la variabile sublist come una lista vuota di tipo
     * ListAdapter
     * 
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        list = new ListAdapter();
    }

    /**
     * Verifica del funzionamento del metodo size()
     * <p>
     * <b>Design</b> Il test verifica che il metodo size() su una sottolista vuota
     * ritorni zero
     * <br>
     * <b>Description</b> Istanziamo una nuova sottolista vuota, chiamiamo il metodo
     * size() e verifichiamo che il risultato sia zero
     * <br>
     * <b>Pre-Condition</b> La sottolista è istanziata e non contiene alcun elemento
     * <br>
     * <b>Post-Condition</b> La sottolista rimane vuota e di lunghezza 0
     * <br>
     * <b>Expected Results</b> Il metodo size() ritorna il valore 0
     * </p>
     */
    @Test
    public void emptySubListHasSizeZero() {
        ListAdapter subList = list.subList(0, 0);
        assertEquals(0, subList.size());
    }

    /**
     * Verifica del funzionamento del metodo size()
     * <p>
     * <br>
     * <b>Design</b> Il test verifica che il metodo size() su una sottolista di 1
     * elemento ritorni il valore 1
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, inseriamo un oggetto, creiamo
     * una sottolista che comprenda l'elemento, chiamiamo il metodo
     * size() e verifichiamo che il risultato sia uno
     * <br>
     * <b>Pre-Condition</b> La sottolista è istanziata e contiene esattamente un
     * elemento
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> Il metodo size() ritorna il valore 1
     * </p>
     */
    @Test
    public void oneElementSubListHasSizeOne() {
        list.add("A");

        ListAdapter subList = list.subList(0, 1);

        assertEquals(1, subList.size());
    }

    /**
     * Verifica del funzionamento del metodo changeSize()
     * <p>
     * <br>
     * <b>Design</b> Il test verifica che il metodo add() aumenti di 1 la dimensione
     * della lista.
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, inseriamo oggetti e
     * verifichiamo che size() ritorni il risultato atteso
     * <br>
     * <b>Pre-Condition</b> La sottolista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista contiene degli elementi
     * <br>
     * <b>Expected Results</b> Il metodo size() ritorna i valori attesi
     * </p>
     */
    @Test
    public void changeSizeOnSubList() {
        ListAdapter subList = list.subList(0, 0);

        for (int i = 1; i <= 10; i++) {
            subList.add(Math.random());
            assertEquals(i, subList.size());
        }
    }

    /**
     * Verifica del funzionamento del metodo getIndex()
     * <p>
     * <br>
     * <b>Design</b> Il test verifica che il metodo getIndex() ritorni gli elementi
     * con l'indice atteso
     * <br>
     * <b>Description</b> Istanziamo una nuova lista, inseriamo oggetti. Instanziamo
     * poi una sublist con indice iniziale diverso da zero. Verifichiamo che gli
     * elementi siano agli indici con l'offset atteso grazie al metodo get()
     * <br>
     * <b>Pre-Condition</b> Una lista ed una sottolista non vuota
     * <br>
     * <b>Post-Condition</b> La lista e la sottolista non vengono modificate
     * <br>
     * <b>Expected Results</b> Il metodo get() ritorna i valori attesi
     * </p>
     */
    @Test
    public void getOnListAndSubList() {
        list.add("A");
        list.add("B");
        list.add("C");
        ListAdapter subList = list.subList(1, 3);

        assertEquals("B", subList.get(0));
        assertEquals("C", subList.get(1));
    }

    /**
     * Verifica del funzionamento del metodo startIndex()
     * <p>
     * <b>Design</b> Il test verifica che il metodo startIndex() ritorni il valore
     * atteso
     * <br>
     * <b>Description</b> Istanziamo una nuova lista non vuota che contiene uno
     * specifico elemento in posizione 0 e altri elementi diversi successivamente.
     * Instanziamo poi una sottolista che esclude il primo elemento, verifichiamo
     * che la chiamata alla funzione contains() con il primo elemento ritorni False
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
        list.add("C");

        ListAdapter subList = list.subList(1, 3);

        assertFalse(subList.contains("A"));
        assertTrue(subList.contains("B"));
        assertTrue(subList.contains("C"));
    }
}
