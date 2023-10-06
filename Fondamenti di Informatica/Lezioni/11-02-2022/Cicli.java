public class Cicli {
    public static void main(String[] args) {
        String string = "ciao";
        String inverted = "";

        for (int i = string.length() - 1; i >= 0; i--) {
            inverted += string.charAt(i);
        }

        System.out.println(inverted);
    }

    public static void whileLoop() {
        final double INITIAL_BALANCE = 20000;
        final double rate = 5;
        int years = 0;
        double balance = INITIAL_BALANCE;

        while (balance < (INITIAL_BALANCE * 2)) {
            balance = balance + ((balance / 100) * rate);
            years++;
        }
        System.out.println("L'investimento raddoppia in " + years + " anni");
    }
}