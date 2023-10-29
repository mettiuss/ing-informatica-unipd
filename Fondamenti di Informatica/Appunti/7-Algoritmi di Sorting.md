# Algoritmi di Sorting

```java
public class Sorting {
    public static void main(String[] args) {
        //test array
        double[] array = new double[] {8, 3, 5, 7, 2};

        insertionSort(array);

        //print array
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void insertionSort(double[] a) {
        // il ciclo inizia da 1 perché il primo
        // elemento non richiede attenzione
        for (int i = 1; i < a.length; i++) {
            // nuovo elemento da inserire
            double temp = a[i];
            // la variabile di ciclo j va definita
            // fuori dal ciclo perché il suo valore
            // finale viene usato in seguito
            int j; // analizzare con cura il significato di j
            // durante il ciclo e alla fine del ciclo
            // vengono spostati a destra di un posto
            // tutti gli elementi a sinistra di temp
            // che sono maggiori di temp, procedendo
            // da destra a sinistra
            for (j = i; j > 0 && temp < a[j - 1]; j--)
                a[j] = a[j - 1];
            // temp viene inserito in posizione j
            a[j] = temp;
        }
    }

    public static void mergeSort(double[] array) {
        if (array.length < 2) return;
        int split = array.length / 2;
        double[] left = new double[split];
        double[] right = new double[array.length - split];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, split, right, 0, right.length);
        mergeSort(right);
        mergeSort(left);
        merge(array, right, left);
    }

    public static void merge(double[] array, double[] right, double[] left) {
        int ia = 0, ir = 0, il = 0;
        while (ir < left.length && il < right.length) {
            if (left[ir] < right[il]) {
                array[ia++] = left[ir++];
            } else {
                array[ia++] = right[il++];
            }
        }
        while (ir < left.length) array[ia++] = left[ir++];
        while (il < right.length) array[ia++] = right[il++];
    }

    public static void selectionSort(double[] array) {
        for (int i = 0; i < array.length; i++) {

            //search the position of the min, without looking at the already sorted positions
            int minPos = i;
            for (int j = i; j < array.length; j++) {
                if (array[minPos] > array[j]) {
                    minPos = j;
                }
            }

            //swapping the two positions
            double t = array[i];
            array[i] = array[minPos];
            array[minPos] = t;
        }
    }
}
```
