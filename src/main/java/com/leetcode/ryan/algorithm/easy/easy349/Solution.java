package com.leetcode.ryan.algorithm.easy.easy349;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 19,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用 Set 集合的性质来求交集:
     * 使用api和lambda，代码简洁；但是lambda 表达式的使用，自动装箱拆箱会损失性能
     */
    public static int[] intersection(int[] nums1, int[] nums2) {

        Integer[] nums1Int = IntStream.of(nums1).boxed().toArray(Integer[]::new);
        Integer[] nums2Int = IntStream.of(nums2).boxed().toArray(Integer[]::new);

        Set<Integer> nums1Set = new HashSet<>(Arrays.asList(nums1Int));
        Set<Integer> nums2Set = new HashSet<>(Arrays.asList(nums2Int));

        nums1Set.retainAll(nums2Set);

        return nums1Set.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 完全使用 primitive type 的数组操作，不使用 Set 集合类，效率更高
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersectionTwo(int[] nums1, int[] nums2) {
        // corner case
        if (nums1 == null || nums1.length == 0
                || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        int max = nums1[0], min = nums1[0];
        for (int i = 1; i < nums1.length; i++) {
            if (nums1[i] > max) {
                max = nums1[i];
            } else if (nums1[i] < min) {
                min = nums1[i];
            }
        }

        // 使用数组作为map，数组下标作为key
        boolean[] bucket = new boolean[max - min + 1];
        for (int num : nums1) {
            bucket[num - min] = true;
        }

        List<Integer> res = new ArrayList<>(max - min + 1);
        for (int num : nums2) {
            if (num >= min && num <= max) {
                if (bucket[num - min]) {
                    res.add(num);
                    bucket[num - min] = false;
                }
            }
        }

        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 3, 5, 6, 8};
        int[] nums2 = {2, 2, 8, 9, 0};
        log.info("result = {}", Arrays.toString(intersectionTwo(nums1, nums2)));
    }
}
