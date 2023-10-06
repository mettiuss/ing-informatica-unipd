public class CalcolaInteressi {
    public static void main(String[] args) {

        // Creare una variabile oggetto di tipo BankAccount e la chiami account
        BankAccount account = new BankAccount();

        // Depositare 100 euro
        account.deposit(100);

        // Calcolare un interesse del 5%
        double interest = (account.getBalance() / 100) * 5;

        // Depositare gli interessi sul conto
        account.deposit(interest);

        // Stampare il valore attuale del saldo
        System.out.println("Bilancio: " + account.getBalance());
    }
}