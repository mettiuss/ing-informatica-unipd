public class QuadraticEquationTester {
    public static void main(String[] args) {
        QuadraticEquation equation = new QuadraticEquation(Double.parseDouble(args[0]), Double.parseDouble(args[1]),
                Double.parseDouble(args[2]));
        if (!equation.hasSolutions()) {
            System.out.println("Questa equazione non ha soluzioni reali");
            return;
        }
        System.out.println(
                "Le soluzioni dell'equzione sono: " + equation.getSolution1() + " e " + equation.getSolution2());
    }
}