package com.github.ryan.algorithm.leetcode.medium.medium826;

import java.util.Arrays;

public class Solution {

    private static class Job {
        int difficulty;
        int profit;
        Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, (a, b) -> a.difficulty - b.difficulty);
        Arrays.sort(worker);
        // no need to reset i and best
        int res = 0, i = 0, best = 0;
        for (int skill : worker) {
            while (i < n && skill >= jobs[i].difficulty) {
                best = Math.max(best, jobs[i].profit);
                i++;
            }
            res += best;
        }
        return res;
    }

}
