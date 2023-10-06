import java.util.Scanner;

public class PrintTimeInterval {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci il primo orario: ");
        String first = console.nextLine();
        System.out.println("Inserisci il secondo orario: ");
        String second = console.nextLine();
        int hour = Integer.parseInt(second.substring(0, 2)) - Integer.parseInt(first.substring(0, 2));
        int minute = Integer.parseInt(second.substring(2, 4)) - Integer.parseInt(first.substring(2, 4));
        System.out.println(hour + " ore e " + minute + " minuti");
        console.close();
    }
}
