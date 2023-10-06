import java.util.Scanner;

public class PrintToken {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci una frase da tre parole");
        String first = console.next();
        String second = console.next();
        String third = console.next();
        System.out.print(third + " ");
        System.out.print(second + " ");
        System.out.println(first + " ");
        console.close();
    }
}
