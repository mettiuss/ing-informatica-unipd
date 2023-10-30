import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class DLList<T> implements PositionalList<T>, Iterable<T> {
    private class DLLNode<T> implements Position<T> {
        private T element;
        private DLLNode<T> next, prev;
        private DLList<T> owner;

        public DLLNode(DLList<T> o, T e, DLLNode<T> p, DLLNode<T> n) {
            owner = o;
            element = e;
            prev = p;
            next = n;
        }

        public DLLNode(DLList<T> o) {
            this(o, null, null, null);
        }

        public T getElement() {
            return element;
        }

        public DLLNode<T> getPrev() {
            return prev;
        }

        public DLLNode<T> getNext() {
            return next;
        }

        public void setElement(T e) {
            element = e;
        }

        public void setNext(DLLNode<T> n) {
            next = n;
        }

        public void setPrev(DLLNode<T> p) {
            prev = p;
        }

        public boolean checkOwner(DLList<T> o) {
            return o == owner;
        }

        public void setOwner(DLList<T> o) {
            owner = o;
        }
    }

    private class DLLIterator<T> implements Iterator<T> {
        private DLList<T>.DLLNode<T> pos;

        public DLLIterator(DLList<T> l) {
            pos = l.head;
        }

        public boolean hasNext() {
            return pos.getNext().getNext() != null;
        }

        public T next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            pos = pos.getNext();
            return pos.getElement();
        }
    }

    private DLLNode<T> head, tail;
    private int size;

    public DLList() {
        size = 0;
        head = new DLLNode<T>(this);
        tail = new DLLNode<T>(this);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public DLLNode<T> first() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();
        return head.getNext();
    }

    public DLLNode<T> last() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException();
        return tail.getPrev();
    }

    public DLLNode<T> prev(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
        DLLNode<T> pos = check_position(p);

        if (pos.getPrev() == head)
            throw new BoundaryViolationException();

        return pos.getPrev();
    }

    public DLLNode<T> next(Position<T> p) throws InvalidPositionException, BoundaryViolationException {
        DLLNode<T> pos = check_position(p);

        if (pos.getNext() == tail)
            throw new BoundaryViolationException();

        return pos.getNext();
    }

    public void addFirst(T element) {
        DLLNode<T> pos = new DLLNode<T>(this, element, head, head.getNext());
        head.getNext().setPrev(pos);
        head.setNext(pos);
        size++;
    }

    public void addLast(T element) {
        DLLNode<T> pos = new DLLNode<T>(this, element, tail.getPrev(), tail);
        tail.getPrev().setNext(pos);
        tail.setPrev(pos);
        size++;
    }

    public void addBefore(Position<T> p, T element) throws InvalidPositionException {
        DLLNode<T> pos = check_position(p);
        DLLNode<T> e = new DLLNode<T>(this, element, pos.getPrev(), pos);
        pos.getPrev().setNext(e);
        pos.setPrev(e);
        size++;
    }

    public void addAfter(Position<T> p, T element) throws InvalidPositionException {
        DLLNode<T> pos = check_position(p);
        DLLNode<T> e = new DLLNode<T>(this, element, pos, pos.getNext());
        pos.getNext().setPrev(e);
        pos.setNext(e);
        size++;
    }

    public T set(Position<T> p, T element) throws InvalidPositionException {
        DLLNode<T> pos = check_position(p);
        T el = pos.getElement();
        pos.setElement(element);
        return el;
    }

    public T remove(Position<T> p) throws InvalidPositionException {
        DLLNode<T> pos = check_position(p);
        DLLNode<T> prev = pos.getPrev();
        DLLNode<T> next = pos.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        pos.setOwner(null);
        size--;
        return pos.element;
    }

    private DLLNode<T> check_position(Position<T> p) throws InvalidPositionException {
        if (p == null || !(p instanceof DLLNode))
            throw new InvalidPositionException();
        DLLNode<T> pos = (DLLNode<T>) p;
        if (!pos.checkOwner(this))
            throw new InvalidPositionException();
        return pos;
    }

    public Iterator<T> iterator() {
        return new DLLIterator(this);
    }

    public Iterable<T> clone() {
        DLList<T> list = new DLList<T>();
        java.util.Iterator<T> iter = iterator();
        while (iter.hasNext())
            list.addLast(iter.next());
        return list;
    }
}
