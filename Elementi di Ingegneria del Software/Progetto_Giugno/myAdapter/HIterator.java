package myAdapter;

import java.util.NoSuchElementException;

/**
 * Un iteratore di una collezione, si comporta similmente all'Enumeration della
 * CLDC 1.1
 * 
 * L'interfaccia è la stessa che si può ritorvare in Java 2 SE 1.4.2.
 */
public interface HIterator {
    /**
     * Verifica se l'iterazione ha un elemento successivo.
     * 
     * @return true se l'iterazione ha un elemento successivo, false altrimenti
     */
    boolean hasNext();

    /**
     * Ritorna il successivo elemento dell'iterazione
     * 
     * @return l'elemento successivo
     * @throws NoSuchElementException Se non esiste l'elemento successivo
     */
    Object next();

    /**
     * Elimina l'ultimo elemento che è stato fornito dal metodo next() dalla
     * collezione sottostante. Questo metodo può essere chiamato solo una volta per
     * ogni next().
     * 
     * @throws IllegalStateException Se il metodo viene chiamato prima di next()
     *                               oppure se viene chiamato più volte.
     */
    void remove();
}