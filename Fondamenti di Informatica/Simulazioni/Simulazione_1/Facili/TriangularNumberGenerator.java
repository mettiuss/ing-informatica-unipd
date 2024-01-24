import java.util.Scanner;

public class TriangularNumberGenerator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci un numero >= 1");
        int k = console.nextInt();

        for (int i = 1; i < k; i++) {
            System.out.println(triangularNumber(i));
        }

        console.close();
    }

    private static int triangularNumber(int k) {
        if (k < 2)
            return 1;
        return k + triangularNumber(k - 1);
    }
}