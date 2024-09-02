package myTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;

/**
 * Test suite per la classe IteratorAdapter.
 * Verifica il corretto funzionamento della classe IteratorAdapter e ne verifica
 * la conformità all'interfaccia List di Java 2 SE 1.4.2.
 * 
 * <br>
 * Utilizza JUnit 4.13.2 e può essere complata con il comando:
 * <br>
 * <code>javac -d bin -cp "junit/*" myTest/* myAdapter/*</code>
 * <br>
 * e può essere eseguita con il comando:
 * <br>
 * <code>java -cp "bin:junit/*" org.junit.runner.JUnitCore myTest.IteratorAdapterTester</code>
 * 
 * <br>
 * <br>
 * <b>Design della Test Suite</b>
 * <ul>
 * <li><b>Implementazione dell'interfaccia</b></li>
 * <li><b>Verifica dei Costruttori</b></li>
 * <li><b>Verifica del metodo hasNext</b> su iteratore pieno e vuoto</li>
 * <li><b>Verifica del metodo next</b> su iteratore con un elemento successivo e
 * su iteratore vuoto</li>
 * <li><b>Verifica dei metodo remove</b> quando next() è stato chiamato
 * precedentemente e quando non è stato già chiamato</li>
 * </ul>
 * 
 * @author Matteo Cuzzolin (2066701)
 */
public class IteratorAdapterTester {
    /**
     * lista che viene inizializzata prima di ogni test
     */
    private ListAdapter list;

