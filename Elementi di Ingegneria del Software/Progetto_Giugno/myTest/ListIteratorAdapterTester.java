package myTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;

/**
 * Test suite per la classe ListIteratorAdapter.
 * Verifica il corretto funzionamento della classe ListIteratorAdapter e ne
 * verifica la conformità all'interfaccia List di Java 2 SE 1.4.2.
 * 
 * <br>
 * Utilizza JUnit 4.13.2 e può essere complata con il comando:
 * <br>
 * <code>javac -d bin -cp "junit/*" myTest/* myAdapter/*</code>
 * <br>
 * e può essere eseguita con il comando:
 * <br>
 * <code>java -cp "bin:junit/*" org.junit.runner.JUnitCore myTest.ListIteratorAdapterTester</code>
 * 
 * <br>
 * <br>
 * <b>Design della Test Suite</b>
 * <ul>
 * <li><b>Implementazione dell'interfaccia</b></li>
 * <li><b>Verifica del costruttore che specifica l'indice</b></li>
 * <li><b>Verifica del metodo hasPrevious</b></li>
 * <li><b>Verifica del metodo add</b> su iteratore pieno e vuoto</li>
 * <li><b>Verifica del metodo previous</b> e della sua interazione col metodo
 * remove()</li>
 * <li><b>Verifica del metodo previousIndex</b></li>
 * <li><b>Verifica del metodo set</b> dopo chiamata di next(), di previous() e
 * quando nessuno dei due è stato chiamato</li>
 * </ul>
 * 
 * @author Matteo Cuzzolin (2066701)
 */
public class ListIteratorAdapterTester {
    /**
     * lista che viene inizializzata prima di ogni test
     */
    private ListAdapter list;

    /**
     * Costruttore di default
     */
    public ListIteratorAdapterTester() {
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
     * Verifica che la classe ListIteratorAdapter implementi l'interfaccia
     * HListIterator
     * <p>
     * <b>Design</b> Il test è motivato dal verificare che ListIteratorAdapter sia
     * un'implementazione di HListIterator
     * <br>
     * <b>Description</b> Istanziamo ListIteratorAdapter e lo assegnamo ad una
     * variabile di
     * tipo HListIterator, se non insorgono eccezioni il test ha successo.
     * <br>
     * <b>Pre-Condition</b> Nessuna Pre Condizione
     * <br>
     * <b>Post-Condition</b> La variabile contiene un oggetto ListIteratorAdapter
     * <br>
     * <b>Expected Results</b> Nessuna eccezione si è verificata
     * </p>
     */
    @Test
    public void implementsHListIteratorInterface() {
        HListIterator _ = list.listIterator();
    }

    /**
     * Verifica che il costruttore ListIteratorAdapter(Vector, int) funzioni
     * correttamente
     * <p>
     * <b>Design</b> Il test verifica che l'indice di iterazione sia conforme a
     * quello specificato nel costruttore
     * <br>
     * <b>Description</b> Istanziamo ListIteratorAdapter con index != 0 e
     * verifichiamo che nextIndex() sia corretto
     * <br>
     * <b>Pre-Condition</b> Nessuna Pre Condizione
     * <br>
     * <b>Post-Condition</b> La variabile contiene un oggetto ListIteratorAdapter
     * che inizia l'iterazione all'indice corretto
     * <br>
     * <b>Expected Results</b> nextIndex() è uguale all'indice specificato
     * </p>
     */
    @Test
    public void constructorWithIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator(1);

        assertEquals("B", it.next());
    }

    /**
     * Verifica del funzionamento del metodo nextIndex()
     * <p>
     * <b>Design</b> Il test verifica che il metodo nextIndex() ritorni l'indice
     * corretto dell'elemento che verrebbe ritornato da una chiamata a next().
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota e
     * invochiamo il metodo next() e nextIndex() durante l'iterazione, inoltre
     * verifichiamo che alla fine dell'iterazione nextIndex() ritorni la dimensione
     * del vettore
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata nextIndex() ritorna l'indice corretto
     * dell'elemento che verrebbe ritornato da una chiamata a next().
     * </p>
     */
    @Test
    public void nextIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator();
        int index = 0;
        while (it.hasNext()) {
            assertEquals(index, it.nextIndex());
            it.next();
            index++;
        }

