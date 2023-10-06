public class Bonifico {
   public static void main(String[] args) {
      //Creare due conti bancari (account1 e account2).
      BankAccount account1 = new BankAccount();
      BankAccount account2 = new BankAccount();
      
      //Depositare in account1 1000 euro.
      account1.deposit(1000);
      
      //Depositare in account2 100 euro.
      account2.deposit(100);
      
      //Stampare a video il valore del saldo di ciascun conto.
      System.out.println("Bilancio account 1: " + account1.getBalance());
      System.out.println("Bilancio account 2: " + account2.getBalance());
      System.out.println();
      
      //Prelevare da account1 400 euro e depositare la stessa quantita' in account2.
      account1.withdraw(400);
      account2.deposit(400);  
       
      //Stampare a video il valore del saldo di ciascun conto.
      System.out.println("Bilancio account 1: " + account1.getBalance());
      System.out.println("Bilancio account 2: " + account2.getBalance());
   }
}
