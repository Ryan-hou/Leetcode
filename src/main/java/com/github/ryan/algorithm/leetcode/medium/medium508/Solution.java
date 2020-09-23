package com.github.ryan.algorithm.leetcode.medium.medium508;

import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private int maxFreq;
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        maxFreq = 0;
        // key: sum, value: frequency of this sum
        Map<Integer, Integer> sumFreqMap = new HashMap<>();
        calcSubtreeSum(sumFreqMap, root);

        List<Integer> res = new ArrayList<>();
        for (int sum : sumFreqMap.keySet()) {
            if (sumFreqMap.get(sum) == maxFreq) {
                res.add(sum);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
    private int calcSubtreeSum(Map<Integer, Integer> sumFreqMap, TreeNode root) {
        if (root == null) return 0;
        int left = calcSubtreeSum(sumFreqMap, root.left);
        int right = calcSubtreeSum(sumFreqMap, root.right);
        int curSum = root.val + left + right;
        sumFreqMap.put(curSum, sumFreqMap.getOrDefault(curSum, 0) + 1);
        maxFreq = Math.max(maxFreq, sumFreqMap.get(curSum));
        return curSum;
    }
}
