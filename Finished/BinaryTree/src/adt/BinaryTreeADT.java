package adt;

import java.util.Iterator;

/**
 * An interface for the ADT binary tree.
 * Entries in a binary tree are relegated to nodes that have a left and right child,
 * with a single node as the root of the binary tree.
 * <p>
 * This ADT was taken from HVL in Bergen and translated to english by Jonas Hellevik,
 * and at the same time edited by Jonas Hellevik to have an explanation of what the methods should accomplish
 * when implemented.
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public interface BinaryTreeADT<T> {
    /**
     * Returns the total number of elements in the tree as an integer
     *
     * @return The total number of elements in the tree
     */
    int getSize();

    /**
     * Returns a reference of the root node's data
     *
     * @return The object data in the root of the tree
     */
    T getRootData();

    /**
     * Returns the height/depth of the tree as an integer
     *
     * @return The depth of the tree
     */
    int getHeight();

    /**
     * Checks if the tree is empty
     *
     * @return True if the tree is empty
     */
    boolean isEmpty();

    /**
     * Removes all the nodes in the tree by setting the root to null
     */
    void clear();

    /**
     * Returns a reference to a newly constructed iterable preorder collection of the tree
     *
     * @return A reference to an iterable collection of all current elements in the tree sorted in preorder
     */
    Iterator<T> getPreorderIterator();

    /**
     * Returns a reference to a newly constructed iterable inorder collection of the tree
     *
     * @return A reference to an iterable collection of all current elements in the tree sorted in inorder
     */
    Iterator<T> getInorderIterator();

    /**
     * Returns a reference to a newly constructed iterable postorder collection of the tree
     *
     * @return A reference to an iterable collection of all current elements in the tree sorted in postorder
     */
    Iterator<T> getPostOrderIterator();

    /**
     * Returns a reference to a newly constructed iterable level-order collection of the tree
     *
     * @return A reference to an iterable collection of all current elements in the tree sorted in level-order
     */
    Iterator<T> getLevelIterator();

    /**
     * Creates a binary tree with one node
     *
     * @param rootData The object that is the data in the root node
     */
    void setTree(T rootData);

    /**
     * Constructs a new binary tree
     *
     * @param rootData The object that is the data in the root node
     * @param left     Left subtree of the new tree
     * @param right    Right subtree of the new tree
     * @throws ClassCastException If the subtrees are not of the same class type as the root
     */
    void setTree(T rootData, BinaryTreeADT<T> left, BinaryTreeADT<T> right);

} // end BinaryTreeADT
