public class MyFirstArray {
    public static void main(String[] args) {
        int[] array = new int[10];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 3;
        array[4] = 4;
        array[5] = 5;
        array[6] = 6;
        array[7] = 7;
        array[8] = 8;
        array[9] = 9;

        System.out.println("Lunghezza Array: " + array.length);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i]);
        }

        System.out.println();
        incrementAll(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

        int value = 2;
        System.out.println();
        System.out.println(value);
        incrementVar(value);
        System.out.println(value);
    }

    public static void incrementAll(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i]++;
        }
    }

    public static void incrementVar(int value) {
        value++;
    }
}