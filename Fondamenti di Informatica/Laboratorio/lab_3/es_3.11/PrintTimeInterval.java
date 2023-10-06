import java.util.Scanner;

public class PrintTimeInterval {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Inserisci il primo orario: ");
        String firstInput = console.nextLine();
        System.out.print("Inserisci il secondo orario: ");
        String secondInput = console.nextLine();
        int first = Integer.parseInt(firstInput.substring(0, 2)) * 60 + Integer.parseInt(firstInput.substring(2, 4));
        int second = Integer.parseInt(secondInput.substring(0, 2)) * 60 + Integer.parseInt(secondInput.substring(2, 4));
        int result = ((second - first) + 24 * 60) % (24 * 60);
        System.out.println("Tempo trascorso: " + result / 60 + " ore e " + result % 60 + " minuti");
        console.close();
    }
}
