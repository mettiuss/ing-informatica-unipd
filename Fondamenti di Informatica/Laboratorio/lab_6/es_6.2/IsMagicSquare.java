import java.util.Scanner;

public class IsMagicSquare {

    public static int find(int[] v, int target) {
        int j = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] == target) {
                j++;
            }
        }
        return j;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // Ottiene i dati dall'utente
        int length = 0;
        int[] numberList = new int[0];

        System.out.println("Inserisci i numeri che formano il quadrato: ");
        while (console.hasNextInt()) {
            length++;
            numberList = ArrayUtil.resize(numberList, length);
            numberList[length - 1] = console.nextInt();
        }

        // verifica che il numero di valori introdotti sia il quadrato di un numero
        // intero n: in caso contrario, il programma termina segnalando un fallimento;

        if (Math.sqrt(numberList.length) - (int) Math.sqrt(numberList.length) != 0) {
            System.out.println("Le cifre inserite non sono il quadrato di un numero intero.");
            return;
        }

        int side = (int) Math.sqrt(numberList.length);

        // verifica che la sequenza di valori introdotta contenga tutti (e soli) i
        // numeri da 1 a n^2, senza ripetizioni: in caso contrario, il programma termina
        // segnalando un fallimento (pensate bene a come implementare questo controllo,
        // si puo' fare, anzi si deve fare, solo con quanto visto finora);

        for (int i = 0; i < numberList.length; i++) {
            if (numberList[i] > Math.pow(side, 2)) {
                System.out.println("Una cifra inserita supera n^2.");
                return;
            }
        }

        for (int i = 0; i < numberList.length; i++) {
            if (find(numberList, numberList[i]) > 1) {
                System.out.println("Una cifra e' duplicata.");
                return;
            }
        }

        // dispone i valori all'interno di un array bidimensionale, riempito per righe
        // seguendo l'ordine con cui sono stati forniti i valori: il primo valore prende
        // posto nell'angolo in alto a sinistra, il secondo nella seconda posizione da
        // sinistra della prima riga e così via, riempiendo righe successive del
        // quadrato;
        int[][] square = new int[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                square[i][j] = numberList[i * side + j];
            }
        }

        System.out.println("Array inserito:");
        for (int i = 0; i < square.length; i++) {
            System.out.println(ArrayUtil.printArray(square[i], square.length));
        }

        // verifica la validità delle regole del quadrato magico, interrompendo la
        // verifica con la segnalazione di fallimento non appena una regola non sia
        // rispettata;

        // Ottengo prima il numero magico
        int magic = 0;
        for (int i = 0; i < side; i++) {
            magic += square[0][i];
        }

        // verifica righe
        for (int i = 0; i < side; i++) {
            int row = 0;
            for (int j = 0; j < side; j++) {
                row += square[i][j];
            }
            if (row != magic) {
                System.out.println("Quadrato non magico");
                return;
            }
        }

        // verifica colonne
        for (int i = 0; i < side; i++) {
            int column = 0;
            for (int j = 0; j < side; j++) {
                column += square[j][i];
            }
            if (column != magic) {
                System.out.println("Quadrato non magico");
                return;
            }
        }

        // verifica diagonali
        int diagonal = 0;
        for (int i = 0; i < side; i++) {
            diagonal += square[i][i];
        }
        if (diagonal != magic) {
            System.out.println("Quadrato non magico");
            return;
        }

        diagonal = 0;
        for (int i = 0; i < side; i++) {
            diagonal += square[i][side - 1 - i];
        }
        if (diagonal != magic) {
            System.out.println("Quadrato non magico");
            return;
        }

        System.out.println("Il Quadrato e' magico!");
        console.close();
    }
}
