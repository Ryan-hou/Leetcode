package com.leetcode.ryan.personal.learn.graph.genetree;

import java.io.File;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: GraphTest
 * @date February 13,2018
 */
public class GraphTest {

    public static void main(String[] args) {
        //testInitWGraph();
        //testLazyPrim();
        //System.out.println();
        testPrim();
        System.out.println();
        testKruskal();
    }

    private static void testInitWGraph() {
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

    private static void testLazyPrim() {
        String filename = "test" + File.separator + "testG1W.txt";
        int V = 8;

        Graph<Double> g = new SparseGraph<>(V, false);
        ReadGraph.read(filename, g);
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<>(g);
        List<Edge<Double>> mst = lazyPrimMST.mstEdges();

        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + lazyPrimMST.result());
    }

    private static void testPrim() {
        String filename = "test" + File.separator + "testG1W.txt";
        int V = 8;

        Graph<Double> g = new SparseGraph<>(V, false);
        ReadGraph.read(filename, g);
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<>(g);
        List<Edge<Double>> mst = primMST.mstEdges();

        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + primMST.result());
    }

    private static void testKruskal() {
        String filename = "test" + File.separator + "testG1W.txt";
        int V = 8;

        Graph<Double> g = new SparseGraph<>(V, false);
        ReadGraph.read(filename, g);
        System.out.println("Test Kruskal MST:");
        KruskalMST<Double> kruskalMST = new KruskalMST<>(g);
        List<Edge<Double>> mst = kruskalMST.mstEdges();

        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + kruskalMST.result());
    }
}
