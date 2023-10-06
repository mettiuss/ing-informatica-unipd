/**
 * Classe di prova della classe ChessRook
 * 
 * @author A. Luchetta
 * @version 20201021
 */
public class ChessRookTester {
    public static void main(String[] args) {
        final String INITIAL_CHESS_SQUARE = "d5";
        final String MOVE1 = "d7";
        final String MOVE2 = "b7";
        final String MOVE3 = "b5";
        final String MOVE4 = "d5";
        final String MOVE5 = "e6";

        ChessRook b1 = new ChessRook(INITIAL_CHESS_SQUARE);
        System.out.println(b1);

        b1.moveToSquare(MOVE1);
        System.out.println(b1);

        b1.moveToSquare(MOVE2);
        System.out.println(b1);

        b1.moveToSquare(MOVE3);
        System.out.println(b1);

        b1.moveToSquare(MOVE4);
        System.out.println(b1);

        b1.moveToSquare(MOVE5); // casa non raggiungibile
        System.out.println(b1);

        b1.moveToSquare("abc"); // casa non valida
        System.out.println(b1);

        b1.moveToSquare("p9"); // casa non valida
        System.out.println(b1);
    }
}

class ChessRook {
    public static char row;
    public static char column;

    public ChessRook(String initialSquare) {
        column = initialSquare.charAt(0);
        row = initialSquare.charAt(1);
    }

    public void moveToSquare(String toChessSquare) {
        char column = toChessSquare.charAt(0);
        char row = toChessSquare.charAt(1);
        if (column == this.column || row == this.row) {
            this.column = column;
            this.row = row;
        }
    }

    public String toString() {
        return "Torre in " + column + row;
    }
}