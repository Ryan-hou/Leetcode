package com.leetcode.ryan.personal.test;

import com.leetcode.ryan.personal.sort.MergeSort;
import com.leetcode.ryan.personal.sort.QuickSort;
import com.leetcode.ryan.personal.sort.Sort;
import com.leetcode.ryan.personal.util.ArrayUtil;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: SortTest
 * @date February 07,2018
 */
public class SortTest {

    public static void main(String[] args) {
        int n = 500000;
        int[] arr = ArrayUtil.generateRandomArray(n, 0, 10);
        //int[] arr = ArrayUtil.generateNearlyOrderedArray(n, 10);

        int[] copyArray = ArrayUtil.copyArray(arr);
        testSort("Merge sort:", arr, n, new MergeSort());
        testSort("Quick sort:", copyArray, n, new QuickSort());
        //testSort("Selection sort:", arr, n, new SelectionSort());
        //testSort("Insert sort:", copyArray, n, new InsertSort());
    }

    private static void testSort(String sortName, int[] arr, int n, Sort sort) {
        long startTime = System.currentTimeMillis();
        sort.sort(arr, n);
        long endTime = System.currentTimeMillis();
        assert ArrayUtil.isSorted(arr, n);

        System.out.println(sortName + " sort " + n + " nums consume " + (endTime - startTime) + " ms.");
    }
}
