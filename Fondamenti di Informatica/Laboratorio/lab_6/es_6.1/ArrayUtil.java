public class ArrayUtil {
    public static double[] resize(double[] oldArray, int newLength) {
        int arrayLenght;
        if (newLength < oldArray.length) {
            arrayLenght = newLength;
        } else {
            arrayLenght = oldArray.length;
        }
        double[] newArray = new double[newLength];
        for (int i = 0; i < arrayLenght; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    public static int[] resize(int[] oldArray, int newLength) {
        int arrayLenght;
        if (newLength < oldArray.length) {
            arrayLenght = newLength;
        } else {
            arrayLenght = oldArray.length;
        }
        int[] newArray = new int[newLength];
        for (int i = 0; i < arrayLenght; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }

    public static void print(int[] array) {
        System.out.println();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    public static int[] randomIntArray(int length, int n) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * n);
        }
        return array;
    }

    public static String printArray(int[] v, int vSize) {
        String s = "";
        for (int i = 0; i < vSize; i++) {
            s += v[i] + " ";
        }
        return s;
    }

    public static void remove(int[] v, int vSize, int index) {
        v[index] = v[vSize - 1];
    }

    public static void removeSorted(int[] v, int vSize, int index) {
        for (int i = index; i < vSize - 1; i++) {
            v[i] = v[i + 1];
        }
    }

    public static void insert(int[] v, int vSize, int index, int value) {
        for (int i = vSize - 1; i > index; i--) {
            v[i] = v[i - 1];
        }
        v[index] = value;
    }

    public static int findMin(int[] v, int vSize) {
        int min = v[0];
        for (int i = 0; i < vSize; i++) {
            if (min > v[i]) {
                min = v[i];
            }
        }
        return min;
    }

    public static int findMax(int[] v, int vSize) {
        int max = v[0];
        for (int i = 0; i < vSize; i++) {
            if (max < v[i]) {
                max = v[i];
            }
        }
        return max;
    }

    public static int find(int[] v, int vSize, int target) {
        for (int i = 0; i < vSize; i++) {
            if (v[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
