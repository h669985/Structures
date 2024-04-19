package linkedqueue;

import adt.EmptyQueueException;
import adt.QueueADT;

/**
 * A linked queue implementation of the QueueADT interface
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class LinkedQueue<T> implements QueueADT<T> {
    //---------------------------------------------------
    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }
    //---------------------------------------------------

    private Node head;
    private Node tail;

    /**
     * Constructs an empty linked queue
     */
    public LinkedQueue() {
        head = null;
        tail = null;
    }

    @Override
    public void enqueue(T newEntry) {
        if(isEmpty()) {
            head = new Node(newEntry);
            tail = head;
        } else {
            tail.next = new Node(newEntry);
            tail = tail.next;
        }
    }

    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        } else {
            T data = head.data;
            head = head.next;
            return data;
        }
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
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }
} // end LinkedQueue
