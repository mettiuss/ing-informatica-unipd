public interface Map extends Container {
  Object get(Object key);
  Object remove(Object key);
  Object put(Object key, Object value);
  Object[] keys();
}
