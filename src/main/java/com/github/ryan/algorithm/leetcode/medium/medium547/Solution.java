package com.github.ryan.algorithm.leetcode.medium.medium547;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private static class UF {

        private int count;
        private int[] parent;
        private int[] rank;

        public UF(int count) {
            this.count = count;
            parent = new int[count];
            rank = new int[count];
            for (int i = 0; i < count; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // 返回节点p所在树的根节点
        public int find(int p) {
            // path compression
            if (p != parent[p]) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int proot = find(p);
            int qroot = find(q);
            if (proot == qroot) {
                return;
            }

            if (rank[proot] > rank[qroot]) {
                parent[qroot] = proot;
            } else if (rank[proot] < rank[qroot]) {
                parent[proot] = qroot;
            } else {
                // rank[proot] == rank[qroot]
                parent[proot] = qroot;
                rank[qroot] += 1;
            }
        }

    }

    public int findCircleNum(int[][] M) {
        int N = M.length;
        UF uf = new UF(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (M[i][j] == 1 && i != j) {
                    // 顶点 i，j相连
                    if (!uf.isConnected(i, j)) {
                        uf.union(i, j);
                    }
                }
            }
        }

        Set<Integer> rootSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            rootSet.add(uf.find(i));
        }
        return rootSet.size();
    }

}
