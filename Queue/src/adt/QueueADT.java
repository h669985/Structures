package adt;

/**
 * An interface for the ADT queue.
 * 
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 *
 * Slightly edited by Jonas Hellevik
 */

public interface QueueADT<T> {
	
	/**
	 * Adds a new entry to the back of this queue.
	 * 
	 * @param newEntry An object to be added.
	 */
	void enqueue(T newEntry);

	/**
	 * Removes and returns the entry at the front of this queue.
	 * 
	 * @return The object at the front of the queue.
	 * @throws EmptyQueueException if the queue is empty before the operation.
	 */
	T dequeue();

	/**
	 * Retrieves the entry at the front of this queue.
	 * 
	 * @return The object at the front of the queue.
	 * @throws EmptyQueueException if the queue is empty.
	 */
    T getFront();

	/**
	 * Detects whether this queue is empty.
	 * 
	 * @return True if the queue is empty, or false otherwise.
	 */
    boolean isEmpty();

	/** Removes all entries from this queue. */
    void clear();
	
} // end QueueADT
