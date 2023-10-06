import java.util.Scanner;
import java.util.Locale;

public class ScannerTest {
    public static void main(String[] arg) {
        Scanner console = new Scanner(System.in);
        console.useLocale(Locale.US);

        System.out.println("Inserisci il dato:");
        // int number = console.nextInt();
        // double number = console.nextDouble();
        // String str = console.nextLine();
        // String str = console.next();
        // System.out.println(number);
        console.close();
    }
}