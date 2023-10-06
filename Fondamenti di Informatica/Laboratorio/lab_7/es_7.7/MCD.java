import java.util.*;

public class MCD {
    public static void main(String[] args) {
        int m = 0, n = 0;
        try (Scanner console = new Scanner(System.in)) {
            System.out.println("Inserisci due numeri interi positivi");
            m = console.nextInt();
            n = console.nextInt();
            if (m < 1 || n < 1) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Valore non valido");
            System.exit(1);
        }

        // inverto le variabili se sono nell'ordine errato
        if (m < n) {
            int t = m;
            m = n;
            n = t;
        }

        // algoritmo di Euclide
        System.out.println("MCD: " + recursiveMCD(m, n));
    }

    private static int recursiveMCD(int m, int n) {
        int i = n;
        if (m % n != 0) {
            i = recursiveMCD(n, m % n);
        }
        return i;
    }
}
