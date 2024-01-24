import java.util.Scanner;

public class MultipleLister {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci n: ");
        int n = console.nextInt();
        System.out.println("Inserisci max: ");
        int max = console.nextInt();

        for (int i = n; i <= max; i += n) {
            System.out.print(i + " ");
        }

        console.close();
    }
}