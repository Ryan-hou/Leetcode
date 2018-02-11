package com.leetcode.ryan.personal.learn.unionfind;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind4
 * @date February 11,2018
 */

// Optimize by rank: quick union + rank
public class UnionFind4 implements UnionFind {

    private int[] parent;
    // rank[i]表示以i为根的集合所表示的树的层数
    private int[] rank;
    private int count;

    public UnionFind4(int count) {
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
        assert p >=0 && p < count;
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
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
