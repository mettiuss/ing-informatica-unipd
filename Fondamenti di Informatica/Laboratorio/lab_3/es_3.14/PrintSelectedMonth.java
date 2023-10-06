import java.util.Scanner;

public class PrintSelectedMonth {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Inserisci il numero del mese (1-12):");
        int month = console.nextInt();

        String MONTHS = "gennaio   febbraio  marzo     aprile    maggio    giugno    luglio    agosto    settembre ottobre   novembre  dicembre  ";
        System.out.println(MONTHS.substring(10 * (month - 1), 10 * month));
        console.close();
    }
}
