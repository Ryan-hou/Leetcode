package com.leetcode.ryan.algorithm.hard42;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yanlin.hou@ucarinc.com
 * @description:
 * @className: Solution
 * @date July 11,2017
 */
public class Solution {

    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    /**
     * Begin scan from beginning and end of array.
     * Compare value of left and right pointer, hold the greater one
     * and move the other to inner array. Compute passed area when pointer
     * gets inner.
     * @param height
     * @return
     */
    private static int trap(int[] height) {
        int secondHight = 0;
        int left = 0;
        int right = height.length - 1;
        int area = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                secondHight = Math.max(height[left], secondHight);
                area += (secondHight - height[left]);
                left++;
            } else {
                secondHight = Math.max(height[right], secondHight);
                area += (secondHight - height[right]);
                right--;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        logger.info("Trapping rain water: {}", trap(height));
    }
}
