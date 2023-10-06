import java.util.*;

public class MinMax {
    public static void main(String[] args) {
        int dim = 0, n = 0;
        try (Scanner console = new Scanner(System.in)) {
            System.out.println("Inserisci la lunghezza dell'array");
            dim = console.nextInt();
            System.out.println("Inserisci la variabilita' dei numeri contenuti in esso");
            n = console.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Valore non valido");
            System.exit(1);
        }

        int[] array = new int[dim];

        int min = dim, max = 0;
        Random r = new Random();

        for (int i = 0; i < array.length; i++) {
            int random = r.nextInt(n);
            if (random < min)
                min = random;
            if (random > max)
                max = random;
            array[i] = random;
        }

        System.out.println("Minimo: " + min);
        System.out.println("Massimo: " + max);
    }
}
