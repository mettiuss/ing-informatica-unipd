import java.util.Scanner;

public class Scomposizione {
    public static int[] calcolaPrimi(int n) {
        int[] primi = new int[n + 1];
        int index = 0;
        for (int i = 2; i <= n; i++) {
            boolean primo = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    primo = false;
                }
            }
            if (primo) {
                primi[index] = i;
                index++;
            }
        }
        primi[n] = index;
        return primi;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Inserire un numero da scomporre: ");
        int n = console.nextInt();

        int[] primi = calcolaPrimi(n);

        System.out.print(n + " = ");
        for (int i = 0; i < primi[n]; i++) {
            while (n % primi[i] == 0) {
                n = n / primi[i];
                System.out.print(primi[i] + (n != 1 ? " * " : ""));
            }
        }

        console.close();
    }
}