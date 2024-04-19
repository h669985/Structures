import adt.HeapADT;
import jdk.jfr.BooleanFlag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic JUnit test for testing an implementation of the HeapADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

abstract class AbstractHeapTest {
    private HeapADT<Integer> emptyHeap;
    private HeapADT<Integer> heapWithSomeElements;

    abstract HeapADT<Integer> createHeap();

    @BeforeEach
    void reset() {
        emptyHeap = createHeap();

        heapWithSomeElements = createHeap();
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyHeap.getClass().getSimpleName() + "<Integer> emptyHeap");
        System.out.println("    " + heapWithSomeElements.getClass().getSimpleName() + "<Integer> heapWithSomeElements");
    }

    /**
     * Stuff to test ():
     */
} // end AbstractHeapTest
