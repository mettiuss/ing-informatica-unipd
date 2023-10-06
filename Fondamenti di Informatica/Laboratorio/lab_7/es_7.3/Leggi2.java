import java.util.*;
import java.io.*;

public class Leggi2 {
    public static void main(String[] args) {
        try (FileReader r = new FileReader("input.txt"); Scanner scan = new Scanner(r)) {

            while (scan.hasNext()) {
                System.out.println(scan.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Exception!");
        }
    }
}
