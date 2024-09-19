package myTest;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Random;
import myAdapter.*;
import myAdapter.VectorAdapter.ListIteratorAdapter;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * <b>Summary</b> Verifica il corretto funzionamento della classe {@link VectorAdapter}. <br>
 * Per eseguire questa test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.VectorAdapterTester}
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
 * <li>{@link #constructorWithCapacityIncrementThrowsIllegalArgumentException()} - Verifica che il
 * construttore {@link VectorAdapter#VectorAdapter(int, int)} lanci un'eccezione se inizializzato
 * con capacità negativa.</li>
 * <li>{@link #constructorWithCapacityIncrement()} - Verifica che il construttore
 * {@link VectorAdapter#VectorAdapter(int, int)} inizializzi il vettore con la capacità
 * specificata.</li>
 * <li>{@link #constructorWithCapacityThrowsIllegalArgumentException()} - Verifica che il
 * construttore {@link VectorAdapter#VectorAdapter(int)} lanci un'eccezione se inizializzato con
 * capacità negativa.</li>
 * <li>{@link #constructorWithCapacityCapacity()} - Verifica che il construttore
 * {@link VectorAdapter#VectorAdapter(int)} inizializzi il vettore con la capacità specificata.</li>
 * <li>{@link #defaultConstructorCapacity()} - Verifica che il construttore
 * {@link VectorAdapter#VectorAdapter()} inizializzi il vettore con la capacità di {@code 10}.</li>
 * <li>{@link #constructorWithCollectionThrowsNullPointerException()} - Verifica che il construttore
 * {@link VectorAdapter#VectorAdapter(HCollection)} lanci un'eccezione se invocato con una
 * collezione {@code null}.</li>
 * <li>{@link #constructorWithCollectionWithEmptyCollection()} - Verifica che il construttore
 * {@link VectorAdapter#VectorAdapter(HCollection)} funzioni correttamente quando invocato su una
 * collezione vuota.</li>
 * <li>{@link #constructorWithCollectionAddCorrectElements()} - Verifica che il construttore
 * {@link VectorAdapter#VectorAdapter(HCollection)} inserisca correttamente tutti gli elementi nel
 * nuovo vettore.</li>
 * <li>{@link #copyIntoThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#copyInto(Object[])} lanci un'eccezione se l'array passato come parametro è
 * {@code null}.</li>
 * <li>{@link #copyIntoSmallerArray()} - Verifica che il metodo
 * {@link VectorAdapter#copyInto(Object[])} lanci un'eccezione se l'array passato come parametro è
 * troppo piccolo.</li>
 * <li>{@link #copyIntoSameSizeArray()} - Verifica che il metodo
 * {@link VectorAdapter#copyInto(Object[])} copi correttamente tutti gli elementi del
 * {@code VectorAdapter} in un array della stessa</li>
 * <li>{@link #copyIntoBiggerArray()} - Verifica che il metodo
 * {@link VectorAdapter#copyInto(Object[])} copi correttamente gli elementi in un array più
 * grande.</li>
 * <li>{@link #trimToSizePreservesElements()} - Verifica che il metodo
 * {@link VectorAdapter#trimToSize()} riduca correttamente la capacità del vettore</li>
 * <li>{@link #ensureCapacityIncrementsCapacity()} - Verifica che
 * {@link VectorAdapter#ensureCapacity(int)} aumenti la capacità di un vettore alla quantità
 * specificata.</li>
 * <li>{@link #ensureCapacityDoesNotDecrementCapacity()} - Verifica che
 * {@link VectorAdapter#ensureCapacity(int)} non diminuisca la capacità di un vettore al di sotto
 * della sua capacità attuale.</li>
 * <li>{@link #setSizeThrowsArrayIndexOutOfBoundsException()} - Verifica che
 * {@link VectorAdapter#setSize(int)} lanci un'eccezione quando invocato con un numero
 * negativo.</li>
 * <li>{@link #setSizeDoesNotChangeVector()} - Verifica che {@link VectorAdapter#setSize(int)} non
 * modifichi la dimensione del vettore quando chiamato con la stessa dimensione del vettore.</li>
 * <li>{@link #setSizeIncreasesVectorSize()} - Verifica che il metodo
 * {@link VectorAdapter#setSize(int)} aumenti correttamente la dimensione del vettore.</li>
 * <li>{@link #setSizeDecreasesVectorSize()} - Verifica che il metodo
 * {@link VectorAdapter#setSize(int)} diminuisca correttamente la dimensione del vettore.</li>
 * <li>{@link #emptyVectorHasSizeZero()} - Verifica del metodo {@link VectorAdapter#size()} su un
 * vettore vuoto.</li>
 * <li>{@link #sizeChangesWhileAddingElements()} - Verifica che il metodo
 * {@link VectorAdapter#size()} funzioni correttamente mentre vengono aggiunti elementi.</li>
 * <li>{@link #sizeChangesWhileRemovingElements()} - Verifica che il metodo
 * {@link VectorAdapter#size()} funzioni correttamente mentre vengono rimossi elementi.</li>
 * <li>{@link #isEmptyOnEmptyVector()} - Verifica del metodo {@link VectorAdapter#isEmpty()} su un
 * vettore vuoto.</li>
 * <li>{@link #isEmptyOnNonEmptyVector()} - Verifica del metodo {@link VectorAdapter#isEmpty()} su
 * un vettore non vuoto.</li>
 * <li>{@link #elementsReturnsEnumeration()} - Verifica del metodo {@link VectorAdapter#elements()}
 * ritorni un oggetto di tipo {@link Enumeration}.</li>
 * <li>{@link #containsReturnsTrue()} - Verifica del metodo {@link VectorAdapter#contains(Object)}
 * quando l'elemento è presente nel vettore.</li>
 * <li>{@link #containsReturnsFalse()} - Verifica del metodo {@link VectorAdapter#contains(Object)}
 * quando l'elemento non è presente nel vettore.</li>
 * <li>{@link #indexOfElementFound()} - Verifica che il metodo {@link VectorAdapter#indexOf(Object)}
 * restituisca correttamente l'indice della prima occorrenza di un elemento presente nel
 * vettore.</li>
 * <li>{@link #indexOfElementNotFound()} - Verifica che il metodo
 * {@link VectorAdapter#indexOf(Object)} restituisca {@code -1} quando l'elemento cercato non è
 * presente nel vettore.</li>
 * <li>{@link #indexOfNull()} - Verifica che il metodo {@link VectorAdapter#indexOf(Object)}
 * gestisca correttamente la ricerca di un elemento {@code null}.</li>
 * <li>{@link #indexOfFromIndexThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#indexOf(Object, int)} lanci un'eccezione se l'indice specificato è
 * negativo.</li>
 * <li>{@link #indexOfFromIndexWithIndexBiggerThanVector()} - Verifica del metodo
 * {@link VectorAdapter#indexOf(Object, int)} quando invocato con un indice maggiore della
 * dimensione del vettore.</li>
 * <li>{@link #indexOfFromIndexElementFound()} - Verifica che il metodo
 * {@link VectorAdapter#indexOf(Object, int)} restituisca correttamente l'indice dell'elemento
 * specificato.</li>
 * <li>{@link #indexOfFromIndexElementNotFound()} - Verifica che il metodo
 * {@link VectorAdapter#indexOf(Object, int)} restituisca {@code -1} quando l'elemento cercato non è
 * presente nel vettore.</li>
 * <li>{@link #indexOfFromIndexNull()} - Verifica che il metodo
 * {@link VectorAdapter#indexOf(Object, int)} gestisca correttamente la ricerca di un elemento
 * {@code null}.</li>
 * <li>{@link #lastIndexOfElementFound()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object)} restituisca correttamente l'indice dell'ultima
 * occorrenza di un elemento presente nel vettore.</li>
 * <li>{@link #lastIndexOfElementNotFound()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object)} restituisca {@code -1} quando l'elemento cercato non è
 * presente nel vettore.</li>
 * <li>{@link #lastIndexOfNull()} - Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)}
 * gestisca correttamente la ricerca di un elemento {@code null}.</li>
 * <li>{@link #lastIndexOfFromIndexThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object, int)} lanci un'eccezione se l'indice specificato è
 * maggiore della dimensione del vettore.</li>
 * <li>{@link #lastIndexOfNegativeIndex()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object, int)} ritorni {@code -1} quando invocato con un indice
 * negativo.</li>
 * <li>{@link #lastIndexOfFromIndexElementFound()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object, int)} restituisca correttamente l'indice dell'elemento
 * specificato.</li>
 * <li>{@link #lastIndexOfFromIndexElementNotFound()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object, int)} restituisca {@code -1} quando l'elemento cercato
 * non è presente nel vettore.</li>
 * <li>{@link #lastIndexOfFromIndexNull()} - Verifica che il metodo
 * {@link VectorAdapter#lastIndexOf(Object, int)} gestisca correttamente la ricerca di un elemento
 * {@code null}.</li>
 * <li>{@link #elementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#elementAt(int)} lanci un'eccezione quando invocato con un indice
 * negativo.</li>
 * <li>{@link #elementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#elementAt(int)} lanci un'eccezione quando invocato con un indice uguale alla
 * dimensione del vettore.</li>
 * <li>{@link #elementAtOnVectorWithElements()} - Verifica che il metodo
 * {@link VectorAdapter#elementAt(int)} funzioni correttamente.</li>
 * <li>{@link #firstElementThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link VectorAdapter#firstElement()} lanci un'eccezione quando il vettore è vuoto.</li>
 * <li>{@link #firstElementReturnsFirstElement()} - Verifica che il metodo
 * {@link VectorAdapter#firstElement()} restituisca il primo elemento di un vettore non vuoto.</li>
 * <li>{@link #lastElementThrowsNoSuchElementException()} - Verifica che il metodo
 * {@link VectorAdapter#lastElement()} lanci un'eccezione quando il vettore è vuoto.</li>
 * <li>{@link #lastElementReturnsFirstElement()} - Verifica che il metodo
 * {@link VectorAdapter#lastElement()} restituisca l'ultimo elemento di un vettore non vuoto.</li>
 * <li>{@link #setElementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#setElementAt(Object, int)} lanci un'eccezione quando l'indice fornito è
 * negativo.</li>
 * <li>{@link #setElementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#setElementAt(Object, int)} lanci un'eccezione quando l'indice fornito è
 * maggiore o uguale alla dimensione del vettore.</li>
 * <li>{@link #setElementAtUpdatesValue()} - Verifica che il metodo
 * {@link VectorAdapter#setElementAt(Object, int)} imposti correttamente il valore di un elemento a
 * un indice valido.</li>
 * <li>{@link #removeElementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#removeElementAt(int)} lanci un'eccezione se l'indice è negativo.</li>
 * <li>{@link #removeElementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#removeElementAt(int)} lanci un'eccezione se l'indice è maggiore o uguale
 * alla dimensione del vettore.</li>
 * <li>{@link #removeElementAtRemovesAllElements()} - Verifica che il metodo
 * {@link VectorAdapter#removeElementAt(int)} rimuova correttamente gli elementi da una lista.</li>
 * <li>{@link #insertElementAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#insertElementAt(Object, int)} lanci un'eccezione se invocato con un indice
 * negativo.</li>
 * <li>{@link #insertElementAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#insertElementAt(Object, int)} lanci un'eccezione se invocato con un indice
 * maggiore della dimensione.</li>
 * <li>{@link #insertElementAtAtIndexOnNonEmpty()} - Verifica che il metodo
 * {@link VectorAdapter#insertElementAt(Object, int)} aggiunga correttamente gli elementi su un
 * vettore.</li>
 * <li>{@link #addElementAddsElementAtTheEndOfVector()} - Verifica che il metodo
 * {@link VectorAdapter#addElement(Object)} aggiunga correttamente elementi al termine del
 * vettore.</li>
 * <li>{@link #removeElementFirstOccurrence()} - Verifica che il metodo
 * {@link VectorAdapter#removeElement(Object obj)} rimuova solamente la prima occorrenza
 * dell'oggetto specificato nel vettore.</li>
 * <li>{@link #removeElementNotPresent()} - Verifica il metodo
 * {@link VectorAdapter#removeElement(Object obj)} quando l'oggetto da rimuovere non è presente nel
 * vettore.</li>
 * <li>{@link #removeElementNull()} - Verifica che il metodo
 * {@link VectorAdapter#removeElement(Object obj)} rimuova correttamente un elemento {@code null}
 * presente nel vettore.</li>
 * <li>{@link #removeAllElementsEmptiesVector()} - Verifica che il metodo
 * {@link VectorAdapter#removeAllElements()} rimuova correttamente gli elementi dal vettore.</li>
 * <li>{@link #cloneClonesVector()} - Verifica che il metodo {@link VectorAdapter#clone()}
 * restituisca un nuovo oggetto che contiene gli stessi elementi del vettore originale.</li>
 * <li>{@link #cloneDeepCopies()} - Verifica che il metodo {@link VectorAdapter#clone()} crei un
 * nuovo oggetto le cui modifiche non sono riflesse sull'originale.</li>
 * <li>{@link #toArrayMultipleElements()} - Verifica che il metodo {@link VectorAdapter#toArray()}
 * restituisca un array contenente gli elementi presenti nel vettore.</li>
 * <li>{@link #toArrayThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#toArray(Object[])} lanci un'eccezione se l'array fornito è
 * {@code null}.</li>
 * <li>{@link #testToArrayIncompatibleTypeArray()} - Verifica che il metodo
 * {@link VectorAdapter#toArray(Object[])} lanci un'eccezione se il tipo runtime dell'array fornito
 * non è compatibile con il tipo degli</li>
 * <li>{@link #toArrayCorrectSizeArray()} - Verifica che il metodo
 * {@link VectorAdapter#toArray(Object[])} popoli correttamente l'array fornito quando ha una
 * dimensione uguale a quella del vettore.</li>
 * <li>{@link #toArrayOversizedArray()} - Verifica che il metodo
 * {@link VectorAdapter#toArray(Object[])} popoli correttamente l'array fornito quando ha una
 * dimensione maggiore a quella del vettore.</li>
 * <li>{@link #toArrayUndersizedArray()} - Verifica che il metodo
 * {@link VectorAdapter#toArray(Object[])} popoli correttamente l'array fornito quando ha una
 * dimensione minore a quella del vettore.</li>
 * <li>{@link #getThrowsExceptionForNegativeIndex()} - Verifica che il metodo
 * {@link VectorAdapter#get(int)} lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando
 * invocato con un indice negativo.</li>
 * <li>{@link #getThrowsExceptionForIndexGreaterThanSize()} - Verifica che il metodo
 * {@link VectorAdapter#get(int)} lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando
 * invocato con un indice uguale alla dimensione del vettore.</li>
 * <li>{@link #getOnVectorWithElements()} - Verifica che il metodo {@link VectorAdapter#get(int)}
 * funzioni correttamente.</li>
 * <li>{@link #setThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#set(int, Object)} lanci un'eccezione quando l'indice fornito è
 * negativo.</li>
 * <li>{@link #setThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#set(int, Object)} lanci un'eccezione quando l'indice fornito è maggiore o
 * uguale alla dimensione del vettore.</li>
 * <li>{@link #setUpdatesValue()} - Verifica che il metodo {@link VectorAdapter#set(int, Object)}
 * imposti correttamente il valore di un elemento a un indice valido.</li>
 * <li>{@link #addAddsElementAtTheEndOfVector()} - Verifica che il metodo
 * {@link VectorAdapter#add(Object)} aggiunga correttamente elementi al termine del vettore.</li>
 * <li>{@link #removeFirstOccurrence()} - Verifica che il metodo
 * {@link VectorAdapter#remove(Object obj)} rimuova solamente la prima occorrenza dell'oggetto
 * specificato nel vettore.</li>
 * <li>{@link #removeNotPresent()} - Verifica il metodo {@link VectorAdapter#remove(Object obj)}
 * quando l'oggetto da rimuovere non è presente nel vettore.</li>
 * <li>{@link #removeNull()} - Verifica che il metodo {@link VectorAdapter#remove(Object obj)}
 * rimuova correttamente un elemento {@code null} presente nel vettore.</li>
 * <li>{@link #addAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#add(int, Object)} lanci un'eccezione se invocato con un indice
 * negativo.</li>
 * <li>{@link #addAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#add(int, Object)} lanci un'eccezione se invocato con un indice maggiore
 * della dimensione.</li>
 * <li>{@link #addAtAtIndexOnNonEmpty()} - Verifica che il metodo
 * {@link VectorAdapter#add(int, Object)} aggiunga correttamente gli elementi su un vettore.</li>
 * <li>{@link #removeThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#remove(int)} lanci un'eccezione se l'indice è negativo.</li>
 * <li>{@link #removeThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#remove(int)} lanci un'eccezione se l'indice è maggiore o uguale alla
 * dimensione del vettore.</li>
 * <li>{@link #removeRemovesAllElements()} - Verifica che il metodo
 * {@link VectorAdapter#remove(int)} rimuova correttamente gli elementi da una lista.</li>
 * <li>{@link #clearEmptiesVector()} - Verifica che il metodo {@link VectorAdapter#clear()} rimuova
 * correttamente gli elementi dal vettore.</li>
 * <li>{@link #containsAllThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#containsAll(HCollection)} lanci un'eccezione se l'array fornito è
 * {@code null}.</li>
 * <li>{@link #containsAllElementsPresent()} - Verifica che il metodo
 * {@link VectorAdapter#containsAll(HCollection)} restituisca {@code true} quando il vettore
 * contiene tutti gli elementi della collezione specificata.</li>
 * <li>{@link #containsAllElementsNotPresent()} - Verifica che il metodo
 * {@link VectorAdapter#containsAll(HCollection)} restituisca {@code false} quando il vettore non
 * contiene tutti gli elementi della collezione specificata.</li>
 * <li>{@link #addAllThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(HCollection)} lanci un'eccezione quando la collezione specificata è
 * {@code null}.</li>
 * <li>{@link #addAllonNonEmptyVector()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(HCollection)} aggiungacorrettamente tutti gli elementi dalla
 * collezione specificata.</li>
 * <li>{@link #addAllOnSelf()} - Verifica il comportamento del metodo
 * {@link VectorAdapter#addAll(HCollection)} quando l'argomento è il vettore stesso.</li>
 * <li>{@link #removeAllThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#removeAll(HCollection)} lanci un'eccezione quando la collezione specificata
 * è {@code null}.</li>
 * <li>{@link #removeAllReturnsTrue()} - Verifica che il metodo
 * {@link VectorAdapter#removeAll(HCollection)} rimuova dal vettore tutti gli elementi specificati
 * nella collezione.</li>
 * <li>{@link #removeAllReturnsFalse()} - Verifica che il metodo
 * {@link VectorAdapter#removeAll(HCollection)} non modifichi il vettore se nessuno gli elementi
 * specificati nella collezione è presente nel</li>
 * <li>{@link #retainAllThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#retainAll(HCollection)} lanci un'eccezione quando la collezione specificata
 * è {@code null}.</li>
 * <li>{@link #retainAllRetainsElements()} - Verifica la funzionalità del metodo
 * {@link VectorAdapter#retainAll(HCollection)}.</li>
 * <li>{@link #retainAllOnSelf()} - Verifica che il metodo
 * {@link VectorAdapter#retainAll(HCollection)} si comporti correttamente quando invocato
 * utilizzando come parametro il vettore stesso.</li>
 * <li>{@link #retainAllRemovesAll()} - Verifica che il metodo
 * {@link VectorAdapter#retainAll(HCollection)} si comporti correttamente quando invocato
 * utilizzando come parametro una collezione vuota.</li>
 * <li>{@link #addAllAtThrowsNullPointerException()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(int, HCollection)} lanci un'eccezione quando la collezione
 * specificata è {@code null}.</li>
 * <li>{@link #addAllAtThrowsArrayIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(int, HCollection)} lanci un'eccezione se l'indice è negativo.</li>
 * <li>{@link #addAllAtThrowsArrayIndexOutOfBoundsException2()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(int, HCollection)} lanci un'eccezione se l'indice è maggiore alla
 * dimensione del vettore.</li>
 * <li>{@link #addAllAtIndex()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(int, HCollection)} aggiunga correttamente gli elementi all'indice
 * specificato.</li>
 * <li>{@link #addAllAtIndexWithEmptyCollection()} - Verifica che il metodo
 * {@link VectorAdapter#addAll(int, HCollection)} quando la collezione è vuota.</li>
 * <li>{@link #equalsOnNonList()} - Verifica che il metodo {@link VectorAdapter#equals(Object)}
 * restituisca {@code false} quando l'oggetto confrontato non è una lista.</li>
 * <li>{@link #equalsReturnsTrue()} - Verifica che il metodo {@link VectorAdapter#equals(Object)}
 * restituisca {@code true} quando due vettori contengono gli stessi elementi nello stesso
 * ordine.</li>
 * <li>{@link #equalsReturnsFalse()} - Verifica che il metodo {@link VectorAdapter#equals(Object)}
 * restituisca {@code false} quando due vettori hanno contenuti diversi.</li>
 * <li>{@link #equalsReturnsFalse2()} - Verifica che il metodo {@link VectorAdapter#equals(Object)}
 * restituisca {@code false} quando due vettori dimensioni diverse.</li>
 * <li>{@link #testEqualsWithSelf()} - Verifica che il metodo {@link VectorAdapter#equals(Object)}
 * restituisca {@code true} quando il vettore viene confrontato con sé stesso.</li>
 * <li>{@link #hasCodeEqualVectors()} - Verifica che il metodo {@link VectorAdapter#hashCode()}
 * ritorni lo stesso codice per vettori con elementi identici.</li>
 * <li>{@link #hashCodeWithDifferentVectors()} - Verifica che il metodo
 * {@link VectorAdapter#hashCode()} ritorni codici hash diversi per vettori diversi.</li>
 * <li>{@link #hashCodeConsistency()} - Verifica che il metodo {@link VectorAdapter#hashCode()} di
 * un vettore sia coerente attraverso chiamate multiple.</li>
 * <li>{@link #toStringEmptyVector()} - Verifica che il metodo {@link VectorAdapter#toString()}
 * restituisca "[]" per un vettore vuoto.</li>
 * <li>{@link #toStringOnVector()} - Verifica che il metodo {@link VectorAdapter#toString()}
 * restituisca la stringa corretta per un vettore non vuoto.</li>
 * <li>{@link #testToStringWithCyclicReference()} - Verifica che il metodo
 * {@link VectorAdapter#toString()} gestisca correttamente i riferimenti ciclici a se stesso.</li>
 * <li>{@link #subListThrowsIllegalArgumentException()} - Verifica che
 * {@link VectorAdapter#subList(int, int)} lanci un'eccezione quando
 * {@code fromIndex > toIndex}.</li>
 * <li>{@link #subListThrowsIndexOutOfBoundsException()} - Verifica che
 * {@link VectorAdapter#subList(int, int)} lanci un'eccezione quando {@code fromIndex < 0}.</li>
 * <li>{@link #subListThrowsIndexOutOfBoundsException2()} - Verifica che
 * {@link VectorAdapter#subList(int, int)} lanci un'eccezione quando {@code toIndex > size}.</li>
 * <li>{@link #subListBetweenRange()} - Verifica che {@link VectorAdapter#subList(int, int)} ritorni
 * la corretta vista del vettore.</li>
 * <li>{@link #subListFullRange()} - Verifica che {@link VectorAdapter#subList(int, int)} ritorni la
 * corretta vista del vettore.</li>
 * <li>{@link #subListEqualIndices()} - Verifica che {@link VectorAdapter#subList(int, int)} ritorni
 * la corretta vista del vettore.</li>
 * <li>{@link #iteratorReturnsHIterator()} - Verifica del metodo {@link VectorAdapter#iterator()}
 * ritorni un oggetto di tipo {@link HIterator}.</li>
 * <li>{@link #listiteratorReturnsHListIterator()} - Verifica del metodo
 * {@link VectorAdapter#listIterator()} ritorni un oggetto di tipo {@link HListIterator}.</li>
 * <li>{@link #listIteratorThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#listIterator(int)} lanci un'eccezione quando invocato con un indice
 * negativo.</li>
 * <li>{@link #listItreatorThrowsIndexOutOfBoundsException()} - Verifica che il metodo
 * {@link VectorAdapter#listIterator(int)} lanci un'eccezione {@link IndexOutOfBoundsException}
 * quando invocato con un indice uguale alla dimensione del vettore.</li>
 * <li>{@link #listiteratorReturnsHListIteratorFromIndex()} - Verifica del metodo
 * {@link VectorAdapter#listIterator(int)} ritorni un oggetto di tipo {@link HListIterator}.</li>
 * <li>{@link #listIteratorReturnsCorrectView()} - Verifica del metodo
 * {@link VectorAdapter#listIterator(int)} ritorni un oggetto di tipo {@link HListIterator} che
 * inizia dall'indice corretto.</li>
 * </ul>
 * <p>
 * <b>Test Suite Execution Records</b> {@code 130} test eseguiti, {@code 130} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link VectorAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
public class VectorAdapterTester {

    /**
     * vettore inizializzato prima di ogni test
     */
    private VectorAdapter vector;

    /**
     * Costruttore di default
     */
    public VectorAdapterTester() {}

    /**
     * Inizializza la variabile {@code vector} come un vettore vuoto di tipo VectorAdapter.
     *
     * Viene eseguito prima di ogni test
     */
    @Before
    public void init() {
        vector = new VectorAdapter();
    }

    // ======== VectorAdapter(int, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int, int)}
     * lanci un'eccezione se inizializzato con capacità negativa.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int, int)}
     * lanci un'eccezione {@link IllegalArgumentException} se inizializzato con capacità negativa.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore
     * {@link VectorAdapter#VectorAdapter(int, int)} utilizzando un numero negativo come parametro
     * di capacità iniziale del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condition.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene instanziato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#VectorAdapter(int, int)} lancia l'eccezione
     * {@link IllegalArgumentException}.
     * </p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorWithCapacityIncrementThrowsIllegalArgumentException() {
        new VectorAdapter(-1, 0);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int, int)}
     * inizializzi il vettore con la capacità specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int, int)}
     * inizializzi il vettore con la capacità specificata. Il valore di {@code capacityIncrement} e
     * {@code initialCapacity} viene impostato con valori casuali.
     * </p>
     * <p>
     * <b>Design</b> Utilizzando un ciclo instanzia diversi vettori con il costruttore
     * {@link VectorAdapter#VectorAdapter(int, int)} utilizzando valori casuali per entrambi i
     * parametri (contenuti tra 0 e 99), poi invoca il metodo {@link VectorAdapter#capacity()} e
     * verifica che ritorni lo stesso valore utilizzato nel costruttore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna pre-condizione
     * </p>
     * <p>
     * <b>Post-Condition</b> I vettori instanziati hanno le corrette capacità.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#capacity()} ritorna la stessa capacità
     * specificata nel costruttore.
     * </p>
     */
    @Test
    public void constructorWithCapacityIncrement() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int initialCapacity = random.nextInt(100);
            VectorAdapter vect = new VectorAdapter(initialCapacity, random.nextInt(100));
            assertEquals(initialCapacity, vect.capacity());
        }
    }

    // ======== VectorAdapter(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int)} lanci
     * un'eccezione se inizializzato con capacità negativa.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int)}
     * lanci un'eccezione {@link IllegalArgumentException} se inizializzato con capacità negativa.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore
     * {@link VectorAdapter#VectorAdapter(int)} utilizzando un numero negativo sulla capacità
     * iniziale del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condition.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene instanziato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#VectorAdapter(int)} lancia l'eccezione
     * {@link IllegalArgumentException}.
     * </p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorWithCapacityThrowsIllegalArgumentException() {
        new VectorAdapter(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int)}
     * inizializzi il vettore con la capacità specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(int)}
     * inizializzi il vettore con la capacità specificata. Il valore di {@code initialCapacity}
     * viene impostato con valori casuali
     * </p>
     * <p>
     * <b>Design</b> Grazie ad un ciclo instanzia diversi vettori con il costruttore
     * {@link VectorAdapter#VectorAdapter(int)} impostando una dimensione iniziale casuale
     * (contenuto tra 0 e 99), poi invoca su ognuno il metodo {@link VectorAdapter#capacity()} e
     * verifica che ritorni lo stesso valore utilizzato nel costruttore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condizione.
     * </p>
     * <p>
     * <b>Post-Condition</b> I vettori instanziati hanno le corrette capacità.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#capacity()} ritorna la stessa capacità
     * specificata nel costruttore.
     * </p>
     */
    @Test
    public void constructorWithCapacityCapacity() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int initialCapacity = random.nextInt(100);
            VectorAdapter vect = new VectorAdapter(initialCapacity);
            assertEquals(initialCapacity, vect.capacity());
        }
    }

    // ======== VectorAdapter() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter()} inizializzi
     * il vettore con la capacità di {@code 10}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter()}
     * inizializzi il vettore con la capacità di {@code 10}. Come specificato dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore {@link VectorAdapter#VectorAdapter()}
     * poi invoca il metodo {@link VectorAdapter#capacity()} e verifica che ritorni {@code 10}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condition.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore viene instanziato ed ha capacità 10.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#capacity()} ritorna {@code 10}.
     * </p>
     */
    @Test
    public void defaultConstructorCapacity() {
        assertEquals(10, vector.capacity());
    }

    // ======== VectorAdapter(HCollection) ========
    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(HCollection)}
     * lanci un'eccezione se invocato con una collezione {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore
     * {@link VectorAdapter#VectorAdapter(HCollection)} lanci un'eccezione
     * {@link NullPointerException} se invocato con una collezione {@code null}. Come specificato
     * dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore
     * {@link VectorAdapter#VectorAdapter(HCollection)} impostando {@code null} come parametro.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condition.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene instanziato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#VectorAdapter(HCollection)} lancia l'eccezione
     * {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void constructorWithCollectionThrowsNullPointerException() {
        new VectorAdapter(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(HCollection)}
     * funzioni correttamente quando invocato su una collezione vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore
     * {@link VectorAdapter#VectorAdapter(HCollection)} funzioni correttamente quando invocato su
     * una collezione vuota. Il comportamento atteso è lo stesso del costruttore
     * {@link VectorAdapter#VectorAdapter()}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore
     * {@link VectorAdapter#VectorAdapter(HCollection)} specificando una collezione vuota. Verifica
     * poi che il vettore ritornato abbia dimensione {@code 0} e capacità {@code 10}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condition.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore viene instanziato, vuoto e di capacità {@code 10}.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#VectorAdapter(HCollection)} si comporta come
     * {@link VectorAdapter#VectorAdapter()} instanziando un vettore vuoto di capacità {@code 10}.
     * </p>
     */
    @Test
    public void constructorWithCollectionWithEmptyCollection() {
        VectorAdapter vect = new VectorAdapter(vector);

        assertEquals(0, vect.size());
        assertEquals(0, vect.capacity());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il construttore {@link VectorAdapter#VectorAdapter(HCollection)}
     * inserisca correttamente tutti gli elementi nel nuovo vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il construttore
     * {@link VectorAdapter#VectorAdapter(HCollection)} inserisca correttamente tutti gli elementi
     * nel nuovo vettore. Viene verificata la presenza di tutti gli elementi, nell'ordine corretto,
     * ovvero quello specificato dall'iteratore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore
     * {@link VectorAdapter#VectorAdapter(HCollection)} specificando una collezione non vuota.
     * Utilizzando poi l'iteratore fornito da {@link VectorAdapter#iterator()} assieme a quello
     * fornito dalla collezione viene verificato che gli elementi siano tutti presenti nell'ordine
     * corretto. Questo viene fatto chiamando ripetutamente il metodo {@code next()} dei due
     * iteratori e verificando che il risultato sia sempre uguale. Viene anche verificata la
     * dimensione dei due vettori per assicurarsi l'uguaglianza delle collezioni.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Nessuna Pre-Condizione.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore viene instanziato e contiene tutti gli elementi della
     * collezione, nell'ordine corretto.
     * </p>
     * <p>
     * <b>Expected Results</b> Le invocazioni ai metodi {@code next()} dei due iteratori ritornano
     * le stesse coppie di elementi. La dimensione delle due collezioni è la stessa.
     * </p>
     */
    @Test
    public void constructorWithCollectionAddCorrectElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");

        VectorAdapter vector2 = new VectorAdapter(vector);

        assertEquals(vector.size(), vector2.size());

        HIterator it1 = vector.iterator();
        HIterator it2 = vector2.iterator();

        while (it1.hasNext() || it2.hasNext()) {
            assertEquals(it1.next(), it2.next());
        }
    }

    // ======== void copyInto(Object[]) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} lanci
     * un'eccezione se l'array passato come parametro è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} lanci
     * un'eccezione {@link NullPointerException} se l'array passato come parametro è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto. Invoca il metodo
     * {@link VectorAdapter#copyInto(Object[])} sul vettore con {@code null} come parametro.
     * Verifica che venga sollevata un'eccezione {@link NullPointerException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#copyInto(Object[])} lancia
     * un'eccezione {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void copyIntoThrowsNullPointerException() {
        vector.copyInto(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} lanci
     * un'eccezione se l'array passato come parametro è troppo piccolo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} lanci
     * un'eccezione {@link IndexOutOfBoundsException} se l'array passato come parametro è troppo
     * piccolo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elementi e un array lunghezza 1. Invoca il metodo
     * {@link VectorAdapter#copyInto(Object[])} sul vettore con l'array come parametro. Si verifica
     * che venga sollevata un'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di 2 elementi e un array di dimensione 1.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e l'array rimangono invariati
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#copyInto(Object[])} lancia
     * un'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void copyIntoSmallerArray() {
        vector.addElement("A");
        vector.addElement("B");

        Object[] array = new Object[1];

        vector.copyInto(array);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} copi
     * correttamente tutti gli elementi del {@code VectorAdapter} in un array della stessa
     * dimensione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} copi
     * correttamente tutti gli elementi del vettore in un array della stessa dimensione. Viene
     * inoltre verificato che l'ordine degli elementi sia corretto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di 3 elementi e un array di 3 elementi di tipo
     * {@link Object}, invoca il metodo {@link VectorAdapter#copyInto(Object[])} sul vettore ponendo
     * l'array come argomento. Infine viene verificato che l'elemento di indice {@code k} del
     * vettore e l'elemento di indice {@code k} dell'array sono uguali
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di 3 elementi e un array è di 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> L'array contiene gli stessi elementi del vettore, nello stesso ordine.
     * Il vettore rimane invariato
     * </p>
     * <p>
     * <b>Expected Results</b> L'elemento di indice {@code k} del vettore e l'elemento di indice
     * {@code k} dell'array sono uguali.
     * </p>
     */
    @Test
    public void copyIntoSameSizeArray() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        Object[] array = new Object[3];
        vector.copyInto(array);

        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("C", vector.get(2));
        assertEquals(3, vector.size());

        assertEquals(vector.get(0), array[0]);
        assertEquals(vector.get(1), array[1]);
        assertEquals(vector.get(2), array[2]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} copi
     * correttamente gli elementi in un array più grande.
     * </p>
     * <p>
     * <b>Description</b>Verifica che il metodo {@link VectorAdapter#copyInto(Object[])} copi
     * correttamente gli elementi in un array più grande. Le posizioni non utilizzate dell'array
     * dovrebbero rimanere invariate, ovvero {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elementi e un array di dimensione {@code 3}. Invoca
     * {@link VectorAdapter#copyInto(Object[])} sul vettore con l'array come parametro. Verifica che
     * i primi due elementi dell'array coincidano con quelli del vettore, e l'ultimo sia rimasto
     * {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di 2 elementi e un array di dimensione 3.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Nell'array i primi due elementi contengono gli elementi del vettore,
     * l'ultimo elemento rimane {@code null}.
     * </p>
     */

    @Test
    public void copyIntoBiggerArray() {
        vector.add("A");
        vector.add("B");

        Object[] array = new Object[3];
        vector.copyInto(array);

        assertEquals(vector.get(0), array[0]);
        assertEquals(vector.get(1), array[1]);
        assertNull(array[2]);
    }

    // ======== void trimToSize() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#trimToSize()} riduca correttamente
     * la capacità del vettore
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#trimToSize()} riduca
     * correttamente la capacità del vettore. Inoltre verifica che gli elementi del vettore
     * rimangano invariati dopo l'invocazione del metodo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 3} elementi e capacità {@code 10}. Invoca il
     * metodo {@link VectorAdapter#trimToSize()}. Verifica che la capacità finale sia {@code 3} e
     * verifica che tutti gli elementi siano rimasti invariati dopo l'operazione.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Il vettore è instanziato, di {@code 3} elementi e capacità {@code 10}
     * </p>
     * <p>
     * <b>Post-Condition</b> Gli elementi del vettore rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Gli elementi del vettore sono invariati e la capacità è stata ridotta
     * a {@code 3}.
     * </p>
     */
    @Test
    public void trimToSizePreservesElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        assertEquals(10, vector.capacity());
        vector.trimToSize();

        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("C", vector.get(2));
        assertEquals(3, vector.size());
        assertEquals(3, vector.capacity());
    }

    // ======== void ensureCapacity(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#ensureCapacity(int)} aumenti la capacità di
     * un vettore alla quantità specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#ensureCapacity(int)} aumenti la capacità
     * di un vettore alla quantità specificata. In particolare un vettore con capacità {@code 10} e
     * viene incrementato almeno a capacità {@code 25}.
     * </p>
     * <p>
     * <b>Design</b> Viene instanziato un vettore vuoto di capacità 10, viene invocato il metodo
     * {@code ensureCapacity(25)} e viene verificato il valore della nuova capacità la quale deve
     * essere maggiore o uguale di {@code 25}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto instanziato di capacità 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> Un vettore vuoto instanziato di capacità di almeno 25.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#ensureCapacity(int)} aumenta la
     * capacità del vettore ad un valore maggiore o uguale a 25.
     * </p>
     */
    @Test
    public void ensureCapacityIncrementsCapacity() {
        assertEquals(10, vector.capacity());
        vector.ensureCapacity(25);
        assertTrue(vector.capacity() >= 25);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#ensureCapacity(int)} non diminuisca la
     * capacità di un vettore al di sotto della sua capacità attuale.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#ensureCapacity(int)} non diminuisca la
     * capacità di un vettore al di sotto della sua capacità attuale. In particolare ad un vettore
     * di capacità {@code 10} si prova a decrementare la capacità a {@code 5}. Ci si aspetta che la
     * capacità rimanga {@code 10}.
     * </p>
     * <p>
     * <b>Design</b> Viene instanziato un vettore di capacità 10, viene invocato il metodo
     * {@code ensureCapacity(5)} e viene verificato il valore della nuova capacità.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore instanziato di capacità 10.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#ensureCapacity(int)} non modifica il
     * vettore.
     * </p>
     */
    @Test
    public void ensureCapacityDoesNotDecrementCapacity() {
        assertEquals(10, vector.capacity());
        vector.ensureCapacity(5);
        assertEquals(10, vector.capacity());
    }

    // ======== void setSize(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#setSize(int)} lanci un'eccezione quando
     * invocato con un numero negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#setSize(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando viene invocato con un numero negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, invoca su di esso il metodo {@code setSize(-1)},
     * verifica che venga lanciata l'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#setSize(int)} lancia l'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setSizeThrowsArrayIndexOutOfBoundsException() {
        vector.setSize(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#setSize(int)} non modifichi la dimensione
     * del vettore quando chiamato con la stessa dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#setSize(int)} non modifichi la
     * dimensione del vettore quando chiamato con la stessa dimensione del vettore. In particolare
     * verifichiamo che su un vettore di {@code 1} elemento invocando {@code setSize(1)} il vettore
     * non venga modificato.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elmento, invoca il metodo {@code setSize(1)} e
     * verifica che dimensione e contenuto del vettore non siano cambiati.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> La chiamata {@code setSize(1)} non modifica il vettore.
     * </p>
     */
    @Test
    public void setSizeDoesNotChangeVector() {
        vector.add("A");
        vector.setSize(1);
        assertEquals(1, vector.size());
        assertEquals("A", vector.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#setSize(int)} aumenti
     * correttamente la dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#setSize(int)} aumenti
     * correttamente la dimensione del vettore. Verifica inoltre che gli elementi successivi
     * all'ultimo elemento vengano impostati a {@code null}
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore contenente un elmento, invoca poi {@code setSize(2)}.
     * Verifica poi che il vettore abbia dimensione 2, dove il primo elemento è l'elemento iniziale,
     * mentre il secondo è {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 1} elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha dimensione {@code 2}.
     * </p>
     * <p>
     * <b>Expected Results</b> Il vettore ha dimensione {@code 2} con il primo elmento invariato e
     * il secondo a {@code null}.
     * </p>
     */
    @Test
    public void setSizeIncreasesVectorSize() {
        vector.add("A");
        vector.setSize(2);

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertNull(vector.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#setSize(int)} diminuisca
     * correttamente la dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#setSize(int)} aumenti
     * correttamente la dimensione del vettore. Verifica inoltre che gli elementi successivi
     * all'ultimo elemento vengano rimossi correttamente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore contenente {@code 2} elementi, invoca poi
     * {@code setSize(1)}. Verifica poi che il vettore abbia dimensione 1, dove l'unico elemento è
     * il primo del vettore iniziale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 2} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha dimensione {@code 1}.
     * </p>
     * <p>
     * <b>Expected Results</b> Il vettore ha dimensione {@code 1} e l'elemento è il primo elemento
     * del vettore.
     * </p>
     */
    @Test
    public void setSizeDecreasesVectorSize() {
        vector.add("A");
        vector.add("B");

        vector.setSize(1);

        assertEquals(1, vector.size());
        assertEquals("A", vector.get(0));
    }

    // ======== int size() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#size()} su un vettore vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#size()} ritorni {@code 0}
     * quando invocato su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore di default, chiama la funzione
     * {@link VectorAdapter#size()} e verifica che ritorni {@code 0}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#size()} ritorna {@code 0}.
     * </p>
     */
    @Test
    public void emptyVectorHasSizeZero() {
        assertEquals(0, vector.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#size()} funzioni correttamente
     * mentre vengono aggiunti elementi.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#size()} funzioni correttamente
     * mentre vengono aggiunti elementi. Questo viene fatto invocando ripetutamente il metodo
     * {@link VectorAdapter#size()} mentre vengono inseriti degli elementi nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore, per {@code 10} iterazioni di un ciclo inserisce un
     * elemento nel vettore e verifica che la dimensione del vettore sia aumentata di {@code 1}
     * rispetto all'iterazione precendente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha dimensione {@code 10}.
     * </p>
     * <p>
     * <b>Expected Results</b> La dimensione aumenta di {@code 1} ad ogni iterazione.
     * </p>
     */
    @Test
    public void sizeChangesWhileAddingElements() {
        for (int i = 1; i <= 10; i++) {
            vector.add("A");
            assertEquals(i, vector.size());
        }
        assertEquals(10, vector.size());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#size()} funzioni correttamente
     * mentre vengono rimossi elementi.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#size()} funzioni correttamente
     * mentre vengono rimossi elementi. Questo viene fatto invocando ripetutamente il metodo
     * {@link VectorAdapter#size()} mentre vengono rimossi degli elementi dal vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di 10 elementi, per {@code 10} iterazioni di un ciclo
     * rimuove il primo elemento nel vettore e verifica che la dimensione del vettore sia diminuita
     * di {@code 1} rispetto all'iterazione precendente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di 10 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha dimensione {@code 0}.
     * </p>
     * <p>
     * <b>Expected Results</b> La dimensione diminuiesce di {@code 1} ad ogni iterazione.
     * </p>
     */
    @Test
    public void sizeChangesWhileRemovingElements() {
        for (int i = 1; i <= 10; i++) {
            vector.add("A");
        }
        for (int i = 1; i <= 10; i++) {
            vector.removeElementAt(0);
            assertEquals(10 - i, vector.size());
        }
        assertEquals(0, vector.size());
    }

    // ======== boolean isEmpty() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#isEmpty()} su un vettore vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#isEmpty()} ritorni
     * {@code true} quando invocato su un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore con il costruttore di default, chiama la funzione
     * {@link VectorAdapter#isEmpty()} e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#isEmpty()} ritorna {@code true}.
     * </p>
     */
    @Test
    public void isEmptyOnEmptyVector() {
        assertTrue(vector.isEmpty());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#isEmpty()} su un vettore non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#isEmpty()} ritorni
     * {@code false} quando invocato su un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore ed aggiunge un elemento, chiama la funzione
     * {@link VectorAdapter#isEmpty()} e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#isEmpty()} ritorna {@code false}.
     * </p>
     */
    @Test
    public void isEmptyOnNonEmptyVector() {
        vector.add("A");
        assertFalse(vector.isEmpty());
    }

    // ======== Enumeration elements() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#elements()} ritorni un oggetto di
     * tipo {@link Enumeration}.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#elements()} ritorni un oggetto di
     * tipo {@link Enumeration}. Test estensivi dell'implementazione di {@code EnumerationAdapter}
     * sono svolti nella test suite {@code EnumerationAdapterTester}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, chiama la funzione {@link VectorAdapter#elements()}
     * e verifica che un oggetto di tipo {@link Enumeration}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#elements()} ritorna un oggetto di tipo
     * {@link Enumeration}.
     * </p>
     */
    @Test
    public void elementsReturnsEnumeration() {
        Enumeration enumeration = vector.elements();
    }

    // ======== boolean contains(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#contains(Object)} quando l'elemento è
     * presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#contains(Object)} quando
     * l'elemento è presente nel vettore. In questo caso ci aspettiamo che ritorni {@code true}
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, chiama il metodo
     * {@link VectorAdapter#contains(Object)} con un'elemento che sappiamo essere presente nel
     * vettore e verifica che ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#contains(Object)} ritorna {@code true}
     * </p>
     */
    @Test
    public void containsReturnsTrue() {
        vector.add("A");

        assertTrue(vector.contains("A"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#contains(Object)} quando l'elemento
     * non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#contains(Object)} quando
     * l'elemento non è presente nel vettore. In questo caso ci aspettiamo che ritorni {@code false}
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, chiama il metodo
     * {@link VectorAdapter#contains(Object)} con un'elemento che sappiamo non essere presente nel
     * vettore e verifica che ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#contains(Object)} ritorna {@code false}
     * </p>
     */
    @Test
    public void containsReturnsFalse() {
        vector.add("A");

        assertFalse(vector.contains("B"));
    }

    // ======== int indexOf(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object)} restituisca
     * correttamente l'indice della prima occorrenza di un elemento presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object)} restituisca
     * correttamente l'indice della prima occorrenza di un elemento presente nel vettore. L'elemento
     * cercato è contenuto più volte nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore con più elementi, utilizza il metodo
     * {@link VectorAdapter#indexOf(Object)} per cercare un elemento contenuto più volte nel
     * vettore. Verifica che il metodo restituisca l'indice corretto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente almeno un elemento duplicato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice della prima occorrenza dell'elemento.
     * </p>
     */
    @Test
    public void indexOfElementFound() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("B");

        assertEquals(1, vector.indexOf("B"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nel vettore. Come descritto dalla
     * documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, utilizza il metodo
     * {@link VectorAdapter#indexOf(Object)} utilizzando come parametro un elemento non presente nel
     * vettore. Verifica che il metodo restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code -1} poiché l'elemento cercato non è
     * presente nel vettore.
     * </p>
     */
    @Test
    public void indexOfElementNotFound() {
        vector.add("A");
        vector.add("B");

        assertEquals(-1, vector.indexOf("X"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object)} gestisca
     * correttamente la ricerca di un elemento {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object)} gestisca
     * correttamente la ricerca di un elemento {@code null}. Se presente, deve restituire l'indice
     * della prima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, con più elementi {@code null}, quindi utilizza il
     * metodo {@code indexOf(Object)} con parametro {@code null}. Verifica che l'indice restituito
     * corrisponda alla prima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente più elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice della prima occorrenza di
     * {@code null}.
     * </p>
     */
    @Test
    public void indexOfNull() {
        vector.add("A");
        vector.add(null);
        vector.add("C");
        vector.add(null);

        assertEquals(1, vector.indexOf(null));
    }

    // ======== int indexOf(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)} lanci
     * un'eccezione se l'indice specificato è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} se l'indice specificato è negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto, invoca il metodo {@code indexOf(Object, -1)} . Verifica
     * che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata l'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void indexOfFromIndexThrowsIndexOutOfBoundsException() {
        vector.indexOf("A", -1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#indexOf(Object, int)} quando invocato
     * con un indice maggiore della dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#indexOf(Object, int)} quando
     * invocato con un indice maggiore della dimensione del vettore. In particolare ci si aspetta
     * che il metodo ritorni ({@code -1}).
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto di dimensione minore di 5, invoca il metodo
     * {@code indexOf(Object, 5)}. Verifica che il metodo ritorni {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#indexOf(Object, int)} ritorna
     * {@code -1}
     * </p>
     */
    @Test
    public void indexOfFromIndexWithIndexBiggerThanVector() {
        vector.add("A");
        assertEquals(-1, vector.indexOf("A", 5));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)} restituisca
     * correttamente l'indice dell'elemento specificato.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)}
     * restituisca correttamente l'indice della prima occorrenza di un elemento partendo da un
     * indice specifico.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto con elementi duplicati, quindi utilizza il metodo
     * {@link VectorAdapter#indexOf(Object, int)} per cercare un elemento duplicato partendo da un
     * indice intermedio. Verifica che il metodo restituisca l'indice del secondo elemento
     * duplicato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente almeno un elemento duplicato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice della prima occorrenza dell'elemento a
     * partire dall'indice specificato.
     * </p>
     */
    @Test
    public void indexOfFromIndexElementFound() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("B");

        assertEquals(3, vector.indexOf("B", 2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)}
     * restituisca {@code -1} quando l'elemento cercato non è presente nel vettore. Come descritto
     * dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, utilizza il metodo
     * {@link VectorAdapter#indexOf(Object, int)} utilizzando come parametro un elemento non
     * presente nel vettore. Verifica che il metodo restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code -1} poiché l'elemento cercato non è
     * presente nel vettore.
     * </p>
     */
    @Test
    public void indexOfFromIndexElementNotFound() {
        vector.add("A");
        vector.add("B");

        assertEquals(-1, vector.indexOf("X", 1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)} gestisca
     * correttamente la ricerca di un elemento {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#indexOf(Object, int)} gestisca
     * correttamente la ricerca di un elemento {@code null}. Se presente, deve restituire l'indice
     * della prima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, con più elementi {@code null}, quindi utilizza il
     * metodo {@code indexOf(Object, int)} con parametro {@code null}. Verifica che l'indice
     * restituito corrisponda alla prima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente più elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice della prima occorrenza di
     * {@code null}.
     * </p>
     */
    @Test
    public void indexOfFromIndexNull() {
        vector.add("A");
        vector.add(null);
        vector.add("C");
        vector.add(null);

        assertEquals(3, vector.indexOf(null, 2));
    }

    // ======== int lastIndexOf(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)} restituisca
     * correttamente l'indice dell'ultima occorrenza di un elemento presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)}
     * restituisca correttamente l'indice dell'ultima occorrenza di un elemento presente nel
     * vettore. L'elemento cercato è contenuto più volte nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore con più elementi, utilizza il metodo
     * {@link VectorAdapter#lastIndexOf(Object)} per cercare un elemento contenuto più volte nel
     * vettore. Verifica che il metodo restituisca l'indice corretto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente almeno un elemento duplicato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice dell'ultima occorrenza dell'elemento.
     * </p>
     */
    @Test
    public void lastIndexOfElementFound() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("B");

        assertEquals(3, vector.lastIndexOf("B"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)} restituisca
     * {@code -1} quando l'elemento cercato non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)}
     * restituisca {@code -1} quando l'elemento cercato non è presente nel vettore. Come descritto
     * dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, utilizza il metodo
     * {@link VectorAdapter#lastIndexOf(Object)} utilizzando come parametro un elemento non presente
     * nel vettore. Verifica che il metodo restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code -1} poiché l'elemento cercato non è
     * presente nel vettore.
     * </p>
     */
    @Test
    public void lastIndexOfElementNotFound() {
        vector.add("A");
        vector.add("B");

        assertEquals(-1, vector.lastIndexOf("X"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)} gestisca
     * correttamente la ricerca di un elemento {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object)} gestisca
     * correttamente la ricerca di un elemento {@code null}. Se presente, deve restituire l'indice
     * dell'ultima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, con più elementi {@code null}, quindi utilizza il
     * metodo {@code lastIndexOf(Object)} con parametro {@code null}. Verifica che l'indice
     * restituito corrisponda all'ultima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente più elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice dell'ultima occorrenza di
     * {@code null}.
     * </p>
     */
    @Test
    public void lastIndexOfNull() {
        vector.add("A");
        vector.add(null);
        vector.add("C");
        vector.add(null);

        assertEquals(3, vector.lastIndexOf(null));
    }

    // ======== int lastIndexOf(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)} lanci
     * un'eccezione se l'indice specificato è maggiore della dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * lanci un'eccezione {@link IndexOutOfBoundsException} se l'indice specificato è maggiore della
     * dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto, invoca il metodo
     * {@link VectorAdapter#lastIndexOf(Object, int)} con un indice maggiore della dimensione del
     * vettore. Verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata l'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void lastIndexOfFromIndexThrowsIndexOutOfBoundsException() {
        vector.lastIndexOf("A", 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)} ritorni
     * {@code -1} quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * ritorni {@code -1} quando invocato con un indice negativo. Come definito dalla
     * documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto, invoca il metodo
     * {@link VectorAdapter#lastIndexOf(Object, int)} con un indice negativo . Verifica che il
     * metodo ritorni {@code -1}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code -1}
     * </p>
     */
    @Test
    public void lastIndexOfNegativeIndex() {
        assertEquals(-1, vector.lastIndexOf("A", -1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * restituisca correttamente l'indice dell'elemento specificato.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * restituisca correttamente l'indice dell'ultima occorrenza di un elemento partendo da un
     * indice specifico.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto con elementi duplicati, quindi utilizza il metodo
     * {@link VectorAdapter#lastIndexOf(Object, int)} per cercare un elemento duplicato partendo da
     * un indice intermedio. Verifica che il metodo restituisca l'indice del primo elemento
     * duplicato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente almeno un elemento duplicato.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice della prima occorrenza dell'elemento a
     * partire dall'indice specificato.
     * </p>
     */
    @Test
    public void lastIndexOfFromIndexElementFound() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("B");

        assertEquals(1, vector.lastIndexOf("B", 2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * restituisca {@code -1} quando l'elemento cercato non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * restituisca {@code -1} quando l'elemento cercato non è presente nel vettore. Come descritto
     * dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, utilizza il metodo
     * {@link VectorAdapter#lastIndexOf(Object, int)} utilizzando come parametro un elemento non
     * presente nel vettore. Verifica che il metodo restituisca {@code -1}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code -1} poiché l'elemento cercato non è
     * presente nel vettore.
     * </p>
     */
    @Test
    public void lastIndexOfFromIndexElementNotFound() {
        vector.add("A");
        vector.add("B");

        assertEquals(-1, vector.indexOf("X", 1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)} gestisca
     * correttamente la ricerca di un elemento {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastIndexOf(Object, int)}
     * gestisca correttamente la ricerca di un elemento {@code null}. Se presente, deve restituire
     * l'indice dell'ultima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, con più elementi {@code null}, quindi utilizza il
     * metodo {@code lastIndexOf(Object, int)} con parametro {@code null}. Verifica che l'indice
     * restituito corrisponda all'ultima occorrenza di {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto contenente più elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce l'indice dell'ultima occorrenza di
     * {@code null}.
     * </p>
     */
    @Test
    public void lastIndexOfFromIndexNull() {
        vector.add("A");
        vector.add(null);
        vector.add("C");
        vector.add(null);

        assertEquals(1, vector.lastIndexOf(null, 2));
    }

    // ======== Object elementAt(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#elementAt(int)} lanci un'eccezione
     * quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#elementAt(int)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando invocato con un indice negativo.
     * L'operazione non dovrebbe modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto. Invoca il metodo {@code elementAt(-1)}. Verifica
     * che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore instanziato vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void elementAtThrowsArrayIndexOutOfBoundsException() {
        assertEquals(0, vector.size());
        vector.elementAt(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#elementAt(int)} lanci un'eccezione
     * quando invocato con un indice uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#elementAt(int)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando invocato con un indice uguale alla
     * dimensione del vettore. L'operazione non dovrebbe modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento. Invoca il metodo {@code elementAt(1)}.
     * Verifica che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore con un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void elementAtThrowsArrayIndexOutOfBoundsException2() {
        vector.add("A");

        vector.elementAt(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#elementAt(int)} funzioni
     * correttamente.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#elementAt(int)} funzioni
     * correttamente. Questo viene fatto invocandolo in vari indici di un vettore e verificando che
     * ritorni i valori corretti, senza modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 3} elementi. Invoca il metodo
     * {@link VectorAdapter#elementAt(int)} {@code 3} volte e verifica che gli elementi ritornati
     * siano corretti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore instanziato di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#elementAt(int)} ritorna gli elementi
     * corretti.
     * </p>
     */
    @Test
    public void elementAtOnVectorWithElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        assertEquals("A", vector.elementAt(0));
        assertEquals("B", vector.elementAt(1));
        assertEquals("C", vector.elementAt(2));
    }

    // ======== Object firstElement() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#firstElement()} lanci un'eccezione
     * quando il vettore è vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#firstElement()} lanci
     * un'eccezione {@link NoSuchElementException} quando il vettore è vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto e invoca il metodo
     * {@link VectorAdapter#firstElement()}, verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link NoSuchElementException}.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void firstElementThrowsNoSuchElementException() {
        vector.firstElement();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#firstElement()} restituisca il
     * primo elemento di un vettore non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#firstElement()} restituisca il
     * primo elemento di un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto di {@code 2} elementi, verifica che
     * {@link VectorAdapter#firstElement()} restituisca il primo elemento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 2} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce correttamente il primo elemento del vettore.
     * </p>
     */
    @Test
    public void firstElementReturnsFirstElement() {
        vector.add("A");
        vector.add("B");

        assertEquals("A", vector.firstElement());
    }

    // ======== Object lastElement() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastElement()} lanci un'eccezione
     * quando il vettore è vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastElement()} lanci
     * un'eccezione {@link NoSuchElementException} quando il vettore è vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto e invoca il metodo
     * {@link VectorAdapter#lastElement()}, verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link NoSuchElementException}.
     * </p>
     */
    @Test(expected = NoSuchElementException.class)
    public void lastElementThrowsNoSuchElementException() {
        vector.lastElement();
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#lastElement()} restituisca
     * l'ultimo elemento di un vettore non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#lastElement()} restituisca
     * l'ultimo elemento di un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto di {@code 2} elementi, verifica che
     * {@link VectorAdapter#lastElement()} restituisca l'ultimo elemento.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 2} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce correttamente l'ultimo elemento del vettore.
     * </p>
     */
    @Test
    public void lastElementReturnsFirstElement() {
        vector.add("A");
        vector.add("B");

        assertEquals("B", vector.lastElement());
    }

    // ======== void setElementAt(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#setElementAt(Object, int)} lanci
     * un'eccezione quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#setElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, invoca {@code setElement("A", -1)} e verifica che
     * venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setElementAtThrowsArrayIndexOutOfBoundsException() {
        vector.setElementAt("A", -1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#setElementAt(Object, int)} lanci
     * un'eccezione quando l'indice fornito è maggiore o uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#setElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è maggiore
     * o uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, invoca
     * {@link VectorAdapter#setElementAt(Object, int)} con un indice uguale alla dimensione del
     * vettore e verifica che venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setElementAtThrowsArrayIndexOutOfBoundsException2() {
        vector.add("A");
        vector.add("B");

        vector.setElementAt("C", 2);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#setElementAt(Object, int)} imposti
     * correttamente il valore di un elemento a un indice valido.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#setElementAt(Object, int)}
     * imposti correttamente il valore di un elemento a un indice valido. Questo viene verificato
     * con un vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, invoca
     * {@link VectorAdapter#setElementAt(Object, int)} su uno degli elementi e verifica che il
     * valore sia stato correttamente modificato all'indice specificato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto
     * </p>
     * <p>
     * <b>Post-Condition</b> Il valore dell'elemento all'indice specificato è stato aggiornato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il valore all'indice specificato viene aggiornato con il nuovo
     * valore.
     * </p>
     */
    @Test
    public void setElementAtUpdatesValue() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        vector.setElementAt("X", 1);

        assertEquals("X", vector.get(1));
    }

    // ======== void removeElementAt(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeElementAt(int)} lanci
     * un'eccezione se l'indice è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeElementAt(int)} sollevi
     * un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, poi chiama {@code removeElementAt(-1)} e verifica
     * che venga lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Ul vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'invocazione {@code removeElementAt(-1)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementAtThrowsArrayIndexOutOfBoundsException() {
        vector.removeElementAt(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeElementAt(int)} lanci
     * un'eccezione se l'indice è maggiore o uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeElementAt(int)} sollevi
     * un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è maggiore o
     * uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento, poi chiama {@code removeElementAt(1)} e
     * verifica che venga lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene sollevata un'eccezione e il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeElementAtThrowsArrayIndexOutOfBoundsException2() {
        vector.add("A");

        vector.removeElementAt(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeElementAt(int)} rimuova
     * correttamente gli elementi da una lista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeElementAt(int)} rimuova
     * correttamente gli elementi da una lista. In particolare da una lista di {@code 3} elementi
     * viene prima rimosso quello centrale, poi l'ultimo e infine il primo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 3} elementi. Rimuove poi prima quello centrale
     * ({@code removeElementAt(1)}), poi l'ultimo ({@code removeElementAt(1)}) e infine il
     * primo({@code removeElementAt(0)}). Lo stato viene verificato dopo ogni rimozione per
     * verificare che sia consistente con quanto atteso.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore è vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo ogni chiamata di {@code removeElementAt} la lista diminuisce la
     * sua dimensione di 1 e viene rimosso il corretto elemento.
     * </p>
     */
    @Test
    public void removeElementAtRemovesAllElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        vector.removeElementAt(1);

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("C", vector.get(1));

        vector.removeElementAt(1);

        assertEquals(1, vector.size());
        assertEquals("A", vector.get(0));

        vector.removeElementAt(0);

        assertEquals(0, vector.size());
    }

    // ======== void insertElementAt(Object, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione se invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto. Invoca il metodo
     * {@link VectorAdapter#insertElementAt(Object, int)} utilizzando come indice {@code -1},
     * verifica che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#insertElementAt(Object, int)} lancia
     * un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void insertElementAtThrowsArrayIndexOutOfBoundsException() {
        vector.insertElementAt("A", -1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione se invocato con un indice maggiore della dimensione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * lanci un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * maggiore della dimensione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto. Invoca il metodo {@code insertElementAt("A", 5)},
     * verifica che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#insertElementAt(Object, int)} lancia
     * un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void insertElementAtThrowsArrayIndexOutOfBoundsException2() {
        vector.insertElementAt("A", 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * aggiunga correttamente gli elementi su un vettore.
     * </p>
     * <p>
     * <b>Description</b>Verifica che il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * aggiunga correttamente gli elementi su un vettore. Viene verificato che il metodo riesca ad
     * aggiungere elementi all'inizio, alla fine e nel mezzo di un vettore.
     * </p>
     * <p>
     * <b>Design</b> Istanzia un vettore vuoto, aggiunge un elemento nel vettore ({@code "X"}), poi
     * uno prima ({@code "A"}) ed uno dopo ({@code "B"}) di esso. Verifica poi che la lunghezza del
     * vettore sia 3 e che gli elementi siano nell'ordine atteso ({@code ["A", "X", "B"]}).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore contiene 3 elementi nell'ordine atteso.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#insertElementAt(Object, int)}
     * inserisce correttamente gli elementi.
     * </p>
     */
    @Test
    public void insertElementAtAtIndexOnNonEmpty() {
        vector.insertElementAt("X", 0);
        vector.insertElementAt("A", 0);
        vector.insertElementAt("B", 2);

        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("X", vector.get(1));
        assertEquals("B", vector.get(2));
    }

    // ======== void addElement(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addElement(Object)} aggiunga
     * correttamente elementi al termine del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addElement(Object)} aggiunga
     * correttamente elementi al termine del vettore. Questo viene fatto aggiungendo diversi
     * elementi e verificando che l'ultimo elemento del vettore sia sempre quello aggiunto più di
     * recente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, mediante un ciclo di {@code 10} iterazioni aggiunge
     * elementi (in particolare aggiunge in ordine 1, 2, 3, ..., 10) e verifica che l'ultimo
     * elemento del vector sia lo stesso elemento appena aggiunto. Verifica inoltre che la
     * dimensione del vettore aumenti di {@code 1} ad ogni invocazione.
     *
     * Infine verifica che l'ordine sia mantenuto, il vettore finale deve contenere tutti i numeri
     * da 1 a 10 in ordine crescente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore contiene {@code 10} elementi, i numeri da 1 a 10 in ordine
     * crescente.
     * </p>
     * <p>
     * <b>Expected Results</b> Ad ogni iterazione lo stato del vettore è quello atteso.
     * </p>
     */
    @Test
    public void addElementAddsElementAtTheEndOfVector() {
        for (int i = 1; i <= 10; i++) {
            vector.addElement(i);
            assertEquals(i, vector.lastElement());
            assertEquals(i, vector.size());
        }
        assertEquals(10, vector.size());
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, vector.get(i - 1));
        }
    }

    // ======== boolean removeElement(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeElement(Object obj)} rimuova
     * solamente la prima occorrenza dell'oggetto specificato nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeElement(Object obj)}
     * rimuova solamente la prima occorrenza dell'oggetto specificato nel vettore. Per farlo invoca
     * il metodo su un vettore con elementi duplucati, con uno di questi come parametro.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore contenente elemnti duplicati. Invoca
     * {@link VectorAdapter#removeElement(Object obj)} su uno degli elementi duplicati e verifica
     * che la dimensione del vettore sia ridotta e che la prima occorrenza dell'elemento venga
     * rimossa.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore contenente elementi duplicati.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha una sola occorrenza in meno dell'elemento specificato.
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

        assertTrue(vector.removeElement("A"));

        assertEquals(2, vector.size());
        assertEquals("B", vector.get(0));
        assertEquals("A", vector.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il metodo {@link VectorAdapter#removeElement(Object obj)} quando
     * l'oggetto da rimuovere non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeElement(Object obj)}
     * ritorni {@code false} quando l'oggetto da rimuovere non è presente nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, invoca il metodo
     * {@link VectorAdapter#removeElement(Object obj)} su un oggetto che sappiamo non essere nel
     * vettore. Verifica che il vettore sia rimasto invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato e il metodo ritorna {@code false}.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code false} e il vettore rimane invariato.
     * </p>
     */
    @Test
    public void removeElementNotPresent() {
        vector.add("A");
        vector.add("B");

        assertFalse(vector.removeElement("X"));

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeElement(Object obj)} rimuova
     * correttamente un elemento {@code null} presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeElement(Object obj)}
     * rimuova correttamente un elemento {@code null} presente nel vettore. Verifica inoltre che ne
     * rimuova solo un'occorrenza.
     * </p>
     * <p>
     * <b>Design</b> Instanzia il vettore con alcuni elementi, due dei quali sono {@code null}.
     * Invoca il metodo {@link VectorAdapter#removeElement(Object obj)} con {@code null} come
     * parametro e verifica che la dimensione venga ridotta e che l'elemento {@code null} sia stato
     * rimosso correttamente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore che contiene almeno due elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha un elemento {@code null} in meno.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code true} e l'elemento {@code null} viene
     * rimosso correttamente.
     * </p>
     */
    @Test
    public void removeElementNull() {
        vector.add("A");
        vector.add(null);
        vector.add(null);

        assertTrue(vector.removeElement(null));

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertNull(vector.get(1));
    }

    // ======== void removeAllElements() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeAllElements()} rimuova
     * correttamente gli elementi dal vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeAllElements()} rimuova
     * correttamente gli elementi dal vettore. Per fare questa verifica viene utilizzato il metodo
     * {@link VectorAdapter#isEmpty()}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento. Invoca il metodo
     * {@code removeAllElements()}. Verifica che il vettore sia vuoto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore con un elemento non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#removeAllElements()} il
     * vettore è vuoto.
     * </p>
     */
    @Test
    public void removeAllElementsEmptiesVector() {
        vector.add("A");

        assertFalse(vector.isEmpty());

        vector.removeAllElements();

        assertTrue(vector.isEmpty());
    }

    // ======== Object clone() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#clone()} restituisca un nuovo
     * oggetto che contiene gli stessi elementi del vettore originale.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#clone()} restituisca un nuovo
     * oggetto che contiene gli stessi elementi del vettore originale, nello stesso ordine.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, lo clona e verifica che la copia sia della
     * stessa dimensione del vettore originale e che contenga gli stessi elementi del vettore
     * originale grazie al metodo {@link VectorAdapter#get(int)}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> La copia del vettore contiene gli stessi elementi dell'originale.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#clone()} genera una copia del vettore.
     * </p>
     */
    @Test
    public void cloneClonesVector() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        VectorAdapter clonedVector = (VectorAdapter) vector.clone();

        assertEquals(vector.size(), clonedVector.size());
        assertEquals(vector.get(0), clonedVector.get(0));
        assertEquals(vector.get(1), clonedVector.get(1));
        assertEquals(vector.get(2), clonedVector.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#clone()} crei un nuovo oggetto le
     * cui modifiche non sono riflesse sull'originale.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#clone()} crei un nuovo oggetto
     * le cui modifiche non sono riflesse sull'originale. Verifica quindi che la copia sia di tipo
     * {@code deep}.
     * </p>
     * <p>
     * <b>Design</b> Crea un copia non vuota instanziata da un vettore non vuoto, aggiunge poi un
     * elemento alla copia e verifica che esso non sia presente nel vettore originale.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore e una copia non vuoti.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore originale e la copia hanno contenuti differenti dopo una
     * modifica.
     * </p>
     * <p>
     * <b>Expected Results</b> Le modifiche alla copia non influenzano il vettore originale.
     * </p>
     */
    @Test
    public void cloneDeepCopies() {
        vector.add("A");
        vector.add("B");
        VectorAdapter clonedVector = (VectorAdapter) vector.clone();

        clonedVector.add("X");

        assertEquals(2, vector.size());
        assertFalse(vector.contains("X"));
        assertEquals(3, clonedVector.size());
    }

    // ======== Object[] toArray() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toArray()} restituisca un array
     * contenente gli elementi presenti nel vettore.
     * </p>
     * <p>
     * <b>Description</b> VVerifica che il metodo {@link VectorAdapter#toArray()} restituisca un
     * array contenente gli elementi presenti nel vettore, nell'ordine corretto.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore di tre elementi, invoca il metodo
     * {@link VectorAdapter#toArray()} e verifica che l'array restituito contenga gli stessi
     * elementi nello stesso ordine.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'array restituito contiene esattamente gli stessi elementi del
     * vettore, nello stesso ordine.
     * </p>
     */
    @Test
    public void toArrayMultipleElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        Object[] array = vector.toArray();

        assertEquals(3, array.length);
        assertEquals("A", array[0]);
        assertEquals("B", array[1]);
        assertEquals("C", array[2]);
    }

    // ======== Object[] toArray(Object[]) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} lanci
     * un'eccezione se l'array fornito è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} lanci
     * un'eccezione {@link NullPointerException} se l'array fornito è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto, poi invoca {@code toArray(null)} e verifica che venga
     * lanciata un'eccezione di tipo {@link NullPointerException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void toArrayThrowsNullPointerException() {
        vector.toArray(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} lanci
     * un'eccezione se il tipo runtime dell'array fornito non è compatibile con il tipo degli
     * elementi del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} lanci
     * un'eccezione {@link ArrayStoreException} se il tipo runtime dell'array fornito non è
     * compatibile con il tipo degli elementi del vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore con elementi di tipo {@link String}, chiama
     * {@link VectorAdapter#toArray(Object[])} passando un array di tipo incompatibile
     * ({@link Integer}) e verifica che venga lanciata un'eccezione {@link ArrayStoreException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore contenente elementi di tipo {@link String}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link ArrayStoreException}.
     * </p>
     */
    @Test(expected = ArrayStoreException.class)
    public void testToArrayIncompatibleTypeArray() {
        vector.add("A");

        Integer[] array = new Integer[2]; // Incompatible array type

        vector.toArray(array);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione uguale a quella del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione uguale a quella del vettore. Ci si
     * aspetta quindi che gli elementi coincidano indice per indice.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore, chiama {@link VectorAdapter#toArray(Object[])} passando
     * un array della stessa dimensione del vettore, verifica che gli elementi siano stati copiati
     * nell'array fornito nell'ordine corretto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore contenente elementi di tipo {@link String}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'array fornito viene popolato correttamente con gli elementi del
     * vettore e non viene allocato un nuovo array.
     * </p>
     */
    @Test
    public void toArrayCorrectSizeArray() {
        vector.add("A");
        vector.add("B");
        String[] array = new String[2];

        Object[] resultArray = vector.toArray(array);

        assertSame(array, resultArray);
        assertEquals("A", resultArray[0]);
        assertEquals("B", resultArray[1]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione maggiore a quella del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione maggiore a quella del vettore. Ci si
     * aspetta che gli elementi di troppo vengano impostati a {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Istanzia un vettore non vuoto, chiama {@link VectorAdapter#toArray(Object[])}
     * passando un array di dimensione maggiore del vettore, verifica che gli elementi in eccesso
     * dell'array vengano impostati a {@code null}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore contenente elementi di tipo {@link String}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Gli elementi vengono copiati nell'array, quelli in eccesso nell'array
     * vengono impostati a {@code null}.
     * </p>
     */
    @Test
    public void toArrayOversizedArray() {
        vector.add("A");
        vector.add("B");
        String[] array = new String[3];

        Object[] resultArray = vector.toArray(array);

        assertSame(array, resultArray);
        assertEquals("A", resultArray[0]);
        assertEquals("B", resultArray[1]);
        assertNull(resultArray[2]);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione minore a quella del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toArray(Object[])} popoli
     * correttamente l'array fornito quando ha una dimensione minore a quella del vettore, verifica
     * che l'array venga sostituito con un nuovo array di dimensione sufficiente.
     * </p>
     * <p>
     * <b>Design</b>Istanzia un vettore non vuoto, chiama {@link VectorAdapter#toArray(Object[])}
     * passando un array di dimensione minore del vettore, verifica che l'array venga sostituito con
     * un nuovo array di dimensione sufficiente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore contenente elementi di tipo {@link String}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene creato un nuovo array con la dimensione corretta e gli elementi
     * del vettore vengono copiati correttamente in esso.
     * </p>
     */
    @Test
    public void toArrayUndersizedArray() {
        vector.add("A");
        vector.add("B");
        String[] array = new String[1];

        Object[] resultArray = vector.toArray(array);

        assertNotSame(array, resultArray);
        assertEquals(2, resultArray.length);
        assertEquals("A", resultArray[0]);
        assertEquals("B", resultArray[1]);
    }

    // ======== Object get(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#get(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#get(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando invocato con un indice negativo. L'operazione
     * non dovrebbe modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto. Invoca il metodo {@code get(-1)}. Verifica che
     * venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore instanziato vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getThrowsExceptionForNegativeIndex() {
        assertEquals(0, vector.size());
        vector.get(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#get(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando invocato con un indice uguale alla dimensione
     * del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#get(int)} lanci un'eccezione
     * {@link ArrayIndexOutOfBoundsException} quando invocato con un indice uguale alla dimensione
     * del vettore. L'operazione non dovrebbe modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento. Invoca il metodo {@code get(1)}. Verifica
     * che venga lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore con un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link ArrayIndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getThrowsExceptionForIndexGreaterThanSize() {
        vector.add("A");

        vector.get(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#get(int)} funzioni correttamente.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#get(int)} funzioni
     * correttamente. Questo viene fatto invocandolo in vari indici di un vettore e verificando che
     * ritorni i valori corretti, senza modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 3} elementi. Invoca il metodo
     * {@link VectorAdapter#get(int)} {@code 3} volte e verifica che gli elementi ritornati siano
     * corretti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore instanziato di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#get(int)} ritorna gli elementi
     * corretti.
     * </p>
     */
    @Test
    public void getOnVectorWithElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("C", vector.get(2));
    }

    // ======== Object set(int, Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#set(int, Object)} lanci
     * un'eccezione quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#set(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, invoca {@code setElement("A", -1)} e verifica che
     * venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setThrowsArrayIndexOutOfBoundsException() {
        vector.set(-1, "A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#set(int, Object)} lanci
     * un'eccezione quando l'indice fornito è maggiore o uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#set(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando l'indice fornito è maggiore o
     * uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, invoca {@link VectorAdapter#set(int, Object)}
     * con un indice uguale alla dimensione del vettore e verifica che venga lanciata l'eccezione
     * corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia una {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setThrowsArrayIndexOutOfBoundsException2() {
        vector.add("A");
        vector.add("B");

        vector.set(2, "C");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#set(int, Object)} imposti
     * correttamente il valore di un elemento a un indice valido.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#set(int, Object)} imposti
     * correttamente il valore di un elemento a un indice valido. Questo viene verificato con un
     * vettore non vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, invoca {@link VectorAdapter#set(int, Object)}
     * su uno degli elementi e verifica che il valore sia stato correttamente modificato all'indice
     * specificato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto
     * </p>
     * <p>
     * <b>Post-Condition</b> Il valore dell'elemento all'indice specificato è stato aggiornato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il valore all'indice specificato viene aggiornato con il nuovo
     * valore.
     * </p>
     */
    @Test
    public void setUpdatesValue() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        vector.set(1, "X");

        assertEquals("X", vector.get(1));
    }

    // ======== boolean add(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#add(Object)} aggiunga
     * correttamente elementi al termine del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#add(Object)} aggiunga
     * correttamente elementi al termine del vettore. Questo viene fatto aggiungendo diversi
     * elementi e verificando che l'ultimo elemento del vettore sia sempre quello aggiunto più di
     * recente.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, mediante un ciclo di {@code 10} iterazioni aggiunge
     * elementi (in particolare aggiunge in ordine 1, 2, 3, ..., 10) e verifica che l'ultimo
     * elemento del vector sia lo stesso elemento appena aggiunto. Verifica inoltre che la
     * dimensione del vettore aumenti di {@code 1} ad ogni invocazione.
     *
     * Infine verifica che l'ordine sia mantenuto, il vettore finale deve contenere tutti i numeri
     * da 1 a 10 in ordine crescente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore contiene {@code 10} elementi, i numeri da 1 a 10 in ordine
     * crescente.
     * </p>
     * <p>
     * <b>Expected Results</b> Ad ogni iterazione lo stato del vettore è quello atteso.
     * </p>
     */
    @Test
    public void addAddsElementAtTheEndOfVector() {
        for (int i = 1; i <= 10; i++) {
            vector.add(i);
            assertEquals(i, vector.lastElement());
            assertEquals(i, vector.size());
        }
        assertEquals(10, vector.size());
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, vector.get(i - 1));
        }
    }

    // ======== boolean remove(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#remove(Object obj)} rimuova
     * solamente la prima occorrenza dell'oggetto specificato nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#remove(Object obj)} rimuova
     * solamente la prima occorrenza dell'oggetto specificato nel vettore. Per farlo invoca il
     * metodo su un vettore con elementi duplucati, con uno di questi come parametro.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore contenente elemnti duplicati. Invoca
     * {@link VectorAdapter#remove(Object obj)} su uno degli elementi duplicati e verifica che la
     * dimensione del vettore sia ridotta e che la prima occorrenza dell'elemento venga rimossa.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore contenente elementi duplicati.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha una sola occorrenza in meno dell'elemento specificato.
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

        assertTrue(vector.remove("A"));

        assertEquals(2, vector.size());
        assertEquals("B", vector.get(0));
        assertEquals("A", vector.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il metodo {@link VectorAdapter#remove(Object obj)} quando l'oggetto
     * da rimuovere non è presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#remove(Object obj)} ritorni
     * {@code false} quando l'oggetto da rimuovere non è presente nel vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, invoca il metodo
     * {@link VectorAdapter#remove(Object obj)} su un oggetto che sappiamo non essere nel vettore.
     * Verifica che il vettore sia rimasto invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato e il metodo ritorna {@code false}.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code false} e il vettore rimane invariato.
     * </p>
     */
    @Test
    public void removeNotPresent() {
        vector.add("A");
        vector.add("B");

        assertFalse(vector.remove("X"));

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#remove(Object obj)} rimuova
     * correttamente un elemento {@code null} presente nel vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#remove(Object obj)} rimuova
     * correttamente un elemento {@code null} presente nel vettore. Verifica inoltre che ne rimuova
     * solo un'occorrenza.
     * </p>
     * <p>
     * <b>Design</b> Instanzia il vettore con alcuni elementi, due dei quali sono {@code null}.
     * Invoca il metodo {@link VectorAdapter#remove(Object obj)} con {@code null} come parametro e
     * verifica che la dimensione venga ridotta e che l'elemento {@code null} sia stato rimosso
     * correttamente.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore che contiene almeno due elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore ha un elemento {@code null} in meno.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code true} e l'elemento {@code null} viene
     * rimosso correttamente.
     * </p>
     */
    @Test
    public void removeNull() {
        vector.add("A");
        vector.add(null);
        vector.add(null);

        assertTrue(vector.remove(null));

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertNull(vector.get(1));
    }

    // ======== void add(int, Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#add(int, Object)} lanci
     * un'eccezione se invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#add(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto. Invoca il metodo {@link VectorAdapter#add(int, Object)}
     * utilizzando come indice {@code -1}, verifica che venga lanciata un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#add(int, Object)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAtThrowsArrayIndexOutOfBoundsException() {
        vector.add(-1, "A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#add(int, Object)} lanci
     * un'eccezione se invocato con un indice maggiore della dimensione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#add(int, Object)} lanci
     * un'eccezione {@link ArrayIndexOutOfBoundsException} quando viene invocato con un indice
     * maggiore della dimensione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto. Invoca il metodo {@code add("A", 5)}, verifica che venga
     * lanciata un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#add(int, Object)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAtThrowsArrayIndexOutOfBoundsException2() {
        vector.add(1, "A");
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#add(int, Object)} aggiunga
     * correttamente gli elementi su un vettore.
     * </p>
     * <p>
     * <b>Description</b>Verifica che il metodo {@link VectorAdapter#add(int, Object)} aggiunga
     * correttamente gli elementi su un vettore. Viene verificato che il metodo riesca ad aggiungere
     * elementi all'inizio, alla fine e nel mezzo di un vettore.
     * </p>
     * <p>
     * <b>Design</b> Istanzia un vettore vuoto, aggiunge un elemento nel vettore ({@code "X"}), poi
     * uno prima ({@code "A"}) ed uno dopo ({@code "B"}) di esso. Verifica poi che la lunghezza del
     * vettore sia 3 e che gli elementi siano nell'ordine atteso ({@code ["A", "X", "B"]}).
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore contiene 3 elementi nell'ordine atteso.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#add(int, Object)} inserisce
     * correttamente gli elementi.
     * </p>
     */
    @Test
    public void addAtAtIndexOnNonEmpty() {
        vector.add(0, "X");
        vector.add(0, "A");
        vector.add(2, "B");

        assertEquals(3, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("X", vector.get(1));
        assertEquals("B", vector.get(2));
    }

    // ======== Object remove(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#remove(int)} lanci un'eccezione se
     * l'indice è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#remove(int)} sollevi
     * un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, poi chiama {@code remove(-1)} e verifica che venga
     * lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Ul vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'invocazione {@code remove(-1)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeThrowsArrayIndexOutOfBoundsException() {
        vector.remove(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#remove(int)} lanci un'eccezione se
     * l'indice è maggiore o uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#remove(int)} sollevi
     * un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è maggiore o
     * uguale alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento, poi chiama {@code remove(1)} e verifica
     * che venga lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene sollevata un'eccezione e il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeThrowsArrayIndexOutOfBoundsException2() {
        vector.add("A");

        vector.remove(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#remove(int)} rimuova correttamente
     * gli elementi da una lista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#remove(int)} rimuova
     * correttamente gli elementi da una lista. In particolare da una lista di {@code 3} elementi
     * viene prima rimosso quello centrale, poi l'ultimo e infine il primo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 3} elementi. Rimuove poi prima quello centrale
     * ({@code remove(1)}), poi l'ultimo ({@code remove(1)}) e infine il primo({@code remove(0)}).
     * Lo stato viene verificato dopo ogni rimozione per verificare che sia consistente con quanto
     * atteso.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore è vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo ogni chiamata di {@code remove} la lista diminuisce la sua
     * dimensione di 1 e viene rimosso il corretto elemento.
     * </p>
     */
    @Test
    public void removeRemovesAllElements() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        vector.remove(1);

        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("C", vector.get(1));

        vector.remove(1);

        assertEquals(1, vector.size());
        assertEquals("A", vector.get(0));

        vector.remove(0);

        assertEquals(0, vector.size());
    }

    // ======== void clear() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#clear()} rimuova correttamente gli
     * elementi dal vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#clear()} rimuova correttamente
     * gli elementi dal vettore. Per fare questa verifica viene utilizzato il metodo
     * {@link VectorAdapter#isEmpty()}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento. Invoca il metodo {@code clear()}. Verifica
     * che il vettore sia vuoto.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore con un elemento non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#clear()} il vettore è vuoto.
     * </p>
     */
    @Test
    public void clearEmptiesVector() {
        vector.add("A");

        assertFalse(vector.isEmpty());

        vector.clear();

        assertTrue(vector.isEmpty());
    }

    // ======== boolean containsAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)} lanci
     * un'eccezione se l'array fornito è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)}
     * lanci un'eccezione {@link NullPointerException} se l'array fornito è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto, poi invoca {@code containsAll(null)} e verifica che
     * venga lanciata un'eccezione di tipo {@link NullPointerException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Viene lanciata un'eccezione {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void containsAllThrowsNullPointerException() {
        vector.containsAll(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)}
     * restituisca {@code true} quando il vettore contiene tutti gli elementi della collezione
     * specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)}
     * restituisca {@code true} quando il vettore contiene tutti gli elementi della collezione
     * specificata indipendentemente dall'ordine degli elementi.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e una collezione con un sottoinsieme degli elementi
     * presenti nel vettore. Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)}
     * restituisca {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una collezione contenente un sottoinsieme degli
     * elementi del vettore.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la collezione rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true}.
     * </p>
     */
    @Test
    public void containsAllElementsPresent() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        HCollection collection = new VectorAdapter();
        collection.add("A");
        collection.add("C");

        assertTrue(vector.containsAll(collection));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)}
     * restituisca {@code false} quando il vettore non contiene tutti gli elementi della collezione
     * specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)}
     * restituisca {@code false} quando il vettore non contiene tutti gli elementi della collezione
     * specificata indipendentemente dall'ordine degli elementi.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e una collezione con elementi non presenti nel
     * vettore. Verifica che il metodo {@link VectorAdapter#containsAll(HCollection)} restituisca
     * {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una collezione contenente elementi non presenti
     * nel vettore.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore e la collezione rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code false}.
     * </p>
     */
    @Test
    public void containsAllElementsNotPresent() {
        vector.add("A");
        vector.add("B");
        vector.add("C");

        HCollection collection = new VectorAdapter();
        collection.add("A");
        collection.add("X");

        assertFalse(vector.containsAll(collection));
    }

    // ======== boolean addAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(HCollection)} lanci
     * un'eccezione quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(HCollection)} lanci
     * un'eccezione {@link NullPointerException} quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore buoto e invoca il metodo {@code addAll(null)}, verifica che
     * venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia l'eccezione {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void addAllThrowsNullPointerException() {
        vector.addAll(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(HCollection)}
     * aggiungacorrettamente tutti gli elementi dalla collezione specificata.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(HCollection)} aggiunga
     * correttamente tutti gli elementi dalla collezione specificata. Verifica che tutti gli
     * elementi della collezione siano aggiunti correttamente alla fine del vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e una collezione separata. Invoca
     * {@link VectorAdapter#addAll(HCollection)} e verifica che tutti gli elementi della collezione
     * siano presenti alla fine del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una collezione non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore contiene tutti gli elementi precedenti più quelli della
     * collezione aggiunta.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true} e tutti gli elementi della
     * collezione vengono aggiunti alla fine del vettore.
     * </p>
     */
    @Test
    public void addAllonNonEmptyVector() {
        vector.add("X");
        vector.add("Y");

        HCollection collection = new VectorAdapter();
        collection.add("A");
        collection.add("B");

        assertTrue(vector.addAll(collection));
        assertEquals(4, vector.size());
        assertEquals("X", vector.get(0));
        assertEquals("Y", vector.get(1));
        assertEquals("A", vector.get(2));
        assertEquals("B", vector.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica il comportamento del metodo {@link VectorAdapter#addAll(HCollection)}
     * quando l'argomento è il vettore stesso.
     * </p>
     * <p>
     * <b>Description</b> Verifica il comportamento del metodo
     * {@link VectorAdapter#addAll(HCollection)} quando l'argomento è il vettore stesso. Ci si
     * aspetta gli elementi del vettore risultino duplicati.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto ed invoca {@code addAll(vector)} su sé stesso,
     * verifica che non ci siano eccezioni e che gli elementi del vettore risultino siano duplicati.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il contenuto del vettore viene duplicato.
     * </p>
     * <p>
     * <b>Expected Results</b> Nessuna eccezione viene lanciata e il contenuto del vettore viene
     * duplicato.
     * </p>
     */
    @Test
    public void addAllOnSelf() {
        vector.add("A");
        vector.add("B");

        assertTrue(vector.addAll(vector));
        assertEquals(4, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
        assertEquals("A", vector.get(2));
        assertEquals("B", vector.get(3));
    }

    // ======== boolean removeAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeAll(HCollection)} lanci
     * un'eccezione quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeAll(HCollection)} lanci
     * un'eccezione {@link NullPointerException} quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto e invoca il metodo {@code removeAll(null)}. Verifica che
     * venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void removeAllThrowsNullPointerException() {
        vector.removeAll(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeAll(HCollection)} rimuova
     * dal vettore tutti gli elementi specificati nella collezione.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeAll(HCollection)}
     * rimuova dal vettore tutti gli elementi specificati nella collezione e verifica che ritorni
     * {@code true}. Viene anche verificato che vengano rimossi elementi duplicati.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e una collezione contenente un sottoinsieme degli
     * elementi del vettore. Invoca {@link VectorAdapter#removeAll(HCollection)} e verifica che
     * tutti gli elementi in comune vengano rimossi dal vettore. Inoltre verifica che il metodo
     * ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una collezione contenente elementi da rimuovere.
     * </p>
     * <p>
     * <b>Post-Condition</b> Gli elementi della collezione vengono rimossi dal vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true} e gli elementi in comune tra la
     * collezione e il vettore vengono rimossi.
     * </p>
     */
    @Test
    public void removeAllReturnsTrue() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("B");

        HCollection collection = new VectorAdapter();
        collection.add("B");
        collection.add("C");

        assertTrue(vector.removeAll(collection));

        assertEquals(1, vector.size());
        assertEquals("A", vector.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#removeAll(HCollection)} non
     * modifichi il vettore se nessuno gli elementi specificati nella collezione è presente nel
     * vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#removeAll(HCollection)} non
     * modifichi il vettore se nessuno gli elementi specificati nella collezione è presente nel
     * vettore.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e una collezione con elementi non presenti nel
     * vettore. Invoca {@link VectorAdapter#removeAll(HCollection)}e verifica che il vettore rimanga
     * invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto e una collezione non vuoto senza elementi in
     * comune.
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

        HCollection collection = new VectorAdapter();
        collection.add("X");
        collection.add("Y");

        assertFalse(vector.removeAll(collection));
        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
    }

    // ======== boolean retainAll(HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#retainAll(HCollection)} lanci
     * un'eccezione quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#retainAll(HCollection)} lanci
     * un'eccezione {@link NullPointerException} quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto e invoca il metodo {@code retainAll(null)}. Verifica che
     * venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void retainAllThrowsNullPointerException() {
        vector.retainAll(null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica la funzionalità del metodo
     * {@link VectorAdapter#retainAll(HCollection)}.
     * </p>
     * <p>
     * <b>Description</b> Verifica la funzionalità del metodo
     * {@link VectorAdapter#retainAll(HCollection)}. In particolare verifica che dopo l'invocazione
     * vengano eliminati dal vettore tutti gli elementi non presenti nella collezione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e una collezione che contiene alcuni, ma non tutti,
     * elementi del vettore. Invoca il metodo {@link VectorAdapter#retainAll(HCollection)}. Verifica
     * che vengano rimossi dal vettore tutti gli elementi non specificati nella collezione e che il
     * metodo ritorni {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto ed una collezione non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Vengono rimossi dal vettore tutti gli elementi che non sono in comune
     * con la collezione.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#retainAll(HCollection)} ritorna
     * {@code true}
     * </p>
     */
    @Test
    public void retainAllRetainsElements() {
        vector.add("A");
        vector.add("A");
        vector.add("B");
        vector.add("X");

        VectorAdapter vect = new VectorAdapter();
        vect.add("B");

        assertTrue(vector.retainAll(vect));

        assertEquals(1, vector.size());
        assertEquals("B", vector.get(0));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#retainAll(HCollection)} si
     * comporti correttamente quando invocato utilizzando come parametro il vettore stesso.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#retainAll(HCollection)} si
     * comporti correttamente quando invocato utilizzando come parametro il vettore stesso. In
     * particolare ci si aspetta che il vettore non venga modificato e che il metodo ritorni
     * {@code false}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto. Invoca il metodo
     * {@link VectorAdapter#retainAll(HCollection)} utilizzando il vettore stesso come parametro.
     * Verifica che il vettore rimanga invariato e che il metodo ritorni {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#retainAll(HCollection)} ritorna
     * {@code false}
     * </p>
     */
    @Test
    public void retainAllOnSelf() {
        vector.add("A");
        vector.add("A");
        vector.add("B");
        vector.add("X");

        assertFalse(vector.retainAll(vector));

        assertEquals(4, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("A", vector.get(1));
        assertEquals("B", vector.get(2));
        assertEquals("X", vector.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#retainAll(HCollection)} si
     * comporti correttamente quando invocato utilizzando come parametro una collezione vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#retainAll(HCollection)} si
     * comporti correttamente quando invocato utilizzando come parametro una collezione vuota. In
     * particolare ci si aspetta che il metodo si comporti esattamente come
     * {@link VectorAdapter#clear()}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto. Invoca il metodo
     * {@link VectorAdapter#retainAll(HCollection)} utilizzando una collezione vuota come parametro.
     * Verifica che il vettore venga svuotato di tutti i suoi elementi e che il metodo ritorni
     * {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore è vuoto.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#retainAll(HCollection)} ritorna
     * {@code true}
     * </p>
     */
    @Test
    public void retainAllRemovesAll() {
        vector.add("A");
        vector.add("A");
        vector.add("B");
        vector.add("X");

        assertTrue(vector.retainAll(new VectorAdapter()));

        assertTrue(vector.isEmpty());
    }

    // ======== boolean addAll(int, HCollection) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)} lanci
     * un'eccezione quando la collezione specificata è {@code null}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)}
     * lanci un'eccezione {@link NullPointerException} quando la collezione specificata è
     * {@code null}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto e invoca il metodo {@code addAll(0, null)}. Verifica che
     * venga lanciata l'eccezione corretta.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia {@link NullPointerException}.
     * </p>
     */
    @Test(expected = NullPointerException.class)
    public void addAllAtThrowsNullPointerException() {
        vector.addAll(0, null);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)} lanci
     * un'eccezione se l'indice è negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)}
     * sollevi un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è
     * negativo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, poi chiama {@code addAll(-1, Collection)} e
     * verifica che venga lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Ul vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'invocazione {@code addAll(-1, Collection)} lancia un'eccezione
     * {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAllAtThrowsArrayIndexOutOfBoundsException() {
        vector.addAll(-1, vector);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)} lanci
     * un'eccezione se l'indice è maggiore alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)}
     * sollevi un'eccezione {@link ArrayIndexOutOfBoundsException} se l'indice specificato è
     * maggiore alla dimensione del vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento, poi chiama {@code addAll(2, Collection)} e
     * verifica che venga lanciata l'eccezioe {@link ArrayIndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Viene sollevata un'eccezione e il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo lancia un'eccezione {@link ArrayIndexOutOfBoundsException}.
     * </p>
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addAllAtThrowsArrayIndexOutOfBoundsException2() {
        vector.add("A");

        vector.addAll(2, vector);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)} aggiunga
     * correttamente gli elementi all'indice specificato.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)}
     * aggiunga correttamente gli elementi a partire dall'indice specificato, spostando a destra gli
     * elementi successivi.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elementi, poi chiama {@code addAll(1, Collection)}
     * e verifica che gli elementi della collezione siano aggiunti a partire dall'indice corretto,
     * mantenendo l'ordine corretto e spostando a destra gli elementi successivi del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di due elementi e una collezione non vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Vengono aggiunti correttamente gli elementi della collezione al
     * vettore.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code true}.
     * </p>
     */
    @Test
    public void addAllAtIndex() {
        vector.add("A");
        vector.add("B");

        assertTrue(vector.addAll(1, vector));
        assertEquals(4, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("A", vector.get(1));
        assertEquals("B", vector.get(2));
        assertEquals("B", vector.get(3));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)} quando
     * la collezione è vuota.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#addAll(int, HCollection)}
     * quando la collezione è vuota. In particolare ci si aspetta che il vettore non vegna
     * modificato e che il metodo ritorni {@code false}
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elementi, poi chiama {@code addAll(1, Collection)}
     * con una collezione vuota. Verifica che il vettore non sia stato modificato e che il metodo
     * ritorni {@code false}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di due elementi e una collezione vuota.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo ritorna {@code false}.
     * </p>
     */
    @Test
    public void addAllAtIndexWithEmptyCollection() {
        vector.add("A");
        vector.add("B");

        assertFalse(vector.addAll(1, new VectorAdapter()));
        assertEquals(2, vector.size());
        assertEquals("A", vector.get(0));
        assertEquals("B", vector.get(1));
    }

    // ======== boolean equals(Object) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code false} quando l'oggetto confrontato non è una lista.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code false} quando l'oggetto confrontato non è una lista. Viene verificato passando un
     * oggetto di tipo {@link String}.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore e lo confronta con un oggetto di tipo {@link String}. Verifica
     * che {@code equals()} restituisca {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore e un oggetto non di tipo {@link String}.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code false}.
     * </p>
     */
    @Test
    public void equalsOnNonList() {
        assertFalse(vector.equals("Not_A_List"));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code true} quando due vettori contengono gli stessi elementi nello stesso ordine.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code true} quando due vettori contengono gli stessi elementi nello stesso ordine. Questo
     * viene verificato con due vettori diversi, anche se contenenti gli stessi elementi (alcuni dei
     * quali sono l'elemento {@code null}).
     * </p>
     * <p>
     * <b>Design</b> Crea due vettori con gli stessi elementi. Verifica che {@code equals()}
     * restituisca {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Due vettori con gli stessi elementi nello stesso ordine.
     * </p>
     * <p>
     * <b>Post-Condition</b> I due vettori rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true}.
     * </p>
     */
    @Test
    public void equalsReturnsTrue() {
        vector.add("A");
        vector.add(null);
        vector.add("C");

        VectorAdapter anotherVector = new VectorAdapter();
        anotherVector.add("A");
        anotherVector.add(null);
        anotherVector.add("C");

        assertTrue(vector.equals(anotherVector));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code false} quando due vettori hanno contenuti diversi.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code false} quando due vettori hanno contenuti diversi.
     * </p>
     * <p>
     * <b>Design</b> Crea due vettori con contenuti diversi. Verifica che {@code equals()}
     * restituisca {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Due vettori con contenuti diversi.
     * </p>
     * <p>
     * <b>Post-Condition</b> I due vettori rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code false}.
     * </p>
     */
    @Test
    public void equalsReturnsFalse() {
        vector.add("A");
        vector.add("B");

        VectorAdapter anotherVector = new VectorAdapter();
        anotherVector.add("A");
        anotherVector.add("C");

        assertFalse(vector.equals(anotherVector));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code false} quando due vettori dimensioni diverse.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code false} quando due vettori dimensioni diverse.
     * </p>
     * <p>
     * <b>Design</b> Crea due vettori con dimensioni diverse. Verifica che {@code equals()}
     * restituisca {@code false}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Due vettori con dimensioni diverse.
     * </p>
     * <p>
     * <b>Post-Condition</b> I due vettori rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code false}.
     * </p>
     */
    @Test
    public void equalsReturnsFalse2() {
        vector.add("A");
        vector.add("B");

        VectorAdapter anotherVector = new VectorAdapter();
        anotherVector.add("A");

        assertFalse(vector.equals(anotherVector));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code true} quando il vettore viene confrontato con sé stesso.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#equals(Object)} restituisca
     * {@code true} quando il vettore viene confrontato con sé stesso. Come specificato dalla
     * documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, chiama {@code equals()} con il riferimento allo
     * stesso vettore. Verifica che {@code equals()} restituisca {@code true}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce {@code true}.
     * </p>
     */
    @Test
    public void testEqualsWithSelf() {
        vector.add("A");
        vector.add("B");

        assertTrue(vector.equals(vector));
    }

    // ======== int hashCode() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#hashCode()} ritorni lo stesso
     * codice per vettori con elementi identici.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#hashCode()} ritorni lo stesso
     * codice per vettori con elementi identici. Alcuni di questi elementi sono {@code null} per
     * verificare che non comporti eccezioni.
     * </p>
     * <p>
     * <b>Design</b> Crea due vettori identici non vuoti con alcuni elementi {@code null}. Verifica
     * che il codice hash sia uguale per entrambi i vettori.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Due vettori identici non vuoti con alcuni elementi {@code null}.
     * </p>
     * <p>
     * <b>Post-Condition</b> I due vettori rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo restituisce lo stesso hash code per entrambi i vettori.
     * </p>
     */
    @Test
    public void hasCodeEqualVectors() {
        vector.add("A");
        vector.add(null);
        vector.add("C");

        HList anotherVector = new VectorAdapter();
        anotherVector.add("A");
        anotherVector.add(null);
        anotherVector.add("C");

        assertTrue(vector.equals(anotherVector));
        assertEquals(vector.hashCode(), anotherVector.hashCode());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#hashCode()} ritorni codici hash
     * diversi per vettori diversi.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#hashCode()} ritorni codici
     * hash diversi per vettori diversi. Come specificato dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea due vettori diversi non vuoti. Verifica che i codici hash siano diversi.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Due vettori diversi non vuoti.
     * </p>
     * <p>
     * <b>Post-Condition</b> I vettori rimangono invariati.
     * </p>
     * <p>
     * <b>Expected Results</b> I codici hash dei vettori sono diversi.
     * </p>
     */
    @Test
    public void hashCodeWithDifferentVectors() {
        vector.add("A");
        vector.add("B");

        HList anotherVector = new VectorAdapter();
        anotherVector.add("A");
        anotherVector.add("C");

        assertFalse(vector.equals(anotherVector));
        assertNotEquals(vector.hashCode(), anotherVector.hashCode());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#hashCode()} di un vettore sia
     * coerente attraverso chiamate multiple.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#hashCode()} di un vettore sia
     * coerente attraverso chiamate multiple. Come specificato dalla documentazione
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto, poi chiama più volte il metodo {@code hashCode()} su
     * un vettore e verifica che il codice hash rimanga invariato.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene mai modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il codice hash rimane invariato tra chiamate multiple.
     * </p>
     */
    @Test
    public void hashCodeConsistency() {
        vector.add("A");
        vector.add("B");

        int initialHashCode = vector.hashCode();
        assertEquals(initialHashCode, vector.hashCode());
        assertEquals(initialHashCode, vector.hashCode());
    }

    // ======== String toString() ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toString()} restituisca "[]" per
     * un vettore vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toString()} restituisca "[]"
     * per un vettore vuoto. Come descritto dalla documentazione.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore vuoto e invoca il metodo {@link VectorAdapter#toString()}.
     * Verifica che il risultato sia "[]".
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#toString()} ritorna "[]".
     * </p>
     */
    @Test
    public void toStringEmptyVector() {
        assertEquals("[]", vector.toString());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toString()} restituisca la stringa
     * corretta per un vettore non vuoto.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toString()} restituisca la
     * stringa corretta per un vettore non vuoto. Ovvero una rappresentazione che mostri tutti gli
     * elementi del vettore separati da una virgola.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto e verifica che la rappresentazione sia quella attesa.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#toString()} restituisce la stringa
     * attesa.
     * </p>
     */
    @Test
    public void toStringOnVector() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        assertEquals("[A, B, C]", vector.toString());
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#toString()} gestisca correttamente
     * i riferimenti ciclici a se stesso.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#toString()} gestisca
     * correttamente i riferimenti ciclici a se stesso. Ci si aspetta che il vettore venga
     * rappresentato come "(this Collection)" così da evitare cicli infiniti.
     * </p>
     * <p>
     * <b>Design</b> Crea un vettore non vuoto che contiene sé stesso tra i suoi elemeneti.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto che contiene sé stesso come elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Il metodo {@link VectorAdapter#toString()} restituisce la stringa
     * attesa e non genera cicli infiniti.
     * </p>
     */
    @Test
    public void testToStringWithCyclicReference() {
        vector.add("A");
        vector.add(vector);
        vector.add("C");
        assertEquals("[A, (this Collection), C]", vector.toString());
    }

    // ======== HList subList(int, int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#subList(int, int)} lanci un'eccezione quando
     * {@code fromIndex > toIndex}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#subList(int, int)} lanci un'eccezione
     * {@link IllegalArgumentException} quando {@code fromIndex > toIndex}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elemento. Invoca il metodo {@code subList(2, 1)}.
     * Verifica che l'eccezione {@link IllegalArgumentException} venga lanciata
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di due elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#subList(int, int)} viene
     * lanciata un'eccezione {@link IllegalArgumentException}
     * </p>
     */
    @Test(expected = IllegalArgumentException.class)
    public void subListThrowsIllegalArgumentException() {
        vector.add("A");
        vector.add("B");
        vector.subList(2, 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#subList(int, int)} lanci un'eccezione quando
     * {@code fromIndex < 0}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#subList(int, int)} lanci un'eccezione
     * {@link IndexOutOfBoundsException} quando {@code fromIndex < 0}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elementi. Invoca il metodo {@code subList(-1, 1)}.
     * Verifica che l'eccezione {@link IndexOutOfBoundsException} venga lanciata
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di due elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#subList(int, int)} viene
     * lanciata un'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsIndexOutOfBoundsException() {
        vector.add("A");
        vector.add("B");
        vector.subList(-1, 1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#subList(int, int)} lanci un'eccezione quando
     * {@code toIndex > size}.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#subList(int, int)} lanci un'eccezione
     * {@link IndexOutOfBoundsException} quando {@code toIndex > size}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di due elementi. Invoca il metodo {@code subList(0, 3)}.
     * Verifica che l'eccezione {@link IndexOutOfBoundsException} venga lanciata
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di due elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#subList(int, int)} viene
     * lanciata un'eccezione {@link IndexOutOfBoundsException}
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void subListThrowsIndexOutOfBoundsException2() {
        vector.add("A");
        vector.add("B");
        vector.subList(0, 3);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#subList(int, int)} ritorni la corretta vista
     * del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#subList(int, int)} ritorni la corretta
     * vista del vettore. Ovvero tra {@code fromIndex}, inclusivo, e {@code toIndex}, esclusivo.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 4} elementi. Invoca il metodo
     * {@code subList(1, 3)}. Verifica che la sottolista abbia dimensione 2 e che contenga gli
     * elementi che hanno indice 1 e 2 nel vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 4} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#subList(int, int)} la
     * sottolista ha dimensione 2 e che contiene gli elementi che hanno indice 1 e 2 nel vettore.
     * </p>
     */
    @Test
    public void subListBetweenRange() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        vector.add("D");
        HList subList = vector.subList(1, 3);
        assertEquals(2, subList.size());
        assertEquals(vector.get(1), subList.get(0));
        assertEquals(vector.get(2), subList.get(1));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#subList(int, int)} ritorni la corretta vista
     * del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#subList(int, int)} ritorni la corretta
     * vista del vettore. In questo caso la vista corretta è l'intero vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 3} elementi. Invoca il metodo
     * {@code subList(0, 3)}. Verifica che la sottolista abbia dimensione 3 e che contenga gli
     * stessi elementi nello stesso ordine del vettore.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 3} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#subList(int, int)} la
     * sottolista ha dimensione 3 e che contiene gli stessi elementi nello stesso ordine del
     * vettore.
     * </p>
     */
    @Test
    public void subListFullRange() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        HList subList = vector.subList(0, 3);
        assertEquals(3, subList.size());
        assertEquals(vector.get(0), subList.get(0));
        assertEquals(vector.get(1), subList.get(1));
        assertEquals(vector.get(2), subList.get(2));
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che {@link VectorAdapter#subList(int, int)} ritorni la corretta vista
     * del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che {@link VectorAdapter#subList(int, int)} ritorni la corretta
     * vista del vettore. In questo caso la vista corretta è un vettore vuoto.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di {@code 2} elementi. Invoca il metodo
     * {@code subList(1, 1)}. Verifica che la sottolista abbia dimensione 0.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di {@code 2} elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore non viene modificato.
     * </p>
     * <p>
     * <b>Expected Results</b> Dopo la chiamata a {@link VectorAdapter#subList(int, int)} la
     * sottolista ha dimensione 0.
     * </p>
     */
    @Test
    public void subListEqualIndices() {
        vector.add("A");
        vector.add("B");
        HList subList = vector.subList(1, 1);
        assertEquals(0, subList.size());
    }

    // ======== HIterator iterator() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#iterator()} ritorni un oggetto di
     * tipo {@link HIterator}.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#iterator()} ritorni un oggetto di
     * tipo {@link HIterator}. Test estensivi dell'implementazione di {@code IteratorAdapter} sono
     * svolti nella test suite {@code IteratorAdapterTester}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, chiama la funzione {@link VectorAdapter#iterator()}
     * e verifica che un oggetto di tipo {@link HIterator}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#iterator()} ritorna un oggetto di tipo
     * {@link HIterator}.
     * </p>
     */
    @Test
    public void iteratorReturnsHIterator() {
        HIterator iterator = vector.iterator();
    }

    // ======== HListIterator listIterator() ========

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#listIterator()} ritorni un oggetto di
     * tipo {@link HListIterator}.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#listIterator()} ritorni un
     * oggetto di tipo {@link HListIterator}. Test estensivi dell'implementazione di
     * {@code ListIteratorAdapter} sono svolti nella test suite {@code ListIteratorAdapterTester}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto, chiama la funzione
     * {@link VectorAdapter#listIterator()} e verifica che un oggetto di tipo {@link HListIterator}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#listIterator()} ritorna un oggetto di tipo
     * {@link HListIterator}.
     * </p>
     */
    @Test
    public void listiteratorReturnsHListIterator() {
        HListIterator iterator = vector.listIterator();
    }

    // ======== HListIterator listIterator(int) ========

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#listIterator(int)} lanci
     * un'eccezione quando invocato con un indice negativo.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#listIterator(int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} quando invocato con un indice negativo.
     * L'operazione non dovrebbe modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore vuoto. Invoca il metodo {@code listIterator(-1)}. Verifica
     * che venga lanciata un'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore instanziato vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link IndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorThrowsIndexOutOfBoundsException() {
        vector.listIterator(-1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica che il metodo {@link VectorAdapter#listIterator(int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} quando invocato con un indice uguale alla
     * dimensione del vettore.
     * </p>
     * <p>
     * <b>Description</b> Verifica che il metodo {@link VectorAdapter#listIterator(int)} lanci
     * un'eccezione {@link IndexOutOfBoundsException} quando invocato con un indice uguale alla
     * dimensione del vettore. L'operazione non dovrebbe modificare il vettore.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di un elemento. Invoca il metodo {@code listIterator(2)}.
     * Verifica che venga lanciata un'eccezione {@link IndexOutOfBoundsException}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore con un elemento.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> L'eccezione {@link IndexOutOfBoundsException} viene lanciata.
     * </p>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void listItreatorThrowsIndexOutOfBoundsException() {
        vector.add("A");

        vector.listIterator(2);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#listIterator(int)} ritorni un oggetto
     * di tipo {@link HListIterator}.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#listIterator(int)} ritorni un
     * oggetto di tipo {@link HListIterator}. Test estensivi dell'implementazione di
     * {@code ListIteratorAdapter} sono svolti nella test suite {@code ListIteratorAdapterTester}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore non vuoto, chiama la funzione
     * {@link VectorAdapter#listIterator(int)} con un indice valido e verifica che un oggetto di
     * tipo {@link HListIterator}
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore non vuoto.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#listIterator(int)} ritorna un oggetto di tipo
     * {@link HListIterator}.
     * </p>
     */
    @Test
    public void listiteratorReturnsHListIteratorFromIndex() {
        vector.add("A");
        HListIterator iterator = vector.listIterator(1);
    }

    /**
     * <p>
     * <b>Summary</b> Verifica del metodo {@link VectorAdapter#listIterator(int)} ritorni un oggetto
     * di tipo {@link HListIterator} che inizia dall'indice corretto.
     * </p>
     * <p>
     * <b>Description</b> Verifica del metodo {@link VectorAdapter#listIterator(int)} ritorni un
     * oggetto di tipo {@link HListIterator} che inizia dall'indice corretto. Test estensivi
     * dell'implementazione di {@code ListIteratorAdapter} sono svolti nella test suite
     * {@code ListIteratorAdapterTester}.
     * </p>
     * <p>
     * <b>Design</b> Instanzia un vettore di 3 elementi, instanzia un iteratore grazie al metodo
     * {@link VectorAdapter#listIterator(int)} con un indice valido e verifica che l'iteratore inizi
     * dall'indice corretto invocando {@link ListIteratorAdapter#next()} e
     * {@link ListIteratorAdapter#hasNext()}.
     * </p>
     * <p>
     * <b>Pre-Condition</b> Un vettore di 3 elementi.
     * </p>
     * <p>
     * <b>Post-Condition</b> Il vettore rimane invariato.
     * </p>
     * <p>
     * <b>Expected Results</b> {@link VectorAdapter#listIterator(int)} ritorna gli elementi del
     * vettore a partire dall'indice specificato.
     * </p>
     */
    @Test
    public void listIteratorReturnsCorrectView() {
        vector.add("A");
        vector.add("B");
        vector.add("C");
        HListIterator iterator = vector.listIterator(1);

        assertEquals("B", iterator.next());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
