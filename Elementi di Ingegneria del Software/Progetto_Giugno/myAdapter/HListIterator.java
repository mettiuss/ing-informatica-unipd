package myAdapter;

import java.util.NoSuchElementException;

/**
 * Un iteratore per le liste che permette di attraversare in entrambe le
 * direzioni la lista e permette di modificarla durante l'iterazione.
 */
public interface HListIterator extends HIterator {
    /**
     * Aggiunge un elemento nella lista originale, l'elemento viene inserito
     * immediatamente prima dell'elemento che verrebbe ritornato dalla chiamata a
     * next(), se presente. Altrimenti viene inserito immediatamente dopo l'elemento
     * che verrebbe ritornato dalla chiamata a previous(), se presente.
     * Se la lista è vuota viene inserito come unico elemento
     *
     * @param o L'elemento da aggiungere alla lista.
     */
    void add(Object o);

    /**
     * Verifica se l'iterazione ha un elemento successivo.
     * 
     * @return true se l'iterazione ha un elemento successivo, false altrimenti
     */
    boolean hasNext();

    /**
     * Verifica se l'iterazione ha un elemento precendente.
     * 
     * @return true se l'iterazione ha un elemento precedente, false altrimenti.
     */
    boolean hasPrevious();

    /**
     * Ritorna il successivo elemento dell'iterazione
     * 
     * @return l'elemento successivo
     * @throws NoSuchElementException Se non esiste l'elemento successivo
     */
    Object next();

    /**
     * RItorna l'indice dell'elemento che verrebbe ritornato se si chiamasse next()
     * 
     * @return l'indirizzo dell'elemento che sarebbe ritornato da una chiamata a
     *         next(), oppure la lunghezza della lista se ci si trova alla fine
     *         dell'iterazione
     */
    int nextIndex();

    /**
     * Ritorna il precedente elemento dell'iterazione
     * 
     * @return l'elemento precendente
     * @throws NoSuchElementException Se non esiste l'elemento precedente
     */
    Object previous();

    /**
     * Ritorna l'indice dell'elemento che verrebbe ritornato se si chiamasse
     * previous()
     * 
     * @return l'indirizzo dell'elemento che sarebbe ritornato da una chiamata a
     *         previous(), oppure -1 se ci si trova all'inizio dell'iterazione
     */
    int previousIndex();

    /**
     * Elimina l'ultimo elemento che è stato fornito dal metodo next() dalla
     * collezione sottostante. Questo metodo può essere chiamato solo una volta per
     * ogni next().
     * 
     * @throws IllegalStateException Se il metodo viene chiamato prima di next()
     *                               oppure se viene chiamato più volte.
     */
    void remove();

    /**
     * Rimpiazza l'ultimo elemento ritornato da next() o previous()
     * 
     * @param o L'oggetto con cui rimpiazzare l'elemento
     * @throws IllegalStateException se next() o previous() non sono stati chiamati
     *                               in precedenza
     */
    void set(Object o);
}