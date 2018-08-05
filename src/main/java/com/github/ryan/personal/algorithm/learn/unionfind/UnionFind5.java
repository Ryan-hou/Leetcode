package com.github.ryan.personal.algorithm.learn.unionfind;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind5
 * @date February 11,2018
 */

// Path compression: Quick Union + rank + path compression
public class UnionFind5 implements UnionFind {

    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind5(int count) {
        parent = new int[count];
        rank = new int[count];
        this.count = count;
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int size() {
        return count;
    }

    @Override
    public int find(int p) {
        assert p >= 0 && p < count;

        // path compression 1
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;

        // path compression 2
//        if (p != parent[p]) {
//            parent[p] = find(parent[p]);
//        }
//        return parent[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
