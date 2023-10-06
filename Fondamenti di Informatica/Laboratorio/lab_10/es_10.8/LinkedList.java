public class LinkedList implements Container {

    private ListNode head, tail;

    // costruttore
    public LinkedList() {
        makeEmpty();
    }

    // metodi di Container
    public void makeEmpty() {
        head = tail = new ListNode();
    }

    public boolean isEmpty() {
        return (head == tail);
    }

    // metodi di accesso
    public Object getFirst() { // operazione O(1)
        if (isEmpty()) {
            throw new EmptyLinkedListException();
        }
        return head.getNext().getElement();
    }

    public Object getLast() { // operazione O(1)
        if (isEmpty()) {
            throw new EmptyLinkedListException();
        }
        return tail.getElement();
    }

    // metodi modificatori di inserimento
    public void addFirst(Object e) {
        head.setElement(e);
        head = new ListNode(null, head);
    }

    public void addLast(Object e) {
        tail.setNext(new ListNode(e, null));
        tail = tail.getNext();
    }

    // metodi modificatori di rimozione
    public Object removeFirst() {
        Object e = getFirst();
        head = head.getNext();
        head.setElement(null);
        return e;
    }

    public Object removeLast() {
        Object e = getLast();
        ListNode temp = head;
        while (temp.getNext() != tail) {
            temp = temp.getNext();
        }
        tail = temp;
        tail.setNext(null);
        return e;
    }

    public String toString() {
        String s = "";
        ListIterator iter = new LinkedListIterator(head);
        while (iter.hasNext()) {
            s += iter.next() + " ";
        }
        return s;
    }

    // classe interna privata che descrive il nodo della lista
    private class ListNode {

        private Object element; // riferimento al dato
        private ListNode next; // riferimento al nodo successivo

        public ListNode(Object e, ListNode n) {
            element = e;
            next = n;
        }

        public ListNode() {
            element = null;
            next = null;
        }

        public Object getElement() {
            return element;
        }

        public ListNode getNext() {
            return next;
        }

        public void setElement(Object e) {
            element = e;
        }

        public void setNext(ListNode n) {
            next = n;
        }
    }

    // metodo che restituisce un iteratore sulla lista concatenata
    public ListIterator getIterator() {
        return new LinkedListIterator(head);
    }

    // classe che definisce l'iteratore
    private class LinkedListIterator implements ListIterator {

        public LinkedListIterator(ListNode head) {
            current = head;
            previous = null;
        }

        public boolean hasNext() {
            return current.getNext() != null;
        }

        public Object next() {
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            previous = current;
            current = current.getNext();
            return current.getElement();
        } // nodo che precede la posizione attuale // (non è mai null)

        public void add(Object obj) {
            ListNode n = new ListNode(obj, current.getNext());
            current.setNext(n);
            previous = current;
            current = n; // il nodo prima l’iteratore e’ n

            if (current.getNext() == null)
                tail = current; // gestione ultimo nodo
        }

        public void remove() {
            if (previous == null) {
                throw new IllegalStateException();
            }
            previous.setNext(current.getNext());
            current = previous;
            if (current.getNext() == null)
                tail = current; // gestione ultimo nodo
            previous = null; // non si puo’ fare remove due volte
        }

        private ListNode current;
        private ListNode previous;
    }
}

class EmptyLinkedListException extends RuntimeException {
}
