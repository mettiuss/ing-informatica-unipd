
public class PadlockTester {
    public static void main(String[] args) {
        final String PASSWD1 = "APRITI_SESAMO";
        final String PASSWD2 = "RIAPRITI_SESAMO";

        System.out.println("Creo un lucchetto con passoword " + PASSWD1);
        Padlock p = new Padlock(PASSWD1);
        System.out.println(p);

        System.out.println("Chiudo il lucchetto");
        p.close();
        System.out.println(p);

        System.out.println("Provo ad aprire con password " + PASSWD2);
        p.open(PASSWD2);
        System.out.println(p);

        System.out.println("Provo ad aprire con password " + PASSWD1);
        p.open(PASSWD1);
        System.out.println(p);

        System.out.println("Cambio password, settando " + PASSWD2);
        p.setPasswd(PASSWD1, PASSWD2);

        System.out.println("Provo ad aprire con password " + PASSWD2);
        p.open(PASSWD2);
        System.out.println(p);
    }
}