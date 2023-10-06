//Realizzare una classe SortedArraySortedMap che implementi l'interfaccia SortedMap utilizzando un array riempito in parte in cui le associazioni sono mantenute ordinate per chiave. Riflettere sulla complessita' dei metodi di inserimento/rimozione/ricerca. Poiche' SortedMap estende Map che a sua volta estende Container, tutte e tre le interfacce dovranno essere presenti nella cartella in cui compilate e tutti i metodi in esse definite devono essere implementati. Potete testare il funzionamente con SortedMapTester, oppure creare voi stessi un tester.

public class SortedArraySortedMap implements SortedMap {
    private Pair[] v;
    private int vSize;
    private static final int CAPACITY = 10;

    public SortedArraySortedMap() {
        v = new Pair[CAPACITY];
        makeEmpty();
    }

    private void resize(Pair[] src, int length) {
        Pair[] w = new Pair[length];
        System.arraycopy(src, 0, w, 0, src.length);
        v = w;
    }

    public int size() {
        return vSize;
    }

    public void makeEmpty() {
        vSize = 0;
    }

    public boolean isEmpty() {
        return vSize == 0;
    }

    public Object put(Object key, Object value) {
        if (key == null || value == null || !(key instanceof Comparable))
            throw new IllegalArgumentException();

        if (v.length == vSize)
            resize(v, vSize * 2);

        Object obj = remove(key);
        int i = vSize - 1;
        while (i >= 0 && ((Comparable) v[i].getKey()).compareTo(key) > 0) {
            v[i + 1] = v[i];
            i--;
        }
        v[i + 1] = new Pair(key, value);
        vSize++;
        return obj;
    }

    public Object get(Object key) {
        int pos = binSearch(key);
        if (pos == -1)
            return null;
        return v[pos].getValue();
    }

    private int binSearch(Object key) {
        if (!(key instanceof Comparable))
            return -1;
        return binSearchAlg(v, 0, vSize - 1, (Comparable) key);
    }

    private static int binSearchAlg(Pair[] src, int start, int end, Comparable key) {
        if (start > end)
            return -1;
        int mid = (end + start) / 2;
        Comparable middle = (Comparable) (src[mid].getKey());
        if (middle.compareTo(key) == 0) {
            return mid;
        } else if (middle.compareTo(key) > 0) {
            return binSearchAlg(src, start, mid - 1, key);
        } else {
            return binSearchAlg(src, mid + 1, end, key);
        }
    }

    public Object remove(Object key) {
        int pos = binSearch(key);
        if (pos == -1)
            return null;
        Object obj = v[pos].getValue();
        for (int i = pos; i < vSize; i++) {
            v[i] = v[i + 1];
        }
        vSize--;
        return obj;
    }

    public Object[] keys() {
        return sortedKeys();
    }

    public Comparable[] sortedKeys() {
        Comparable[] keys = new Comparable[vSize];
        for (int i = 0; i < vSize; i++) {
            keys[i] = (Comparable) (v[i].getKey());
        }
        return keys;
    }

    private class Pair {
        private Object key;
        private Object value;

        public Pair(Object key, Object value) {
            setKey(key);
            setValue(value);
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
    }
}
