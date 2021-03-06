package CS61B.HW2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation implements PercolationParent {
    private int[] grid;
    private WeightedQuickUnionUF uf;
    private int numOpenSites;
    private int gridEdge;
    private int topSiteIndex;
    private int bottomSiteIndex;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N * N];
        for (int i = 0; i < N * N; i++) {
            grid[i] = -1;
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        topSiteIndex = N * N;
        bottomSiteIndex = N * N + 1;
        for (int i = 0; i < N; i++) {
            uf.union(i, topSiteIndex);
        }
        numOpenSites = 0;
        gridEdge = N;
    }

    public static void main(String[] args) {

    }

    @Override
    public void open(int row, int col) {
        if (row < 0 || row >= gridEdge) {
            throw new IndexOutOfBoundsException("Row out of bound");
        }
        if (col < 0 || col >= gridEdge) {
            throw new IndexOutOfBoundsException("Col out of bound");
        }
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
        if (row == gridEdge - 1) {
            uf.union(row * gridEdge + col, bottomSiteIndex);
        }
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= gridEdge) {
            throw new IndexOutOfBoundsException("Row out of bound");
        }
        if (col < 0 || col >= gridEdge) {
            throw new IndexOutOfBoundsException("Col out of bound");
        }
        return grid[row * gridEdge + col] == 0;
    }

    private boolean isConnectedToTopSite(int row, int col) {
        return uf.connected(row * gridEdge + col, topSiteIndex);
    }

    @Override
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= gridEdge) {
            throw new IndexOutOfBoundsException("Row out of bound");
        }
        if (col < 0 || col >= gridEdge) {
            throw new IndexOutOfBoundsException("Col out of bound");
        }
        if (row == 0) {
            return (isOpen(row, col));
        }
        return isConnectedToTopSite(row, col);
    }

    @Override
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    @Override
    public boolean percolates() {
        return uf.connected(topSiteIndex, bottomSiteIndex);
    }
}
