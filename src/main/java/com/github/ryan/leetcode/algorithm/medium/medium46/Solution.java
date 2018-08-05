package com.github.ryan.leetcode.algorithm.medium.medium46;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 02,2018
 */

/**
 * 排列问题(树形问题，可以画出递归树)：
 * 使用递归(回溯)，在回溯到相应的解后，注意状态的恢复
 */

@Slf4j
public class Solution {

    private static List<List<Integer>> res = new ArrayList<>();
    private static boolean[] used;

    public static List<List<Integer>> permute(int[] nums) {
        res.clear();
        if (nums.length == 0) {
            return res;
        }

        used = new boolean[nums.length];
        List<Integer> p = new ArrayList<>();
        generatePermutation(nums, 0, p);

        return new ArrayList<>(res);
    }

    /**
     * p保存了一个有index个元素的排列
     * 向这个排列的末尾增加第index+1个元素，获得一个有 index+1 个元素的排列
     * @param nums
     * @param index
     * @param p
     */
    private static void generatePermutation(int[] nums, int index, List<Integer> p) {

        if (index == nums.length) {
            System.out.println("res add" + p + ", return");

            res.add(new ArrayList<>(p)); // wrong code: res.add(p)
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                System.out.println("p = " + p + ", add " + nums[i]);

                // 将nums[i]添加在p中
                p.add(nums[i]);
                used[i] = true;
                generatePermutation(nums, index + 1, p);
                p.remove((Integer) nums[i]); // remove(Object) vs remove(int index)
                used[i] = false;
            }
        }
        return;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        permute(nums);
        log.info("result = {}", res);
    }

}
