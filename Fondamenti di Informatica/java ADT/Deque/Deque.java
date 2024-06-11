public interface Deque extends Container {
    void addFirst(Object obj);
    void addLast(Object obj);

    Object removeFirst() throws EmptyQueueException;
    Object removeLast() throws EmptyQueueException;

    Object getFront() throws EmptyQueueException;
    Object getBack() throws EmptyQueueException;
}