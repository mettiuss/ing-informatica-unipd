import java.util.Scanner;

public class ReverseNumbers {
    public static void main(String[] args) {
        final int ARRAY_STEPS = 2;
        Scanner console = new Scanner(System.in);
        int length = ARRAY_STEPS;
        int i = 0;
        int[] array = new int[length];
        System.out.println("Inserisci una lista di numeri");

        while (console.hasNextInt()) {
            if (i >= length) {
                length += ARRAY_STEPS;
                array = ArrayUtil.resize(array, length);
            }
            array[i] = console.nextInt();
            i++;
        }

        for (int n = array.length - 1; n >= 0; n--) {
            System.out.println(array[n]);
        }
        console.close();
    }
}