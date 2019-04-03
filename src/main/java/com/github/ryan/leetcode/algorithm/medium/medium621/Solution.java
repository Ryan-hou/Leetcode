package com.github.ryan.leetcode.algorithm.medium.medium621;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 02,2019
 */
public class Solution {

    // use priority queue: always process task with most frequency first
    // https://leetcode.com/problems/task-scheduler/discuss/259218/Java-solution-using-priority-queue-with-explanation
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        if (n == 0) return tasks.length;

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        // min heap
        PriorityQueue<Map.Entry<Character, Integer>> sortedTasks
                = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            sortedTasks.offer(entry);
        }

        int intervals = 0;
        while (!sortedTasks.isEmpty()) {
            List<Map.Entry<Character, Integer>> tasksInPeriod = new ArrayList<>();
            int period = n + 1;
            while (period > 0 && !sortedTasks.isEmpty()) {
                Map.Entry<Character, Integer> curTask = sortedTasks.poll();
                curTask.setValue(curTask.getValue() - 1);
                tasksInPeriod.add(curTask);
                period--;
                intervals++;
            }

            // process remaining tasks in period
            for (Map.Entry<Character, Integer> remainedTask : tasksInPeriod) {
                if (remainedTask.getValue() > 0) {
                    sortedTasks.offer(remainedTask);
                }
            }

            if (!sortedTasks.isEmpty() && period > 0) {
                intervals += period;
            }
        }
        return intervals;
    }


    // The core idea: calculate the idles
    // https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
    public int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        if (n == 0) return tasks.length;

        int[] freq = new int[26]; // index: ch - 'A' -> val: ch's frequency
        int max = 0; // maximum frequency
        int maxCount = 0;
        for (char ch : tasks) {
            freq[ch - 'A']++;
            int chFreq = freq[ch - 'A'];
            if (max == chFreq) {
                maxCount++;
            } else if (max < chFreq) {
                maxCount = 1;
                max = chFreq;
            } // else: max > chFreq, do nothing
        }

        int taskNum = tasks.length;
        int parts = max - 1;
        int partLen = n - (maxCount - 1);
        int emptySpots = parts * partLen;
        int availableTasks = taskNum - max * maxCount;
        int idles = Math.max(0, emptySpots - availableTasks);
        return taskNum + idles;
    }
}
