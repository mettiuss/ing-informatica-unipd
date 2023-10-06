import java.util.Scanner;

public class SomeNumbers {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        int sum = 0;
        int absSum = 0;
        int moltiplication = 1;

        int index = 0;

        System.out.println("Inserisci la lista di numeri:");
        while (console.hasNextInt()) {
            int n = console.nextInt();
            sum += n;
            absSum += Math.abs(n);
            moltiplication = moltiplication * n;
            index++;
        }

        if (index < 2) {
            System.out.println("Inserisci almeno 2 valori");
            console.close();
            return;
        }

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + sum / index);
        System.out.println("Sum of absolute values: " + absSum);
        System.out.println("Moltiplication: " + moltiplication);
        console.close();
    }
}