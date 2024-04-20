package javadeque;

import adt.DequeADT;
import adt.EmptyQueueException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A java util deque implementation of the DequeADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaDeque<T> implements DequeADT<T> {

    private final Deque<T> deque;

    /**
     * Constructs and empty deque with a default capacity of 16
     */
    public JavaDeque() {
        deque = new ArrayDeque<>();
    }

    /**
     * Constructs an empty deque with a specified initial capacity
     *
     * @param capacity the specified initial capacity
     */
    public JavaDeque(int capacity) {
        deque = new ArrayDeque<>(capacity);
    }

    @Override
    public void addToFront(T newEntry) {
        deque.addFirst(newEntry);
    }

    @Override
    public void addToBack(T newEntry) {
        deque.addLast(newEntry);
    }

    @Override
    public T removeFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return deque.pollFirst();
    }

    @Override
    public T removeBack() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return deque.pollLast();
    }

    @Override
    public T getFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return deque.peekFirst();
    }

    @Override
    public T getBack() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return deque.peekLast();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public void clear() {
        deque.clear();
    }

} // end JavaDeque
