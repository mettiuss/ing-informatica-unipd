import java.util.Scanner;

public class EuclideMCD {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci il primo numero:");
        int n = console.nextInt();
        System.out.println("Inserisci il secondo numero:");
        int m = console.nextInt();

        // scambia i numeri nel caso in cui fossero nell'ordine errato
        if (n > m) {
            int c = m;
            m = n;
            n = c;
        }

        while (m % n != 0) {
            int c = m;
            m = n;
            n = c % n;
        }

        System.out.println("Il MCD è: " + n);
        console.close();
    }
}