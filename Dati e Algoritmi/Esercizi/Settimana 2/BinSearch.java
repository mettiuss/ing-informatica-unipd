public class BinSearch {

    public static boolean binSearch(int[] list, int target) {
        int start = 0;
        int end = list.length - 1;

        while (start <= end) {
            int mid = (end + start) / 2;
            if (list[mid] == target) return true;
            if (list[mid] > target) end = mid - 1;
            if (list[mid] < target) start = mid + 1;
        }

        return false;
    }

    public static void main(String[] args) {
        boolean result = binSearch(new int[] { 1, 3, 6, 7, 9, 10, 12, 16 }, 9);

        System.out.println(result);
    }
}
