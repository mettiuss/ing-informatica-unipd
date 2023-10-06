import java.util.Scanner;

public class PrintToken {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci una frase da tre parole");
        System.out.println(console.next());
        System.out.println(console.next());
        System.out.println(console.next());
        console.close();
    }
}
