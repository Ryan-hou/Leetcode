package com.github.ryan.algorithm.personal.test;

import com.github.ryan.algorithm.personal.sort.HeapSort;
import com.github.ryan.algorithm.personal.sort.MergeSort;
import com.github.ryan.algorithm.personal.sort.Sort;
import com.github.ryan.algorithm.personal.util.ArrayUtil;

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
        //testSort("Quick sort:", copyArray, n, new QuickSort());
        testSort("Heap sort:", copyArray, n, new HeapSort());
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
