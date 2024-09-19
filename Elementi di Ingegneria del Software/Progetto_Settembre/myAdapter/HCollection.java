package myAdapter;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * L'interfaccia root nella gerarchia delle collezioni. Una collezione rappresenta un gruppo di
 * oggetti, conosciuti come i suoi elementi. Alcune collezioni permettono elementi duplicati, altre
 * no. Alcune sono ordinate, altre no. L'SDK non fornisce implementazioni dirette di questa
 * interfaccia: fornisce implementazioni di sottointerfacce più specifiche come HList. Questa
 * interfaccia è tipicamente usata per passare collezioni e manipolarle quando si desidera la
 * massima generalità.
 * </p>
 * <p>
 * Tutte le classi di implementazione {@code HCollection} di uso generale (che tipicamente
 * implementano {@code HCollection} indirettamente attraverso una delle sue sottointerfacce)
 * dovrebbero fornire due costruttori "standard": un costruttore vuoto (senza argomenti), che crea
 * una collezione vuota, e un costruttore con un singolo argomento di tipo {@code HCollection}, che
 * crea una nuova collezione con gli stessi elementi dell'argomento. Di fatto, il secondo
 * costruttore permette all'utente di copiare qualsiasi collezione, producendo una collezione
 * equivalente del tipo di implementazione desiderato. Non c'è modo di far rispettare questa
 * convenzione (poiché le interfacce non possono contenere costruttori) ma tutte le implementazioni
 * di {@code HCollection} di uso generale nelle librerie della piattaforma Java la rispettano.
 * </p>
 * <p>
 * Alcune implementazioni di collezioni hanno restrizioni sugli elementi che possono contenere. Ad
 * esempio, alcune implementazioni proibiscono elementi {@code null}, e alcune hanno restrizioni sui
 * tipi dei loro elementi. Tentare di aggiungere un elemento non idoneo lancia un'eccezione
 * unchecked, tipicamente {@code NullPointerException} o {@code ClassCastException}. Tentare di
 * verificare la presenza di un elemento non idoneo può lanciare un'eccezione, o può semplicemente
 * restituire {@code false}; alcune implementazioni esibiranno il primo comportamento e alcune il
 * secondo. Più in generale, tentare un'operazione su un elemento non idoneo la cui conclusione non
 * comporterebbe l'inserimento di un elemento non idoneo nella collezione può lanciare un'eccezione
 * o può avere successo, a discrezione dell'implementazione. Tali eccezioni sono segnate come
 * "opzionali" nella specifica di questa interfaccia.
 * </p>
 *
 * @author Matteo Cuzzolin (matr: 2066701)
 */
public interface HCollection {
    /**
     * Restituisce il numero di elementi presenti in questa collezione. Se questa collezione
     * contiene più di {@code Integer.MAX_VALUE} elementi, restituisce {@code Integer.MAX_VALUE}.
     *
     * @return il numero di elementi in questa collezione.
     */
    int size();

    /**
     * Restituisce {@code true} se questa collezione non contiene elementi.
     *
     * @return {@code true} se questa collezione non contiene elementi.
     */
    boolean isEmpty();

    /**
     * Restituisce {@code true} se questa collezione contiene l'elemento specificato. Più
     * formalmente, restituisce {@code true} se e solo se questa collezione contiene almeno un
     * elemento e tale che {@code (o==null ? e==null : o.equals(e))}.
     *
     * @param o l'elemento la cui presenza in questa collezione deve essere verificata.
     * @return true se questa collezione contiene l'elemento specificato.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         collezione (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa collezione non
     *         supporta elementi {@code null} (opzionale).
     */
    boolean contains(Object o);

    /**
     * Restituisce un iteratore sugli elementi di questa collezione. Non ci sono garanzie riguardo
     * l'ordine in cui gli elementi vengono restituiti (a meno che questa collezione non sia
     * un'istanza di una classe che fornisce una garanzia specifica).
     *
     * @return un {@code HIterator} sugli elementi di questa collezione.
     */
    HIterator iterator();

