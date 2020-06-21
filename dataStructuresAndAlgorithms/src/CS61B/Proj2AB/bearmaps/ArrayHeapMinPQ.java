package CS61B.Proj2AB.bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    public T[] itemArray;
    private double[] priorityArray;
    private int size;
    private int capacity;
    private final int CAPACITY_DEFAULT = 10;
    private final double upperUsageThreshold = 0.75;
    private final double lowerUsageThreshold = 0.25;
    private final double RESIZE_FACTOR = 2.0;
    private HashMap<T, Integer> itemToIndexHashMap;

    public ArrayHeapMinPQ() {
        itemArray = (T[]) new Object[CAPACITY_DEFAULT];
        priorityArray = new double[CAPACITY_DEFAULT];
        capacity = CAPACITY_DEFAULT;
        size = 0;
        itemToIndexHashMap = new HashMap<>();
    }

    private double getCurrentUsageFactor() {
        return (double) size / capacity;
    }

    private void resize(int newCapacity) {
        T[] newItemArray = (T[]) new Object[newCapacity];
        double[] newPriorityArray = new double[newCapacity];
        System.arraycopy(itemArray, 1, newItemArray, 1, size + 1);
        System.arraycopy(priorityArray, 1, newPriorityArray, 1, size + 1);
        capacity = newCapacity;
        itemArray = newItemArray;
        priorityArray = newPriorityArray;
    }

    public static void main(String[] args) {

    }

    private int parent(int index) {
        return index / 2;
    }

    public void swapPlaces(int index1, int index2) {
        T item1 = itemArray[index1];
        T item2 = itemArray[index2];
        double priority1 = priorityArray[index1];
        itemArray[index1] = itemArray[index2];
        priorityArray[index1] = priorityArray[index2];
        itemArray[index2] = item1;
        priorityArray[index2] = priority1;
        //change values in hash map
        itemToIndexHashMap.put(item1, index2);
        itemToIndexHashMap.put(item2, index1);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        itemArray[size + 1] = item;
        priorityArray[size + 1] = priority;
        itemToIndexHashMap.put(item, size + 1);

        if (size == 0) {
            size += 1;
            return;
        }

        swimUp(size + 1);

        //resize arrays if usage above threshold factor
        if (getCurrentUsageFactor() >= upperUsageThreshold) {
            resize((int) (capacity * RESIZE_FACTOR));
        }
        size += 1;
    }

    private void swimUp(int index) {
        int currentIndex = index;
        int parentIndex = parent(currentIndex);
        while (Double.compare(priorityArray[currentIndex],
                priorityArray[parentIndex]) < 0
                && parentIndex != 0) {
            swapPlaces(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parent(currentIndex);
            if (parentIndex == 0) {
                break;
            }
        }
    }

    @Override
    public boolean contains(T item) {
        return itemToIndexHashMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return itemArray[1];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (itemToIndexHashMap.containsKey(item) == false) {
            throw new NoSuchElementException();
        } else {
            int index = itemToIndexHashMap.get(item);
            double existingPriority = priorityArray[index];
            priorityArray[index] = priority;
            if (priority < existingPriority) {
                swimUp(index);
            } else if (priority > existingPriority) {
                swimDown(index);
            } else {
                return;
            }
        }
    }

    public int findSuccessor(int index) {
        int leftChildIndex = 2 * index;
        int rightChildIndex = leftChildIndex + 1;
        if (leftChildIndex > size) {
            // no children, heap property satisfied
            return -1;
        } else {
            double indexPriority = priorityArray[index];
            double leftPriority = priorityArray[leftChildIndex];
            double rightPriority = priorityArray[rightChildIndex];
            if (rightChildIndex > size) {
                //only left child
                if (Double.compare(leftPriority,
                        indexPriority) >= 0) {
                    //left child is larger, heap property satisfied
                    return -1;
                } else {
                    //left child is smaller, swim down
                    return leftChildIndex;
                }
            } else {
                //has 2 children
                if (Double.compare(leftPriority,
                        indexPriority) >= 0 &&
                        Double.compare(rightPriority,
                                indexPriority) >= 0) {
                    //both children are larger or equal, heap property satisfied
                    return -1;
                } else {
                    //need to swim down
                    if (Double.compare(leftPriority,
                            rightPriority) > 0) {
                        //left child is larger, swim down right side
                        return rightChildIndex;
                    } else {
                        //left child is smaller, swim down left side
                        return leftChildIndex;
                    }
                }
            }
        }
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        //remove the first node in the array and replace it
        //with the last node
        T smallestItem = itemArray[1];
        itemArray[1] = itemArray[size];
        itemArray[size] = null;
        priorityArray[1] = priorityArray[size];
        itemToIndexHashMap.remove(smallestItem);
        size -= 1;
        //swim down
        swimDown(1);

        //resize arrays if usage below threshold
        if (getCurrentUsageFactor() <= lowerUsageThreshold) {
            resize((int) (capacity / RESIZE_FACTOR));
        }
        return smallestItem;
    }

    private void swimDown(int index) {
        int currentIndex = index;
        int successorIndex = findSuccessor(currentIndex);
        while (successorIndex != -1) {
            swapPlaces(currentIndex, successorIndex);
            currentIndex = successorIndex;
            successorIndex = findSuccessor(currentIndex);
        }
    }
}
