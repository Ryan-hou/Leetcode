package com.github.ryan.algorithm.leetcode.medium.medium1423;

public class Solution {

    // Use sliding window
    // to get the max out of the k points, we essentially want to remove
    // len - k points from the array. In other words, if we have a sliding
    // window with size len - k, we want to remove this window somewhere
    // along the path to make sure the sum of the k points left is max.
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        for (int n : cardPoints) {
            sum += n;
        }
        int windowSum = 0;
        int windowSize = cardPoints.length - k;
        int max = 0;
        for (int i = 0; i < cardPoints.length; i++) {
            if (i < windowSize) {
                windowSum += cardPoints[i];
            } else {
                max = Math.max(max, sum - windowSum);
                windowSum -= cardPoints[i - windowSize];
                windowSum += cardPoints[i];
            }
        } // end for
        return Math.max(max, sum - windowSum);
    }

}
