public class SavingsAccount extends BankAccount {
    public SavingsAccount(double rate) {
        super(); // costruttore della superclasse
        interestRate = rate;
    }

    public SavingsAccount(double initialBalance, double rate) {
        super(initialBalance); // costruttore della superclasse
        interestRate = rate;
    }

    // accredita gli interessi al termine del mese. Attenzione: usa il metodo
    // deposit della superclasse, altrimenti verrebbe addebitata la penale FEE
    public void addInterest() // NUOVO METODO
    {
        super.deposit(getBalance() * interestRate / 100);
    }

    // ------- metodi di accesso --------

    public double getInterestRate() {
        return interestRate;
    }

    // ------ metodi di Object sovrascritti ---------
    // ........... toString, equals .................
    public String toString() {
        return "SavingsAccount[interestRate=" + interestRate + "]" + super.toString();
    }

    public boolean equals(SavingsAccount other) {
        return super.equals(other) && getInterestRate() == other.getInterestRate();
    }

    // -------- nuovi campi di esemplare ----------------

    private double interestRate;
}
