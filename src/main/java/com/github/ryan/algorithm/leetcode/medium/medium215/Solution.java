package com.github.ryan.algorithm.leetcode.medium.medium215;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date September 19,2018
 */
@Slf4j
public class Solution {

    // 法一：
    // 最直观的方法，使用排序，时间复杂度O(nlogn)
    // 排序后整个数组有序，比题目要求的第K大要严格，因此时间复杂度肯定高了
    public int findKthLargest1(int[] nums, int k) {
        assert nums != null && k >= 1 && k <= nums.length;

        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length - k];
    }


    // 对nums[l...r]部分进行快速排序
    public void quickSort(int[] nums, int l, int r) {
        if (l >= r) return; // 递归出口
        int p = partition(nums, l, r);
        quickSort(nums, l, p - 1);
        quickSort(nums, p + 1, r);
    }

    // 对nums[l...r]进行partition操作
    // 返回p,使 nums[l...p-1] < nums[p], nums[p+1,r] >= nums[p]
    private int partition(int[] nums, int l, int r) {
        // 随机化pivot
        Random random = new Random();
        int pivot = Math.abs(random.nextInt() % (r - l + 1)) + l;
        swap(nums, l, pivot);

        int v = nums[l];

        // nums[l+1...j] < v, nums[j+1...i) >= v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] < v) {
                swap(nums, i, j + 1);
                j++;
            }
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // 法二：
    // 借助快排的partition操作，不需要使整个数组有序
    // 时间复杂度O(n)
    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int l, int r, int k) {

        int p = partition(nums, l, r);
        if (p == nums.length - k) {
            return nums[p];
        }

        if (p > nums.length - k) {
            return quickSelect(nums, l, p - 1, k);
        } else {
            return quickSelect(nums, p + 1, r, k);
        }
    }

    // 法三：
    // 使用优先队列（最小堆）
    public int findKthLargest3(int[] nums, int k) {

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }

        // O((n-k)logk)
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        //int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        //int k = 4;
        int[] nums = {99, 99};
        int k = 1;

        //new Solution().quickSort(nums, 0, nums.length - 1);
        //log.info("Sorted array = {}", Arrays.toString(nums));
        log.info("{}th largest number is {}", k, new Solution().findKthLargest1(nums, k));
        log.info("{}th largest number is {}", k, new Solution().findKthLargest2(nums, k));
        log.info("{}th largest number is {}", k, new Solution().findKthLargest3(nums, k));
    }
}
