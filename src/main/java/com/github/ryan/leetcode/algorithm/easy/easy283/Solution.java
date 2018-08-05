package com.github.ryan.leetcode.algorithm.easy.easy283;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 07,2018
 */
public class Solution {

    /**
     * 不考虑题目限制条件，最简单直接的方式，开辟新的数组空间
     * 时间复杂度/空间复杂度均为 O(n)
     */
    public static void moveZeroesOne(int[] nums) {

        int numsLen = nums.length;
        int[] nonZeroArray = new int[numsLen];

        for (int i = 0, j = 0; i < numsLen; i++) {
            if (nums[i] != 0) {
                nonZeroArray[j++] = nums[i];
            }
        }

        for (int i = 0, length = nonZeroArray.length; i < length; i++) {
            nums[i] = nonZeroArray[i];
        }

        for (int i = nonZeroArray.length; i < numsLen; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 定义两个指针，通过覆盖的方式移动数字
     * 空间复杂度O(1)
     * @param nums
     */
    public static void moveZeroesTwo(int[] nums) {

        int k = 0; // nums中，[0,k)的元素均为非零元素(循环不变量)
        // 遍历到第i个元素后，保证[0,i]中所有非零元素都按照顺序排列在[0,k)中
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }

        // [k,nums.length) 赋值为零
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 定义两个指针，通过元素交换操作数字
     * 时间复杂度O(n) 空间复杂度O(1)
     * @param nums
     */
    public static void moveZeroes(int[] nums) {

        int k = 0; // nums中，[0,k)的元素均为非零元素
        // 遍历到第i个元素后，保证[0,i]中所有的非零元素都按照顺序排列在[0,k)中
        // 同时,[k,i]为0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                    k++;
                } else {
                    k++; // i==k(避免数组元素不为0进行多余的数据交换操作)
                }


            }
        }

    }



    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

}
