package com.github.ryan.algorithm.leetcode.hard.hard493;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date September 25,2018
 */
@Slf4j
public class Solution {

    // Given an array nums, we call (i, j)
    // an important reverse pair if i < j and nums[i] > 2*nums[j].

    // use merge sort
    // 在进行merge操作之前，判断两个有序的数组之间是否存在逆序对
    // 时间复杂度O(nlogn)
    public int reversePairs1(int[] nums) {
        return mergeSortInternal(nums, 0, nums.length - 1);
    }


    public void mergeSort(int[] arr, int n) {
        assert arr != null && arr.length == n;
        mergeSortInternal(arr, 0, n - 1);
    }

    // 使用递归，自顶向下操作，使arr[l...r]有序
    // 在原来merge sort的基础上，增加逆序对处理，并返回逆序对的个数
    private int mergeSortInternal(int[] arr, int l, int r) {

        if (l >= r) return 0; // 递归出口

        int mid = l + (r - l) / 2;
        int cnt = mergeSortInternal(arr, l, mid) + mergeSortInternal(arr, mid + 1, r);

        // 处理逆序对
        for (int i = l,j = mid + 1; i <= mid; i++) {
            // 2 * arr[j]存在整型越界风险
            while (j <= r && arr[i] / 2.0 > arr[j]) {
                j++;
                // cnt++; // bug!!
            }
            cnt += j - (mid + 1); // think about it
        }

        if (arr[mid] > arr[mid + 1]) {
            merge(arr, l, mid, r);
        }
        return cnt;
    }

    // 对数组arr[l...mid]和数组arr[mid+1...r]进行归并操作，操作后arr[l...r]保持有序
    private void merge(int[] arr, int l, int mid, int r) {

        // 构造辅助空间
        int[] aux = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }

        // 维护i,j,k三个指针的语义
        int i = l, j = mid + 1; // i,j分别指向两个待归并数组(aux数组)的头部
        // k指向arr数组待归并的位置
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] > aux[j - l]) { // aux[] 和 arr[] 不要混
                arr[k] = aux[j - l];
                j++;
            } else {
                // arr[i - l] <= arr[j - l]
                arr[k] = aux[i - l];
                i++;
            }
        }
    }


    public static void main(String[] args) {
//        int[] arr = {1, 4, 2, 3, 7, 9};
//        new Solution().mergeSort(arr, arr.length);
//        log.info("Sorted array = {}", Arrays.toString(arr));
        //int[] arr = {2, 4, 3, 5, 1};
        int[] arr = {5, 4, 3, 2, 1};
        log.info("Reverse pairs num = {}", new Solution().reversePairs1(arr));
        log.info("Sorted array = {}", Arrays.toString(arr));
    }
}
