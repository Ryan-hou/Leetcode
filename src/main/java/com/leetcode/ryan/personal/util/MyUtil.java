package com.leetcode.ryan.personal.util;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: MyUtil
 * @date January 07,2018
 */
public class MyUtil {

    private MyUtil() {}

    public static int[] generateOrderedArray(int n) {
        assert (n > 0);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

}
