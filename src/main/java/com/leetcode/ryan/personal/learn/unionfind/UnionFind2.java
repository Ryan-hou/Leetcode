package com.leetcode.ryan.personal.learn.unionfind;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind2
 * @date February 11,2018
 */

// Quick Union: 通过数组构建树形结构

public class UnionFind2 implements UnionFind {

    private int[] parent;
    private int count;

    public UnionFind2(int count) {
        parent = new int[count];
        this.count = count;
        for (int i = 0; i < count; i++) {
            // 初始状态下，都为根节点，父节点为自身
            parent[i] = i;
        }
    }

    @Override
    public int find(int p) {
        assert p >= 0 && p < count;

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

        // 把任意一个根节点指向另外一个根节点，可能导致树高过高，后面的优化
        // 均是基于控制树高
        parent[pRoot] = qRoot;
    }

}
