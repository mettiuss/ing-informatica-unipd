public class StampaNumero {
    public static void main(String[] args) {
        final int numero = 823;
        for (int i = 0; i < 3; i++) {
            System.out.print(((numero / (int) Math.pow(10, 2 - i)) % 10) + " ");
        }
        System.out.println();
    }
}
