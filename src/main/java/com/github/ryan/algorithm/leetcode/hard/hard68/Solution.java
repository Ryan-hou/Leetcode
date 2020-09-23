package com.github.ryan.algorithm.leetcode.hard.hard68;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 01,2019
 */
public class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> res = new ArrayList<>();
        int num = 0;
        while (num < words.length) {
            int len = 0;
            int count = 0;
            int wordLen = 0;
            while (num < words.length) {
                if (len > 0) len++;
                len += words[num].length();
                // break to process one line
                if (len > maxWidth) break;
                wordLen += words[num].length();
                // add a word
                count++;
                num++;
            }
            res.add(getSentence(words, maxWidth, num - count, num, num == words.length, wordLen));
        }
        return res;
    }

    private String getSentence(String[] words, int maxWidth, int start, int end,
                               boolean lastLine, int wordLen) {

        StringBuilder b = new StringBuilder();
        int nspace = maxWidth - wordLen;
        if (end - start == 1) {
            // specail case: one word
            b.append(words[start]);
            addNSpace(b, nspace);
            return b.toString();
        } else if (lastLine) {
            // special case: last line
            for (int i = start; i < end; i++) {
                b.append(words[i]);
                if (i != end - 1) {
                    b.append(" ");
                    nspace--;
                }
            }
            addNSpace(b, nspace);
            return b.toString();
        } else {
            // normal case
            int wordNum = end - start;
            int avg = nspace / (wordNum - 1);
            int remainder = nspace % (wordNum - 1);
            for (int i = start; i < end; i++) {
                b.append(words[i]);
                if (i != end - 1) {
                    addNSpace(b, avg);
                }
                if (remainder > 0) {
                    b.append(" ");
                    remainder--;
                }
            }
            return b.toString();
        }
    }

    private void addNSpace(StringBuilder b, int n) {
        for (int i = 0; i < n; i++) {
            b.append(" ");
        }
    }
}
