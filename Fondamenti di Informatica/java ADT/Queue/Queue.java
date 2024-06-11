public interface Queue extends Container {
    void enqueue(Object obj);
    Object dequeue() throws EmptyQueueException;
    Object getFront() throws EmptyQueueException;
}