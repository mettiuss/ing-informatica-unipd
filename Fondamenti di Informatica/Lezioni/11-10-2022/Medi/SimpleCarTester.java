public class SimpleCarTester {
    public static void main(String[] args) {
        // nuovo esemplare di auto
        SimpleCar a = new SimpleCar();

        // aumenta marcia 0->1
        a.gearUp();

        // invio dati a standard output
        System.out.println(a);

        // accelerazione
        a.speedUp();

        // invio dati a standard output
        System.out.println(a);

        // aumenta marcia 1 -> 2
        a.gearUp();

        // accelerazione
        a.speedUp();

        // accelerazione
        a.speedUp();

        // invio dati a standard output
        System.out.println(a);

        // aumenta marcia 2 -> 3
        a.gearUp();

        // accelerazione
        a.speedUp();

        // accelerazione
        a.speedUp();

        // invio dati a standard output
        System.out.println(a);

        // aumenta marcia 3 -> 4
        a.gearUp();

        // aumenta marcia 4 -> 5
        a.gearUp();

        // accelerazione
        a.speedUp();

        // invio dati a standard output
        System.out.println(a);

        // aumenta marcia 5 -> 6
        a.gearUp();

        // accelera
        a.speedUp();

        // invio dati a standard output
        System.out.println(a);

        // scala marcia
        a.gearDown(); // 6 -> 5
        a.gearDown(); // 5 -> 4
        a.gearDown(); // 4 -> 3
        a.gearDown(); // 3 -> 2

        // frena
        a.brake();
        a.brake();
        a.brake();
        a.brake();
        System.out.println(a);

        // scala marcia
        a.gearDown(); // 2 -> 1

        // frean
        a.brake();
        a.brake();
        System.out.println(a);

        // frena
        a.brake();
        a.brake();
        a.brake();
        System.out.println(a);

        // frena
        a.brake();
        System.out.println(a);
    }
}