package com.github.ryan.algorithm.leetcode.medium.medium1066;

public class Solution {


    /**
     * top-down -> 递归 + 记忆化搜索
     *
     * @param workers
     * @param bikes
     * @return
     */
    public int assignBikes(int[][] workers, int[][] bikes) {
        int[][] memo = new int[workers.length][1 << bikes.length];
        return assign(0, 0, workers, bikes, memo);
    }

    // 从 workerIdx 的员工开始分配自行车，且当前已经分配出去的自行车为 assigned，返回最短路径和
    private int assign(int workerIdx, int assigned, int[][] workers, int[][] bikes, int[][] memo) {
        if (workerIdx >= workers.length) {
            return 0;
        }
        if (memo[workerIdx][assigned] != 0) {
            return memo[workerIdx][assigned];
        }

        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            if ((assigned & (1 << i)) != 0) {
                // 第 i 个自行车已经被分配出去
                continue;
            }

            // worker[workerIdx] 分配自行车 bikes[i]
            int dis = assign(workerIdx + 1, assigned | (1 << i), workers, bikes, memo)
                    + dis(workers[workerIdx], bikes[i]);
            minDis = Math.min(minDis, dis);
        }
        memo[workerIdx][assigned] = minDis;
        return minDis;
    }

    private int dis(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

}
