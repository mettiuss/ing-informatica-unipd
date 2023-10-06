//Realizzare una classe ArrayMultiMap che implementi l'interfaccia MultiMap con un array riempito in parte in cui l'ordine degli elementi non e' importante. Riflettere sulla complessita' dei metodi di inserimento/rimozione/ricerca. Potete provare la classe con il tester MultiMapTester

public class ArrayMultiMap implements MultiMap {
    private Pair[] v;
    private int vSize;
    private static final int INITIAL_SIZE = 10;

    public ArrayMultiMap() {
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
        if (key == null || value == null)
            throw new IllegalArgumentException();
        if (vSize == v.length)
            resize(v, vSize * 2);
        v[vSize++] = new Pair(key, value);
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
        for (int i = 0; i < vSize; i++) {
            if (v[i].getKey().equals(key)) {
                Object obj = v[i].getValue();
                v[i] = v[vSize - 1];
                vSize--;
                return obj;
            }
        }
        return null;
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
        for (int i = 0; i < vSize; i++) {
            if (v[i].getKey().equals(key)) {
                return v[i].getValue();
            }
        }
        return null;
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
