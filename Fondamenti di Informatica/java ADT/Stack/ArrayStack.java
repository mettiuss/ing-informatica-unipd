public class ArrayStack implements Stack {
    private Object[] v;
    private int vSize;

    public ArrayStack() {
        v = new Object[10];
        makeEmpty();
    }

    public void makeEmpty() {
        vSize = 0;
    }

    public boolean isEmpty() {
        return vSize == 0;
    }

    public void push(Object obj) {
        if (vSize >= v.length) {
            resize();
        }
        v[vSize++] = obj;
    }

    public Object pop() {
        if (isEmpty()) throw new EmptyStackException();
        vSize--;
        return v[vSize];
    }

    public Object top() {
        if (isEmpty()) throw new EmptyStackException();
        return v[vSize - 1];
    }

    private void resize() {
        Object[] t = new Object[v.length * 2];
        System.arraycopy(v, 0, t, 0, v.length);
        v = t;
    }
}

class EmptyStackException extends RuntimeException{}