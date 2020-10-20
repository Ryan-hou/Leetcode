package com.github.ryan.algorithm.leetcode.medium.medium1215;

import java.util.*;

public class Solution {


    // ============ 常规思路：根据当前值，递归求解下一个值 ============
    private List<Integer> res;
    private int low;
    private int high;

    public List<Integer> countSteppingNumbers(int low, int high) {

        res = new ArrayList<>();
        this.low = low;
        this.high = high;
        if (low == 0) {
            res.add(0);
        }

        for (int i = 1; i <= 9; i++) {
            helper(i);
        }

        Collections.sort(res);
        return res;
    }

    /**
     * 从 cur 出发，计算 stepping number
     * @param cur
     */
    private void helper(long cur) {
        // 递归出口
        if (cur > high) {
            return;
        }

        if (low <= cur && cur <= high) {
            res.add((int) cur);
        }

        long last = cur % 10;
        long inc = cur * 10 + last + 1;
        long dec = cur * 10 + last - 1;
        if (last == 0) {
            helper(inc);
        } else if (last == 9) {
            helper(dec);
        } else {
            helper(dec);
            helper(inc);
        }
    }

    // ================== 图论建模 =================

    /**
     * 图论建模
     * @param low
     * @param high
     * @return
     */
    public List<Integer> countSteppingNumbers2(int low, int high) {
        // graph: vertext -> current num, edge -> cur num to next stepping num
        // for example: 1 -> 10, 12
        List<Integer> res = new ArrayList<>();
        if (low == 0) {
            res.add(0);
        }

        // BFS
        Queue<Long> q = new ArrayDeque<>();
        // num strating from 1 to 9
        for (long i = 1; i <= 9; i++) {
            q.offer(i);
        }

        while (!q.isEmpty()) {
            long cur = q.poll();
            if (cur < high) {
                long last = cur % 10;
                if (last > 0) {
                    q.offer(cur * 10 + last - 1);
                }
                if (last < 9) {
                    q.offer(cur * 10 + last + 1);
                }
            }

            if (low <= cur && cur <= high) {
                res.add((int) cur);
            }
        }
        return res;
    }

}
