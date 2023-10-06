public class MyRectangleTester {
    public static void main(String[] args) {
        MyRectangle rectangle = new MyRectangle(10, 20, 30, 40);
        System.out.println(rectangle);
        rectangle.resize(2);
        System.out.println(rectangle);
        rectangle.translate(20, 20);
        System.out.println(rectangle);
    }
}
