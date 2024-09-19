package myAdapter;

/**
 * <p>
 * Una raccolta ordinata (nota anche come sequenza). L'utente di questa interfaccia ha un controllo
 * preciso su dove ogni elemento viene inserito nella lista. L'utente può accedere agli elementi
 * tramite il loro indice intero (posizione nella lista) e cercare elementi nella lista.
 * </p>
 * <p>
 * A differenza dei {@code Set}, questa lista consente lo storage di elementi nulli o elementi
 * dupplicati, ovvero consente l'inserimento di elementi e1 e2 tali che {@code e1.equals(e2)}. Ogni
 * utente nell'implementazione di questa interfaccia è libero di modificare questi comportamenti.
 * </p>
 * <p>
 * L'interfaccia {@code HList} pone ulteriori vincoli, oltre a quelli specificati nell'interfaccia
 * HCollection, sui contratti dei metodi hiterator, add, remove, equals e hashCode. Le dichiarazioni
 * per altri metodi ereditati sono incluse qui per comodità.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce quattro metodi per l'accesso posizionale (indicizzato) agli
 * elementi della lista. Il primo indice della lista (come con gli array) corrisponde a zero. Si
 * noti che queste operazioni possono essere eseguite in un tempo proporzionale al valore
 * dell'indice per alcune implementazioni (la classe {@code LinkedList}, ad esempio). Pertanto,
 * iterare sugli elementi di una lista è tipicamente preferibile all'indicizzazione se il chiamante
 * non conosce l'implementazione.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce un iteratore speciale, chiamato {@code HList}Iterator, che
 * consente l'inserimento e la sostituzione degli elementi, e l'accesso bidirezionale oltre alle
 * normali operazioni fornite dall'interfaccia HIterator. È fornito un metodo per ottenere un
 * iteratore della lista che inizi in una posizione specificata nella lista.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce due metodi per cercare un oggetto specificato. Dal punto di
 * vista delle prestazioni, questi metodi dovrebbero essere usati con cautela. In molte
 * implementazioni eseguiranno ricerche lineari costose.
 * </p>
 * <p>
 * L'interfaccia {@code HList} fornisce due metodi per inserire e rimuovere efficientemente più
 * elementi in un punto arbitrario della lista.
 * </p>
 * <p>
 * Alcune implementazioni di liste hanno restrizioni sugli elementi che possono contenere. Ad
 * esempio, alcune implementazioni proibiscono elementi nulli, e alcune hanno restrizioni sui tipi
 * dei loro elementi. Tentare di aggiungere un elemento non idoneo lancia un'eccezione non
 * controllata, tipicamente {@link NullPointerException} o {@link ClassCastException}. Tentare di
 * interrogare la presenza di un elemento non idoneo può lanciare un'eccezione, oppure può
 * semplicemente restituire {@code false}; alcune implementazioni mostreranno il primo comportamento
 * e altre il secondo. Più in generale, tentare un'operazione su un elemento non idoneo la cui
 * conclusione non comporterebbe l'inserimento di un elemento non idoneo nella lista può lanciare
 * un'eccezione oppure può avere successo, a discrezione dell'implementazione. Tali eccezioni sono
 * contrassegnate come "opzionali" nella specifica di questa interfaccia.
 * </p>
 *
 * @author Matteo Cuzzolin (matr: 2066701)
 */
public interface HList extends HCollection {
    /**
     * Inserisce tutti gli elementi nella collezione specificata in questa lista nella posizione
     * specificata (operazione opzionale). Sposta l'elemento attualmente in quella posizione (se
     * presente) e tutti gli elementi successivi verso destra (aumenta i loro indici). I nuovi
     * elementi appariranno in questa lista nell'ordine in cui vengono restituiti dall'iteratore
     * della collezione specificata. Il comportamento di questa operazione non è specificato se la
     * collezione specificata viene modificata mentre l'operazione è in corso. (Si noti che ciò
     * accadrà se la collezione specificata è questa lista ed è non vuota).
     *
     * @param index l'indice in cui inserire il primo elemento della collezione specificata.
     * @param c gli elementi da inserire in questa lista.
     * @return {@code true} se questa lista è stata modificata come risultato della chiamata.
     * @throws UnsupportedOperationException {@code addAll} non è supportato da questa lista.
     * @throws ClassCastException se la classe di uno degli elementi della collezione specificata ne
     *         impedisce l'aggiunta a questa lista.
     * @throws NullPointerException se la collezione specificata contiene uno o più elementi
     *         {@code null} e questa lista non supporta elementi {@code null}, oppure se la
     *         collezione specificata è {@code null}.
     * @throws IllegalArgumentException se qualche aspetto di uno degli elementi della collezione
     *         specificata ne impedisce l'aggiunta a questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index > size())}.
     */
    boolean addAll(int index, HCollection c);

    /**
     * Restituisce l'elemento alla posizione specificata in questa lista.
     *
     * @param index l'indice dell'elemento da restituire.
     * @return l'elemento alla posizione specificata in questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index >= size())}.
     */
    Object get(int index);

    /**
     * Sostituisce l'elemento alla posizione specificata in questa lista con l'elemento specificato
     * (operazione opzionale).
     *
     * @param index l'indice dell'elemento da sostituire.
     * @param element l'elemento da memorizzare alla posizione specificata.
     * @return l'elemento precedentemente alla posizione specificata.
     * @throws UnsupportedOperationException {@code set} non è supportato da questa lista.
     * @throws ClassCastException se la classe dell'elemento specificato impedisce di essere
     *         aggiunto a questa lista.
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null}.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato impedisce di
     *         essere aggiunto a questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index >= size())}.
     */
    Object set(int index, Object element);

