public class SimpleTriangle {
    private int a;
    private int b;
    private int c;

    public SimpleTriangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int perimeter() {
        return a + b + c;
    }

    public double area() {
        return Math.sqrt((a + b + c) * (-a + b + c) * (a - b + c) * (a + b - c)) / 4;
    }

    public String toString() {
        return "SimpleTriangle " + a + " " + b + " " + c;
    }
}