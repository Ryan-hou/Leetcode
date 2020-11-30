package com.github.ryan.algorithm.leetcode.easy.easy1010;

public class Solution {


    // space complexity: O(1); time complexity: O(n)
    public int numPairsDivisibleBy60(int[] time) {
        // (a + b) % 60 = 0 -> ((a % 60) + (b % 60)) % 60 = 0
        // if a % 60 == 0 then b % 60 == 0
        // if a % 60 != 0 then b % 60 == (60 - a % 60)

        // remainders[i] -> count of (num % 60 == i) from time array
        int[] remainders = new int[60];
        int res = 0;
        for (int t : time) {
            if (t % 60 == 0) {
                // a % 60 == 0 and b % 60 == 0
                res += remainders[0];
            } else {
                // a % 60 != 0 and b % 60 == 60 - (a % 60)
                res += remainders[60 - (t % 60)];
            }

            remainders[t % 60]++;
        }
        return res;
    }

}
