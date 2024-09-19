package myTest;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import myAdapter.HListIterator;
import myAdapter.VectorAdapter;
import myAdapter.VectorAdapter.IteratorAdapter;
import myAdapter.VectorAdapter.ListIteratorAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della classe {@link ListIteratorAdapter}. <br>
 * Per eseguire questa test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.ListIteratorAdapterTester}
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
 * <li>{@link #addAtCorrectPosition()} - Verifica che il metodo
 * {@link ListIteratorAdapter#add(Object)} inserisca correttamente un elemento nel vettore.</li>
 * <li>{@link #addToEmptyList()} - Verifica che il metodo {@link ListIteratorAdapter#add(Object)}
 * inserisca correttamente un elemento in un vettore vuoto.</li>
 * <li>{@link #hasPreviousOnEmpty()} - Verifica che il metodo
 * {@link ListIteratorAdapter#hasPrevious()} ritorni {@code false} se non ci sono altri elementi di
 * iterazione.</li>
 * <li>{@link #hasPreviousOnNonEmpty()} - Verifica che il metodo
 * {@link ListIteratorAdapter#hasPrevious()} ritorni {@code true} se ci sono altri elementi di
 * iterazione.</li>
 * <li>{@link #nextIndexAtBeginning()} - Verifica che il metodo
 * {@link ListIteratorAdapter#nextIndex()} restituisca {@code 0} quando l'iteratore si trova
 * all'inizio del vettore.</li>
 * <li>{@link #nextIndexOnEmptyList()} - Verifica che il metodo
 * {@link ListIteratorAdapter#nextIndex()} restituisca {@code 0} quando viene chiamato su un
 * iteratore di un vettore vuoto.</li>
 * <li>{@link #nextIndexWithAdd()} - Verifica che il metodo {@link ListIteratorAdapter#nextIndex()}
 * restituisca l'indice corretto quando viene invocato {@link ListIteratorAdapter#add(Object)}</li>
 * <li>{@link #nextIndexWithNext()} - Verifica che il metodo {@link ListIteratorAdapter#nextIndex()}
 * restituisca l'indice corretto mentre viene invocato {@link IteratorAdapter#next()}</li>
 * <li>{@link #nextIndexWithPrevious()} - Verifica che il metodo
 * {@link ListIteratorAdapter#nextIndex()} restituisca l'indice corretto mentre viene invocato
 * {@link ListIteratorAdapter#previous()}</li>
 * <li>{@link #nextIndexWithRemove()} - Verifica che il metodo
 * {@link ListIteratorAdapter#nextIndex()} restituisca l'indice corretto quando viene invocato
 * {@link IteratorAdapter#remove()}</li>
 * <li>{@link #previousIndexOnEmptyList()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca {@code -1} quando viene chiamato su un
 * iteratore di un vettore vuoto.</li>
 * <li>{@link #previousIndexWithAdd()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice corretto quando viene invocato
 * {@link ListIteratorAdapter#add(Object)}</li>
 * <li>{@link #previousIndexWithNext()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice corretto mentre viene invocato
 * {@link IteratorAdapter#next()}</li>
 * <li>{@link #previousIndexWithPrevious()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice corretto mentre viene invocato
 * {@link ListIteratorAdapter#previous()}</li>
 * <li>{@link #previousIndexWithRemove()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice corretto quando viene invocato
 * {@link IteratorAdapter#remove()}</li>
 * <li>{@link #previousReturnsCorrectElements()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previous()} ritorni tutti gli elementi nel vettore nell'ordine
 * corretto.</li>
 * <li>{@link #previousThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previous()} lanci un'eccezione se non ci sono ulteriori elementi
 * nell'iterazione.</li>
 * <li>{@link #setAfterAdd()} - Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
 * un'eccezione se viene chiamato subito dopo {@link ListIteratorAdapter#add(Object)}</li>
 * <li>{@link #setAfterNext()} - Verifica che il metodo {@link ListIteratorAdapter#set(Object)}
 * sostituisca correttamente l'ultimo elemento restituito da {@link IteratorAdapter#next()}.</li>
 * <li>{@link #setAfterPrevious()} - Verifica che il metodo {@link ListIteratorAdapter#set(Object)}
 * sostituisca correttamente l'ultimo elemento restituito da
 * {@link ListIteratorAdapter#previous()}.</li>
 * <li>{@link #setAfterRemove()} - Verifica che il metodo {@link ListIteratorAdapter#set(Object)}
 * lanci un'eccezione se viene chiamato subito dopo {@link IteratorAdapter#remove()}</li>
 * <li>{@link #setWithoutNextOrPrevious()} - Verifica che il metodo
 * {@link ListIteratorAdapter#set(Object)} lanci un'eccezione se viene chiamato prima di
 * {@code next()} o {@code previous()}.</li>
 * </ul>
 * <p>
 * <b>Test Suite Execution Records</b> {@code 22} test eseguiti, {@code 22} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link VectorAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
public class ListIteratorAdapterTester {

    /**
     * vettore inizializzato prima di ogni test
     */
    private VectorAdapter vector;

    /**
     * Costruttore di default
     */
    public ListIteratorAdapterTester() {}

    /**
     * Inizializza la variabile {@code vector} come un vettore vuoto di tipo VectorAdapter.
     *
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        vector = new VectorAdapter();
    }

    // ======== boolean hasPrevious() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#hasPrevious()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#hasPrevious()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione. Questo viene verificato su
     * un'iteratore su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, su di esso instanzia un'iterazione grazie al metodo
     * {@link VectorAdapter#listIterator()}. Chiama poi il metodo
     * {@link ListIteratorAdapter#hasPrevious()} e verifica che ritorni {@code false}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#hasPrevious()} ritorna {@code false}
     * </p>
     */
    @Test
    public void hasPreviousOnEmpty() {
        HListIterator it = vector.listIterator();

        assertFalse(it.hasPrevious());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#hasPrevious()} ritorni
     * {@code true} se ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#hasPrevious()} ritorni
     * {@code true} se ci sono altri elementi di iterazione. Questo viene verificato su un'iteratore
     * su un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, con un elemento, su di esso instanzia
     * un'iterazione grazie al metodo {@code listIterator(1)}. Chiama poi il metodo
     * {@link ListIteratorAdapter#hasPrevious()} e verifica che ritorni {@code true}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#hasPrevious()} ritorna {@code true}
     * </p>
     */
    @Test
    public void hasPreviousOnNonEmpty() {
        vector.add("A");
        HListIterator it = vector.listIterator(1);

        assertTrue(it.hasPrevious());
    }

    // ======== Object previous() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'iterazione. Questo viene verificato su
     * un'iteratore su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, su di esso instanzia un'iteratore grazie al metodo
     * {@link VectorAdapter#iterator()}. Chiama poi il metodo {@link ListIteratorAdapter#previous()}
     * e verifica che venga lanciata l'eccezione {@link NoSuchElementException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e vuoto e l'iteratore è instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#previous()} lancia l'eccezione
     * {@link NoSuchElementException}
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void previousThrowsNoSuchElementException() {
        HListIterator it = vector.listIterator();

        it.previous();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} ritorni tutti
     * gli elementi nel vettore nell'ordine corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} ritorni
     * tutti gli elementi nel vettore nell'ordine corretto. Questo viene verificato eseguendo in
     * coppia {@link ListIteratorAdapter#previous()} e {@link VectorAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia un'iteratore che inizia
     * dall'ultimo indice grazie al metodo {@code listIterator(size())} <br>
     * Viene poi eseguita un'iterazione all'indietro su tutto l'iteratore, ci si aspetta che ogni
     * invocazione di {@code previous()} sia uguale a {@code get(i)}. (Dove {@code i} segue il
     * valore del cursore dell'iteratore incrementando di {@code 1} ad ogni iterazione).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato e non vuoto e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il vettore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previous()} e {@code get(i)} ritornano gli stessi valori
     * </p>
     */
    @Test
    public void previousReturnsCorrectElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HListIterator it = vector.listIterator(vector.size());

        int i = vector.size() - 1;
        while (it.hasPrevious()) {
            assertEquals(vector.get(i), it.previous());
            i--;
        }
    }

    // ======== int nextIndex() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * {@code 0} quando viene chiamato su un iteratore di un vettore vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * {@code 0} quando viene chiamato su un iteratore di un vettore vuoto. Ovvero dovrebbe
     * ritornare la dimensione del vettore in quanto {@code hasNext()} ritornerebbe {@code false}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, crea un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Verifica che {@code nextIndex()} restituisca {@code 0}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e l'iteratore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#nextIndex()} restituisce {@code 0}.
     * </p>
     */
    @Test
    public void nextIndexOnEmptyList() {
        HListIterator iterator = vector.listIterator();

        assertEquals(0, iterator.nextIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * {@code 0} quando l'iteratore si trova all'inizio del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * {@code 0} quando l'iteratore si trova all'inizio del vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Invoca {@code nextIndex()} e verifica che il valore
     * restituito sia 0.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e l'iteratore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextIndex()} restituisce {@code 0} all'inizio della lista.
     * </p>
     */
    @Test
    public void nextIndexAtBeginning() {
        vector.add("A");
        vector.add("B");
        HListIterator iterator = vector.listIterator();

        assertEquals(0, iterator.nextIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto mentre viene invocato {@link ListIteratorAdapter#next()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto mentre viene invocato {@link ListIteratorAdapter#next()}. Ci si aspetta che
     * l'indice restituito da {@code nextIndex()} sia quello dell'elemento che verrà successivamente
     * ritornato da {@code next()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Invoca ricorsivamente {@code nextIndex()} e verifica
     * che l'indice restituito sia quello dell'elemento che verrà successivamente ritornato da
     * {@code next()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato, L'iteratore si trova alla fine del
     * vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextIndex()} restituisce l'indice dell'elemento che verrà poi
     * restituito da {@code next()}.
     * </p>
     */
    @Test
    public void nextIndexWithNext() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HListIterator iterator = vector.listIterator();

        while (iterator.hasNext()) {
            assertEquals(vector.get(iterator.nextIndex()), iterator.next());
        }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto mentre viene invocato {@link ListIteratorAdapter#previous()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto mentre viene invocato {@link ListIteratorAdapter#previous()}. Ci si aspetta
     * che l'indice restituito da {@code nextIndex()} sia quello dell'elemento che verrebbe
     * ritornato da {@code next()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Invoca ricorsivamente {@code previous()} e
     * {@code nextIndex()}, verifica che l'indice restituito sia quello dell'elemento che verrebbe
     * ritornato da {@code next()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato, L'iteratore si all'inizio del vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextIndex()} restituisce l'indice dell'elemento che verrebbe
     * ritornato da {@code next()}.
     * </p>
     */
    @Test
    public void nextIndexWithPrevious() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HListIterator iterator = vector.listIterator(vector.size());

        int i = vector.size();
        while (iterator.hasPrevious()) {
            i--;
            iterator.previous();
            assertEquals(iterator.nextIndex(), i);
        }
        assertEquals(0, i);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto quando viene invocato {@link ListIteratorAdapter#remove()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto quando viene invocato {@link ListIteratorAdapter#remove()}. Viene
     * verificato sia nel caso in cui è stato chiamato prima {@link ListIteratorAdapter#next()}
     * oppure {@link ListIteratorAdapter#previous()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore nel mezzo del vettore grazie
     * ad {@link VectorAdapter#listIterator()}. Invoca prima {@code next()} e {@code remove()} poi
     * invoca {@code previous()} e {@code remove()}. In entrambi i casi viene verificato che
     * {@link ListIteratorAdapter#nextIndex()} restituisca l'indice dell'elemento che è appena stato
     * rimosso da {@code remove()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Vengono rimossi 2 elementi dal vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextIndex()} restituisce l'indice dell'elemento che è appena
     * stato rimosso da {@code remove()}.
     * </p>
     */
    @Test
    public void nextIndexWithRemove() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        HListIterator iterator = vector.listIterator(1);

        iterator.next();
        iterator.remove();

        assertEquals(1, iterator.nextIndex());

        iterator.previous();
        iterator.remove();

        assertEquals(0, iterator.nextIndex());

        assertEquals(1, vector.size());
        assertEquals("C", vector.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto quando viene invocato {@link ListIteratorAdapter#add(Object)}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * l'indice corretto quando viene invocato {@link ListIteratorAdapter#add(Object)}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore nel mezzo del vettore grazie
     * ad {@link VectorAdapter#listIterator()}. Invoca {@code add()} e verifica che
     * {@link ListIteratorAdapter#nextIndex()} restituisca l'indice dell'elemento successivo a
     * quello appena aggiunto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene aggiunto un elemento al vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextIndex()} restituisce l'indice dell'elemento successivo a
     * quello appena aggiunto.
     * </p>
     */
    @Test
    public void nextIndexWithAdd() {
        vector.add("A");
        vector.add("B");

        HListIterator iterator = vector.listIterator(1);

        iterator.add("X");

        assertEquals(2, iterator.nextIndex());

        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("X", vector.get(1));
        assertEquals("B", vector.get(2));
    }

    // ======== int previousIndex() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()} restituisca
     * {@code -1} quando viene chiamato su un iteratore di un vettore vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()}
     * restituisca {@code -1} quando viene chiamato su un iteratore di un vettore vuoto. Ovvero
     * quanto definito dalla documentazione nel caso in cui ci si trovi in testa al vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, crea un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Verifica che {@code previousIndex()} restituisca
     * {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e l'iteratore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#previousIndex()} restituisce {@code -1}.
     * </p>
     */
    @Test
    public void previousIndexOnEmptyList() {
        HListIterator iterator = vector.listIterator();

        assertEquals(-1, iterator.previousIndex());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()} restituisca
     * l'indice corretto mentre viene invocato {@link ListIteratorAdapter#next()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()}
     * restituisca l'indice corretto mentre viene invocato {@link ListIteratorAdapter#next()}. Ci si
     * aspetta che l'indice restituito da {@code previousIndex()} sia quello dell'elemento che
     * verrebbe ritornato da {@code previous()}
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Invoca ricorsivamente {@code previousIndex()} e
     * verifica che l'indice restituito sia quello dell'elemento che verrebbe ritornato da
     * {@code previous()}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato, L'iteratore si trova alla fine del
     * vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previousIndex()} restituisce l'indice dell'elemento che
     * verrebbe restituito da {@code previous()}.
     * </p>
     */
    @Test
    public void previousIndexWithNext() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HListIterator iterator = vector.listIterator();

        int i = -1;
        while (iterator.hasNext()) {
            assertEquals(i, iterator.previousIndex());
            iterator.next();
            i++;
        }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()} restituisca
     * l'indice corretto mentre viene invocato {@link ListIteratorAdapter#previous()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()}
     * restituisca l'indice corretto mentre viene invocato {@link ListIteratorAdapter#previous()}.
     * Ci si aspetta che l'indice restituito da {@code previousIndex()} sia quello dell'elemento che
     * verrà successivamente ritornato da {@code previous()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}. Invoca ricorsivamente {@code previous()} e
     * {@code previousIndex()}, verifica che l'indice restituito sia quello dell'elemento che verrà
     * successivamente ritornato da {@code previous()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato, l'iteratore si trova all'inizio del
     * vettore
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previousIndex()} restituisce l'indice dell'elemento che verrà
     * successivamente ritornato da {@code previous()}.
     * </p>
     */
    @Test
    public void previousIndexWithPrevious() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        HListIterator iterator = vector.listIterator(vector.size());

        while (iterator.hasPrevious()) {
            assertEquals(vector.get(iterator.previousIndex()), iterator.previous());
        }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()} restituisca
     * l'indice corretto quando viene invocato {@link ListIteratorAdapter#remove()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()}
     * restituisca l'indice corretto quando viene invocato {@link ListIteratorAdapter#remove()}.
     * Viene verificato sia nel caso in cui è stato chiamato prima
     * {@link ListIteratorAdapter#next()} oppure {@link ListIteratorAdapter#previous()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore nel mezzo del vettore grazie
     * ad {@link VectorAdapter#listIterator()}. Invoca prima {@code next()} e {@code remove()} poi
     * invoca {@code previous()} e {@code remove()}. In entrambi i casi viene verificato che
     * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice dell'elemento precedente a
     * quello che è appena stato rimosso da {@code remove()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Vengono rimossi 2 elementi dal vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previousIndex()} restituisce l'indice dell'elemento precedente
     * a quello che è appena stato rimosso da {@code remove()}.
     * </p>
     */
    @Test
    public void previousIndexWithRemove() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        HListIterator iterator = vector.listIterator(2);

        iterator.next();
        iterator.remove();

        assertEquals(1, iterator.previousIndex());

        iterator.previous();
        iterator.remove();

        assertEquals(0, iterator.previousIndex());

        assertEquals(1, vector.size());
        assertEquals("A", vector.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()} restituisca
     * l'indice corretto quando viene invocato {@link ListIteratorAdapter#add(Object)}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()}
     * restituisca l'indice corretto quando viene invocato {@link ListIteratorAdapter#add(Object)}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore nel mezzo del vettore grazie
     * ad {@link VectorAdapter#listIterator()}. Invoca {@code add()} e verifica che
     * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice dell'elemento appena
     * aggiunto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene aggiunto un elemento al vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previousIndex()} restituisce l'indice dell'elemento appena
     * aggiunto.
     * </p>
     */
    @Test
    public void previousIndexWithAdd() {
        vector.add("A");
        vector.add("B");

        HListIterator iterator = vector.listIterator(1);

        iterator.add("X");

        assertEquals(1, iterator.previousIndex());

        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("X", vector.get(1));
        assertEquals("B", vector.get(2));
    }

    // ======== void set(Object o) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
     * un'eccezione se viene chiamato prima di {@code next()} o {@code previous()}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
     * un'eccezione {@link IllegalStateException} se viene chiamato prima di {@code next()} o
     * {@code previous()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto da cui instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}, invoca poi {@code set("A")} e verifica che venga
     * sollevata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e l'iteratore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#set(Object)} lancia
     * {@link IllegalStateException}.
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void setWithoutNextOrPrevious() {
        vector.add("A");
        HListIterator iterator = vector.listIterator();

        iterator.set("A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
     * un'eccezione se viene chiamato subito dopo {@link ListIteratorAdapter#remove()}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
     * un'eccezione {@link IllegalStateException} se viene chiamato subito dopo {@code remove()}
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto da cui instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}, invoca poi {@code next()} e {@code remove()}. Infine
     * invoca {@code set("A")} e verifica che venga sollevata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha un elemento in meno.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#set(Object)} lancia
     * {@link IllegalStateException}.
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void setAfterRemove() {
        vector.add("A");
        vector.add("B");
        HListIterator iterator = vector.listIterator();

        iterator.next();
        iterator.remove();

        iterator.set("A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
     * un'eccezione se viene chiamato subito dopo {@link ListIteratorAdapter#add(Object)}
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} lanci
     * un'eccezione {@link IllegalStateException} se viene chiamato subito dopo {@code add()}
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto da cui instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}, invoca poi {@code add("X")}. Infine invoca
     * {@code set("B")} e verifica che venga sollevata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha un elemento in più.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#set(Object)} lancia
     * {@link IllegalStateException}.
     * </p>
     */
    @Test(expected = IllegalStateException.class)
    public void setAfterAdd() {
        vector.add("A");
        HListIterator iterator = vector.listIterator();

        iterator.add("X");

        iterator.set("B");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} sostituisca
     * correttamente l'ultimo elemento restituito da {@link ListIteratorAdapter#next()}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} sostituisca
     * correttamente l'ultimo elemento restituito da {@link ListIteratorAdapter#next()}. La
     * dimensione del vettore e l'ordine dei suoi elementi non vegnono modificati.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto da cui instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}, invoca poi {@code next()}. Infine invoca
     * {@code set("nuovo")} e verifica che l'elemento sia correttamente sostituito.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'elemento del vettore viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento è correttamente sostituito con il nuovo valore.
     * </p>
     */
    @Test
    public void setAfterNext() {
        vector.add("A");
        HListIterator iterator = vector.listIterator();

        iterator.next();
        iterator.set("nuovo");

        assertEquals("nuovo", vector.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} sostituisca
     * correttamente l'ultimo elemento restituito da {@link ListIteratorAdapter#previous()}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} sostituisca
     * correttamente l'ultimo elemento restituito da {@link ListIteratorAdapter#previous()}. La
     * dimensione del vettore e l'ordine dei suoi elementi non vegnono modificati.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto da cui instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}, invoca poi {@code previous()}. Infine invoca
     * {@code set("nuovo")} e verifica che l'elemento sia correttamente sostituito.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'elemento del vettore viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento è correttamente sostituito con il nuovo valore.
     * </p>
     */
    @Test
    public void setAfterPrevious() {
        vector.add("A");
        HListIterator iterator = vector.listIterator(1);

        iterator.previous();
        iterator.set("nuovo");

        assertEquals("nuovo", vector.get(0));
    }

    // ======== void add(Object o) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#add(Object)} inserisca
     * correttamente un elemento nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#add(Object)} inserisca
     * correttamente un elemento nel vettore. Dopo l'inserimento, verifica che il nuovo elemento
     * venga posizionato nel vettore nel punto corretto e che le chiamate successive a
     * {@code next()} e {@code previous()} funzionino come previsto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia un iteratore grazie ad
     * {@link VectorAdapter#listIterator()}, e usa il metodo {@code add(Object)} per inserire un
     * elemento in una specifica posizione del vettore. Successivamente, verifica che il nuovo
     * elemento venga posizionato nel vettore nel punto corretto e che le chiamate successive a
     * {@code next()} e {@code previous()} funzionino come previsto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha dimensione maggiore di 1.
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento è correttamente inserito nel vettore e le chiamate a
     * {@code next()} e {@code previous()} funzionino come previsto.
     * </p>
     */
    @Test
    public void addAtCorrectPosition() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        HListIterator iterator = vector.listIterator(1);

        iterator.add("X");

        assertEquals(4, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("X", vector.get(1));
        assertEquals("B", vector.get(2));
        assertEquals("C", vector.get(3));
        assertEquals("X", vector.get(iterator.previousIndex()));
        assertEquals("B", vector.get(iterator.nextIndex()));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#add(Object)} inserisca
     * correttamente un elemento in un vettore vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#add(Object)} inserisca
     * correttamente un elemento in un vettore vuoto come primo e unico elemento.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto, ottiene un iteratore e usa il metodo {@code add(Object)}
     * per inserire un elemento. Verifica che l'elemento sia inserito correttamente e che diventi
     * l'unico elemento del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore contiene un unico elemento.
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento è correttamente inserito come unico elemento nel vettore.
     * </p>
     */
    @Test
    public void addToEmptyList() {
        HListIterator iterator = vector.listIterator();

        iterator.add("X");

        assertEquals(1, vector.size());
        assertEquals("X", vector.get(0));
    }
}
