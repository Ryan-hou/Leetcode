package com.github.ryan.algorithm.leetcode.medium.medium526;

public class Solution {


    private int N;
    // visited[i] -> 数字i是否已经被使用
    private boolean[] visited;
    private int count;

    // backtracking
    /**
     * The idea behind this approach is simple. We try to crate all the permutations
     * of numbers from 1 to N. We can fix one number at a particular position and check
     * for the divisibility criteria of that number at that particular position. But,
     * we need to keep a track of the numbers which have already been considered earlier
     * so that they're not reconsidered while generating the permutations.
     * If the current number doesn't satisfy the divisibility criteria, we can leave
     * all the permutations that can be generated with that number at the particular
     * position. This helps to prune the search space of the permutations to a great extent.
     * We do so by trying to place each of the numbers of each position.
     *
     * @param N
     * @return
     */
    public int countArrangement(int N) {
        this.N = N;
        count = 0;
        visited = new boolean[N + 1];
        calc(1);
        return count;
    }

    // 从 pos 位置尝试放 1-N 的数字看是否存在满足条件的解
    private void calc(int pos) {
        if (pos > N) {
            count++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                calc(pos + 1);
                // backtracking
                visited[i] = false;
            }
        }
        // return 递归出口
    }

}
