import java.util.Random;
import java.util.Arrays;

public class ArrayUtilTester {
    public static void main(String[] args) {
        Random r = new Random();
        Integer[] list = new Integer[10];

        for (int i = 0; i < list.length; i++) {
            list[i] = r.nextInt(10);
        }
        System.out.println(Arrays.toString(list));


        ArrayUtil.selectionSort(list);

        System.out.println(Arrays.toString(list));
    }
}