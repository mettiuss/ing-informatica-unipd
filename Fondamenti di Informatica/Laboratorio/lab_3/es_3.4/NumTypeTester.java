/*
Esempi di arrotondamenti e conversioni tra tipi numerici
*/

public class NumTypeTester {
    public static void main(String[] args) {

        System.out.println(
                "\n************ overflow in somme di int *********************");

        int verylargeint = 2100000000; // se verylargeint ha un valore vicino a
                                       // 2^31 (ovvero 2147500000) la somma
                                       // verylargeint+verylargeint produce un
                                       // risultato inaspettato. Perche`?.
        System.out.println("verylargeint: " + verylargeint);

        verylargeint = verylargeint + verylargeint;
        System.out.println("verylargeint + verylargeint: " + verylargeint);

        System.out.println(
                "\n************ conversione da double a int ********************");

        double smalldouble = 1.93;

        System.out.println("smalldouble: " + smalldouble);

        // int smallint = smalldouble; //cosi` non funziona (provare). Perche`?
        int smallint = (int) smalldouble; // cosi` si tronca la parte decimale
        System.out.println("smallint con cast: " + smallint);

        // smallint = Math.round(smalldouble); //cosi` non funziona (provare).
        // Perche`?
        smallint = (int) Math.round(smalldouble); // Cosi` si approssima all'int
                                                  // piu` vicino. Notare che
                                                  // Math.round applicato ad un
                                                  // numero double restituisce
                                                  // un numero in formato long
        System.out.println("smallint con Math.round e cast: " + smallint);

        System.out.println(
                "\n************ conversione da double a long ********************");

        double largedouble = 3e9;
        System.out.println("largedouble: " + largedouble);

        int largeint = (int) largedouble; // se largedouble e` piu` grande di
                                          // 2^31 (ovvero di 2.1475e9) il cast
                                          // produce un risultato inaspettato.
                                          // Perche`?.
        System.out.println("largeint con cast: " + largeint);

        long largelong = (long) largedouble; // in questo caso il cast funziona
                                             // (e tronca alla parte intera).
                                             // Perche`?
        System.out.println("largelong con cast: " + largelong);

        largelong = Math.round(largedouble); // non e` necessario effettuare
                                             // il cast a long. Perche`?
        System.out.println("largelong con cast: " + largelong);

        System.out.println(
                "\n*********** errori di arrotondamento in numeri double *********");

        double periodicdouble = 4.35; // 4.35 ha una rappresentazione periodica
                                      // in base 2 (provare per credere)
        System.out.println("periodicdouble: " + periodicdouble);
        // l'approssimazione introdotta dalla rappresentazione periodica diventa
        // evidente se moltiplico questo numero per cento volte
        System.out.print("100*periodicdouble: ");
        System.out.println(100 * periodicdouble);
        // ancora peggio: se converto in formato int il numero double 100*4.35
        // ottengo il numero intero 434 invece che 435!
        int periodicint = (int) (100 * periodicdouble);// ci vogliono le par.tonde
        System.out.println("(int) (100*periodicdouble): " + periodicint);

        System.out.println(
                "\n*********** operazioni tra double \"molto grandi\" **************");

        double verylargedouble1 = 2e16;
        double verylargedouble2 = verylargedouble1 + 1;
        double result = verylargedouble2 - verylargedouble1;
        System.out.println("verylargedouble1: " + verylargedouble1);
        System.out.println("verylargedouble2: " + verylargedouble2);
        System.out.println("verylargedouble2-verylargedouble1: " + result);
        // Provare con verylargedouble1 piu` grande di 1e16. In questo caso
        // succede che result vale 0 invece che 1. Come e` possibile??

        System.out.println(
                "\n*********** overflow con double \"molto molto grandi\" **********");

        double veryverylargedouble1 = 1e308;
        double veryverylargedouble2 = veryverylargedouble1 * 2;
        System.out.println("veryverylargedouble1: " + veryverylargedouble1);
        System.out.println("veryverylargedouble * 2: " + veryverylargedouble2);

        // Provare con veryverylargedouble1 = 1e308. Cosa succede e perche'?
        // Poi provare con veryverylargedouble1 = 2e308. Cosa succede e perche'?

        /*
         */
    }

}
