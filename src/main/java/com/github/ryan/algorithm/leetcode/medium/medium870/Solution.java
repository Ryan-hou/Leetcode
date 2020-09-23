package com.github.ryan.algorithm.leetcode.medium.medium870;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    // Greedy
    public int[] advantageCount(int[] A, int[] B) {
        int[] res = new int[A.length];
        Arrays.sort(A);
        int start = 0;
        int end = A.length - 1;
        List<Integer> bb = IntStream.of(B).boxed().collect(Collectors.toList());
        for (int i = 0; i < res.length; i++) {
            int maxb = Collections.max(bb);
            int maxbIdx = bb.indexOf(maxb);
            int maxa = A[end];
            if (maxa > maxb) {
                res[maxbIdx] = maxa;
                end--;
                bb.remove(maxbIdx);
                bb.add(maxbIdx, -1);
            } else {
                res[maxbIdx] = A[start];
                start++;
                bb.remove(maxbIdx);
                bb.add(maxbIdx, -1);
            }
        }
        return res;
    }

}
