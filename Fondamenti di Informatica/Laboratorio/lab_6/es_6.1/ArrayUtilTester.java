import java.util.Scanner;

public class ArrayUtilTester {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci la dimensione dell'array: ");
        int vSize = console.nextInt();
        System.out.println("Inserisci la variabilita' dei numeri casuali: ");
        int n = console.nextInt();

        int[] v = ArrayUtil.randomIntArray(vSize, n);
        System.out.println(ArrayUtil.printArray(v, vSize));

        System.out.println("Inserisci un valore da aggiungere e la sua posizione: ");
        int value = console.nextInt();
        int pos = console.nextInt();
        vSize++;
        v = ArrayUtil.resize(v, vSize);
        ArrayUtil.insert(v, vSize, pos, value);
        System.out.println(ArrayUtil.printArray(v, vSize));

        System.out.println("Inserisci la posizione del valore da eliminare: ");
        int delPos = console.nextInt();
        ArrayUtil.remove(v, vSize, delPos);
        vSize--;
        v = ArrayUtil.resize(v, vSize);
        System.out.println(ArrayUtil.printArray(v, vSize));

        System.out.println("Inserisci la posizione del valore da eliminare: ");
        delPos = console.nextInt();
        ArrayUtil.removeSorted(v, vSize, delPos);
        vSize--;
        v = ArrayUtil.resize(v, vSize);
        System.out.println(ArrayUtil.printArray(v, vSize));

        System.out.println("Inserisci un valore da cercare: ");
        int target = console.nextInt();
        System.out.println("Il valore " + target + " si trova in posizione" + ArrayUtil.find(v, vSize, target));

        System.out.println("Il valore minimo dell'array: " + ArrayUtil.findMin(v, vSize));
        System.out.println("Il valore massimo dell'array: " + ArrayUtil.findMax(v, vSize));
        console.close();
    }
}
