
//inizializza lunghezza muro
//inizializza lunghezza piastrella
//calcola lunghezza muro - lunghezza piastrella
//dividi lunghezza muto per (lunghezza piastrella * 2)
//prendi solo la parte intera, moltiplica per 2 e aggiungi 1
import java.util.Scanner;

public class CalcolaPiastrelle {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Insert the total with:");
        double totalWidth = console.nextDouble();
        System.out.println("Insert the tile with:");
        double tileWidth = console.nextDouble();
        System.out.println("Insert the pattern size:");
        int patternSize = console.nextInt();

        int tilesNumber = countTiles(totalWidth, tileWidth, patternSize);
        System.out.println("Numer of tiles: " + tilesNumber);

        double gap = computeGap(totalWidth, tileWidth, tilesNumber);
        System.out.println("Amount of gap: " + gap);
        console.close();
    }

    public static int countTiles(double totalWidth, double tileWidth, int patternSize) {
        patternSize--;
        int tiles = (int) ((totalWidth - tileWidth) / (tileWidth * patternSize));
        return (tiles * patternSize) + 1;
    }

    public static double computeGap(double totalWidth, double tileWidth, int tilesNumber) {
        return (totalWidth - (tileWidth * tilesNumber)) / 2;
    }

}
