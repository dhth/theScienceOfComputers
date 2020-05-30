package CS61B.CLab7;

import java.util.NoSuchElementException;

/*  @author Josh Hug, with most code created by:
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class BST<Key extends Comparable<Key>> {
    /**
     * Private methods and variables follow. There's no need to read
     * any of this.
     */

    private Node root;             // root of CS61B.CLab7.BST

    /**
     * Initializes an empty CS61B.CLab7.BST.
     */
    public BST() {
    }

    /**
     * Returns the number of keys in this CS61B.CLab7.BST.
     *
     * @return the number of keys in this CS61B.CLab7.BST
     */
    public int size() {
        return size(root);
    }

    /**
     * Does this CS61B.CLab7.BST contain the given key?
     *
     * @param key the key
     * @return {@code true} if this CS61B.CLab7.BST contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return contains(root, key);
    }

    /**
     * Inserts the specified key into the CS61B.CLab7.BST.
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void add(Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls put() with a null key");
        root = add(root, key);
    }

    /**
     * Removes the specified key and its associated value from this CS61B.CLab7.BST
     * (if the key is in this CS61B.CLab7.BST).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void deleteTakingSuccessor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls deleteTakingSuccessor() with a null key");
        root = deleteTakingSuccessor(root, key);
    }

    public void deleteTakingRandom(Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls deleteTakingRandom() with a null key");
        root = deleteTakingRandom(root, key);
    }

    /**
     * Returns a random item from the CS61B.CLab7.BST.
     */
    public Key getRandomKey() {
        return getRandomNode(root).key;
    }

    /* Returns a tree with key deleted from the tree rooted at x. */
    private Node deleteTakingSuccessor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = deleteTakingSuccessor(x.left, key);
        else if (cmp > 0) x.right = deleteTakingSuccessor(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right); // x points at successor
            x.right = deleteMin(t.right); // successor points at right tree as if it had been deleted
            x.left = t.left; // successor points at left tree as it was
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteTakingRandom(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = deleteTakingRandom(x.left, key);
        else if (cmp > 0) x.right = deleteTakingRandom(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            boolean random = RandomGenerator.getRandomBoolean();
            if (random) { // use successor with 50% chance
                Node t = x;
                x = min(t.right); // x points at successor
                x.right = deleteMin(t.right); // successor points at right tree as if it had been deleted
                x.left = t.left; // successor points at left tree as it was
            } else { // use predecessor
                Node t = x;
                x = max(t.left); // x points at predecessor
                x.left = deleteMax(t.left); // predecessor points at left tree as if it had been deleted
                x.right = t.right; // predecessor points at right tree as it was
            }

        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Returns the smallest key in the CS61B.CLab7.BST.
     *
     * @return the smallest key in the CS61B.CLab7.BST
     * @throws NoSuchElementException if the CS61B.CLab7.BST is empty
     */
    private Key min() {
        if (isEmpty())
            throw new NoSuchElementException("calls min() with empty CS61B.CLab7.BST");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    /**
     * Returns the largest key in the CS61B.CLab7.BST.
     *
     * @return the largest key in the CS61B.CLab7.BST
     * @throws NoSuchElementException if the CS61B.CLab7.BST is empty
     */
    private Key max() {
        if (isEmpty())
            throw new NoSuchElementException("calls max() with empty CS61B.CLab7.BST");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    /**
     * Gets a random node in the tree.
     */
    private Node getRandomNode(Node t) {
        int leftSize = t.left == null ? 0 : t.left.size;
        int index = RandomGenerator.getRandomInt(t.size);
        if (index < leftSize) {
            return getRandomNode(t.left);
        } else if (index == leftSize) {
            return t;
        } else {
            return getRandomNode(t.right);
        }
    }

    private Node add(Node x, Key key) {
        if (x == null) return new Node(key, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = add(x.left, key);
        else if (cmp > 0) x.right = add(x.right, key);
        else ; // do nothing, key already exists
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Removes the smallest key and associated value from the CS61B.CLab7.BST.
     *
     * @throws NoSuchElementException if the CS61B.CLab7.BST is empty
     */
    private void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("CS61B.CLab7.BST underflow");
        root = deleteMin(root);
    }

    /**
     * Returns the root of a tree with the minimum of x deleted.
     * That is, if I am the minimum, return my right child!
     */
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the largest key and associated value from the CS61B.CLab7.BST.
     *
     * @throws NoSuchElementException if the CS61B.CLab7.BST is empty
     */
    private void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("CS61B.CLab7.BST underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    // return number of key-value pairs in CS61B.CLab7.BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    private boolean contains(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return false;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return contains(x.left, key);
        else if (cmp > 0) return contains(x.right, key);
        else return true;
    }

    /**
     * Returns true if this CS61B.CLab7.BST is empty.
     *
     * @return {@code true} if this CS61B.CLab7.BST is empty; {@code false} otherwise
     */
    private boolean isEmpty() {
        return size() == 0;
    }

    public double getAverageDepth() {
        int iPL = getInternalPathLength(root, 0);
        return iPL / (double) size();
    }

    private int getInternalPathLength(Node x, int level) {
        if (x == null) {
            return 0;
        } else {
            return level + getInternalPathLength(x.left, level + 1) +
                    getInternalPathLength(x.right, level + 1);
//                    level;
        }
    }

    private class Node {
        private Key key;           // sorted by key
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, int size) {
            this.key = key;
            this.size = size;
        }
    }
}
