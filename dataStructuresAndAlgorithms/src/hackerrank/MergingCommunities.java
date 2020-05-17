package hackerrank;

import CS61B.Lab6.UnionFind;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/merging-communities/problem
 */
public class MergingCommunities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < q; i++) {
            char queryType = sc.next().charAt(0);
            if (queryType == 'Q') {
                int index = sc.nextInt();
                System.out.println(uf.sizeOf(index - 1));
            } else {
                int index1 = sc.nextInt();
                int index2 = sc.nextInt();
                uf.union(index1 - 1, index2 - 1);
            }
        }
    }
}
