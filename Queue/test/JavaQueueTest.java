import adt.QueueADT;
import javaqueue.JavaQueue;

/**
 * A JUnit test for testing a java util queue implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaQueueTest extends AbstractQueueADTTest {
    @Override
    QueueADT<String> createNewQueue() {
        return new JavaQueue<>();
    }
} // end JavaQueueTest