        assertEquals(list.size(), it.nextIndex());
    }

    /**
     * Verifica del funzionamento del metodo hasPrevious()
     * <p>
     * <b>Design</b> Il test verifica che il metodo hasPrevious() ritorni false
     * quando l'iteratore è all'inizio della lista. Inoltre verifica che ritorni
     * true in quasiasi altro momento dell'iterazione
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota e
     * invochiamo il metodo hasPrevious() all'inizio dell'iterazione e durante
     * l'iterazione
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata hasPrevious() ritorna false solamente
     * alla prima chiamata
     * </p>
     */
    @Test
    public void hasPrevious() {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator();
        assertFalse(it.hasPrevious());
        while (it.hasNext()) {
            it.next();
            assertTrue(it.hasPrevious());
        }
    }

    /**
     * Verifica del funzionamento del metodo previous()
     * <p>
     * <b>Design</b> Il test verifica che il metodo previous() ritorni l'elemento
     * precedente dopo una chiamata a next().
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota,
     * chiamiamo next() e poi invochiamo il metodo previous().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata previous() ritorna l'elemento precedente.
     * </p>
     */
    @Test
    public void previous() {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();
        it.next();
        assertEquals("A", it.previous());
    }

    /**
     * Verifica del funzionamento del metodo previous()
     * <p>
     * <b>Design</b> Il test verifica che il metodo previous() sia compatibile con
     * il metodo remove()
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota,
     * chiamiamo next() e poi invochiamo il metodo previous().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata previous() ritorna l'elemento precedente.
     * </p>
     */
    @Test
    public void previousRemove() {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();
        it.next();
        assertEquals("A", it.previous());
        it.remove();
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    /**
     * Verifica del funzionamento del metodo previous()
     * <p>
     * <b>Design</b> Il test verifica che il metodo previous() lanci una
     * NoSuchElementException quando non ci sono elementi precedenti.
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista vuota e
     * invochiamo il metodo previous() all'inizio dell'iterazione.
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata previous() lancia una
     * NoSuchElementException.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void previousThrowsNoSuchElementException() {

        HListIterator it = list.listIterator();
        it.previous();
    }

    /**
     * Verifica del funzionamento del metodo previousIndex()
     * <p>
     * <b>Design</b> Il test verifica che il metodo previousIndex() ritorni -1
     * quando l'iteratore è all'inizio della lista e che ritorni il corretto indice
     * durante l'iterazione
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota e posizioniamo
     * l'iterazione al termine della lista, poi iteriamo dalla fine all'inizio della
     * lista verificando che il metodo previousIndex() ritorni il risultato atteso
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata previousIndex() ritorna il risultato
     * atteso.
     * </p>
     */
    @Test
    public void previousIndex() {
        list.add("A");
        list.add("B");
        list.add("C");

        HListIterator it = list.listIterator();
        while (it.hasNext()) {
            it.next();
        }

        int index = 2;
        while (it.hasPrevious()) {
            assertEquals(index, it.previousIndex());
            it.previous();
            index--;
        }
        assertEquals(-1, it.previousIndex());
    }

    /**
     * Verifica del funzionamento del metodo set()
     * <p>
     * <b>Design</b> Il test verifica che il metodo set() sostituisca correttamente
     * l'elemento dopo una chiamata a next().
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota,
     * chiamiamo next() e poi invochiamo il metodo set().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista viene modificata correttamente
     * <br>
     * <b>Expected Results</b> La chiamata set() sostituisce correttamente
     * l'elemento.
     * </p>
     */
    @Test
    public void setAfterNext() {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();
        it.next(); // Moves to the first element
        it.set("Z");
        assertEquals(2, list.size());

        assertEquals("Z", list.get(0));
        assertEquals("B", list.get(1));
    }

    /**
     * Verifica del funzionamento del metodo set()
     * <p>
     * <b>Design</b> Il test verifica che il metodo set() sostituisca correttamente
     * l'elemento dopo una chiamata a previous().
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota,
     * chiamiamo previous() e poi invochiamo il metodo set().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista viene modificata correttamente
     * <br>
     * <b>Expected Results</b> La chiamata set() sostituisce correttamente
     * l'elemento.
     * </p>
     */
    @Test
    public void setAfterPrevious() {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();
        it.next();
        it.previous();
        it.set("Z");
        assertEquals(2, list.size());
        assertEquals("Z", list.get(0));
        assertEquals("B", list.get(1));
    }

    /**
     * Verifica del funzionamento del metodo set()
     * <p>
     * <b>Design</b> Il test verifica che il metodo set() lanci
     * un'IllegalStateException se next() o previous() non sono stati chiamati.
     * <br>
     * <b>Description</b> Istanziamo una nuova non vuota e
     * invochiamo il metodo set() senza chiamare next() o previous().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista rimane invariata
     * <br>
     * <b>Expected Results</b> La chiamata set() lancia un'IllegalStateException.
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void setIllegalStateException() {
        list.add("A");

        HListIterator it = list.listIterator();
        it.set("Z");
    }

    /**
     * Verifica del funzionamento del metodo add()
     * <p>
     * <b>Design</b> Il test verifica che il metodo add() aggiunga correttamente gli
     * elementi nella lista
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista non vuota e
     * invochiamo il metodo add() per ogni iterazione
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e non vuota
     * <br>
     * <b>Post-Condition</b> La lista contiene elementi aggiunti alternatamente ad
     * elementi della lista
     * <br>
     * <b>Expected Results</b> La chiamata add() aggiunge correttamente gli elementi
     * </p>
     */
    @Test
    public void addEveryWhere() {
        list.add("A");
        list.add("B");

        HListIterator it = list.listIterator();
        it.add("aggiunto");
        while (it.hasNext()) {
            it.next();
            it.add("aggiunto");
        }
        assertEquals(5, list.size());
        assertEquals("aggiunto", list.get(0));
        assertEquals("A", list.get(1));
        assertEquals("aggiunto", list.get(2));
        assertEquals("B", list.get(3));
        assertEquals("aggiunto", list.get(4));
    }

    /**
     * Verifica del funzionamento del metodo add()
     * <p>
     * <b>Design</b> Il test verifica che il metodo add() aggiunga correttamente un
     * elemento in una lista vuota.
     * <br>
     * <b>Description</b> Istanziamo una nuova Lista vuota e invochiamo il metodo
     * add().
     * <br>
     * <b>Pre-Condition</b> La lista è istanziata e vuota
     * <br>
     * <b>Post-Condition</b> La lista contiene solamente l'elemento aggiunto
     * <br>
     * <b>Expected Results</b> La chiamata add() aggiunge correttamente l'elemento
     * nella lista.
     * </p>
     */
    @Test
    public void addEmptyList() {
        HListIterator it = list.listIterator();
        it.add("A");
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }
}
