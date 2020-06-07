package CS61B.HW2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] grid;
    private WeightedQuickUnionUF uf;
    private int numOpenSites;
    private int gridEdge;

    public Percolation(int N) {
        grid = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            grid[i] = -1;
        }
        uf = new WeightedQuickUnionUF(N * N);
        numOpenSites = 0;
        gridEdge = N;
    }

    public static void main(String[] args) {

    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        int index = row * gridEdge + col;
        grid[index] = 0;
        if (row > 0) {
            //check top
            if (grid[index - gridEdge] == 0) {
                uf.union(index, index - gridEdge);
            }
        }
        if (col < gridEdge - 1) {
            //check right
            if (grid[index + 1] == 0) {
                uf.union(index, index + 1);
            }
        }
        if (row < gridEdge - 1) {
            //check bottom
            if (grid[index + gridEdge] == 0) {
                uf.union(index, index + gridEdge);
            }
        }
        if (col > 0) {
            //check left
            if (grid[index - 1] == 0) {
                uf.union(index, index - 1);
            }
        }
        numOpenSites += 1;
    }

    public boolean isOpen(int row, int col) {
        return grid[row * gridEdge + col] == 0;
    }

    public boolean isFull(int row, int col) {
        if (row == 0) {
            if (isOpen(row, col)) {
                return true;
            }
        } else {
            for (int i = 0; i < gridEdge; i++) {
                if (uf.connected(row * gridEdge + col, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        for (int i = 0; i < gridEdge; i++) {
            for (int j = gridEdge * (gridEdge - 1); j < gridEdge * gridEdge; j++) {
                if (uf.connected(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
