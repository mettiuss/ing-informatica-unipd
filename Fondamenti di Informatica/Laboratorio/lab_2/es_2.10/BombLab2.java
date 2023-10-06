import java.util.*;

public class BombLab2 {

    // Primo stadio
    public static int first(int n) {
        int result = n - 10 + 3 * 2;
        return result;
    }

    // Secondo stadio
    public static int second(int n) {
        int result = n / 10 + n % 10;
        return result;
    }

    // Terzo stadio
    public static int third(int n) {

        int result = n % 10;
        n = n / 10;
        result = result + n % 10;
        n = n / 10;
        result = result + n % 10;
        n = n / 10;
        result = result + n % 10;
        n = n / 10;
        result = result + n % 10;
        n = n / 10;
        result = result + n % 10;

        return result;

    }

    public static void main(String[] args) {

        if (args.length != 1) {

            System.out.println("Se il tuo numero di matricola e' 123456, devi scrivere\n java BombLab 123456");
            System.exit(1);
        }
        int matricola = Integer.parseInt(args[0]);

        Scanner console = new Scanner(System.in);
        System.out.println("Beep beep beep... la bomba sta per esplodere! Ahahahah!");
        System.out.println("Inserisci il valore per disinnescare il primo stadio della bomba");
        int firstAnswer = console.nextInt();
        if (firstAnswer != first(matricola)) {
            System.out.println("BOOOOOOM!!!!!!!");
            System.out.println("Spiacente... sei esploso, fine dei giochi");
            System.exit(1);
        }
        System.out.println("Sei solo stato/a fortunato/a! Prova con il secondo stadio...");

        int secondAnswer = console.nextInt();
        if (secondAnswer != second(matricola)) {
            System.out.println("BOOOOOOM!!!!!!!");
            System.out.println("Spiacente... sei esploso, fine dei giochi");
            System.exit(1);
        }
        System.out.println("Finora ho scherzato, risolvi il terzo stadio se ne sei capace");
        int thirdAnswer = console.nextInt();
        if (thirdAnswer != third(matricola)) {
            System.out.println("BOOOOOOM!!!!!!!");
            System.out.println("Spiacente... sei esploso, fine dei giochi");
            System.exit(1);
        }
        System.out.println("Per questa volta hai vinto tu, matricola " + matricola + "... ma non finisce qui!");
        console.close();
    }

}
