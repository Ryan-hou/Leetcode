package com.github.ryan.algorithm.leetcode.hard.hard4;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date May 31,2019
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        assert nums1 != null && nums2 != null;
        int k = (nums1.length + nums2.length + 1) / 2;
        double res = getKth(nums1, nums2, k);
        if (((nums1.length + nums2.length) & 1) == 0) {
            res += getKth(nums1, nums2, k + 1);
            res /= 2.0;
        }
        return res;
    }

    // k from 1th
    private int getKth(int[] n1, int[] n2, int k) {
        // keep n1.len <= n2.len
        if (n2.length < n1.length) return getKth(n2, n1, k);
        if (n1.length == 0) return n2[k - 1];
        if (k == 1) return Math.min(n1[0], n2[0]);

        // i >= 1
        int i = Math.min(n1.length, k / 2);
        int j = k - i; // j >= 1, j >= k / 2
        if (n1[i - 1] < n2[j - 1]) {
            int[] new1 = new int[n1.length - i];
            System.arraycopy(n1, i, new1, 0, n1.length - i);
            return getKth(new1, n2, k - i);
        } else {
            int[] new2 = new int[n2.length - j];
            System.arraycopy(n2, j, new2, 0, n2.length - j);
            return getKth(n1, new2, k - j);
        }
    }

    // 时间复杂度 O(m + n)
    // 空间复杂度 O(m + n)
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] sorted = new int[nums1.length + nums2.length];
        int k = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                sorted[k++] = nums1[i++];
            } else {
                sorted[k++] = nums2[j++];
            }
        }

        while (i < nums1.length) {
            sorted[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            sorted[k++] = nums2[j++];
        }

        int len = nums1.length + nums2.length;
        if ((len & 1) == 0) {
            return (sorted[len / 2] + sorted[len / 2 - 1]) / 2.0;
        } else {
            return sorted[len / 2];
        }
    }
}

