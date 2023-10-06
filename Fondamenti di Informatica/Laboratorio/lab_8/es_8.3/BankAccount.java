public class BankAccount {
    private double balance = 0;

    public BankAccount(double saldo) { // esempio di costruttore
        balance = saldo; // definisce lo stato iniziale di balance
    }

    public BankAccount() {
        balance = 0;
    }

    public boolean deposit(double amount) {
        if (amount <= 0)
            return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount < 0 || amount > balance)
            return false;
        balance -= amount;
        return true;
    }

    public double interest(double amount) {
        if (amount < 0)
            return 0;
        double interest = balance * (amount / 100);
        balance += interest;
        return interest;
    }

    public double getBalance() {
        return balance;
    }
}
