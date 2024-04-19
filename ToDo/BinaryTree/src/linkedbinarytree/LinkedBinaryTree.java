package linkedbinarytree;

import adt.BinaryTreeADT;
import adt.QueueADT;
import adt.StackADT;

import linkedqueue.LinkedQueue;
import linkedstack.LinkedStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A linked binary tree implementation of the BinaryTreeADT interface
 * <p>
 * Is dependent on my Stack and Queue modules for the implementation of the iterator methods
 *
 * @author Jonas Hellevik
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    //------------------------------------------------------------------------------------
    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }

        public T getData()                  {return this.data;}
        // public void setData(T data)         {this.data = data;} // unused
        public Node<T> getLeft()            {return this.left;}
        public Node<T> getRight()           {return this.right;}
        public void setLeft(Node<T> left)   {this.left = left;}
        public void setRight(Node<T> right) {this.right = right;}
        public boolean hasLeft()            {return this.left != null;}
        public boolean hasRight()           {return this.right != null;}
        // public boolean isLeaf()             {return !this.hasLeft() && !this.hasRight();} // unused
    } // end Node<T>
    //------------------------------------------------------------------------------------

    private Node<T> root;

    public LinkedBinaryTree() {
        root = null;
    }

    public LinkedBinaryTree(T rootData) {
        root = new Node<>(rootData);
    }

    public LinkedBinaryTree(T rootData, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) {
        setTree(rootData, left, right);
    }

    @Override
    public int getSize() {
        return getSize(root);
    }

    private int getSize(Node<T> n) {
        if(n == null) {
            return 0;
        }
        return 1 + getSize(n.left) + getSize(n.right);
    }

    @Override
    public T getRootData() {
        if(root == null) {
            return null;
        }
        return root.getData();
    }

    @Override
    public int getHeight() {
        if(isEmpty()) {
            return 0;
        }
        return getHeight(root) - 1;
    }

    private int getHeight(Node<T> n) {
        if(n == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(n.left), getHeight(n.right));
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    private class PreorderIterator implements Iterator<T> {
        private final StackADT<Node<T>> stack;

        public PreorderIterator() {
            stack = new LinkedStack<>();
            if(!isEmpty()) {
                stack.push(root);
            } // end if
        } // end constructor

        @Override
        public boolean hasNext() {
            return  !stack.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<T> next = stack.pop();
            if(next.hasRight()) {
                stack.push(next.getRight());
            }

            if(next.hasLeft()) {
                stack.push(next.getLeft());
            }

            return next.data;
        } // end next()
    } // end PreorderIterator

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    /**
     * Solved by using a while loop approach where we start by pushing all possible left children to the stack
     * Then every call to next pops the stack, then checks if that node has a right child,
     * if so we call the left push to that node's right child before proceeding with returning the data in the node
     * that was popped.
     * In summary, we are always trying to find the leftmost node in the tree.
     */
    private class InorderIterator implements Iterator<T> {
        private final StackADT<Node<T>> stack;

        public InorderIterator() {
            stack = new LinkedStack<>();
            pushLeft(root);
        }

        /**
         * Pushes all the left sub-nodes to the stack of the initial node
         *
         * @param node The initial node
         */
        private void pushLeft(Node<T> node) {
            while(node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<T> next = stack.pop();

            if(next.hasRight()) {
                pushLeft(next.getRight());
            }

            return next.getData();
        } // end next()
    } // end InorderIterator

    @Override
    public Iterator<T> getPostOrderIterator() {
        return new PostOrderIterator();
    }

    private class PostOrderIterator implements Iterator<T> {
        private final StackADT<Node<T>> firstStack;
        private final StackADT<Node<T>> secondStack;

        public PostOrderIterator() {
            firstStack = new LinkedStack<>();
            secondStack = new LinkedStack<>();
            if(!isEmpty()) {
                firstStack.push(root);
            } // end if
        } // end constructor

        @Override
        public boolean hasNext() {
            return !firstStack.isEmpty() || !secondStack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (secondStack.isEmpty()) {
                while (!firstStack.isEmpty()) {
                    Node<T> current = firstStack.pop();
                    secondStack.push(current);

                    if (current.hasLeft()) {
                        firstStack.push(current.getLeft());
                    }

                    if (current.hasRight()) {
                        firstStack.push(current.getRight());
                    }

                } // end while
            } // end if

            return secondStack.pop().getData();
        } // end next()

    } // end PostOrderIterator

    @Override
    public Iterator<T> getLevelIterator() {
        return new LevelorderIterator();
    }

    private class LevelorderIterator implements Iterator<T> {
        private final QueueADT<Node<T>> queue;

        public LevelorderIterator() {
            queue = new LinkedQueue<>();
            if (!isEmpty()) {
                queue.enqueue(root);
            } // end if
        } // end constructor

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            Node<T> next = queue.dequeue();

            if(next.hasLeft()) {
                queue.enqueue(next.getLeft());
            }

            if(next.hasRight()) {
                queue.enqueue(next.getRight());
            }

            return next.getData();
        }
    }

    @Override
    public void setTree(T rootData) {
        root = new Node<>(rootData);
    }

    @Override @SuppressWarnings("rawtypes")
    public void setTree(T rootData, BinaryTreeADT<T> left, BinaryTreeADT<T> right) {
        root = new Node<>(rootData);

        if (left != null) {
            if(left instanceof LinkedBinaryTree<T>) {
                root.setLeft(((LinkedBinaryTree)left).root);

            } else {
                throw new ClassCastException();

            }
        }

        if(right != null){
            if(right instanceof LinkedBinaryTree<T>) {
                root.setRight(((LinkedBinaryTree)right).root);

            } else {
                throw new ClassCastException();

            } // end else
        } // end if
    } // end setTree(...)

} // end LinkedBinaryTree