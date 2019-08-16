package com.github.ryan.leetcode.algorithm.medium.medium378;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 16,2019
 */
@Slf4j
public class Solution {

    public int kthSmallest(int[][] matrix, int k) {
        Set<Pair> set = new HashSet<>();
        // small heap
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.val));
        Pair p = new Pair(0, 0, matrix[0][0]);
        pq.offer(p);
        set.add(p);
        Pair cur = null;
        while (k > 0) {
            cur = pq.poll();
            k--;
            int row = cur.row;
            int col = cur.col;

            if (row + 1 < matrix.length) {
                Pair rp = new Pair(row + 1, col, matrix[row + 1][col]);
                if (set.add(rp)) {
                    pq.offer(rp);
                }
            }
            if (col + 1 < matrix.length) {
                Pair cp = new Pair(row, col + 1, matrix[row][col + 1]);
                if (set.add(cp)) {
                    pq.offer(cp);
                }
            }
        }
        return matrix[cur.row][cur.col];
    }

    private static class Pair {
        int row;
        int col;
        int val;

        public Pair(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

        @Override
        public int hashCode() {
            return this.row * 31 + this.col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj instanceof Pair) {
                Pair newObj = (Pair) obj;
                if (this.row == newObj.row && this.col == newObj.col) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
