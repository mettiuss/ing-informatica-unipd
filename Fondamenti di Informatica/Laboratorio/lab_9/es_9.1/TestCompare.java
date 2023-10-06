import java.util.Random;

public class TestCompare {
    public static void main(String[] args) {
        BankAccount[] list = new BankAccount[10];
        Random random = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = new BankAccount(random.nextInt(1000));
        }

        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i].getBalance() + " ");
        }
        System.out.println();

        ArrayAlgorithms.insertionSort(list);

        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i].getBalance() + " ");
        }
        System.out.println();
    }
}
