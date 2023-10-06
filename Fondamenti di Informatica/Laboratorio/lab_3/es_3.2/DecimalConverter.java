import java.util.Scanner;

public class DecimalConverter {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci il numero da convertire: ");
        int number = console.nextInt();
        System.out.println("Inserisci la base: ");
        int base = console.nextInt();

        if (number > (Math.pow(base, 3) - 1)) {
            System.out.println(
                    "Il numero è troppo grande, il massimo per questa base è: " + (int) (Math.pow(base, 3) - 1));
            console.close();
            return;
        }

        System.out.println("Il numero " + number + " in base " + base + " è uguale a: " + fromBaseTen(number, base));
        console.close();
    }

    private static String fromBaseTen(int number, int finalBase) {
        int three = number % finalBase;
        number = number / finalBase;
        int two = number % finalBase;
        number = number / finalBase;
        int one = number % finalBase;
        number = number / finalBase;
        return "" + one + two + three;
    }

    /*
     * private static int toBaseTen(int number, int initialBase) {
     * return ((number / 100 % 10) * initialBase) + ((number / 10 % 10) *
     * initialBase) + ((number % 10) * initialBase);
     * }
     */
}
