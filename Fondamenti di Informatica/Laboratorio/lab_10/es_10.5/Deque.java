/**
 * interfaccia Deque - definisce l'ADT Coda Doppia
 * 
 * @author Goodrich - Tamassia
 * @see Container
 * @see EmptyDequeException
 * 
 */
public interface Deque extends Container {
    /**
     * inserisce l'elemento specificato all'inizio alla doppia coda
     * 
     * @param element l'elemento da inserire
     */
    void addFirst(Object element);

    /**
     * inserisce l'elemento specificato alla fine alla doppia coda
     * 
     * @param element l'elemento da inserire
     */
    // void addLast(Object element);

    /**
     * ispeziona, ma non estrae, il primo elemento della doppia coda
     * 
     * @return il primo elemento della doppia coda
     * @throws EmptyDequeException se la coda doppia e' vuota
     */
    Object getFirst() throws EmptyDequeException;

    /**
     * ispeziona, ma non estrae, l'ultimo elemento della doppia coda
     * 
     * @return l'ultimo elemento della doppia coda
     * @throws EmptyDequeException se la coda doppia e' vuota
     */
    Object getLast() throws EmptyDequeException;

    /**
     * ispeziona ed estrae il primo elemento della doppia coda
     * 
     * @return il primo elemento della doppia coda
     * @throws EmptyDequeException se la coda doppia e' vuota
     */
    Object removeFirst() throws EmptyDequeException;

    /**
     * ispeziona ed estrae l'ultimo elemento della doppia coda
     * 
     * @return il primo elemento della doppia coda
     * @throws EmptyDequeException se la coda doppia e' vuota
     */
    Object removeLast() throws EmptyDequeException;
}