import adt.DequeADT;
import adt.EmptyQueueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic JUnit test for testing an implementation of the DequeADT interface
 *
 * @author Jonas Hellevik
 * @version 3.0
 */

abstract class AbstractDequeADTTest {

    private DequeADT<String> emptyDeque;
    private DequeADT<String> dequeWithOneFrontElement;
    private DequeADT<String> dequeWithOneBackElement;
    private DequeADT<String> dequeWithFourElements;

    abstract DequeADT<String> createDeque();

    @BeforeEach
    void reset() {
        emptyDeque = createDeque();

        dequeWithOneFrontElement = createDeque();
        dequeWithOneFrontElement.addToFront("Derivative");

        dequeWithOneBackElement = createDeque();
        dequeWithOneBackElement.addToBack("Integral");

        dequeWithFourElements = createDeque();
        dequeWithFourElements.addToFront("Multiply");
        dequeWithFourElements.addToBack("Divide");
        dequeWithFourElements.addToFront("Addition");
        dequeWithFourElements.addToBack("Subtraction");
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyDeque.getClass().getSimpleName() + "<String> emptyDeque");
        System.out.println("    " + dequeWithOneFrontElement.getClass().getSimpleName() + "<String> dequeWithOneFrontElement");
        System.out.println("    " + dequeWithOneBackElement.getClass().getSimpleName() + "<String> dequeWithOneBackElement");
        System.out.println("    " + dequeWithFourElements.getClass().getSimpleName() + "<String> dequeWithFourElements");
    }

    /*
     * Stuff to test (addToFront, addToBack, removeFront, removeBack, getFront, getBack, isEmpty. clear):
     *
     * - A new deque should be empty
     * - A deque with one or more elements should not be empty
     * - addToFront should always add the element to the deque as the front most element
     * - addToBack should always add the element to the deque as the back most element
     * - removeFront/removeBack should throw EmptyQueueException if the deque is empty before operation
     * - removeFront/removeBack should return the element it removed from a deque with one or more elements
     * - removeFront should always remove the front most element in the deque
     * - removeBack should always remove the back most element in the deque
     * - getFront/getBack on an empty deque should throw EmptyQueueException
     * - getFront should return the front most element on a deque that is not empty
     * - getBack should return the back most element on a deque that is not empty
     * - getFront/getBack should not change anything about the deque in any form
     * - If all elements in a deque have been removed the deque should be empty
     * - clear on an empty deque should leave you with an empty deque
     * - clear should remove all elements from a deque, leaving you with an empty deque
     * - operating on the deque that changes the content of it should not change the order of the elements in the deque
     */

    @Test
    void aNewDequeShouldBeEmptyTest() {
        assertTrue(emptyDeque.isEmpty());
    }

    @Test
    void aDequeWithOneOrMoreElementsShouldNotBeEmptyTest() {
        assertFalse(dequeWithOneFrontElement.isEmpty());

        assertFalse(dequeWithOneBackElement.isEmpty());

        assertFalse(dequeWithFourElements.isEmpty());
    }

    @Test
    void addToFrontShouldAlwaysAddTheElementToTheFrontOfTheDequeTest() {
        assertEquals("Derivative", dequeWithOneFrontElement.getFront());
        assertEquals("Addition", dequeWithFourElements.getFront());
    }

    @Test
    void addToBackShouldAlwaysAddTheElementToTheBackOfTheDequeTest() {
        assertEquals("Integral", dequeWithOneBackElement.getBack());
        assertEquals("Subtraction", dequeWithFourElements.getBack());
    }

    @Test
    void removeFrontShouldThrowEmptyQueueExceptionOnAnEmptyDequeTest() {
        assertThrows(EmptyQueueException.class, ()->{emptyDeque.removeFront();});
    }

    @Test
    void removeBackShouldThrowEmptyQueueExceptionOnAnEmptyDequeTest() {
        assertThrows(EmptyQueueException.class, ()->{emptyDeque.removeBack();});
    }

    @Test
    void removeFrontShouldReturnTheElementItRemovedFromANonEmptyDequeTest() {
        assertEquals("Derivative", dequeWithOneFrontElement.removeFront());
    }

    @Test
    void removeBackShouldReturnTheElementItRemovedFromANonEmptyDequeTest() {
        assertEquals("Integral", dequeWithOneBackElement.removeBack());
    }

    @Test
    void removeFrontShouldAlwaysRemoveTheFrontMostElementInANonEmptyDequeTest() {
        assertEquals("Addition", dequeWithFourElements.removeFront());
        assertEquals("Multiply", dequeWithFourElements.removeFront());
        assertEquals("Divide", dequeWithFourElements.removeFront());
        assertEquals("Subtraction", dequeWithFourElements.removeFront());
    }

    @Test
    void removeBackShouldAlwaysRemoveTheBackMostElementInANonEmptyDequeTest() {
        assertEquals("Subtraction", dequeWithFourElements.removeBack());
        assertEquals("Divide", dequeWithFourElements.removeBack());
        assertEquals("Multiply", dequeWithFourElements.removeBack());
        assertEquals("Addition", dequeWithFourElements.removeBack());
    }

    @Test
    void getFrontShouldThrowEmptyQueueExceptionOnAnEmptyDequeTest() {
        assertThrows(EmptyQueueException.class, ()->{emptyDeque.getFront();});
    }

    @Test
    void getBackShouldThrowEmptyQueueExceptionOnAnEmptyDequeTest() {
        assertThrows(EmptyQueueException.class, ()->{emptyDeque.getBack();});
    }

    @Test
    void getFrontShouldReturnTheFrontMostElementOnANonEmptyDequeTest() {
        assertEquals("Derivative", dequeWithOneFrontElement.getFront());
        assertEquals("Integral", dequeWithOneBackElement.getFront());
        assertEquals("Addition", dequeWithFourElements.getFront());
    }

    @Test
    void getBackShouldReturnTheBackMostElementOnANonEmptyDequeTest() {
        assertEquals("Derivative", dequeWithOneFrontElement.getBack());
        assertEquals("Integral", dequeWithOneBackElement.getBack());
        assertEquals("Subtraction", dequeWithFourElements.getBack());
    }

    @Test
    void getFrontShouldNotChangeAnythingInANonEmptyDequeTest() {
        assertEquals("Addition", dequeWithFourElements.getFront());
        assertEquals("Addition", dequeWithFourElements.getFront());
        removeFrontShouldAlwaysRemoveTheFrontMostElementInANonEmptyDequeTest();
    }

    @Test
    void getBackShouldNotChangeAnythingInANonEmptyDequeTest() {
        assertEquals("Subtraction", dequeWithFourElements.getBack());
        assertEquals("Subtraction", dequeWithFourElements.getBack());
        removeBackShouldAlwaysRemoveTheBackMostElementInANonEmptyDequeTest();
    }

    @Test
    void IfAllElementsInADequeWithOneOrMoreElementsAreRemovedItShouldBeEmptyTest() {
        dequeWithOneFrontElement.removeFront();
        assertTrue(dequeWithOneFrontElement.isEmpty());

        dequeWithOneBackElement.removeBack();
        assertTrue(dequeWithOneBackElement.isEmpty());

        dequeWithFourElements.removeBack();
        dequeWithFourElements.removeBack();
        dequeWithFourElements.removeBack();
        dequeWithFourElements.removeBack();
        assertTrue(dequeWithFourElements.isEmpty());
    }

    @Test
    void clearOnAnEmptyDequeShouldLeaveYouWithAnEmptyDequeTest() {
        assertTrue(emptyDeque.isEmpty());
    }

    @Test
    void clearOnADequeWithOneOrMoreElementsShouldLeaveYouWithAnEmptyDequeTest() {
        dequeWithOneFrontElement.clear();
        assertTrue(dequeWithOneFrontElement.isEmpty());

        dequeWithFourElements.clear();
        assertTrue(dequeWithFourElements.isEmpty());
    }

} // end AbstractDequeADTTest
