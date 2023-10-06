import java.util.Scanner;

public class SegnoZodiacale {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci la tua data di nascita:");

        String[] date = console.nextLine().split(" ");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);

        if (month < 1 || month > 12 || day < 1 || day > 31 || (month == 2 && day > 29)
                || ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)) {
            System.out.println("Data non valida!");
            return;
        }

        if ((month == 3 && day >= 21) || (month == 4 && day <= 20))
            System.out.println("Ariete");
        else if ((month == 4 && day >= 21) || (month == 5 && day <= 20))
            System.out.println("Toro");
        else if ((month == 5 && day >= 21) || (month == 6 && day <= 21))
            System.out.println("Gemelli");
        else if ((month == 6 && day >= 22) || (month == 7 && day <= 22))
            System.out.println("Cancro");
        else if ((month == 7 && day >= 23) || (month == 8 && day <= 22))
            System.out.println("Leone");
        else if ((month == 8 && day >= 23) || (month == 9 && day <= 22))
            System.out.println("Vergine");
        else if ((month == 9 && day >= 23) || (month == 10 && day <= 22))
            System.out.println("Bilancia");
        else if ((month == 10 && day >= 23) || (month == 11 && day <= 22))
            System.out.println("Scorpione");
        else if ((month == 11 && day >= 23) || (month == 12 && day <= 21))
            System.out.println("Sagittario");
        else if ((month == 12 && day >= 22) || (month == 1 && day <= 20))
            System.out.println("Capricorno");
        else if ((month == 1 && day >= 21) || (month == 2 && day <= 19))
            System.out.println("Aquario");
        else if ((month == 2 && day >= 20) || (month == 3 && day <= 20))
            System.out.println("Pesci");

        console.close();
    }
}
