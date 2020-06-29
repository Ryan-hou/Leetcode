package com.github.ryan.leetcode.algorithm.hard.hard843;

import java.util.*;

public class Solution {

    // This is the Master's API interface.
    // You should not implement it, or speculate about its implementation
    public interface Master {
        int guess(String word);
    }

    private static final Random r = new Random();

    public void findSecretWord(String[] wordlist, Master master) {
        List<String> list = new ArrayList<>(Arrays.asList(wordlist));

        for (int i = 0; i < 10; i++) {
            int idx = r.nextInt(list.size());
            String cur = list.get(idx);
            int same = master.guess(cur);
            if (same == 6) return;

            Iterator<String> iter = list.iterator();
            while (iter.hasNext()) {
                String w = iter.next();
                if (match(cur, w) != same) {
                    iter.remove();
                }
            }
        }
    }



    private int match(String s1, String s2) {
        int match = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                match++;
            }
        }
        return match;
    }

}
