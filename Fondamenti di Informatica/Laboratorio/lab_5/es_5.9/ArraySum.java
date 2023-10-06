import java.util.Scanner;

public class ArraySum {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Quanti elementi?");
        double sum = 0;
        int length = console.nextInt();
        double[] firstArray = new double[length];
        double[] secondArray = new double[length];

        System.out.println("Inserisci gli elementi del primo array, uno per riga:");
        for (int i = 0; i < length; i++) {
            firstArray[i] = console.nextDouble();
        }

        System.out.println("Inserisci gli elementi del secondo array, uno per riga:");
        for (int i = 0; i < length; i++) {
            secondArray[i] = console.nextDouble();
        }

        System.out.println("In output:\n");

        for (int i = 0; i < length; i++) {
            sum += firstArray[i] + secondArray[i];
            System.out.print(firstArray[i] + secondArray[i] + " ");
        }
        System.out.println();
        System.out.println(sum);
        console.close();
    }
}