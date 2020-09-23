package com.github.ryan.algorithm.leetcode.hard.hard295;

import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 14,2019
 */
public class MedianFinder {

    // store half data that are bigger than bigHeap
    private PriorityQueue<Integer> smallHeap;
    private PriorityQueue<Integer> bigHeap;
    private int count;

    /** initialize your data structure here. */
    public MedianFinder() {
        smallHeap = new PriorityQueue<>();
        bigHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }

    public void addNum(int num) {
        if (count % 2 == 0) {
            smallHeap.offer(num);
        }  else {
            bigHeap.offer(num);
        }
        count++;
        if (!bigHeap.isEmpty() && smallHeap.peek() < bigHeap.peek()) {
            int small = smallHeap.poll();
            int big = bigHeap.poll();
            smallHeap.offer(big);
            bigHeap.offer(small);
        }
    }

    public double findMedian() {
        return count % 2 == 0
                ? ((double) bigHeap.peek() + smallHeap.peek()) / 2 : smallHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
