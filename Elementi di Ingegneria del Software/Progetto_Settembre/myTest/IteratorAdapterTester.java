package myTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import myAdapter.HIterator;
import myAdapter.VectorAdapter;
import myAdapter.VectorAdapter.IteratorAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della classe {@link IteratorAdapter}. <br>
 * Per eseguire questa test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.IteratorAdapterTester}
 * </p>
 * <p>
 * <b>Design</b> Prima di ogni test viene inizializzato un nuovo oggetto di tipo
 * {@link VectorAdapter}, esso viene creato vuoto per ogni test case. <br>
 * L'obiettivo dei test case è quello di verificare il comportamento di tutti i metodi in vari casi,
 * sia quelli standard che quelli limite. <br>
 * Vengono anche verificate le situazioni di errore per assicurarsi che vengano gestite
 * correttamente.
 * </p>
 * <p>
 * <b>Pre-Condition</b> Prima di eseguire la test suite il progetto deve essere compilato e i file
 * .jar delle sue dipendenze, ovvero junit (4.13.2) e hamcrest-core (1.3), devono trovarsi nella
 * cartella {@code Matcher}. <br>
 * Per l'esecuzione della test suite è stato utilizzato JAVA SE 18 (build 18.0.2.1+1-1).
 * </p>
 * <p>
 * <b>Post-Condition</b> Un report completo dei test eseguiti, superati e falliti viene generato
 * dopo l'esecuzione della test suite.
 * </p>
 * <b>Test Cases</b>
 * <ul>
 * <li>{@link #hasNextOnEmpty()} - Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni
 * {@code false} se non ci sono altri elementi di iterazione.</li>
 * <li>{@link #hasNextOnNonEmpty()} - Verifica che il metodo {@link IteratorAdapter#hasNext()}
 * ritorni {@code true} se ci sono altri elementi di iterazione.</li>
 * <li>{@link #nextReturnsCorrectElements()} - Verifica che il metodo {@link IteratorAdapter#next()}
 * ritorni tutti gli elementi nel vettore nell'ordine corretto.</li>
 * <li>{@link #nextThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link IteratorAdapter#next()} lanci un'eccezione se non ci sono ulteriori elementi
 * nell'iterazione.</li>
 * <li>{@link #removeRemovesAllElements()} - Verifica che il metodo {@link IteratorAdapter#remove()}
 * riesca a rimuovere tutti gli elementi di un vettore.</li>
 * <li>{@link #removeRemovesCorrectElement()} - Verifica che il metodo
 * {@link IteratorAdapter#remove()} rimuova correttamente l'ultimo elemento ritornato.</li>
 * <li>{@link #removeThrowsIllegalStateException()} - Verifica che il metodo
 * {@link IteratorAdapter#remove()} lanci un'eccezione quando viene invocato senza prima invocare
 * {@link IteratorAdapter#next()}</li>
 * <li>{@link #removeThrowsIllegalStateException2()} - Verifica che il metodo
 * {@link IteratorAdapter#remove()} lanci un'eccezione quando viene invocato due volte avendo prima
 * invocato {@link IteratorAdapter#next()}.</li>
 * </ul>
 * <p>
 * <b>Test Suite Execution Records</b> {@code 8} test eseguiti, {@code 8} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link VectorAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
public class IteratorAdapterTester {

    /**
     * vettore inizializzato prima di ogni test
     */
    private VectorAdapter vector;

    /**
     * Costruttore di default
     */
    public IteratorAdapterTester() {}

    /**
     * Inizializza la variabile {@code vector} come un vettore vuoto di tipo VectorAdapter.
     *
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        vector = new VectorAdapter();
    }

    // ======== boolean hasNext() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni {@code false}
     * se non ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione. Questo viene verificato su
     * un'iteratore su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, su di esso instanzia un'iterazione grazie al metodo
     * {@link VectorAdapter#iterator()}. Chiama poi il metodo {@link IteratorAdapter#hasNext()} e
     * verifica che ritorni {@code false}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link IteratorAdapter#hasNext()} ritorna {@code false}
     * </p>
     */
    @Test
    public void hasNextOnEmpty() {
        HIterator it = vector.iterator();

        assertFalse(it.hasNext());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni {@code true}
     * se ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni
     * {@code true} se ci sono altri elementi di iterazione. Questo viene verificato su un'iteratore
     * su un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, con un elemento, su di esso instanzia
     * un'iterazione grazie al metodo {@link VectorAdapter#iterator()}. Chiama poi il metodo
     * {@link IteratorAdapter#hasNext()} e verifica che ritorni {@code true}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link IteratorAdapter#hasNext()} ritorna {@code true}
     * </p>
     */
    @Test
    public void hasNextOnNonEmpty() {
        vector.add("A");
        HIterator it = vector.iterator();

        assertTrue(it.hasNext());
    }

    // ======== Object next() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#next()} lanci un'eccezione se
     * non ci sono ulteriori elementi nell'iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#next()} lanci un'eccezione
     * {@link NoSuchElementException} se non ci sono ulteriori elementi nell'iterazione. Questo
     * viene verificato su un'iteratore su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, su di esso instanzia un'iteratore grazie al metodo
     * {@link VectorAdapter#iterator()}. Chiama poi il metodo {@link IteratorAdapter#next()} e
     * verifica che venga lanciata l'eccezione {@link NoSuchElementException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e vuoto e l'iteratore è instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link IteratorAdapter#next()} lancia l'eccezione
     * {@link NoSuchElementException}
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void nextThrowsNoSuchElementException() {
        HIterator it = vector.iterator();

        it.next();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#next()} ritorni tutti gli
     * elementi nel vettore nell'ordine corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#next()} ritorni tutti gli
     * elementi nel vettore nell'ordine corretto. Questo viene verificato eseguendo in coppia
     * {@link IteratorAdapter#next()} e {@link VectorAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia un'iteratore grazie al
     * metodo {@link VectorAdapter#iterator()}. <br>
     * Viene poi eseguita un'iterazione su tutto l'iteratore, ci si aspetta che ogni invocazione di
     * {@code next()} sia uguale a {@code get(i)} (Dove {@code i} segue il valore del cursore
     * dell'iteratore incrementando di {@code 1} ad ogni iterazione).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato, l'iteratore si trova alla fine del
     * vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code next()} e {@code get(i)} ritornano gli stessi valori
     * </p>
     */
    @Test
    public void nextReturnsCorrectElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HIterator it = vector.iterator();

        int i = 0;
        while (it.hasNext()) {
            assertEquals(vector.get(i), it.next());
            i++;
        }
    }

    // ======== void remove() ========
    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#remove()} lanci un'eccezione
     * quando viene invocato senza prima invocare {@link IteratorAdapter#next()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#remove()} lanci un'eccezione
     * {@link IllegalStateException} quando viene invocato senza prima invocare
     * {@link IteratorAdapter#next()}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia un'iteratore grazie al
     * metodo {@link VectorAdapter#iterator()}. Viene poi chiamato {@link IteratorAdapter#remove()}
     * e si verifica che venga lanciata l'eccezione {@link IllegalStateException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e l'iteratore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@code remove()} lancia l'eccezione {@link IllegalStateException}
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void removeThrowsIllegalStateException() {
        vector.add("A");

        HIterator it = vector.iterator();

        it.remove();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#remove()} lanci un'eccezione
     * quando viene invocato due volte avendo prima invocato {@link IteratorAdapter#next()}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#remove()} lanci un'eccezione
     * quando viene invocato due volte avendo prima invocato {@link IteratorAdapter#next()}. Viene
     * verificato che la prima chiamata a {@link IteratorAdapter#remove()} rimuova un elemento e la
     * seconda lanci l'eccezione.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia un'iteratore grazie al
     * metodo {@link VectorAdapter#iterator()}. <br>
     * Viene poi invocato {@link IteratorAdapter#next()}, successivamente viene invocato
     * {@link IteratorAdapter#remove()} 2 volte. Dopo la prima chiamata si verifica che venga
     * rimosso un elemento e dopo la seconda si verifica che venga lanciata l'eccezione
     * {@link IllegalStateException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha un elemento in meno, il primo
     * </p>
     * <p>
     * <b>Expected Results</b> La seconda invocazione di {@code remove()} lancia l'eccezione
     * {@link IllegalStateException}
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void removeThrowsIllegalStateException2() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        HIterator it = vector.iterator();

        it.next();
        it.remove();

        assertEquals(2, vector.size());

        it.remove();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#remove()} rimuova correttamente
     * l'ultimo elemento ritornato.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#remove()} rimuova
     * correttamente l'ultimo elemento ritornato. Questo viene verificato su un vettore di 3
     * elementi dove vengono rimossi tutti e 3 gli elementi partendo da quello con indice più basso
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di 3 elementi, su di esso instanzia un'iteratore grazie al
     * metodo {@link VectorAdapter#iterator()}. Viene poi vengono invocati per 3 volte
     * {@link IteratorAdapter#next()} e {@link IteratorAdapter#remove()} ad ogni invocazione dei
     * metodi vengono verificati gli elementi presenti nel vettore e ne viene verificata la
     * lunghezza.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato di 3 elementi e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore è vuoto e l'iteratore non ha altri elementi
     * </p>
     * <p>
     * <b>Expected Results</b> Dal vettore vengono rimossi gli elementi a partire da quello con
     * l'indice più basso e la dimensione del vettore si riduce correttamente.
     * </p>
     */
    @Test
    public void removeRemovesCorrectElement() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        HIterator it = vector.iterator();

        it.next();
        it.remove();

        assertEquals("B", vector.get(0));
        assertEquals("C", vector.get(1));
        assertEquals(2, vector.size());

        it.next();
        it.remove();

        assertEquals("C", vector.get(0));
        assertEquals(1, vector.size());

        it.next();
        it.remove();

        assertEquals(0, vector.size());
        assertFalse(it.hasNext());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#remove()} riesca a rimuovere
     * tutti gli elementi di un vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#remove()} riesca a rimuovere
     * tutti gli elementi di un vettore. Questo viene verificato eseguendo una iterazione completa
     * sul vettore e chiamando {@code remove()} su ogni elemento.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia un'iteratore grazie al
     * metodo {@link VectorAdapter#iterator()}. Viene poi eseguita un'iterazione finchè
     * {@link IteratorAdapter#hasNext()} è vera dove viene eseguito {@code next()} e
     * {@code remove()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore è vuoto
     * </p>
     * <p>
     * <b>Expected Results</b> {@code remove()} rimuove tutti gli elementi del vettore
     * </p>
     */
    @Test
    public void removeRemovesAllElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HIterator it = vector.iterator();

        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        assertTrue(vector.isEmpty());
    }
}
