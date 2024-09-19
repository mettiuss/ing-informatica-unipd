package myAdapter;

import java.util.NoSuchElementException;

/**
 * <p>
 * Un iteratore per liste che permette al programmatore di attraversare la lista in entrambe le
 * direzioni, modificare la lista durante l'iterazione e ottenere la posizione corrente
 * dell'iteratore nella lista. Un {@code HListIterator} non ha un elemento corrente; la sua
 * posizione del cursore si trova sempre tra l'elemento che verrebbe restituito da una chiamata a
 * previous() e l'elemento che verrebbe restituito da una chiamata a next(). In una lista di
 * lunghezza n, ci sono n+1 valori di indice validi, da 0 a n, inclusi.
 * </p>
 * 
 * <pre>

         Element(0)   Element(1)   Element(2)   ... Element(n)
        ^            ^            ^            ^               ^
 Index: 0            1            2            3               n+1
 * </pre>
 * <p>
 * Nota che {@link #remove()} e {@link #set(Object)} non si basano sulla posizione dell'iteratore,
 * bensì sull'ultimo elemento ritornato da una chiamata a {@link #next()} o {@link #previous()}.
 * </p>
 *
 * @author Matteo Cuzzolin (matr: 2066701)
 */
public interface HListIterator extends HIterator {
    /**
     * Restituisce {@code true} se questo iteratore di lista ha altri elementi durante
     * l'attraversamento della lista in direzione inversa. (In altre parole, restituisce
     * {@code true} se {@code previous()} restituirebbe un elemento piuttosto che lanciare
     * un'eccezione).
     *
     * @return {@code true} se l'iteratore di lista ha altri elementi durante l'attraversamento
     *         della lista in direzione inversa.
     */
    boolean hasPrevious();

    /**
     * Restituisce l'elemento precedente nella lista. Questo metodo può essere chiamato
     * ripetutamente per scorrere la lista all'indietro, o alternato con chiamate a {@code next()}
     * per muoversi avanti e indietro. (Si noti che chiamate alternate a {@code next()} e
     * {@code previous()} restituiranno ripetutamente lo stesso elemento).
     *
     * @return l'elemento precedente nella lista.
     * @throws NoSuchElementException se l'iterazione non ha un elemento precedente.
     */
    Object previous();

    /**
     * Restituisce l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
     * {@code next()}. (Restituisce la dimensione della lista se l'iteratore di lista è alla fine
     * della lista).
     *
     * @return l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
     *         {@code next()}, o la dimensione della lista se l'iteratore di lista è alla fine della
     *         lista.
     */
    int nextIndex();

    /**
     * Restituisce l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
     * {@code previous()}. (Restituisce {@code -1} se l'iteratore di lista è all'inizio della
     * lista).
     *
     * @return l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
     *         {@code previous}, o {@code -1} se l'iteratore di lista è all'inizio della lista.
     */
    int previousIndex();

    /**
     * Sostituisce l'ultimo elemento restituito da {@code next} o {@code previous} con l'elemento
     * specificato (operazione opzionale). Questa chiamata può essere effettuata solo se né
     * {@code HListIterator.remove} né {@code HListIterator.add} sono stati chiamati dopo l'ultima
     * chiamata a {@code next} o {@code previous}.
     *
     * @param o l'elemento con cui sostituire l'ultimo elemento restituito da {@code next} o
     *        {@code previous}.
     * @throws UnsupportedOperationException {@code set} non è supportata da questo iteratore di
     *         lista.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce l'aggiunta a
     *         questa lista.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato ne impedisce
     *         l'aggiunta a questa lista.
     * @throws IllegalStateException se non è stato chiamato né {@code next} né {@code previous},
     *         oppure se {@code remove} o {@code add} sono stati chiamati dopo l'ultima chiamata a
     *         {@code next} o {@code previous}.
     */
    void set(Object o);

    /**
     * Inserisce l'elemento specificato nella lista (operazione opzionale). L'elemento viene
     * inserito immediatamente prima del prossimo elemento che verrebbe restituito da {@code next},
     * se presente, e dopo il prossimo elemento che verrebbe restituito da {@code previous}, se
     * presente. (Se la lista non contiene elementi, il nuovo elemento diventa l'unico elemento
     * nella lista). Il nuovo elemento viene inserito prima del cursore implicito: una successiva
     * chiamata a {@code next} non sarà influenzata, e una successiva chiamata a {@code previous}
     * restituirà il nuovo elemento. (Questa chiamata aumenta di uno il valore che verrebbe
     * restituito da una chiamata a {@code nextIndex} o {@code previousIndex}).
     *
     * @param o l'elemento da inserire.
     * @throws UnsupportedOperationException {@code add} non è supportato da questo iteratore di
     *         lista.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce l'aggiunta a
     *         questa lista.
     * @throws IllegalArgumentException se qualche aspetto di questo elemento ne impedisce
     *         l'aggiunta a questa lista.
     */
    void add(Object o);
}
