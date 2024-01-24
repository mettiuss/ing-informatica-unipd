import java.util.Scanner;

public class isPalindrome {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("inserire una riga: ");
        String word = console.next();
        boolean palidrome = true;

        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i))
                palidrome = false;
        }

        System.out.println(
                palidrome ? "La stringa " + word + " e' palindroma" : "La stringa " + word + " non e' palindroma");
        console.close();
    }
}