public class AccountTester {
    public static void main(String[] args) {
        // test metodi di BankAccount
        BankAccount a = new BankAccount();
        testAccount(a);

        // test metodi di SavingsAccount
        a = new SavingsAccount(.5);
        testAccount(a);

        // test metodi di CheckingAccount
        a = new CheckingAccount();
        testAccount(a);
    }

    // Metodo statico ausiliario per effettuare il collaudo con operazioni
    // diverse a seconda del tipo dell'oggetto a cui punta il riferimento a
    private static void testAccount(BankAccount a) {
        System.out.println("\n********** Collaudo di " + a.getClass().getName()
                + "**********");

        /*
         * Le seguenti operazioni di versamento e prelievo vengono effettuate
         * qualunque sia il tipo dell'oggetto. Pero` i risultati possono
         * cambiare di volta in volta, per il polimorfismo
         */
        a.deposit(1000);
        System.out.println("Stato dopo Versamento:\n     " + a);
        a.withdraw(500);
        System.out.println("Stato dopo Prelievo:\n     " + a);

        BankAccount newa = null;

        // Le seguenti operazioni possono essere eseguite solo se l'oggetto e`
        if (a instanceof SavingsAccount) // ... di tipo SavingsAccount
        {
            SavingsAccount sa = (SavingsAccount) a;
            sa.addInterest();
            System.out.println("Interessi fine mese:\n     " + a);
            newa = new SavingsAccount(sa.getBalance(), sa.getInterestRate());
        } else if (a instanceof CheckingAccount) // ... di tipo CheckingAccount
        {
            CheckingAccount ca = (CheckingAccount) a;
            for (int i = 0; i < 5; i++) // facciamo un po'
            {
                ca.deposit(100); // di operazioni
                ca.withdraw(100); // a saldo nullo
            }
            System.out.println("Effettuate " + ca.getTransactionCount() +
                    " operazioni, di cui " + ca.FREE_TRANSACTIONS +
                    " operazioni gratuite");
            ca.deductFees();
            System.out.println("Addebito operazioni:\n     " + a);
            newa = new CheckingAccount(ca.getBalance(), ca.getTransactionCount());
        } else // ... di tipo BankAccount
            newa = new BankAccount(a.getBalance());

        /*
         * Ora il riferimento newa punta ad un oggetto identico a quello
         * puntato da a, perche` lo abbiamo costruito inizializzandolo con lo
         * stato attuale di a
         */
        System.out.println();
        System.out.println("Stato attuale del conto a:\n     " + a);
        System.out.println("Stato del nuovo conto newa:\n     " + newa);
        System.out.println("Check di uguaglianza: " + a.equals(newa));

        // modifichiamo i due conti trasferendo soldi dall'uno all'altro
        a.transfer(100, newa);
        System.out.println("Stato del conto a dopo bonifico:\n     " + a);
        System.out.println("Stato del conto newa dopo bonifico:\n     " + newa);
        System.out.println("Check di uguaglianza: " + a.equals(newa));

    }
}