    /**
     * <p>
     * Restituisce un array contenente tutti gli elementi di questa collezione. Se la collezione
     * fornisce garanzie sull'ordine in cui i suoi elementi sono restituiti dal suo iteratore,
     * questo metodo deve restituire gli elementi nello stesso ordine.
     * </p>
     * <p>
     * L'array restituito sarà "sicuro", nel senso che questa collezione non manterrà alcun
     * riferimento all'array. (In altre parole, questo metodo deve allocare un nuovo array anche se
     * questa collezione è basata su un array). Il chiamante è quindi libero di modificare l'array
     * restituito.
     * </p>
     * <p>
     * Questo metodo funge da ponte tra le API basate su array e quelle basate su collezioni.
     * </p>
     * 
     * @return un array contenente tutti gli elementi di questa collezione.
     */
    Object[] toArray();

    /**
     * <p>
     * Restituisce un array contenente tutti gli elementi di questa collezione; il tipo runtime
     * dell'array restituito è quello dell'array specificato. Se la collezione ci sta nell'array
     * specificato, viene restituita in esso. Altrimenti, viene allocato un nuovo array con il tipo
     * runtime dell'array specificato e la dimensione di questa collezione.
     * </p>
     * <p>
     * Se questa collezione ci sta nell'array specificato con spazio aggiuntivo (cioè, l'array ha
     * più elementi di questa collezione), l'elemento nell'array immediatamente successivo alla fine
     * della collezione viene impostato a {@code null}. Questo è utile per determinare la lunghezza
     * di questa collezione solo se il chiamante sa che questa collezione non contiene elementi
     * {@code null}.
     * </p>
     * <p>
     * Se questa collezione fornisce garanzie sull'ordine in cui i suoi elementi sono restituiti dal
     * suo iteratore, questo metodo deve restituire gli elementi nello stesso ordine.
     * </p>
     * <p>
     * Come il metodo {@code toArray()}, questo metodo funge da ponte tra le API basate su array e
     * quelle basate su collezioni. Inoltre, questo metodo consente un controllo preciso sul tipo
     * runtime dell'array restituito e, in determinate circostanze, può essere utilizzato per
     * risparmiare sui costi di allocazione.
     * </p>
     * <p>
     * Supponiamo che {@code l} sia un {@code List} noto per contenere solo stringhe. Il seguente
     * codice può essere utilizzato per copiare la lista in un nuovo array di {@code String}:
     * </p>
     * 
     * <pre>
     * String[] x = (String[]) l.toArray(new String[0]);
     * </pre>
     * 
     * Si noti che {@code toArray(new Object[0])} è identico, per funzionalità, a {@code toArray()}.
     *
     * @param a l'array in cui memorizzare gli elementi di questa collezione, se è sufficientemente
     *        grande; altrimenti, viene allocato un nuovo array dello stesso tipo runtime per questo
     *        scopo.
     * @return un array contenente gli elementi di questa collezione.
     * @throws ArrayStoreException se il tipo runtime dell'array specificato non è un supertipo del
     *         tipo runtime di ogni elemento in questa collezione.
     * @throws NullPointerException se l'array specificato è {@code null}.
     */
    Object[] toArray(Object[] a);

