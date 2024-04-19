package adt;

/**
 * An interface for the ADT deque.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 *
 * Slightly edited by Jonas Hellevik
 */
public interface DequeADT<T> {
	
	/**
	 * Adds a new entry to the front/back of this deque.
	 * 
	 * @param newEntry An object to be added.
	 */
    void addToFront(T newEntry);

	void addToBack(T newEntry);

	/**
	 * Removes and returns the front/back entry of this deque.
	 * 
	 * @return The object at the front/back of the deque.
	 * @throws EmptyQueueException if the deque is empty before the operation.
	 */
    T removeFront();

	T removeBack();

	/**
	 * Retrieves the front/back entry of this deque.
	 * 
	 * @return The object at the front/back of the deque.
	 * @throws EmptyQueueException if the deque is empty.
	 */
    T getFront();

	T getBack();

	/**
	 * Detects whether this deque is empty.
	 * 
	 * @return True if the deque is empty, or false otherwise.
	 */
    boolean isEmpty();

	/** Removes all entries from this deque. */
    void clear();
} // end DequeADT
