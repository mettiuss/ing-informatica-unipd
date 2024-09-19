package myTest;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import myAdapter.VectorAdapter;
import myAdapter.VectorAdapter.EnumerationAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della classe {@link EnumerationAdapter}. <br>
 * Per eseguire questa test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.EnumerationAdapterTester}
 * </p>
 * <p>
 * <b>Design</b> Prima di ogni test viene inizializzato un nuovo oggetto di tipo
 * {@link VectorAdapter}, esso viene creato vuoto per ogni test case. <br>
 * L'obiettivo dei test case è quello di verificare il comportamento di tutti i metodi in vari casi,
 * sia quelli standard che quelli limite. <br>
 * Vengono anche verificate le situazioni di errore per assicurarsi che vengano gestite
 * correttamente. <br>
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
 * <li>{@link #hasMoreElementsOnEmpty()} - Verifica che il metodo
 * {@link EnumerationAdapter#hasMoreElements()} ritorni {@code false} se non ci sono altri elementi
 * di iterazione.</li>
 * <li>{@link #hasMoreElementsOnNonEmpty()} - Verifica che il metodo
 * {@link EnumerationAdapter#hasMoreElements()} ritorni {@code true} se ci sono altri elementi di
 * iterazione.</li>
 * <li>{@link #nextElementReturnsCorrectElements()} - Verifica che il metodo
 * {@link EnumerationAdapter#nextElement()} ritorni tutti gli elementi nel vettore nell'ordine
 * corretto.</li>
 * <li>{@link #nextElementThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link EnumerationAdapter#nextElement()} lanci un'eccezione se non ci sono ulteriori elementi
 * nell'enumerazione.</li>
 * </ul>
 * <p>
 * <b>Test Suite Execution Records</b> {@code 4} test eseguiti, {@code 4} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link VectorAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
public class EnumerationAdapterTester {

    /**
     * vettore inizializzato prima di ogni test
     */
    private VectorAdapter vector;

    /**
     * Costruttore di default
     */
    public EnumerationAdapterTester() {}

    /**
     * Inizializza la variabile {@code vector} come un vettore vuoto di tipo VectorAdapter.
     *
     * Viene eseguito prima di ogni test.
     */
    @Before
    public void init() {
        vector = new VectorAdapter();
    }

    // ======== boolean hasMoreElements() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#hasMoreElements()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#hasMoreElements()}
     * ritorni {@code false} se non ci sono altri elementi di iterazione. Questo viene verificato su
     * un'enumerazione su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, su di esso instanzia un'enumerazione grazie al
     * metodo {@link VectorAdapter#elements()}. Chiama poi il metodo
     * {@link EnumerationAdapter#hasMoreElements()} e verifica che ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto e un'enumerazione instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione e il vettore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link EnumerationAdapter#hasMoreElements()} ritorna {@code false}.
     * </p>
     */
    @Test
    public void hasMoreElementsOnEmpty() {
        Enumeration enumeration = vector.elements();

        assertFalse(enumeration.hasMoreElements());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#hasMoreElements()} ritorni
     * {@code true} se ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#hasMoreElements()}
     * ritorni {@code true} se ci sono altri elementi di iterazione. Questo viene verificato su
     * un'enumerazione su un vettore con un elemento.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore contenente un elemento, su di esso instanzia
     * un'enumerazione grazie al metodo {@link VectorAdapter#elements()}. Chiama poi il metodo
     * {@link EnumerationAdapter#hasMoreElements()} e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e contiene un elemento e l'enumerazione è
     * instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione e il vettore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link EnumerationAdapter#hasMoreElements()} ritorna {@code true}.
     * </p>
     */
    @Test
    public void hasMoreElementsOnNonEmpty() {
        vector.add("A");
        Enumeration enumeration = vector.elements();

        assertTrue(enumeration.hasMoreElements());
    }

    // ======== Object nextElement() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'enumerazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} lanci
     * un'eccezione {@link NoSuchElementException} se non ci sono ulteriori elementi
     * nell'enumerazione. Questo viene verificato su un'enumerazione su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, su di esso instanzia un'enumerazione grazie al
     * metodo {@link VectorAdapter#elements()}. Chiama poi il metodo
     * {@link EnumerationAdapter#nextElement()} e verifica che venga lanciata l'eccezione
     * {@link NoSuchElementException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e vuoto e l'enumerazione è instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione e il vettore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link EnumerationAdapter#nextElement()} lancia l'eccezione
     * {@link NoSuchElementException}.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void nextElementThrowsNoSuchElementException() {
        Enumeration enumeration = vector.elements();

        enumeration.nextElement();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} ritorni tutti
     * gli elementi nel vettore nell'ordine corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} ritorni
     * tutti gli elementi nel vettore nell'ordine corretto. Questo viene verificato eseguendo in
     * coppia {@link EnumerationAdapter#nextElement()} e {@link VectorAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia un'enumerazione grazie al
     * metodo {@link VectorAdapter#elements()}. <br>
     * Viene poi eseguita un'iterazione su tutta l'enumerazione, ci si aspetta che ogni invocazione
     * di {@code nextElement()} sia uguale a {@code get(i)} (Dove {@code i} segue il valore del
     * cursore dell'iteratore incrementando di {@code 1} ad ogni iterazione).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'enumerazione è instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato, l'enumerazione si trova alla fine del
     * vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextElement()} e {@code get(i)} ritornano gli stessi valori.
     * </p>
     */
    @Test
    public void nextElementReturnsCorrectElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        Enumeration enumeration = vector.elements();

        int i = 0;
        while (enumeration.hasMoreElements()) {
            assertEquals(vector.get(i), enumeration.nextElement());
            i++;
        }
    }
}
