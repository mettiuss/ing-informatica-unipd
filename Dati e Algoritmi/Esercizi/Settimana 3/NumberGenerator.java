public class NumberGenerator {

    public static int generate(int[] arr) {
        if (arr == null) throw new IllegalArgumentException();

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Math.abs(arr[i]);
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        int number = generate(new int[] { 1, 5, 6, 7 });
        System.out.println(number);
    }
}
