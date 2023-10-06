public class PowerTable {
    public static void main(String[] args) {
        final int LINE_LENGTH = 4;
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 5; y++) {
                String pow = "" + (int) Math.round(Math.pow(x, y));
                while (pow.length() < LINE_LENGTH) {
                    pow = " " + pow;
                }
                System.out.print(pow + " ");
            }
            System.out.println();
        }
    }
}