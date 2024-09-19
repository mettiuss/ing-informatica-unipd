package myAdapter;

import java.util.AbstractCollection;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * <p>
 * La classe {@code Vector} implementa un array dinamico di oggetti. Come un array, contiene
 * componenti che possono essere accessibili utilizzando un indice intero. Tuttavia, la dimensione
 * di un {@code Vector} può aumentare o diminuire secondo necessità per permettere l'aggiunta e la
 * rimozione di elementi dopo la creazione del {@code Vector}.
 * </p>
 * 
 * <p>
 * Ogni {@code Vector} cerca di ottimizzare la gestione della memoria mantenendo una capacità e un
 * {@code capacityIncrement}. La capacità è sempre almeno grande quanto la dimensione del
 * {@code Vector}; è solitamente maggiore perché, quando vengono aggiunti componenti al
 * {@code Vector}, la memoria viene aumentata in blocchi della dimensione di
 * {@code capacityIncrement}. Un'applicazione può aumentare la capacità di un {@code Vector} prima
 * di inserire un grande numero di componenti; ciò riduce la quantità di riallocazioni incrementali.
 * </p>
 * 
 * @author Matteo Cuzzolin (matr: 2066701)
 */
public class VectorAdapter implements HList {
    /**
     * Adapted Class
     */
    protected Vector vector;

    /**
     * Aggiorna la dimensione della struttura dati (disponibile solo per SubList)
     * 
     * @param newSize la nuova dimensione della struttura dati.
     */
    protected void changeSize(int newSize) {}

    /**
     * Ritorna l'indice di inizio della struttura dati
     * 
     * @return l'indice di inizio della struttura dati
     */
    protected int startIndex() {
        return 0;
    }

    /**
     * Costruisce un {@code Vector} vuoto con la capacità iniziale e l'incremento di capacità
     * specificati.
     *
     * @param initialCapacity la capacità iniziale del {@code Vector}.
     * @param capacityIncrement l'ammontare con cui la capacità viene aumentata quando il
     *        {@code Vector} supera il limite.
     * @throws IllegalArgumentException se la capacità iniziale specificata è negativa.
     */
    public VectorAdapter(int initialCapacity, int capacityIncrement) {
        vector = new Vector(initialCapacity, capacityIncrement);
    }

    /**
     * Costruisce un {@code Vector} vuoto con la capacità iniziale specificata e con l'incremento di
     * capacità uguale a zero.
     *
     * @param initialCapacity la capacità iniziale del {@code Vector}.
     * @throws IllegalArgumentException se la capacità iniziale specificata è negativa.
     */
    public VectorAdapter(int initialCapacity) {
        this(initialCapacity, 0);
    }

    /**
     * Costruisce un {@code Vector} vuoto in modo che il suo array di dati interno abbia una
     * dimensione di 10 e il suo incremento di capacità standard sia zero.
     */
    public VectorAdapter() {
        this(10, 0);
    }

    /**
     * Costruisce un {@code Vector} contenente gli elementi della collezione specificata,
     * nell'ordine in cui sono restituiti dall'iterator della collezione.
     *
     * @param c la collezione i cui elementi devono essere inseriti in questo {@code Vector}.
     * @throws NullPointerException se la collezione specificata è nulla.
     */
    public VectorAdapter(HCollection c) {
        if (c == null)
            throw new NullPointerException();

        vector = new Vector(c.size(), 0);
        addAll(c);
    }

    /**
     * Copia i componenti di questo {@code Vector} nell'array specificato. L'elemento all'indice k
     * in questo {@code Vector} viene copiato nel componente {@code k} di {@code anArray}. L'array
     * deve essere abbastanza grande da contenere tutti gli oggetti in questo {@code Vector},
     * altrimenti viene sollevata un'eccezione {@link IndexOutOfBoundsException}.
     *
     * @param anArray l'array in cui i componenti vengono copiati.
     * @throws NullPointerException se l'array fornito è {@code null}.
     * @throws IndexOutOfBoundsException se l'array non è abbastanza grande
     */
    public void copyInto(Object[] anArray) {
        if (anArray == null)
            throw new NullPointerException();
        if (anArray.length < size())
            throw new IndexOutOfBoundsException();

        HIterator iterator = iterator();
        int index = 0;
        while (iterator.hasNext()) {
            anArray[index++] = iterator.next();
        }
    }

    /**
     * Riduce la {@code capacity} di questo {@code Vector} alla {@code size} attuale del
     * {@code Vector}. Se la {@code capacity} di questo {@code Vector} è maggiore della sua
     * {@code size} attuale, la {@code capacity} viene cambiata per eguagliare la {@code size}
     * sostituendo il suo array di dati interno, conservato nel campo {@code elementData}, con uno
     * più piccolo. Un'applicazione può utilizzare questa operazione per minimizzare l'uso della
     * memoria di un {@code Vector}.
     */
    public void trimToSize() {
        vector.trimToSize();
    }

    /**
     * <p>
     * Aumenta la capacità di questo {@code Vector}, se necessario, per garantire che possa
     * contenere almeno il numero di componenti specificato dal parametro {@code minCapacity}.
     * </p>
     * <p>
     * Se la capacità corrente di questo {@code Vector} è inferiore a {@code minCapacity}, allora la
     * sua capacità viene aumentata sostituendo il suo array di dati interno, conservato nel campo
     * elementData, con uno più grande. La dimensione del nuovo array di dati sarà la dimensione
     * precedente più {@code capacityIncrement}, a meno che il valore di {@code capacityIncrement}
     * sia minore o uguale a zero, nel qual caso la nuova capacità sarà il doppio della capacità
     * precedente; ma se questa nuova dimensione è ancora inferiore a {@code minCapacity}, allora la
     * nuova capacità sarà {@code minCapacity}.
     * </p>
     * 
     * @param minCapacity la capacità minima desiderata.
     */
    public void ensureCapacity(int minCapacity) {
        vector.ensureCapacity(minCapacity);
    }

