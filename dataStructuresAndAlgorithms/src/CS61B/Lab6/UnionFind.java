package CS61B.Lab6;

import java.util.LinkedList;
import java.util.ListIterator;

public class UnionFind {

    private int[] sets;
    private int DEFAULT_SET_VALUE = -1;
    private int size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        sets = new int[n];
        for (int i = 0; i < n; i++) {
            sets[i] = DEFAULT_SET_VALUE;
        }
        size = n;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= size || vertex < 0) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int rootIndex = find(v1);
        return -1 * sets[rootIndex];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return sets[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects v1 to root of the set of which v2 is a part of */
    private void connect(int v1, int v2) {
        int rootOfLargerTree = find(v2);
        int rootOfSmallerTree = find(v1);
        if (rootOfLargerTree != rootOfSmallerTree) {
            int sizeOfSmallerTree = sizeOf(rootOfSmallerTree);
            sets[rootOfSmallerTree] = rootOfLargerTree;
            sets[rootOfLargerTree] -= sizeOfSmallerTree;
        }
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size1 > size2) {
            // connect v2 to root of v1's set
            connect(v2, v1);
        } else {
            // connect v2 to root of v2's set
            connect(v1, v2);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if (sets[vertex] < 0) {
            //vertex is a root node
            return vertex;
        }

        //immediate parent is root, no need for path compression
        if (sets[sets[vertex]] < 0){
            return sets[vertex];
        }

        //compress path
        int currentIndex = vertex;
        LinkedList<Integer> chain = new LinkedList<>();
        while (sets[currentIndex] >= 0) {
            chain.add(currentIndex);
            currentIndex = sets[currentIndex];
        }
        // currentIndex is now the root vertex
        ListIterator iterator = chain.listIterator(chain.size());
        while (iterator.hasPrevious()) {
            sets[(int) iterator.previous()] = currentIndex;
        }

        return currentIndex;
    }

}
