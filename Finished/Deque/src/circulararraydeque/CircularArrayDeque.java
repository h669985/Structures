    package circulararraydeque;

    import adt.DequeADT;
    import adt.EmptyQueueException;

    import java.util.Arrays;

    /**
     * A dynamic circular array deque implementation of the DequeADT interface
     *
     * @author Jonas Hellevik
     * @version 1.0
     */

    @SuppressWarnings("unchecked")
    public class CircularArrayDeque<T> implements DequeADT<T> {
        private static final int DEFAULT_CAPACITY = 16;

        private T[] deque;
        private int frontIndex;
        private int backIndex;

        /**
         * Constructs an empty deque with an initial capacity of 16
         */
        public CircularArrayDeque() {
            this(DEFAULT_CAPACITY + 1);
        }

        public CircularArrayDeque(int capacity) {
            deque = (T[]) new Object[capacity + 1];
            frontIndex = 0;
            backIndex = capacity;
        }

        private int onePlusModN(int index) {
            return (index + 1) % deque.length;
        }

        private int oneMinusModN(int index) {
            return (index - 1 + deque.length) % deque.length;
        }

        /**
         * Checks if the deque is full
         *
         * @return true if the deque is full
         */
        private boolean isFull() {
            return onePlusModN(backIndex) == oneMinusModN(frontIndex);
        }

        /**
         * Checks if the deque is 1/16th full
         *
         * @return true if the deque is less than 1/16th full
         */
        private boolean isOneSixteenthFull() {
            int numElements = (backIndex - frontIndex + deque.length) % deque.length;
            return numElements < deque.length / 16;
        }

        /**
         * Doubles the size of the deque if it is full or decreases it by half if it is 1/16th full
         */
        private void ensureCapacity() {
            if(isFull()) {
                T[] oldDeque = deque;
                int oldCapacity = oldDeque.length;
                int newCapacity = oldCapacity * 2;

                deque = (T[]) new Object[newCapacity];
                for(int i = 0; i < oldCapacity - 1; i++) {
                    deque[i] = oldDeque[frontIndex];
                    frontIndex = (frontIndex + 1) % oldCapacity;
                }
                frontIndex = 0;
                backIndex = oldCapacity - 1;
                // System.out.print(" m "); // Debug code

            }  else if(isOneSixteenthFull()) {
                T[] oldDeque = deque;
                int oldCapacity = oldDeque.length;
                int newCapacity = oldCapacity / 2;
                int elementsToCopy = (backIndex - frontIndex + oldCapacity) % oldCapacity;

                deque = (T[]) new Object[newCapacity];
                for(int i = 0; i <= elementsToCopy; i++) {
                    deque[i] = oldDeque[frontIndex];
                    frontIndex = (frontIndex + 1) % oldCapacity;
                }
                frontIndex = 0;
                backIndex = (frontIndex + elementsToCopy) % newCapacity;
                // System.out.print(" d "); // Debug code
            }
        }

        @Override
        public void addToFront(T newEntry) {
            ensureCapacity();
            frontIndex = oneMinusModN(frontIndex);
            deque[frontIndex] = newEntry;
        }

    @Override
    public void addToBack(T newEntry) {
        ensureCapacity();
        backIndex = onePlusModN(backIndex);
        deque[backIndex] = newEntry;
    }

    @Override
    public T removeFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        T entry = deque[frontIndex];
        deque[frontIndex] = null;
        frontIndex = onePlusModN(frontIndex);
        return entry;
    }

    @Override
    public T removeBack() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        T entry = deque[backIndex];
        deque[backIndex] = null;
        backIndex = oneMinusModN(backIndex);
        return entry;
    }

    @Override
    public T getFront() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return deque[frontIndex];
    }

    @Override
    public T getBack() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }
        return deque[backIndex];
    }

    @Override
    public boolean isEmpty() {
        return frontIndex == onePlusModN(backIndex);
    }

    @Override
    public void clear() {
        Arrays.fill(deque, null);
        frontIndex = 0;
        backIndex = deque.length - 1;
    }
} // end CircularArrayDeque