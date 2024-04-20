import adt.ListADT;
import javalinkedlist.JavaLinkedList;

/**
 * A JUnit test of my second java util implementation of the ListADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaLinkedListTest extends AbstractListADTTest {
    @Override
    ListADT<String> createNewList() {
        return new JavaLinkedList<>();
    }
} // end JavaLinkedListTest
