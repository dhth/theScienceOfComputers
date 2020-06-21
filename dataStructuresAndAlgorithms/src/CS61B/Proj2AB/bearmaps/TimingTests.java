package CS61B.Proj2AB.bearmaps;

import CS61B.CLab7.RandomGenerator;

public class TimingTests {
    public static void main(String[] args) {
        int numInsertions = 1000000;
        int[] randomNumbers = new int[numInsertions];
        for (int i = 0; i < numInsertions; i++) {
            randomNumbers[i] = RandomGenerator.getRandomInt(numInsertions);
        }

        NaiveMinPQ<Integer> naivePQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        System.out.println("Metrics for " + numInsertions + " insertions.");
        printStats(naivePQ, randomNumbers, "Naive PQ");
        printStats(arrayHeapMinPQ, randomNumbers, "ArrayMinHeapPQ");
    }

    public static void printStats(ExtrinsicMinPQ pq,
                                  int[] randomNumbers, String pqName) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < randomNumbers.length; i++) {
            pq.add(i, i);
        }
        for (int i = 0; i < 1000; i++) {
            pq.removeSmallest();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed for " + pqName
                + ": " + (end - start) / 1000.0 + " seconds.");
    }

}
