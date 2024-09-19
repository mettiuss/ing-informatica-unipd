package myTest;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import myAdapter.HCollection;
import myAdapter.HIterator;
import myAdapter.HList;
import myAdapter.HListIterator;
import myAdapter.VectorAdapter;
import myAdapter.VectorAdapter.*;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della classe {@link SubListAdapter}. <br>
 * Per eseguire questa test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.SubListAdapterTester}
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
 * <li>{@link #copyIntoThrowsNullPointerException()} - Verifica che il metodo
 * {@link SubListAdapter#copyInto(Object[])} lanci un'eccezione se l'array passato come parametro è
 * {@code null}.</li>
 * <li>{@link #copyIntoSmallerArray()} - Verifica che il metodo
 * {@link SubListAdapter#copyInto(Object[])} lanci un'eccezione se l'array passato come parametro è
 * troppo piccolo.</li>
 * <li>{@link #copyIntoSameSizeArray()} - Verifica che il metodo
 * {@link SubListAdapter#copyInto(Object[])} copi correttamente tutti gli elementi del
 * {@code SubListAdapter} in un array della stessa</li>
 * <li>{@link #copyIntoBiggerArray()} - Verifica che il metodo
 * {@link SubListAdapter#copyInto(Object[])} copi correttamente gli elementi in un array più
 * grande.</li>
 * <li>{@link #setSizeThrowsArrayIndexOutOfBoundsException()} - Verifica che
 * {@link SubListAdapter#setSize(int)} lanci un'eccezione quando invocato con un numero
 * negativo.</li>
 * <li>{@link #setSizeDoesNotChangeSubList()} - Verifica che {@link SubListAdapter#setSize(int)} non
 * modifichi la dimensione della sottolista quando chiamato con la stessa dimensione della
 * sottolista.</li>
 * <li>{@link #setSizeIncreasesSubListSize()} - Verifica che il metodo
 * {@link SubListAdapter#setSize(int)} aumenti correttamente la dimensione della sottolista.</li>
 * <li>{@link #setSizeDecreasesSubListSize()} - Verifica che il metodo
 * {@link SubListAdapter#setSize(int)} diminuisca correttamente la dimensione della sottolista.</li>
 * <li>{@link #isEmptyOnEmptySubList()} - Verifica del metodo {@link SubListAdapter#isEmpty()} su
 * una sottolista vuota.</li>
 * <li>{@link #isEmptyOnNonEmptySubList()} - Verifica del metodo {@link SubListAdapter#isEmpty()} su
 * una sottolista non vuota.</li>
 * <li>{@link #hasMoreElementsOnEmpty()} - Verifica che il metodo
 * {@link EnumerationAdapter#hasMoreElements()} ritorni {@code false} se non ci sono altri elementi
 * di iterazione.</li>
 * <li>{@link #hasMoreElementsOnNonEmpty()} - Verifica che il metodo
 * {@link EnumerationAdapter#hasMoreElements()} ritorni {@code true} se ci sono altri elementi di
 * iterazione.</li>
 * <li>{@link #nextElementThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link EnumerationAdapter#nextElement()} lanci un'eccezione se non ci sono ulteriori elementi
 * nell'enumerazione.</li>
 * <li>{@link #nextElementReturnsCorrectElements()} - Verifica che il metodo
 * {@link EnumerationAdapter#nextElement()} ritorni tutti gli elementi nella sottolista nell'ordine
 * corretto.</li>
 * <li>{@link #containsReturnsFalse()} - Verifica del metodo {@link SubListAdapter#contains(Object)}
 * quando l'elemento non è presente nella sottolista.</li>
 * <li>{@link #indexOfElementFound()} - Verifica che il metodo
 * {@link SubListAdapter#indexOf(Object)} restituisca correttamente l'indice della prima occorrenza
 * di un elemento presente nella sottolista.</li>
 * <li>{@link #indexOfElementNotFound()} - Verifica che il metodo
 * {@link SubListAdapter#indexOf(Object)} restituisca {@code -1} quando l'elemento cercato non è
 * presente nella sottolista.</li>
 * <li>{@link #indexOfFromIndexThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#indexOf(Object, int)} lanci un'eccezione se l'indice specificato è
 * negativo.</li>
 * <li>{@link #indexOfFromIndexWithIndexBiggerThanVector()} - Verifica del metodo
 * {@link VectorAdapter#indexOf(Object, int)} quando invocato con un indice maggiore della
 * dimensione della sottolista.</li>
 * <li>{@link #lastindexOfElementFound()} - Verifica che il metodo
 * {@link SubListAdapter#lastIndexOf(Object)} restituisca correttamente l'indice dell'ultima
 * occorrenza di un elemento presente nella sottolista.</li>
 * <li>{@link #lastIndexOfElementNotFound()} - Verifica che il metodo
 * {@link SubListAdapter#lastIndexOf(Object)} restituisca {@code -1} quando l'elemento cercato non è
 * presente nella sottolista.</li>
 * <li>{@link #lastIndexOfFromIndexThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#lastIndexOf(Object, int)} lanci un'eccezione se l'indice specificato è
 * maggiore della dimensione della sottolista.</li>
 * <li>{@link #lastIndexOfNegativeIndex()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object, int)} ritorni {@code -1} quando invocato con un indice
 * negativo.</li>
 * <li>{@link #elementAtThrowsExceptionForNegativeIndex()} - Verifica che il metodo
 * {@link SubListAdapter#elementAt(int)} lanci un'eccezione quando invocato con un indice
 * negativo.</li>
 * <li>{@link #elementAtThrowsExceptionForIndexGreaterThanSize()} - Verifica che il metodo
 * {@link SubListAdapter#elementAt(int)} lanci un'eccezione quando invocato con un indice uguale
 * alla dimensione della sottolista.</li>
 * <li>{@link #elementAtOnVectorWithElements()} - Verifica che il metodo
 * {@link SubListAdapter#elementAt(int)} funzioni correttamente.</li>
 * <li>{@link #firstElementThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link SubListAdapter#firstElement()} lanci un'eccezione quando la sottolista è vuota.</li>
 * <li>{@link #firstElementReturnsFirstElement()} - Verifica che il metodo
 * {@link SubListAdapter#firstElement()} restituisca il primo elemento di una sottolista non
 * vuota.</li>
 * <li>{@link #lastElementThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link SubListAdapter#lastElement()} lanci un'eccezione quando la sottolista è vuota.</li>
 * <li>{@link #lastElementReturnsFirstElement()} - Verifica che il metodo
 * {@link SubListAdapter#lastElement()} restituisca l'ultimo elemento di una sottolista non
 * vuota.</li>
 * <li>{@link #setElementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#setElementAt(Object, int)} lanci un'eccezione quando l'indice fornito è
 * negativo.</li>
 * <li>{@link #setElementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#setElementAt(Object, int)} lanci un'eccezione quando l'indice fornito è
 * maggiore o uguale alla dimensione della sottolista.</li>
 * <li>{@link #setElementAtUpdatesValue()} - Verifica che il metodo
 * {@link SubListAdapter#setElementAt(Object, int)} imposti correttamente il valore di un elemento a
 * un indice valido.</li>
 * <li>{@link #removeElementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#removeElementAt(int index)} lanci un'eccezione se l'indice è negativo.</li>
 * <li>{@link #removeElementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#removeElementAt(int index)} lanci un'eccezione se l'indice è maggiore o
 * uguale alla dimensione della sottolista.</li>
 * <li>{@link #removeElementAtRemovesAllElements()} - Verifica che il metodo
 * {@link SubListAdapter#removeElementAt(int index)} rimuova correttamente gli elementi da una
 * lista.</li>
 * <li>{@link #insertElementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#insertElementAt(Object, int)} lanci un'eccezione se invocato con un indice
 * negativo.</li>
 * <li>{@link #insertElementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#insertElementAt(Object, int)} lanci un'eccezione se invocato con un indice
 * maggiore della dimensione.</li>
 * <li>{@link #insertElementAtAtIndexOnNonEmpty()} - Verifica che il metodo
 * {@link SubListAdapter#insertElementAt(Object, int)} aggiunga correttamente gli elementi su una
 * sottolista.</li>
 * <li>{@link #addElementAddsElementAtTheEndOfVector()} - Verifica che il metodo
 * {@link SubListAdapter#addElement(Object)} aggiunga correttamente elementi al termine della
 * sottolista.</li>
 * <li>{@link #removeElementFirstOccurrence()} - Verifica che il metodo
 * {@link SubListAdapter#removeElement(Object obj)} rimuova solamente la prima occorrenza
 * dell'oggetto specificato nella sottolista.</li>
 * <li>{@link #removeElementNotPresent()} - Verifica il metodo
 * {@link SubListAdapter#removeElement(Object obj)} quando l'oggetto da rimuovere non è presente
 * nella sottolista.</li>
 * <li>{@link #removeAllElementsEmptiesVector()} - Verifica che il metodo
 * {@link SubListAdapter#removeAllElements()} rimuova correttamente gli elementi dalla
 * sottolista.</li>
 * <li>{@link #cloneClonesSubList()} - Verifica che il metodo {@link SubListAdapter#clone()}
 * restituisca un nuovo oggetto che contiene gli stessi elementi della sottolista originale.</li>
 * <li>{@link #toArrayMultipleElements()} - Verifica che il metodo {@link SubListAdapter#toArray()}
 * restituisca un array contenente gli elementi presenti nella sottolista.</li>
 * <li>{@link #toArrayMultipleElements2()} - Verifica che il metodo
 * {@link SubListAdapter#toArray(Object[])} popoli correttamente l'array fornito quando ha una
 * dimensione uguale a quella della sottolista.</li>
 * <li>{@link #getThrowsExceptionForNegativeIndex()} - Verifica che il metodo
 * {@link SubListAdapter#get(int)} lanci un'eccezione quando invocato con un indice negativo.</li>
 * <li>{@link #getThrowsExceptionForIndexGreaterThanSize()} - Verifica che il metodo
 * {@link SubListAdapter#get(int)} lanci un'eccezione quando invocato con un indice uguale alla
 * dimensione della sottolista.</li>
 * <li>{@link #getOnVectorWithElements()} - Verifica che il metodo {@link SubListAdapter#get(int)}
 * funzioni correttamente.</li>
 * <li>{@link #setThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#set(int, Object)} lanci un'eccezione quando l'indice fornito è
 * negativo.</li>
 * <li>{@link #setThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#set(int, Object)} lanci un'eccezione quando l'indice fornito è maggiore o
 * uguale alla dimensione della sottolista.</li>
 * <li>{@link #setUpdatesValue()} - Verifica che il metodo {@link SubListAdapter#set(int, Object)}
 * imposti correttamente il valore di un elemento a un indice valido.</li>
 * <li>{@link #addAddsElementAtTheEndOfVector()} - Verifica che il metodo
 * {@link SubListAdapter#add(Object)} aggiunga correttamente elementi al termine della
 * sottolista.</li>
 * <li>{@link #removeFirstOccurrence()} - Verifica che il metodo
 * {@link SubListAdapter#remove(Object obj)} rimuova solamente la prima occorrenza dell'oggetto
 * specificato nella sottolista.</li>
 * <li>{@link #removeNotPresent()} - Verifica il metodo {@link SubListAdapter#remove(Object obj)}
 * quando l'oggetto da rimuovere non è presente nella sottolista.</li>
 * <li>{@link #addAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#add(int, Object)} lanci un'eccezione se invocato con un indice
 * negativo.</li>
 * <li>{@link #addAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#add(int, Object)} lanci un'eccezione se invocato con un indice maggiore
 * della dimensione.</li>
 * <li>{@link #addAtIndexOnNonEmpty()} - Verifica che il metodo
 * {@link SubListAdapter#add(int, Object)} aggiunga correttamente gli elementi su una
 * sottolista.</li>
 * <li>{@link #removeThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#remove(int)} lanci un'eccezione se l'indice è negativo.</li>
 * <li>{@link #removeThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#remove(int)} lanci un'eccezione se l'indice è maggiore o uguale alla
 * dimensione della sottolista.</li>
 * <li>{@link #removeRemovesAllElements()} - Verifica che il metodo
 * {@link SubListAdapter#remove(int)} rimuova correttamente gli elementi da una lista.</li>
 * <li>{@link #clearEmptiesVector()} - Verifica che il metodo {@link SubListAdapter#clear()} rimuova
 * correttamente gli elementi dalla sottolista.</li>
 * <li>{@link #containsAllElementsNotPresent()} - Verifica che il metodo
 * {@link SubListAdapter#containsAll(HCollection)} restituisca {@code false} quando la sottolista
 * non contiene tutti gli elementi della</li>
 * <li>{@link #addAllonNonEmptyVector()} - Verifica che il metodo
 * {@link SubListAdapter#addAll(HCollection)} aggiunga correttamente tutti gli elementi dalla
 * collezione specificata.</li>
 * <li>{@link #addAllOnSelf()} - Verifica il comportamento del metodo
 * {@link SubListAdapter#addAll(HCollection)} quando l'argomento è la sottolista stessa.</li>
 * <li>{@link #removeAllReturnsFalse()} - Verifica che il metodo
 * {@link SubListAdapter#removeAll(HCollection)} funzioni correttamente sulla sottolista.</li>
 * <li>{@link #retainAllRemovesAll()} - Verifica che il metodo
 * {@link SubListAdapter#retainAll(HCollection)} si comporti correttamente quando invocato
 * utilizzando come parametro una collezione vuota.</li>
 * <li>{@link #addAllAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#addAll(int, HCollection)} lanci un'eccezione se l'indice è negativo.</li>
 * <li>{@link #addAllAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link SubListAdapter#addAll(int, HCollection)} lanci un'eccezione se l'indice è maggiore alla
 * dimensione della sottolista.</li>
 * <li>{@link #equalsReturnsTrue()} - Verifica che il metodo {@link SubListAdapter#equals(Object)}
 * restituisca {@code true} quando una sottolista ed un vettore contengono gli stessi elementi nello
 * stesso</li>
 * <li>{@link #testEqualsWithSelf()} - Verifica che il metodo {@link SubListAdapter#equals(Object)}
 * restituisca {@code true} quando la sottolist viene confrontata con sé stessa.</li>
 * <li>{@link #hashCodeOnSubList()} - Verifica che il metodo {@link SubListAdapter#hashCode()}
 * ritorni codici hash diversi per il vettore e la sottolista.</li>
 * <li>{@link #toStringOnSubList()} - Verifica che il metodo {@link SubListAdapter#toString()}
 * restituisca la stringa corretta per una sottolista non vuota.</li>
 * <li>{@link #testToStringWithCyclicReference()} - Verifica che il metodo
 * {@link SubListAdapter#toString()} gestisca correttamente i riferimenti ciclici a se stesso.</li>
 * <li>{@link #subListThrowsIndexOutOfBoundsException()} - Verifica che
 * {@link SubListAdapter#subList(int, int)} lanci un'eccezione quando {@code fromIndex < 0}.</li>
 * <li>{@link #subListThrowsIndexOutOfBoundsException2()} - Verifica che
 * {@link SubListAdapter#subList(int, int)} lanci un'eccezione quando {@code toIndex > size}.</li>
 * <li>{@link #subListBetweenRange()} - Verifica che {@link SubListAdapter#subList(int, int)}
 * ritorni la corretta vista della sottolista.</li>
 * <li>{@link #subListFullRange()} - Verifica che {@link SubListAdapter#subList(int, int)} ritorni
 * la corretta vista della sottolista.</li>
 * <li>{@link #listIteratorThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#listIterator(int)} lanci un'eccezione quando invocato con un indice
 * negativo.</li>
 * <li>{@link #listItreatorThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link SubListAdapter#listIterator(int)} lanci un'eccezione {@link IndexOutOfBoundsException}
 * quando invocato con un indice uguale alla</li>
 * <li>{@link #listIteratorReturnsCorrectView()} - Verifica del metodo
 * {@link SubListAdapter#listIterator(int)} ritorni un oggetto di tipo {@link HListIterator} che
 * inizia dall'indice corretto.</li>
 * <li>{@link #hasNextOnEmpty()} - Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni
 * {@code false} se non ci sono altri elementi di iterazione.</li>
 * <li>{@link #nextThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link IteratorAdapter#next()} lanci un'eccezione se non ci sono ulteriori elementi
 * nell'iterazione.</li>
 * <li>{@link #nextReturnsCorrectElements()} - Verifica che il metodo {@link IteratorAdapter#next()}
 * ritorni tutti gli elementi nella sottolista nell'ordine corretto.</li>
 * <li>{@link #removeRemovesElementFromVector()} - Verifica che il metodo
 * {@link IteratorAdapter#remove()} rimuova correttamente l'ultimo elemento ritornato sia dalla
 * sottolista che dal vettore.</li>
 * <li>{@link #hasPreviousOnEmpty()} - Verifica che il metodo
 * {@link ListIteratorAdapter#hasPrevious()} ritorni {@code false} se non ci sono altri elementi di
 * iterazione.</li>
 * <li>{@link #previousThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previous()} lanci un'eccezione se non ci sono ulteriori elementi
 * nell'iterazione.</li>
 * <li>{@link #previousReturnsCorrectElements()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previous()} ritorni tutti gli elementi della sottolista nell'ordine
 * corretto.</li>
 * <li>{@link #nextIndexOnEmptyList()} - Verifica che il metodo
 * {@link ListIteratorAdapter#nextIndex()} restituisca {@code 0} quando viene chiamato su un
 * iteratore di una sottolista vuota.</li>
 * <li>{@link #nextIndexWithNext()} - Verifica che il metodo {@link ListIteratorAdapter#nextIndex()}
 * restituisca l'indice corretto mentre viene invocato {@link ListIteratorAdapter#next()}</li>
 * <li>{@link #previousIndexOnEmptyList()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca {@code -1} quando viene chiamato su un
 * iteratore di una sottolista vuota.</li>
 * <li>{@link #previousIndexWithPrevious()} - Verifica che il metodo
 * {@link ListIteratorAdapter#previousIndex()} restituisca l'indice corretto mentre viene invocato
 * {@link ListIteratorAdapter#previous()}</li>
 * <li>{@link #setAddsToVector()} - Verifica che il metodo {@link ListIteratorAdapter#set(Object)}
 * sostituisca correttamente l'ultimo elemento restituito da
 * {@link ListIteratorAdapter#next()}.</li>
 * <li>{@link #addElementAtCorrectPosition()} - Verifica che il metodo
 * {@link ListIteratorAdapter#add(Object)} inserisca correttamente un elemento nella sottolista e
 * nel vettore.</li>
 * </ul>
 * <p>
 * <b>Test Suite Execution Records</b> {@code 94} test eseguiti, {@code 94} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link VectorAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
public class SubListAdapterTester {

    /**
     * vettore inizializzato prima di ogni test
     */
    private VectorAdapter vector;

    /**
     * Costruttore di default
     */
    public SubListAdapterTester() {}

    /**
     * Inizializza la variabile {@code subList} come una sottolista vuota di tipo SubListAdapter.
     *
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        vector = new VectorAdapter();
    }

    private SubListAdapter getSubList(int parentElements, int fromIndex, int toIndex) {
        for (int i = 1; i <= parentElements; i++) {
            vector.add("Elemento" + i);
        }
        return (SubListAdapter) vector.subList(fromIndex, toIndex);
    }

    // ======== void copyInto(Object[]) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} lanci
     * un'eccezione se l'array passato come parametro è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} lanci
     * un'eccezione {@link NullPointerException} se l'array passato come parametro è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista vuota. Invoca il metodo
     * {@link SubListAdapter#copyInto(Object[])} sulla sottolista con {@code null} come parametro.
     * Verifica che venga sollevata un'eccezione {@link NullPointerException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#copyInto(Object[])} lancia
     * un'eccezione {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void copyIntoThrowsNullPointerException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.copyInto(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} lanci
     * un'eccezione se l'array passato come parametro è troppo piccolo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} lanci
     * un'eccezione {@link IndexOutOfBoundsException} se l'array passato come parametro è troppo
     * piccolo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di due elementi e un array lunghezza 1. Invoca il
     * metodo {@link SubListAdapter#copyInto(Object[])} sulla sottolista con l'array come parametro.
     * Si verifica che venga sollevata un'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di 2 elementi e un array di dimensione 1.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e l'array rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#copyInto(Object[])} lancia
     * un'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void copyIntoSmallerArray() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add("A");
        subList.add("B");

        Object[] array = new Object[1];

        subList.copyInto(array);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} copi
     * correttamente tutti gli elementi del {@code SubListAdapter} in un array della stessa
     * dimensione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} copi
     * correttamente tutti gli elementi della sottolista in un array della stessa dimensione. Viene
     * inoltre verificato che l'ordine degli elementi sia corretto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di 3 elementi e un array di 3 elementi di tipo
     * {@link Object}, invoca il metodo {@link SubListAdapter#copyInto(Object[])} sulla sottolista
     * ponendo l'array come argomento. Infine viene verificato che l'elemento di indice {@code k}
     * della sottolista e l'elemento di indice {@code k} dell'array sono uguali
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di 3 elementi e un array è di 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'array contiene gli stessi elementi della sottolista, nello stesso
     * ordine. La sottolista rimane invariato
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento di indice {@code k} della sottolista e l'elemento di
     * indice {@code k} dell'array sono uguali.
     * </p>
     */
    @Test
    public void copyIntoSameSizeArray() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add("A");
        subList.add("B");
        subList.add("C");

        Object[] array = new Object[3];
        subList.copyInto(array);

        assertEquals("A", subList.get(0));
        assertEquals("B", subList.get(1));
        assertEquals("C", subList.get(2));
        assertEquals(3, subList.size());

        assertEquals(subList.get(0), array[0]);
        assertEquals(subList.get(1), array[1]);
        assertEquals(subList.get(2), array[2]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} copi
     * correttamente gli elementi in un array più grande.
     * </p>
     * <p>
     * <b>Description</b>Verifica che il metodo {@link SubListAdapter#copyInto(Object[])} copi
     * correttamente gli elementi in un array più grande. Le posizioni non utilizzate dell'array
     * dovrebbero rimanere invariate, ovvero {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di due elementi e un array di dimensione {@code 3}.
     * Invoca {@link SubListAdapter#copyInto(Object[])} sulla sottolista con l'array come parametro.
     * Verifica che i primi due elementi dell'array coincidano con quelli della sottolista, e
     * l'ultimo sia rimasto {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di 2 elementi e un array di dimensione 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificata.
     * </p>
     * <p>
     * <b>Expected Results</b> Nell'array i primi due elementi contengono gli elementi della
     * sottolista, l'ultimo elemento rimane {@code null}.
     * </p>
     */

    @Test
    public void copyIntoBiggerArray() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add("A");
        subList.add("B");

        Object[] array = new Object[3];
        subList.copyInto(array);

        assertEquals(subList.get(0), array[0]);
        assertEquals(subList.get(1), array[1]);
        assertNull(array[2]);
    }

    // ======== void setSize(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link SubListAdapter#setSize(int)} lanci un'eccezione quando
     * invocato con un numero negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link SubListAdapter#setSize(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando viene invocato con un numero negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista vuota, invoca su di esso il metodo
     * {@code setSize(-1)}, verifica che venga lanciata l'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#setSize(int)} lancia l'eccezione
     * {@link ArrayIndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setSizeThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.setSize(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link SubListAdapter#setSize(int)} non modifichi la dimensione
     * della sottolista quando chiamato con la stessa dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link SubListAdapter#setSize(int)} non modifichi la
     * dimensione della sottolista quando chiamato con la stessa dimensione della sottolista. In
     * particolare verifichiamo che su una sottolista di {@code 1} elemento invocando
     * {@code setSize(1)} la sottolista non venga modificata.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di un elmento, invoca il metodo {@code setSize(1)} e
     * verifica che dimensione e contenuto della sottolista e del vettore non siano cambiati.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificata. Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> La chiamata {@code setSize(1)} non modifica la sottolista e neanche
     * il vettore.
     * </p>
     */
    @Test
    public void setSizeDoesNotChangeSubList() {
        SubListAdapter subList = getSubList(2, 0, 1);
        subList.setSize(1);
        assertEquals(1, subList.size());
        assertEquals("Elemento1", subList.get(0));

        assertEquals(2, vector.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#setSize(int)} aumenti
     * correttamente la dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#setSize(int)} aumenti
     * correttamente la dimensione della sottolista. Verifica inoltre che gli elementi successivi
     * all'ultimo elemento vengano impostati a {@code null}.
     * </p>
     * <p>
     * <b>Design</b><br>
     * Instanzia un vettore di due elementi, {@code [Element1, Element2]}, instanzia una subList
     * contenente un elmento invocando {@code vector.subList(0, 1)} ({@code [Element1]}). <br>
     * Invoca poi {@code setSize(2)}. Verifica che la sottolista abbia dimensione {@code 2} e che
     * contenga {@code [Element1, null]}. <br>
     * Infine verifica che il vettore abbia dimensione {@code 3} e che contenga
     * {@code [Element1, null, Element2]}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 1} elemento derivata da un vettore di {@code 2}
     * elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista ha dimensione {@code 2}. Il vettore ha dimensione
     * {@code 3}.
     * </p>
     * <p>
     * <b>Expected Results</b> La sottolista ha dimensione {@code 2} e che contiene
     * {@code [Element1, null]}. Il vettore ha dimensione {@code 3} e contiene
     * {@code [Element1, null, Element2]}
     * </p>
     */
    @Test
    public void setSizeIncreasesSubListSize() {
        SubListAdapter subList = getSubList(2, 0, 1);
        subList.setSize(2);

        assertEquals(2, subList.size());
        assertEquals("Elemento1", subList.get(0));
        assertNull(subList.get(1));

        assertEquals(3, vector.size());
        assertEquals("Elemento1", vector.get(0));
        assertNull(vector.get(1));
        assertEquals("Elemento2", vector.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#setSize(int)} diminuisca
     * correttamente la dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#setSize(int)} aumenti
     * correttamente la dimensione della sottolista. Verifica inoltre che gli elementi successivi
     * all'ultimo elemento vengano rimossi correttamente.
     * </p>
     * <p>
     * <b>Design</b> <br>
     * Instanzia un vettore di {@code 3} elementi, {@code [Elemento1, Elemento2, Elemento3]},
     * instanzia una subList contenente tutti e due gli elementi invocando
     * {@code vector.subList(0, 2)} ({@code [Elemento1, Elemento2]}). <br>
     * Invoca poi {@code setSize(1)}. Verifica che la sottolista abbia dimensione {@code 1} e che
     * contenga {@code [Elemento1]}. <br>
     * Infine verifica che il vettore abbia dimensione {@code 2} e che contenga
     * {@code [Elemento1, Elemento3]}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 2} elementi derivata da un vettore di {@code 3}
     * elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista ha dimensione {@code 1}. Il vettore ha dimensione
     * {@code 2}.
     * </p>
     * <p>
     * <b>Expected Results</b> La sottolista ha dimensione {@code 1} e che contiene
     * {@code [Elemento1]}. Il vettore ha dimensione {@code 2} e contiene
     * {@code [Elemento1, Elemento3]}
     * </p>
     */
    @Test
    public void setSizeDecreasesSubListSize() {
        SubListAdapter subList = getSubList(3, 0, 1);
        subList = (SubListAdapter) vector.subList(0, 2);
        subList.setSize(1);

        assertEquals(1, subList.size());
        assertEquals("Elemento1", subList.get(0));

        assertEquals(2, vector.size());
        assertEquals("Elemento1", vector.get(0));
        assertEquals("Elemento3", vector.get(1));
    }

    // ======== boolean isEmpty() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link SubListAdapter#isEmpty()} su una sottolista vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che la {@link SubListAdapter#isEmpty()} ritorni {@code true}
     * quando invocata su una sottolista vuota generata da un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, poi crea una sottolista vuota invocando
     * {@code subList(0, 0)}. Infine viene verificato che {@link SubListAdapter#isEmpty()} ritorni
     * {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link SubListAdapter#isEmpty()} ritorna {@code true}.
     * </p>
     */
    @Test
    public void isEmptyOnEmptySubList() {
        SubListAdapter subList = getSubList(2, 0, 0);
        assertTrue(subList.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link SubListAdapter#isEmpty()} su una sottolista non
     * vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che la {@link SubListAdapter#isEmpty()} ritorni {@code true}
     * quando invocata su una sottolista non vuota generata da un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, poi crea una sottolista vuota invocando
     * {@code subList(0, 1)}. Infine viene verificato che {@link SubListAdapter#isEmpty()} ritorni
     * {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link SubListAdapter#isEmpty()} ritorna {@code false}
     * </p>
     */
    @Test
    public void isEmptyOnNonEmptySubList() {
        SubListAdapter subList = getSubList(2, 0, 1);
        assertFalse(subList.isEmpty());
    }

    // ======== boolean Enumeration.hasMoreElements() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#hasMoreElements()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#hasMoreElements()}
     * ritorni {@code false} se non ci sono altri elementi di iterazione. Questo viene verificato su
     * un'enumerazione su una sottolista vuota, ma costruita su un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia una sottolista vuota,
     * sulla quale instanzia un'enumerazione grazie al metodo {@link SubListAdapter#elements()}.
     * Chiama poi il metodo {@link EnumerationAdapter#hasMoreElements()} e verifica che ritorni
     * {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto, una sottolista vuota e l'enumerazione è
     * instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione, il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link EnumerationAdapter#hasMoreElements()} ritorna {@code false}.
     * </p>
     */
    @Test
    public void hasMoreElementsOnEmpty() {
        SubListAdapter subList = getSubList(1, 0, 0);
        Enumeration enumeration = subList.elements();

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
     * un'enumerazione su una sottolista non vuota.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista contenente un elemento, su di essa instanzia
     * un'enumerazione grazie al metodo {@link SubListAdapter#elements()}. Chiama poi il metodo
     * {@link EnumerationAdapter#hasMoreElements()} e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista contiene un elemento e l'enumerazione è instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link EnumerationAdapter#hasMoreElements()} ritorna {@code true}.
     * </p>
     */
    @Test
    public void hasMoreElementsOnNonEmpty() {
        SubListAdapter subList = getSubList(1, 0, 1);
        Enumeration enumeration = subList.elements();

        assertTrue(enumeration.hasMoreElements());
    }

    // ======== Object Enumeration.nextElement() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'enumerazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'enumerazione. Questo viene verificato su
     * un'enumerazione su una sottolista vuota, ma il vettore a cui essa fa riferimento non è vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia una sottolista vuota,
     * sulla quale instanzia un'enumerazione grazie al metodo {@link SubListAdapter#elements()}.
     * Chiama poi il metodo {@link EnumerationAdapter#nextElement()} e verifica che venga lanciata
     * l'eccezione {@link NoSuchElementException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto, una sottolista vuota e un'enumerazione
     * instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione, il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link EnumerationAdapter#nextElement()} lancia l'eccezione
     * {@link NoSuchElementException}.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void nextElementThrowsNoSuchElementException() {
        SubListAdapter subList = getSubList(1, 0, 0);
        Enumeration enumeration = subList.elements();

        enumeration.nextElement();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} ritorni tutti
     * gli elementi nella sottolista nell'ordine corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link EnumerationAdapter#nextElement()} ritorni
     * tutti gli elementi nella sottolista nell'ordine corretto. Questo viene verificato eseguendo
     * in coppia {@link EnumerationAdapter#nextElement()} e {@link SubListAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, su di esso instanzia una sottolista non vuota
     * (di dimensione inferiore al vettore), sulla quale instanzia un'enumerazione grazie al metodo
     * {@link SubListAdapter#elements()}.
     *
     * Viene poi eseguita un'iterazione su tutta l'Enumerazione all'interno della quale ci si
     * aspetta che {@code nextElement()} sia uguale a {@code get(i)} (Dove {@code i} viene
     * incrementato di {@code 1} ad ogni iterazione).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore e una sottolista non vuoti e un'enumerazione instanziata.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'enumerazione, il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextElement()} e {@code get(i)} ritornano gli stessi valori.
     * </p>
     */
    @Test
    public void nextElementReturnsCorrectElements() {
        SubListAdapter subList = getSubList(10, 2, 6);

        Enumeration enumeration = subList.elements();

        int i = 0;
        while (enumeration.hasMoreElements()) {
            assertEquals(subList.get(i), enumeration.nextElement());
            i++;
        }
    }

    // ======== boolean contains(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link SubListAdapter#contains(Object)} quando l'elemento
     * non è presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link SubListAdapter#contains(Object)} quando
     * l'elemento non è presente nella sottolista, ma è presente nel vettore da cui essa è derivata.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto contenente {@code "Elemento1"}, poi invoca
     * {@code sottolista(1, 2)} in modo che essa con contenga {@code "Elemento1"}. Chiama poi il
     * metodo {@link SubListAdapter#contains(Object)} con l'elemento {@code "Elemento1"} che
     * sappiamo essere presente nel vettore, ma non nella sottolista, e verifica che ritorni
     * {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link SubListAdapter#contains(Object)} ritorna {@code false}
     * </p>
     */
    @Test
    public void containsReturnsFalse() {
        SubListAdapter subList = getSubList(2, 1, 2);

        assertFalse(subList.contains("Elemento1"));
        assertTrue(vector.contains("Elemento1"));
    }

    // ======== int indexOf(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#indexOf(Object)} restituisca
     * correttamente l'indice della prima occorrenza di un elemento presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#indexOf(Object)} restituisca
     * correttamente l'indice della prima occorrenza di un elemento presente nella sottolista.
     * L'elemento cercato è contenuto più volte nella sottolista, ed è anche contenuto nel vettore
     * ad un indice più basso di quello della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore con più elementi ({@code ["X", "Elemento1", "X", "X"]}) dal
     * quale instanzia una sottolista di 3 elementi ({@code ["Elemento1", "X", "X"]}). Utilizza il
     * metodo {@link SubListAdapter#indexOf(Object)} per cercare l'elemento {@code "X"}, verifica
     * che il metodo restituisca l'indice corretto per la sottolista ({@code 1}) e per il vettore
     * ({@code 0}).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota contenente un elemento duplicato.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore rimangono invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice ({@code 1}) per la sottolista e
     * l'indice ({@code 0}) per il vettore quando si ricerca l'elemento {@code "X"}.
     * </p>
     */
    @Test
    public void indexOfElementFound() {
        vector.add("X");
        vector.add("Elemento1");
        vector.add("X");
        vector.add("X");

        SubListAdapter subList = (SubListAdapter) vector.subList(1, 4);

        assertEquals(1, subList.indexOf("X"));
        assertEquals(0, vector.indexOf("X"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#indexOf(Object)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#indexOf(Object)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nella sottolista. Anche se esso è
     * presente nel vettore, come descritto dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista non vuota.
     * Utilizza il metodo {@link SubListAdapter#indexOf(Object)} utilizzando come parametro un
     * elemento non presente nella sottolista. Verifica che il metodo restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code -1} poiché l'elemento cercato non è
     * presente nella sottolista.
     * </p>
     */
    @Test
    public void indexOfElementNotFound() {
        SubListAdapter subList = getSubList(4, 1, 3);

        assertEquals(-1, subList.indexOf("Elemento1"));
    }

    // ======== int indexOf(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#indexOf(Object, int)} lanci
     * un'eccezione se l'indice specificato è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#indexOf(Object, int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} se l'indice specificato è negativo. Questo
     * deve avvenire anche se nel vettore iniziale è presente un elemento prima dell'inizio della
     * sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista vuota. Invoca il
     * metodo {@code indexOf(Object, -1)} . Verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata l'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfFromIndexThrowsIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(2, 1, 1);
        subList.indexOf("A", -1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#indexOf(Object, int)} quando invocato
     * con un indice maggiore della dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#indexOf(Object, int)} quando
     * invocato con un indice maggiore della dimensione della sottolista. In particolare ci si
     * aspetta che il metodo ritorni ({@code -1}) anche se l'elemento è presente in una posizione
     * maggiore dell'indice specificato nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore di lunghezza {@code 10}, con l'elemento {@code "X"} in
     * posizione {@code 8}, instanzia una sottolista che contiene solo il primo elmento del vettore.
     * Invoca poi il metodo {@code indexOf("X", 5)}. Verifica che il metodo ritorni {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di lunghezza {@code 10}, con l'elemento {@code "X"} in
     * posizione {@code 8}. Una sottolista che contiene solo il primo elmento del vettore.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#indexOf(Object, int)} ritorna
     * {@code -1}
     * </p>
     */
    @Test
    public void indexOfFromIndexWithIndexBiggerThanVector() {
        SubListAdapter subList = getSubList(10, 0, 1);
        vector.set(8, "X");

        assertEquals(-1, subList.indexOf("X", 5));
    }

    // ======== int lastIndexOf(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#lastIndexOf(Object)} restituisca
     * correttamente l'indice dell'ultima occorrenza di un elemento presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#lastIndexOf(Object)}
     * restituisca correttamente l'indice dell'ultima occorrenza di un elemento presente nella
     * sottolista. L'elemento cercato è contenuto più volte nella sottolista, ed è anche contenuto
     * nel vettore ad un indice più alto di quello della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore con più elementi ({@code ["X", "Elemento1", "X", "X"]}) dal
     * quale instanzia una sottolista di 3 elementi ({@code ["X", "Elemento1", "X"]}). Utilizza il
     * metodo {@link SubListAdapter#indexOf(Object)} per cercare l'elemento {@code "X"}, verifica
     * che il metodo restituisca l'indice corretto per la sottolista ({@code 2}) e per il vettore
     * ({@code 3}).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota contenente un elemento duplicato.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore rimangono invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice ({@code 2}) per la sottolista e
     * l'indice ({@code 3}) per il vettore quando si ricerca l'elemento {@code "X"}.
     * </p>
     */
    @Test
    public void lastindexOfElementFound() {
        vector.add("X");
        vector.add("Elemento1");
        vector.add("X");
        vector.add("X");

        SubListAdapter subList = (SubListAdapter) vector.subList(1, 4);

        assertEquals(2, subList.lastIndexOf("X"));
        assertEquals(3, vector.lastIndexOf("X"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#lastIndexOf(Object)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#lastIndexOf(Object)}
     * restituisca {@code -1} quando l'elemento cercato non è presente nella sottolista. Anche se
     * esso è presente nel vettore, come descritto dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista non vuota.
     * Utilizza il metodo {@link SubListAdapter#lastIndexOf(Object)} utilizzando come parametro un
     * elemento non presente nella sottolista. Verifica che il metodo restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code -1} poiché l'elemento cercato non è
     * presente nella sottolista.
     * </p>
     */
    @Test
    public void lastIndexOfElementNotFound() {
        SubListAdapter subList = getSubList(4, 1, 3);

        assertEquals(-1, subList.lastIndexOf("Elemento1"));
    }

    // ======== int lastIndexOf(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#lastIndexOf(Object, int)} lanci
     * un'eccezione se l'indice specificato è maggiore della dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#lastIndexOf(Object, int)}
     * lanci un'eccezione {@link IndexOutOfBoundsException} se l'indice specificato è maggiore della
     * dimensione della sottolista. Questo deve avvenire anche se lo stesso indice è minore della
     * dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista vuota. Invoca il
     * metodo {@code lastIndexOf(Object, 1)}. Verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata l'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void lastIndexOfFromIndexThrowsIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(2, 1, 1);
        subList.lastIndexOf("A", 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)} ritorni
     * {@code -1} quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * ritorni {@code -1} quando invocato con un indice negativo. Come definito dalla
     * documentazione, anche se un elemento a questo indice esiste nel vettore iniziale (inziando a
     * contare dall'indice iniziale della sottolista).
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista vuota, invoca il
     * metodo {@link VectorAdapter#lastIndexOf(Object, int)} con un indice negativo . Verifica che
     * il metodo ritorni {@code -1}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code -1}
     * </p>
     */
    @Test
    public void lastIndexOfNegativeIndex() {
        SubListAdapter subList = getSubList(2, 1, 1);
        assertEquals(-1, subList.lastIndexOf("A", -1));
    }

    // ======== Object elementAt(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#elementAt(int)} lanci
     * un'eccezione quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#elementAt(int)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando invocato con un indice negativo.
     * L'operazione non dovrebbe modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Invoca il
     * metodo {@code elementAt(-1)}. Verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane vuota.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void elementAtThrowsExceptionForNegativeIndex() {
        SubListAdapter subList = getSubList(3, 1, 1);

        subList.elementAt(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#elementAt(int)} lanci
     * un'eccezione quando invocato con un indice uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#elementAt(int)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando invocato con un indice uguale alla
     * dimensione della sottolista. L'operazione non dovrebbe modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di un elemento, instanziata da un vettore con più elementi.
     * Invoca il metodo {@code elementAt(1)}. Verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista con un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void elementAtThrowsExceptionForIndexGreaterThanSize() {
        SubListAdapter subList = getSubList(3, 0, 1);

        subList.elementAt(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#elementAt(int)} funzioni
     * correttamente.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#elementAt(int)} funzioni
     * correttamente. Questo viene fatto invocandolo in vari indici di una sottolista e verificando
     * che ritorni i valori corretti, senza modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di {@code 3} elementi. Invoca il metodo
     * {@link SubListAdapter#elementAt(int)} {@code 3} volte e verifica che gli elementi ritornati
     * siano corretti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invarita.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#elementAt(int)} ritorna gli elementi
     * corretti.
     * </p>
     */
    @Test
    public void elementAtOnVectorWithElements() {
        SubListAdapter subList = getSubList(5, 1, 4);

        assertEquals("Elemento2", subList.elementAt(0));
        assertEquals("Elemento3", subList.elementAt(1));
        assertEquals("Elemento4", subList.elementAt(2));
    }

    // ======== Object firstElement() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#firstElement()} lanci
     * un'eccezione quando la sottolista è vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#firstElement()} lanci
     * un'eccezione {@link NoSuchElementException} quando la sottolista è vuota.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, e invoca il
     * metodo {@link SubListAdapter#firstElement()}, verifica che venga lanciata l'eccezione
     * corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link NoSuchElementException}.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void firstElementThrowsNoSuchElementException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.firstElement();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#firstElement()} restituisca il
     * primo elemento di una sottolista non vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#firstElement()} restituisca
     * il primo elemento di una sottolista non vuota.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, aggiunge poi
     * {@code 3} elementi, verifica che {@link SubListAdapter#firstElement()} restituisca il primo
     * elemento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce correttamente il primo elemento della
     * sottolista.
     * </p>
     */
    @Test
    public void firstElementReturnsFirstElement() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add("A");
        subList.add("B");

        assertEquals("A", subList.firstElement());
    }

    // ======== Object lastElement() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#lastElement()} lanci un'eccezione
     * quando la sottolista è vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#lastElement()} lanci
     * un'eccezione {@link NoSuchElementException} quando la sottolista è vuota.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, e invoca il
     * metodo {@link SubListAdapter#lastElement()}, verifica che venga lanciata l'eccezione
     * corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link NoSuchElementException}.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void lastElementThrowsNoSuchElementException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.lastElement();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#lastElement()} restituisca
     * l'ultimo elemento di una sottolista non vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#lastElement()} restituisca
     * l'ultimo elemento di una sottolista non vuota.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, aggiunge poi
     * {@code 3} elementi, verifica che {@link SubListAdapter#lastElement()} restituisca l'ultimo
     * elemento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce correttamente l'ultimo elemento della
     * sottolista.
     * </p>
     */
    @Test
    public void lastElementReturnsFirstElement() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add("A");
        subList.add("B");

        assertEquals("B", subList.lastElement());
    }

    // ======== void setElementAt(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#setElementAt(Object, int)} lanci
     * un'eccezione quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#setElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, invoca
     * {@code setElementAt("A", -1)} e verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setElementAtThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);

        subList.setElementAt("A", -1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#setElementAt(Object, int)} lanci
     * un'eccezione quando l'indice fornito è maggiore o uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#setElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è maggiore
     * o uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota, instanziata da un vettore di dimensione
     * maggiore, invoca {@link SubListAdapter#setElementAt(Object, int)} con un indice uguale alla
     * dimensione della sottolista e verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setElementAtThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(5, 1, 3);

        subList.setElementAt("C", 2);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#setElementAt(Object, int)}
     * imposti correttamente il valore di un elemento a un indice valido.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#setElementAt(Object, int)}
     * imposti correttamente il valore di un elemento a un indice valido. Questo viene verificato
     * con una sottolista non vuota. Verifica inoltre che l'elemento venga modificato anche nel
     * vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota, instanziata da un vettore di dimensione
     * maggiore, invoca {@link SubListAdapter#setElementAt(Object, int)} su uno degli elementi e
     * verifica che il valore sia stato correttamente modificato all'indice specificato. Infine
     * verifica che l'elemento sia stato modificato anche nel vettore da cui è stata instanziata la
     * sottolista.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il valore dell'elemento all'indice specificato è stato aggiornato sia
     * nella sottolista che nel vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> Il valore all'indice specificato viene aggiornato con il nuovo
     * valore.
     * </p>
     */
    @Test
    public void setElementAtUpdatesValue() {
        SubListAdapter subList = getSubList(5, 1, 3);

        subList.setElementAt("X", 1);

        assertEquals("X", subList.get(1));
        assertEquals("X", vector.get(2));
    }

    // ======== void removeElementAt(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#removeElementAt(int index)} lanci
     * un'eccezione se l'indice è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeElementAt(int index)}
     * sollevi un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, poi chiama
     * {@code removeElementAt(-1)} e verifica che venga lanciata l'eccezioe
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'invocazione {@code removeElementAt(-1)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementAtThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.removeElementAt(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#removeElementAt(int index)} lanci
     * un'eccezione se l'indice è maggiore o uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeElementAt(int index)}
     * sollevi un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è
     * maggiore o uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di un elemento, instanziata da un vettore di dimensione
     * maggiore, poi chiama {@code removeElementAt(1)} e verifica che venga lanciata l'eccezioe
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene sollevata un'eccezione e la sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementAtThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(4, 1, 2);

        subList.removeElementAt(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#removeElementAt(int index)}
     * rimuova correttamente gli elementi da una lista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeElementAt(int index)}
     * rimuova correttamente gli elementi da una lista. In particolare da una lista di {@code 3}
     * elementi viene prima rimosso quello centrale, poi l'ultimo e infine il primo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di {@code 3} elementi. Rimuove poi prima quello
     * centrale ({@code removeElementAt(1)}), poi l'ultimo ({@code removeElementAt(1)}) e infine il
     * primo({@code removeElementAt(0)}). Lo stato viene verificato dopo ogni rimozione per
     * verificare che sia consistente con quanto atteso. Infine verifica che anche la dimensione del
     * vettore sia diminuita di 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista è vuota.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo ogni chiamata di {@code removeElementAt} la lista diminuisce la
     * sua dimensione di 1 e viene rimosso il corretto elemento.
     * </p>
     */
    @Test
    public void removeElementAtRemovesAllElements() {
        SubListAdapter subList = getSubList(5, 1, 4);

        subList.removeElementAt(1);

        assertEquals(2, subList.size());
        assertEquals("Elemento2", subList.get(0));
        assertEquals("Elemento4", subList.get(1));

        subList.removeElementAt(1);

        assertEquals(1, subList.size());
        assertEquals("Elemento2", subList.get(0));

        subList.removeElementAt(0);

        assertEquals(0, subList.size());
        assertEquals(2, vector.size());
    }

    // ======== void insertElementAt(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione se invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Invoca il
     * metodo {@link SubListAdapter#insertElementAt(Object, int)} utilizzando come indice
     * {@code -1}, verifica che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#insertElementAt(Object, int)} lancia
     * un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void insertElementAtThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.insertElementAt("A", -1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione se invocato con un indice maggiore della dimensione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * maggiore della dimensione.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Invoca il
     * metodo {@code insertElementAt("A", 5)}, verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#insertElementAt(Object, int)} lancia
     * un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void insertElementAtThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.insertElementAt("A", 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * aggiunga correttamente gli elementi su una sottolista.
     * </p>
     * <p>
     * <b>Description</b>Verifica che il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * aggiunga correttamente gli elementi su una sottolista. Viene verificato che il metodo riesca
     * ad aggiungere elementi all'inizio, alla fine e nel mezzo di una sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Aggiunge un
     * elemento nella sottolista ({@code "X"}), poi uno prima ({@code "A"}) ed uno dopo
     * ({@code "B"}) di esso. Verifica poi che la lunghezza del a sottolista sia 3 e che gli
     * elementi siano nell'ordine atteso ({@code ["A", "X", "B"]}).
     *
     * Infine verifica che anche la dimensione del vettore sia aumentata di 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista contiene 3 elementi nell'ordine atteso.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#insertElementAt(Object, int)}
     * inserisce correttamente gli elementi.
     * </p>
     */
    @Test
    public void insertElementAtAtIndexOnNonEmpty() {
        SubListAdapter subList = getSubList(2, 1, 1);
        subList.insertElementAt("X", 0);
        subList.insertElementAt("A", 0);
        subList.insertElementAt("B", 2);

        assertEquals(3, subList.size());
        assertEquals("A", subList.get(0));
        assertEquals("X", subList.get(1));
        assertEquals("B", subList.get(2));

        assertEquals(5, vector.size());
    }

    // ======== void addElement(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#addElement(Object)} aggiunga
     * correttamente elementi al termine della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#addElement(Object)} aggiunga
     * correttamente elementi al termine della sottolista. Questo viene fatto aggiungendo diversi
     * elementi e verificando che l'ultimo elemento della sottolista sia sempre quello aggiunto più
     * di recente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista vuota, mediante un ciclo di {@code 10} iterazioni
     * aggiunge elementi (in particolare aggiunge in ordine 1, 2, 3, ..., 10) e verifica che
     * l'ultimo elemento del vector sia lo stesso elemento appena aggiunto. Verifica inoltre che la
     * dimensione della sottolista aumenti di {@code 1} ad ogni invocazione. <br>
     * Verifica che l'ordine sia mantenuto, la sottolista finale deve contenere tutti i numeri da 1
     * a 10 in ordine crescente.
     *
     * Inoltre verifica che anche la dimensione del vettore aumenti di {@code 10}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista contiene {@code 10} elementi, i numeri da 1 a 10 in
     * ordine crescente.
     * </p>
     * <p>
     * <b>Expected Results</b> Ad ogni iterazione lo stato della sottolista è quello atteso.
     * </p>
     */
    @Test
    public void addElementAddsElementAtTheEndOfVector() {
        SubListAdapter subList = getSubList(3, 1, 1);
        for (int i = 1; i <= 10; i++) {
            subList.addElement(i);
            assertEquals(i, subList.lastElement());
            assertEquals(i, subList.size());
        }
        assertEquals(10, subList.size());
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, subList.get(i - 1));
        }
        assertEquals(13, vector.size());
    }

    // ======== boolean removeElement(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#removeElement(Object obj)}
     * rimuova solamente la prima occorrenza dell'oggetto specificato nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeElement(Object obj)}
     * rimuova solamente la prima occorrenza dell'oggetto specificato nella sottolista. Verifica che
     * non rimuova elementi nel vettore, anche se essi sono uguali all'argomento del metodo e sono
     * precedenti a quelli della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore ({@code ["A", "B", "A", "A"]}), Instanzia poi una
     * sottolista che non contiene il primo elemento del vettore ({@code ["B", "A", "A"]}). Invoca
     * {@code removeElement("A")} e verifica che la dimensione della sottolista sia ridotta e che
     * venga rimossa la prima occorrenza di {@code "A"} presente in sottolista, ma non quella
     * presente in vector.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore e una sottolista contenenti elementi duplicati.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore hanno una sola occorrenza in meno
     * dell'elemento specificato.
     * </p>
     * <p>
     * <b>Expected Results</b> La prima occorrenza dell'elemento viene rimossa e il metodo ritorna
     * {@code true}.
     * </p>
     */
    @Test
    public void removeElementFirstOccurrence() {
        vector.add("A");
        vector.add("B");
        vector.add("A");
        vector.add("A");

        SubListAdapter subList = (SubListAdapter) vector.subList(1, 4);

        assertTrue(subList.removeElement("A"));

        assertEquals(2, subList.size());
        assertEquals("B", subList.get(0));
        assertEquals("A", subList.get(1));

        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("A", vector.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il metodo {@link SubListAdapter#removeElement(Object obj)} quando
     * l'oggetto da rimuovere non è presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeElement(Object obj)}
     * ritorni {@code false} quando l'oggetto da rimuovere non è presente nella sottolista, anche se
     * esso è presente nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista non vuota, invoca il metodo
     * {@link SubListAdapter#removeElement(Object obj)} su un oggetto che sappiamo non essere nella
     * sottolista, ma che sappiamo essere nel vettore. Verifica che ila sottolista sia rimasto
     * invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore rimangono invariati e il metodo ritorna
     * {@code false}.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code false} e la sottolista e il vettore
     * rimangono invariati.
     * </p>
     */
    @Test
    public void removeElementNotPresent() {
        vector.add("X");
        vector.add("A");
        vector.add("B");

        SubListAdapter subList = (SubListAdapter) vector.subList(1, 3);

        assertFalse(subList.removeElement("X"));

        assertEquals(2, subList.size());
        assertEquals("A", subList.get(0));
        assertEquals("B", subList.get(1));

        assertEquals(3, vector.size());
        assertEquals("X", vector.get(0));
    }

    // ======== void removeAllElements() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#removeAllElements()} rimuova
     * correttamente gli elementi dalla sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeAllElements()} rimuova
     * correttamente gli elementi dalla sottolista. Per fare questa verifica viene utilizzato il
     * metodo {@link SubListAdapter#isEmpty()}.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di un elemento, instanziata da un vettore di dimensione
     * maggiore. Invoca il metodo {@code removeAllElements()}. Verifica che la sottolista sia vuota,
     * mentre il vettore ha un elemento in meno, ma non è vuoto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista con un elemento non vuota instanziata da un vettore di
     * dimensione maggiore di {@code 1}
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista è vuota, il vettore ha dimensione ridotta di {@code 1} e
     * non ha l'elemento inizialmente presente nella subList.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link SubListAdapter#removeAllElements()} la
     * sottolista è vuota, mentre il vettore no.
     * </p>
     */
    @Test
    public void removeAllElementsEmptiesVector() {
        SubListAdapter subList = getSubList(2, 1, 2);

        assertFalse(subList.isEmpty());

        subList.removeAllElements();

        assertTrue(subList.isEmpty());
        assertEquals(1, vector.size());
        assertEquals("Elemento1", vector.get(0));
    }

    // ======== Object clone() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#clone()} restituisca un nuovo
     * oggetto che contiene gli stessi elementi della sottolista originale.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#clone()} restituisca un nuovo
     * oggetto che contiene gli stessi elementi della sottolista originale, nello stesso ordine.
     * Questo test verifica che la clonazione di una sottolista non reintroduca elementi del
     * vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista non vuoto, lo clona e verifica che la copia sia della
     * stessa dimensione della sottolista originale e che contenga gli stessi elementi della
     * sottolista originale grazie al metodo {@link SubListAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La copia della sottolista contiene gli stessi elementi dell'originale.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#clone()} genera una copia della
     * sottolista.
     * </p>
     */
    @Test
    public void cloneClonesSubList() {
        SubListAdapter subList = getSubList(5, 1, 4);

        VectorAdapter clonedSubList = (VectorAdapter) subList.clone();

        assertEquals(subList.size(), clonedSubList.size());
        assertEquals(subList.get(0), clonedSubList.get(0));
        assertEquals(subList.get(1), clonedSubList.get(1));
        assertEquals(subList.get(2), clonedSubList.get(2));
    }

    // ======== Object[] toArray() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#toArray()} restituisca un array
     * contenente gli elementi presenti nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> VVerifica che il metodo {@link SubListAdapter#toArray()} restituisca un
     * array contenente gli elementi presenti nella sottolista, nell'ordine corretto.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di tre elementi, invoca il metodo
     * {@link SubListAdapter#toArray()} e verifica che l'array restituito contenga gli stessi
     * elementi nello stesso ordine.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'array restituito contiene esattamente gli stessi elementi della
     * sottolista, nello stesso ordine.
     * </p>
     */
    @Test
    public void toArrayMultipleElements() {
        SubListAdapter subList = getSubList(3, 1, 1);

        subList.add("A");
        subList.add("B");
        subList.add("C");

        Object[] array = subList.toArray();

        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }

    // ======== Object[] toArray(Object[]) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione uguale a quella della subList.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione uguale a quella della subList. Ci si
     * aspetta quindi che gli elementi coincidano indice per indice.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una subList, chiama {@link SubListAdapter#toArray(Object[])} passando
     * un array della stessa dimensione della subList, verifica che gli elementi siano stati copiati
     * nell'array fornito nell'ordine corretto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una subList contenente elementi di tipo {@link String}.
     * </p>
     * <p>
     * <b>Post-Condition</b> La subList rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'array fornito viene popolato correttamente con gli elementi della
     * subList e non viene allocato un nuovo array.
     * </p>
     */
    @Test
    public void toArrayMultipleElements2() {
        SubListAdapter subList = getSubList(3, 1, 1);

        subList.add("A");
        subList.add("B");
        String[] array = new String[2];

        Object[] resultArray = subList.toArray(array);

        assertSame(array, resultArray);
        assertEquals("A", resultArray[0]);
        assertEquals("B", resultArray[1]);
    }

    // ======== Object get(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#get(int)} lanci un'eccezione
     * quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#get(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando invocato con un indice negativo. L'operazione
     * non dovrebbe modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Invoca il
     * metodo {@code get(-1)}. Verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane vuota.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getThrowsExceptionForNegativeIndex() {
        SubListAdapter subList = getSubList(3, 1, 1);

        subList.get(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#get(int)} lanci un'eccezione
     * quando invocato con un indice uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#get(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando invocato con un indice uguale alla dimensione
     * della sottolista. L'operazione non dovrebbe modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di un elemento, instanziata da un vettore con più elementi.
     * Invoca il metodo {@code get(1)}. Verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista con un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getThrowsExceptionForIndexGreaterThanSize() {
        SubListAdapter subList = getSubList(3, 0, 1);

        subList.get(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#get(int)} funzioni correttamente.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#get(int)} funzioni
     * correttamente. Questo viene fatto invocandolo in vari indici di una sottolista e verificando
     * che ritorni i valori corretti, senza modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di {@code 3} elementi. Invoca il metodo
     * {@link SubListAdapter#get(int)} {@code 3} volte e verifica che gli elementi ritornati siano
     * corretti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invarita.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#get(int)} ritorna gli elementi
     * corretti.
     * </p>
     */
    @Test
    public void getOnVectorWithElements() {
        SubListAdapter subList = getSubList(5, 1, 4);

        assertEquals("Elemento2", subList.get(0));
        assertEquals("Elemento3", subList.get(1));
        assertEquals("Elemento4", subList.get(2));
    }

    // ======== Object set(int, Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#set(int, Object)} lanci
     * un'eccezione quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#set(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, invoca
     * {@code set(-1, "A")} e verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);

        subList.set(-1, "A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#set(int, Object)} lanci
     * un'eccezione quando l'indice fornito è maggiore o uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#set(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è maggiore o
     * uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota, instanziata da un vettore di dimensione
     * maggiore, invoca {@link SubListAdapter#set(int, Object)} con un indice uguale alla dimensione
     * della sottolista e verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(5, 1, 3);

        subList.set(2, "C");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#set(int, Object)} imposti
     * correttamente il valore di un elemento a un indice valido.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#set(int, Object)} imposti
     * correttamente il valore di un elemento a un indice valido. Questo viene verificato con una
     * sottolista non vuota. Verifica inoltre che l'elemento venga modificato anche nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota, instanziata da un vettore di dimensione
     * maggiore, invoca {@link SubListAdapter#set(int, Object)} su uno degli elementi e verifica che
     * il valore sia stato correttamente modificato all'indice specificato. Infine verifica che
     * l'elemento sia stato modificato anche nel vettore da cui è stata instanziata la sottolista.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il valore dell'elemento all'indice specificato è stato aggiornato sia
     * nella sottolista che nel vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> Il valore all'indice specificato viene aggiornato con il nuovo
     * valore.
     * </p>
     */
    @Test
    public void setUpdatesValue() {
        SubListAdapter subList = getSubList(5, 1, 3);

        subList.set(1, "X");

        assertEquals("X", subList.get(1));
        assertEquals("X", vector.get(2));
    }

    // ======== boolean add(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#add(Object)} aggiunga
     * correttamente elementi al termine della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#add(Object)} aggiunga
     * correttamente elementi al termine della sottolista. Questo viene fatto aggiungendo diversi
     * elementi e verificando che l'ultimo elemento della sottolista sia sempre quello aggiunto più
     * di recente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista vuota, mediante un ciclo di {@code 10} iterazioni
     * aggiunge elementi (in particolare aggiunge in ordine 1, 2, 3, ..., 10) e verifica che
     * l'ultimo elemento del vector sia lo stesso elemento appena aggiunto. Verifica inoltre che la
     * dimensione della sottolista aumenti di {@code 1} ad ogni invocazione. <br>
     * Verifica che l'ordine sia mantenuto, la sottolista finale deve contenere tutti i numeri da 1
     * a 10 in ordine crescente. <br>
     * Inoltre verifica che anche la dimensione del vettore aumenti di {@code 10}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista contiene {@code 10} elementi, i numeri da 1 a 10 in
     * ordine crescente.
     * </p>
     * <p>
     * <b>Expected Results</b> Ad ogni iterazione lo stato della sottolista è quello atteso.
     * </p>
     */
    @Test
    public void addAddsElementAtTheEndOfVector() {
        SubListAdapter subList = getSubList(3, 1, 1);
        for (int i = 1; i <= 10; i++) {
            subList.add(i);
            assertEquals(i, subList.lastElement());
            assertEquals(i, subList.size());
        }
        assertEquals(10, subList.size());
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, subList.get(i - 1));
        }
        assertEquals(13, vector.size());
    }

    // ======== boolean remove(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#remove(Object obj)} rimuova
     * solamente la prima occorrenza dell'oggetto specificato nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#remove(Object obj)} rimuova
     * solamente la prima occorrenza dell'oggetto specificato nella sottolista. Verifica che non
     * rimuova elementi nel vettore, anche se essi sono uguali all'argomento del metodo e sono
     * precedenti a quelli della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore ({@code ["A", "B", "A", "A"]}), Instanzia poi una
     * sottolista che non contiene il primo elemento del vettore ({@code ["B", "A", "A"]}). Invoca
     * {@code remove("A")} e verifica che la dimensione della sottolista sia ridotta e che venga
     * rimossa la prima occorrenza di {@code "A"} presente in sottolista, ma non quella presente in
     * vector.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore e una sottolista contenenti elementi duplicati.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore hanno una sola occorrenza in meno
     * dell'elemento specificato.
     * </p>
     * <p>
     * <b>Expected Results</b> La prima occorrenza dell'elemento viene rimossa e il metodo ritorna
     * {@code true}.
     * </p>
     */
    @Test
    public void removeFirstOccurrence() {
        vector.add("A");
        vector.add("B");
        vector.add("A");
        vector.add("A");

        SubListAdapter subList = (SubListAdapter) vector.subList(1, 4);

        assertTrue(subList.remove("A"));

        assertEquals(2, subList.size());
        assertEquals("B", subList.get(0));
        assertEquals("A", subList.get(1));

        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("A", vector.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il metodo {@link SubListAdapter#remove(Object obj)} quando l'oggetto
     * da rimuovere non è presente nella sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#remove(Object obj)} ritorni
     * {@code false} quando l'oggetto da rimuovere non è presente nella sottolista, anche se esso è
     * presente nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista non vuota, invoca il metodo
     * {@link SubListAdapter#remove(Object obj)} su un oggetto che sappiamo non essere nella
     * sottolista, ma che sappiamo essere nel vettore. Verifica che ila sottolista sia rimasto
     * invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore rimangono invariati e il metodo ritorna
     * {@code false}.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code false} e la sottolista e il vettore
     * rimangono invariati.
     * </p>
     */
    @Test
    public void removeNotPresent() {
        vector.add("X");
        vector.add("A");
        vector.add("B");

        SubListAdapter subList = (SubListAdapter) vector.subList(1, 3);

        assertFalse(subList.remove("X"));

        assertEquals(2, subList.size());
        assertEquals("A", subList.get(0));
        assertEquals("B", subList.get(1));

        assertEquals(3, vector.size());
        assertEquals("X", vector.get(0));
    }

    // ======== void add(int, Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#add(int, Object)} lanci
     * un'eccezione se invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#add(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Invoca il
     * metodo {@link SubListAdapter#add(int, Object)} utilizzando come indice {@code -1}, verifica
     * che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#add(int, Object)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAtThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add(-1, "A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#add(int, Object)} lanci
     * un'eccezione se invocato con un indice maggiore della dimensione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#add(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * maggiore della dimensione.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Invoca il
     * metodo {@code add(5, "A")}, verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#add(int, Object)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAtThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.add(1, "A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#add(int, Object)} aggiunga
     * correttamente gli elementi su una sottolista.
     * </p>
     * <p>
     * <b>Description</b>Verifica che il metodo {@link SubListAdapter#add(int, Object)} aggiunga
     * correttamente gli elementi su una sottolista. Viene verificato che il metodo riesca ad
     * aggiungere elementi all'inizio, alla fine e nel mezzo di una sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto. Aggiunge un
     * elemento nella sottolista ({@code "X"}), poi uno prima ({@code "A"}) ed uno dopo
     * ({@code "B"}) di esso. Verifica poi che la lunghezza del a sottolista sia 3 e che gli
     * elementi siano nell'ordine atteso ({@code ["A", "X", "B"]}).
     *
     * Infine verifica che anche la dimensione del vettore sia aumentata di 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista contiene 3 elementi nell'ordine atteso.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#add(int, Object)} inserisce
     * correttamente gli elementi.
     * </p>
     */
    @Test
    public void addAtIndexOnNonEmpty() {
        SubListAdapter subList = getSubList(2, 1, 1);
        subList.add(0, "X");
        subList.add(0, "A");
        subList.add(2, "B");

        assertEquals(3, subList.size());
        assertEquals("A", subList.get(0));
        assertEquals("X", subList.get(1));
        assertEquals("B", subList.get(2));

        assertEquals(5, vector.size());
    }

    // ======== Object remove(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#remove(int)} lanci un'eccezione
     * se l'indice è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#remove(int)} sollevi
     * un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista vuota, instanziata da un vettore non vuoto, poi chiama
     * {@code remove(-1)} e verifica che venga lanciata l'eccezioe
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'invocazione {@code remove(-1)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(3, 1, 1);
        subList.remove(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#remove(int)} lanci un'eccezione
     * se l'indice è maggiore o uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#remove(int)} sollevi
     * un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è maggiore o
     * uguale alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di un elemento, instanziata da un vettore di dimensione
     * maggiore, poi chiama {@code remove(1)} e verifica che venga lanciata l'eccezioe
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene sollevata un'eccezione e la sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(4, 1, 2);

        subList.remove(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#remove(int)} rimuova
     * correttamente gli elementi da una lista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#remove(int)} rimuova
     * correttamente gli elementi da una lista. In particolare da una lista di {@code 3} elementi
     * viene prima rimosso quello centrale, poi l'ultimo e infine il primo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di {@code 3} elementi. Rimuove poi prima quello
     * centrale ({@code remove(1)}), poi l'ultimo ({@code remove(1)}) e infine il
     * primo({@code remove(0)}). Lo stato viene verificato dopo ogni rimozione per verificare che
     * sia consistente con quanto atteso. Infine verifica che anche la dimensione del vettore sia
     * diminuita di 3.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista è vuota.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo ogni chiamata di {@code remove} la lista diminuisce la sua
     * dimensione di 1 e viene rimosso il corretto elemento.
     * </p>
     */
    @Test
    public void removeRemovesAllElements() {
        SubListAdapter subList = getSubList(5, 1, 4);

        subList.remove(1);

        assertEquals(2, subList.size());
        assertEquals("Elemento2", subList.get(0));
        assertEquals("Elemento4", subList.get(1));

        subList.remove(1);

        assertEquals(1, subList.size());
        assertEquals("Elemento2", subList.get(0));

        subList.remove(0);

        assertEquals(0, subList.size());
        assertEquals(2, vector.size());
    }

    // ======== void clear() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#clear()} rimuova correttamente
     * gli elementi dalla sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#clear()} rimuova
     * correttamente gli elementi dalla sottolista. Per fare questa verifica viene utilizzato il
     * metodo {@link SubListAdapter#isEmpty()}.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista di un elemento, instanziata da un vettore di dimensione
     * maggiore. Invoca il metodo {@code clear()}. Verifica che la sottolista sia vuota, mentre il
     * vettore ha un elemento in meno, ma non è vuoto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista con un elemento non vuota instanziata da un vettore di
     * dimensione maggiore di {@code 1}
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista è vuota, il vettore ha dimensione ridotta di {@code 1} e
     * non ha l'elemento inizialmente presente nella subList.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link SubListAdapter#clear()} la sottolista è
     * vuota, mentre il vettore no.
     * </p>
     */
    @Test
    public void clearEmptiesVector() {
        SubListAdapter subList = getSubList(2, 1, 2);

        assertFalse(subList.isEmpty());

        subList.clear();

        assertTrue(subList.isEmpty());
        assertEquals(1, vector.size());
        assertEquals("Elemento1", vector.get(0));
    }

    // ======== boolean containsAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#containsAll(HCollection)}
     * restituisca {@code false} quando la sottolista non contiene tutti gli elementi della
     * collezione specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#containsAll(HCollection)}
     * restituisca {@code false} quando la sottolista non contiene tutti gli elementi della
     * collezione specificata indipendentemente dall'ordine degli elementi. Questo ci aspettiamo
     * avvenga anche se nel vettore questi elementi sono presenti.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto contenente l'elemento "X" dal quale istanzia una
     * sottolista non contenente l'elemento "X". Crea poi una collezione contenente l'elemento "X".
     * Verifica che il metodo {@code sottolista.containsAll(HCollection)} restituisca {@code false},
     * anceh se {@code vector.containsAll(HCollection)} restituisce {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente l'elemento "X", una sottolista non
     * contenente l'elemento "X" e una collezione contenente l'elemento "X" .
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore, la sottolista e la collezione rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code false} se invocato sulla sottolista, ma
     * {@code true} se invocato sul vettore.
     * </p>
     */
    @Test
    public void containsAllElementsNotPresent() {
        vector.add("A");
        vector.add("B");
        vector.add("X");

        SubListAdapter subList = (SubListAdapter) vector.subList(0, 2);

        HCollection collection = new VectorAdapter();
        collection.add("A");
        collection.add("X");

        assertFalse(subList.containsAll(collection));
        assertTrue(vector.containsAll(collection));
    }

    // ======== boolean addAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#addAll(HCollection)} aggiunga
     * correttamente tutti gli elementi dalla collezione specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#addAll(HCollection)} aggiunga
     * correttamente tutti gli elementi dalla collezione specificata. Verifica che tutti gli
     * elementi della collezione siano aggiunti correttamente alla fine della sottolista e verifica
     * che gli elementi siano aggiunti anche al vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota e una collezione separata. Invoca
     * {@link SubListAdapter#addAll(HCollection)} e verifica che tutti gli elementi della collezione
     * siano presenti alla fine della sottolista e che siano presenti nel vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un sottolista non vuota e una collezione non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista contengono tutti gli elementi iniziali più
     * quelli della collezione aggiunta.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true} e tutti gli elementi della
     * collezione vengono aggiunti alla fine della sottolista e nel vettore.
     * </p>
     */
    @Test
    public void addAllonNonEmptyVector() {
        SubListAdapter sottolista = getSubList(4, 1, 3);

        HCollection collection = new VectorAdapter();
        collection.add("A");
        collection.add("B");

        assertTrue(sottolista.addAll(collection));
        assertEquals(4, sottolista.size());
        assertEquals("Elemento2", sottolista.get(0));
        assertEquals("Elemento3", sottolista.get(1));
        assertEquals("A", sottolista.get(2));
        assertEquals("B", sottolista.get(3));

        assertTrue(vector.contains("A"));
        assertTrue(vector.contains("B"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il comportamento del metodo
     * {@link SubListAdapter#addAll(HCollection)} quando l'argomento è la sottolista stessa.
     * </p>
     * <p>
     * <b>Description</b> Verifica il comportamento del metodo
     * {@link SubListAdapter#addAll(HCollection)} quando l'argomento è la sottolista stesso. Ci ai
     * aspetta gli elementi della sottolista risultino duplicati.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota ed invoca {@code addAll(vector)} su sé stesso,
     * verifica che non ci siano eccezioni e che gli elementi della sottolista risultino siano
     * duplicati.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il contenuto della sottolista viene duplicato.
     * </p>
     * <p>
     * <b>Expected Results</b> Nessuna eccezione viene lanciata e il contenuto della sottolista
     * viene duplicato.
     * </p>
     */
    @Test
    public void addAllOnSelf() {
        SubListAdapter sottolista = getSubList(4, 1, 3);

        assertTrue(sottolista.addAll(sottolista));
        assertEquals(4, sottolista.size());
        assertEquals("Elemento2", sottolista.get(0));
        assertEquals("Elemento3", sottolista.get(1));
        assertEquals("Elemento2", sottolista.get(2));
        assertEquals("Elemento3", sottolista.get(3));
    }

    // ======== boolean removeAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#removeAll(HCollection)} funzioni
     * correttamente sulla sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#removeAll(HCollection)}
     * funzioni correttamente sulla sottolista. Ovvero che nessun elemento del vettore e della
     * sottolista vegna modificato quando non ci sono elementi in comune tra sottolista e
     * collezione, anche se ci sono elementi in comune tra vettore e collezione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista non vuota. Crea
     * poi una collezione che condivide alcuni elementi con il vettore, ma non con la sottolista.
     * Invoca {@link SubListAdapter#removeAll(HCollection)} e verifica che il vettore rimanga
     * invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore e una sottolista non vuoti e una collezione non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code false} e il vettore rimane invariato.
     * </p>
     */
    @Test
    public void removeAllReturnsFalse() {
        vector.add("A");
        vector.add("B");
        vector.add("X");

        SubListAdapter subList = (SubListAdapter) vector.subList(0, 2);

        HCollection collection = new VectorAdapter();
        collection.add("X");

        assertFalse(subList.removeAll(collection));
        assertEquals(2, subList.size());
        assertEquals("A", subList.get(0));
        assertEquals("B", subList.get(1));
        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("X", vector.get(2));
    }

    // ======== boolean retainAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#retainAll(HCollection)} si
     * comporti correttamente quando invocato utilizzando come parametro una collezione vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#retainAll(HCollection)} si
     * comporti correttamente quando invocato utilizzando come parametro una collezione vuota. In
     * particolare ci si aspetta che il metodo si comporti esattamente come
     * {@link SubListAdapter#clear()}. Ci si aspetta quindi che vengano rimossi tutti gli elementi
     * che fanno parte della sottolista, senza intaccare quelli del vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista non vuota. Invoca
     * il metodo {@link SubListAdapter#retainAll(HCollection)} utilizzando una collezione vuota come
     * parametro. Verifica che la sottolista venga svuotata di tutti i suoi elementi, mentre il
     * vettore mantiene solo gli elementi che non fanno parte della sottolista. Verifica inoltre che
     * il metodo ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista è vuota, il vettore mantiene solo gli elementi non
     * presenti nella sottolista.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#retainAll(HCollection)} ritorna
     * {@code true}
     * </p>
     */
    @Test
    public void retainAllRemovesAll() {
        SubListAdapter sottolista = getSubList(6, 1, 5);

        assertTrue(sottolista.retainAll(new VectorAdapter()));

        assertTrue(sottolista.isEmpty());
        assertEquals(2, vector.size());
        assertEquals("Elemento1", vector.get(0));
        assertEquals("Elemento6", vector.get(1));
    }

    // ======== boolean addAll(int, HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#addAll(int, HCollection)} lanci
     * un'eccezione se l'indice è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#addAll(int, HCollection)}
     * sollevi un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista vuota, poi chiama {@code addAll(-1, Collection)} e
     * verifica che venga lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> la sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> L'invocazione {@code addAll(-1, Collection)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAllAtThrowsArrayIndexOutOfBoundsException() {
        SubListAdapter sottolista = getSubList(2, 1, 1);
        sottolista.addAll(-1, vector);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#addAll(int, HCollection)} lanci
     * un'eccezione se l'indice è maggiore alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#addAll(int, HCollection)}
     * sollevi un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è
     * maggiore alla dimensione della sottolista.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di un elemento, poi chiama
     * {@code addAll(2, Collection)} e verifica che venga lanciata l'eccezioe
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene sollevata un'eccezione e la sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAllAtThrowsArrayIndexOutOfBoundsException2() {
        SubListAdapter sottolista = getSubList(3, 1, 2);

        sottolista.addAll(2, sottolista);
    }

    // ======== boolean equals(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#equals(Object)} restituisca
     * {@code true} quando una sottolista ed un vettore contengono gli stessi elementi nello stesso
     * ordine.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#equals(Object)} restituisca
     * {@code true} quando una sottolista ed un vettore contengono gli stessi elementi nello stesso
     * ordine. Questo viene verificato con una sottolista ed un vettore diversi, anche se contenenti
     * gli stessi elementi (alcuni dei quali sono l'elemento {@code null}).
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista ed un vettore con gli stessi elementi. Verifica che
     * {@code equals()} restituisca {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista ed un vettore con gli stessi elementi nello stesso
     * ordine.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true}.
     * </p>
     */
    @Test
    public void equalsReturnsTrue() {
        SubListAdapter sottolista = getSubList(2, 1, 1);
        sottolista.add("A");
        sottolista.add(null);
        sottolista.add("C");

        VectorAdapter anotherVector = new VectorAdapter();
        anotherVector.add("A");
        anotherVector.add(null);
        anotherVector.add("C");

        assertTrue(sottolista.equals(anotherVector));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#equals(Object)} restituisca
     * {@code true} quando la sottolist viene confrontata con sé stessa.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#equals(Object)} restituisca
     * {@code true} quando la sottolist viene confrontata con sé stessa. Come specificato dalla
     * documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota, chiama {@code equals()} con il riferimento alla
     * stessa sottolista. Verifica che {@code equals()} restituisca {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true}.
     * </p>
     */
    @Test
    public void testEqualsWithSelf() {
        SubListAdapter sottolista = getSubList(4, 1, 3);

        assertTrue(sottolista.equals(sottolista));
    }

    // ======== int hashCode() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#hashCode()} ritorni codici hash
     * diversi per il vettore e la sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#hashCode()} ritorni codici
     * hash diversi per il vettore e la sottolista quando essi hanno contenuti diversi.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista non vuota e non
     * uguale al vettore. Verifica che i codici hash siano diversi.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto dal quale instanzia una sottolista non vuota e non
     * uguale al vettore.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> I codici hash sono diversi.
     * </p>
     */
    @Test
    public void hashCodeOnSubList() {
        SubListAdapter subList = getSubList(4, 1, 3);

        assertNotEquals(vector.hashCode(), subList.hashCode());
    }

    // ======== String toString() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#toString()} restituisca la
     * stringa corretta per una sottolista non vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#toString()} restituisca la
     * stringa corretta per una sottolista non vuota. Ovvero una rappresentazione che mostri tutti
     * gli elementi della sottolista separati da una virgola.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota e verifica che la rappresentazione sia quella
     * attesa.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#toString()} restituisce la stringa
     * attesa.
     * </p>
     */
    @Test
    public void toStringOnSubList() {
        SubListAdapter subList = getSubList(2, 1, 1);
        subList.add("A");
        subList.add("B");
        subList.add("C");
        assertEquals("[A, B, C]", subList.toString());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#toString()} gestisca
     * correttamente i riferimenti ciclici a se stesso.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#toString()} gestisca
     * correttamente i riferimenti ciclici a se stesso. Ci si aspetta che la sottolista venga
     * rappresentata come "(this Collection)" così da evitare cicli infiniti.
     * </p>
     * <p>
     * <b>Design</b> Crea una sottolista non vuota che contiene sé stesso tra i suoi elemeneti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota che contiene sé stesso come elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificata.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link SubListAdapter#toString()} restituisce la stringa
     * attesa e non genera cicli infiniti.
     * </p>
     */
    @Test
    public void testToStringWithCyclicReference() {
        SubListAdapter subList = getSubList(2, 1, 1);
        subList.add("A");
        subList.add(subList);
        subList.add("C");
        assertEquals("[A, (this Collection), C]", subList.toString());
    }

    // ======== HList subList(int, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link SubListAdapter#subList(int, int)} lanci un'eccezione
     * quando {@code fromIndex < 0}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link SubListAdapter#subList(int, int)} lanci un'eccezione
     * {@link IndexOutOfBoundsException} quando {@code fromIndex < 0}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di due elementi. Invoca il metodo
     * {@code subList(-1, 1)}. Verifica che l'eccezione {@link IndexOutOfBoundsException} venga
     * lanciata
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di due elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificata.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link SubListAdapter#subList(int, int)} viene
     * lanciata un'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsIndexOutOfBoundsException() {
        SubListAdapter subList = getSubList(4, 1, 3);

        subList.subList(-1, 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link SubListAdapter#subList(int, int)} lanci un'eccezione
     * quando {@code toIndex > size}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link SubListAdapter#subList(int, int)} lanci un'eccezione
     * {@link IndexOutOfBoundsException} quando {@code toIndex > size}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di due elementi. Invoca il metodo
     * {@code subList(0, 3)}. Verifica che l'eccezione {@link IndexOutOfBoundsException} venga
     * lanciata
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di due elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista non viene modificata.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link SubListAdapter#subList(int, int)} viene
     * lanciata un'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsIndexOutOfBoundsException2() {
        SubListAdapter subList = getSubList(4, 1, 3);

        subList.subList(0, 3);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link SubListAdapter#subList(int, int)} ritorni la corretta
     * vista della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link SubListAdapter#subList(int, int)} ritorni la corretta
     * vista della sottolista. Ovvero tra {@code fromIndex}, inclusivo, e {@code toIndex},
     * esclusivo.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettor di {@code 6} elementi dal quale instanzia una sottolista di
     * {@code 4} elementi. Invoca il metodo {@code subList(1, 3)}. Verifica che la sottolista della
     * sottolista abbia dimensione {@code 2} e che contenga gli elementi che hanno indice 1 e 2
     * nella sottolista.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 6} elementi ed una sottolista di {@code 4}
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista non vengono modificati.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link SubListAdapter#subList(int, int)} la
     * sottolista della sottolista ha dimensione 2 e che contiene gli elementi che hanno indice 1 e
     * 2 nella sottolista.
     * </p>
     */
    @Test
    public void subListBetweenRange() {
        SubListAdapter subList = getSubList(6, 1, 5);

        HList newSubList = subList.subList(1, 3);
        assertEquals(2, newSubList.size());
        assertEquals(subList.get(1), newSubList.get(0));
        assertEquals(subList.get(2), newSubList.get(1));
        assertEquals(vector.get(2), newSubList.get(0));
        assertEquals(vector.get(3), newSubList.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link SubListAdapter#subList(int, int)} ritorni la corretta
     * vista della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link SubListAdapter#subList(int, int)} ritorni la corretta
     * vista della sottolista. In questo caso la vista corretta è l'intero vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia una sottolista di {@code 3} elementi da un vettore di dimensioni
     * maggiori. Invoca il metodo {@code subList(0, 3)}. Verifica che la sottolista della sottolista
     * abbia dimensione 3 e che contenga gli stessi elementi nello stesso ordine della sottolista
     * iniziale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista iniziale non vengono modificati.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link SubListAdapter#subList(int, int)} la
     * sottolista della sottolista ha dimensione 3 e che contiene gli stessi elementi nello stesso
     * ordine della sottolista iniziale.
     * </p>
     */
    @Test
    public void subListFullRange() {
        SubListAdapter subList = getSubList(6, 1, 4);

        HList newSubList = subList.subList(0, 3);

        assertEquals(3, newSubList.size());
        assertEquals(subList.get(0), newSubList.get(0));
        assertEquals(subList.get(1), newSubList.get(1));
        assertEquals(subList.get(2), newSubList.get(2));
    }

    // ======== HListIterator listIterator(int) ========
    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#listIterator(int)} lanci
     * un'eccezione quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#listIterator(int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} quando invocato con un indice negativo.
     * L'operazione non dovrebbe modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista vuota. Invoca il
     * metodo {@code listIterator(-1)}. Verifica che venga lanciata un'eccezione
     * {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una sottolista vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link IndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorThrowsIndexOutOfBoundsException() {
        SubListAdapter sottolista = getSubList(3, 1, 1);
        sottolista.listIterator(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link SubListAdapter#listIterator(int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} quando invocato con un indice uguale alla
     * dimensione della sottolista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link SubListAdapter#listIterator(int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} quando invocato con un indice uguale alla
     * dimensione della sottolista. L'operazione non dovrebbe modificare la sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista di un elemento.
     * Invoca il metodo {@code listIterator(2)}. Verifica che venga lanciata un'eccezione
     * {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una sottolista di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link IndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void listItreatorThrowsIndexOutOfBoundsException() {
        SubListAdapter sottolista = getSubList(3, 1, 2);

        sottolista.listIterator(2);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link SubListAdapter#listIterator(int)} ritorni un
     * oggetto di tipo {@link HListIterator} che inizia dall'indice corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link SubListAdapter#listIterator(int)} ritorni un
     * oggetto di tipo {@link HListIterator} che inizia dall'indice corretto. Test estensivi
     * dell'implementazione di {@code ListIteratorAdapter} sono svolti nella test suite
     * {@code ListIteratorAdapterTester}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista di {@code 3}
     * elementi. Instanzia un iteratore grazie al metodo {@link SubListAdapter#listIterator(int)}
     * con un indice valido e verifica che l'iteratore inizi dall'indice corretto invocando
     * {@link ListIteratorAdapter#next()} e {@link ListIteratorAdapter#hasNext()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una sottolista di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link SubListAdapter#listIterator(int)} ritorna gli elementi del
     * vettore a partire dall'indice specificato.
     * </p>
     */
    @Test
    public void listIteratorReturnsCorrectView() {
        SubListAdapter sottolista = getSubList(5, 1, 4);

        HListIterator iterator = sottolista.listIterator(1);

        assertEquals("Elemento3", iterator.next());
        assertEquals("Elemento4", iterator.next());
        assertFalse(iterator.hasNext());
    }

    // ======== boolean IteratorAdapter.hasNext() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni {@code false}
     * se non ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#hasNext()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione. Questo viene verificato su
     * un'iteratore su una sottolista vuota, instanziata da un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto sul quale instanzia una sottolista vuota. Su di essa
     * instanzia un'iterazione grazie al metodo {@link SubListAdapter#iterator()}. Chiama poi il
     * metodo {@link IteratorAdapter#hasNext()} e verifica che ritorni {@code false}
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista è instanziata e vuota e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e la sottolista rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link IteratorAdapter#hasNext()} ritorna {@code false}
     * </p>
     */
    @Test
    public void hasNextOnEmpty() {
        SubListAdapter sottolista = getSubList(3, 1, 1);

        HIterator it = sottolista.iterator();

        assertFalse(it.hasNext());
    }

    // ======== Object IteratorAdapter.next() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#next()} lanci un'eccezione se
     * non ci sono ulteriori elementi nell'iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#next()} lanci un'eccezione
     * se non ci sono ulteriori elementi nell'iterazione. Questo viene verificato su un'iteratore su
     * una sottolista vuota, instanziata da un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto sul quale instanzia una sottolista vuota. Su di essa
     * instanzia un'iteratore grazie al metodo {@link SubListAdapter#iterator()}. Chiama poi il
     * metodo {@link IteratorAdapter#next()} e verifica che venga lanciata l'eccezione
     * {@link NoSuchElementException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista è instanziata e vuota e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e il sottolista rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link IteratorAdapter#next()} lancia l'eccezione
     * {@link NoSuchElementException}
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void nextThrowsNoSuchElementException() {
        SubListAdapter sottolista = getSubList(3, 1, 1);

        HIterator it = sottolista.iterator();

        it.next();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#next()} ritorni tutti gli
     * elementi nella sottolista nell'ordine corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#next()} ritorni tutti gli
     * elementi nella sottolista nell'ordine corretto. Questo viene verificato eseguendo in coppia
     * {@link IteratorAdapter#next()} e {@link SubListAdapter#get(int)}. Questo test verifica che
     * solamente gli elementi presenti nella sottolista vengano mostrati e che vengano ignorati
     * quelli presenti solo nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto sul quale instanzia una sottolista di dimensioni
     * minori, ma non vuota. Su di essa instanzia un'iteratore grazie al metodo
     * {@link SubListAdapter#iterator()}. Viene poi eseguita un'iterazione finchè
     * {@link IteratorAdapter#hasNext()} è vera e ci si aspetta che {@code next()} sia uguale a
     * {@code get(i)}. Dove {@code i} viene incrementato di {@code 1} ad ogni iterazione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista è instanziata e non vuota e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code next()} e {@code get(i)} ritornano gli stessi valori
     * </p>
     */
    @Test
    public void nextReturnsCorrectElements() {
        SubListAdapter sottolista = getSubList(6, 1, 5);

        HIterator it = sottolista.iterator();

        int i = 0;
        while (it.hasNext()) {
            assertEquals(sottolista.get(i), it.next());
            i++;
        }
    }

    // ======== void IteratorAdapter.remove() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link IteratorAdapter#remove()} rimuova correttamente
     * l'ultimo elemento ritornato sia dalla sottolista che dal vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link IteratorAdapter#remove()} rimuova
     * correttamente l'ultimo elemento ritornato sia dalla sottolista che dal vettore. Questo viene
     * verificato su una sottolista di {@code 3} elementi dove vengono rimossi tutti e {@code 3} gli
     * elementi partendo da quello con indice più basso
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore di più di {@code 3} elementi sul quale instanzia una sottlista
     * di {@code 3} elementi. Su di essa instanzia un'iteratore grazie al metodo
     * {@link SubListAdapter#iterator()}. Viene poi vengono invocati per {@code 3} volte
     * {@link IteratorAdapter#next()} e {@link IteratorAdapter#remove()} ad ogni invocazione dei
     * metodi vengono verificati gli elementi presenti nella sottolista e nel vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato di più di {@code 3} elementi, la sottolista è
     * di lunghezza {@code 3} e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista è vuota, l'iteratore non ha altri elementi e il vettore
     * ha {@code 3} elementi in meno
     * </p>
     * <p>
     * <b>Expected Results</b> Dal vettore vengono rimossi gli elementi a partire da quello con
     * l'indice più basso e la dimensione del vettore si riduce correttamente.
     * </p>
     */
    @Test
    public void removeRemovesElementFromVector() {
        SubListAdapter sottolista = getSubList(5, 1, 4);

        HIterator it = sottolista.iterator();

        it.next();
        it.remove();

        assertEquals("Elemento3", sottolista.get(0));
        assertEquals("Elemento4", sottolista.get(1));
        assertEquals(2, sottolista.size());
        assertEquals(4, vector.size());

        it.next();
        it.remove();

        assertEquals("Elemento4", sottolista.get(0));
        assertEquals(1, sottolista.size());
        assertEquals(3, vector.size());

        it.next();
        it.remove();

        assertEquals(0, sottolista.size());
        assertFalse(it.hasNext());
        assertEquals(2, vector.size());
        assertEquals("Elemento1", vector.get(0));
        assertEquals("Elemento5", vector.get(1));
    }

    // ======== boolean ListIteratorAdapter.hasPrevious() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#hasPrevious()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#hasPrevious()} ritorni
     * {@code false} se non ci sono altri elementi di iterazione. Questo viene verificato su
     * un'iteratore su una sottolista vuota, instanziata da un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto sul quale instanzia una sottolista vuota. Su di essa
     * instanzia un'iterazione grazie al metodo {@link SubListAdapter#listIterator()}. Chiama poi il
     * metodo {@link ListIteratorAdapter#hasPrevious()} e verifica che ritorni {@code false}
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista è instanziata e vuota e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e la sottolista rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#hasPrevious()} ritorna {@code false}
     * </p>
     */
    @Test
    public void hasPreviousOnEmpty() {
        SubListAdapter sottolista = getSubList(3, 1, 1);

        HListIterator it = sottolista.listIterator();

        assertFalse(it.hasPrevious());
    }

    // ======== Object ListIteratorAdapter.previous() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'iterazione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} lanci
     * un'eccezione se non ci sono ulteriori elementi nell'iterazione. Questo viene verificato su
     * un'iteratore su una sottolista vuota creata da un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto sul quale instanzia una sottolista vuota. Su di essa
     * instanzia un'iteratore grazie al metodo {@link SubListAdapter#listIterator()}. Chiama poi il
     * metodo {@link ListIteratorAdapter#previous()} e verifica che venga lanciata l'eccezione
     * {@link NoSuchElementException}
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista è instanziata e vuota e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#previous()} lancia l'eccezione
     * {@link NoSuchElementException}
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void previousThrowsNoSuchElementException() {
        SubListAdapter sottolista = getSubList(3, 1, 1);

        HListIterator it = sottolista.listIterator();

        it.previous();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} ritorni tutti
     * gli elementi della sottolista nell'ordine corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previous()} ritorni
     * tutti gli elementi della sottolista nell'ordine corretto. Questo viene verificato eseguendo
     * in coppia {@link ListIteratorAdapter#previous()} e {@link SubListAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto sul quale instanzia una sottolista non vuota. Su di
     * essa instanzia un'iteratore che inizia all'ultimo indice grazie al metodo
     * {@code listIterator(size())}. Viene poi eseguita un'iterazione finchè
     * {@link ListIteratorAdapter#hasPrevious()} è vera e ci si aspetta che {@code previous()} sia
     * uguale a {@code get(i)}. Dove {@code i} viene decrementato di {@code 1} ad ogni iterazione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> La sottolista è instanziato e non vuota e l'iteratore è instanziato.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'iteratore e la sottolista rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previous()} e {@code get(i)} ritornano gli stessi valori
     * </p>
     */
    @Test
    public void previousReturnsCorrectElements() {
        SubListAdapter sottolista = getSubList(6, 1, 4);

        HListIterator it = sottolista.listIterator(sottolista.size());

        int i = sottolista.size() - 1;
        while (it.hasPrevious()) {
            assertEquals(sottolista.get(i), it.previous());
            i--;
        }
    }

    // ======== int ListIteratorAdapter.nextIndex() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * {@code 0} quando viene chiamato su un iteratore di una sottolista vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#nextIndex()} restituisca
     * {@code 0} quando viene chiamato su un iteratore di una sottolista vuota. Ovvero dovrebbe
     * ritornare la dimensione della sottolista in quanto {@code hasNext()} ritornerebbe
     * {@code false}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale istanzia una sottolista vuota. Crea un
     * iteratore grazie ad {@link SubListAdapter#listIterator()}. Verifica che {@code nextIndex()}
     * restituisca {@code 0}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuota e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e l'iteratore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#nextIndex()} restituisce {@code 0}.
     * </p>
     */
    @Test
    public void nextIndexOnEmptyList() {
        SubListAdapter sottolista = getSubList(2, 1, 1);
        HListIterator iterator = sottolista.listIterator();

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
     * <b>Design</b> Crea un vettore non vuoto dal quale istanzia una sottolista non vuota.
     * Instanzia un iteratore grazie ad {@link SubListAdapter#listIterator()}. Invoca ricorsivamente
     * {@code nextIndex()} e verifica che l'indice restituito sia quello dell'elemento che verrà
     * successivamente ritornato da {@code next()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista rimane invariato, L'iteratore si trova alla fine della
     * sottolista.
     * </p>
     * <p>
     * <b>Expected Results</b> {@code nextIndex()} restituisce l'indice dell'elemento che verrà poi
     * restituito da {@code next()}.
     * </p>
     */
    @Test
    public void nextIndexWithNext() {
        SubListAdapter sottolista = getSubList(6, 1, 5);

        HListIterator iterator = sottolista.listIterator();

        while (iterator.hasNext()) {
            assertEquals(sottolista.get(iterator.nextIndex()), iterator.next());
        }
    }

    // ======== int ListIteratorAdapter.previousIndex() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()} restituisca
     * {@code -1} quando viene chiamato su un iteratore di una sottolista vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#previousIndex()}
     * restituisca {@code -1} quando viene chiamato su un iteratore di una sottolista vuota. Ovvero
     * quanto definito dalla documentazione nel caso in cui ci si trovi in testa alla sottolista.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale istanzia una sottolista non vuota. Crea un
     * iteratore grazie ad {@link SubListAdapter#listIterator()}. Verifica che
     * {@code previousIndex()} restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista vuoto e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e l'iteratore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link ListIteratorAdapter#previousIndex()} restituisce {@code -1}.
     * </p>
     */
    @Test
    public void previousIndexOnEmptyList() {
        SubListAdapter sottolista = getSubList(2, 1, 1);
        HListIterator iterator = sottolista.listIterator();

        assertEquals(-1, iterator.previousIndex());
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
     * <b>Design</b> Crea un vettore non vuoto dal quale istanzia una sottolista non vuota.
     * Instanzia un iteratore grazie ad {@link SubListAdapter#listIterator()}. Invoca ricorsivamente
     * {@code previous()} e {@code previousIndex()}, verifica che l'indice restituito sia quello
     * dell'elemento che verrà successivamente ritornato da {@code previous()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Una sottolista non vuota e un {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sttolista rimane invariata, l'iteratore si trova all'inizio della
     * sottolista
     * </p>
     * <p>
     * <b>Expected Results</b> {@code previousIndex()} restituisce l'indice dell'elemento che verrà
     * successivamente ritornato da {@code previous()}.
     * </p>
     */
    @Test
    public void previousIndexWithPrevious() {
        SubListAdapter sottolista = getSubList(6, 1, 5);

        HListIterator iterator = sottolista.listIterator(sottolista.size());

        while (iterator.hasPrevious()) {
            assertEquals(sottolista.get(iterator.previousIndex()), iterator.previous());
        }
    }

    // ======== void ListIteratorAdapter.set(Object o) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} sostituisca
     * correttamente l'ultimo elemento restituito da {@link ListIteratorAdapter#next()}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#set(Object)} sostituisca
     * correttamente l'ultimo elemento restituito da {@link ListIteratorAdapter#next()}. La
     * dimensione della sottolista e l'ordine dei suoi elementi non vegnono modificati. Verifica
     * inoltre che la modifica avvenga sia sulla sottolista che sul vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, instanzia una sottolista non vuota. Da essa
     * instanzia un iteratore grazie ad {@link SubListAdapter#listIterator()}, invoca poi
     * {@code next()}. Infine invoca {@code set("nuovo")} e verifica che l'elemento sia
     * correttamente sostituito sia sulla sottolista che sul vettore
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto, una sottolista non vuota e un
     * {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'elemento della sottolista e del vettore viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento è correttamente sostituito con il nuovo valore.
     * </p>
     */
    @Test
    public void setAddsToVector() {
        SubListAdapter subList = getSubList(3, 1, 2);
        HListIterator iterator = subList.listIterator();

        iterator.next();
        iterator.set("nuovo");

        assertEquals("nuovo", subList.get(0));
        assertEquals("nuovo", vector.get(1));
    }

    // ======== void ListIteratorAdapter.add(Object o) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link ListIteratorAdapter#add(Object)} inserisca
     * correttamente un elemento nella sottolista e nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link ListIteratorAdapter#add(Object)} inserisca
     * correttamente un elemento nella sottolista e nel vettore.. Dopo l'inserimento, verifica che
     * il nuovo elemento venga posizionato nella sottolista nel punto corretto e che le chiamate
     * successive a {@code next()} e {@code previous()} funzionino come previsto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto dal quale instanzia una sottolista non vuota. Da essa
     * instanzia un iteratore grazie ad {@link SubListAdapter#listIterator()}, e usa il metodo
     * {@code add(Object)} per inserire un elemento in una specifica posizione della sottolista.
     * Successivamente, verifica che il nuovo elemento venga posizionato nella sottolista nel punto
     * corretto e che le chiamate successive a {@code next()} e {@code previous()} funzionino come
     * previsto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto, una sottolista non vuota e un
     * {@link HListIterator}.
     * </p>
     * <p>
     * <b>Post-Condition</b> La sottolista e il vettore hanno dimensione maggiore di {@code 1}.
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento è correttamente inserito nella sottolista e nel vettore e
     * le chiamate a {@code next()} e {@code previous()} funzionino come previsto.
     * </p>
     */
    @Test
    public void addElementAtCorrectPosition() {
        SubListAdapter subList = getSubList(5, 1, 4);
        HListIterator iterator = subList.listIterator(1);

        iterator.add("X");

        assertEquals(4, subList.size());
        assertEquals("Elemento2", subList.get(0));
        assertEquals("X", subList.get(1));
        assertEquals("Elemento3", subList.get(2));
        assertEquals("Elemento4", subList.get(3));
        assertEquals("X", subList.get(iterator.previousIndex()));
        assertEquals("Elemento3", subList.get(iterator.nextIndex()));
        assertEquals(6, vector.size());
        assertTrue(vector.contains("X"));
    }
}
