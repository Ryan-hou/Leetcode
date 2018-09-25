package com.github.ryan.leetcode.algorithm.medium.medium347;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;

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
//        for (int i = 0; i < nums.length; i++) {
//            if (freqMap.get(nums[i]) == null) {
//                freqMap.put(nums[i], 1);
//            } else {
//                freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
//            }
//        }
        for (int n : nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }
        return freqMap;
    }


    /**
     * 法一：
     * 时间复杂度：O(nlogk)
     * Using priority queue to maintain top k elements
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {

        // k的合法性题目已经规定

        // O(n)
        Map<Integer, Integer> freqMap = getFreqMap(nums);
        List<Integer> res = new ArrayList<>(k);

        // 扫描freqMap，维护当前出现频率最高的k个元素
        // 在优先队列中，按照频率排序
        // nlog(k)
        Queue<Pair> pq = new PriorityQueue<>();
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

        // klogk
        for (int i = 0; i < k; i++) {
            res.add(pq.poll().num);
        }
        return res;
    }

    /**
     * 法二：
     * 时间复杂度: O(nlogn)
     * Using sort algorithm
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequentUseSort(int[] nums, int k) {

        Map<Integer, Integer> freqMap = getFreqMap(nums);

        List<Integer> res = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> sortedRes = new ArrayList<>(freqMap.entrySet());
//        Collections.sort(sortedRes, new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
//                return -(o1.getValue() - o2.getValue());
//            }
//        });
        // nlogn
        Collections.sort(sortedRes, ((o1, o2) -> -(o1.getValue() - o2.getValue())));

        for (int i = 0; i < k; i++) {
            res.add(sortedRes.get(i).getKey());
        }
        return res;
    }

    // 法三：
    // 使用数组下标作为数字频率，数组元素为所有该频率的数字
    // 空间换时间，O(n)
    public static List<Integer> topKFrequentUseArray(int[] nums, int k) {

        Map<Integer, Integer> freqMap = getFreqMap(nums);

        List<Integer>[] bucket = new List[nums.length + 1];
        // O(n)
        for (int num : freqMap.keySet()) {
            int freq = freqMap.get(num);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(num);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i > 0 && k > 0; i--) {
            if (bucket[i] != null) {
                List<Integer> sameFreqList = bucket[i];
                res.addAll(sameFreqList);
                k -= sameFreqList.size();
            }
        }
        return res;
    }

    // 法四：
    // 使用基于红黑树的有序Map：TreeMap
    // Use TreeMap. Use frequency as the key, so we can get all frequencies in order
    public static List<Integer> topKFrequentUseTreeMap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = getFreqMap(nums);

        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int num : freqMap.keySet()) {
            int freq = freqMap.get(num);
            if (!treeMap.containsKey(freq)) {
                treeMap.put(freq, new LinkedList<>());
            }
            treeMap.get(freq).add(num);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            Map.Entry<Integer, List<Integer>> entry = treeMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }

    // 法五：
    // 使用快排partition思路，先找到满足条件的第k个元素的下标，然后从该下标到最后一个元素即为问题的解
    // 时间复杂度O(n)
    public static List<Integer> topKFrequentUsePartition(int[] nums, int k) {
        Map<Integer, Integer> freqMap = getFreqMap(nums);
        Pair[] pairs = convertMap2Array(freqMap);

        int index = quickSelect(pairs, 0, pairs.length - 1, k);

        List<Integer> res = new ArrayList<>(k);
        for (int i = index; i < pairs.length; i++) {
            res.add(pairs[i].num);
        }
        return res;
    }

    // 返回pairs[l...r]中k most frequent elements的下标
    private static int quickSelect(Pair[] pairs, int l, int r, int k) {
        int p = partition(pairs, l, r);

        if (p == pairs.length - k) {
            return p;
        }

        if (p > pairs.length - k) {
            return quickSelect(pairs, l, p - 1, k);
        } else {
            return quickSelect(pairs, p + 1, r, k);
        }

    }

    // 对 pairs[l...r]进行partition操作
    // 返回p，使 pairs[l...p-1].freq < pairs[p].freq, pairs[p + 1, r].freq >= pairs[p].freq
    private static int partition(Pair[] pairs, int l, int r) {
        // 随机化pivot
        Random random = new Random();
        int pivot = Math.abs(random.nextInt() % (r - l + 1)) + l;
        swap(pairs, l, pivot);

        Pair pair = pairs[l];

        // pairs[l+1...j].freq < pair.freq, pairs[j+1...i).freq >= pair.freq
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (pairs[i].freq < pair.freq) {
                swap(pairs, i, j + 1);
                j++;
            }
        }
        swap(pairs, l, j);
        return j;
    }

    private static Pair[] convertMap2Array(Map<Integer, Integer> freqMap) {
        Pair[] pairs = new Pair[freqMap.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            Pair pair = new Pair(entry.getValue(), entry.getKey());
            pairs[i++] = pair;
        }
        return pairs;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,2,2,2,2,3,3,3,4,4,4,4};
        int k = 3;
//        int[] nums = {3, 0, 1, 0};
//        int k = 1;

        log.info("result = {}", topKFrequent(nums, k));
        log.info("result = {}", topKFrequentUseArray(nums, k));
        log.info("result = {}", topKFrequentUseTreeMap(nums, k));
        log.info("result = {}", topKFrequentUsePartition(nums, k));
    }

}
