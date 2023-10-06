import java.util.Random;

public class Player {
    private String name;
    private int score;
    private int[] dice;

    // costruttore: il punteggio iniziale e' 0 cosi' come
    // i valori dei tiri dei tre dadi
    public Player(String aName) {
        name = aName;
        score = 0;
        dice = new int[] { 0, 0, 0 };
    }

    // metodi di accesso
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    // aggiorna il punteggio incrementandolo di una unita'
    public void addPoint() {
        score++;
    }

    // resetta il punteggio
    public void resetScore() {
        score = 0;
    }

    // restituisce un riferimento a un nuovo array di interi in
    // cui i valori ottenuti nei tre lanci sono ordinati
    public int[] sortDice() {
        for (int j = 0; j < dice.length; j++) {
            for (int i = 0; i < dice.length - 1; i++) {
                if (dice[i] < dice[i + 1]) {
                    int t = dice[i];
                    dice[i] = dice[i + 1];
                    dice[i + 1] = t;
                }
            }
        }
        return dice;
    }

    // simula un turno di lancio di dadi attribuendo a ciascun
    // lancio un valore casuale tra 1 e 6
    public void turno() {
        Random r = new Random();
        for (int i = 0; i < dice.length; i++) {
            dice[i] = r.nextInt(6);
        }
    }

    // restituisce una stringa con il nome del giocatore e
    // i valori dei lanci dei dadi
    public String toString() {
        String s = name;
        for (int i = 0; i < dice.length; i++) {
            s += "  " + dice[i];
        }
        return s;
    }
}