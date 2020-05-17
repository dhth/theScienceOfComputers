package leetcode;

import CS61B.Lab6.UnionFind;

/**
 * https://leetcode.com/problems/regions-cut-by-slashes
 */
public class RegionsCutBySlashes {


    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;

        UnionFind uf = new UnionFind(n * n * 4);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);

                int root = 4 * (i * n + j);
                if (c != '\\') {
                    uf.union(root + 1, root);
                    uf.union(root + 3, root + 2);
                }
                if (c != '/') {
                    uf.union(root + 2, root);
                    uf.union(root + 3, root + 1);
                }

                //check down
                if (i < n - 1) {
                    uf.union(root + 4 * n, root + 3);
                }

                //check right
                if (j < n - 1) {
                    uf.union(root + 4 + 1, root + 2);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n * n * 4; i++) {
            if (uf.find(i) == i) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] grid = {"/\\", "\\/"};
        int solution = regionsBySlashes(grid);
        System.out.println(solution);
    }
}
