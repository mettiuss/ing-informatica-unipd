public class CountOnesMatrix {

    public static int countOnes(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException();

        int ones = 0;
        int row = matrix.length - 1;
        int column = 0;
        while (column < matrix.length && row >= 0) {
            if (matrix[row][column] == 0) {
                ones += column;
                row--;
            } else {
                column++;
            }
        }

        if (column == matrix.length) ones += column;
        return ones;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
            new int[] { 1, 1, 0 },
            new int[] { 0, 0, 0 },
            new int[] { 0, 0, 0 },
        };
        int number = countOnes(matrix);
        System.out.println(number);
    }
}