    /**
     * Costruttore di default
     */
    public IteratorAdapterTester() {
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
     * Verifica che la classe IteratorAdapter implementi l'interfaccia HIterator
     * <p>
     * <b>Design</b> Il test è motivato dal verificare che IteratorAdapter sia
     * un'implementazione di HIterator
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector e poi uno di tipo
     * IteratorAdapter e assegnamo quest'ultimo ad una variabile
     * di
     * tipo HIterator, se non insorgono eccezioni il test ha successo.
     * <br>
     * <b>Pre-Condition</b> Una variabile ti topo vector vuota
     * <br>
     * <b>Post-Condition</b> La variabile contiene un oggetto IteratorAdapter
     * <br>
     * <b>Expected Results</b> Nessuna eccezione si è verificata
     * </p>
     */
    @Test
    public void implementsHIteratorInterface() {
        HIterator _ = list.iterator();
    }

    /**
     * Verifica il metodo hasNext()
     * <p>
     * <b>Design</b> Verifica che il metodo hasNext() chiamato su un iteratore
     * istanziato da un vettore vuoto ritorni false
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector vuoto e poi uno di
     * tipo IteratorAdapter a partire da esso, verifichiamo che il metodo hasNext()
     * ritorni false
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector vuoto
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il metodo hasNext() ritorna false
     * </p>
     */
    // @Test
    public void hasNextOnEmpty() {
        HIterator it = list.iterator();

        assertEquals(false, it.hasNext());
    }

    /**
     * Verifica il metodo hasNext()
     * <p>
     * <b>Design</b> Verifica che il metodo hasNext() chiamato su un iteratore
     * istanziato da un vettore non vuoto ritorni true
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector non vuoto e poi uno
     * di
     * tipo IteratorAdapter a partire da esso, verifichiamo che il metodo hasNext()
     * ritorni true
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector non vuoto
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il metodo hasNext() ritorna true
     * </p>
     */
    @Test
    public void hasNextOnFull() {
        list.add("A");
        HIterator it = list.iterator();

        assertEquals(true, it.hasNext());
    }

    /**
     * Verifica il metodo next()
     * <p>
     * <b>Design</b> Verifica che il metodo next() chiamato su un iteratore
     * istanziato da un vettore non vuoto ritorni l'elemento successivo
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector non vuoto e poi uno
     * di tipo IteratorAdapter a partire da esso, verifichiamo che il metodo
     * next() ritorni il primo elemento inserito in vector
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector non vuoto
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il metodo next() ritorna l'elemento
     * </p>
     */
    @Test
    public void nextReturnsFirstElement() {
        list.add("A");
        list.add("B");
        HIterator it = list.iterator();

        assertEquals("A", it.next());
    }

    /**
     * Verifica il metodo next()
     * <p>
     * <b>Design</b> Verifica che il metodo next() chiamato su un iteratore
     * istanziato da un vettore vuoto lanci l'eccezione NoSuchElementException
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector vuoto e poi uno
     * di tipo IteratorAdapter a partire da esso, verifichiamo che il metodo
     * next() lanci l'eccezione NoSuchElementException
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector vuoto
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Il metodo next() lancia l'eccezione
     * NoSuchElementException
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void nextThrowsNoSuchElementExceptionOnEmptyCollection() {
        HIterator it = list.iterator();

        it.next();
    }

    /**
     * Verifica il metodo remove()
     * <p>
     * <b>Design</b> Verifica che il metodo remove() chiamato su un iteratore
     * istanziato da un vettore non vuoto rimuova correttamente l'ultimo oggetto
     * ritornato da next().
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector non vuoto e poi uno
     * di tipo IteratorAdapter a partire da esso, chiamiamo next(), poi remove().
     * Verifichiamo infine che su vector sia stato rimosso il primo elemento.
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector non vuoto
     * <br>
     * <b>Post-Condition</b> Viene rimosso il primo elemento da vector
     * <br>
     * <b>Expected Results</b> get(0) sul vettore ritorna un oggetto diverso da
     * quello che era inizialmente impostato come primo elemento
     * </p>
     */
    @Test
    public void removeRemovesFirstElement() {
        list.add("A");
        list.add("B");
        HIterator it = list.iterator();

        it.next();
        it.remove();
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    /**
     * Verifica il metodo remove()
     * <p>
     * <b>Design</b> Verifica che il metodo remove() chiamato su un iteratore
     * istanziato da un vettore di lunghezza 10 rimuova correttamente l'n-esimo
     * oggetto ritornato da next().
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector non vuoto e poi uno
     * di tipo IteratorAdapter a partire da esso, chiamiamo next() per n volte (dove
     * n è un numero scelto casualmente), poi remove().
     * Verifichiamo infine che su vector sia stato rimosso l'n-esimo elemento.
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector di lunghezza 10
     * <br>
     * <b>Post-Condition</b> Viene rimosso l'n-esimo elemento da vector
     * <br>
     * <b>Expected Results</b> elementAt(n) sul vettore ritorna un oggetto diverso
     * da
     * quello che era inizialmente impostato come n-esimo elemento
     * </p>
     */
    @Test
    public void removeRemovesNElement() {
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
        int n = (int) (Math.random() * 9) + 1; // [1, 9]
        HIterator it = list.iterator();

        for (int j = 0; j <= n; j++) {
            it.next();
        }
        it.remove();

        assertEquals(n + 1, list.get(n));
    }

    /**
     * Verifica il metodo remove()
     * <p>
     * <b>Design</b> Verifica che il metodo remove() chiamato su un iteratore
     * istanziato da un vettore vuoto lanci l'eccezione IllegalStateException quando
     * viene chiamato prima di next()
     * <br>
     * <b>Description</b> Istanziamo un oggetto di tipo Vector vuoto e poi uno
     * di tipo IteratorAdapter a partire da esso, chiamiamo remove().
     * <br>
     * <b>Pre-Condition</b> Un oggetto di tipo IteratorAdapter istanziato da un
     * Vector vuoto
     * <br>
     * <b>Post-Condition</b> Nessuna Post Condizione
     * <br>
     * <b>Expected Results</b> Viene lanciata l'eccezione IllegalStateException
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void removeThrowsIllegalStateException() {
        HIterator it = list.iterator();

        it.remove();
    }
}
