package com.leetcode.ryan.personal.util;

import java.util.Arrays;
import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: ArrayUtil
 * @date January 07,2018
 */
public class ArrayUtil {

    private ArrayUtil() {}

    public static int[] generateOrderedArray(int n) {
        assert (n > 0);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // 生成有n个元素的随机数组，每个元素的随机范围为[rangeL, rangeR]
    public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        assert rangeL >= 0 && rangeR >= 0;

        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = Math.abs(random.nextInt()) % (rangeR - rangeL + 1) + rangeL;
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static int[] generateNearlyOrderedArray(int n, int swapTimes) {
        int[] array = generateOrderedArray(n);
        Random random = new Random();

        for (int i = 0; i < swapTimes; i++) {
            int posx = Math.abs(random.nextInt()) % n;
            int posy = Math.abs(random.nextInt()) % n;
            swap(array, posx, posy);
        }
        return array;
    }

    public static boolean isSorted(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Swaps x[a] with x[b].
     */
    public static void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

}
