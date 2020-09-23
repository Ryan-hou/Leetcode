package com.github.ryan.algorithm.leetcode.easy.easy414;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date November 30,2018
 */
public class Solution {

    public int thirdMax(int[] nums) {
        assert nums != null && nums.length > 0;

        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        // 维护容量为3的小根堆，堆顶的数据即为本堆中最小的数据，也就是整个数组中第三大的数
        PriorityQueue<Integer> pq = new PriorityQueue<>(3);
        for (int n : set) {
            pq.add(n);
            if (pq.size() > 3) {
                // 移除当前最小的数据
                pq.poll();
            }
        }

        // corner case
        if (pq.size() == 2) {
            pq.poll();
        }
        // else pq.size() == 1 || pq.size() == 3
        return pq.peek();
    }
}
