package CS61B.Lab7;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>, V> implements Map61B {
    public Node root;

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean contains = containsKey(root, (K) key);
        return contains;
    }

    private boolean containsKey(Node x, K searchKey) {
        if (x == null) {
            return false;
        }
        int cmp = searchKey.compareTo(x.key);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return containsKey(x.left, searchKey);
        } else {
            return containsKey(x.right, searchKey);
        }
    }

    @Override
    public Object get(Object key) {
        return get(root, (K) key);
    }

    private V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.value;
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size;
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (key == null)
            throw new IllegalArgumentException("calls put with a null key");
        // TODO if val == null
        root = put(root, (K) key, (V) value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = x.size + 1;
        return x;
    }

    @Override
    public Set keySet() {
        Set s = new LinkedHashSet();
        s = traverseTree(root, s);
        return s;
    }

    private Set traverseTree(Node x, Set s) {
        if (x == null) {
            return s;
        }
        s = traverseTree(x.left, s);

        s.add(x.key);

        s = traverseTree(x.right, s);
        return s;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        printInOrder(x.left);

        System.out.println(x.key + " -> " + x.value);

        printInOrder(x.right);
    }

    private Node getParent(Node parentNode, Node childNode, K key) {
        int cmp = key.compareTo(childNode.key);
        if (cmp == 0) {
            return parentNode;
        } else if (cmp < 0) {
            return getParent(childNode, childNode.left, key);
        } else {
            return getParent(childNode, childNode.right, key);
        }
    }

    public K getParentsKey(K key) {
        Node node = getParent(null, root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node parent(K key) {
        Node node = getParent(null, root, key);
        return node;
    }

    public int getSize(K key) {
        return getSize(root, key);
    }

    private int getSize(Node x, K key) {
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.size;
        } else if (cmp < 0) {
            return getSize(x.left, key);
        } else {
            return getSize(x.right, key);
        }
    }

    private Node getNode(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return getNode(x.left, key);
        } else {
            return getNode(x.right, key);
        }
    }

    @Override
    public Object remove(Object key) {
        K k = (K) key;
        Node node = getNode(root, k);
        if (node != null) {
            V removedValue = node.value;
            root = remove(root, k);
            return removedValue;
        }
        return null;
    }

    private Node remove(Node T, K key) {
        int cmp = key.compareTo(T.key);
        if (cmp < 0) {
            T.left = remove(T.left, key);
        } else if (cmp > 0) {
            T.right = remove(T.right, key);
        }
        // otherwise, we've found the node to be removed
        else if (T.left == null) {
            return T.right;
        } else if (T.right == null) {
            return T.left;
        } else {
            // remove node with two children
            T.right = swapSmallest(T.right, T);
        }
        T.size -= 1;
        return T;
    }

    private Node swapSmallest(Node T, Node R) {
        if (T.left == null) {
            //change R's properties
            R.key = T.key;
            R.value = T.value;
            return T.right;
        } else {
            T.left = swapSmallest(T.left, R);
            T.size -= 1;
            return T;
        }
    }

    @Override
    public Object remove(Object key, Object value) {
        K k = (K) key;
        Node node = getNode(root, k);
        if (node != null && node.value == value) {
            Node removedNode = remove(root, k);
            return removedNode.value;
        }
        return null;
    }

    public int heightOfTree() {
        return height(root, 0);
    }

    private int height(Node x, int currentHeight) {
        if (x == null) {
            return currentHeight - 1;
        }
        int leftHeight = height(x.left, currentHeight + 1);
        int rightHeight = height(x.right, currentHeight + 1);
        if (leftHeight >= rightHeight) {
            return leftHeight;
        }
        return rightHeight;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void forEach(Consumer action) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Spliterator spliterator() {
        throw new UnsupportedOperationException("Not supported");
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }
}
