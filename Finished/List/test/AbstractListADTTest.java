import adt.ListADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A generic JUnit test for testing an implementation of the ListADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

abstract class AbstractListADTTest {

    private ListADT<String> emptyList;
    private ListADT<String> listWithTwoEntries;

    abstract ListADT<String> createNewList();

    @BeforeEach
    void reset() {
        emptyList = createNewList();
        listWithTwoEntries = createNewList();

        listWithTwoEntries.add("Peter");
        listWithTwoEntries.add("Clara");
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyList.getClass().getSimpleName() + "<String> emptyList");
        System.out.println("    " + listWithTwoEntries.getClass().getSimpleName() + "<String> listWithTwoEntries");
    }

    /*
     * Stuff to test (add, remove, clear, replace, getEntry, toArray, contains, getLenght, isEmpty):
     *
     * - A new list should be empty
     * - An existing empty list should be empty
     * - A list with one or more entries should not be empty
     * - add(T anEntry) should add the entry to the end of the list and increase the size of the list by 1 if applicable by the implementation
     * - add(int newPosition, T anEntry) should throw IndexOutOfBoundsException if either newPosition < 1 or newPosition > getLength() + 1
     * - add(int newPosition, T anEntry) should add the entry to the given position
     * - add(int newPosition, T anEntry) should increase the size of the list by 1 before adding the new entry if applicable by the implementation
     * - add(int newPosition, T anEntry) should shift the entries after over by 1 before adding the entry
     * - remove should throw IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength()
     * - remove should return the entry it removed
     * - remove should decrease the size of the list by 1 after removing the entry if applicable by the implementation
     * - remove should shift the entries after the removed entry back by 1 after removing the entry
     * - replace should throw IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength()
     * - replace should return the entry it replaced
     * - replace should replace the given entry with the entry at the given position
     * - getEntry should throw IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength()
     * - getEntry should return the entry of the given integer position
     * - toArray should return an empty array if the list is empty
     * - toArray should create a new array with the lenght equal to the number of entries in the list
     * - toArray should return a newly allocated array of all the entries in the same order they occurred in the list
     * - contains should return true if an entry exists in the list
     * - contains should return false if an entry does not exist in the list
     * - getLenght should return an integer value representing the current number of entries in the list
     * - isEmpty should return true on an empty list
     * - isEmpty should return false on a list with one or more entries in it
     */

    @Test
    void addShouldAddTheEntryAtTheEndOfTheListTest() {
        emptyList.add("Donald");
        assertEquals("Donald", emptyList.getEntry(1));
        listWithTwoEntries.add("Kent");
        assertEquals("Kent", listWithTwoEntries.getEntry(3));
    }


    @Test
    void addWithGivenPositionShouldThrowIndexOutOfBoundsExceptionIfTheGivenPositionIsOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {listWithTwoEntries.add(4, "Fred");});
    }

    @Test
    void addWithGivenPositionShouldPutTheEntryAtTheGivenPositionTest() {
        listWithTwoEntries.add(2, "Hailey");
        assertEquals("Hailey", listWithTwoEntries.getEntry(2));
    }

    @Test // Also checks that the previous entry was unaffected
    void afterAddingAnEntryWithAGivenPositionAtTheListItShouldShiftTheEntriesAfterByOneTheListByOneTest() {
        listWithTwoEntries.add(2,"Greg");
        assertEquals("Peter", listWithTwoEntries.getEntry(1));
        assertEquals("Clara", listWithTwoEntries.getEntry(3));
    }

    @Test
    void removeShouldThrowIndexOutOfBoundsExceptionIfTheGivenPositionIsOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {listWithTwoEntries.remove(4);});
    }

    @Test
    void removeShouldReturnTheEntryItRemovedTest() {
        assertEquals("Clara", listWithTwoEntries.remove(2));
    }

    @Test
    void removeShouldShiftTheEntriesAfterTheRemovedEntryBackByOneTest() {
        listWithTwoEntries.remove(1);
        assertEquals("Clara", listWithTwoEntries.getEntry(1));
    }

    @Test
    void replaceShouldThrowIndexOutOfBoundsExceptionIfTheGivenPositionIsOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {listWithTwoEntries.replace(4, "Ted");});
    }

    @Test
    void replaceShouldReturnTheEntryItReplacedTest() {
        assertEquals("Peter", listWithTwoEntries.replace(1, "Pat"));
    }

    @Test
    void replaceShouldReplaceTheGivenEntryAtTheGivenPositionTest() {
        listWithTwoEntries.replace(2, "Belle");
        assertEquals("Belle", listWithTwoEntries.getEntry(2));
    }

    @Test
    void getEntryShouldThrowIndexOutOfBoundsExceptionIfTheGivenPositionIsOutOfBoundsTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {listWithTwoEntries.getEntry(4);});
    }

    @Test
    void getEntryShouldReturnTheEntryOfTheGivenPositionTest() {
        assertEquals("Clara", listWithTwoEntries.getEntry(2));
    }

    @Test
    void toArrayShouldReturnAnEmptyArrayIfThereAreNoEntriesInTheListTest() {
        String[] array = new String[0];
        assertArrayEquals(array, emptyList.toArray());
    }

    @Test
    void toArrayShouldReturnAnArrayOfLenghtEqualToTheNumberOfEntriesInTheListTest() {
        assertEquals(2, listWithTwoEntries.toArray().length);
    }

    @Test
    void toArrayShouldReturnAnArrayWithAllTheEntriesInTheListInTheSameOrderTest() {
        assertEquals("Peter", listWithTwoEntries.toArray()[0]);
        assertEquals("Clara", listWithTwoEntries.toArray()[1]);
    }

    @Test
    void containsShouldReturnTrueIfAnEntryExistsTest() {
        assertTrue(listWithTwoEntries.contains("Peter"));
        assertTrue(listWithTwoEntries.contains("Clara"));
    }

    @Test
    void containsShouldReturnFalseIfAnEntryDoesNotExistTest() {
        assertFalse(listWithTwoEntries.contains("Hugh"));
        listWithTwoEntries.remove(2);
        assertFalse(listWithTwoEntries.contains("Clara"));
    }

    @Test
    void getLenghtShouldReturnAnIntegerRepresentingTheNumberOfEntriesInTheList() {
        assertEquals(0, emptyList.getLength());
        assertEquals(2, listWithTwoEntries.getLength());
    }

    @Test
    void aNewListShouldBeEmptyTest() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void anExistingEmptyListShouldBeEmptyTest() {
        emptyList.add("John");
        emptyList.clear();
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void aListWithOneOrMoreEntriesShouldNotBeEmptyTest() {
        assertFalse(listWithTwoEntries.isEmpty());
        emptyList.add("Joline");
        assertFalse(emptyList.isEmpty());
    }

    /**
     * This test is created to try and break the code despite the rigorous testing above
     * It does not check stuff like using up memory, or garbage collection type stuff.
     * This final test is only concerned with testing the methods listed in ListADT
     * (Usually Java handles that stuff pretty well automatically)
     * If all the tests have been passed and this one has been passed then your code is quite robust!
     * (Note that ChatGPT 4 played a big part in creating this test, but the test should be valid after testing
     *  against four different implementations I created for ListADT)
     */
    @Test @SuppressWarnings("ConstantConditions")
    void finalBossTest() throws InterruptedException {
        boolean tryFinalBoss = false; // Change this variable to true if you're ready
        if(tryFinalBoss) {
            // That's right we're running this loop a lot of times. This test will take a while
            int iterations = 0;
            while (iterations < 30) {
                System.gc(); Thread.sleep(500); // Ensuring we're not going to run out of memory during an iteration

                // Reset
                ListADT<String> finalBossList = createNewList();
                Random seed = new Random();
                int bound = seed.nextInt(1,100);
                String[] solution = new String[bound];

                finalBossList.clear();
                assertTrue(finalBossList.isEmpty());


                // Checking how it handles adding a lot of elements on top of the list
                for (int i = 0; i < bound; i++) {
                    String check = seed.nextInt() + "";

                    finalBossList.add(check);

                    solution[i] = check;
                }

                if(finalBossList.isEmpty()) {
                    System.out.println("List is empty"); // Debug line
                }

                assertArrayEquals(solution, finalBossList.toArray());
                int entries = solution.length - 1;
                for (int i = 1; i <= entries; i++) {
                    assertEquals(solution[i-1], finalBossList.getEntry(i));
                    assertTrue(finalBossList.contains(solution[i-1]));
                }

                if(finalBossList.isEmpty()) {
                    System.out.println("List is empty"); // Debug line
                }
                assertFalse(finalBossList.isEmpty());
                System.out.print("add; ");


                // Checking how it handles adding elements at random places
                for (int i = 0; i < bound / 2; i++) {
                    String check = seed.nextInt() + "";

                    // Generate a 1-based random index for insertion into the list
                    int randomIndex = seed.nextInt(1, solution.length + 1);

                    // Add the element at the 1-based randomIndex in finalBossList
                    finalBossList.add(randomIndex, check);

                    // Create a new array larger by one to accommodate the new element
                    String[] newSolution = new String[solution.length + 1];

                    // Adjust the randomIndex to be 0-based for the solution array
                    int arrayIndex = randomIndex - 1;

                    // Copy elements from original solution up to the 0-based index into newSolution
                    System.arraycopy(solution, 0, newSolution, 0, arrayIndex);

                    // Insert the new element at the 0-based index in newSolution
                    newSolution[arrayIndex] = check;

                    // Copy the rest of the elements from original solution into newSolution, starting after the inserted element
                    System.arraycopy(solution, arrayIndex, newSolution, arrayIndex + 1, solution.length - arrayIndex);

                    // Update the solution to point to the newSolution including the inserted element
                    solution = newSolution;
                }

                assertArrayEquals(solution, finalBossList.toArray());
                entries = solution.length - 1;
                for (int i = 1; i <= entries; i++) {
                    assertEquals(solution[i-1], finalBossList.getEntry(i));
                    assertTrue(finalBossList.contains(solution[i-1]));
                }
                assertFalse(finalBossList.isEmpty());
                System.out.print("add(int); ");


                // Checks that replacing random elements works correctly
                for (int i = 0; i < solution.length - 1; i++) {
                    String check = seed.nextInt() + "";

                    // Generate a 1-based random index for replacing into the list
                    int randomIndex = seed.nextInt(1, solution.length + 1);

                    // Replace the element at the 1-based randomIndex in finalBossList
                    finalBossList.replace(randomIndex, check);

                    // Replace the element at the 1-based randomIndex in the solution array
                    solution[randomIndex - 1] = check;
                }

                assertArrayEquals(solution, finalBossList.toArray());
                entries = solution.length - 1;
                for (int i = 1; i <= entries; i++) {
                    assertEquals(solution[i-1], finalBossList.getEntry(i));
                    assertTrue(finalBossList.contains(solution[i-1]));
                }
                assertFalse(finalBossList.isEmpty());
                System.out.print("replace; ");

                // Checks that removing random elements works correctly
                for (int i = 0; i < solution.length / 2; i++) {
                    // Generate a 1-based random index for removal
                    int randomIndex = seed.nextInt(1, solution.length + 1);

                    // Remove the element at the 1-based randomIndex in finalBossList
                    finalBossList.remove(randomIndex);

                    // Create a new array smaller by one to accommodate the removal
                    String[] newSolution = new String[solution.length - 1];

                    // Adjust the randomIndex to be 0-based for the solution array
                    int arrayIndex = randomIndex - 1;

                    // Copy elements from original solution up to the 0-based index into newSolution
                    System.arraycopy(solution, 0, newSolution, 0, arrayIndex);

                    // Skip the removed element and copy the rest of the elements from original solution into newSolution
                    System.arraycopy(solution, arrayIndex + 1, newSolution, arrayIndex, solution.length - arrayIndex - 1);

                    // Update the solution to point to the newSolution excluding the removed element
                    solution = newSolution;
                }


                entries = solution.length - 1;
                for (int i = 1; i <= entries; i++) {
                    assertEquals(solution[i-1], finalBossList.getEntry(i));
                    assertTrue(finalBossList.contains(solution[i-1]));
                }
                assertFalse(finalBossList.isEmpty());
                System.out.print("remove; ");

                finalBossList.clear();
                assertTrue(finalBossList.isEmpty());

                iterations++;
                System.out.println(iterations + " passed!");
            } // end while
        }  // end if
    } // end finalBossTest()

} // end AbstractListADTTest
