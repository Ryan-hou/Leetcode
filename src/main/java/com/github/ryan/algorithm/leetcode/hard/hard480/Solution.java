package com.github.ryan.algorithm.leetcode.hard.hard480;

import java.util.*;

public class Solution {

    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> res = new ArrayList<>();
        // max heap
        // use a2.compareTo(a1) not a2 - a1 caz a2 - a1 may bigger than Integer.MAX_VALUE
        PriorityQueue<Integer> lo = new PriorityQueue<>((a1, a2) -> a2.compareTo(a1));
        // min heap
        PriorityQueue<Integer> hi = new PriorityQueue<>();
        // key -> number need to delete, value -> count of this number
        Map<Integer, Integer> delMap = new HashMap<>();
        // index of current incoming element being processed
        int i = 0;

        // initialize the heaps
        while (i < k) {
            lo.offer(nums[i++]);
        }
        for (int j = 0; j < k / 2; j++) {
            hi.offer(lo.poll());
        }

        while (true) {
            // get median of current window
            // int -> long (corner case)
            res.add((k & 1) == 0
                    ? ((double) lo.peek() + (double) hi.peek()) / 2
                    : (double) lo.peek());

            if (i >= nums.length) {
                break; // break if all elements processed
            }

            int outNum = nums[i - k];
            int inNum = nums[i];
            i++;
            // balance factor
            int balance = 0;

            // number outNum exits window
            balance += (outNum <= lo.peek() ? -1 : 1);
            delMap.put(outNum, delMap.getOrDefault(outNum, 0) + 1);
            // number inNum enters window
            if (!lo.isEmpty() && inNum <= lo.peek()) {
                balance++;
                lo.offer(inNum);
            } else {
                balance--;
                hi.offer(inNum);
            }

            // re-balance heaps
            // lo and hi is balaced or balance is 2/-2
            if (balance < 0) {
                // lo needs more valid elements
                lo.offer(hi.poll());
            }
            if (balance > 0) {
                // hi needs more valid elements
                hi.offer(lo.poll());
            }

            // lazy remove technique
            // remove invalid numbers that should be discarded from heap tops
            while (delMap.get(lo.peek()) != null && delMap.get(lo.peek()) > 0) {
                delMap.put(lo.peek(), delMap.get(lo.peek()) - 1);
                lo.poll();
            }
            while (!hi.isEmpty()
                    && delMap.get(hi.peek()) != null && delMap.get(hi.peek()) > 0) {
                delMap.put(hi.peek(), delMap.get(hi.peek()) - 1);
                hi.poll();
            }

        } // end while

        return res.stream().mapToDouble(e -> e).toArray();
    }

}
