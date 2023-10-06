public class SimpleTriangleTester {
    public static void main(String[] args) {
        // generazione dei triangoli
        SimpleTriangle t1 = new SimpleTriangle(3, 4, 5);
        SimpleTriangle t2 = new SimpleTriangle(5, 12, 13);
        SimpleTriangle t3 = new SimpleTriangle(7, 24, 25);

        // Calcolo stringhe
        String s1 = t1 + " perimetro " + t1.perimeter() + " area " + t1.area();
        String s2 = t2 + " perimetro " + t2.perimeter() + " area " + t2.area();
        String s3 = t3 + " perimetro " + t3.perimeter() + " area " + t3.area();

        // invio messaggi a standard output
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}