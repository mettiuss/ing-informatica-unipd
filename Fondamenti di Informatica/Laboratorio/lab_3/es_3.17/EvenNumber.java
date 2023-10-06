import java.util.Scanner;

public class EvenNumber {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci un numero pari");
        int number = console.nextInt();
        if (number % 2 == 1) {
            System.out.println("Il numero non è pari");
            System.out.println("Inserisci un numero PARI");
            number = console.nextInt();
            if (number % 2 == 1) {
                System.out.println("Questo non è ancora un numero pari, ti credevo più intelligente");
                return;
            }
        }
        System.out.println("Ottimo, " + number + " è un bellissimo numero pari");
        console.close();
    }
}