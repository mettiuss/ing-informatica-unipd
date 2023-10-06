public class Interruttore {
    private boolean stato;

    // Costruttore: inizializza l'interruttore in stato "down"
    // Assumiamo "down" corrisponda a false e "up" a true
    public Interruttore() {
        stato = false;
    }

    // Metodo di accesso della variabile di esemplare interruttore
    public boolean getStatusInterruttore() {
        return stato;
    }

    // Metodo di accesso alla variabile statica lampadina
    public boolean isBulbOn() {
        return stato;
    }

    // Modificatore: cambia lo stato dell'interruttore
    // (e della lampadina!)
    public void changeStatus() {
        if (stato) {
            stato = false;
        } else {
            stato = true;
        }
    }

    // Stampa lo stato dell'interruttore: up/down a seconda
    // che status sia true o false
    public String printStatus() {
        if (stato) {
            return "up";
        } else {
            return "down";
        }
    }
}
