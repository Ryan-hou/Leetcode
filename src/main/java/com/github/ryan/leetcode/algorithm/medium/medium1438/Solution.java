package com.github.ryan.leetcode.algorithm.medium.medium1438;

import java.util.TreeSet;

public class Solution {

    private static class Pair implements Comparable<Pair> {
        int num;
        int idx;
        public Pair(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair p) {
            if (this == p) return 0;
            if (this.num == p.num) {
                return Integer.compare(this.idx, p.idx);
            } else {
                return Integer.compare(this.num, p.num);
            }
        }

        @Override
        public int hashCode() {
            return this.num * 31 + this.idx;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Pair)) return false;
            Pair p = (Pair) obj;
            return this.num == p.num && this.idx == p.idx;
        }
    }

    // Sliding window
    public int longestSubarray(int[] nums, int limit) {
        TreeSet<Pair> set = new TreeSet<>();

        int i = 0, j = 0;
        int res = 0;
        set.add(new Pair(nums[0], 0));
        // [i, j] -> any two nums in [i, j] with absolute diff <= limit
        while (j < nums.length) {
            Pair min = set.first();
            Pair max = set.last();
            if (max.num - min.num <= limit) {
                res = Math.max(res, j - i + 1);
                j++;
                if (j < nums.length) {
                    set.add(new Pair(nums[j], j));
                }
            } else {
                set.remove(new Pair(nums[i], i));
                i++;
            }
        }
        return res;
    }

}
