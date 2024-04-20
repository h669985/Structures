import adt.BinaryTreeADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
/**
 * A generic JUnit test for testing an implementation of the BinaryTreeADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

@SuppressWarnings("FieldCanBeLocal")
abstract class AbstractBinaryTreeTest {
    private BinaryTreeADT<Integer> emptyTree;

    private BinaryTreeADT<Integer> rootTree;

    private BinaryTreeADT<Integer> leftBranch;
    private BinaryTreeADT<Integer> rightBranch;

    private BinaryTreeADT<Integer> leaf1;
    private BinaryTreeADT<Integer> leaf2;
    private BinaryTreeADT<Integer> leaf3;
    private BinaryTreeADT<Integer> leaf4;

    abstract BinaryTreeADT<Integer> createBinaryTree();

    @BeforeEach
    void reset() {
        emptyTree = createBinaryTree();

        rootTree = createBinaryTree();

        leftBranch = createBinaryTree();
        rightBranch = createBinaryTree();

        leaf1 = createBinaryTree();
        leaf2 = createBinaryTree();
        leaf3 = createBinaryTree();
        leaf4 = createBinaryTree();

        leaf1.setTree(3);
        leaf2.setTree(4);
        leaf3.setTree(5);
        leaf4.setTree(6);

        leftBranch.setTree(1, leaf1, leaf2);
        rightBranch.setTree(2, leaf3, leaf4);

        rootTree.setTree(0, leftBranch, rightBranch);
    }

    @Test
    void info() {
        System.out.println(this.getClass().getSimpleName());
        System.out.println("    " + emptyTree.getClass().getSimpleName() + "<Integer> emptyTree");
        System.out.println("    " + rootTree.getClass().getSimpleName() + "<Integer> rootTree");
    }

    /*
     * Stuff to test (getSize, getRootData, getHeight, isEmpty, clear,
     * getPreorderIterator, getInorderIterator, getPostOrderIterator, getLevelIterator,
     * setTree, setTree(...)):
     *
     * - getSize should return 0 on an empty tree
     * - getSize should return the number of elements in a tree with one or more elements as an integer
     * - getRootData should return null on an empty tree
     * - getRootData should return the data in the root of a tree with one or more elements
     * - getHeight should return 0 on an empty tree
     * - getHeight should return the height/depth of a tree with one or more elements as an integer
     * - isEmpty should return true on an empty tree
     * - isEmpty should return false on a tree with one or more elements in it
     * - clear should delete all the elements in a tree, leaving you with an empty tree
     * - getPreorderIterator should return a reference to some iterable collection of all the current elements in the tree sorted in preorder
     * - getInorderIterator should return a reference to some iterable collection of all the current elements in the tree sorted in inorder
     * - getPostOrderIterator should return a reference to some iterable collection of all the current elements in the tree sorted in postorder
     * - getLevelIterator should return a reference to some iterable collection of all the current elements in the tree sorted in level-order
     * - setTree should assign a new node to the root of the tree with the node containing the data
     * - setTree(...) should do the same as above, but also assign the tree's left and right children as two other trees
     * - setTree(...) should throw ClassCastException if the subtrees assigned to the root are not of the same class type
     */

    @Test
    void getSizeShouldReturnZeroOnAnEmptyTreeTest() {
        assertEquals(0, emptyTree.getSize());
    }

    @Test
    void getSizeShouldReturnTheNumberOfElementsOnATreeWithOneOrMoreElementsTest() {
        assertEquals(7, rootTree.getSize());
    }

    @Test
    void getRootDataShouldReturnNullOnAnEmptyTreeTest() {
        assertNull(emptyTree.getRootData());
    }

    @Test
    void getRootDataShouldReturnTheRootDataOnATreeWithOneOrMoreElementsTest() {
        assertEquals(0, rootTree.getRootData());
    }

    @Test
    void getHeightShouldReturnZeroOnAnEmptyTreeTest() {
        assertEquals(0, emptyTree.getHeight());
    }

    @Test
    void getHeightShouldReturnTheHeightOnATreeWithOneOrMoreElementsTest() {
        assertEquals(2, rootTree.getHeight());
    }

    @Test
    void isEmptyShouldReturnTrueOnAnEmptyTreeTest() {
        assertTrue(emptyTree.isEmpty());
    }

    @Test
    void isEmptyShouldReturnTrueOnANonEmptyTreeTest() {
        assertFalse(rootTree.isEmpty());
    }

    @Test
    void clearShouldRemoveAllTheElementsInATreeWithAnyNumberOfElementsTest() {
        emptyTree.clear();
        assertTrue(emptyTree.isEmpty());

        rootTree.clear();
        assertTrue(rootTree.isEmpty());
    }

    @Test
    void getPreorderIterator() {
        int index = 0;
        Integer[] solution = {0, 1, 3, 4, 2, 5, 6};

        for (Iterator<Integer> it = rootTree.getPreorderIterator(); it.hasNext(); ) {
            assertEquals(solution[index], it.next());
            index++;
        }
    }

    @Test
    void getInorderIterator() {
        int index = 0;
        Integer[] solution = {3, 1, 4, 0, 5, 2, 6};

        for (Iterator<Integer> it = rootTree.getInorderIterator(); it.hasNext(); ) {
            assertEquals(solution[index], it.next());
            index++;
        }
    }

    @Test
    void getPostorderIterator() {
        int index = 0;
        Integer[] solution = {3, 4, 1, 5, 6, 2, 0};

        for (Iterator<Integer> it = rootTree.getPostOrderIterator(); it.hasNext(); ) {
            assertEquals(solution[index], it.next());
            index++;
        }
    }

    @Test
    void getLevelOrderIterator() {
        int index = 0;
        Integer[] solution = {0, 1, 2, 3, 4, 5, 6};

        for (Iterator<Integer> it = rootTree.getLevelIterator(); it.hasNext(); ) {
            assertEquals(solution[index], it.next());
            index++;
        }
    }

    @Test
    void setTreeShouldAssignANewNodeToTheRootOfTheTreeTest() {
        emptyTree.setTree(45);
        assertFalse(emptyTree.isEmpty());
        assertEquals(45, emptyTree.getRootData());
    }

    @Test
    void setTreeShouldAssignTheSubtreesOfTheRootOfTheTreeTest() {
        emptyTree.setTree(1, leaf1, null);
        assertFalse(emptyTree.isEmpty());
        assertEquals(2, emptyTree.getSize());

        emptyTree.setTree(2, null, leaf2);
        assertFalse(emptyTree.isEmpty());
        assertEquals(2, emptyTree.getSize());

        emptyTree.setTree(2, leaf1, leaf2);
        assertFalse(emptyTree.isEmpty());
        assertEquals(3, emptyTree.getSize());
    }
} // end AbstractBinaryTreeTest
