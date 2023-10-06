public class ScambiaVariabili {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        a += b;
        b = a - b;
        a = a - b;
        System.out.println("A: " + a + "\nB: " + b);
    }
}
