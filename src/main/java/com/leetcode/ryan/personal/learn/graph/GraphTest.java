package com.leetcode.ryan.personal.learn.graph;

import java.util.Iterator;
import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: GraphTest
 * @date February 12,2018
 */
public class GraphTest {


    public static void main(String[] args) {
        int N = 20;
        int M = 100;

        // Sparse Graph
        Graph g1 = new SparseGraph(N, false);
        initGraph(g1, M, N);
        // O(E)
        printEdge4Vertex(N, g1);

        System.out.println();

        // Dense Graph
        Graph g2 = new DenseGraph(N, false);
        initGraph(g2, M, N);
        // O(V^2)
        printEdge4Vertex(N, g2);
    }

    private static void initGraph(Graph g, int M, int N) {
        Random r = new Random();
        for (int i = 0; i < M; i++) {
            int a = Math.abs(r.nextInt()) % N;
            int b = Math.abs(r.nextInt()) % N;
            g.addEdge(a, b);
        }
    }


    private static void printEdge4Vertex(int N, Graph g) {
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterator<Integer> it = g.iterator(v);
            while (it.hasNext()) {
                System.out.print(it.next() + " ");
            }
            System.out.println();
        }
    }

}
