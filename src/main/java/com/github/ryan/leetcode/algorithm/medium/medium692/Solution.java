package com.github.ryan.leetcode.algorithm.medium.medium692;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date March 19,2019
 */
@Slf4j
public class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>(words.length);
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        // min heap
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((e1, e2) -> e1.getValue().equals(e2.getValue())
                        ? -e1.getKey().compareTo(e2.getKey())
                        : e1.getValue().compareTo(e2.getValue()));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<String> res = new LinkedList<>();
        // so stupid !
        // for (Map.Entry<String, Integer> entry : pq) {
        //   res.add(0, entry.getKey());
        // }
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 3;
        log.info("top k words are: {}", new Solution().topKFrequent(words, k));
    }
}
