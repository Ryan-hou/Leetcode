package com.github.ryan.leetcode.algorithm.hard.hard51;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 02,2018
 */
@Slf4j
public class Solution {

    private static boolean[] col, dia1, dia2; // 保存列及两个对角线是否已经存在皇后
    private static List<List<String>> res = new ArrayList<>(); // 保存n皇后问题所有的解

    public static List<List<String>> solveNQueens(int n) {

        res.clear();
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];

        List<Integer> row = new ArrayList<>();
        putQueue(n, 0, row);

        return res;
    }

    // 尝试在一个n皇后问题中，摆放第index行的皇后位置
    private static void putQueue(int n, int index, List<Integer> row) {

        if (index == n) {
            res.add(generateBoard(n, new ArrayList<>(row)));
            return;
        }

        // i为列序号
        for (int i = 0; i < n; i++) {
            // 尝试将第index行的皇后摆放在第i列
            if (!col[i]
                    && !dia1[index + i]
                    && !dia2[index - i + n - 1]) {

                row.add(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueue(n, index + 1, row);
                // backtracking
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.remove((Integer) i); // be careful! remove(int index)
            }
        }
        return;
    }

    private static List<String> generateBoard(int n, List<Integer> row) {
        assert (row.size() == n);

        List<String> board = new ArrayList<>(n);
        for (int i = 0; i < n; i ++) {
            char[] c = new char[n];
            Arrays.fill(c, '.');
            c[row.get(i)] ='Q';
            board.add(new String(c));
        }
        return board;
    }

    public static void main(String[] args) {
        int n = 4;

        List<List<String>> res = solveNQueens(n);
        for (List<String> list : res) {
            for (String str : list) {
                System.out.println(str);
            }
            System.out.println("------------------");
        }

    }
}
