package myTest;

import static org.junit.Assert.*;

import java.util.EmptyStackException;
import myAdapter.*;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della classe {@link StackAdapter}. <br>
 * Per eseguire questa test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.StackAdapterTester}
 * </p>
 * <p>
 * <b>Design</b> Prima di ogni test viene inizializzato un nuovo oggetto di tipo
 * {@link StackAdapter}, esso viene creato vuoto per ogni test case. <br>
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
 * <p>
 * <b>Test Cases</b> <br>
 * {@link #emptyOnEmptyVector()} - Verifica del metodo {@link StackAdapter#empty()} su uno stack
 * vuoto.<br>
 * {@link #emptyOnNonEmptyVector()} - Verifica del metodo {@link StackAdapter#empty()} su uno stack
 * non vuoto.<br>
 * {@link #peekThrowsEmptyStackException()} - Verifica del metodo {@link StackAdapter#peek()} su uno
 * stack vuoto.<br>
 * {@link #peekWileAdding()} - Verifica del metodo {@link StackAdapter#peek()} mentre vengono
 * aggiunti elementi allo stack.<br>
 * {@link #peekWileRemoving()} - Verifica del metodo {@link StackAdapter#peek()} mentre vengono
 * rimossi elementi dallo stack.<br>
 * {@link #popRemovesElement()} - Verifica del metodo {@link StackAdapter#pop()} su uno stack non
 * vuoto.<br>
 * {@link #popThrowsEmptyStackException()} - Verifica del metodo {@link StackAdapter#pop()} su uno
 * stack vuoto.<br>
 * {@link #pushAddsElementAtTheEndOfStack()} - Verifica che il metodo
 * {@link StackAdapter#push(Object)} aggiunga correttamente elementi al termine dello stack.<br>
 * {@link #searchElementInStack()} - Verifica che il metodo {@link StackAdapter#search(Object)}
 * funzioni correttamente su uno stack non vuoto.<br>
 * {@link #searchElementNotInStack()} - Verifica che il metodo {@link StackAdapter#search(Object)}
 * restituisca {@code -1} se l'elemento cercato non è presente nello stack.<br>
 * </p>
 * <p>
 * <b>Test Suite Execution Records</b> {@code 10} test eseguiti, {@code 10} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link StackAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
public class StackAdapterTester {

    /**
     * stack inizializzato prima di ogni test
     */
    private StackAdapter stack;

    /**
     * Costruttore di default
     */
    public StackAdapterTester() {}

    /**
     * Inizializza la variabile {@code stack} come uno stack vuoto di tipo StackAdapter.
     *
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        stack = new StackAdapter();
    }

    // ======== Object push(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link StackAdapter#push(Object)} aggiunga
     * correttamente elementi al termine dello stack.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#push(Object)} aggiunga
     * correttamente elementi al termine dello stack. Questo viene fatto aggiungendo diversi
     * elementi e verificando che l'ultimo elemento dello stack sia sempre quello aggiunto più di
     * recente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack vuoto, mediante un ciclo di {@code 10} iterazioni aggiunge
     * elementi (in particolare aggiunge in ordine 1, 2, 3, ..., 10) e verifica che l'ultimo
     * elemento dello stack sia lo stesso elemento appena aggiunto. Verifica inoltre che la
     * dimensione dello stack aumenti di {@code 1} ad ogni invocazione.
     *
     * Infine verifica che l'ordine sia mantenuto, lo stack finale deve contenere tutti i numeri da
     * 1 a 10 in ordine crescente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack contiene {@code 10} elementi, i numeri da 1 a 10 in ordine
     * crescente.
     * </p>
     * <p>
     * <b>Expected Results</b> Ad ogni iterazione lo stato dello stack è quello atteso.
     * </p>
     */
    @Test
    public void pushAddsElementAtTheEndOfStack() {
        for (int i = 1; i <= 10; i++) {
            stack.push(i);
            assertEquals(i, stack.lastElement());
            assertEquals(i, stack.size());
        }
        assertEquals(10, stack.size());
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, stack.get(i - 1));
        }
    }

    // ======== Object pop() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#pop()} su uno stack vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#pop()} lanci un'eccezione
     * {@link EmptyStackException} quando invocato su uno stack vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack vuoto, chiama la funzione {@link StackAdapter#pop()} e
     * verifica venga lanciata l'eccezione {@link EmptyStackException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack rimane vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#pop()} lancia l'eccezione
     * {@link EmptyStackException}
     * </p>
     */
    @Test(expected = EmptyStackException.class)
    public void popThrowsEmptyStackException() {
        stack.pop();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#pop()} su uno stack non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#pop()} rimuova sempre l'ultimo
     * elemento dello stack e che lo ritorni correttamente quando invocato su uno stack non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack non vuoto, chiama più volte la funzione
     * {@link StackAdapter#pop()} e verifica venga ritornato il corretto elemento e che la
     * dimensione dello stack venga ridotta correttamente
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack è vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#pop()} rimuove l'ultimo elemento ritornandolo
     * correttamente.
     * </p>
     */
    @Test
    public void popRemovesElement() {
        stack.push("A");
        stack.push("B");
        stack.push("C");

        assertEquals(stack.lastElement(), stack.pop());
        assertEquals(2, stack.size());
        assertEquals(stack.lastElement(), stack.pop());
        assertEquals(1, stack.size());
        assertEquals(stack.lastElement(), stack.pop());
        assertEquals(0, stack.size());
    }

    // ======== Object peek() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#peek()} su uno stack vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#peek()} lanci un'eccezione
     * {@link EmptyStackException} quando invocato su uno stack vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack vuoto, chiama la funzione {@link StackAdapter#peek()} e
     * verifica venga lanciata l'eccezione {@link EmptyStackException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack rimane vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#peek()} lancia l'eccezione
     * {@link EmptyStackException}
     * </p>
     */
    @Test(expected = EmptyStackException.class)
    public void peekThrowsEmptyStackException() {
        stack.peek();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#peek()} mentre vengono aggiunti
     * elementi allo stack.
     * </p>
     * <p>
     * <b>Description</b>Verifica del metodo {@link StackAdapter#peek()} mentre vengono aggiunti
     * elementi allo stack. In particolare ci si aspetta che il metodo ritorni sempre l'elemento
     * aggiunto più di recente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack vuoto, in un ciclo viene aggiunto un elemento e viene
     * invocata la funzione {@link StackAdapter#peek()} per verificare che l'elemento ritornato sia
     * lo stesso che è stato appena aggiunto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack contiene degli elementi
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#peek()} ritorna sempre l'ultimo elemento
     * aggiunto.
     * </p>
     */
    @Test
    public void peekWileAdding() {
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
            assertEquals(i, stack.peek());
        }
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#peek()} mentre vengono rimossi
     * elementi dallo stack.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link StackAdapter#peek()} mentre vengono rimossi
     * elementi dallo stack. In particolare ci si aspetta che il metodo ritorni sempre l'elemento
     * che sta per essere rimosso.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack non vuoto, in un ciclo viene invocata la funzione
     * {@link StackAdapter#peek()} e viene rimosso un elemento per verificare che l'elemento
     * ritornato da {@code peek()} sia lo stesso che è stato appena rimosso.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack è vuoto
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#peek()} ritorna sempre l'elemento che sta per
     * essere rimosso. aggiunto.
     * </p>
     */
    @Test
    public void peekWileRemoving() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        while (!stack.empty()) {
            assertEquals(stack.peek(), stack.pop());
        }
    }

    // ======== boolean empty() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#empty()} su uno stack vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#empty()} ritorni {@code true}
     * quando invocato su uno stack vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack con il costruttore di default, chiama la funzione
     * {@link StackAdapter#empty()} e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack rimane vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#empty()} ritorna {@code true}.
     * </p>
     */
    @Test
    public void emptyOnEmptyVector() {
        assertTrue(stack.empty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link StackAdapter#empty()} su uno stack non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#empty()} ritorni {@code false}
     * quando invocato su uno stack non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia uno stack ed aggiunge un elemento, chiama la funzione
     * {@link StackAdapter#empty()} e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link StackAdapter#empty()} ritorna {@code false}.
     * </p>
     */
    @Test
    public void emptyOnNonEmptyVector() {
        stack.push("A");
        assertFalse(stack.isEmpty());
    }

    // ======== int search(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link StackAdapter#search(Object)} restituisca
     * {@code -1} se l'elemento cercato non è presente nello stack.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#search(Object)} restituisca
     * {@code -1} se l'elemento cercato non è presente nello stack. Come specificato dalla
     * documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea uno stack non vuoto, chiama {@link StackAdapter#search(Object)} per un
     * elemento non presente nello stack e verifica che il risultato sia {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link StackAdapter#search(Object)} restituisce {@code -1}
     * </p>
     */
    @Test
    public void searchElementNotInStack() {
        stack.add("A");

        assertEquals(-1, stack.search("D"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link StackAdapter#search(Object)} funzioni
     * correttamente su uno stack non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link StackAdapter#search(Object)} funzioni
     * correttamente su uno stack non vuoto. In particolare viene verificato che il metodo funzioni
     * correttamente anche su elementi duplicati ed elementi {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea uno stack contenente elementi duplicati e {@code null}. Invoca
     * {@link StackAdapter#search(Object)} per tutti glie elementi e verifica che il valore di
     * ritorno sia corretto per tutti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Uno stack contenente elementi duplicati e {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Lo stack rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo{@link StackAdapter#search(Object)} restituisce la distanza
     * corretta dalla cima dello stack per tutti gli elementi.
     * </p>
     */
    @Test
    public void searchElementInStack() {
        stack.add("A");
        stack.add("A");
        stack.add(null);
        stack.add("B");

        assertEquals(2, stack.search(null));
        assertEquals(3, stack.search("A"));
        assertEquals(1, stack.search("B"));
    }
}
