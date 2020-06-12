package com.github.ryan.leetcode.algorithm.hard.hard272;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    private static class Entry {
        double diff;
        int val;
        public Entry(double diff, int val) {
            this.diff = diff;
            this.val = val;
        }
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Entry> maxHeap = new PriorityQueue<>((e1, e2) -> Double.compare(e2.diff, e1.diff));
        inorder(root, target, k, maxHeap);
        List<Integer> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.poll().val);
        }
        return res;
    }

    private void inorder(TreeNode root, double target, int k, PriorityQueue<Entry> maxHeap) {
        if (root == null) return;

        inorder(root.left, target, k, maxHeap);
        int val = root.val;
        if (maxHeap.size() < k) {
            maxHeap.offer(new Entry(Math.abs(val - target), val));
        } else {
            double curDiff = Math.abs(val - target);
            if (curDiff < maxHeap.peek().diff) {
                maxHeap.poll();
                maxHeap.offer(new Entry(curDiff, val));
            }
        }
        inorder(root.right, target, k, maxHeap);
    }

}
