public class BankAccount implements Comparable {
    public BankAccount() {
        balance = 0;
    }

    public BankAccount(double initialBalance) {
        deposit(initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException();
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        if (amount > balance || amount <= 0)
            throw new IllegalArgumentException();
        balance = balance - amount;
    }

    public void transfer(double amount, BankAccount other) {
        withdraw(amount);
        other.deposit(amount);
    }

    // ------- comparable --------
    public int compareTo(BankAccount other) {
        if (getBalance() < other.getBalance())
            return -1;
        else if (getBalance() > other.getBalance())
            return 1;
        else
            return 0;
    }

    // ------- metodi di accesso --------

    public double getBalance() {
        return balance;
    }

    // ------ metodi di Object da sovrascrivere ---------
    // ......... toString e equals ......................
    public String toString() {
        return "BankAccount[balance=" + getBalance() + "]";
    }

    public boolean equals(BankAccount other) {
        return getBalance() == other.getBalance();
    }

    // -------- campi di esemplare ---------

    private double balance;
}
