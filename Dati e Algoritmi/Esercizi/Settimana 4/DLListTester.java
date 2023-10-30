import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class DLListTester {
    public static void main(String[] args) // un po' di collaudo
    {
        try {
            test1();
        } catch (InvalidPositionException | EmptyListException | BoundaryViolationException e) {
            e.printStackTrace();
        }
        test2();
        // eventuali altri test
    }

    private static void test1() throws InvalidPositionException, EmptyListException, BoundaryViolationException {
        PositionalList<String> list = new DLList<String>();
        try {
            list.first();
            error();
        } catch (EmptyListException e) {
        }
        // System.out.println(list); // ()
        list.addLast("O");
        // System.out.println(list); // (O)
        list.addFirst("T");
        // System.out.println(list); // (T,O)
        if (list.size() == 2)
            list.addAfter(list.first(), "U");
        // System.out.println(list); // (T,U,O)
        list.addBefore(list.last(), "K");
        // System.out.println(list); // (T,U,K,O)
        list.addBefore(list.last(), "T");
        // System.out.println(list); // (T,U,K,T,O)
        if (!"K".equals(list.remove(list.prev(list.prev(list.last())))))
            error();
        // System.out.println(list); // (T,U,T,O)
        if (!"O".equals(list.set(list.last(), "T")))
            error();
        // System.out.println(list); // (T,U,T,T)
        list.addAfter(list.last(), "O");
        // System.out.println(list); // (T,U,T,T,O)
        list.addAfter(list.last(), "K");
        // System.out.println(list); // (T,U,T,T,O,K)
        list.addBefore(list.last(), "O");
        // System.out.println(list); // (T,U,T,T,O,O,K)
        list.addBefore(list.prev(list.last()), " ");
        // System.out.println(list); // (T,U,T,T,O, ,O,K)
        Position<String> p = list.first();
        String finalTestString = "";
        for (int i = 1; i < list.size(); i++, p = list.next(p))
            finalTestString += p.getElement();
        finalTestString += p.getElement();
        if (finalTestString.equals("TUTTO OK"))
            System.out.println("Test 1 OK");
        else
            System.out.println("Test 1 Failed: " + finalTestString);
    }

    private static void test2() {
        DLList<String> list = new DLList<String>();
        list.addLast("O");
        list.addLast("K");
        list.addLast("2");
        String finalTestString = "";
        java.util.Iterator<String> iter = list.iterator();
        while (iter.hasNext())
            finalTestString += iter.next();

        Iterable<String> list2 = (Iterable<String>) list.clone();
        java.util.Iterator<String> iter2 = list2.iterator();
        while (iter2.hasNext())
            finalTestString += iter2.next();
        if (finalTestString.equals("OK2OK2"))
            System.out.println("Test 2 OK");
        else
            System.out.println("Test 2 Failed: " + finalTestString);
    }

    private static void error() {
        System.err.println("ERROR");
    }
}
