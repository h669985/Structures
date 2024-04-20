import adt.QueueADT;
import circulararrayqueue.CircularArrayQueue;

/**
 * A JUnit test for testing a circular array queue implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class CircularArrayQueueTest extends AbstractQueueADTTest {
    @Override
    QueueADT<String> createNewQueue() {
        return new CircularArrayQueue<>();
    }
} // end CircularArrayQueueTest
