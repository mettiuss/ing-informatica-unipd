import java.awt.*;
import java.util.*;
import javax.swing.JComponent;

public class BlockComponent extends JComponent {

    public static void mondrian(Graphics2D g2, int x, int y, int w, int h) {

        final int MINw = 120; // larghezza minima di un blocco
        final int MINh = 120; // altezza minima di un blocco
        final int PROBABILITY = 10; // probabilita' che un blocco venga diviso 10%

        // creo una variabile Random
        Random rand = new Random();

        // se larghezza e altezza sono entrambe <= dei rispettivi
        // minimi mi trovo nel "caso base"
        if (w <= MINw && h <= MINh) {
            // 1) creo un rettangolo (classe Rectangle) box con le dimensioni attuali
            Rectangle box = new Rectangle(x, y, w, h);
            // 2) setto il colore con g2.setColor(Color.BLACK);
            g2.setColor(Color.BLACK);
            // 3) disegno il rettangolo con g2.draw(box);
            g2.draw(box);
            // 4) ripeto cambiando le cordinate in modo da creare un
            // bordo di 3 pixel disegnando rettangoli piu' piccoli
            box = new Rectangle(x + 1, y + 1, w - 2, h - 2);
            g2.setColor(Color.BLACK);
            g2.draw(box);
            box = new Rectangle(x + 2, y + 2, w - 4, h - 4);
            g2.setColor(Color.BLACK);
            g2.draw(box);
            // 5) creo un nuovo colore di tipo Color (es. completamente random con
            // componenti RGB da 1 a 255; random tra una scelta limitata
            // (es. rosso (Color.RED), giallo (Color.YELLOW), blu, bianco);
            // random in base alla posizione del rettangolo
            // (es. parte in alto a sx del "quadro" toni di rosso, parte in alto a dx del
            // "quadro" toni di blu, etc) oppure come vi pare.
            double color = rand.nextDouble();
            if (color < 0.25) {
                g2.setColor(Color.YELLOW);
            } else if (color < 0.50) {
                g2.setColor(Color.RED);
            } else if (color < 0.75) {
                g2.setColor(Color.BLUE);
            } else {
                g2.setColor(Color.WHITE);
            }
            // 6) riempio il rettangolo utilizzando il metodo g2.fill(...) e passando il
            // colore
            // scelto come argomento.
            g2.fill(box);
            return;
        }

        // se non ho un rettangolo sufficientemente piccolo
        // decido se dividerlo o no con probabilita' PROBABILITY
        // se non lo divido creo un rettangolo con bordo come prima
        // setto il colore di riempimento a bianco e coloro
        if (rand.nextInt(100) < PROBABILITY) {
            // 1) creo un rettangolo (classe Rectangle) box con le dimensioni attuali
            Rectangle box = new Rectangle(x, y, w, h);
            // 2) setto il colore con g2.setColor(Color.BLACK);
            g2.setColor(Color.BLACK);
            // 3) disegno il rettangolo con g2.draw(box);
            g2.draw(box);
            // 4) ripeto cambiando le cordinate in modo da creare un
            // bordo di 3 pixel disegnando rettangoli piu' piccoli
            box = new Rectangle(x + 1, y + 1, w - 2, h - 2);
            g2.setColor(Color.BLACK);
            g2.draw(box);
            box = new Rectangle(x + 2, y + 2, w - 4, h - 4);
            g2.setColor(Color.BLACK);
            g2.draw(box);
            // 5) creo un nuovo colore di tipo Color
            g2.setColor(Color.WHITE);
            // 6) riempio il rettangolo utilizzando il metodo g2.fill(...) e passando il
            // colore
            // scelto come argomento.
            g2.fill(box);
            return;
        }

        // se invece lo divido verifico se devo dividere solo
        // la largezza, solo l'altezza o entrambe
        // per ciascun caso calcolo le nuove coordinate
        // e dimensioni e invoco ricorsivamente il metodo per
        // ciascuno dei nuovi blocchi individuati
        // le nuove dimensioni sono calcolate individuando una
        // lunghezza a caso tra 1/3 e 2/3 della dimensione che devo
        // dividere e le coordinate sono determinate di conseguenza

        if (w <= MINw) {
            int ymid = rand.nextInt(h / 3) + h / 3;
            mondrian(g2, x, y, w, ymid);
            mondrian(g2, x, y + ymid, w, h - ymid);
        } else if (h <= MINh) {
            int xmid = rand.nextInt(w / 3) + w / 3;
            mondrian(g2, x, y, xmid, h);
            mondrian(g2, x + xmid, y, w - xmid, h);
        } else {
            int xmid = rand.nextInt(w / 3) + w / 3;
            int ymid = rand.nextInt(h / 3) + h / 3;

            mondrian(g2, x, y, xmid, ymid); // alto a sx
            mondrian(g2, x + xmid, y, w - xmid, ymid); // alto a dx
            mondrian(g2, x, y + ymid, xmid, h - ymid); // basso a sx
            mondrian(g2, x + xmid, y + ymid, w - xmid, h - ymid); // basso a dx

        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int x = 0;
        int y = 0;
        int width = 1024;
        int heigh = 768;
        // creo un rettangolo
        Rectangle box = new Rectangle(x, y, width, heigh);
        // disegno un rettangolo
        g2.draw(box);
        // divido a caso il rettangolo sia in larghezza che in altezza
        Random r = new Random();
        int xmid = r.nextInt(width) + 1;
        int ymid = r.nextInt(heigh) + 1;

        // invoco il metodo mondrian per ciscuno dei 4 blocchi
        mondrian(g2, x, heigh, xmid, ymid); // alto a sx
        mondrian(g2, xmid, heigh, width - xmid, ymid); // alto a dx
        mondrian(g2, x, ymid, xmid, heigh - ymid); // basso a sx
        mondrian(g2, xmid, ymid, width - xmid, heigh - ymid); // basso a dx
    }
}
