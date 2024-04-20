package adt;

import java.util.EmptyStackException;

/**
 * An interface for the ADT stack.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 *
 * Expanded upon by Jonas Hellevik 2024 by adding uncommon methods for a stack structure (size, contains, entries, search)
 * (Note that these methods are unusual because the advantage of a stack is that working with stack methods
 * like adding and removing has a time complexity of O(1).
 * Size could have a time complexity of O(1) or O(n) depending on the implementation.
 * Contains, entries, and search will have a time complexity of O(n).
 * Although contains and search will return early if it finds the entry. Entries will always iterate the whole stack.)
 */
public interface StackADT<T> {

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    void push(T newEntry);

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    T pop();

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    T peek();

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    boolean isEmpty();

    /** Removes all entries from this stack. */
    void clear();

    /**
     * Returns how many entries are in the stack as an integer
     *
     * @return the number of entries in the stack
     */
    int size();

    /**
     * Checks whether the stack contains an entry or not, returns false if the stack is empty
     *
     * @param anEntry An entry to check if is in the stack
     * @return true if the stack contains the entry
     */
    boolean contains(T anEntry);

    /**
     * Checks how many entries there are in the stack
     * Will also count the number of null elements in the stack
     *
     * @param anEntry An entry to check how many entries there are in the stack
     * @return Returns the number of occurrences of a particular entry in the stack
     */
    int entries(T anEntry);

    /**
     * Returns the index of an entry starting at 0 from the topmost element going down
     *
     * @param anEntry An entry to find which position in the stack it is
     * @return The index position of the entry in the stack, -1 if the stack does not contain the entry or is empty
     */
    int search(T anEntry);

} // end StackADT