    /**
     * Imposta la {@code size} di questo {@code Vector}. Se la nuova {@code size} è maggiore della
     * {@code size} attuale, nuovi elementi {@code null} vengono aggiunti alla fine del
     * {@code Vector}. Se la nuova {@code size} è inferiore alla {@code size} attuale, tutti i
     * componenti i cui indici sono maggiori o uguali a {@code newSize} vengono scartati.
     *
     * @param newSize la nuova {@code size} di questo {@code Vector}.
     * @throws ArrayIndexOutOfBoundsException se la nuova {@code size} è negativa.
     */
    public void setSize(int newSize) {
        if (newSize < 0)
            throw new ArrayIndexOutOfBoundsException();
        if (newSize > size()) {
            for (int i = newSize - size(); i > 0; i--) {
                add(null);
            }
        }
        if (newSize < size()) {
            for (int i = size() - newSize; i > 0; i--) {
                remove(size() - 1);
            }
        }
    }

    /**
     * Restituisce la {@code capacity} corrente di questo {@code Vector}.
     *
     * @return la {@code capacity} corrente (la lunghezza del suo array di dati interno, conservato
     *         nel campo {@code elementData} di questo {@code Vector}).
     */
    public int capacity() {
        return vector.capacity();
    }

    /**
     * Restituisce il numero di componenti in questo {@code Vector}.
     *
     * @return il numero di componenti in questo {@code Vector}.
     */
    @Override
    public int size() {
        return vector.size();
    }

    /**
     * Verifica se questo {@code Vector} non ha componenti.
     *
     * @return {@code true} se e solo se questo {@code Vector} non ha componenti, cioè, la sua
     *         dimensione è zero; {@code false} altrimenti.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Restituisce un'enumerazione dei componenti di questo {@code Vector}. L'oggetto Enumeration
     * restituito genererà tutti gli elementi di questo {@code Vector}. Il primo elemento generato è
     * l'elemento all'indice {@code 0}, poi l'elemento all'indice {@code 1}, e così via.
     *
     * @return un'enumerazione dei componenti di questo {@code Vector}.
     * @see Enumeration
     */
    public Enumeration elements() {
        return new EnumerationAdapter();
    }

    /**
     * Verifica se l'oggetto specificato è un componente di questo {@code Vector}.
     *
     * @param elem l'oggetto da verificare.
     * @return {@code true} se e solo se l'oggetto specificato è lo stesso di un componente in
     *         questo {@code Vector}, come determinato dal metodo {@code equals}; {@code false}
     *         altrimenti.
     */
    @Override
    public boolean contains(Object elem) {
        return indexOf(elem) != -1;
    }

    /**
     * Cerca la prima occorrenza dell'argomento dato, testando l'uguaglianza utilizzando il metodo
     * {@code equals}.
     *
     * @param elem l'oggetto da cercare.
     * @return l'indice della prima occorrenza dell'argomento in questo {@code Vector}, ossia il
     *         valore più piccolo {@code k} per cui {@code elem.equals(elementData[k])} è vero;
     *         restituisce {@code -1} se l'oggetto non viene trovato.
     * @see Object#equals(Object)
     */
    @Override
    public int indexOf(Object elem) {
        return indexOf(elem, 0);
    }

    /**
     * Cerca la prima occorrenza dell'argomento dato, iniziando la ricerca dall'indice specificato,
     * e testando l'uguaglianza utilizzando il metodo {@code equals}.
     *
     * @param elem l'oggetto da cercare.
     * @param index l'indice non negativo da cui iniziare la ricerca.
     * @return l'indice della prima occorrenza dell'argomento in questo {@code Vector} a partire
     *         dalla posizione index o successivamente nel {@code Vector}, ossia il valore più
     *         piccolo {@code k} per cui {@code elem.equals(elementData[k]) && (k >= index)} è vero;
     *         restituisce {@code -1} se l'oggetto non viene trovato. (Restituisce -1 se index >= la
     *         {@code size} corrente di questo Vector.)
     * @throws IndexOutOfBoundsException se l'indice è negativo.
     * @see Object#equals(Object)
     */
    public int indexOf(Object elem, int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();


        for (int i = index; i < size(); i++) {
            if (elem == null ? get(i) == null : elem.equals(get(i)))
                return i;
        }
        return -1;
    }

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'oggetto specificato in questo
     * {@code Vector}.
     *
     * @param elem il componente desiderato.
     * @return l'indice dell'ultima occorrenza dell'oggetto specificato in questo {@code Vector},
     *         ossia il valore più grande {@code k} per cui {@code elem.equals(elementData[k])} è
     *         vero; restituisce {@code -1} se l'oggetto non viene trovato.
     */
    @Override
    public int lastIndexOf(Object elem) {
        return lastIndexOf(elem, size() - 1);
    }

    /**
     * Cerca all'indietro l'oggetto specificato, partendo dall'indice specificato, e restituisce
     * l'indice corrispondente.
     *
     * @param elem il componente desiderato.
     * @param index l'indice da cui iniziare la ricerca.
     * @return l'indice dell'ultima occorrenza dell'oggetto specificato in questo {@code Vector}
     *         alla posizione minore o uguale a {@code index} nel {@code Vector}, ossia il valore
     *         più grande {@code k} per cui {@code elem.equals(elementData[k]) && (k <= index)} è
     *         vero; restituisce {@code -1} se l'oggetto non viene trovato. (Restituisce {@code -1}
     *         se {@code index} è negativo.)
     * @throws IndexOutOfBoundsException se {@code index} è maggiore o uguale alla {@code size}
     *         corrente di questo {@code Vector}.
     */
    public int lastIndexOf(Object elem, int index) {
        if (index >= size())
            throw new IndexOutOfBoundsException();

        for (int i = index; i >= 0; i--) {
            if (elem == null ? get(i) == null : elem.equals(get(i)))
                return i;
        }

        return -1;
    }

