import java.util.*;

public class MultiMapTester {

    public static void main(String[] args) {

        ArrayMultiMap m = new ArrayMultiMap();
        Random rand = new Random(123); // fisso il seed per debugging

        String s = "Una stringa di prova per testare la mappa con una stringa con duplicati";
        Scanner scan = new Scanner(s);

        while (scan.hasNext()) {
            String str = scan.next();
            int value = rand.nextInt(100);
            System.out.println("Inserisco (" + str + "," + value + ")");
            m.insert(str, value);
        }

        System.out.println("Numero di elementi introdotti " + m.size());

        System.out.println("Cerco il valore associato alla parola \"prova\"");
        Object obj = m.find("prova");
        if (obj == null) {
            System.out.println("La parola cercata non esiste nella multimappa");
        } else {
            System.out.println("in valore associato e'" + (Integer) obj);
        }

        System.out.println("Rimuovo una associazione con chiave \"prova\"");
        m.remove("prova");

        System.out.println("Cerco il valore associato alla parola \"prova\"");
        obj = m.find("prova");
        if (obj == null) {
            System.out.println("La parola cercata non esiste nella multimappa");
        } else {
            System.out.println("la lunghezza e' " + (Integer) obj);
        }

        System.out.println("Cerco i valori associato alla parola \"con\"");
        Object[] values = m.findAll("con");
        if (values.length == 0) {
            System.out.println("La parola cercata non esiste nella multimappa");
        } else {
            for (int i = 0; i < values.length; i++) {
                System.out.println((Integer) values[i]);
            }
        }
        System.out.println("Rimuovo una associazione con chiave \"con\"");
        m.removeAll("con");
        System.out.println("Cerco i valori associato alla parola \"con\"");
        values = m.findAll("con");
        if (values.length == 0) {
            System.out.println("La parola cercata non esiste nella multimappa");
        } else {
            for (int i = 0; i < values.length; i++) {
                System.out.println((Integer) values[i]);
            }
        }

        System.out.println("Stampo le chiavi");
        Object[] k = m.keys();
        for (int i = 0; i < k.length; i++) {
            System.out.println((String) k[i]);
        }

        scan.close();

    }

}
