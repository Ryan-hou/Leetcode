package com.leetcode.ryan.personal.sort;

import com.leetcode.ryan.personal.util.ArrayUtil;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: InsertSort
 * @date February 06,2018
 */
public class InsertSort {

    /**
     * 时间复杂度：O(n^2)
     * 但是内层循环存在提前终止的可能，在近乎有序的情况下，时间复杂度接近于O(n)
     *
     * @param arr
     * @param n
     */
    public static void insertSort(int[] arr, int n) {

        for (int i = 1; i < n; i++) {

            // 寻找元素arr[i]合适的插入位置
//            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
//                // 一次swap需要三次赋值，可以优化掉
//                ArrayUtil.swap(arr, j, j - 1);
//            }
            int e = arr[i];
            int j; // j保存元素e应该插入的位置
            for (j = i; j > 0 && arr[j - 1] > e; j--) {
                // 向后覆盖元素(把数组后移，将e放入到合适的位置)
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public static void main(String[] args) {
        int n = 10000;
        int[] arr = ArrayUtil.generateNearlyOrderedArray(n, 10);
        insertSort(arr, n);
        ArrayUtil.isSorted(arr, n);
    }
}
