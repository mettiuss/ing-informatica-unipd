public class ArrayDeque implements Deque {
    private Object[] v;
    private int head, tail;

    public ArrayDeque() {
        v = new Object[5];
        makeEmpty();
    }

    public void makeEmpty() {
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void addFirst(Object obj) {
        if (increment(head) == tail) resize();
        v[head] = obj;
        head = increment(head);
    }

    public void addLast(Object obj) {
        if (decrement(tail) == head) resize();
        v[decrement(tail)] = obj;
        tail = decrement(tail);
    }

    public Object removeLast() {
        if (isEmpty()) throw new EmptyQueueException();
        tail = increment(tail);
        return v[tail];
    }

    public Object removeFirst() {
        if (isEmpty()) throw new EmptyQueueException();
        head = decrement(head);
        return v[head];
    }

    public Object getFront() {
        if (isEmpty()) throw new EmptyQueueException();
        return v[decrement(head)];
    }

    public Object getBack() {
        if (isEmpty()) throw new EmptyQueueException();
        return v[tail];
    }

    private int increment(int i) {
        return (i + 1) % v.length;
    }

    private int decrement(int i) {
        if (i == 0) {
            return v.length - 1;
        }
        return (i - 1);
    }

    private void resize() {
        Object[] t = new Object[v.length * 2];
        if (head > tail) {
            System.arraycopy(v, 0, t, tail, head - tail);
            head = head - tail;
            tail = 0;
        } else {
            System.arraycopy(v, tail, t, 0, v.length - tail);
            System.arraycopy(v, 0, t, v.length - tail, head);
            head = head + tail - 1;
            tail = 0;
        }
        v = t;
    }

    public String toString() {
        String t = "";
        for (int i = 0; i < v.length; i++) {
            t += " " + v[i];
        }
        return t;
    }
}

class EmptyQueueException extends RuntimeException {}