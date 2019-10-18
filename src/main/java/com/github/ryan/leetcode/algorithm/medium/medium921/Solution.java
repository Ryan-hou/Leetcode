package com.github.ryan.leetcode.algorithm.medium.medium921;

public class Solution {

    public int minAddToMakeValid(String S) {
        int res = 0;
        // balance of the string: the number of '(' minus the
        // number of ')'
        int bal = 0;
        for (char ch : S.toCharArray()) {
            bal += ch == '(' ? 1 : -1;
            // It is guaranteed bal >= -1
            if (bal == -1) {
                res++;
                bal++;
            }
        }
        return res + bal;
    }

}
