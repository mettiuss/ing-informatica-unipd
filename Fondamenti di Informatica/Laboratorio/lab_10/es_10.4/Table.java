public interface Table extends Container {
    void insert(int key, Object value);

    void remove(int key);

    Object find(int key);
}