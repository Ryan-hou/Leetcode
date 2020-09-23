package com.github.ryan.algorithm.leetcode.medium.medium229;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // Idea behind the issue is that there can only be at most 2 element which fullfill the criteria.
    // (n/3 + n/3) = 2n/3. 2 elements which have sum greater than 2n/3. Which leaves rest of the elements with sum less than n/3.
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length < 1) return new ArrayList<>();

        int n1 = -1;
        int n2 = -1;
        int c1 = 0;
        int c2 = 0;
        for (int n : nums) {
            if (c1 == 0) {
                n1 = n;
                c1++;
            } else if (n1 == n) {
                c1++;
            } else if (c2 == 0) {
                n2 = n;
                c2++;
            } else if (n2 == n) {
                c2++;
            } else {
                c1--;
                c2--;
            }
        }

        // reset count
        c1 = 0;
        c2 = 0;
        for (int n : nums) {
            if (n == n1) {
                c1++;
            } else if (n == n2) {
                c2++;
            }
        }

        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (c1 > len / 3) {
            res.add(n1);
        }
        if (c2 > len / 3) {
            res.add(n2);
        }
        return res;
    }

}
