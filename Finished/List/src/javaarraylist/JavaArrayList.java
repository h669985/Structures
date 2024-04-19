package javaarraylist;

import adt.ListADT;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An implementation of the ListADT interface using Java's own collection utilities
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class JavaArrayList<T> implements ListADT<T> {

    private final ArrayList<T> list;

    public JavaArrayList() {
        list = new ArrayList<>(0);
    }


    @Override
    public void add(T newEntry) {
        list.ensureCapacity(list.size() + 1);
        list.add(newEntry);
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if(newPosition < 1 || newPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        list.ensureCapacity(list.size() + 1);
        list.add(newPosition - 1, newEntry);
    }

    @Override
    public T remove(int givenPosition) {
        if(givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        list.trimToSize();
        list.ensureCapacity(list.size() + 1);
        return list.remove(givenPosition - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        if(givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }
        return list.set(givenPosition - 1, newEntry);
    }

    @Override
    public T getEntry(int givenPosition) {
        if(givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }
        return list.get(givenPosition - 1);
    }

    @Override
    public T[] toArray() {
        if(isEmpty()) {
            return (T[]) new Object[getLength()];
        } else {
            T[] array = (T[]) Array.newInstance(list.get(0).getClass(), getLength());
            for (int i = 0; i < getLength(); i++) {
                array[i] = list.get(i);
            }
            return array;
        }
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
} // end JavaArrayList
