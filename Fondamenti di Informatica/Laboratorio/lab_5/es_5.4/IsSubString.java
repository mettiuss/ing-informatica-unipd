import java.util.Scanner;

public class IsSubString {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci la prima stringa:");
        String s1 = console.nextLine().toLowerCase();
        System.out.println("Inserisci la seconda stringa:");
        String s2 = console.nextLine().toLowerCase();

        boolean isSubString = false;

        for (int x = 0; x < s1.length(); x++) {
            for (int y = x; y < s1.length() + 1; y++) {
                if (s1.substring(x, y).equals(s2)) {
                    isSubString = true;
                }
            }
        }

        System.out.println(isSubString ? "s1 è sottostringa di s2" : "s1 non è sottostringa di s2");
        console.close();
    }
}