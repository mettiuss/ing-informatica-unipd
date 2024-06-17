package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Adattatore che implementa l'interfaccia HList presente nella Java 2 SE 1.4.2,
 * utilizza la classe Vector come struttura dati utilizzando solo i metodi
 * presenti nella CLDC 1.1.
 * 
 * L'implementazione è del tutto simile a quella di ArrayList presente in Java 2
 * SE 1.4.2. Tutte le optional operations sono implementate, e vengono permessi
 * tutti gli oggetti, incluso null.
 * 
 * @author Matteo Cuzzolin (2066701)
 */
public class ListAdapter implements HList {
    /**
     * Adapted Class
     */
    protected Vector v;

    /**
     * Costruisce una lista vuota di capienza iniziale specificata.
     *
     * @param initialCapacity la capacità iniziale della lista.
     * @throws IllegalArgumentException Se la capacità iniziale è negativa
     */
    public ListAdapter(int initialCapacity) {
        v = new Vector(initialCapacity);
    }

    /**
     * Costruisce una lista vuota di capienza iniziale 10.
     */
    public ListAdapter() {
        this(10);
    }

    /**
     * Costruisce una lista a partire dalla struttura dati fornita, la quale deve
     * essere conforme all'interfaccia HCollection
     *
     * @param c la struttura dati HCollection.
     * @throws NullPointerException Se la collection fornita è null
     */
    public ListAdapter(HCollection c) {
        v = new Vector(10);
        HIterator it = c.iterator();

        while (it.hasNext()) {
            add(it.next());
        }
    }

    /**
     * Aggiunge un elemento in coda alla lista
     *
     * @param o l'oggetto da aggiungere
     * @return true
     */
    public boolean add(Object o) {
        add(size(), o);
        return true;
    }

    /**
     * Inserisce l'elemento specificato nell'indice specificato, sposta a destra
     * tutti gli elementi successivi della lista
     *
     * @param index   L'indice a cui inserire l'elemento
     * @param element L'elemento da inserire
     * 
     * @throws IndexOutOfBoundsException Se {@code index < 0 || index > size()}
     */
    public void add(int index, Object element) {
        checkOutOfBoundsforAdd(index);
        v.insertElementAt(element, getIndex(index));
        changeSize(1);
    }

    /**
     * Inserisce tutti gli elementi della Collezione alla fine della lista
     *
     * @param c La collezione da cui prendere gli elementi
     * 
     * @return true
     * @throws NullPointerException Se la collezione è null
     */
    public boolean addAll(HCollection c) {
        HIterator it = c.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }

    /**
     * Inserisce tutti gli elementi della Collezione all'indice specificato della
     * lista
     *
     * @param index L'indice a partire dal quale si inseriscono gli elementi
     * @param c     La collezione da cui prendere gli elementi
     * 
     * @return true
     * @throws NullPointerException      Se la collezione è null
     * @throws IndexOutOfBoundsException Se {@code index < 0 || index >= size()}
     */
    public boolean addAll(int index, HCollection c) {
        checkOutOfBounds(index);

        HIterator it = c.iterator();

        while (it.hasNext()) {
            add(index++, it.next());
        }

        return true;
    }

