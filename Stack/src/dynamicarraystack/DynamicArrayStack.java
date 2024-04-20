package dynamicarraystack;

import adt.StackADT;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * An implementation of the StackADT interface using arrays
 *
 * @author Jonas Hellevik
 * @version 2.0
 */

@SuppressWarnings("unchecked")
public class DynamicArrayStack<T> implements StackADT<T> {

    private static final int DEFAULT_CAPACITY = 8;

    private T[] array;
    private int size;

    /**
     * Creates a new dynamic stack array with a default capacity of 8
     */
    public DynamicArrayStack() {
     this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a new dynamic stack array with the given capacity
     *
     * @param capacity size of the stack
     */
    public DynamicArrayStack(int capacity) {
        array = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void push(T newEntry) {
        if (size == array.length) { // If the array is full, double it in size before proceeding with the rest of the code
            T[] newArray = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        array[size] = newEntry;
        this.size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        if(size < array.length / 4 && array.length / 2 >= DEFAULT_CAPACITY) { // Resize when the stack is 1/4 full and the new array would not have a size lower than the default capacity
            T[] newArray = (T[]) new Object[array.length / 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }

        T entry = array[size - 1];
        array[size - 1] = null;
        size--;
        return entry;
    }

    @Override
    public T peek() {
        if(isEmpty()) {
            throw new EmptyStackException();
        }

        return array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override // O(1)
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override // O(1)
    public int size() {
        return size;
    }

    @Override // O(n)
    public boolean contains(T anEntry) {
        if(isEmpty()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], anEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override // O(n)
    public int entries(T anEntry) {
        int entries = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], anEntry)) {
                entries++;
            }
        }
        return entries;
    }

    @Override // O(n)
    public int search(T anEntry) {
        if(isEmpty()) {
            return -1;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], anEntry)) {
               return size - 1 - i;
            }
        }

        return -1;
    }
} // end DynamicArrayStack
