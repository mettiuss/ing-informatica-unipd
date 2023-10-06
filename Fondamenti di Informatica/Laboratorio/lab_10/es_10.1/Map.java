public interface Map extends Container {
    Object put(Object key, Object value);

    Object get(Object key);

    Object remove(Object key);

    Object[] keys();
}