    /**
     * <p>
     * Assicura che questa collezione contenga l'elemento specificato (operazione opzionale).
     * Restituisce {@code true} se la collezione è cambiato in seguito alla chiamata. (Restituisce
     * {@code false} se la collezione non consente duplicati e contiene già l'elemento specificato).
     * </p>
     * <p>
     * Le collezioni che supportano questa operazione possono porre limitazioni sugli elementi che
     * possono essere aggiunti a questa collezione. In particolare, alcune collezioni rifiutano di
     * aggiungere elementi {@code null} e altre impongono restrizioni sul tipo di elementi che
     * possono essere aggiunti. Le classi di collezioni devono specificare chiaramente nella loro
     * documentazione qualsiasi restrizione sugli elementi che possono essere aggiunti.
     * </p>
     * <p>
     * Se una collezione rifiuta di aggiungere un particolare elemento per qualsiasi motivo diverso
     * dal fatto che lo contiene già, deve lanciare un'eccezione (invece di restituire
     * {@code false}). Questo preserva l'invariante che una collezione contiene sempre l'elemento
     * specificato dopo il ritorno di questa chiamata.
     * </p>
     * 
     * @param o elemento la cui presenza in questa collezione deve essere garantita
     * @return {@code true} se la collezione è stata modificata a seguito della chiamata
     * @throws UnsupportedOperationException {@code add} non è supportata da questa collezione.
     * @throws ClassCastException la classe dell'elemento specificato impedisce di aggiungerlo a
     *         questa collezione.
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa collezione non
     *         supporta elementi {@code null}.
     * @throws IllegalArgumentException qualche aspetto di questo elemento impedisce di aggiungerlo
     *         a questa collezione.
     */
    boolean add(Object o);

    /**
     * Rimuove una singola istanza dell'elemento specificato da questa collezione, se è presente
     * (operazione opzionale). Più formalmente, rimuove un elemento {@code e} tale che
     * {@code (o==null ? e==null : o.equals(e))}, se questa collezione contiene uno o più elementi
     * di questo tipo. Restituisce {@code true} se questa collezione conteneva l'elemento
     * specificato (o, equivalente, se questa collezione è stata modificata come risultato della
     * chiamata).
     *
     * @param o l'elemento da rimuovere da questa collezione, se presente.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws ClassCastException se il tipo dell'elemento specificato è incompatibile con questa
     *         collezione (opzionale).
     * @throws NullPointerException se l'elemento specificato è {@code null} e questa collezione non
     *         supporta elementi {@code null} (opzionale).
     * @throws UnsupportedOperationException {@code remove} non è supportato da questa collezione.
     */
    boolean remove(Object o);

    /**
     * Restituisce {@code true} se questa collezione contiene tutti gli elementi della collezione
     * specificata.
     *
     * @param c la collezione da verificare per la sua presenza in questa collezione.
     * @return {@code true} se questa collezione contiene tutti gli elementi della collezione
     *         specificata.
     * @throws ClassCastException se il tipo di uno o più elementi nella collezione specificata è
     *         incompatibile con questa collezione (opzionale).
     * @throws NullPointerException se la collezione specificata contiene uno o più elementi
     *         {@code null} e questa collezione non supporta elementi {@code null} (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see #contains(Object)
     */
    boolean containsAll(HCollection c);

    /**
     * Aggiunge tutti gli elementi della collezione specificata a questa collezione (operazione
     * opzionale). Il comportamento di questa operazione non è definito se la collezione specificata
     * viene modificata durante l'esecuzione dell'operazione. (Questo implica che il comportamento
     * di questa chiamata non è definito se la collezione specificata è questa collezione e questa
     * collezione non è vuota.)
     *
     * @param c la collezione di elementi da inserire in questa collezione.
     * @return true se questa collezione è stata modificata come risultato della chiamata.
     * @throws UnsupportedOperationException se questa collezione non supporta il metodo addAll.
     * @throws ClassCastException se la classe di un elemento della collezione specificata impedisce
     *         di aggiungerlo a questa collezione.
     * @throws NullPointerException se la collezione specificata contiene uno o più elementi
     *         {@code null} e questa collezione non supporta elementi {@code null}, o se la
     *         collezione specificata è {@code null}.
     * @throws IllegalArgumentException se qualche aspetto di un elemento della collezione
     *         specificata impedisce di aggiungerlo a questa collezione.
     */
    boolean addAll(HCollection c);

