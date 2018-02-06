package com.leetcode.ryan.personal.util;

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
        int[] arr = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            arr[i] = Math.abs(random.nextInt()) % (rangeR - rangeL + 1) + rangeL;
        }
        return arr;
    }

    public static void printArray(int[] arr, int n) {
        long curTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
        System.out.println("Consume " + (System.currentTimeMillis() - curTime) + " ms.");
    }

    public static boolean isSorted(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
