package myAdapter;

/**
 * L'interfaccia radice di tutte le collezioni, ovvero di un gruppo di oggetti.
 * 
 * L'interfaccia è la stessa che si può ritorvare in Java 2 SE 1.4.2.
 */
public interface HCollection {
    /**
     * Aggiunge un elemento alla collezione
     *
     * @param o l'oggetto da aggiungere
     * @return true
     */
    boolean add(Object o);

    /**
     * Inserisce tutti gli elementi della Collezione nella collezione
     *
     * @param c La collezione da cui prendere gli elementi
     * 
     * @return true
     * @throws NullPointerException Se la collezione è null
     */
    boolean addAll(HCollection c);

    /**
     * Rimuove tutti gli elementi da una collezione
     */
    void clear();

    /**
     * Verifica se la collezione contiene l'elemento speicificato
     * 
     * @param o l'elemento da verificare
     * @return true se la collezione contiene l'elemento, false altrimenti
     */
    boolean contains(Object o);

    /**
     * Verifica se la collezione contiene tutti gli elementi della Collezione
     * 
     * @param c la collezione contenente gli elementi da verificare
     * @return true se la collezione contiene tutti gli elementi, false altrimenti
     * @throws NullPointerException se la collection è null
     */
    boolean containsAll(HCollection c);

    /**
     * Compara l'oggetto specificato con la collezione, ritorna vero solo se
     * le due collezioni sono uguali
     * 
     * @param o l'oggetto da confrontare
     * @return true se le due collezioni sono uguali, false altrimenti
     */
    boolean equals(Object o);

    /**
     * Ritorna un hash code per la collezione.
     * 
     * @return l'hash code della collezione.
     */
    int hashCode();

    /**
     * Verifica che la collezione sia vuota
     * 
     * @return true se la collezione è vuota, false altrimenti
     */
    boolean isEmpty();

    /**
     * Ritorna un iteratore degli elementi della collezione
     *
     * @return l'iteratore degli elementi della collezione
     */
    HIterator iterator();

    /**
     * Rimuove la prima occorrenza dell'oggetto specificato, se è
     * presente
     *
     * @param o l'oggetto da rimuovere
     * @return true se l'oggetto viene trovato e rimosso, false altrimenti
     */
    boolean remove(Object o);

    /**
     * Rimuove dalla collezione tutti gli elementi che si trovano nella collezione
     * specificata
     *
     * @param c la collezione
     * @return true se la lista viene modificata, false altrimenti
     * @throws NullPointerException se l'oggetto è null
     */
    boolean removeAll(HCollection c);

    /**
     * Mantiene nella collezione solamente gli elementi presenti nella collezione
     * specificata, tutti gli altri elementi vengono rimossi
     *
     * @param c la collezione
     * @return true se la lista viene modificata, false altrimenti
     * @throws NullPointerException se l'oggetto è null
     */
    boolean retainAll(HCollection c);

    /**
     * Ritorna il numero di elementi contenuti nella collezione
     *
     * @return il numero di elementi
     */
    int size();

    /**
     * Ritorna un array contenente tutti gli elementi della collezione
     *
     * @return l'array contenente gli elementi della collezione
     */
    Object[] toArray();

    /**
     * Ritorna un array contenente tutti gli elementi della lista utilizzando come
     * tipo quello specificato dall'array a
     *
     * @param a L'array che specifica il tipo dell'array ritornato
     * @return l'array contenente gli elementi della lista del tipo specificato
     */
    Object[] toArray(Object[] a);
}