package linkedlist;

import adt.ListADT;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * An implementation of the ListADT interface using a linked list
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class LinkedList<T> implements ListADT<T> {
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

    private Node head;
    private int size;

    /**
     * Creates an empty linked list
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public void add(T newEntry) {
        if (isEmpty()) {
            head = new Node(newEntry);
        } else {
            Node newnode = getNodeAt(getLength());
            newnode.next = new Node(newEntry);
        }
        size++;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        if(newPosition < 1 || newPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(newEntry);

        if (newPosition == 1) {
            newNode.next = head;
            head = newNode;

        } else {
            Node nodeBefore = getNodeAt(newPosition - 1);
            newNode.next = nodeBefore.next;
            nodeBefore.next = newNode;
        } // end else
        size++;
    } // end method

    @Override
    public T remove(int givenPosition) {
        if(givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        T removedData = null;
        if(givenPosition == 1) {
            removedData = head.data;
            head = head.next;

        } else {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            removedData = nodeBefore.next.data;
            nodeBefore.next = nodeBefore.next.next;

        } // end else
        size--;
        return removedData;
    } // end method

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        if(givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        Node node = getNodeAt(givenPosition);
        T replacedEntry = node.data;
        node.data = newEntry;
        return replacedEntry;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition < 1 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }



        return getNodeAt(givenPosition).data;
    }

    @Override
    public T[] toArray() {
        if (isEmpty()) {
            return (T[]) new Object[getLength()];
        } else {
            T[] array = (T[]) Array.newInstance(head.data.getClass(), getLength());

            int i = 0;
            Node current = head;
            while (current != null) {
                array[i] = current.data;
                i++;

                current = current.next;
            }
            return array;
        }
    }

    @Override
    public boolean contains(T anEntry) {
        if(isEmpty()) {
            return false;
        }

        Node current = head;
        while(current != null) {
            if(Objects.equals(current.data, anEntry)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns a reference to the node at a given position. Precondition: (The chain
     * is not empty) and (1 <= givenPosition <= numberOfEntries).
     *
     * @param givenPosition the position of the node in the chain
     * @return The node at the given position
     */
    private Node getNodeAt(int givenPosition) {
        Node currentNode = head;
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

} // end LinkedList
