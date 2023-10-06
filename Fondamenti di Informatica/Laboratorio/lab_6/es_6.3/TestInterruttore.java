import java.util.Scanner;

public class TestInterruttore {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        Interruttore one = new Interruttore();
        Interruttore two = new Interruttore();

        System.out.println("interruttore 1: " + one.printStatus());
        System.out.println("interruttore 2: " + two.printStatus());
        System.out.println("Scegli l'interruttore 1 o 2 (0 per uscire)");

        while (console.hasNextInt()) {
            int i = console.nextInt();
            switch (i) {
                case 0:
                    return;
                case 1:
                    one.changeStatus();
                    break;
                case 2:
                    two.changeStatus();
                    break;
            }
            System.out.println("interruttore 1: " + one.printStatus());
            System.out.println("interruttore 2: " + two.printStatus());
            System.out.println("Scegli l'interruttore 1 o 2 (0 per uscire)");
        }
        console.close();
    }
}
