package CS61B.HW2;

import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStatsRunner {
    public static void main(String[] args) {
        int N = 20;
        int T = 50;
        PercolationFactory pf = new PercolationFactory("wqu");
        Stopwatch watch1 = new Stopwatch();
        PercolationStats stats = new PercolationStats(N, T, pf);
        double weightedQUTime = watch1.elapsedTime();

        PercolationFactory pf2 = new PercolationFactory("qf");
        Stopwatch watch2 = new Stopwatch();
        PercolationStats stats2 = new PercolationStats(N, T, pf2);
        double qfTime = watch2.elapsedTime();

        System.out.println(stats.mean());
        System.out.println(stats.stddev());
        System.out.println(stats.confidenceLow());
        System.out.println(stats.confidenceHigh());
        System.out.println("Time taken:");
        System.out.println("\t- Weighted Quick Union: " + weightedQUTime + " " +
                "seconds.");
        System.out.println("\t- Quick Find: " + qfTime + " " +
                "seconds.");
    }
}
