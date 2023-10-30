import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

interface Container {
    int size(); // Quantità di dati, non di memoria

    boolean isEmpty();
}

interface Position<T> {
    T getElement();

    void setElement(T element);
}

public interface PositionalList<T> extends Container {
    Position<T> first() throws EmptyListException;

    Position<T> last() throws EmptyListException;

    Position<T> prev(Position<T> p) throws InvalidPositionException, BoundaryViolationException;

    Position<T> next(Position<T> p) throws InvalidPositionException, BoundaryViolationException;

    void addFirst(T element);

    void addLast(T element);

    void addBefore(Position<T> p, T element) throws InvalidPositionException;

    void addAfter(Position<T> p, T element) throws InvalidPositionException;

    T set(Position<T> p, T element) throws InvalidPositionException;

    T remove(Position<T> p) throws InvalidPositionException;
}