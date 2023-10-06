public class Chronometer {
    private long startTime;
    private long elapsedTime;

    public Chronometer() {
        reset();
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        elapsedTime = System.currentTimeMillis() - startTime;
    }

    public void reset() {
        elapsedTime = 0;
        startTime = 0;
    }

    public boolean isRunning() {
        return startTime == 0;
    }

    public String getElapsedTime() {
        if (startTime == 0)
            return "0.0";

        return String.format("%.4f", elapsedTime * 1E-3) + " s";
    }

    public boolean isTimeReady() {
        return startTime == 0;
    }

    public String toString() {
        String state = "";
        if (startTime == 0) {
            state = "off";
        } else if (startTime != 0 && elapsedTime == 0) {
            state = "running";
        } else {
            state = "ready";
        }
        return "Chronometer " + state + " " + getElapsedTime();
    }
}