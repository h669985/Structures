import adt.ListADT;
import linkedlist.LinkedList;

/**
 * A JUnit test of my linked list implementation of the ListADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class LinkedListTest extends AbstractListADTTest {
    @Override
    ListADT<String> createNewList() {
        return new LinkedList<>();
    }
} // end LinkedListTest
