import java.util.*;

public class ListReverse {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Inserisci un numero intero:");
        int n = console.nextInt();

        for (int i = 0; i < n; i++) {
            list.addFirst(random.nextInt(100));
        }

        System.out.println(list);

        printReverse(list.getIterator());
    }

    public static void printReverse(ListIterator l) {
        String t = "";
        while (l.hasNext()) {
            t = l.next() + " " + t;
        }
        System.out.println(t);
    }
}
