public class TimeDepositAccount extends BankAccount {
    public TimeDepositAccount(double rate, double time) {
        super(); // costruttore della superclasse
        interestRate = rate;
        monthsLeft = time;
    }

    public TimeDepositAccount(double initialBalance, double rate, double time) {
        super(initialBalance); // costruttore della superclasse
        interestRate = rate;
        monthsLeft = time;
    }

    // accredita gli interessi al termine del mese. Attenzione: usa il metodo
    // deposit della superclasse, altrimenti verrebbe addebitata la penale FEE
    public void addInterest() // NUOVO METODO
    {
        monthsLeft--;
        if (monthsLeft < 1) {
            super.deposit(getBalance() * interestRate / 100);
        }
    }

    public void withdraw(double amount) // SOVRASCRITTO
    {
        super.withdraw(amount); // sottrai amount dal saldo
        if (monthsLeft > 0) {
            super.withdraw(TRANSACTION_FEE);
        }
    }

    // ------- metodi di accesso --------
    public double getInterestRate() {
        return interestRate;
    }

    public double getMonthsLeft() {
        return monthsLeft;
    }

    // ------ metodi di Object sovrascritti ---------
    // ........... toString, equals .................
    public String toString() {
        return "TimeDepositAccount[interestRate=" + interestRate + "monthsLeft=" + monthsLeft + "]" + super.toString();
    }

    public boolean equals(TimeDepositAccount other) {
        return super.equals(other) && getInterestRate() == other.getInterestRate()
                && monthsLeft == other.getMonthsLeft();
    }

    // -------- nuovi campi di esemplare ----------------

    private double interestRate;
    private double monthsLeft;
    public static final double TRANSACTION_FEE = 2.0;
}
