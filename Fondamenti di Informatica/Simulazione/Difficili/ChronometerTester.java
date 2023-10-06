/**
 * Classe di prova della classe Chronometer
 * 
 * @author A. Luchetta
 * @version 20201027
 * 
 */
public class ChronometerTester {
    public static void main(String[] args) {
        final int TIME_SLICE = 10000000;

        Chronometer s = new Chronometer();
        System.out.println(s);

        // misura 1
        s.start();
        for (int i = 0; i < TIME_SLICE; i++)
            Math.sqrt(2 + i);
        s.stop();
        String s1 = s.toString();
        s.reset();

        // misura 2
        s.start();
        for (int i = 0; i < TIME_SLICE; i++)
            Math.sin(2 + i);
        s.stop();
        String s2 = s.toString();
        s.reset();

        // misura 3
        s.start();
        for (int i = 0; i < TIME_SLICE; i++)
            Math.cos(2 + i);
        s.stop();
        String s3 = s.toString();
        s.reset();

        // misura 4
        s.start();
        for (int i = 0; i < TIME_SLICE; i++)
            Math.cos(2 + i);
        String s4 = s.toString();
        // s.stop() non fermo il cronometro
        s.reset();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }
}