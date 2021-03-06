package com.github.ryan.algorithm.personal.sort;

import com.github.ryan.algorithm.personal.util.ArrayUtil;

import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: QuickSort
 * @date February 07,2018
 */

/**
 * 同归并排序都使用了分治算法：
 * 分而治之，就是将原问题分割成同等结构的子问题，之后将子问题逐一解决后，
 * 原问题也就得到了解决
 *
 * 归并排序在分时比较简单，一分为二即可，治时需要使用 merge 操作
 * 快排分时，需要选择随机的pivot，然后通过一次partition操作，把pivot放到最终应该放的位置，
 * 然后对pivot两侧递归的进行处理，治时比较简单，每次操作后都有一个元素全局有序
 *
 * 归并排序的思想可以用来求解逆序对（逆序对可以衡量一组数据的有序程度）:hard493
 * 快速排序可以用来通过O(n)的时间复杂度获取第n大的数：medium215，medium347
 */
public class QuickSort implements Sort {

    private static Random random = new Random();

    // 对arr[l...r]部分进行快速排序
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int p = partition2(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p，使得 arr[l...p-1] < arr[p]; arr[p+1...r] > arr[p]
    private int partition(int[] arr, int l, int r) {

        // 随机化选取pivot
        int rPivot = Math.abs(random.nextInt()) % (r - l + 1) + l;
        ArrayUtil.swap(arr, l, rPivot);

        int v = arr[l];

        // arr[l+1...j] < v; arr[j+1...i) > v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                ArrayUtil.swap(arr, i, j + 1);
                j++;
            }
        }
        ArrayUtil.swap(arr, l, j);
        return j;
    }

    // 双路快排partition，避免数据存在大量重复时影响性能
    // (把重复的数据分别放到两部分，避免左右两部分不平衡)
    private int partition2(int[] arr, int l, int r) {

        // 随机化选取pivot
        int rPivot = Math.abs(random.nextInt()) % (r - l + 1) + l;
        ArrayUtil.swap(arr, l, rPivot);

        int v = arr[l];

        // arr[l+1...i)<=v; arr(j...r]>=v
        int i = l + 1, j = r;
        while (true) {
            while (i <= r && arr[i] < v) i++;
            while (j >= l + 1 && arr[j] > v) j--;
            if (i > j) break;

            ArrayUtil.swap(arr, i, j);
            i++;
            j--;
        }
        ArrayUtil.swap(arr, l, j);
        return j;
    }


    // 三路快排处理arr[l...r]
    // 将arr[l...r]分为 <v; ==v; >v三部分
    // 之后递归对 <v; >v 两部分继续进行三路快排
    public void quickSort3Way(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        // partition
        int rPivot = Math.abs(random.nextInt()) % (r - l + 1) + l;
        ArrayUtil.swap(arr, l, rPivot);

        int v = arr[l];

        int lt = l; // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1; // arr[lt+1...i) == v
        while (i < gt) {
            if (arr[i] < v) {
//                ArrayUtil.swap(arr, i, lt + 1);
//                lt++;
//                i++;
                ArrayUtil.swap(arr, i++, ++lt);
            } else if (arr[i] > v) {
//                ArrayUtil.swap(arr, i, gt - 1);
//                gt--;
                ArrayUtil.swap(arr, i, --gt);
            } else {
                // arr[i] == v
                i++;
            }
        }

        ArrayUtil.swap(arr, l, lt);

        quickSort3Way(arr, l, lt - 1);
        quickSort3Way(arr, gt, r);
    }


    @Override
    public void sort(int[] nums, int n) {
        //quickSort(nums, 0, n -1);
        quickSort3Way(nums, 0, n - 1);
    }
}
