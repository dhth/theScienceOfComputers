package leetcode;

import CS61B.Lab6.UnionFind;

/**
 * https://leetcode.com/problems/friend-circles/
 */

public class FriendCircles {
    public static int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        int startIndex = 1;
        for (int i = 0; i < n; i++) {
            for (int j = startIndex; j < n; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
            startIndex += 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                res += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int[][] matrix = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(matrix));
    }
}
