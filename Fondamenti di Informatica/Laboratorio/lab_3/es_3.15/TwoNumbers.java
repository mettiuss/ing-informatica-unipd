import java.util.Scanner;

public class TwoNumbers {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Scrivi il primo numero");
        int one = console.nextInt();
        System.out.println("Scrivi il secondo numero");
        int two = console.nextInt();
        System.out.println("La somma è: " + (one + two));
        System.out.println("Il prodotto è: " + (one * two));
        System.out.println("Il valore medio è: " + (one + two) / 2.0);
        if (one > two) {
            System.out.println("Il valore massimo è: " + one);
            System.out.println("Il valore minimo è: " + two);
        } else {
            System.out.println("Il valore massimo è: " + two);
            System.out.println("Il valore minimo è: " + one);
        }
        System.out.println("Il valore assoluto della differenza è: " + (one - two > 0 ? one - two : two - one));
        console.close();
    }
}