package com.github.ryan.algorithm.leetcode.easy.easy350;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 21,2018
 */
@Slf4j
public class Solution {


    /**
     * 使用map进行查询操作
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {

        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> nums1Map = new HashMap<>(nums1.length);
        for (int num : nums1) {
            if (nums1Map.get(num) == null) {
                nums1Map.put(num, 1);
            } else {
                nums1Map.put(num, nums1Map.get(num) + 1);
            }
        }

        List<Integer> res = new ArrayList<>(nums1.length);
        for (int num : nums2) {
            if (nums1Map.get(num) != null && nums1Map.get(num) > 0) {
                res.add(num);
                nums1Map.put(num, nums1Map.get(num) - 1);
            }
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        log.info("result = {}", Arrays.toString(intersect(nums1, nums2)));
    }

}
