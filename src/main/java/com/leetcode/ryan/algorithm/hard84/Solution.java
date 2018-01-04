package com.leetcode.ryan.algorithm.hard84;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 22,2017
 */
@Slf4j
public class Solution {

    /**
     * 思路: 找到左边第一个比当前高度小的值的下标:i
     * 找到右边第一个比当前高度小的值的下标:j
     * 当前高度(h)的矩形面积为: s = h * (j - i -1)
     * @param heights
     * @return
     */
    private static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0)
            return 0;

        // idx of the first left bar that is lower than current
        int[] firstLessFromLeft = new int[heights.length];
        // idx of the first right bar that is lower than current
        int[] firstLessFromRight = new int[heights.length];
        firstLessFromLeft[0] = -1;
        firstLessFromRight[heights.length - 1] = heights.length;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = firstLessFromLeft[p];
            }
            firstLessFromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                p = firstLessFromRight[p];
            }
            firstLessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (firstLessFromRight[i] - firstLessFromLeft[i] - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        log.info("maxArea = {}", largestRectangleArea(heights));
    }
}
