package circulararrayqueue;

import adt.EmptyQueueException;
import adt.QueueADT;

import java.util.Arrays;

/**
 * A circular array queue implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class CircularArrayQueue<T> implements QueueADT<T> {

    // We want 1 extra space available to assert whether the queue is empty or full
    private static final int DEFAULT_CAPACITY = 11;

    private T[] array;
    private int front;
    private int rear;

    /**
     * Constructs an empty queue with a default capacity of 10
     */
    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty queue with the given initial capacity
     *
     * @param capacity the size of the queue
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int capacity) {
        array = (T[]) new Object[capacity + 1];
        front = 0;
        rear = capacity;
    }

private int onePlussModuloN(int index) {
        return (index + 1) % array.length;
}

    @Override
    public void enqueue(T newEntry) {
        ensureCapacity();
        rear = onePlussModuloN(rear);
        array[rear] = newEntry;
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        T result = array[front];
        array[front] = null;
        front = onePlussModuloN(front);
        return result;
    }

    @Override
    public T getFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return front == onePlussModuloN(rear);
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        front = 0;
        rear = array.length - 1;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if(isArrayFull()) {
            T[] oldArray = array;
            int oldCapacity = oldArray.length;
            int newCapacity = oldCapacity * 2;

            array = (T[]) new Object[newCapacity];
            for(int i = 0; i < oldCapacity - 1; i++) {
                array[i] = oldArray[front];
                front = (front + 1) % oldCapacity;
            }
            front = 0;
            rear = oldCapacity - 2;
        }
    }

    private boolean isArrayFull() {
        return front == ((rear + 2) % array.length);
    }

} // end CircularArrayQueue
