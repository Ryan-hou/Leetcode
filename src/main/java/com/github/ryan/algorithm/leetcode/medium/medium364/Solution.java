package com.github.ryan.algorithm.leetcode.medium.medium364;

import java.util.ArrayList;
import java.util.List;
import static com.github.ryan.algorithm.leetcode.easy.easy339.Solution.NestedInteger;

public class Solution {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() < 1) return 0;

        List<Integer> levelSum = new ArrayList<>();
        depthSum(nestedList, 0, levelSum);
        int depth = levelSum.size();
        int res = 0;
        for (int num : levelSum) {
            res += num * depth;
            depth--;
        }
        return res;
    }

    // level traversal
    private void depthSum(List<NestedInteger> nestedList, int level, List<Integer> levelSum) {
        if (level + 1 > levelSum.size()) {
            levelSum.add(0);
        }
        int sum = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                sum += ni.getInteger();
            } else {
                depthSum(ni.getList(), level + 1, levelSum);
            }
        }
        levelSum.set(level, levelSum.get(level) + sum);
    }

}
