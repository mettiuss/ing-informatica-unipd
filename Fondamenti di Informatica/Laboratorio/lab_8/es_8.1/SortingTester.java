import java.util.*;

public class SortingTester {
    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);

        double[] array = new double[length];

        for (int i = 0; i < length; i++) {
            array[i] = length - i;
        }

        long initialTime;

        double[] selectionArray = new double[length];
        System.arraycopy(array, 0, selectionArray, 0, length);
        initialTime = System.currentTimeMillis();
        ArrayAlgs.selectionSort(selectionArray);
        long selectionTime = System.currentTimeMillis() - initialTime;

        double[] mergeArray = new double[length];
        System.arraycopy(array, 0, mergeArray, 0, length);
        initialTime = System.currentTimeMillis();
        ArrayAlgs.selectionSort(mergeArray);
        long mergeTime = System.currentTimeMillis() - initialTime;

        double[] insertionArray = new double[length];
        System.arraycopy(array, 0, insertionArray, 0, length);
        initialTime = System.currentTimeMillis();
        ArrayAlgs.selectionSort(insertionArray);
        long insertionTime = System.currentTimeMillis() - initialTime;

        if (length <= 20) {
            System.out.println(ArrayAlgs.toString(array));
            System.out.println(ArrayAlgs.toString(selectionArray));
            System.out.println(ArrayAlgs.toString(mergeArray));
            System.out.println(ArrayAlgs.toString(insertionArray));
        }

        /*
         * 10000 elementi:
         * Selection Sort: 50 ms
         * Merge Sort: 45 ms
         * Insertion Sort: 45 ms
         * 
         * 100000 elementi:
         * Selection Sort: 4514 ms
         * Merge Sort: 4502 ms
         * Insertion Sort: 3677 ms
         * 
         * 100000 elementi gia' ordinati:
         * Selection Sort: 1881 ms
         * Merge Sort: 1850 ms
         * Insertion Sort: 1863 ms
         * 
         * 100000 elementi ordine decrescente:
         * Selection Sort: 5201 ms
         * Merge Sort: 5321 ms
         * Insertion Sort: 3750 ms
         */

        System.out.println("Selection Sort: " + selectionTime + " ms");
        System.out.println("Merge Sort: " + mergeTime + " ms");
        System.out.println("Insertion Sort: " + insertionTime + " ms");
    }
}
