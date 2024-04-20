package adt;

/**
 * An interface for the ADT heap
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public interface MaxHeapADT<T extends Comparable<? super T>> {
    /**
     * Adds a new element to the heap
     *
     * @param newElement element to be added
     */
    void add(T newElement);

    /**
     * Removes the largest element in the heap
     *
     * @return the element that was removed
     */
    T removeMax();

    /**
     * Finds the largest element in the heap
     *
     * @return the element that was found
     */
    T findMax();

    /**
     * Checks if the heap is empty
     *
     * @return true if the heap is empty
     */
    boolean isEmpty();

    /**
     * Checks the number of elements in the heap
     *
     * @return an integer representing the number of elements in the heap
     */
    int size();

    /**
     * Removes all elements from the heap
     */
    void clear();
} // end MaxHeapADT