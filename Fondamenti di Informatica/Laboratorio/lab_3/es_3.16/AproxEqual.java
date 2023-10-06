import java.util.Scanner;

public class AproxEqual {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Scrivi il primo numero");
        double one = console.nextDouble();
        System.out.println("Scrivi il secondo numero");
        double two = console.nextDouble();
        if (Math.abs(one - two) <= 1E-14 * Math.max(Math.abs(one), Math.abs(two))) {
            System.out.println("These numbers are close to equal");
        } else {
            System.out.println("These numbers are very different");
        }
        console.close();
    }
}