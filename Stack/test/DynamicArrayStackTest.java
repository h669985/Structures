import adt.StackADT;
import dynamicarraystack.DynamicArrayStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A JUnit test of my array structure implementation of the StackADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class DynamicArrayStackTest extends AbstractStackADTTest {

    @Override
    StackADT<String> createNewStack() {
        return new DynamicArrayStack<>();
    }

    /*
     * We need to test how capacity is handled specific to this implementation of adt.StackADT
     * - We need to ensure we are increasing capacity correctly when adding new elements
     * - We need to ensure we are decreasing capacity correctly when popping elements.
     */

    @Test
    void aFullStackShouldIncreaseCapacityWhenAddingMoreElementsTest() {
        StackADT<String> fullStack = new DynamicArrayStack<>(4);
        fullStack.push("a");
        fullStack.push("b");
        fullStack.push("c");
        fullStack.push("d");
        assertDoesNotThrow(() -> fullStack.push("e"));
    }

    @Test
    void aReducibleStackShouldPreserveTheElementsInItWhenAReductionHasBeenApplied() {
        StackADT<String> fullStack = new DynamicArrayStack<>(64);
        fullStack.push("a");
        fullStack.push("b");
        fullStack.push("c");
        fullStack.push("d");
        assertDoesNotThrow(() -> {fullStack.pop();});
        assertEquals("c", fullStack.pop());
        assertEquals("b", fullStack.pop());
        assertEquals("a", fullStack.pop());
    }
} // end DynamicArrayStackTest
