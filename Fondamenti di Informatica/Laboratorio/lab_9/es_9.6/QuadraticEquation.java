public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    /*
     * Costruttore: riceve i coefficienti a, b, c dell'equazione quadratica
     * e inizializza i campi di esemplare secondo i valori ricevuti
     */
    public QuadraticEquation(double acoeff, double bcoeff, double ccoeff) {
        this.a = acoeff;
        this.b = bcoeff;
        this.c = ccoeff;
    }

    /*
     * Restituisce la prima delle due soluzioni dell'equazione quadratica,
     * usando la ben nota formula...
     */
    public double getSolution1() {
        return (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
    }

    /*
     * Restituisce la seconda delle due soluzioni dell'equazione quadratica,
     * usando la ben nota formula...
     */
    public double getSolution2() {
        return (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
    }

    /*
     * Restituisce il valore false se l'equazione non ha soluzioni
     * (ovvero se il discriminante e` negativo)
     */
    public boolean hasSolutions() {
        return (Math.pow(b, 2) - 4 * a * c) >= 0;
    }
}