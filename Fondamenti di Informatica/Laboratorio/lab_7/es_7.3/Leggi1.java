import java.util.*;
import java.io.*;

public class Leggi1 {
    public static void main(String[] args) {
        try (FileReader r = new FileReader("input.txt"); Scanner scan = new Scanner(r)) {

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Exception!");
        }
    }
}
