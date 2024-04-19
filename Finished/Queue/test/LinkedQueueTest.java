import adt.QueueADT;
import linkedqueue.LinkedQueue;

/**
 * A JUnit test for testing a linked queue implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class LinkedQueueTest extends AbstractQueueADTTest {

    @Override
    QueueADT<String> createNewQueue() {
        return new LinkedQueue<>();
    }
} // end LinkedQueueTest
