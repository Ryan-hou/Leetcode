package com.github.ryan.leetcode.algorithm.easy.easy888;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int[] fairCandySwap(int[] A, int[] B) {
        // Alice gives candy x, and receives candy y
        // Sa - x + y = Sb -y + x -> y = x + (Sb - Sa) / 2
        int sa = 0, sb = 0;
        Set<Integer> setB = new HashSet<>();
        for (int n : A) {
            sa += n;
        }
        for (int n : B) {
            sb += n;
            setB.add(n);
        }

        int delta = (sb - sa) / 2;
        // If Alice gives x, she expects to revice x + delta
        for (int x : A) {
            if (setB.contains(x + delta)) {
                return new int[] { x, x + delta };
            }
        }
        throw new IllegalArgumentException("Invalid arguments.");
    }

}
