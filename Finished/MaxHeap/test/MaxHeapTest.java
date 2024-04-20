import ArrayMaxHeap.MaxHeap;
import adt.MaxHeapADT;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test for testing an array implementation of the ADT interface MaxHeap
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class MaxHeapTest extends AbstractMaxHeapTest {
    @Override
    MaxHeapADT<Integer> createHeap() {
        return new MaxHeap<>();
    }

    /**
     * Lazy test to check that the private method ensureCapacity works as intended
     * Although it doesn't check against a solution array to ensure that we don't
     * lose elements when reducing the size of the capacity
     */
    @Test
    void ensureCapacityTest() throws InterruptedException {
        System.gc(); Thread.sleep(500);
        int numOfRuns = 1000000;
        Random seed = new Random();
        int initialCapacity = seed.nextInt(16, 1024);
        MaxHeap<Integer> heap = new MaxHeap<>(initialCapacity);

        // Initially fill the heap to capacity to possibly trigger an increase in size
        for (int i = 0; i < initialCapacity; i++) {
            assertDoesNotThrow(() -> heap.add(seed.nextInt(64)));
        }

        boolean lastActionWasAdd = true;

        while (numOfRuns > 0) {
            // Decide to add or remove based on the last action
            if (lastActionWasAdd && seed.nextDouble() < 0.5) {
                // If the last action was add, 50% chance to switch to remove
                for (int i = 0; i < seed.nextInt(1, initialCapacity/2); i++) {
                    if (!heap.isEmpty()) { // Prevents removing from an empty heap
                        assertNotNull(heap.removeMax());
                        lastActionWasAdd = false;
                    }
                }
            } else {
                // If the last action was remove, always add
                for (int i = 0; i < seed.nextInt(1, initialCapacity/16); i++) {
                    assertDoesNotThrow(() -> heap.add(seed.nextInt(64)));
                }

                lastActionWasAdd = true;
            }

            // Occasionally, switch actions regardless of the last one, using different probabilities
            if (seed.nextDouble() < 0.2) {
                lastActionWasAdd = !lastActionWasAdd; // Switch action
            }

            numOfRuns--;
        } // end while
    } // end ensureCapacityTest
} // end MaxHeapTest
