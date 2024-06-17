package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test suite per l'intero adattatore ListAdapter
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = { ListIteratorAdapterTester.class, ListAdapterTester.class, IteratorAdapterTester.class,
        SubListAdapterTester.class })
public class TestRunner {
    /**
     * Costruttore di default
     */
    public TestRunner() {
    }
    // Classe vuota
}
