import adt.ListADT;

import arraylist.ArrayList;

/**
 * A JUnit test of my array implementation of the ListADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class ArrayListTest extends AbstractListADTTest {
    @Override
    ListADT<String> createNewList() {
        return new ArrayList<>();
    }
} // end ArrayListTest
