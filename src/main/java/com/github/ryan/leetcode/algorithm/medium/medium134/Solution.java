package com.github.ryan.leetcode.algorithm.medium.medium134;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 26,2019
 */
public class Solution {

    // time complexity: O(n)
    // i from [l,r], sum of gas[i] >= sum of cost[i] -> 则[l,r]中一定存在某一点可以绕一圈,反证法可证
    // i from [l,r], if sum of (gas[i] - cost[i]) < 0 -> 则[l,r]中不存在一个起始点可以让车绕[l,r]一圈
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int sum = 0;
        int idx = 0;
        for (int i = 0; i < gas.length; i++) {
            total += (gas[i] - cost[i]);
            sum += (gas[i] - cost[i]);
            if (sum < 0) {
                sum = 0;
                idx = i + 1;
            }
        }
        return total < 0 ? -1 : idx;
    }

    // brute force: time complexity: O(n^2)
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (canComplete(gas, cost, i)) return i;
        }
        return -1;
    }

    private boolean canComplete(int[] gas, int[] cost, int start) {
        boolean isCircuit = false;
        int i = start;
        int sum = 0;
        while (!isCircuit || i != start) {
            sum += (gas[i] - cost[i]);
            if (sum < 0) {
                return false;
            }
            if (i + 1 >= gas.length) {
                isCircuit = true;
            }
            i = (i + 1) % gas.length;
        }
        return true;
    }
}
