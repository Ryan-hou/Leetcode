package com.leetcode.ryan.personal.learn;

import com.leetcode.ryan.personal.util.MyUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BinarySearch
 * @date January 07,2018
 */
@Slf4j
public class BinarySearch {

    /**
     * 时间复杂度 O(logn), logn是比n要优秀的多的时间复杂度
     * @param arr
     * @param n
     * @param target
     * @return
     */
    static int binarySearch(int[] arr, int n, int target) {

        int l = 0, r = n -1; // 在 [l...r] 的范围里寻找target（这个定义就是循环不变量，在下面的循环中要维护这个定义）
        while (l <= r) { // 当 l==r 时，区间[l...r] 依然有效
            int mid = l + (r - l) / 2; // 避免整型溢出
            if (arr[mid] == target) {
                return mid;
            }

            if (target > arr[mid]) {
                l = mid + 1; // target 在[mid+1...r]中
            } else {
                r = mid - 1; // target 在[l...mid-1] 中
            }
        }

        return -1;
    }

    /**
     * 递归调用：
     * 时间复杂度: O(logn)
     * 如果递归函数中，只进行一次递归调用，递归深度为depth
     * 在每个递归函数中，时间复杂度为 T
     * 则总体的时间复杂度为 O(T*depth)
     *
     * 如果在递归中进行多次递归调用，计算调用的次数(其实只进行一次递归调用，通过递归的深度和每次递归的
     * 复杂度计算出来的也是调用的次数)
     * 如：f(n)=f(n-1)+f(n-1) 时间复杂度为递归数的节点数O(2^n) （指数级别，一般
     * 需要通过剪枝来优化到多项式级别）
     *
     * 更复杂的递归时间复杂度，参见主定理
     * @param arr
     * @param l
     * @param r
     * @param target
     * @return
     */
    static int binarySearchWithRecur(int[] arr, int l, int r, int target) {
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l)/2;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return binarySearchWithRecur(arr, l, mid - 1, target);
        } else {
            return binarySearchWithRecur(arr, mid + 1, r, target);
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        int[] data = MyUtil.generateOrderedArray(n);

        long timeMillis = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            assert (i == binarySearch(data, n, i));
        }
        long endTimeMillis = System.currentTimeMillis();

        log.info("binarySearch test completed");
        // O(logn) 复杂度是非常优异的时间复杂度，可以在1秒内处理百万数据
        log.info("Time cost: {} milliseconds", (endTimeMillis - timeMillis));

        int[] nums = {2, 4, 5, 7, 8};
        log.info("target index = {}", binarySearchWithRecur(nums, 0, nums.length - 1, 5));
    }

}