    /**
     * Inserisce l'elemento specificato nella posizione specificata in questa lista (operazione
     * opzionale). Sposta l'elemento attualmente in quella posizione (se presente) e tutti gli
     * elementi successivi verso destra (aggiunge uno ai loro indici).
     *
     * @param index l'indice in cui l'elemento specificato deve essere inserito.
     * @param element l'elemento da inserire.
     * @throws UnsupportedOperationException {@code add} non è supportato da questa lista.
     * @throws ClassCastException se la classe dell'elemento specificato ne impedisce l'aggiunta a
     *         questa lista.
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null}.
     * @throws IllegalArgumentException se qualche aspetto dell'elemento specificato ne impedisce
     *         l'aggiunta a questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index > size())}.
     */
    void add(int index, Object element);


    /**
     * Rimuove l'elemento alla posizione specificata in questa lista (operazione opzionale). Sposta
     * eventuali elementi successivi a sinistra (sottrae uno dai loro indici). Restituisce
     * l'elemento che è stato rimosso dalla lista.
     *
     * @param index l'indice dell'elemento da rimuovere.
     * @return l'elemento precedentemente alla posizione specificata.
     * @throws UnsupportedOperationException {@code remove} non è supportato da questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index >= size())}.
     */
    Object remove(int index);

    /**
     * Restituisce l'indice in questa lista della prima occorrenza dell'elemento specificato, oppure
     * {@code -1} se questa lista non contiene l'elemento. Più formalmente, restituisce il più basso
     * indice {@code i} tale che {@code (o==null ? get(i)==null : o.equals(get(i)))}, oppure
     * {@code -1} se non esiste tale indice.
     *
     * @param o l'elemento da cercare.
     * @return l'indice in questa lista della prima occorrenza dell'elemento specificato, oppure
     *         {@code -1} se questa lista non contiene l'elemento.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         lista (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null} (opzionale).
     */
    int indexOf(Object o);

    /**
     * Restituisce l'indice in questa lista dell'ultima occorrenza dell'elemento specificato, oppure
     * {@code -1} se questa lista non contiene l'elemento. Più formalmente, restituisce l'indice più
     * alto {@code i} tale che {@code (o==null ? get(i)==null : o.equals(get(i)))}, oppure
     * {@code -1} se non esiste tale indice.
     *
     * @param o l'elemento da cercare.
     * @return l'indice in questa lista dell'ultima occorrenza dell'elemento specificato, oppure
     *         {@code -1} se questa lista non contiene l'elemento.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         lista (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa lista non
     *         supporta elementi {@code null} (opzionale).
     */
    int lastIndexOf(Object o);

    /**
     * Restituisce un iteratore di lista degli elementi in questa lista (nell'ordine corretto).
     *
     * @return un iteratore di lista degli elementi in questa lista (nell'ordine corretto).
     */
    HListIterator listIterator();

    /**
     * Restituisce un iteratore di lista degli elementi in questa lista (nell'ordine corretto),
     * partendo dalla posizione specificata in questa lista. L'indice specificato indica il primo
     * elemento che verrebbe restituito da una chiamata iniziale al metodo {@code next}. Una
     * chiamata iniziale al metodo {@code previous} restituirebbe l'elemento con l'indice
     * specificato meno uno.
     *
     * @param index l'indice del primo elemento da restituire dall'iteratore di lista (tramite una
     *        chiamata al metodo {@code next}).
     * @return un iteratore di lista degli elementi in questa lista (nell'ordine corretto), partendo
     *         dalla posizione specificata in questa lista.
     * @throws IndexOutOfBoundsException se l'indice è fuori dal range
     *         {@code (index < 0 || index > size())}.
     */
    HListIterator listIterator(int index);

    /**
     * Restituisce una vista della porzione di questa lista compresa tra l'indice {@code fromIndex},
     * incluso, e l'indice {@code toIndex}, escluso. (Se {@code fromIndex} e {@code toIndex} sono
     * uguali, la lista restituita è vuota.) La lista restituita è supportata da questa lista,
     * quindi le modifiche non strutturali nella lista restituita sono riflesse in questa lista, e
     * viceversa. La lista restituita supporta tutte le operazioni opzionali della lista supportate
     * da questa lista.
     *
     * Questo metodo elimina la necessità di operazioni esplicite di intervallo (del tipo
     * comunemente esistente per gli array). Qualsiasi operazione che si aspetta una lista può
     * essere utilizzata come operazione di intervallo passando una vista {@code subList} invece di
     * una lista intera. Ad esempio, il seguente codice rimuove un intervallo di elementi da una
     * lista:
     * 
     * <pre>
     * list.subList(from, to).clear();
     * </pre>
     *
     * Idiomi simili possono essere costruiti per {@code indexOf} e {@code lastIndexOf}, e tutti gli
     * algoritmi nella classe {@code Collections} possono essere applicati a una {@code subList}.
     * 
     * La semantica della lista restituita da questo metodo diventano indefinite se la lista di base
     * (ossia, questa lista) è modificata strutturalmente in qualsiasi modo diverso tramite la lista
     * restituita. (Le modifiche strutturali sono quelle che cambiano la dimensione di questa lista,
     * o la perturbano in modo tale che le iterazioni in corso possono dare risultati errati.)
     *
     * @param fromIndex l'estremo inferiore (inclusivo) della {@code subList}.
     * @param toIndex l'estremo superiore (esclusivo) della {@code subList}.
     * @return una vista dell'intervallo specificato all'interno di questa lista.
     * @throws IndexOutOfBoundsException se il valore dell'indice di estremità è illecito
     *         {@code (fromIndex < 0 || toIndex > size || fromIndex > toIndex)}.
     */
    HList subList(int fromIndex, int toIndex);
}
