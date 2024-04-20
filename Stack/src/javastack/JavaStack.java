package javastack;

import adt.StackADT;

import java.util.Objects;
import java.util.Stack;

/**
 * An implementation of the StackADT interface using Java's own collection utilities
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

public class JavaStack<T> implements StackADT<T> {

    private Stack<T> stack;

    public JavaStack() {
        stack = new Stack<>();
    }


    @Override
    public void push(T newEntry) {
        stack.push(newEntry);
    }

    @Override // Java's own pop throws EmptyStackException
    public T pop() {
        return stack.pop();
    }

    @Override // Java's own pop throws EmptyStackException
    public T peek() {
        return stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override // O(1)
    public void clear() {
        stack = new Stack<>();
    }

    @Override // O(1)
    public int size() {
        return stack.size();
    }

    @Override // O(n)
    public boolean contains(T anEntry) {
        return stack.contains(anEntry);
    }

    @Override // O(n)
    public int entries(T anEntry) {
        if(isEmpty()) {
            return -1;
        }

        int entries = 0;
        for(T entry : stack) {
            if(Objects.equals(entry, anEntry)) {
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

        int result = stack.search(anEntry);

        if(result == -1) {
            return result;
        }

        return result - 1;
    }
} // end JavaStack
