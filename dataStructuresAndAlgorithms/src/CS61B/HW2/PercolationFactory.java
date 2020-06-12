package CS61B.HW2;

public class PercolationFactory {
    private String underlyingDS;

    public PercolationFactory(String ds) {
        underlyingDS = ds;
    }

    public PercolationParent make(int N) {
        if (underlyingDS.equalsIgnoreCase("qf")) {
            return new PercolationWithQuickFind(N);
        }
        return new Percolation(N);
    }
}
