package com.github.ryan.algorithm.leetcode.easy.easy438;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date October 04,2018
 */
@Slf4j
public class Solution {


    // s长度为m，p长度为n
    // 时间复杂度为：O((m-n)*3n) = O((m-n)*n)
    public List<Integer> findAnagrams(String s, String p) {
        assert s != null && p != null;
        assert p.length() <= s.length();

        int sLen = s.length(), pLen = p.length();
        List<Integer> res = new ArrayList<>(sLen);
        int[] freq = new int[26]; // 查找表
        int l = 0, r = pLen - 1; // [l...r]窗口大小保持为p.length

        // O(m-n)
        while (l <= sLen - pLen) {

            // O(n)
            for (int i = l; i <= r; i++) {
                freq[s.charAt(i) - 'a']++;
            }

            // O(n)
            int i = 0;
            for (; i < p.length(); i++) {
                if (freq[p.charAt(i) - 'a'] == 0) {
                    break;
                } else {
                    freq[p.charAt(i) - 'a']--;
                }
            }

            if (i == p.length()) {
                res.add(l);
            }

            // O(n)
            for (int j = l; j <= r; j++) {
                freq[s.charAt(j) - 'a'] = 0;
            }
            l++;
            r++;
        }

        return res;
    }

    // 时间复杂度O(m + 26n)
    public List<Integer> findAnagrams2(String s, String p) {

        int[] pmap = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pmap[p.charAt(i) - 'a']++;
        }

        int i = 0, n = s.length();
        List<Integer> res = new ArrayList<>(n);
        int[] freq = new int[26];

        // O(n)
        for (int j = 0; j < n; j++) {
            freq[s.charAt(j) - 'a']++;

            // [i...j]窗口大小控制在p.length
            if (j - i >= p.length()) {
                freq[s.charAt(i) - 'a']--;
                i++;
            }

            // O(26)
            if (Arrays.equals(freq, pmap)) {
                res.add(i);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        //String s = "cbaebabacd", p = "abc";
        String s = "abab", p = "ab";
        log.info("Anagrams = {}", new Solution().findAnagrams2(s, p));
    }
}
