package com.github.ryan.leetcode.algorithm.hard.hard316;

import java.util.Stack;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 23,2017
 */
public class Solution {

    /**
     * 法一:
     * 采用栈的记忆特性
     * 思路: 把不重复的,满足最小字典序的字符保存到栈中
     * 核心:
     * when to push a character into the Stack?
     * 当前字符在栈中没有出现
     * when to remove a character from the Stack?
     * 1) Stack is not null
     * 2) 当前字符的字典序小于栈顶字符
     * 3) 栈顶元素在后面还会再出现
     * 操作: 利用数组天然的 Map 特性,key 为数组下标, value 为待保存的数据
     * 1) boolean[] visited数组: 用于保存字符是否已被入栈
     * 2) int[] count数组: 用于保存每个字符出现的次数,判断是否可以将字符出栈
     * @param s
     * @return
     */
    public static String removeDuplicateLettersOne(String s) {
        if (s == null) {
            return "";
        }

        int[] count = new int[26];
        char[] chrArray = s.toCharArray();
        for (char c : chrArray) {
            count[c-'a']++;
        }
        boolean[] visited = new boolean[26];

        // 把操作结果保存到栈中
        Stack<Character> result = new Stack<>();
        for (char c : chrArray) {
            count[c-'a']--;
            if (visited[c-'a']) {
                continue;
            }
            while (!result.empty() && count[result.peek()-'a'] > 0 && c < result.peek()) {
                // 字符出栈: while 语句定义了栈顶元素出栈的条件
                char top = result.pop();
                visited[top-'a'] = false;
            }
            // 字符入栈
            visited[c-'a'] = true;
            result.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 法二:
     * 使用贪心,每次找出一个满足最小字典序的字符,然后递归的处理剩余的字符串
     * @param s
     * @return
     */
    public static String removeDuplicateLettersTwo(String s) {
        if (s == null) return "";

        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        // 每次找出一个满足最小字典序的字符
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            // 如果当前字符在后面不再出现,则退出
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        // 递归调用 -- 递归出口是字符串为空串
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLettersTwo(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    public static void main(String[] args) {
        String test = "bcabc";
        String result = removeDuplicateLettersTwo(test);
        System.out.println("Result String: " + result);
    }
}
