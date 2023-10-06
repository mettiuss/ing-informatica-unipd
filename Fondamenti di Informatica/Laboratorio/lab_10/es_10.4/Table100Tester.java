import java.io.*;
import java.util.Scanner;

public class Table100Tester {
    public static void main(String[] arg) {
        ArrayTable100 tab = new ArrayTable100();

        System.out.println("Controllo se e' vuota: ");
        if (!tab.isEmpty()) {
            System.out.println("Errore!");
            System.exit(1);
        }

        System.out.println("Inserisco i numeri corrispondenti alle decine associando la loro meta'");
        for (int i = 1; i < 10; i++) {
            tab.insert(i * 10, i * 10 / 2);
        }

        System.out.println("Provo a inserire un'associazione con chiave -1");
        try {
            tab.insert(-1, 10);
        } catch (InvalidPositionTableException e) {
            System.out.println("E' stata utilizzata una chiave invalida");
        }

        System.out.println("Cerco il valore associato a 50");
        Object obj = tab.find(50);
        if (obj != null) {
            System.out.println("Il valore associato a 50 e' " + (Integer) obj);
        } else {
            System.out.println("Non esistono associazioni con chiave 50");
        }
        System.out.println("Rimuovo l'associazione con chiave 50");
        tab.remove(50);
        System.out.println("Cerco il valore associato a 50");
        obj = tab.find(50);
        if (obj != null) {
            System.out.println("Il valore associato a 50 e' " + (Integer) obj);
        } else {
            System.out.println("Non esistono associazioni con chiave 50");
        }

    }
}