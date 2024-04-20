package javalinkedlist;

import adt.ListADT;

import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 * An implementation of the ListADT interface using Java's own collection utilities
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class JavaLinkedList<T> implements ListADT<T> {

    private final LinkedList<T> list;

    public JavaLinkedList() {
        list = new LinkedList<>();
    }


    @Override
    public void add(T newEntry) {
        list.add(newEntry);
    }

    @Override
    public void add(int newPosition, T newEntry) {
        list.add(newPosition - 1, newEntry);
    }

    @Override
    public T remove(int givenPosition) {
        return list.remove(givenPosition - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        return list.set(givenPosition - 1, newEntry);
    }

    @Override
    public T getEntry(int givenPosition) {
        return list.get(givenPosition - 1);
    }

    @Override
    public T[] toArray() {
        if (isEmpty()) {
            return (T[]) new Object[getLength()];
        }
        T[] array = (T[]) Array.newInstance(list.get(0).getClass(), getLength());
        for (int i = 0; i < getLength(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    @Override
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }

    @Override
    public int getLength() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
} // end JavaLinkedList