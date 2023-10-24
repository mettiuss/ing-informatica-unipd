public class MissingNumber {

    public static int findMissing(int[] arr) {
        if (arr == null) throw new IllegalArgumentException();

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int n = arr.length + 1;
        int n_sum = n * (n + 1) / 2; //Formula Gauss
        return (n_sum - sum);
    }

    public static void main(String[] args) {
        int missingNum = findMissing(new int[] { 1, 2, 3, 5 });
        System.out.println(missingNum);
    }
}
