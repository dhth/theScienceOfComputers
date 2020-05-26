package leetcode;

import CS61B.Lab6.UnionFind;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] res = new int[2];
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if (uf.connected(edges[i][0] - 1, edges[i][1] - 1)) {
                res[0] = edges[i][0];
                res[1] = edges[i][1];
            }
            uf.union(edges[i][0] - 1, edges[i][1] - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        //int[][] input = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        int[][] input = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[] ans = findRedundantConnection(input);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
