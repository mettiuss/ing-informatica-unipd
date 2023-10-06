import java.util.*;
import java.io.*;

public class Formatter {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci il nome del file da cui leggere i dati");
        String in = console.nextLine();

        System.out.println("Inserisci il nome del file su cui scrivere i dati");
        String out = console.nextLine();

        String data = "";

        try (FileReader fileIn = new FileReader(in); Scanner streamIn = new Scanner(fileIn)) {
            streamIn.useDelimiter(" ");
            while (streamIn.hasNext()) {
                String word = streamIn.next();
                data += word.substring(0, 1).toUpperCase() + word.substring(1, word.length()).toLowerCase() + " ";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File di input non trovato");
        } catch (IOException e) {
            System.out.println("File di input non valido");
        }

        try (PrintWriter fileOut = new PrintWriter(out)) {
            fileOut.println(data);
        } catch (FileNotFoundException e) {
            System.out.println("File di output non trovato");
        }

        console.close();
    }
}
