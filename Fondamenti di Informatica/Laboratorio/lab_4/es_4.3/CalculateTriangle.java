import java.util.Scanner;

public class CalculateTriangle {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci un triangolo:");

        int l1 = console.nextInt();
        int l2 = console.nextInt();
        int l3 = console.nextInt();

        String lati = "";
        String angoli = "";

        if (l1 + l2 < l3) {
            System.out.println("Inserisci un triangolo valido!");
            return;
        }

        if (l1 == l2 && l2 == l3)
            lati = "equilatero";
        else if (l1 == l2 || l2 == l3 || l3 == l1)
            lati = "isoscele";
        else
            lati = "scaleno";

        if (lati != "equilatero") {
            if (l1 * l1 - l2 * l2 - l3 * l3 > 0)
                angoli = "ottusangolo";
            if (l1 * l1 - l2 * l2 - l3 * l3 == 0)
                angoli = "rettangolo";
            if (l1 * l1 - l2 * l2 - l3 * l3 < 0)
                angoli = "acutangolo";
        }

        System.out.println("triangolo " + lati + " " + angoli);
        console.close();
    }
}
