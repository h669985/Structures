import adt.DequeADT;
import javadeque.JavaDeque;

/**
 * A JUnit test for testing a java util deque implementation of the DequeADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaDequeTest extends AbstractDequeADTTest {
    @Override
    DequeADT<String> createDeque() {
        return new JavaDeque<>();
    }
} // end JavaDequeTest
