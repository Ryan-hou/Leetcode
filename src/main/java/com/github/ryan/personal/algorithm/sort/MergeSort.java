package com.github.ryan.personal.algorithm.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: MergeSort
 * @date February 07,2018
 */
@Slf4j
public class MergeSort implements Sort {

    public void mergeSort(int[] arr, int n) {
        mergeSortInternal(arr, 0, n - 1);
        //mergeSortBU(arr, n);
    }

    // 递归：自顶向下 使用归并排序，对 arr[l...r] 的范围进行排序
    private void mergeSortInternal(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 优化二：当数据规模比较小时，数据比较趋于有序，使用插入排序来优化速度
//        if (r - l <= 15) {
//            InsertSort.insertSort(arr, l, r);
//            return;
//        }

        int mid = l + (r - l) / 2;
        mergeSortInternal(arr, l, mid);
        mergeSortInternal(arr, mid + 1, r);

        // 优化一：避免不必要的merge操作
        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r);
        }
    }

    // 使用迭代，自底向上实现归并算法
    private void mergeSortBU(int[] arr, int n) {

        for (int sz = 1; sz <= n; sz += sz) {
            for (int i = 0; i + sz < n; i += sz + sz) {
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1]进行归并
                merge(arr, i, i + sz -1, Math.min(i + sz + sz -1, n - 1));
            }
        }
    }

    private void merge(int[] arr, int l, int mid, int r) {
        // 辅助数据空间
        int[] aux = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    @Override
    public void sort(int[] nums, int n) {
        mergeSort(nums, n);
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = {1, 6, 7, 8, 2, 3, 4, 5};
        mergeSort.sort(nums, nums.length);
        log.info("Sorted array = {}", Arrays.toString(nums));
    }
}
