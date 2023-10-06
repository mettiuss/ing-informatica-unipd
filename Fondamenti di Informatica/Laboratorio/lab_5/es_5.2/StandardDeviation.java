import java.util.Scanner;

public class StandardDeviation {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        int sum = 0;
        int powSum = 0;

        int index = 0;

        System.out.println("Inserisci la lista di numeri:");
        while (console.hasNextInt()) {
            int n = console.nextInt();
            sum += n;
            powSum += Math.pow(n, 2);
            index++;
        }

        if (index < 2) {
            System.out.println("Inserisci almeno 2 valori");
            console.close();
            return;
        }

        System.out.println("Standard Deviation: " + Math.sqrt((powSum - sum * sum / index) / (index - 1)));
        console.close();
    }
}