import adt.StackADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic JUnit test for testing an implementation of the StackADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

abstract class AbstractStackADTTest {

    private StackADT<String> emptyStack;
    private StackADT<String> stackWithFourElements;

    abstract StackADT<String> createNewStack();

    @BeforeEach
    void reset() {
        emptyStack = createNewStack();

        stackWithFourElements = createNewStack();

        stackWithFourElements.push("{");
        stackWithFourElements.push("(");
        stackWithFourElements.push(")");
        stackWithFourElements.push("}");
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyStack.getClass().getSimpleName() + "<String> emptyStack");
        System.out.println("    " + stackWithFourElements.getClass().getSimpleName() + "<String> stackWithFourElements");
    }

    /*
     * Stuff to test (push, pop, peek, isEmpty, clear, size, contains, entries, search):
     *
     * - A new stack should be empty
     * - A stack with one or more elements should not be empty
     * - Elements added to the stack should always be in the opposite order they are removed
     *   - In other words elements are always added as the topmost element
     *      - Push should always add elements as the topmost element
     *      - Pop should always remove the topmost element and return the element
     *      - Peek should always return the topmost element without changing the stack
     * - Trying to pop an empty stack should throw EmptyStackException
     * - Trying to peek an empty stack should throw EmptyStackException
     * - If all elements in a stack have been popped the stack should be empty.
     * - Clear should remove all elements from a stack, leaving you with an empty stack
     * - Size should return 0 on an empty stack
     * - Size should return the number of elements in the stack
     * - Contains on a null entry should return false
     * - Contains should return true if the stack contains the element
     * - Entries should return the number of same entries in the stack
     * - Searching for a null entry should return -1
     * - Search should return the index position of the element in the stack
     * - Search should return -1 if the stack does not contain the element
     */

    @Test
    void aNewStackShouldBeEmptyTest(){
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    void aStackWithOneOrMoreElementsShouldNotBeEmptyTest(){
        assertFalse(stackWithFourElements.isEmpty());
    }

    @Test
    void tryingToPopAnEmptyStackShouldThrowEmptyStackExceptionTest() {
        assertThrows(EmptyStackException.class, () -> emptyStack.pop());
    }

    @Test
    void tryingToPeekAnEmptyStackShouldThrowEmptyStackExceptionTest() {
        assertThrows(EmptyStackException.class, () -> emptyStack.peek());
    }

    @Test
    void clearOnAnEmptyStackShouldLeaveYouWithAnEmptyStackTest() {
        emptyStack.clear();
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    void clearOnAStackWithElementsShouldLeaveYouWithAnEmptyStackTest() {
        assertFalse(stackWithFourElements.isEmpty());
        stackWithFourElements.clear();
        assertTrue(stackWithFourElements.isEmpty());
    }

    @Test
    void pushShouldAddAnElementToTheStackTest() {
        assertTrue(emptyStack.isEmpty());
        emptyStack.push("NoLongerEmpty");
        assertFalse(emptyStack.isEmpty());
    }

    @Test
    void popShouldReturnTheTopmostElementTest() {
        assertEquals("}", stackWithFourElements.pop());
        assertEquals(")", stackWithFourElements.pop());
        assertEquals("(", stackWithFourElements.pop());
        assertEquals("{", stackWithFourElements.pop());
    }

    @Test
    void peekShouldReturnTheTopmostElementTest() {
        assertEquals("}", stackWithFourElements.peek());
        assertEquals("}", stackWithFourElements.peek());
        assertEquals("}", stackWithFourElements.peek());
    }

    @Test
    void popShouldRemoveTheTopmostElementTest() {
        assertEquals("}", stackWithFourElements.pop());
        assertNotEquals("}", stackWithFourElements.peek());
        assertEquals(")", stackWithFourElements.pop());
        assertNotEquals(")", stackWithFourElements.peek());
        assertEquals("(", stackWithFourElements.pop());
        assertNotEquals("(", stackWithFourElements.peek());
    }

    @Test
    void poppingAllElementsShouldLeaveYouWithAnEmptyStackTest() {
        stackWithFourElements.pop();
        stackWithFourElements.pop();
        stackWithFourElements.pop();
        stackWithFourElements.pop();
        assertTrue(stackWithFourElements.isEmpty());
    }

    @Test
    void peekShouldNotChangeTheStackTest() {
        assertEquals("}", stackWithFourElements.peek());
        assertEquals("}", stackWithFourElements.peek());
        stackWithFourElements.pop();
        assertEquals(")", stackWithFourElements.peek());
        assertEquals(")", stackWithFourElements.peek());
    }

    @Test
    void sizeShouldReturnZeroOnAnEmptyStackTest() {
        assertEquals(0, emptyStack.size());
    }

    @Test
    void sizeShouldReturnTheSizeOfTheStackTest() {
        int i = 4;
        while (!(i == 0)) {
            assertEquals(i, stackWithFourElements.size());
            stackWithFourElements.pop();
            i--;
        }
    }

    @Test
    void containsShouldReturnFalseIfAnEntryIsNull() {
        assertFalse(stackWithFourElements.contains(null));
        assertFalse(emptyStack.contains(null));
    }

    @Test
    void containsShouldReturnTrueIfTheStackContainsTheElementTest() {
        assertTrue(stackWithFourElements.contains("}"));
        assertTrue(stackWithFourElements.contains("("));
        assertTrue(stackWithFourElements.contains("{"));
        assertTrue(stackWithFourElements.contains(")"));
    }

    @Test
    void containsShouldReturnFalseIfTheStackDoesNotContainTheElementTest() {
        assertFalse(stackWithFourElements.contains("a"));
        assertFalse(emptyStack.contains("a"));
    }

    @Test
    void containsShouldBeAbleToReturnTrueOnNullEntries() {
        emptyStack.push(null);
        assertTrue(emptyStack.contains(null));
        stackWithFourElements.push(null);
        stackWithFourElements.push("b");
        assertTrue(stackWithFourElements.contains(null));
    }

    @Test
    void containsShouldBeAbleToReturnFalseOnEmptyStack() {
        assertFalse(emptyStack.contains("a"));
    }

    @Test // Also tests if it correctly counts duplicates
    void entriesShouldReturnTheNumberOfEntriesInTheStackTest() {
        assertEquals(1, stackWithFourElements.entries("}"));
        stackWithFourElements.push("}");
        assertEquals(2, stackWithFourElements.entries("}"));
        stackWithFourElements.pop();
        assertEquals(1, stackWithFourElements.entries("}"));
    }

    @Test
    void entriesShouldBeAbleToCountNullEntriesTest() {
        emptyStack.push(null);
        assertEquals(1, emptyStack.entries(null));
        emptyStack.push(null);
        assertEquals(2, emptyStack.entries(null));
        emptyStack.push(null);
        assertEquals(3, emptyStack.entries(null));
    }

    @Test
    void searchShouldReturnMinusOneIfTheStackDoesNotContainTheElementTest() {
        assertEquals(-1, stackWithFourElements.search("a"));
        assertEquals(-1, emptyStack.search("a"));
    }

    @Test
    void searchShouldReturnTheCorrectIndexOfTheElementTest() {
        assertEquals(3, stackWithFourElements.search("{"));
        assertEquals(2, stackWithFourElements.search("("));
        assertEquals(1, stackWithFourElements.search(")"));
        assertEquals(0, stackWithFourElements.search("}"));
    }

    @Test
    void searchShouldReturnTheIndexOfANullEntryTest() {
        emptyStack.push(null);
        emptyStack.push("a");
        emptyStack.push("b");
        emptyStack.push("c");
        assertEquals(3, emptyStack.search(null));
        stackWithFourElements.push(null);
        assertEquals(0, stackWithFourElements.search(null));
    }

    @Test
    void searchShouldReturnMinusOneIfTheStackIsEmptyTest() {
        assertEquals(-1, emptyStack.search("a"));
    }

} // end AbstractADTTest
