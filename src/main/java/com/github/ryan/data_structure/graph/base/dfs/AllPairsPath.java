package com.github.ryan.data_structure.graph.base.dfs;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

/**
 * 所有点对的路径
 */
public class AllPairsPath {

    private UnweightedGraph g;
    private SingleSourcePath[] paths;

    public AllPairsPath(UnweightedGraph g) {
        this.g = g;
        paths = new SingleSourcePath[g.V()];
        for (int v = 0; v < g.V(); v++) {
            paths[v] = new SingleSourcePath(g, v);
        }
    }

    public boolean isConnected(int s, int t) {
        GraphUtil.validateVertex(g, s);
        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t) {
        GraphUtil.validateVertex(g, s);
        return paths[s].pathTo(t);
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("path.txt");
        AllPairsPath allPath = new AllPairsPath(g);
        System.out.println("0 -> 6: " + allPath.path(0,6));
        System.out.println("0 is connected to 5? " + allPath.isConnected(0,5));
    }
}
