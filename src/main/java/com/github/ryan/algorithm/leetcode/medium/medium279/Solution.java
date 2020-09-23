package com.github.ryan.algorithm.leetcode.medium.medium279;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 29,2018
 */
@Slf4j
public class Solution {


    /**
     * 由n为正整数，正整数至少可以表示为n个1相加，所以该问题一定有解
     * 直觉解法是贪心算法，但是稍加验证即可排除，12=9+1+1+1／12＝4+4+4
     *
     * 问题可以转化为图论算法：
     * n（1～n）为图的顶点，x*2为边
     * 问题即为从n到0的有向无权图中求最短路径（使用队列和BFS）
     *
     * 注意性能问题：
     * 树的每一个节点只能有一个父节点，因此到达每个节点的路径只能有一条，而图的每一个节点都可以有多个上一级节点，
     * 因此存在多条路径到达该节点，可能会带来大量的计算
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        assert n > 0;

        Deque<Pair<Integer, Integer>> q = new ArrayDeque<>();
        q.add(new Pair(n, 0));

        boolean[] visited = new boolean[n + 1];
        visited[n] = true;

        while (!q.isEmpty()) {
           int num = q.peek().getKey();
           int step = q.peek().getValue();
           q.poll();

           for (int i = 1; ; i++) {
               int left = num - i*i;
               if (left < 0)
                   break;
               if (left == 0)
                   return step + 1;

               // 避免重复推入了大量节点
               if (!visited[left]) {
                   q.add(new Pair<>(left, step + 1));
                   visited[left] = true;
               }
           }
        }

        throw new IllegalArgumentException("No solution");
    }

    public static void main(String[] args) {
        int n = 7168;
        log.info("result = {}", numSquares(n));
    }

}
