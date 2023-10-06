import java.util.Scanner;

public class TrisGame {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        MyTris game = new MyTris();
        char turn = 'x';
        while (!game.isWinning('x') && !game.isWinning('o')) {
            System.out.println(game.toString());
            System.out.println("È il turno del giocatore " + turn);
            int column = console.nextInt();
            int row = console.nextInt();
            if (game.getCharInPosition(row, column) == '.') {
                game.setCharInPosition(row, column, turn);
                if (turn == 'x')
                    turn = 'o';
                else if (turn == 'o')
                    turn = 'x';
            } else {
                System.out.println("Questa posizione è già occupata");
            }
        }
        if (game.isWinning('x'))
            System.out.println("Vittoria del giocatore x");
        if (game.isWinning('o'))
            System.out.println("Vittoria del giocatore o");
        console.close();
    }
}