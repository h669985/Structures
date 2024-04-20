import adt.BinaryTreeADT;
import linkedbinarytree.LinkedBinaryTree;

public class LinkedBinaryTreeTest extends AbstractBinaryTreeTest {
    @Override
    BinaryTreeADT<Integer> createBinaryTree() {
        return new LinkedBinaryTree<>();
    }
} // end LinkedBinaryTreeTest
