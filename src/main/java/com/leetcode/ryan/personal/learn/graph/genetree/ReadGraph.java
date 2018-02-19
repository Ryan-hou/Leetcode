package com.leetcode.ryan.personal.learn.graph.genetree;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: ReadGraph
 * @date February 13,2018
 */
@Slf4j
public class ReadGraph {

    private ReadGraph() {}

    public static void read(String fileName, Graph graph) {
        BufferedReader bis = null;

        try {
            String parentPath = com.leetcode.ryan.personal.learn.graph.base.ReadGraph.class.getResource("/").getPath();
            bis = new BufferedReader(new FileReader(new File(parentPath + fileName)));

            String line;
            int V, E;

            line = bis.readLine();
            assert (line != null);
            String[] ve = line.split(" ");
            V = Integer.parseInt(ve[0]);
            E = Integer.parseInt(ve[1]);
            assert (graph.V() == V);

            for (int i = 0; i < E; i++) {
                line = bis.readLine();
                int a = Integer.parseInt(line.split(" ")[0]);
                int b = Integer.parseInt(line.split(" ")[1]);
                //double c = Double.parseDouble(line.split(" ")[2]);
                int c = Integer.parseInt(line.split(" ")[2]);
                assert (a >= 0 && a < V);
                assert (b >= 0 && b < V);
                graph.addEdge(a, b, c);
            }

        } catch (IOException e) {
            log.error("IOException", e);
        }
    }
}
