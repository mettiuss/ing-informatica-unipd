import java.util.Scanner;

public class DNA {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci una sequenza di DNA:");
        String dna = console.nextLine();

        for (int i = dna.length() - 1; i >= 0; i--) {
            if (dna.charAt(i) == 'A')
                System.out.print("T");
            else if (dna.charAt(i) == 'T')
                System.out.print("A");
            else if (dna.charAt(i) == 'C')
                System.out.print("G");
            else if (dna.charAt(i) == 'G')
                System.out.print("C");
        }
        System.out.println();
        console.close();
    }
}