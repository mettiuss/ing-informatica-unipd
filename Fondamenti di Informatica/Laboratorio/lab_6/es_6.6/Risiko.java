import java.util.Scanner;

public class Risiko {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci il nome del primo giocatore");
        Player p1 = new Player(console.nextLine());
        System.out.println("Inserisci il nome del secondo giocatore");
        Player p2 = new Player(console.nextLine());

        p1.turno();
        int[] dice1 = p1.sortDice();
        p2.turno();
        int[] dice2 = p2.sortDice();

        System.out.println("lanci ordinati");
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        int p1Win = 0;
        int p2Win = 0;

        for (int i = 0; i < dice1.length; i++) {
            if (dice1[i] > dice2[i]) {
                p1Win++;
            } else {
                p2Win++;
            }
        }

        if (p1Win > p2Win) {
            System.out.println(p1.getName() + " vince " + p1Win + " a " + p2Win);
        } else {
            System.out.println(p2.getName() + " vince " + p2Win + " a " + p1Win);
        }
        console.close();
    }
}