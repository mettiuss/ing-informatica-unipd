public class ArrayMap implements Map {

  private Pair[] v;
  private int vSize;

  public ArrayMap() {
    v = new Pair[2];
    makeEmpty();
  }

  public void makeEmpty() {
    vSize = 0;
  }

  public boolean isEmpty() {
    return vSize == 0;
  }

  public Object get(Object key) {
    int index = find(key);
    if (index == -1) return null;
    return v[index].getValue();
  }

  public Object remove(Object key) {
    int index = find(key);
    if (index == -1) return null;
    Object value = v[index].getValue();
    for (int j = index; j < vSize; j++) {
      v[index - 1] = v[index];
    }
    vSize--;
    return value;
  }

  public Object put(Object key, Object value) {
    int index = find(key);
    if (index == -1) {
      if (vSize + 1 == v.length) {
        resize();
      }
      v[vSize++] = new Pair(key, value);
      sort();
      return null;
    }
    Object old = v[index].getValue();
    v[index] = new Pair(key, value);
    sort();
    return old;
  }

  public Object[] keys() {
    Object[] keys = new Object[vSize];
    for (int i = 0; i < vSize; i++) {
      keys[i] = v[i].getKey();
    }
    return keys;
  }

  private void sort() {
    for (int i = 1; i < vSize; i++) {
      Pair p = v[i];
      int j = i;
      while(((Comparable)p.getKey()).compareTo(v[j].getKey()) > 0) {
        v[j] = v[j - 1];
      }
      v[j - 1] = p;
    }
  }

  private int find(Object key) {
    for (int i = 0; i < vSize; i++) {
      if (v[i].getKey().equals(key)) return i;
    }
    return -1;
  }

  private void resize() {
    Pair[] t = new Pair[v.length * 2];
    System.arraycopy(v, 0, t, 0, v.length);
    v = t;
  }

  private class Pair {

    protected Object key;
    protected Object value;

    public Pair(Object key, Object value) {
      this.key = key;
      this.value = value;
    }

    public Object getKey() {
      return key;
    }

    public Object getValue() {
      return value;
    }
  }

  public String toString() {
    String t = "";
    for (int i = 0; i < vSize; i++) {
      t += " " + v[i].getKey();
    }
    return t;
  }
}
