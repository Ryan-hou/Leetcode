package com.github.ryan.personal.data_structure.tree.segment_tree;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        if (isNull(arr) || isNull(merger)) {
            throw new IllegalArgumentException("arr or merger is null!");
        }
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];

        buildSegmentTree(0, 0, data.length - 1);
    }

    // 在 treeIdx 的位置创建表示区间 [l...r] 的线段树
    private void buildSegmentTree(int treeIdx, int l, int r) {
        if (l == r) {
            tree[treeIdx] = data[l];
            return;
        }

        int leftTreeIdx = leftChild(treeIdx);
        int rightTreeIdx = rightChild(treeIdx);
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIdx, l, mid);
        buildSegmentTree(rightTreeIdx, mid + 1, r);

        tree[treeIdx] = merger.merge(tree[leftTreeIdx], tree[rightTreeIdx]);
    }

    public int size() {
        return data.length;
    }

    public E get(int index) {
        validateIndex(index);
        return data[index];
    }

    // 返回区间 [queryL, queryR] 的值
    public E query(int queryL, int queryR) {
        validateIndex(queryL);
        validateIndex(queryR);
        if (queryL > queryR) {
            throw new IllegalArgumentException("Parameter is illegal!");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以 treeIdx 为根的线段树中 [l...r] 的范围里，搜索区间 [queryL, queryR] 的值
    private E query(int treeIdx, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIdx];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIdx = leftChild(treeIdx);
        int rightTreeIdx = rightChild(treeIdx);
        if (queryL >= mid + 1) {
            return query(rightTreeIdx, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIdx, l, mid, queryL, queryR);
        } else {
            E leftRes = query(leftTreeIdx, l, mid, queryL, mid);
            E rightRes = query(rightTreeIdx, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftRes, rightRes);
        }
    }

    // 将 index 位置的值更新为 e
    public void set(int index,  E e) {
        validateIndex(index);
        data[index] = e;

        set(0, 0, data.length - 1, index, e);
    }

    // 在以 treeIdx 为根的线段树中 [l...r] 的范围里，更新 index 的值为 e
    private void set(int treeIdx, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIdx] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftTreeIdx = leftChild(treeIdx);
        int rightTreeIdx = rightChild(treeIdx);
        if (index >= mid + 1) {
            set(rightTreeIdx, mid + 1, r, index, e);
        } else {
            // index <= mid
            set(leftTreeIdx, l, mid, index, e);
        }

        tree[treeIdx] = merger.merge(tree[leftTreeIdx], tree[rightTreeIdx]);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; i < tree.length; i++) {
            b.append(tree[i]);
            if (i != tree.length - 1) {
                b.append(", ");
            }
        }
        b.append(']');
        return b.toString();
    }


    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal!");
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        log.info("==== segTree = {} ====", segTree);

        // -3
        log.info("==== Element from {} to {} is: {}", 0, nums.length - 1, segTree.query(0, nums.length - 1));
    }
}
