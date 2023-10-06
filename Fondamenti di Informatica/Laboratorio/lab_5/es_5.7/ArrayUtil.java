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
}