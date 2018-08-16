package com.github.ryan.personal.data_structure.tree.union_find;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind5
 * @date February 11,2018
 */

// Path compression: Quick Union + rank + path compression
// 时间复杂度 O(h),严格证明 O(log*n),近乎是O(1)级别，比O(logn)快
public class UnionFind5 implements UnionFind {

    private int[] parent;
    // 这里的rank并不是树的深度，而是一种作为参考使用的优先级
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
