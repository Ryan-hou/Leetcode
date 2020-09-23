package com.github.ryan.algorithm.leetcode.medium.medium454;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 22,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：题目给出了数据容量，因此我们可以根据数据容量来设计算法需要的时间复杂度
     * 暴力解法：四层循环 O(n^4)
     * 使用查找表：把其中一组数据放到查找表，遍历剩下的三组数据，时间复杂度为 O(n^3),最多需要进行 500^3次运算，计算机比较吃力
     * 改变查找表需要查找的内容：（灵活的选择键值）把其中两组数据的和作为查找表，遍历剩下的两组数据，时间复杂度为O(n^2)
     */


    /**
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(n^2)
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0) return 0;

        Map<Integer, Integer> twoSumMap = new HashMap<>(A.length << 2);
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (twoSumMap.get(C[i] + D[j]) == null) {
                    twoSumMap.put(C[i] + D[j], 1);
                } else {
                    twoSumMap.put(C[i] + D[j], twoSumMap.get(C[i] + D[j]) + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (twoSumMap.get(0 - A[i] - B[j]) != null) {
                    res += twoSumMap.get(0 - A[i] - B[j]);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        /**
         * A = [1, 2]
         * B = [-2, -1]
         * C = [-1, 2]
         * D = [0, 2]
         */
        int[] A = {1, 2}, B = {-2, -1}, C = {-1, 2}, D = {0, 2};
        log.info("count = {}", fourSumCount(A, B, C, D));
    }
}
