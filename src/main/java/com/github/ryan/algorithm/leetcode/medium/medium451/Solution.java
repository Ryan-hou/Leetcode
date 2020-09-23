package com.github.ryan.algorithm.leetcode.medium.medium451;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 07,2018
 */
public class Solution {

    // 使用HashMap统计频率，使用堆处理顺序
    public String frequencySort(String s) {
        if (s == null) return "";

        char[] sArr = s.toCharArray();
        Map<Character, Integer> cMap = new HashMap<>();
        for (char c : sArr) {
            cMap.put(c, cMap.getOrDefault(c, 0) + 1);
        }

        // 不同的Character数量有限
        PriorityQueue<Map.Entry<Character, Integer>> pq
                = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        pq.addAll(cMap.entrySet());

        StringBuilder res = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                res.append(entry.getKey());
            }
        }
        return res.toString();
    }

    // 使用桶排序的思路
    public String frequencySort2(String s) {
        if (s == null) return "";

        char[] sArr = s.toCharArray();
        Map<Character, Integer> cMap = new HashMap<>();
        for (char c : sArr) {
            cMap.put(c, cMap.getOrDefault(c, 0) + 1);
        }


        // 借助桶排序的思路，把桶下标作为字符出现的次数，桶内字符串作为出现次数为
        // 桶下标的所有字符
        StringBuilder[] bucket = new StringBuilder[sArr.length + 1];
        for (Map.Entry<Character, Integer> entry : cMap.entrySet()) {
            Character c = entry.getKey();
            Integer v = entry.getValue();
            for (int i = 0; i < v; i++) {
                if (bucket[v] == null) {
                    bucket[v] = new StringBuilder();
                }
                bucket[v].append(c);
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = sArr.length; i > 0; i--) {
            if (bucket[i] != null) {
                res.append(bucket[i]);
            }
        }

        return res.toString();
    }
}