    /**
     * Rimuove tutti gli elementi di una lista e imposta la dimensione a 0
     */
    public void clear() {
        IteratorAdapter it = iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Verifica se la lista contiene l'elemento speicificato
     * 
     * @param o l'elemento da verificare
     * @return true se la lista contiene l'elemento, false altrimenti
     */
    public boolean contains(Object o) {
        for (int i = startIndex(); i < size(); i++) {
            if (get(i).equals(o))
                return true;
        }
        return false;
    }

    /**
     * Verifica se la lista contiene tutti gli elementi della Collezione
     * 
     * @param c la collezione contenente gli elementi da verificare
     * @return true se la lista contiene gli elementi, false altrimenti
     * @throws NullPointerException se la collection è null
     */
    public boolean containsAll(HCollection c) {
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next()))
                return false;
        }

        return true;
    }

    /**
     * Compara l'oggetto specificato con la lista, ritorna vero solo se l'oggetto è
     * una lista, se entrambe le liste sono della stessa dimensione tutti gli
     * elementi sono uguali indice per indice.
     * 
     * @param o l'oggetto da confrontare
     * @return true se le due liste sono uguali, false altrimenti
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        HIterator it1;
        try {
            it1 = ((HList) o).iterator();
        } catch (ClassCastException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        HIterator it2 = iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
                return false;
            }
        }

        if (it1.hasNext() || it2.hasNext()) {
            return false;
        }

        return true;
    }

    /**
     * Ritorna l'elemento all'indice specificato
     *
     * @param index l'indice dell'elemento
     * @return l'elemento alla posizione specificata
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    public Object get(int index) {
        checkOutOfBounds(index);
        return v.elementAt(getIndex(index));
    }

    /**
     * Ritorna un hash code per la lista.
     * 
     * @return l'hash code della lista.
     */
    public int hashCode() {
        int hashCode = 1;
        HIterator i = iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }

        return hashCode;
    }

    /**
     * Ritorna il numero di elementi contenuti nella lista
     *
     * @return il numero di elementi
     */
    public int size() {
        return v.size();
    }

    /**
     * Ritorna un iteratore degli elementi della lista
     *
     * @return l'iteratore degli elementi della lista
     */
    public IteratorAdapter iterator() {
        return new IteratorAdapter(startIndex());
    }

    /**
     * Verifica che la lista sia vuota
     * 
     * @return true se la lista è vuota, false altrimenti
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Trova l'indice della prima ricorrenza dell'oggetto specificato nella lista,
     * -1 se non viene trovato alcun elemento
     * 
     * @param o l'oggetto da cercare nella lista
     * @return l'indice dell'oggetto, -1 se non viene trovato
     */
    public int indexOf(Object o) {
        IteratorAdapter it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if ((next == null && o == null) || next.equals(o)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Trova l'indice dell'ultima ricorrenza dell'oggetto specificato nella lista,
     * -1 se non viene trovato alcun elemento
     * 
     * @param o l'oggetto da cercare nella lista
     * @return l'indice dell'ultima ricorrenza dell'oggetto, -1 se non viene
     *         trovato
     */
    public int lastIndexOf(Object o) {
        HListIterator it = listIterator(size());

        int i = size();

        while (it.hasPrevious()) {
            i--;
            if (it.previous().equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Ritorna un iteratore per la lista
     * 
     * @return l'iteratore degli elementi della lista
     */
    public HListIterator listIterator() {
        return new ListIteratorAdapter(startIndex());
    }

    /**
     * Ritorna un iteratore per la lista partendo dall'indice specificato
     * 
     * @param index l'indice da cui iniziare l'iterazione
     * 
     * @return l'iteratore degli elementi della lista
     */
    public HListIterator listIterator(int index) {
        return new ListIteratorAdapter(index);
    }

    /**
     * Rimuove l'elemento all'indice specificato, tutti gli elementi successivi
     * vengono spostati a sinistra di una posizione
     *
     * @param index l'indice dell'elemento da rimuovere
     * @return l'elemento che è stato rimosso
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    public Object remove(int index) {
        checkOutOfBounds(index);
        Object obj = get(index);
        v.removeElementAt(index);
        changeSize(-1);
        return obj;
    }

    /**
     * Rimuove la prima occorrenza dell'oggetto specificato, se è
     * presente, tutti gli elementi successivi vengono spostati a sinistra di una
     * posizione
     *
     * @param o l'oggetto da rimuovere
     * @return true se l'oggetto viene trovato e rimosso, false altrimenti
     */
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1)
            return false;
        remove(index);
        return true;
    }

    /**
     * Rimuove dalla lista tutti gli elementi che si trovano nella collezione
     * specificata
     *
     * @param c la collezione
     * @return true se la lista viene modificata, false altrimenti
     * @throws NullPointerException se l'oggetto è null
     */
    public boolean removeAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = iterator();
        boolean changed = false;

        while (it.hasNext()) {
            Object el = it.next();
            if (c.contains(el)) {
                it.remove();
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Mantiene nella lista solamente gli elementi presenti nella collezione
     * specificata, tutti gli altri elementi vengono rimossi
     *
     * @param c la collezione
     * @return true se la lista viene modificata, false altrimenti
     * @throws NullPointerException se l'oggetto è null
     */
    public boolean retainAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator it = iterator();
        boolean changed = false;

        while (it.hasNext()) {
            Object el = it.next();
            if (!c.contains(el)) {
                it.remove();
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Rimpiazza l'elemento nella posizione specificata con l'elemento specificato
     *
     * @param index   l'indice della posizione
     * @param element l'elemento da inserire nella posizione
     * @return l'elemento precedentemente presente nella posizione specificata
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    public Object set(int index, Object element) {
        checkOutOfBounds(index);
        Object obj = v.elementAt(index);
        v.setElementAt(element, index);
        return obj;
    }

    /**
     * Ritorna la sottolista [fromIndex, toIndex), la sottolista fa riferimento alla
     * stessa struttura dati della list corrente, questo significa che modifiche non
     * strutturali vengono riflesse dalla sottolista alla lista e viceversa.
     *
     * @param fromIndex l'indice dell'elemento iniziale della lista
     * @param toIndex   l'indice dell'elemento precedente a quello finale della
     *                  lista
     * @return la sottolista specificata
     * @throws IndexOutOfBoundsException se
     *                                   {@code from < 0 || toIndex > size() || fromIndex > toIndex}
     */
    public SubListAdapter subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();

        return new SubListAdapter(v, fromIndex, toIndex);
    }

    /**
     * Ritorna un array contenente tutti gli elementi della lista
     *
     * @return l'array contenente gli elementi della lista
     */
    public Object[] toArray() {
        Object[] obj = new Object[size()];
        HIterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            obj[i] = it.next();
            i++;
        }

        return obj;
    }

    /**
     * Ritorna un array contenente tutti gli elementi della lista utilizzando come
     * tipo quello specificato dall'array a
     *
     * @param a L'array che specifica il tipo dell'array ritornato
     * @return l'array contenente gli elementi della lista del tipo specificato
     */
    public Object[] toArray(Object[] a) {
        if (a.length < size()) {
            a = new Object[size()];

        }

        for (int i = 0; i < size(); i++) {
            a[i] = get(i);
        }

        return a;
    }

    /**
     * Ritorna l'indice di inizio della struttura dati
     * 
     * @return l'indice di inizio della struttura dati
     */
    protected int startIndex() {
        return 0;
    }

    /**
     * Verifica che l'indice sia {@code index >= 0 && index < size()}
     * 
     * @param index l'indice da verificare
     * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
     */
    protected void checkOutOfBounds(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Verifica che l'indice sia {@code index >= 0 && index <= size()}
     * 
     * @param index l'indice da verificare
     * @throws IndexOutOfBoundsException se {@code index < 0 && index > size()}
     */
    protected void checkOutOfBoundsforAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
    }

    /**
     * Aggiorna la dimensione della struttura dati
     * 
     * @param amount l'entita del cambiamento da apportare
     */
    protected void changeSize(int amount) {
    }

    /**
     * In questo caso ritorna lo stesso valore inserito, ma può essere usata per
     * introdurre un offset
     * 
     * @param index l'indice
     * @return lo stesso valore
     */
    protected int getIndex(int index) {
        return index;
    }

    /**
     * Adattatore che implementa l'interfaccia HIterator utilizzando
     * solo i metodi presenti nella CLDC 1.1
     */
    private class IteratorAdapter implements HIterator {
        /**
         * Indice dell'elemento precedentemente ritornato da next()
         */
        protected int lastReturned = -1;
        /**
         * Indice dell'attuale posizione dell'iteratore
         */
        protected int cursor;

        /**
         * Costruisce un iteratore
         *
         * @param cursor L'indice di partenza dell'iterazione
         * @throws IndexOutOfBoundsException se {@code index < 0 || index > size()}
         */
        public IteratorAdapter(int cursor) {
            checkOutOfBoundsforAdd(cursor);
            this.cursor = cursor;
        }

        /**
         * Verifica se l'iterazione ha un elemento successivo.
         * 
         * @return true se l'iterazione ha un elemento successivo, false altrimenti
         */
        public boolean hasNext() {
            return cursor < size();
        }

        /**
         * Ritorna il successivo elemento dell'iterazione
         * 
         * @return l'elemento successivo
         * @throws NoSuchElementException Se non esiste l'elemento successivo
         */
        public Object next() {
            if (!this.hasNext())
                throw new NoSuchElementException();
            Object currObj = get(cursor);
            lastReturned = cursor;
            cursor++;
            return currObj;
        }

        /**
         * Elimina l'ultimo elemento che è stato fornito dal metodo next() dalla
         * collezione sottostante. Questo metodo può essere chiamato solo una volta per
         * ogni next().
         * 
         * @throws IllegalStateException Se il metodo viene chiamato prima di next()
         *                               oppure se viene chiamato più volte.
         */
        public void remove() {
            if (lastReturned == -1)
                throw new IllegalStateException();

            v.removeElementAt(lastReturned);
            changeSize(-1);

            if (cursor != 0)
                cursor--;
            lastReturned = -1;
        }
    }

    /**
     * Adattatore che implementa l'interfaccia HListIterator, utilizzando solo i
     * metodi presenti nella CLDC 1.1
     */
    private class ListIteratorAdapter extends IteratorAdapter implements HListIterator {
        /**
         * Costruisce un iteratore
         *
         * @param cursor L'indice di partenza dell'iterazione
         * @throws IndexOutOfBoundsException se {@code index < 0 || index >= size()}
         */
        public ListIteratorAdapter(int cursor) {
            super(cursor);
        }

        /**
         * Aggiunge un elemento nella lista originale, l'elemento viene inserito
         * immediatamente prima dell'elemento che verrebbe ritornato dalla chiamata a
         * next(), se presente. Altrimenti viene inserito immediatamente dopo l'elemento
         * che verrebbe ritornato dalla chiamata a previous(), se presente.
         * Se la lista è vuota viene inserito come unico elemento
         *
         * @param o L'elemento da aggiungere alla lista.
         */
        public void add(Object o) {
            v.insertElementAt(o, getIndex(cursor));
            changeSize(1);
            cursor++;
        }

        /**
         * Verifica se l'iterazione ha un elemento precendente.
         * 
         * @return true se l'iterazione ha un elemento precedente, false altrimenti.
         */
        public boolean hasPrevious() {
            return cursor != 0;
        }

        /**
         * RItorna l'indice dell'elemento che verrebbe ritornato se si chiamasse next()
         * 
         * @return l'indirizzo dell'elemento che sarebbe ritornato da una chiamata a
         *         next(), oppure la lunghezza della lista se ci si trova alla fine
         *         dell'iterazione
         */
        public int nextIndex() {
            return cursor;
        }

        /**
         * Ritorna il precedente elemento dell'iterazione
         * 
         * @return l'elemento precendente
         * @throws NoSuchElementException Se non esiste l'elemento precedente
         */
        public Object previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            cursor--;
            lastReturned = cursor;
            return v.elementAt(cursor);
        }

        /**
         * Ritorna l'indice dell'elemento che verrebbe ritornato se si chiamasse
         * previous()
         * 
         * @return l'indirizzo dell'elemento che sarebbe ritornato da una chiamata a
         *         previous(), oppure -1 se ci si trova all'inizio dell'iterazione
         */
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * Rimpiazza l'ultimo elemento ritornato da next() o previous()
         * 
         * @param o L'oggetto con cui rimpiazzare l'elemento
         * @throws IllegalStateException se next() o previous() non sono stati chiamati
         *                               in precedenza
         */
        public void set(Object o) {
            if (lastReturned == -1)
                throw new IllegalStateException();
            v.setElementAt(o, lastReturned);
        }
    }

    /**
     * Classe privata di supporto al metodo subList()
     */
    private class SubListAdapter extends ListAdapter {
        /**
         * Indice di inizio della struttura dati
         */
        private int startIndex;

        /**
         * Dimensione della struttura dati
         */
        private int size;

        /**
         * Costruttore
         * 
         * @param vect  struttura dati a cui fare riferimento
         * @param start indice di inizio della sublist
         * @param end   indice di fine della sublist
         */
        public SubListAdapter(Vector vect, int start, int end) {
            v = vect;
            startIndex = start;
            size = end - start;
        }

        /**
         * Override del metodo size
         * 
         * @return la dimensione della sottolista
         */
        public int size() {
            return size;
        }

        /**
         * Ritorna l'indice di inizio della sottolista
         * 
         * @return l'indice di inizio della sottolista
         */
        protected int startIndex() {
            return startIndex;
        }

        /**
         * Permette di cambiare le dimensioni della sottolista
         */
        protected void changeSize(int amount) {
            size = size + amount;
        }

        /**
         * Permette di introdurre un offset agli indici
         * 
         * @return l'indice con l'offset applicato
         */
        protected int getIndex(int index) {
            return startIndex + index;
        }
    }
}
