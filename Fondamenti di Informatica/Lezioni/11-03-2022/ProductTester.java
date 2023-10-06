import java.util.Scanner;

public class ProductTester {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Introdurre il nome del prodotto");
        String name1 = console.nextLine();
        System.out.println("Introdurre il prezzo del prodotto");
        double price1 = Double.parseDouble(console.nextLine());

        System.out.println("Introdurre il nome del prodotto");
        String name2 = console.nextLine();
        System.out.println("Introdurre il prezzo del prodotto");
        double price2 = Double.parseDouble(console.nextLine());

        Product product1 = new Product(name1, price1);
        Product product2 = new Product(name2, price2);

        if (product1.getPrice() > product2.getPrice()) {
            System.out.println(product1.getName() + " " + product1.getPrice());
            System.out.println(product2.getName() + " " + product2.getPrice());
        } else {
            System.out.println(product2.getName() + " " + product2.getPrice());
            System.out.println(product1.getName() + " " + product1.getPrice());
        }

        System.out.println("Inserisci lo sconto da applicare al prodotto più caro");
        double rate = console.nextDouble();
        if (product1.getPrice() > product2.getPrice()) {
            product1.reducePrice(rate);
        } else {
            product2.reducePrice(rate);
        }

        if (product1.getPrice() > product2.getPrice()) {
            System.out.println(product1.getName() + " " + product1.getPrice());
            System.out.println(product2.getName() + " " + product2.getPrice());
        } else {
            System.out.println(product2.getName() + " " + product2.getPrice());
            System.out.println(product1.getName() + " " + product1.getPrice());
        }

        console.close();
    }
}