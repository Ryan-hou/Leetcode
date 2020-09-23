package com.github.ryan.algorithm.leetcode.easy.easy172;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 09,2019
 */
public class Solution {

    // https://leetcode.com/problems/factorial-trailing-zeroes/discuss/334090/0ms-c%2B%2B-solution-with-SUPER-intuitive-explaination
    // calculate how many 5s
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res += (n / 5);
            n /= 5;
        }
        return res;
    }
}
