import java.util.Scanner;

public class QEater {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean q;
        do {
            System.out.println("Dammi una q");
            String in = console.nextLine();
            if (in.toLowerCase().equals("q")) {
                System.out.println("Grazie");
                q = true;
            } else {
                System.out.println("Non hai capito");
                q = false;
            }
        } while (!q);

        console.close();
    }
}