import java.util.*;

public class SortedMapTester {

    public static void main(String[] args) {

        SortedArraySortedMap m = new SortedArraySortedMap();

        String s = "Una stringa di prova per testare la mappa con una stringa con duplicati";
        Scanner scan = new Scanner(s);

        while (scan.hasNext()) {
            String str = scan.next();
            System.out.println("Inserisco (" + str + "," + str.length() + ")");
            Object dup = m.put(str, str.length());
            if (dup != null) {
                System.out.println("Duplicato!");
            }
        }

        for (int i = 0; i < m.size(); i++) {
            System.out.print(m.v[i].getKey() + "|");
        }
        System.out.println();

        System.out.println(m.remove("la"));

        for (int i = 0; i < m.size(); i++) {
            System.out.print(m.v[i].getKey() + "|");
        }
        System.out.println();

        scan.close();

    }

}
