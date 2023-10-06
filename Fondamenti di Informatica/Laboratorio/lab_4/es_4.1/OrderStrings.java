import java.util.Scanner;

public class OrderStrings {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci 3 stringhe");

        String s1 = console.nextLine();
        String s2 = console.nextLine();
        String s3 = console.nextLine();

        if (s1.compareTo(s2) < 0 && s1.compareTo(s3) < 0) {
            if (s2.compareTo(s3) < 0)
                System.out.println(s1 + "\n" + s2 + "\n" + s3);
            else
                System.out.println(s1 + "\n" + s3 + "\n" + s2);
        } else if (s2.compareTo(s1) < 0 && s2.compareTo(s3) < 0) {
            if (s1.compareTo(s3) < 0)
                System.out.println(s2 + "\n" + s1 + "\n" + s3);
            else
                System.out.println(s2 + "\n" + s3 + "\n" + s1);
        } else {
            if (s2.compareTo(s1) < 0)
                System.out.println(s3 + "\n" + s2 + "\n" + s1);
            else
                System.out.println(s3 + "\n" + s1 + "\n" + s2);
        }

        console.close();
    }
}