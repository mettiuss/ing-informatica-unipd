import java.util.Scanner;

public class BankAccountTester {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        Scanner console = new Scanner(System.in);
        String command = "";

        do {
            if (command.equals("B")) {
                System.out.println("Saldo attuale: " + account.getBalance());
            } else if (command.startsWith("D")) {
                double amount = Double.parseDouble(command.substring(2, command.length()));
                if (account.deposit(amount)) {
                    System.out.println("Versamento effettuato: " + amount);
                } else {
                    System.out.println("Versamento non corretto");
                }
            }
            if (command.startsWith("W")) {
                double amount = Double.parseDouble(command.substring(2, command.length()));
                if (account.withdraw(amount)) {
                    System.out.println("Prelievo effettuato: " + amount);
                } else {
                    System.out.println("Prelievo non autorizzato");
                }
            }
            if (command.startsWith("A")) {
                double amount = Double.parseDouble(command.substring(2, command.length()));
                double interest = account.interest(amount);
                System.out.println("Interessi calcolati e accreditati: " + interest);
            }
            System.out.println("Comando? (Q, B, D, W, A)");
            command = console.nextLine().toUpperCase();
        } while (!command.equals("Q"));

        System.out.println("Arrivederci");

        console.close();
    }
}
