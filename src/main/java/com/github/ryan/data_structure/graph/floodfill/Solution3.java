package com.github.ryan.data_structure.graph.floodfill;

/**
 * 使用并查集标记连通分量
 */
public class Solution3 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R;
    private int C;

    public int maxAreaOfIsland(int[][] grid) {
        // grid is a non-empty 2D array
        R = grid.length;
        C = grid[0].length;

        UF uf = new UF(R * C);
        for (int v = 0; v < R * C; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1) {
                for (int d = 0; d < dirs.length; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];
                    if (inArea(nextx, nexty)
                            && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        uf.union(v, next);
                    }
                }
            }
        }

        int res = 0;
        for (int v = 0; v < R * C; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1) {
                res = Math.max(res, uf.size(v));
            }
        }
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    /**
     * 并查集:
     * 没有基于 rank 进行优化，为了让代码更简洁
     * 基于 rank 的优化可以提升效率，但是提升幅度并不大
     */
    private static class UF {

        /**
         * parent[i] 表示 i 节点的父亲节点
         * parent[i] == i 表示节点 i 为根节点
         */
        private int[] parent;
        /**
         * sz[i] 表示以 i 为根的集合大小
         */
        private int[] sz;

        public UF(int n) {
            parent = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                sz[i] = 1;
            }
        }

        /**
         * 返回点 p 所属集合的根节点
         * @param p
         * @return
         */
        public int find(int p) {
            if (p == parent[p]) {
                return p;
            }

            return find(parent[p]);
        }

        /**
         * 节点 p，q 是否是相连的
         * @param p
         * @param q
         * @return
         */
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        /**
         * 返回点 p 所属集合的大小
         * @param p
         * @return
         */
        public int size(int p) {
            return sz[find(p)];
        }

        /**
         * 点 p，q 进行 union 操作
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return;
            }

            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
    }
}
