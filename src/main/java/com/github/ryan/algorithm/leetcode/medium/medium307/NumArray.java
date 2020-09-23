package com.github.ryan.algorithm.leetcode.medium.medium307;

import com.github.ryan.data_structure.tree.segment_tree.SegmentTree;

import java.util.stream.IntStream;

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
public class NumArray {

    private SegmentTree<Integer> segTree;

    public NumArray(int[] nums) {
        Integer[] data = IntStream.of(nums).boxed().toArray(Integer[]::new);
        segTree = new SegmentTree<>(data, (a, b) -> a + b);
    }

    public void update(int i, int val) {
        segTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        return segTree.query(i, j);
    }
}
