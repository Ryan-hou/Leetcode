package com.github.ryan.algorithm.leetcode.medium.medium17;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 01,2018
 */

/**
 * 树形问题：
 * 使用递归(回溯法)来解决，时间复杂度为指数级O(2^n)
 * 回溯法用来搜索一个解，通常为暴力解法，可以在某些条件下改写为动态规划算法，通过剪枝来减少计算量
 *
 * 合理的打印输出执行轨迹：
 * 在程序中通过合理的打印输出可以更好的了解程序的运行过程以及查找问题，很多时候比debug更有效
 * 合理的打印输出是一种艺术，你需要知道程序的哪个位置是执行的关键点，然后打印出相关信息
 */
@Slf4j
public class Solution {

    // 数组: 天然的map，数组下标作为map的key
    private static final String[] letterMap = {
         " ",  //0
         "",   //1
         "abc",  //2
         "def",  //3
         "ghi",  //4
         "jkl",  //5
         "mno",  //6
         "pqrs",  //7
         "tuv",  //8
         "wxyz",  //9
    };

    private static List<String> res = new ArrayList<>();


    /**
     * s 中保存了此时从digits[0,index-1]翻译得到的一个字母字符串
     * 寻找和digits[index]匹配的字母，获得digits[0,index]翻译得到的解
     * @param digits
     * @param index
     * @param s
     */
    private static void findCombination(char[] digits, int index, String s) {

        // 打印执行结果
        System.out.println(index + ": " + s);

        if (index == digits.length) {
            res.add(s);

            System.out.println("get " + s + ", return");
            return;
        }

        char c = digits[index];
        char[] letters = letterMap[c - '0'].toCharArray();
        for (int i = 0; i < letters.length; i++) {
            // 打印执行结果
            System.out.println("digits[" + index + "], use " + letters[i]);

            findCombination(digits, index + 1, s + letters[i]);
        }
        System.out.println("digits[" + index + "] = " + c + " complete, return");

        return;
    }

    public static List<String> letterCombinations(String digits) {

        res.clear();
        if ("".equals(digits)) {
            return new ArrayList<>();
        }

        findCombination(digits.toCharArray(), 0, "");
        return new ArrayList<>(res);
    }

    public static void main(String[] args) {
        String digits = "23";
        log.info("Result = {}", letterCombinations(digits));
    }

}
