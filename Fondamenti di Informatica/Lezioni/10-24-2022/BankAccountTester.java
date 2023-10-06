public class BankAccountTester {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(10);
        account.deposit(100);
        account.withdraw(200);
        System.out.println(account.getBalance());

        System.out.println("test".compareTo("td6t"));
    }
}