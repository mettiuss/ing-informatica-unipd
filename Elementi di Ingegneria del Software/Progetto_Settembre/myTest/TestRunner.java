package myTest;

import myAdapter.VectorAdapter;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>
 * <b>Summary</b> Punto di ingresso per tutte le test suite.
 * Per eseguire tutte le test suite test suite è possibile utilizzare il seguente comando:
 * {@code java -cp "bin:Matcher/*" org.junit.runner.JUnitCore myTest.TestRunner}
 * </p>
 * <p>
 * <b>Pre-Condition</b> Prima di eseguire la test suite il progetto deve essere compilato e i file
 * .jar delle sue dipendenze, ovvero junit (4.13.2) e hamcrest-core (1.3), devono trovarsi nella
 * cartella {@code Matcher}. <br>
 * Per l'esecuzione della test suite è stato utilizzato JAVA SE 18 (build 18.0.2.1+1-1).
 * </p>
 * <p>
 * <b>Post-Condition</b> Un report completo dei test eseguiti, superati e falliti viene generato
 * dopo l'esecuzione della test suite.
 * </p>
 * <b>Test Cases</b> Da controllare all'interno della documentazione delle singole test suite.
 * <p>
 * <b>Test Suite Execution Records</b> {@code 268} test eseguiti, {@code 268} hanno successo.
 * </p>
 * <p>
 * <b>Execution Variables</b> Nessuna variabile esterna è permessa per questa test suite, una
 * variabile di tipo {@link VectorAdapter} viene inizializzata, vuota, prima di ogni test.
 * </p>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
    value = {
        VectorAdapterTester.class,
        EnumerationAdapterTester.class,
        IteratorAdapterTester.class,
        SubListAdapterTester.class,
        ListIteratorAdapterTester.class,
        StackAdapterTester.class,
    }
)
public class TestRunner {

    /**
     * Costruttore di default
     */
    public TestRunner() {}

    /**
     * Main test suite entry point
     *
     * @param args variabile di default, dovrebbe essere vuota
     */
    public static void main(String[] args) {}
}
