import java.util.Scanner;

public class EraPrimeLister {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Insersci un numero intero");
        int n = console.nextInt();

        boolean[] list = new boolean[n - 1];
        int i = 2;
        while (i < n) {
            boolean deletion = false;
            for (int j = 2; j <= n; j++) {
                if (j != i && j % i == 0) {
                    list[j - 2] = true;
                    deletion = true;
                }
            }
            if (!deletion)
                break;
            i++;
        }

        for (int j = 0; j < list.length; j++) {
            if (!list[j])
                System.out.print((j + 2) + " ");
        }
        System.out.println();
        console.close();
    }
}
