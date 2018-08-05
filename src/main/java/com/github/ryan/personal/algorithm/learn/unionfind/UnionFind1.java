package com.github.ryan.personal.algorithm.learn.unionfind;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind1
 * @date February 11,2018
 */

/**
 * Quick-find: 数组下标作为节点，数组值作为节点内容
 * find时间复杂度为 O(1), union操作为O(n)
 */
public class UnionFind1 implements UnionFind {

    private int[] id;
    private int count;

    public UnionFind1(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public int find(int p) {
        assert p >= 0 && p < count;
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void union(int p, int q) {

        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }

        for (int i = 0; i < count; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }
}
