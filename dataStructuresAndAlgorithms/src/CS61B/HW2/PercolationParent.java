package CS61B.HW2;

public interface PercolationParent {
    public void open(int row, int col);

    public boolean isOpen(int row, int col);

    public boolean isFull(int row, int col);

    public int numberOfOpenSites();

    public boolean percolates();
}
