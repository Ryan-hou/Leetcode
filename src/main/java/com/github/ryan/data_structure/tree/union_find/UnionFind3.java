package com.github.ryan.data_structure.tree.union_find;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind3
 * @date February 11,2018
 */

// Optimize by size: quick union + sz
public class UnionFind3 implements UnionFind {

    private int[] parent;
    private int[] sz; // sz表示以i为根的集合中元素的个数
    private int count;

    public UnionFind3(int count) {
        parent = new int[count];
        sz = new int[count];
        this.count = count;
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int find(int p) {
        assert (p >= 0 && p < count);
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

        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
