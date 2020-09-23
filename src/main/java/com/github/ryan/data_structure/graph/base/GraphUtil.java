package com.github.ryan.data_structure.graph.base;

public class GraphUtil {

    private GraphUtil() {}

    public static void validateVertex(Graph g, int v) {
        if (v < 0 || v >= g.V()) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

}
