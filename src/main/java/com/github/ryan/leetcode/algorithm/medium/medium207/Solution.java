package com.github.ryan.leetcode.algorithm.medium.medium207;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date May 23,2019
 */
public class Solution {


    // 判断图中是否有环,可以使用拓扑排序

    /**
     * 拓扑排序：
     * 对一个有向无环图(Directed Acyclic Graph简称DAG)G进行拓扑排序，是将G中所有顶点排成一个线性序列，
     * 使得图中任意一对顶点u和v，若边(u,v)∈E(G)，则u在线性序列中出现在v之前
     * 拓扑排序对应施工的流程图具有特别重要的作用，它可以决定哪些子工程必须要先执行，哪些子工程要在某些工程执行后才可以执行。
     *
     * 通常，我们把这种顶点表示活动、边表示活动间先后关系的有向图称做顶点活动网(Activity On Vertex network)，简称AOV网。
     * 一个AOV网应该是一个有向无环图，即不应该带有回路，因为若带有回路，则回路上的所有活动都无法进行（对于数据流来说就是死循环）
     * 在AOV网中，若不存在回路，则所有活动可排列成一个线性序列，使得每个活动的所有前驱活动都排在该活动的前面，
     * 我们把此序列叫做拓扑序列(Topological order)，由AOV网构造拓扑序列的过程叫做拓扑排序(Topological sort)
     * AOV网的拓扑序列不是唯一的，满足上述定义的任一线性序列都称作它的拓扑序列
     *
     * 思路：
     * 其实这个问题就是让我们在给定的输入下，判断能否完成拓扑排序
     * 那么，如何判断呢？
     * 这就可以根据入度（indegree）来判断了。所谓入度，就是构成有向图之后，指向各个节点的边数。
     * 具体步骤是，我们每次去掉入度为0的点，将该点加入拓扑排序，同时删去与其连接的边（其它节点的入度会受到影响），
     * 直到去掉所有的点为止，如果中途遇到不存在入度为0的点的情况，那么，就认为这个有向图不是拓扑排序的
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int prev = prerequisites[i][1];
            int ready = prerequisites[i][0];
            if (matrix[prev][ready] == 0) {
                // 避免重复,实际本题目保证了不存在重复边
                indegree[ready]++;
            }
            matrix[prev][ready] = 1;
        }

        // BFS
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            int prev = q.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[prev][i] == 1) {
                    if (--indegree[i] == 0) {
                        q.offer(i);
                    }
                }
            }
        }

        return count == numCourses;
    }
}
