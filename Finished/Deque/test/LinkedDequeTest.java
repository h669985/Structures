import adt.DequeADT;
import linkeddeque.LinkedDeque;

/**
 * A JUnit test for testing a linked deque implementation of the DequeADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class LinkedDequeTest extends AbstractDequeADTTest {
    @Override
    DequeADT<String> createDeque() {
        return new LinkedDeque<>();
    }
} // end LinkedDequeTest
