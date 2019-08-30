package com.github.ryan.leetcode.algorithm.medium.medium957;

import java.util.*;

public class Solution {

    public int[] prisonAfterNDays(int[] cells, int N) {

        Set<String> dupSet = new HashSet<>();
        List<int[]> tmpList = new ArrayList<>();
        tmpList.add(cells);
        boolean cycled = false;
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int[] next = getNextCells(cells);
            String ns = Arrays.toString(next);
            if (dupSet.contains(ns)) {
                cycled = true;
                break;
            } else {
                dupSet.add(ns);
                count++;
                tmpList.add(next);
            }
            System.arraycopy(next, 0, cells, 0, cells.length);
        }

        if (cycled) {
            N = N % count;
            return tmpList.get(N);
        } else {
            return cells;
        }
    }

    private int[] getNextCells(int[] cells) {
        int[] res = new int[cells.length];
        res[0] = 0;
        for (int i = 1; i < cells.length - 1; i++) {
            res[i] = (cells[i - 1] == cells[i + 1] ? 1 : 0);
        }
        res[cells.length - 1] = 0;
        return res;
    }
}
