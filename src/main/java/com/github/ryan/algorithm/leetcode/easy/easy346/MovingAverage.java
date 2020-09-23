package com.github.ryan.algorithm.leetcode.easy.easy346;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage {

    private int size;
    private Queue<Integer> q;
    private int curSum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        curSum = 0;
        q = new LinkedList<>();
    }

    public double next(int val) {
        if (q.size() < size) {
            q.offer(val);
            curSum += val;
        } else {
            q.offer(val);
            int remove = q.poll();
            curSum -= remove;
            curSum += val;
        }
        return (double) curSum / (q.size() == size ? size : q.size());
    }
}
