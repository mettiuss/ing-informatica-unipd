public class ArrayAlgs {
    public static void selectionSort(double[] array) {
        for (int i = 0; i < array.length; i++) {
            int minPos = i;
            for (int j = i; j < array.length; j++) {
                if (array[minPos] > array[j])
                    minPos = j;
            }
            swap(array, minPos, i);
        }
    }

    // metodo ausiliario selectionSort
    private static void swap(double[] array, int initialPos, int finalPos) {
        double t = array[finalPos];
        array[finalPos] = array[initialPos];
        array[initialPos] = t;
    }

    public static void mergeSort(double[] array) {
        if (array.length < 2)
            return; // base case

        int split = array.length / 2;
        double[] left = new double[split];
        double[] right = new double[array.length - split];
        System.arraycopy(array, 0, left, 0, split);
        System.arraycopy(array, split, right, 0, array.length - split);

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    // metodo ausiliario mergeSort
    private static void merge(double[] array, double[] left, double[] right) {
        int iarray = 0, ileft = 0, iright = 0;
        while (ileft < left.length && iright < right.length) {
            if (left[ileft] < right[iright]) {
                array[iarray++] = left[ileft++];
            } else {
                array[iarray++] = right[iright++];
            }
        }
        while (ileft < left.length) {
            array[iarray++] = left[ileft++];
        }
        while (iright < right.length) {
            array[iarray++] = right[iright++];
        }
    }

    public static void insertionSort(double[] array) {
        for (int i = 1; i < array.length; i++) {
            int minPos = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] < array[minPos])
                    break;
                swap(array, j, minPos);
                minPos--;
            }
        }
    }

    public static String toString(double[] array) {
        String arrayData = "";
        for (int i = 0; i < array.length; i++) {
            arrayData += array[i] + " ";
        }
        return arrayData;
    }
}
