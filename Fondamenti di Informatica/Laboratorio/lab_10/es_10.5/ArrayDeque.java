import java.util.NoSuchElementException;

public class ArrayDeque implements Deque {
    private Object[] v;
    private int first, last;
    public int INITIAL_SIZE = 3;

    public ArrayDeque() {
        v = new Object[INITIAL_SIZE];
        makeEmpty();
    }

    public boolean isEmpty() {
        return first == last;
    }

    public void makeEmpty() {
        first = 0;
        last = 0;
    }

    public void addFirst(Object element) {
        if (next(first + 1) == last) {
            resize();
        }
        v[next(first)] = element;
        first = next(first + 1);
    }

    public void addLast(Object element) {
        if (next(last - 1) == first) {
            resize();
        }
        v[next(last - 1)] = element;
        last = next(last - 1);
    }

    public Object removeLast() {
        if (isEmpty())
            throw new EmptyDequeException();
        Object obj = v[last];
        last = next(last + 1);
        return obj;
    }

    public Object removeFirst() {
        if (isEmpty())
            throw new EmptyDequeException();
        Object obj = v[first];
        first = next(first - 1);
        return obj;
    }

    public Object getFirst() {
        if (isEmpty())
            throw new EmptyDequeException();
        return v[first];
    }

    public Object getLast() {
        if (isEmpty())
            throw new EmptyDequeException();
        return v[last];
    }

    // metodi di supporto
    public String toString() {
        String s = "[";
        for (int i = 0; i < v.length; i++) {
            s += v[i] + ", ";
        }
        return s + "]";
    }

    private void resize() {
        Object[] t = new Object[v.length * 2];
        System.arraycopy(v, last, t, 0, v.length - last);
        System.arraycopy(v, 0, t, v.length - last, next(first));
        first = v.length - 1;
        last = 0;
        v = t;
    }

    private int next(int current) {
        if (current < 0) {
            return v.length + current;
        }
        return current % v.length;
    }
}

class EmptyDequeException extends NoSuchElementException {
}