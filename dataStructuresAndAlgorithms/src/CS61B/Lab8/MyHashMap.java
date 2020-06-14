package CS61B.Lab8;

import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int DEFAULT_INITIAL_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int RESIZING_FACTOR = 2;
    private int numBuckets;
    private double loadFactor;
    private int size;
    private ArrayList<MyHashMapNodeList<K, V>> hashArray;

    public MyHashMap() {
        numBuckets = DEFAULT_INITIAL_SIZE;
        loadFactor = DEFAULT_LOAD_FACTOR;
        size = 0;
        hashArray = new ArrayList<MyHashMapNodeList<K, V>>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            hashArray.add(new MyHashMapNodeList<>());
        }
    }

    public MyHashMap(int initialSize) {
        numBuckets = initialSize;
        loadFactor = DEFAULT_LOAD_FACTOR;
        size = 0;
        hashArray = new ArrayList<MyHashMapNodeList<K, V>>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            hashArray.add(new MyHashMapNodeList<>());
        }
    }

    public MyHashMap(int initialSize, double lf) {
        numBuckets = initialSize;
        loadFactor = lf;
        size = 0;
        hashArray = new ArrayList<MyHashMapNodeList<K, V>>(numBuckets);
    }

    public double getCurrentLoad() {
        return (double) size / numBuckets;
    }

    @Override
    public void clear() {
        size = 0;
        hashArray = new ArrayList<MyHashMapNodeList<K, V>>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            hashArray.add(new MyHashMapNodeList<>());
        }
    }

    @Override
    public boolean containsKey(K key) {
        int bucketToPutIn = Math.floorMod(key.hashCode(), numBuckets);
        int indexOfItem = hashArray.get(bucketToPutIn).getIndex(key);
        return (indexOfItem != -1);
    }

    @Override
    public V get(K key) {
        int bucketToPutIn = Math.floorMod(key.hashCode(), numBuckets);
        int indexOfItem = hashArray.get(bucketToPutIn).getIndex(key);
        if (indexOfItem != -1) {
            return hashArray.get(bucketToPutIn).list.get(indexOfItem).getValue();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int newNumBuckets = numBuckets * RESIZING_FACTOR;
        MyHashMap<K, V> newMap = new MyHashMap<>(newNumBuckets);
        for (int i = 0; i < hashArray.size(); i++) {
            for (MyHashMapNode<K, V> node : hashArray.get(i).list) {
                newMap.put(node.key, node.value);
            }
        }
        hashArray = newMap.hashArray;
        numBuckets = newNumBuckets;
    }

    @Override
    public void put(K key, V value) {
        //if load factor is above the threshold, resize
        //else, find bucket from hashcode
        int bucketToPutIn = Math.floorMod(key.hashCode(), numBuckets);
        int indexOfItem = hashArray.get(bucketToPutIn).getIndex(key);
        if (indexOfItem != -1) {
            //change the value of the item
            hashArray.get(bucketToPutIn).list.get(indexOfItem).setValue(value);
        } else {
            //add new item to linked list
            hashArray.get(bucketToPutIn).list.add(new MyHashMapNode<>(key, value));
            size += 1;
            //check if current load factor exceeds threshold
            if (getCurrentLoad() > loadFactor)
                resize();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < hashArray.size(); i++) {
            for (MyHashMapNode<K, V> node : hashArray.get(i).list) {
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        V removedValue = removeItem(key);
        return removedValue;
    }

    private V removeItem(K searchKey) {
        int bucket = Math.floorMod(searchKey.hashCode(), numBuckets);
        int i = 0;
        V removedValue;
        LinkedList<MyHashMapNode<K, V>> list = hashArray.get(bucket).list;
        for (MyHashMapNode<K, V> node : list) {
            if (node.key.equals(searchKey)) {
                removedValue = node.value;
                list.remove(i);
                size -= 1;
                return removedValue;
            }
            i += 1;
        }
        return null;
    }

    private V removeItem(K searchKey, V searchValue) {
        int bucket = Math.floorMod(searchKey.hashCode(), numBuckets);
        int i = 0;
        V removedValue;
        LinkedList<MyHashMapNode<K, V>> list = hashArray.get(bucket).list;
        for (MyHashMapNode<K, V> node : list) {
            if (node.key.equals(searchKey) && node.value.equals(searchValue)) {
                removedValue = node.value;
                list.remove(i);
                size -= 1;
                return removedValue;
            }
            i += 1;
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        V removedValue = removeItem(key, value);
        return removedValue;
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return set.iterator();
    }

    private class MyHashMapNodeList<K, V> {
        private LinkedList<MyHashMapNode<K, V>> list;

        public MyHashMapNodeList() {
            list = new LinkedList<MyHashMapNode<K, V>>();
        }

        public int getIndex(K searchKey) {
            int i = 0;
            for (MyHashMapNode node : list) {
                if (node.key.equals(searchKey)) {
                    return i;
                }
                i += 1;
            }
            return -1;
        }
    }

    private class MyHashMapNode<K, V> {
        private K key;
        private V value;

        public MyHashMapNode() {
        }

        public MyHashMapNode(K k, V val) {
            key = k;
            value = val;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V val) {
            value = val;
        }
    }
}
