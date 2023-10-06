public class ArrayTable100 implements Table {
    private Object[] v;

    public ArrayTable100() {
        makeEmpty();
    }

    public void makeEmpty() {
        v = new Object[100];
    }

    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 0; i < v.length; i++) {
            if (v[i] != null)
                empty = false;
        }
        return empty;
    }

    public void insert(int key, Object value) {
        if (key < 0 || key >= 100)
            throw new InvalidPositionTableException();
        v[key] = value;
    }

    public void remove(int key) {
        v[key] = null;
    }

    public Object find(int key) {
        return v[key];
    }
}

class InvalidPositionTableException extends RuntimeException {
    public InvalidPositionTableException() {
        super();
    }
}