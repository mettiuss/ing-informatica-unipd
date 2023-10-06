import java.util.Scanner;

public class StampaAggettivi {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci un aggettivo qualificativo");
        String input = console.nextLine();
        input = input.substring(0, 1).toUpperCase() + input.substring(1, input.length());
        String finalLetter = input.substring(input.length() - 1, input.length());
        String firstSection = input.substring(0, input.length() - 1);

        System.out.println("Aggettivo Inserito: " + input);
        System.out.println("Forma diminutiva: " + firstSection + "in" + finalLetter);
        System.out.println("Superlativo assoluto: " + firstSection + "issim" + finalLetter);
        console.close();
    }
}
