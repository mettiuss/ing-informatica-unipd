//Realizzare una classe SortedArrayMultiMap che implementi l'interfaccia MultiMap con un array riempito in parte in cui le associazioni sono mantenute in ordine di chiave.
//Riflettere sulla complessita' dei metodi di inserimento/rimozione/ricerca e le differenze con l'implementazione dell'esercizio 2. Potete provare la classe con il tester SortedMultiMapTester

public class SortedArrayMultiMap implements MultiMap {
    private Pair[] v;
    private int vSize;
    private static final int INITIAL_SIZE = 10;

    public SortedArrayMultiMap() {
        v = new Pair[INITIAL_SIZE];
        makeEmpty();
    }

    private void resize(Pair[] src, int l) {
        Pair[] w = new Pair[l];
        System.arraycopy(src, 0, w, 0, vSize);
        v = w;
    }

    public void makeEmpty() {
        vSize = 0;
    }

    public boolean isEmpty() {
        return vSize == 0;
    }

    public int size() {
        return vSize;
    }

    /**
     * Inserisce nel dizionario un’associazione avente
     * chiave uguale a key e valore uguale a value.
     * 
     * @param key   la chiave specificata
     * @param value il valore specificato
     * @throws IllegalArgumentException se key o value sono null
     */
    public void insert(Object key, Object value) {
        if (key == null || value == null || !(key instanceof Comparable))
            throw new IllegalArgumentException();
        if (vSize == v.length)
            resize(v, vSize * 2);
        int i = vSize - 1;
        while (i >= 0 && ((Comparable) v[i].getKey()).compareTo(key) > 0) {
            v[i + 1] = v[i];
            i--;
        }
        v[i + 1] = new Pair(key, value);
        vSize++;
    }

    /**
     * Elimina dal dizionario un’associazione di chiave specificata
     * restituendone il valore associato oppure null se non e’
     * presente nel dizionario.
     * 
     * @param key la chiave specificata
     * @return un valore associato alla chiave specificata,
     *         se presente, null altrimenti
     */
    public Object remove(Object key) {
        int pos = binSearch(key);
        if (pos == -1)
            return null;
        Object obj = v[pos].getValue();
        removeItem(pos);
        return obj;
    }

    private void removeItem(int pos) {
        for (int i = pos; i < vSize; i++) {
            v[i] = v[i + 1];
        }
        vSize--;
    }

    /**
     * Se il dizionario contiene un’associazione avente
     * chiave uguale a key, restituisce true,
     * altrimenti restituisce false
     * 
     * @param key la chiave specificata
     * @return uno dei valori associati se la chiave specificata e’
     *         presente, altrimenti null
     */
    public Object find(Object key) {
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

    private int binSearchAlg(Pair[] src, int start, int end, Comparable key) {
        if (start > end)
            return -1;
        int mid = (start + end) / 2;
        Comparable middle = (Comparable) (src[mid].getKey());
        if (middle.compareTo(key) == 0)
            return mid;
        else if (middle.compareTo(key) > 0) {
            return binSearchAlg(src, start, mid - 1, key);
        } else {
            return binSearchAlg(src, mid + 1, end, key);
        }
    }

    /**
     * Se il dizionario contiene una o più associazioni aventi chiave
     * uguale a key, restituisce i valori, altrimenti restituisce un
     * array vuoto
     * 
     * @param key la chiave specificata
     * @return un array con i valori associati alla chiave specificata,
     *         se presente, o un array vuoto se non presente
     */
    public Object[] findAll(Object key) {
        Object[] found = new Object[vSize];
        int iFound = 0;
        for (int i = 0; i < vSize; i++) {
            if (v[i].getKey().equals(key)) {
                found[iFound++] = v[i].getValue();
            }
        }
        Object[] returnObj = new Object[iFound];
        System.arraycopy(found, 0, returnObj, 0, iFound);
        return returnObj;
    }

    /**
     * @return un array contenente le chiavi del
     *         dizionario, eventualmente ripetute. Restituisce un
     *         array vuoto (0 elementi) se il dizionario e’ vuoto
     */
    public Object[] keys() {
        Object[] k = new Object[vSize];
        for (int i = 0; i < vSize; i++) {
            k[i] = v[i].getKey();
        }
        return k;
    }

    /**
     * Se ci sono associazioni di chiave uguale a key, ne
     * restituisce i valori, altrimenti restituisce un
     * array vuoto.
     * 
     * @param key la chiave specificata
     * @return valori associati alla chiave specificata,
     *         se presente, o un array vuoto altrimenti
     */
    public Object[] removeAll(Object key) {
        Object[] removed = new Object[vSize];
        int iRemoved = 0;
        for (int i = 0; i < vSize; i++) {
            if (v[i].getKey().equals(key)) {
                Object obj = v[i].getValue();
                v[i] = v[vSize - 1];
                vSize--;
                removed[iRemoved++] = obj;
            }
        }
        Object[] returnObj = new Object[iRemoved];
        System.arraycopy(removed, 0, returnObj, 0, iRemoved);
        return returnObj;
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
