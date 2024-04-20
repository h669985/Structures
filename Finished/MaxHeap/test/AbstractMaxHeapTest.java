import adt.MaxHeapADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic JUnit test for testing an implementation of the HeapADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

abstract class AbstractMaxHeapTest {
    private MaxHeapADT<Integer> emptyHeap;
    private MaxHeapADT<Integer> heapWithSomeElements;

    abstract MaxHeapADT<Integer> createHeap();

    @BeforeEach
    void reset() {
        emptyHeap = createHeap();

        heapWithSomeElements = createHeap();

        heapWithSomeElements.add(1);
        heapWithSomeElements.add(4);
        heapWithSomeElements.add(8);
        heapWithSomeElements.add(3);
        heapWithSomeElements.add(6);
        heapWithSomeElements.add(2);
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyHeap.getClass().getSimpleName() + "<Integer> emptyHeap");
        System.out.println("    " + heapWithSomeElements.getClass().getSimpleName() + "<Integer> heapWithSomeElements");
    }

    /*
     * Stuff to test (add, removeMax, findMax, isEmpty, size, clear):
     *
     * - add should add an entry to the heap
     * - removeMax should return null on an empty heap
     * - removeMax should only remove the largest element in the heap
     * - removeMax should return the element it removed
     * - findMax should return null on an empty heap
     * - findMax should return the largest element in the Heap
     * - A newly constructed heap is empty
     * - A heap with one or more elements is not empty
     * - A heap whose elements have all been removed is empty
     * - An empty heap has no entries in it, so the size should be zero
     * - size should return the number of elements in a heap with one or more elements
     * - size should increase if an element has been added
     * - size should decrease if an element has been removed
     * - A cleared heap is empty
     */

    @Test
    void addShouldAddTheElementToTheHeap() {
        emptyHeap.add(5);
        assertFalse(emptyHeap.isEmpty());
        assertEquals(1, emptyHeap.size());
        assertEquals(5, emptyHeap.findMax());
    }

    @Test
    void removeMaxShouldReturnNullOnAnEmptyHeapTest() {
        assertNull(emptyHeap.removeMax());
    }

    @Test
    void removeMaxShouldOnlyRemoveTheLargestElementFromTheHeapTest() {
        heapWithSomeElements.removeMax();
        assertEquals(6, heapWithSomeElements.findMax());
    }

    @Test
    void removeMaxShouldReturnTheLargestElementFromTheHeapTest() {
        assertEquals(8, heapWithSomeElements.removeMax());
        assertEquals(6, heapWithSomeElements.removeMax());
        assertEquals(4, heapWithSomeElements.removeMax());
    }

    @Test
    void findMaxShouldReturnNullOnAnEmptyHeapTest() {
        assertNull(emptyHeap.findMax());
    }

    @Test
    void findMaxShouldReturnTheLargestElementFromTheHeapTest() {
        assertEquals(8, heapWithSomeElements.findMax());
        heapWithSomeElements.add(12);
        assertEquals(12, heapWithSomeElements.findMax());
    }

    @Test
    void isEmptyShouldReturnTrueOnAnEmptyHeapTest() {
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    void isEmptyShouldReturnFalseOnAHeapWithSomeElementsTest() {
        assertFalse(heapWithSomeElements.isEmpty());
    }

    @Test
    void isEmptyShouldReturnTrueOnAHeapWhoseElementsHasBeenRemovedTest() {
        while(!heapWithSomeElements.isEmpty()) {
            heapWithSomeElements.removeMax();
        }
        assertTrue(heapWithSomeElements.isEmpty());
    }

    @Test
    void sizeShouldReturnZeroOnAnEmptyHeapTest() {
        assertEquals(0, emptyHeap.size());
    }

    @Test
    void sizeShouldReturnTheNumberOfElementsInTheHeapTest() {
        assertEquals(6, heapWithSomeElements.size());
    }

    @Test
    void sizeShouldDecreaseIfAnElementHasBeenRemovedTest() {
        heapWithSomeElements.removeMax();
        heapWithSomeElements.removeMax();
        assertEquals(4, heapWithSomeElements.size());
    }

    @Test
    void clearOnAnEmptyHeapShouldLeaveYouWithAnEmptyHeapTest() {
        emptyHeap.clear();
        assertTrue(emptyHeap.isEmpty());
    }

    @Test
    void clearOnAHeapWithSomeElementsShouldLeaveYouWithAnEmptyHeapTest() {
        heapWithSomeElements.clear();
        assertTrue(heapWithSomeElements.isEmpty());
    }
} // end AbstractMaxHeapTest
