import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci una frase");
        String line = console.nextLine();

        for (int i = line.length(); i > 0; i--) {
            System.out.print(line.charAt(i - 1));
        }
        System.out.println();
        console.close();
    }
}