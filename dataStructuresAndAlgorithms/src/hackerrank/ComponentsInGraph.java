package hackerrank;

import CS61B.Lab6.UnionFind;

import java.io.IOException;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/components-in-graph/problem
 */
public class ComponentsInGraph {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        UnionFind uf = new UnionFind(2 * n);
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            uf.union(a - 1, b - 1);
        }
        int minVertices = 2 * n;
        boolean minFound = false;
        int maxVertices = 1;
        for (int i = 0; i < 2 * n; i++) {
            int currentSize = uf.sizeOf(i);
            if (currentSize > maxVertices) {
                maxVertices = currentSize;
            }
            if (currentSize < minVertices && currentSize > 1) {
                minVertices = currentSize;
            }
        }
        System.out.println(minVertices + " " + maxVertices);
    }
}
