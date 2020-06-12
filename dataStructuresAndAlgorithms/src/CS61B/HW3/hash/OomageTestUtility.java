package CS61B.HW3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] bucketSizes = new int[M];
        int bucketNum;
        for (Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0X7FFFFFF) % M;
            bucketSizes[bucketNum] += 1;
        }
        double upperLimit = oomages.size() / (double) 2.5;
        double lowerLimit = oomages.size() / (double) 50;
        for (int i = 0; i < M; i++) {
            if (bucketSizes[i] >= upperLimit || bucketSizes[i] <= lowerLimit) {
                return false;
            }
        }
        return true;
    }
}
