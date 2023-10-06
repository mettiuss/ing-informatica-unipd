public class CalcolaCilindro {
    public static void main(String[] args) {
        float raggio = 10.12f;
        float altezza = 10.87f;
        final double PI = 3.14159265358979;
        double area = PI * Math.pow(raggio, 2);
        double volume = area * altezza;
        System.out.println("***Il volume del cilindro di raggio " + raggio + " e altezza " + altezza + " e' pari a "
                + volume + "***");
    }
}
