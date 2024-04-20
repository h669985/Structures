package linkeddeque;

import adt.EmptyQueueException;
import adt.DequeADT;

/**
 * A linked deque implementation of the DequeADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class LinkedDeque<T> implements DequeADT<T> {
    //---------------------------------------------------
    private class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }
    //---------------------------------------------------

    private Node head;
    private Node tail;

    /**
     * Creates an empty double linked queue
     */
    public LinkedDeque() {
        head = null;
        tail = null;
    }

    @Override
    public void addToFront(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = head;

        if(isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }

        head = newNode;
    }

    @Override
    public void addToBack(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.prev = tail;

        if(isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
    }

    @Override
    public T removeFront() {
        T data = getFront();

        head = head.next;

        if(head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        return data;
    }

    @Override
    public T removeBack() {
        T data = getBack();

        tail = tail.prev;

        if(tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    @Override
    public T getFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        } else {
            return head.data;
        }
    }

    @Override
    public T getBack() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        } else {
            return tail.data;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public void clear() {
        while(!isEmpty()) {
            head = head.next;
            tail = tail.prev;
        }
    }
} // end LinkedDeque