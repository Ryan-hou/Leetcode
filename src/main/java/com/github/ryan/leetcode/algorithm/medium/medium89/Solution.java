package com.github.ryan.leetcode.algorithm.medium.medium89;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 10,2018
 */
public class Solution {

    public List<Integer> grayCode(int n) {
        assert n >= 0;

        List<Integer> res = new ArrayList<>();
        BitSet bs = new BitSet(n); // 初始为 n bit 的 false
        helper(res, bs, n);
        return res;
    }

    private void helper(List<Integer> res, BitSet bs, int n) {
        if (n == 0) {
            // 递归出口，bitset 的n位都访问到了
            res.add(bitSet2Int(bs));
            return;
        }

        // backtracking
        helper(res, bs, n - 1);
        bs.flip(n - 1);
        helper(res, bs, n - 1);
    }

    private int bitSet2Int(BitSet bs) {
        assert bs != null;

        int res = 0;
        for (int i = 0; i < bs.length(); i++) {
            res += (bs.get(i) ? 1 << i : 0);
        }
        return res;
    }

}
