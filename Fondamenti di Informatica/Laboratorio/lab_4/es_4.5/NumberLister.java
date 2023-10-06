import java.util.Scanner;

public class NumberLister {
    public static void main(String[] args) {
        final int maxLenth = 3;

        Scanner console = new Scanner(System.in);
        System.out.println("***STAMPA DEI NUMERI DA 1 A N***");
        System.out.print("N?: ");
        int target = console.nextInt();
        if (target <= 0) {
            System.out.println("Ingresso nonammesso");
        }

        for (int i = 1; i <= target; i++) {
            String out = i + "";
            while (out.length() < maxLenth) {
                out = " " + out;
            }

            System.out.print(out + " ");

            if (i % 10 == 0) {
                System.out.println();
            }
        }
        console.close();
    }
}