    /**
     * Restituisce il componente all'indice specificato. Questo metodo è identico nella funzionalità
     * al metodo {@link VectorAdapter#get(int)} (che fa parte dell'interfaccia {@code List}).
     *
     * @param index un indice all'interno di questo {@code Vector}.
     * @return il componente all'indice specificato.
     * @throws ArrayIndexOutOfBoundsException se l'indice è negativo o non minore della dimensione
     *         corrente di questo oggetto {@code Vector}.
     * @see #get(int)
     * @see List
     */
    public Object elementAt(int index) {
        return get(index);
    }

    /**
     * Restituisce il primo componente (l'elemento all'indice {@code 0}) di questo {@code Vector}.
     *
     * @return il primo componente di questo {@code Vector}.
     * @throws NoSuchElementException se questo {@code Vector} non ha componenti.
     */
    public Object firstElement() {
        try {
            return get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Restituisce l'ultimo componente del {@code Vector}.
     *
     * @return l'ultimo componente del {@code Vector}, ossia il componente all'indice
     *         {@code size() - 1}.
     * @throws NoSuchElementException se questo {@code Vector} è vuoto.
     */
    public Object lastElement() {
        try {
            return get(size() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Imposta il componente all'indice specificato di questo {@code Vector} come l'oggetto
     * specificato. Il componente precedente in quella posizione viene scartato. L'indice deve
     * essere un valore maggiore o uguale a 0 e minore della dimensione corrente del {@code Vector}.
     *
     * Questo metodo è identico nella funzionalità al metodo {@link VectorAdapter#set(int, Object)}
     * (che fa parte dell'interfaccia {@code List}). Si noti che il metodo set inverte l'ordine dei
     * parametri, per corrispondere più strettamente all'uso degli array. Si noti inoltre che il
     * metodo set restituisce il valore precedente che era memorizzato nella posizione specificata.
     *
     * @param obj il valore con cui impostare il componente.
     * @param index l'indice specificato.
     * @throws ArrayIndexOutOfBoundsException se l'indice è invalido.
     * @see #size()
     * @see List
     * @see List#set(int, java.lang.Object)
     */
    public void setElementAt(Object obj, int index) {
        set(index, obj);
    }

    /**
     * <p>
     * Elimina il componente all'indice specificato. Ogni componente in questo vettore con un indice
     * maggiore o uguale all'indice specificato viene spostato verso il basso per avere un indice
     * inferiore di uno rispetto al valore precedente. La dimensione di questo vettore viene ridotta
     * di 1.
     * </p>
     * 
     * <p>
     * L'indice deve essere un valore maggiore o uguale a 0 e inferiore alla dimensione attuale del
     * vettore.
     * </p>
     * 
     * <p>
     * Questo metodo è identico per funzionalità al metodo {@link VectorAdapter#remove(int)}, che fa
     * parte dell'interfaccia {@code List}. Si noti che il metodo {@code remove} restituisce il
     * vecchio valore che era memorizzato nella posizione specificata.
     * </p>
     * 
     * @param index l'indice dell'oggetto da rimuovere.
     * @throws ArrayIndexOutOfBoundsException se l'indice è invalido.
     * @see #size()
     * @see #remove(int)
     * @see java.util.List
     */
    public void removeElementAt(int index) {
        remove(index);
    }

    /**
     * <p>
     * Inserisce l'oggetto specificato come componente in questo {@code Vector} all'indice
     * specificato. Ogni componente in questo {@code Vector} con un indice maggiore o uguale
     * all'indice specificato viene spostato verso l'alto per avere un indice uno maggiore rispetto
     * a quello che aveva precedentemente.
     * </p>
     * <p>
     * L'indice deve essere un valore maggiore o uguale a 0 e minore o uguale alla dimensione
     * corrente del {@code Vector}. (Se l'indice è uguale alla dimensione corrente del
     * {@code Vector}, il nuovo elemento viene aggiunto al {@code Vector}.)
     * </p>
     * <p>
     * Questo metodo è identico nella funzionalità al metodo {@link VectorAdapter#add(int, Object)}
     * (che fa parte dell'interfaccia {@code List}). Si noti che il metodo add inverte l'ordine dei
     * parametri, per corrispondere più strettamente all'uso degli array.
     * </p>
     * 
     * @param obj il componente da inserire.
     * @param index l'indice dove inserire il nuovo componente.
     * @throws ArrayIndexOutOfBoundsException se l'indice è invalido.
     * @see #size()
     * @see #add(int, Object)
     * @see List
     */
    public void insertElementAt(Object obj, int index) {
        add(index, obj);
    }

    /**
     * Aggiunge il componente specificato alla fine di questo {@code Vector}, aumentando la sua
     * dimensione di uno. La capacità di questo {@code Vector} viene aumentata se la sua dimensione
     * diventa maggiore della sua capacità. Questo metodo è identico nella funzionalità al metodo
     * {@link VectorAdapter#add(Object)} (che fa parte dell'interfaccia List).
     *
     * @param obj il componente da aggiungere.
     * @see #add(Object)
     * @see List
     */
    public void addElement(Object obj) {
        add(size(), obj);
    }

    /**
     * Rimuove la prima occorrenza (con l'indice più basso) dell'argomento da questo {@code Vector}.
     * Se l'oggetto viene trovato in questo {@code Vector}, ogni componente con un indice maggiore o
     * uguale all'indice dell'oggetto viene spostata verso il basso, riducendo di uno il loro
     * indice. Questo metodo è identico nella funzionalità al metodo
     * {@link VectorAdapter#remove(Object)} (che fa parte dell'interfaccia {@code List}).
     *
     * @param obj il componente da rimuovere.
     * @return {@code true} se l'argomento era un componente di questo {@code Vector}; {@code false}
     *         altrimenti.
     * @see List#remove(Object)
     */
    public boolean removeElement(Object obj) {
        return remove(obj);
    }

    /**
     * Rimuove tutti i componenti da questo {@code Vector} e imposta la sua dimensione a zero.
     * Questo metodo è identico nella funzionalità al metodo {@link VectorAdapter#clear()} (che fa
     * parte dell'interfaccia {@code List}).
     *
     * @see #clear()
     * @see List
     */
    public void removeAllElements() {
        clear();
    }

    /**
     * Restituisce una copia di questo {@code Vector}. La copia conterrà un riferimento a una copia
     * dell'array di dati interno, non a un riferimento all'array di dati interno originale di
     * questo oggetto {@code Vector}.
     *
     * @return una copia di questo {@code Vector}.
     * @see Cloneable
     * @see java.lang.Object#clone()
     */
    public Object clone() {
        VectorAdapter vect = new VectorAdapter(Math.max(size(), 10));
        HIterator iterator = iterator();
        while (iterator.hasNext()) {
            vect.add(iterator.next());
        }
        return vect;
    }

    /**
     * Restituisce un array contenente tutti gli elementi in questo {@code Vector} nell'ordine
     * corretto.
     *
     * @return un array contenente tutti gli elementi in questa collezione.
     */
    @Override
    public Object[] toArray() {
        Object[] obj = new Object[size()];
        HIterator iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            obj[i] = iterator.next();
            i++;
        }
        return obj;
    }

    /**
     * <p>
     * Restituisce un array contenente tutti gli elementi in questo {@code Vector} nell'ordine
     * corretto; il tipo runtime dell'array restituito è quello dell'array specificato. Se il
     * {@code Vector} entra nell'array specificato, questo viene restituito. In caso contrario,
     * viene allocato un nuovo array con il tipo runtime dell'array specificato e la dimensione di
     * questo {@code Vector}.
     * </p>
     * <p>
     * Se il {@code Vector} entra nell'array specificato con spazio in più (ad esempio, l'array ha
     * più elementi del {@code Vector}), l'elemento dell'array immediatamente successivo alla fine
     * del {@code Vector} viene impostato su {@code null}. Questo è utile per determinare la
     * lunghezza del {@code Vector} solo se chi chiama il metodo sa che il {@code Vector} non
     * contiene alcun elemento {@code null}.
     * </p>
     * 
     * @param a l'array in cui gli elementi del {@code Vector} devono essere memorizzati, se è
     *        abbastanza grande; altrimenti, viene allocato un nuovo array dello stesso tipo runtime
     *        per questo scopo.
     * @return un array contenente gli elementi del {@code Vector}.
     * @throws ArrayStoreException se il tipo runtime di {@code a} non è un supertipo del tipo
     *         runtime di ogni elemento in questo {@code Vector}.
     * @throws NullPointerException se l'array fornito è {@code null}.
     */
    @Override
    public Object[] toArray(Object[] a) {
        if (a == null)
            throw new NullPointerException();

        if (a.length < size())
            a = new Object[size()];

        HIterator iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            a[i] = iterator.next();
            i++;
        }

        while (i < a.length) {
            a[i] = null;
            i++;
        }

        return a;
    }

    /**
     * Restituisce l'elemento alla posizione specificata in questo {@code Vector}.
     *
     * @param index indice dell'elemento da restituire.
     * @return l'oggetto all'indice specificato.
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dall'intervallo
     *         {@code (indice < 0 || indice >= size())}.
     */
    @Override
    public Object get(int index) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        return vector.elementAt(startIndex() + index);
    }

    /**
     * Sostituisce l'elemento alla posizione specificata in questo {@code Vector} con l'elemento
     * specificato.
     *
     * @param index l'indice dell'elemento da sostituire.
     * @param element l'elemento da memorizzare nella posizione specificata.
     * @return l'elemento precedentemente presente nella posizione specificata.
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dall'intervallo
     *         {@code (index < 0 || index >= size())}.
     */
    @Override
    public Object set(int index, Object element) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        Object obj = get(index);
        vector.setElementAt(element, index + startIndex());
        return obj;
    }

    /**
     * Aggiunge l'elemento specificato alla fine di questo {@code Vector}.
     *
     * @param o l'elemento da aggiungere a questo {@code Vector}.
     * @return {@code true} (secondo il contratto generale di {@code HCollection.add}).
     */
    @Override
    public boolean add(Object o) {
        add(size(), o);
        return true;
    }

    /**
     * Rimuove la prima occorrenza dell'elemento specificato in questo {@code Vector}. Se il
     * {@code Vector} non contiene l'elemento, rimane invariato. Più formalmente, rimuove l'elemento
     * con l'indice più basso i tale che {@code (o==null ? get(i)==null : o.equals(get(i))}) (se
     * tale elemento esiste).
     *
     * @param o l'elemento da rimuovere da questo {@code Vector}, se presente.
     * @return {@code true} se il {@code Vector} conteneva l'elemento specificato.
     */
    @Override
    public boolean remove(Object o) {
        HIterator iterator = iterator();
        while (iterator.hasNext()) {
            if (o == null ? iterator.next() == null : iterator.next().equals(o)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Inserisce l'elemento specificato nella posizione specificata di questo {@code Vector}. Sposta
     * l'elemento attualmente presente in quella posizione (se presente) e gli eventuali elementi
     * successivi verso destra (incrementa di uno i loro indici).
     *
     * @param index l'indice alla quale inserire l'elemento specificato.
     * @param element l'elemento da inserire.
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dall'intervallo
     *         {@code (index < 0 || index > size())}.
     */
    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();
        vector.insertElementAt(element, startIndex() + index);
        changeSize(size() + 1);
    }

    /**
     * Rimuove l'elemento alla posizione specificata in questo {@code Vector}. Sposta gli eventuali
     * elementi successivi a sinistra (sottrae uno dai loro indici). Restituisce l'elemento che è
     * stato rimosso dal {@code Vector}.
     *
     * @param index l'indice dell'elemento da rimuovere.
     * @return l'elemento che è stato rimosso.
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dall'intervallo
     *         {@code (index < 0 || index >= size())}.
     */
    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException();
        Object element = vector.elementAt(startIndex() + index);
        vector.removeElementAt(startIndex() + index);
        changeSize(size() - 1);
        return element;
    }

    /**
     * Rimuove tutti gli elementi da questo Vector. Il Vector sarà vuoto dopo il ritorno di questa
     * chiamata (a meno che non venga lanciata un'eccezione).
     */
    @Override
    public void clear() {
        HIterator iterator = iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        changeSize(0);
    }

    /**
     * Restituisce {@code true} se questo vector contiene tutti gli elementi nella collezione
     * specificata.
     *
     * @param c una collezione i cui elementi saranno testati per la loro presenza in questo vector.
     * @return {@code true} se questo vector contiene tutti gli elementi nella collezione
     *         specificata.
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see AbstractCollection#contains(Object)
     */
    @Override
    public boolean containsAll(HCollection c) {
        HIterator iterator = c.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next()))
                return false;
        }
        return true;
    }

    /**
     * Aggiunge tutti gli elementi nella collezione specificata alla fine di questo {@code Vector},
     * nell'ordine in cui sono restituiti dall'iterator della collezione specificata. Il
     * comportamento di questa operazione è indefinito se la collezione specificata viene modificata
     * mentre l'operazione è in corso. (Questo implica che il comportamento di questa chiamata è
     * indefinito se la collezione specificata è questo {@code Vector} e il {@code Vector} non è
     * vuoto.)
     *
     * @param c gli elementi da inserire in questo {@code Vector}.
     * @return true se questo {@code Vector} è cambiato a seguito della chiamata.
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see AbstractCollection#add(Object)
     */
    @Override
    public boolean addAll(HCollection c) {
        return addAll(size(), c);
    }

    /**
     * Rimuove da questo {@code Vector} tutti i suoi elementi che sono contenuti nella collezione
     * specificata.
     *
     * @param c una collezione di elementi da rimuovere dal {@code Vector}.
     * @return {@code true} se questo {@code Vector} è stato modificato a seguito della chiamata.
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see AbstractCollection#remove(Object)
     * @see AbstractCollection#contains(Object)
     */
    @Override
    public boolean removeAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator iterator = c.iterator();
        boolean changed = false;
        while (iterator.hasNext()) {
            Object element = iterator.next();

            while (indexOf(element) != -1) {
                remove(indexOf(element));
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Mantiene solo gli elementi in questo {@code Vector} che sono contenuti nella collezione
     * specificata. In altre parole, rimuove da questo {@code Vector} tutti i suoi elementi che non
     * sono contenuti nella collezione specificata.
     *
     * @param c una collezione di elementi da mantenere in questo {@code Vector} (tutti gli altri
     *        elementi vengono rimossi).
     * @return {@code true} se questo {@code Vector} è stato modificato a seguito della chiamata.
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see AbstractCollection#remove(Object)
     * @see AbstractCollection#contains(Object)
     */
    @Override
    public boolean retainAll(HCollection c) {
        if (c == null)
            throw new NullPointerException();
        HIterator iterator = iterator();
        boolean changed = false;
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Inserisce tutti gli elementi nella collezione specificata in questo {@code Vector} nella
     * posizione specificata. Sposta l'elemento attualmente presente in quella posizione (se
     * presente) e gli eventuali elementi successivi verso destra (aumenta i loro indici). I nuovi
     * elementi appariranno nel {@code Vector} nell'ordine in cui sono restituiti dall'iterator
     * della collezione specificata.
     *
     * @param index l'indice al quale inserire il primo elemento della collezione specificata.
     * @param c gli elementi da inserire in questo {@code Vector}.
     * @return true se questo {@code Vector} è cambiato a seguito della chiamata.
     * @throws ArrayIndexOutOfBoundsException se l'indice è fuori dall'intervallo
     *         {@code (index < 0 || index > size())}.
     * @throws NullPointerException se la collezione specificata è {@code null}.
     */
    @Override
    public boolean addAll(int index, HCollection c) {
        if ((index < 0 || index > size()))
            throw new ArrayIndexOutOfBoundsException();
        if (c == null)
            throw new NullPointerException();
        Object[] elements = c.toArray();
        boolean changed = false;
        for (int i = 0; i < elements.length; i++) {
            add(index + i, elements[i]);
            changed = true;
        }
        return changed;
    }

    /**
     * Confronta l'oggetto specificato con questo {@code Vector} per verificarne l'uguaglianza.
     * Restituisce {@code true} se e solo se l'oggetto specificato è anch'esso una {@code List},
     * entrambe le {@code List} hanno la stessa dimensione, e tutte le coppie corrispondenti di
     * elementi nelle due {@code List} sono uguali. (Due elementi {@code e1} ed {@code e2} sono
     * uguali se {@code (e1==null ? e2==null : e1.equals(e2))}.) In altre parole, due {@code List}
     * sono definite uguali se contengono gli stessi elementi nello stesso ordine.
     *
     * @param o l'oggetto da confrontare per l'uguaglianza con questo {@code Vector}.
     * @return {@code true} se l'oggetto specificato è uguale a questo {@code Vector}.
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HList))
            return false;

        HListIterator it1 = listIterator();
        HListIterator it2 = ((HList) o).listIterator();
        while (it1.hasNext() && it2.hasNext()) {
            Object o1 = it1.next();
            Object o2 = it2.next();
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return !(it1.hasNext() || it2.hasNext());
    }

    /**
     * Restituisce il valore del codice hash per questo {@code Vector}.
     *
     * @return il valore del codice hash per questa lista.
     */
    @Override
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
     * Restituisce una rappresentazione in formato stringa di questo {@code Vector}, contenente la
     * rappresentazione stringa di ciascun elemento.
     *
     * @return una rappresentazione in formato stringa di questa collezione.
     * @see java.util.AbstractCollection#toString()
     */
    public String toString() {
        HIterator iterator = iterator();
        if (!iterator.hasNext())
            return "[]";

        String sb = "[";
        while (true) {
            Object e = iterator.next();
            sb += (e == this ? "(this Collection)" : e);
            if (!iterator.hasNext()) {
                sb += "]";
                return sb;
            }
            sb += ", ";
        }
    }

    /**
     * <p>
     * Restituisce una vista della porzione di questa lista compresa tra {@code fromIndex},
     * inclusivo, e {@code toIndex}, esclusivo. (Se {@code fromIndex} e {@code toIndex} sono uguali,
     * la lista restituita è vuota.) La lista restituita è supportata da questa lista, quindi le
     * modifiche alla lista restituita si riflettono in questa lista e viceversa. La lista
     * restituita supporta tutte le operazioni opzionali supportate da questa lista.
     * </p>
     * <p>
     * Questo metodo elimina la necessità di operazioni di intervallo esplicite (del tipo
     * comunemente esistente per gli array). Qualsiasi operazione che si aspetta una lista può
     * essere utilizzata come operazione di intervallo operando su una vista {@code subList} invece
     * che su una lista intera. Ad esempio, il seguente esempio rimuove un intervallo di elementi da
     * una lista:
     * </p>
     * 
     * <pre>
     * list.subList(from, to).clear();
     * </pre>
     * <p>
     * Idiomi simili possono essere costruiti per {@code indexOf} e {@code lastIndexOf}, e tutti gli
     * algoritmi nella classe {@code Collections} possono essere applicati a una {@code subList}.
     * </p>
     * <p>
     * La semantica della lista restituita da questo metodo diventa indefinita se la lista
     * sottostante (ossia, questa lista) viene modificata strutturalmente in qualsiasi altro modo
     * rispetto alla lista restituita. (Le modifiche strutturali sono quelle che cambiano la
     * dimensione della lista o la perturbano in modo tale che le iterazioni in corso possano
     * restituire risultati errati.)
     * </p>
     * 
     * @param fromIndex l'estremità bassa (inclusiva) della vista {@code subList}.
     * @param toIndex l'estremità alta (esclusiva) della vista {@code subList}.
     * @return una vista dell'intervallo specificato all'interno di questa lista.
     * @throws IndexOutOfBoundsException se il valore dell'indice di estremità è fuori intervallo
     *         {@code (fromIndex < 0 || toIndex > size())}.
     * @throws IllegalArgumentException se gli indici di estremità sono invertiti
     *         {@code (fromIndex > toIndex)}.
     */
    @Override
    public HList subList(int fromIndex, int toIndex) {
        if ((fromIndex > toIndex))
            throw new IllegalArgumentException();
        if ((fromIndex < 0 || toIndex > size()))
            throw new IndexOutOfBoundsException();
        return new SubListAdapter(vector, fromIndex + startIndex(), toIndex + startIndex());
    }

    /**
     * Rimuove da questa {@code List} tutti gli elementi il cui indice è compreso tra
     * {@code fromIndex}, incluso, e {@code toIndex}, escluso. Sposta gli eventuali elementi
     * successivi verso sinistra (riduce i loro indici). Questa chiamata riduce la dimensione della
     * {@code ArrayList} di {@code (toIndex - fromIndex)} elementi. (Se {@code toIndex==fromIndex},
     * questa operazione non ha effetto.)
     *
     * @param fromIndex l'indice del primo elemento da rimuovere.
     * @param toIndex l'indice successivo all'ultimo elemento da rimuovere.
     */
    protected void removeRange(int fromIndex, int toIndex) {
        subList(fromIndex, toIndex).clear();
    }

    /**
     * <p>
     * Restituisce un iteratore sugli elementi in questa lista in ordine corretto.
     * </p>
     * <p>
     * Questa implementazione restituisce una semplice implementazione dell'interfaccia
     * {@code Iterator}, basata sui metodi {@code size()}, {@code get(int)}, e {@code remove(int)}
     * della lista sottostante.
     * </p>
     *
     * <p>
     * Si noti che l'iteratore restituito da questo metodo solleverà un'eccezione
     * {@link UnsupportedOperationException} in risposta al suo metodo {@code remove} a meno che il
     * metodo {@code remove(int)} della lista non sia sovrascritto.
     * </p>
     * <p>
     * Questa implementazione può sollevare eccezioni di runtime in caso di modifica concorrente,
     * come descritto nella specifica per il campo {@code modCount} (protetto).
     * </p>
     * 
     * @return un iteratore sugli elementi in questa lista in ordine corretto.
     */
    @Override
    public HIterator iterator() {
        return new IteratorAdapter();
    }

    /**
     * Restituisce un iteratore sugli elementi in questa lista (in ordine corretto). Questa
     * implementazione restituisce {@code listIterator(0)}.
     *
     * @return un iteratore sugli elementi in questa lista (in ordine corretto).
     */
    @Override
    public HListIterator listIterator() {
        return new ListIteratorAdapter();
    }

    /**
     * <p>
     * Restituisce un iteratore di lista sugli elementi in questa lista (in ordine corretto),
     * partendo dalla posizione specificata nella lista. L'indice specificato indica il primo
     * elemento che verrebbe restituito da una chiamata iniziale al metodo {@code next}. Una
     * chiamata iniziale al metodo {@code previous} restituirebbe l'elemento con l'indice
     * specificato meno uno.
     * </p>
     * <p>
     * Questa implementazione restituisce una semplice implementazione dell'interfaccia
     * {@code ListIterator} che estende l'implementazione dell'interfaccia {@code Iterator}
     * restituita dal metodo {@code iterator()}. L'implementazione di {@code ListIterator} si basa
     * sui metodi {@code get(int)}, {@code set(int, Object)}, {@code add(int, Object)} e
     * {@code remove(int)} della lista sottostante.
     * </p>
     * 
     * @param index l'indice del primo elemento da restituire dall'iteratore di lista (tramite una
     *        chiamata al metodo {@code next}).
     * @return un iteratore di lista sugli elementi in questa lista (in ordine corretto), partendo
     *         dalla posizione specificata nella lista.
     * @throws IndexOutOfBoundsException se l'indice specificato è fuori intervallo
     *         {@code (index < 0 || index > size())}.
     */
    @Override
    public HListIterator listIterator(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        return new ListIteratorAdapter(index);
    }

    /**
     * <p>
     * La classe {@code SubListAdapter} rappresenta una vista della porzione di questa lista
     * compresa tra {@code fromIndex}, inclusivo, e {@code toIndex}, esclusivo. (Se
     * {@code fromIndex} e {@code toIndex} sono uguali, la lista restituita è vuota.) Questa lista è
     * supportata dal vettore, quindi le modifiche apportate alla lista si riflettono sul vettore e
     * viceversa. La lista restituita supporta tutte le operazioni opzionali supportate dal vettore.
     * </p>
     * 
     * @author Matteo Cuzzolin (matr: 2066701)
     */
    public class SubListAdapter extends VectorAdapter {
        /**
         * Indice di inizio della struttura dati
         */
        private int startIndex;

        /**
         * Dimensione della struttura dati
         */
        private int size;

        /**
         * Costruttore della classe {@code SubListAdapter} che inizializza un sottoinsieme definito
         * dagli indici di inizio e fine.
         *
         * @param vect il vettore da che supporta la lista.
         * @param start l'indice di inizio del sottoinsieme (incluso).
         * @param end l'indice di fine del sottoinsieme (escluso).
         */
        public SubListAdapter(Vector vect, int start, int end) {
            vector = vect;
            startIndex = start;
            size = end - start;
        }

        /**
         * Restituisce il numero di componenti in questo {@code SubListAdapter}.
         *
         * @return il numero di componenti in questo {@code SubListAdapter}.
         */
        @Override
        public int size() {
            return size;
        }

        /**
         * Ritorna l'indice di inizio della sottolista
         * 
         * @return l'indice di inizio della sottolista
         */
        @Override
        protected int startIndex() {
            return startIndex;
        }

        /**
         * Modifica la dimensione di {@code SubListAdapter}.
         *
         * @param newSize la nuova dimensione da impostare.
         */
        @Override
        protected void changeSize(int newSize) {
            size = newSize;
        }
    }

    /**
     * La classe {@code EnumerationAdapter} genera una serie di elementi, uno alla volta. Chiamate
     * successive al metodo {@code nextElement} restituiscono elementi successivi della serie.
     *
     * Ad esempio, per stampare tutti gli elementi di un vettore {@code v}:
     *
     * <pre>
     * for (Enumeration e = v.elements(); e.hasMoreElements();) {
     *     System.out.println(e.nextElement());
     * }
     * </pre>
     * 
     * @author Matteo Cuzzolin (matr: 2066701)
     */
    public class EnumerationAdapter implements Enumeration {
        /**
         * Indice dell'attuale posizione dell'iteratore
         */
        private int cursor = 0;

        /**
         * Costruttore di default
         */
        public EnumerationAdapter() {}

        /**
         * Verifica se questa enumerazione contiene altri elementi.
         *
         * @return {@code true} se e solo se questo oggetto di enumerazione contiene almeno un altro
         *         elemento da fornire; {@code false} altrimenti.
         */
        @Override
        public boolean hasMoreElements() {
            return cursor < size();
        }

        /**
         * Restituisce il prossimo elemento di questa enumerazione se questa enumerazione ha almeno
         * un altro elemento da fornire.
         *
         * @return il prossimo elemento di questa enumerazione.
         * @throws NoSuchElementException se non esistono altri elementi.
         */
        @Override
        public Object nextElement() {
            if (!this.hasMoreElements())
                throw new NoSuchElementException();
            Object currObj = get(cursor);
            cursor++;
            return currObj;
        }
    }

    /**
     * Un iteratore su una collezione. {@code HIterator} sostituisce {@code Enumeration} nel
     * framework delle collezioni di Java. Gli iteratori si differenziano dalle enumerazioni in due
     * modi:
     *
     * <ul>
     * <li>Gli iteratori consentono al chiamante di rimuovere elementi dalla collezione sottostante
     * durante l'iterazione con una semantica ben definita.</li>
     * <li>I nomi dei metodi sono stati migliorati.</li>
     * </ul>
     * 
     * @author Matteo Cuzzolin (matr: 2066701)
     */
    public class IteratorAdapter implements HIterator {

        /**
         * Costruttore di default
         */
        public IteratorAdapter() {}

        /**
         * Indice dell'attuale posizione dell'iteratore
         */
        protected int cursor = 0;

        /**
         * Indice dell'elemento precedentemente ritornato
         */
        protected int lastReturned = -1;

        /**
         * Restituisce {@code true} se l'iterazione ha altri elementi. (In altre parole, restituisce
         * {@code true} se {@code next()} restituirebbe un elemento piuttosto che lanciare
         * un'eccezione).
         * 
         * @return {@code true} se l'iteratore ha altri elementi.
         */
        @Override
        public boolean hasNext() {
            return cursor < size();
        }

        /**
         * Restituisce l'elemento successivo nell'iterazione.
         *
         * @return l'elemento successivo nell'iterazione.
         * @throws NoSuchElementException se l'iterazione non ha più elementi.
         */
        @Override
        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = cursor;
            return get(cursor++);
        }

        /**
         * Rimuove dalla collezione sottostante l'ultimo elemento restituito dall'iteratore. Questo
         * metodo può essere chiamato solo una volta per ogni chiamata a {@code next()}. Il
         * comportamento di un iteratore non è specificato se la collezione sottostante viene
         * modificata durante l'iterazione in qualsiasi modo diverso dalla chiamata a questo metodo.
         *
         * @throws IllegalStateException se il metodo {@code next()} non è stato ancora chiamato, o
         *         se il metodo {@code remove()} è già stato chiamato dopo l'ultima chiamata al
         *         metodo {@code next()}.
         */
        @Override
        public void remove() {
            if (lastReturned == -1)
                throw new IllegalStateException();
            VectorAdapter.this.remove(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
        }
    }

    /**
     * <p>
     * Un iteratore per liste che permette al programmatore di attraversare la lista in entrambe le
     * direzioni, modificare la lista durante l'iterazione e ottenere la posizione corrente
     * dell'iteratore nella lista. Un {@code HListIterator} non ha un elemento corrente; la sua
     * posizione del cursore si trova sempre tra l'elemento che verrebbe restituito da una chiamata
     * a previous() e l'elemento che verrebbe restituito da una chiamata a next(). In una lista di
     * lunghezza n, ci sono n+1 valori di indice validi, da 0 a n, inclusi.
     * </p>
     * 
     * <pre>
    
             Element(0)   Element(1)   Element(2)   ... Element(n)
            ^            ^            ^            ^               ^
     Index: 0            1            2            3               n+1
     * </pre>
     * <p>
     * Nota che {@link #remove()} e {@link #set(Object)} non si basano sulla posizione
     * dell'iteratore, bensì sull'ultimo elemento ritornato da una chiamata a {@link #next()} o
     * {@link #previous()}.
     * </p>
     *
     * @author Matteo Cuzzolin (matr: 2066701)
     */
    public class ListIteratorAdapter extends IteratorAdapter implements HListIterator {

        /**
         * Costruttore di default di {@link ListIteratorAdapter}
         */
        protected ListIteratorAdapter() {}

        /**
         * Restituisce un iteratore di lista sugli elementi in questa lista (in ordine corretto),
         * partendo dalla posizione specificata nella lista. L'indice specificato indica il primo
         * elemento che verrebbe restituito da una chiamata iniziale al metodo {@code next}. Una
         * chiamata iniziale al metodo {@code previous} restituirebbe l'elemento con l'indice
         * specificato meno uno.
         * 
         * @param index l'indice del primo elemento da restituire dall'iteratore di lista (tramite
         *        una chiamata al metodo {@code next}).
         */
        protected ListIteratorAdapter(int index) {
            cursor = index;
        }

        /**
         * Restituisce {@code true} se questo iteratore di lista ha altri elementi durante
         * l'attraversamento della lista in direzione inversa. (In altre parole, restituisce
         * {@code true} se {@code previous()} restituirebbe un elemento piuttosto che lanciare
         * un'eccezione).
         *
         * @return {@code true} se l'iteratore di lista ha altri elementi durante l'attraversamento
         *         della lista in direzione inversa.
         */
        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        /**
         * Restituisce l'elemento precedente nella lista. Questo metodo può essere chiamato
         * ripetutamente per scorrere la lista all'indietro, o alternato con chiamate a
         * {@code next()} per muoversi avanti e indietro. (Si noti che chiamate alternate a
         * {@code next()} e {@code previous()} restituiranno ripetutamente lo stesso elemento).
         *
         * @return l'elemento precedente nella lista.
         * @throws NoSuchElementException se l'iterazione non ha un elemento precedente.
         */
        @Override
        public Object previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            lastReturned = previousIndex();
            return get(cursor = previousIndex());
        }

        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
         * {@code next()}. (Restituisce la dimensione della lista se l'iteratore di lista è alla
         * fine della lista).
         *
         * @return l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
         *         {@code next()}, o la dimensione della lista se l'iteratore di lista è alla fine
         *         della lista.
         */
        @Override
        public int nextIndex() {
            return cursor;
        }

        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
         * {@code previous()}. (Restituisce {@code -1} se l'iteratore di lista è all'inizio della
         * lista).
         *
         * @return l'indice dell'elemento che verrebbe restituito da una successiva chiamata a
         *         {@code previous}, o {@code -1} se l'iteratore di lista è all'inizio della lista.
         */
        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        /**
         * Sostituisce l'ultimo elemento restituito da {@code next} o {@code previous} con
         * l'elemento specificato (operazione opzionale). Questa chiamata può essere effettuata solo
         * se né {@code HListIterator.remove} né {@code HListIterator.add} sono stati chiamati dopo
         * l'ultima chiamata a {@code next} o {@code previous}.
         *
         * @param o l'elemento con cui sostituire l'ultimo elemento restituito da {@code next} o
         *        {@code previous}.
         * @throws IllegalStateException se non è stato chiamato né {@code next} né
         *         {@code previous}, oppure se {@code remove} o {@code add} sono stati chiamati dopo
         *         l'ultima chiamata a {@code next} o {@code previous}.
         */
        @Override
        public void set(Object o) {
            if (lastReturned == -1)
                throw new IllegalStateException();
            VectorAdapter.this.set(lastReturned, o);
        }

        /**
         * Inserisce l'elemento specificato nella lista (operazione opzionale). L'elemento viene
         * inserito immediatamente prima del prossimo elemento che verrebbe restituito da
         * {@code next}, se presente, e dopo il prossimo elemento che verrebbe restituito da
         * {@code previous}, se presente. (Se la lista non contiene elementi, il nuovo elemento
         * diventa l'unico elemento nella lista). Il nuovo elemento viene inserito prima del cursore
         * implicito: una successiva chiamata a {@code next} non sarà influenzata, e una successiva
         * chiamata a {@code previous} restituirà il nuovo elemento. (Questa chiamata aumenta di uno
         * il valore che verrebbe restituito da una chiamata a {@code nextIndex} o
         * {@code previousIndex}).
         *
         * @param o l'elemento da inserire.
         */
        @Override
        public void add(Object o) {
            VectorAdapter.this.add(cursor, o);
            cursor++;
            lastReturned = -1;
        }
    }
}
