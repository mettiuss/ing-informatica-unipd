import java.util.*;

public class LinkedListTester {

    public static void main(String[] args) {
        // initialization
        LinkedList list = new LinkedList();
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Inserisci un numero intero:");
        int n = console.nextInt();
        for (int i = 0; i < (n / 2); i++) {
            list.addFirst(random.nextInt(100));
            list.addLast(random.nextInt(100));
        }
        if (n % 2 == 1) {
            list.addFirst(random.nextInt(100));
        }
        System.out.println("List generata: " + list);

        System.out.println("Dopo quale elemento aggiungere il numero 1000:");
        n = console.nextInt();
        ListIterator iterator = list.getIterator();
        while (iterator.hasNext()) {
            if (n == (int) iterator.next()) {
                iterator.add(1000);
            }
        }
        System.out.println("List modificata: " + list);

        System.out.println("Inserire l'elemento da eliminare:");
        n = console.nextInt();
        iterator = list.getIterator();
        while (iterator.hasNext()) {
            if (n == (int) iterator.next()) {
                iterator.remove();
            }
        }
        System.out.println("List modificata: " + list);
        list.removeFirst();
        System.out.println("Rimosso il primo elemento: " + list);
        list.removeLast();
        System.out.println("Rimosso l'utimo elemento: " + list);
    }
}
