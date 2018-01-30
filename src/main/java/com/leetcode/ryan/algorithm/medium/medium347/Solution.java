package com.leetcode.ryan.algorithm.medium.medium347;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 29,2018
 */
@Slf4j
public class Solution {


    private static class Pair implements Comparable<Pair> {

        int freq;
        int num;

        public Pair(int freq, int num) {
            this.freq = freq;
            this.num = num;
        }

        @Override
        public int compareTo(Pair o) {
            return this.freq - o.freq;
        }
    }


    // 统计每个元素出现的频率：key(元素)-value(频率)
    private static Map<Integer, Integer> getFreqMap(int[] nums) {
        Map<Integer, Integer> freqMap = Maps.newHashMapWithExpectedSize(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (freqMap.get(nums[i]) == null) {
                freqMap.put(nums[i], 1);
            } else {
                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            }
        }
        return freqMap;
    }


    /**
     * 时间复杂度：O(nlogk)
     * Using priority queue to maintain top k elements
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {

        assert k > 0;
        Map<Integer, Integer> freqMap = getFreqMap(nums);
        assert k <= freqMap.size();

        List<Integer> res = new ArrayList<>();
        // 扫描freq，维护当前出现频率最高的k个元素
        // 在优先队列中，按照频率排序
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (pq.size() == k) {
                if (entry.getValue() > pq.peek().freq) {
                    pq.poll();
                    pq.add(new Pair(entry.getValue(), entry.getKey()));
                }
            } else {
                pq.add(new Pair(entry.getValue(), entry.getKey()));
            }
        }

        for (int i = 0; i < k; i++) {
            res.add(pq.poll().num);
        }

        return res;
    }

    /**
     * 时间复杂度: O(nlogn)
     * Using sort algorithm
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequentUseSort(int[] nums, int k) {
        assert k > 0;
        Map<Integer, Integer> freqMap = getFreqMap(nums);
        assert k <= freqMap.size();

        List<Integer> res = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> sortedRes = new ArrayList<>(freqMap.entrySet());
//        Collections.sort(sortedRes, new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return -(o1.getValue() - o2.getValue());
//            }
//        });
        Collections.sort(sortedRes, ((o1, o2) -> -(o1.getValue() - o2.getValue())));

        for (int i = 0; i < k; i++) {
            res.add(sortedRes.get(i).getKey());
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,1,1,1,2,2,2,2,3,3,3,4,4,4,4};
//        int k = 3;
        int[] nums = {3, 0, 1, 0};
        int k = 1;

        log.info("result = {}", topKFrequent(nums, k));
    }

}
