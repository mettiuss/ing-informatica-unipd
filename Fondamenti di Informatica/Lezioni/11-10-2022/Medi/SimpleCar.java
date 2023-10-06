public class SimpleCar {
    public static final double INITIAL_SPEED = 10;
    public static final int MIN_GEAR = 0;
    public static final int MAX_GEAR = 6;
    public static final double SPEED_INCREMENT_PERCENT = 0.5;
    public static final double SPEED_DECREMENT_PERCENT = 0.3;

    private double speed;
    private int gear;

    public SimpleCar() {
        speed = 0;
        gear = 0;
    }

    public void brake() {
        if (speed < 5) {
            speed = 0;
        }
        speed -= speed * SPEED_DECREMENT_PERCENT;
    }

    public void speedUp() {
        if (speed == 0) {
            speed = 10;
        } else {
            speed += speed * SPEED_INCREMENT_PERCENT;
        }
    }

    public void gearDown() {
        gear--;
    }

    public void gearUp() {
        gear++;
    }

    public String toString() {
        return "SimpleCar: marcia = " + gear + ", velocita' = " + speed + " km/h";
    }
}