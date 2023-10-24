public class FindSum {

    public static boolean findSum(int[] arr, int z) {
        if (arr == null || arr.length < 2) return false;

        int min = 0;
        int max = arr.length - 1;

        if (arr[max] * 2 < z) return false;
        if (arr[min] * 2 > z) return false;

        while (max > min) {
            int sum = arr[max] + arr[min];
            if (sum == z) return true;
            if (sum < z) min++; else max--;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean found = findSum(new int[] { 1, 5, 6, 7 }, 60);
        System.out.println(found);
    }
}
