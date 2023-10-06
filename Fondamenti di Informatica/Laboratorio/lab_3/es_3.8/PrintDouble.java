import java.util.Scanner;

public class PrintDouble {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci un numero in virgola mobile: ");
        double first = console.nextDouble();
        System.out.println("Inserisci un altro numero in virgola mobile: ");
        double second = console.nextDouble();
        System.out.println("Inserisci un altro numero in virgola mobile: ");
        double third = console.nextDouble();
        System.out.println(first + " + " + second + " + " + third + " = " + (first + second + third));
        console.close();
    }
}
