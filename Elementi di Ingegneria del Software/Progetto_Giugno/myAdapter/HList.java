package myAdapter;

/**
 * Una collezione non ordinata (sequenza). L'utente ha preciso controllo su dove
 * gli elementi vengono inseriti e può accedere ad un elemento specificando il
 * suo indice.
 * 
 * L'interfaccia è la stessa che si può ritorvare in Java 2 SE 1.4.2.
 */
public interface HList extends HCollection {
    /**
     * Inserisce l'elemento specificato nell'indice specificato, sposta a destra
     * tutti gli elementi successivi della lista
     *
     * @param index   L'indice a cui inserire l'elemento
     * @param element L'elemento da inserire
     * 
     * @throws IndexOutOfBoundsException Se (index &lt; 0 || index &gt;= size())
     */
    void add(int index, Object element);

    /**
     * Aggiunge un elemento alla lista
     *
     * @param o l'oggetto da aggiungere
     * @return true
     */
    boolean add(Object o);

    /**
     * Inserisce tutti gli elementi della collezione nella lista
     *
     * @param c La collezione da cui prendere gli elementi
     * 
     * @return true
     * @throws NullPointerException Se la collezione è null
     */
    boolean addAll(HCollection c);

    /**
     * Inserisce tutti gli elementi della Collezione all'indice specificato della
     * lista
     *
     * @param index L'indice a partire dal quale si inseriscono gli elementi
     * @param c     La collezione da cui prendere gli elementi
     * 
     * @return true
     * @throws NullPointerException      Se la collezione è null
     * @throws IndexOutOfBoundsException Se (index &lt; 0 || index &gt;= size())
     */
    boolean addAll(int index, HCollection c);

    /**
     * Rimuove tutti gli elementi da una lista
     */
    void clear();

    /**
     * Verifica se la lista contiene l'elemento speicificato
     * 
     * @param o l'elemento da verificare
     * @return true se la lista contiene l'elemento, false altrimenti
     */
    boolean contains(Object o);

    /**
     * Verifica se la lista contiene tutti gli elementi della collezione
     * 
     * @param c la lista contenente gli elementi da verificare
     * @return true se la lista contiene tutti gli elementi, false altrimenti
     * @throws NullPointerException se la collezione è null
     */
    boolean containsAll(HCollection c);

    /**
     * Compara l'oggetto specificato con la lista, ritorna vero solo se
     * le due collezioni sono uguali
     * 
     * @param o l'oggetto da confrontare
     * @return true se le due collezioni sono uguali, false altrimenti
     */
    boolean equals(Object o);

    /**
     * Ritorna l'elemento all'indice specificato
     *
     * @param index l'indice dell'elemento
     * @return l'elemento alla posizione specificata
     * @throws IndexOutOfBoundsException se (index &lt; 0 || index &gt;= size())
     */
    Object get(int index);

    /**
     * Ritorna un hash code per la lista.
     * 
     * @return l'hash code della lista.
     */
    int hashCode();

    /**
     * Trova l'indice della prima ricorrenza dell'oggetto specificato nella lista,
     * -1 se non viene trovato alcun elemento
     * 
     * @param o l'oggetto da cercare nella lista
     * @return l'indice dell'oggetto, -1 se non viene trovato
     */
    int indexOf(Object o);

    /**
     * Verifica che la lista sia vuota
     * 
     * @return true se la lista è vuota, false altrimenti
     */
    boolean isEmpty();

    /**
     * Ritorna un iteratore degli elementi della lista
     *
     * @return l'iteratore degli elementi della lista
     */
    HIterator iterator();

    /**
     * Trova l'indice dell'ultima ricorrenza dell'oggetto specificato nella lista,
     * -1 se non viene trovato alcun elemento
     * 
     * @param o l'oggetto da cercare nella lista
     * @return l'indice dell'ultima ricorrenza dell'oggetto, -1 se non viene
     *         trovato
     */
    int lastIndexOf(Object o);

    /**
     * Ritorna un iteratore per la lista
     * 
     * @return l'iteratore degli elementi della lista
     */
    HListIterator listIterator();

    /**
     * Ritorna un iteratore per la lista partendo dall'indice specificato
     * 
     * @param index l'indice da cui iniziare l'iterazione
     * @return l'iteratore degli elementi della lista
     */
    HListIterator listIterator(int index);

    /**
     * Rimuove l'elemento all'indice specificato, tutti gli elementi successivi
     * vengono spostati a sinistra di una posizione
     *
     * @param index l'indice dell'elemento da rimuovere
     * @return l'elemento che è stato rimosso
     * @throws IndexOutOfBoundsException se (index &lt; 0 || index &gt;= size())
     */
    Object remove(int index);

    /**
     * Rimuove la prima occorrenza dell'oggetto specificato, se è
     * presente
     *
     * @param o l'oggetto da rimuovere
     * @return true se l'oggetto viene trovato e rimosso, false altrimenti
     */
    boolean remove(Object o);

    /**
     * Rimuove dalla lista tutti gli elementi che si trovano nella collezione
     * specificata
     *
     * @param c la collezione
     * @return true se la lista viene modificata, false altrimenti
     * @throws NullPointerException se l'oggetto è null
     */
    boolean removeAll(HCollection c);

    /**
     * Mantiene nella lista solamente gli elementi presenti nella collezione
     * specificata, tutti gli altri elementi vengono rimossi
     *
     * @param c la collezione
     * @return true se la lista viene modificata, false altrimenti
     * @throws NullPointerException se l'oggetto è null
     */
    boolean retainAll(HCollection c);

    /**
     * Rimpiazza l'elemento nella posizione specificata con l'elemento specificato
     *
     * @param index   l'indice della posizione
     * @param element l'elemento da inserire nella posizione
     * @return l'elemento precedentemente presente nella posizione specificata
     * @throws IndexOutOfBoundsException se (index &lt; 0 || index &gt;= size())
     */
    Object set(int index, Object element);

    /**
     * Ritorna il numero di elementi contenuti nella lista
     *
     * @return il numero di elementi
     */
    int size();

    /**
     * Ritorna la sottolista [fromIndex, toIndex)
     *
     * @param fromIndex l'indice dell'elemento iniziale della lista
     * @param toIndex   l'indice dell'elemento precedente a quello finale della
     *                  lista
     * @return la sottolista specificata
     * @throws IndexOutOfBoundsException se (fromIndex &lt; 0 || toIndex &gt; size()
     *                                   || fromIndex &gt; toIndex)
     */
    HList subList(int fromIndex, int toIndex);

    /**
     * Ritorna un array contenente tutti gli elementi della lista
     *
     * @return l'array contenente gli elementi della lista
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