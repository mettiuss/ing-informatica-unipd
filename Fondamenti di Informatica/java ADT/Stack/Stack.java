public interface Stack extends Container {
    void push(Object obj);
    Object pop() throws EmptyStackException;
    Object top() throws EmptyStackException;
}