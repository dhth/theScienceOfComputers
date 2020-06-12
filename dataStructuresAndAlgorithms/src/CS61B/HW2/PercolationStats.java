package CS61B.HW2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] openSitesArray;
    private int numExperiments;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal N");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("Illegal T");
        }
        openSitesArray = new double[T];
        numExperiments = T;
        PercolationParent p;
        int randomRow;
        int randomCol;
        for (int i = 0; i < T; i++) {
            p = pf.make(N);
            while (!p.percolates()) {
                randomRow = StdRandom.uniform(0, N);
                randomCol = StdRandom.uniform(0, N);
                while (p.isOpen(randomRow, randomCol)) {
                    randomRow = StdRandom.uniform(0, N);
                    randomCol = StdRandom.uniform(0, N);
                }
                p.open(randomRow, randomCol);
                openSitesArray[i] = (double) p.numberOfOpenSites() / (N * N);
            }
        }
    }

    public double mean() {
        return StdStats.mean(openSitesArray);
    }

    public double stddev() {
        return Math.sqrt(StdStats.stddev(openSitesArray));
    }

    public double confidenceLow() {
        return (mean() - ((1.96 * stddev()) / Math.sqrt(numExperiments)));
    }

    public double confidenceHigh() {
        return (mean() + ((1.96 * stddev()) / Math.sqrt(numExperiments)));
    }
}
