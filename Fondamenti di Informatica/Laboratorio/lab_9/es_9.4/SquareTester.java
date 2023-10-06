import java.util.Scanner;

public class SquareTester {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci posizione e dimensioni del primo quadrato");
        Square one = new Square(console.nextInt(), console.nextInt(), console.nextInt());
        System.out.println("Inserisci posizione e dimensioni del secondo quadrato");
        Square two = new Square(console.nextInt(), console.nextInt(), console.nextInt());

        if (one.getArea() > two.getArea()) {
            System.out.println(one);
            System.out.println(two);
        } else {
            System.out.println(two);
            System.out.println(one);
        }

        try {
            System.out.println("Inserisci le nuove dimensioni del primo quadrato");
            one.setSize(console.nextInt(), console.nextInt());

            System.out.println("Inserisci le nuove dimensioni del primo quadrato");
            two.setSize(console.nextInt(), console.nextInt());
        } catch (IllegalArgumentException e) {
            System.out.println("I due lati devono essere uguali");
            System.exit(1);
        }

        if (one.getArea() > two.getArea()) {
            System.out.println(one);
            System.out.println(two);
        } else {
            System.out.println(two);
            System.out.println(one);
        }

        console.close();
    }
}
