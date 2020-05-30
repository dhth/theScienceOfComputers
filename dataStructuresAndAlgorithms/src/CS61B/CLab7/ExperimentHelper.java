package CS61B.CLab7;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /**
     * Returns the internal path length for an optimum binary search tree of
     * size N. Examples:
     * N = 1, OIPL: 0
     * N = 2, OIPL: 1
     * N = 3, OIPL: 2
     * N = 4, OIPL: 4
     * N = 5, OIPL: 6
     * N = 6, OIPL: 8
     * N = 7, OIPL: 10
     * N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        if (N == 1) {
            return 0;
        } else {
            int nNodes = 1;
            int levels = (int) Math.floor(Math.log(N) / Math.log(2));
            int iPL = 0;
            int nodesAtLevel;
            for (int i = 1; i <= levels; i++) {
                if (i < levels) {
                    nodesAtLevel = (int) Math.pow(2, i);
                } else {
                    nodesAtLevel = N - nNodes;
                }
                iPL += nodesAtLevel * i;
                nNodes += nodesAtLevel;
            }
            return iPL;
        }
    }

    /**
     * Returns the average depth for nodes in an optimal CS61B.CLab7.BST of
     * size N.
     * Examples:
     * N = 1, OAD: 0
     * N = 5, OAD: 1.2
     * N = 8, OAD: 1.625
     *
     * @return
     */
    public static double optimalAverageDepth(int N) {
        int iPL = optimalIPL(N);
        return iPL / (double) N;
    }

    public static void randomInsert(BST bst) {
        int randomNum = RandomGenerator.getRandomInt(50000000);
        while (true) {
            if (!bst.contains(randomNum)) {
                bst.add(randomNum);
                break;
            } else {
                randomNum = RandomGenerator.getRandomInt(50000000);
            }
        }
//        return bst;
    }

    public static void asymmetricalRandomDelete(BST bst) {
        bst.deleteTakingSuccessor(bst.getRandomKey());
//        return bst;
    }

    public static void symmetricalRandomDelete(BST bst) {
        bst.deleteTakingRandom(bst.getRandomKey());
//        return bst;
    }
}
