import java.util.*;

public class StringTest {
    public static void main(String[] args) {
        String s = "uno due tre quattro";
        Scanner tok = new Scanner(s);
        String[] t = new String[4];
        for (int i = 0; i <= 4; i++) {
            String a = tok.next();
            System.out.println(a);
            t[i] = a;
            System.out.println(t[i]);
        }
        tok.close();
    }
}