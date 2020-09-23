package com.github.ryan.algorithm.leetcode.easy.easy1002;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> commonChars(String[] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<String>();
        }

        List<String> result = new ArrayList();
        int[] charCount = new int[26];
        for (char ch : A[0].toCharArray()) {
            charCount[ch - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            charCount = getCharCountBucket(A[i], charCount);
        }
        for (int i = 0; i < charCount.length; i++) {
            while (charCount[i] > 0) {
                result.add("" + (char)(i + 'a'));
                charCount[i]--;
            }
        }
        return result;
    }

    public int[] getCharCountBucket(String word, int[] charCount) {
        int[] charCountBucket = new int[26];
        for (char ch : word.toCharArray()) {
            if (charCount[ch - 'a'] > 0) {
                charCount[ch - 'a']--;
                charCountBucket[ch - 'a']++;
            }
        }
        return charCountBucket;
    }

}
