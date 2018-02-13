package com.leetcode.ryan.personal.learn.graph.genetree;

import java.io.File;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: GraphTest
 * @date February 13,2018
 */
public class GraphTest {


    public static void main(String[] args) {
        String filename = "test" + File.separator + "testG1W.txt";
        int V = 8;

        // Test Weighted Dense Graph
        Graph<Double> g1 = new DenseGraph<>(V, false);
        ReadGraph.read(filename, g1);
        g1.show();

        System.out.println();

        // Test Weighted Sparse Graph
        Graph<Double> g2 = new SparseGraph<>(V, false);
        ReadGraph.read(filename, g2);
        g2.show();
    }
}
