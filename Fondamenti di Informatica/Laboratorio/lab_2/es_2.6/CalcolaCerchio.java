public class CalcolaCerchio {
    public static void main(String[] args) {
        float raggio = 10.12f;
        final double PI = 3.14159265358979;
        double circonferenza = raggio * PI;
        System.out.println(
                "***La circonferenza di un cerchio di raggio " + raggio + " e' pari a " + circonferenza + "***");
        double area = PI * Math.pow(raggio, 2);
        System.out.println("***L'area di un cerchio di raggio " + raggio + " e' pari a " + area + "***");
    }
}
