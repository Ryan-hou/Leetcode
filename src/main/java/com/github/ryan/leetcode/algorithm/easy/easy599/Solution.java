package com.github.ryan.leetcode.algorithm.easy.easy599;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 12,2018
 */
@Slf4j
public class Solution {

    // 使用hash table
    public static String[] findRestaurant(String[] list1, String[] list2) {
        assert list1 != null && list2 != null;

        int minIndexSum = Integer.MAX_VALUE;
        List<String> res = new ArrayList<>();
        Map<String, Integer> findMap = Maps.newHashMapWithExpectedSize(list1.length);

        for (int i = 0; i < list1.length; i++) {
            findMap.put(list1[i], i);
        }

        for (int i = 0; i < list2.length; i++) {
            Integer index = findMap.get(list2[i]);
            if (index != null && (index + i) <= minIndexSum) {
                if (index + i < minIndexSum) {
                    res.clear();
                    // 更新minIndexSum
                    minIndexSum = index + i;
                }
                res.add(list2[i]);
            }
        }

        return res.toArray(new String[res.size()]);
    }

    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};
        log.info("Result = {}", (Object) findRestaurant(list1, list2));
    }
}
