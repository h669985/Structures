package maxheap;

import adt.MaxHeapADT;

import java.util.Arrays;

/**
 * An array implementation of the ADT interface MaxHeap
 * <p>
 * This code was mostly copied and translated from my professor, I have only made minor edits.
 * </p>
 * @author Jonas Hellevik
 * @version 1.0
 */

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapADT<T> {
    private static final int DEFAULT_CAPACITY = 100;

    private T[] heap;
    private int size;

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        heap = (T[]) new Comparable[capacity + 1];
        size = 0;
    }

    public MaxHeap(T[] elements) {
        this(elements.length);
        size = elements.length;

        System.arraycopy(elements, 0, heap, 1, size);

        for(int node = size / 2; node > 0; node--)
            repairDown(node);
    }

    private void ensureCapacity() {
        if (size == heap.length - 1) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        } else if (size < heap.length / 16) {
            heap = Arrays.copyOf(heap, heap.length / 2);
        }
    }

    private boolean hasLeftChild(int node) {
        return node * 2 <= size;
    }

    private boolean hasRightChild(int node) {
        return node * 2 + 1 <= size;
    }

    /**
     * Repairs the subtree to ensure max heap order is followed
     * <p>
     * We have a subtree with a root in position node.
     * The subtree is a heap except that the root might not
     * fulfill the requirement to be greater than its children.
     * This method repairs the subtree to ensure that the root is greater
     * than its children, thus making the subtree a heap.
     * </p>
     * @param node index position of the subtree to repair down from
     */
    private void repairDown(int node) {
        boolean done = false;

        T root = heap[node];
        int leftChild = node * 2;

        while(!done && hasLeftChild(node)) {

            int maxChild = leftChild;
            if(hasRightChild(node)) {
                if(heap[leftChild].compareTo(heap[leftChild + 1]) < 0) {
                    maxChild++;
                }
            }

            if(root.compareTo(heap[maxChild]) < 0) {
                heap[node] = heap[maxChild];
                node = maxChild;
                leftChild = 2 * node;
            } else {
                done = true;
            } // end if else
        } // end while
        heap[node] = root;
    } // end repairDown

    @Override
    public void add(T newElement) {
        ensureCapacity();

        int newPosition = size + 1;
        int parent = newPosition / 2;

        while(parent > 0 && heap[parent].compareTo(newElement) < 0) {
            heap[newPosition] = heap[parent];
            newPosition = parent;
            parent = newPosition / 2;
        }

        heap[newPosition] = newElement;
        size++;
    }

    @Override
    public T removeMax() {
        T root = null;

        if(!isEmpty()) {
            root = heap[1];
            heap[1] = heap[size];
            size--;
            repairDown(1);
        }

        return root;
    }

    @Override
    public T findMax() {
        T root = null;

        if (!isEmpty()) {
            root = heap[1];
        }

        return root;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(heap, null);
        size = 0;
    }
} // end MaxHeap