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
    }

}
