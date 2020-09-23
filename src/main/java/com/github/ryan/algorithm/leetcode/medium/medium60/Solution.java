package com.github.ryan.algorithm.leetcode.medium.medium60;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 16,2019
 */
@Slf4j
public class Solution {

    private int idx;
    private String res;

    public String getPermutation(int n, int k) {
        idx = k;
        res = null;
        List<Integer> set = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            set.add(i);
        }

        perm(n, set, new ArrayList<>());
        return res;
    }

    private void perm(int n, List<Integer> set, List<Integer> list) {
        if (res != null) return;
        if (list.size() == n) {
            idx--;
            if (idx == 0) {
                StringBuilder b = new StringBuilder();
                for (Integer num : list) {
                    b.append(num);
                }
                res = b.toString();
            }
            return;
        }

        for (int i = 0; i < set.size(); i++) {
            int val = set.remove(i);
            list.add(val);
            perm(n, set, list);
            set.add(i, val);
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        String res = new Solution().getPermutation(3, 3);
        log.info("res = {}", res);
    }
}
