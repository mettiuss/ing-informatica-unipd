import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci il tuo nome: ");
        System.out.println("Ciao, " + console.nextLine());
        console.close();
    }
}
