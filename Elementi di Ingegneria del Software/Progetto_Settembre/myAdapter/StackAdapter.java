package myAdapter;

import java.util.EmptyStackException;

/**
 * La classe {@code StackAdapter} rappresenta uno stack di oggetti di tipo last-in-first-out (LIFO).
 * Estende la classe {@code VectorAdapter} con cinque operazioni che permettono di trattare un
 * vettore come uno stack. Vengono fornite le usuali operazioni di {@code push} e {@code pop}, così
 * come un metodo per osservare l'elemento in cima allo stack ({@code peek}), un metodo per
 * verificare se lo stack è vuoto, e un metodo per cercare un elemento nello stack e scoprire la sua
 * distanza dalla cima.
 *
 * Quando uno stack viene creato per la prima volta, non contiene alcun elemento.
 */
public class StackAdapter extends VectorAdapter {

    /**
     * Crea uno {@code StackAdapter} vuoto
     */
    public StackAdapter() {
        super();
    }

    /**
     * Inserisce un elemento in cima a questo stack. Ha esattamente lo stesso effetto di:
     * {@link VectorAdapter#addElement(Object)}.
     *
     * @param item l'elemento da inserire in cima allo stack.
     * @return l'elemento passato come argomento {@code item}.
     * @see VectorAdapter#addElement(Object)
     */
    public Object push(Object item) {
        addElement(item);
        return item;
    }

    /**
     * Rimuove l'oggetto in cima a questo stack e restituisce quell'oggetto come valore di questa
     * funzione.
     *
     * @return L'oggetto in cima a questo stack (l'ultimo elemento dell'oggetto {@code Vector}).
     * @throws EmptyStackException se lo stack è vuoto.
     */
    public Object pop() {
        if (size() == 0)
            throw new EmptyStackException();
        return remove(size() - 1);
    }

    /**
     * Esamina l'oggetto in cima a questo stack senza rimuoverlo.
     *
     * @return l'oggetto in cima a questo stack (l'ultimo elemento dell'oggetto {@code Vector}).
     * @throws EmptyStackException se lo stack è vuoto.
     */
    public Object peek() {
        if (size() == 0)
            throw new EmptyStackException();
        return get(size() - 1);
    }

    /**
     * Verifica se questo stack è vuoto.
     *
     * @return {@code true} se e solo se questo stack non contiene elementi; {@code false}
     *         altrimenti.
     */
    public boolean empty() {
        return isEmpty();
    }

    /**
     * Restituisce la posizione basata su {@code 1} in cui si trova un oggetto in questo stack. Se
     * l'oggetto {@code o} è presente nello stack, questo metodo restituisce la distanza dalla cima
     * dello stack della prima occorrenza più vicina alla cima; l'elemento in cima allo stack è
     * considerato alla distanza {@code 1}. Il metodo {@code equals} viene utilizzato per
     * confrontare {@code o} con gli elementi presenti nello stack.
     *
     * @param o l'oggetto desiderato.
     * @return la posizione basata su {@code 1} dalla cima dello stack dove si trova l'oggetto; il
     *         valore di ritorno {@code -1} indica che l'oggetto non è presente nello stack.
     */
    public int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0)
            return size() - i;

        return -1;
    }
}
