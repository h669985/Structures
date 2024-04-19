package linkedstack;

import adt.StackADT;

import java.util.EmptyStackException;
import java.util.Objects;

/**
 * An implementation of the StackADT interface using linked nodes
 *
 * @author Jonas Hellevik
 * @version 2.0
 */

public class LinkedStack<T> implements StackADT<T> {
    //---------------------------------------------------
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }
    //---------------------------------------------------

    private Node top;

    /**
     * Creates a new empty linked stack
     */
    public LinkedStack() {
        top = null;
    }

    @Override
    public void push(T newEntry) {
            Node newNode = new Node(newEntry);
            newNode.next = top;
            top = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T data = top.data;
        top = top.next;
        return data;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override //O(1)
    public void clear() {
        top = null; // Let garbage collection handle dropped nodes
    }

    @Override // O(n)
    public int size() {
        int size = 0;
        Node current = top;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    @Override // O(n)
    public boolean contains(T anEntry) {
        if(top == null) {
            return false;
        }

        Node current = top;
        while (current != null) {
            if (Objects.equals(current.data, anEntry)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override // O(n)
    public int entries(T anEntry) {
        int entries = 0;
        Node current = top;
        while (current != null) {
            if (Objects.equals(current.data, anEntry)) {
                entries++;
            }
            current = current.next;
        }
        return entries;
    }

    @Override // O(n)
    public int search(T anEntry) {
        if(top == null) {
            return -1;
        }

        int index = 0;
        Node current = top;
        while (current != null) {
            if (Objects.equals(current.data, anEntry)) {
                return index;
            }
            index++;
            current = current.next;
        }

        return -1;
    }
} // end LinkedStack