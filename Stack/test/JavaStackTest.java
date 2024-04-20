import adt.StackADT;
import javastack.JavaStack;

/**
 * A JUnit test of my java util implementation of the StackADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaStackTest extends AbstractStackADTTest {
    @Override
    StackADT<String> createNewStack() {
        return new JavaStack<>();
    }
} // end JavaStackTest
