import adt.StackADT;
import linkedstack.LinkedStack;

/**
 * A JUnit test of my linked node structure implementation of the StackADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class LinkedStackTest extends AbstractStackADTTest {
    @Override
    StackADT<String> createNewStack() {
        return new LinkedStack<>();
    }
} // end LinkedStackTest