    /**
     * Rimuove tutti gli elementi di questa collezione che sono anche contenuti nella collezione
     * specificata (operazione opzionale). Dopo che questa chiamata ritorna, questa collezione non
     * conterrà elementi in comune con la collezione specificata.
     *
     * @param c gli elementi da rimuovere da questa collezione.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws UnsupportedOperationException {@code removeAll} non è supportato da questa
     *         collezione.
     * @throws ClassCastException se il tipo di uno o più elementi in questa collezione è
     *         incompatibile con la collezione specificata (opzionale).
     * @throws NullPointerException se questa collezione contiene uno o più elementi {@code null} e
     *         la collezione specificata non supporta elementi {@code null} (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(HCollection c);

    /**
     * Mantiene in questa collezione solo gli elementi che sono contenuti nella collezione
     * specificata (operazione opzionale). In altre parole, rimuove da questa collezione tutti i
     * suoi elementi che non sono contenuti nella collezione specificata.
     *
     * @param c gli elementi da mantenere in questa collezione.
     * @return {@code true} se questa collezione è stata modificata come risultato della chiamata.
     * @throws UnsupportedOperationException {@code retainAll} non è supportato da questa
     *         collezione.
     * @throws ClassCastException se il tipo di uno o più elementi in questa collezione è
     *         incompatibile con la collezione specificata (opzionale).
     * @throws NullPointerException se questa collezione contiene uno o più elementi {@code null} e
     *         la collezione specificata non supporta elementi {@code null} (opzionale).
     * @throws NullPointerException se la collezione specificata è {@code null}.
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(HCollection c);

    /**
     * Rimuove tutti gli elementi da questa collezione (operazione opzionale). Questa collezione
     * sarà vuota dopo che il metodo ritorna, a meno che non venga sollevata un'eccezione.
     *
     * @throws UnsupportedOperationException {@code clear} non è supportato da questa collezione.
     */
    void clear();

    /**
     * <p>
     * Confronta l'oggetto specificato con questa collezione per verificarne l'uguaglianza.
     * </p>
     * <p>
     * Sebbene l'interfaccia {@code HCollection} non aggiunga restrizioni al contratto generale per
     * {@code Object.equals}, i programmatori che implementano l'interfaccia {@code HCollection}
     * "direttamente" (cioè creano una classe che è una {@code HCollection} ma non un {@code Set} o
     * un {@code List}) devono prestare attenzione se scelgono di sovrascrivere
     * {@code Object.equals}. Non è necessario farlo, e l'azione più semplice è affidarsi
     * all'implementazione predefinita di Object, ma l'implementatore potrebbe desiderare di
     * implementare un confronto per "valore" al posto del confronto per "riferimento" predefinito.
     * (Le interfacce {@code List} e {@code Set} richiedono tali confronti per valore.)
     * </p>
     * <p>
     * Il contratto generale per il metodo {@code Object.equals} afferma che equals deve essere
     * simmetrico (cioè, {@code a.equals(b)} se e solo se {@code b.equals(a)}). I contratti per
     * {@code List.equals} e {@code Set.equals} affermano che le liste sono uguali solo ad altre
     * liste e i set ad altri set. Quindi, un metodo equals personalizzato per una classe di
     * collezione che non implementa né l'interfaccia {@code List} né {@code Set} deve restituire
     * {@code false} quando questa collezione viene confrontata con qualsiasi lista o set. (Per la
     * stessa logica, non è possibile scrivere una classe che implementi correttamente entrambe le
     * interfacce {@code Set} e {@code List}.)
     * </p>
     *
     * {@inheritDoc}
     *
     * @param o l'oggetto da confrontare per uguaglianza con questa collezione.
     * @return {@code true} se l'oggetto specificato è uguale a questa collezione.
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @see List#equals(Object)
     */
    boolean equals(Object o);

    /**
     * Restituisce il valore dell'hash code per questa collezione. Sebbene l'interfaccia
     * {@code HCollection} non aggiunga restrizioni al contratto generale per il metodo
     * {@code Object.hashCode}, i programmatori devono notare che qualsiasi classe che sovrascrive
     * il metodo {@code Object.equals} deve anche sovrascrivere il metodo {@code Object.hashCode}
     * per soddisfare il contratto generale del metodo hashCode. In particolare,
     * {@code c1.equals(c2)} implica che {@code c1.hashCode() == c2.hashCode()}.
     *
     * {@inheritDoc}
     *
     * @return il valore dell'hash code per questa collezione.
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();
}
