import java.util.Scanner;

public class IsLeapYear {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci un anno:");

        int year = console.nextInt();
        if (year < 0) {
            System.out.println("Inserisci un anno valido");
            return;
        }
        if (year < 1582) {
            if (year % 4 == 0)
                System.out.println("Anno bisestile");
            else
                System.out.println("Anno non bisestile");
        } else {
            if (year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0)
                System.out.println("Anno bisestile");
            else
                System.out.println("Anno non bisestile");
        }
        console.close();
    }
}