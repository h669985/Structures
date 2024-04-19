package javaqueue;

import adt.EmptyQueueException;
import adt.QueueADT;
import java.util.LinkedList;

/**
 * A linked queue using java util implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaQueue<T> implements QueueADT<T> {

    private LinkedList<T> queue;

    /**
     * Constructs an empty linked queue
     */
    public JavaQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public void enqueue(T newEntry) {
        queue.addLast(newEntry);
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue.removeFirst();
    }

    @Override
    public T getFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void clear() {
        queue.clear();
    }
} // end JavaQueue
