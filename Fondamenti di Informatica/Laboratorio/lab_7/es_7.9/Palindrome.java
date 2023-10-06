import java.util.*;

public class Palindrome {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String word = "";
        System.out.println("Inserisci una parola");
        word = console.nextLine();

        System.out.println(recursiveReverse(word).equals(word));
        console.close();
    }

    private static String recursiveReverse(String n) {
        if (n.length() == 0)
            return "";
        return n.charAt(n.length() - 1) + recursiveReverse(n.substring(0, n.length() - 1));
    }
}
