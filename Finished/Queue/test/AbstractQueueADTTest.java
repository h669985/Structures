import adt.EmptyQueueException;
import adt.QueueADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic JUnit test for testing an implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

abstract class AbstractQueueADTTest {

    private QueueADT<String> emptyQueue;
    private QueueADT<String> queueWithOneElement;
    private QueueADT<String> queueWithFiveElements;

    abstract QueueADT<String> createNewQueue();

    @BeforeEach
    void reset() {
        emptyQueue = createNewQueue();

        queueWithOneElement = createNewQueue();

        queueWithOneElement.enqueue("Birdwatching");

        queueWithFiveElements = createNewQueue();

        queueWithFiveElements.enqueue("Fishing");
        queueWithFiveElements.enqueue("Bowling");
        queueWithFiveElements.enqueue("Golfing");
        queueWithFiveElements.enqueue("Drawing");
        queueWithFiveElements.enqueue("Dancing");
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyQueue.getClass().getSimpleName() + "<String> emptyQueue");
        System.out.println("    " + queueWithOneElement.getClass().getSimpleName() + "<String> queueWithOneElement");
        System.out.println("    " + queueWithFiveElements.getClass().getSimpleName() + "<String> queueWithFiveElements");
    }

    /*
     * Stuff to test (enqueue, dequeue, getFront, isEmpty, clear):
     *
     * - A new queue should be empty
     * - A queue with one or more elements should not be empty
     * - Elements added to the queue should always be added as the back most element
     * - dequeue should throw EmptyQueueException if the queue is empty before operation
     * - dequeue should return the element it removed from a queue with one or more elements
     * - Elements taken from the queue should always be the front most element
     * - getFront on an empty queue should throw EmptyQueueException
     * - getFront should return the front most element on a queue that is not empty
     * - getFront should not change anything about the queue in any form
     * - If all elements in a queue have been dequeued the queue should be empty
     * - clear on an empty queue should leave you with an empty queue
     * - clear should remove all elements from a queue, leaving you with an empty queue
     */

    @Test
    void aNewQueueShouldBeEmptyTest() {
        assertTrue(emptyQueue.isEmpty());
    }

    @Test
    void aQueueWithOneOrMoreElementsShouldNotBeEmptyTest() {
        assertFalse(queueWithOneElement.isEmpty());

        assertFalse(queueWithFiveElements.isEmpty());
    }

    @Test
    void elementsAddedToAQueueShouldBeAddedAsTheBackMostElementTest() {
        assertEquals("Birdwatching", queueWithOneElement.getFront());
        queueWithOneElement.enqueue("Diving");
        assertEquals("Birdwatching", queueWithOneElement.getFront());
        queueWithOneElement.enqueue("Archery");
        assertEquals("Birdwatching", queueWithOneElement.getFront());
        queueWithOneElement.dequeue();
        assertEquals("Diving", queueWithOneElement.getFront());
        queueWithOneElement.dequeue();
        assertEquals("Archery", queueWithOneElement.getFront());
    }

    @Test
    void dequeueShouldThrowEmptyQueueExceptionOnAnEmptyQueueTest() {
        assertThrows(EmptyQueueException.class,()->{emptyQueue.dequeue();});

        queueWithOneElement.dequeue();
        assertThrows(EmptyQueueException.class,()->{queueWithOneElement.getFront();});
    }

    @Test
    void dequeueShouldReturnTheElementItRemovedFromAQueueWithOneOrMoreElementsTest() {
        assertEquals("Birdwatching", queueWithOneElement.dequeue());

        assertEquals("Fishing", queueWithFiveElements.dequeue());
    }

    @Test
    void elementsTakenFromAQueueShouldBeTakenAsTheFrontMostElementTest() {
        assertEquals("Fishing", queueWithFiveElements.getFront());
        assertEquals("Fishing", queueWithFiveElements.dequeue());
        assertEquals("Bowling", queueWithFiveElements.getFront());
        assertEquals("Bowling", queueWithFiveElements.dequeue());
        assertEquals("Golfing", queueWithFiveElements.getFront());
        assertEquals("Golfing", queueWithFiveElements.dequeue());
    }

    @Test
    void getFrontShouldThrowEmptyQueueExceptionOnAnEmptyQueueTest() {
        assertThrows(EmptyQueueException.class,()->{emptyQueue.getFront();});

        queueWithOneElement.dequeue();
        assertThrows(EmptyQueueException.class,()->{queueWithOneElement.getFront();});
    }

    @Test
    void getFrontShouldReturnTheFrontMostElementOnAQueueWithOneOrMoreElementsTest() {
        assertEquals("Birdwatching", queueWithOneElement.getFront());

        assertEquals("Fishing", queueWithFiveElements.getFront());
        queueWithFiveElements.dequeue();
        assertEquals("Bowling", queueWithFiveElements.getFront());
    }

    @Test
    void getFrontShouldNotChangeTheQueueItOperatesOnInAnyWayTest() {
        assertEquals("Birdwatching", queueWithOneElement.getFront());
        assertEquals("Birdwatching", queueWithOneElement.getFront());

        assertEquals("Fishing", queueWithFiveElements.getFront());
        assertEquals("Fishing", queueWithFiveElements.getFront());
        queueWithFiveElements.dequeue();
        assertEquals("Bowling", queueWithFiveElements.getFront());
        assertEquals("Bowling", queueWithFiveElements.getFront());
    }

    @Test
    void ifAllTheElementsInAQueueWithOneOrMoreElementsHaveBeenDequeuedTheQueueShouldBeEmptyTest() {
        queueWithOneElement.dequeue();
        assertTrue(queueWithOneElement.isEmpty());

        while(!queueWithFiveElements.isEmpty()) {
            queueWithFiveElements.dequeue();
        }
        assertTrue(queueWithFiveElements.isEmpty());
    }

    @Test
    void clearOnAnEmptyQueueShouldLeaveYouWithAnEmptyQueueTest() {
        emptyQueue.clear();
        assertTrue(emptyQueue.isEmpty());
    }

    @Test
    void clearOnAQueueWithOneOrMoreElementsShouldLeaveYouWithAnEmptyQueueTest() {
        queueWithOneElement.clear();
        assertTrue(queueWithOneElement.isEmpty());

        queueWithFiveElements.clear();
        assertTrue(queueWithFiveElements.isEmpty());
    }
} // end AbstractQueueADTTest
