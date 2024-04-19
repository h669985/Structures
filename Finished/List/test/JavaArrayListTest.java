import adt.ListADT;
import javaarraylist.JavaArrayList;

/**
 * A JUnit test of my java util implementation of the ListADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaArrayListTest extends AbstractListADTTest {
    @Override
    ListADT<String> createNewList() {
        return new JavaArrayList<>();
    }
} // end JavaArrayListTest
