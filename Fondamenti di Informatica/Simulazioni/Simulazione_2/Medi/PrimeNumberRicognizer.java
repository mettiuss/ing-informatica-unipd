import java.util.Scanner;

public class PrimeNumberRicognizer {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci un numero: ");
        int n = console.nextInt();

        if (n <= 0) {
            System.out.println(n + ": ingresso non ammesso");
        }

        boolean primo = true;
        int divisore = 0;
        for (int i = 2; Math.pow(i, 2) <= n && primo; i++) {
            if (n % i == 0) {
                divisore = i;
                primo = false;
            }
        }
        if (primo) {
            System.out.println("Il numero " + n + " è primo");
        } else {
            System.out.println(n + " è divisibile per " + divisore);
        }

        console.close();
    }
}