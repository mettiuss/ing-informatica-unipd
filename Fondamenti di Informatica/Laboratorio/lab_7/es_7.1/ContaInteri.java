import java.util.*;

public class ContaInteri {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci dei numeri interi");
        int sum = 0;

        while (console.hasNext()) {
            try {
                sum += console.nextInt();
            } catch (InputMismatchException e) {
                console.next();
            }
        }

        System.out.println("La somma dei valori introdotti e': " + sum);
        console.close();
    }
}
