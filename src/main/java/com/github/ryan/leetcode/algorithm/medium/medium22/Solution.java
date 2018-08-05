package com.github.ryan.leetcode.algorithm.medium.medium22;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 28,2018
 */
@Slf4j
public class Solution {

    /**
     * The idea here is to only add '(' and ')' that we know will guarantee
     * us a solution.
     * Once we add a '(' we will then discard it and try a ')' which can only
     * close a valid '('. Each of these steps are recursively called.
     *
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private static void backtrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            //System.out.println("res  " + str);
            return;
        }

        if (open < max) {
            //System.out.println(str);
            backtrack(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            //System.out.println(str);
            backtrack(list, str + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        log.info("res = {}", generateParenthesis(n));
    }
}
