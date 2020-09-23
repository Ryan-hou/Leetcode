package com.github.ryan.algorithm.leetcode.easy.easy118;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 09,2019
 */
public class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            if (i == 0) {
                res.add(list);
                continue;
            }

            List<Integer> prev = res.get(i - 1);
            for (int j = 1; j < i; j++) {
                list.add(prev.get(j) + prev.get(j - 1));
            }
            list.add(1);
            res.add(list);
        }
        return res;
    }

}
