package com.github.ryan.algorithm.leetcode.easy.easy119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date January 04,2019
 */
public class Solution {

    // 法一：最直接，两层循环，空间复杂度O(n^2)
    public List<Integer> getRow(int rowIndex) {
        assert rowIndex >= 0;

        List<Integer> pre = new ArrayList<>(Arrays.asList(1));
        List<Integer> res = new ArrayList<>(Arrays.asList(1));
        for (int i = 1; i <= rowIndex; i++) {
            res.clear();
            res.add(1);
            for (int j = 0; (j + 1) < pre.size(); j++) {
                res.add(pre.get(j) + pre.get(j + 1));
            }
            res.add(1);
            pre = new ArrayList<>(res);
        }
        return res;
    }

    // 法二：使用递归
    public List<Integer> getRow2(int rowIndex) {
        assert rowIndex >= 0;

        // 递归出口
        if (rowIndex == 0) {
            return Arrays.asList(1);
        }

        List<Integer> pre = getRow2(rowIndex - 1);
        List<Integer> res = new ArrayList<>();
        int init = 0;
        for (int n : pre) {
            res.add(init + n);
            init = n;
        }
        res.add(1);
        return res;
    }

    // 法三：空间复杂度O(n)
    /**
     * When generating each row, we can use the previous row directly,
     * so this way we only use O(k) space with k being the number of row.
     * For each new row, we append a 1,
     * letting j iterate from i - 1 backward to 1,
     * and set the jth element as res.set(j, res.get(j-1) + res.get(j)).
     * For example, when k = 4, the process goes like this:
     k == 0
     [1]
     k == 1
     [11]
     k == 2
     [111]  add 1
     [121]  calculate jth spot
     k == 3
     [1211]  add 1
     [1331]   calculate jth spot
     k == 4
     [13311]  add 1
     [14641]  calculate jth spot
     */
    public List<Integer> getRow3(int rowIndex) {
        assert rowIndex >= 0;

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            res.add(1);
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }
}
