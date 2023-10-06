import java.util.Scanner;

public class EncodeCeasar {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci una frase:");
        String line = console.nextLine().toUpperCase();
        System.out.println("Inserisci una chiave:");
        int key = console.nextInt();
        if (key < 1 || key > 25) {
            System.out.println("Chiave di cifratura non valida");
            console.close();
            return;
        }

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                int character = (line.charAt(i) - 65 + key) % 26;
                System.out.print((char) (character + 65));
            } else {
                System.out.print(' ');
            }
        }
        System.out.println();
        console.close();
    }
}