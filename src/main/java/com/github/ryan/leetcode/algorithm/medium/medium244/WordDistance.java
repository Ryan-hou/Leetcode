package com.github.ryan.leetcode.algorithm.medium.medium244;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
public class WordDistance {

    private Map<String, List<Integer>> locDict;

    public WordDistance(String[] words) {
        locDict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            locDict.putIfAbsent(w, new ArrayList<>());
            locDict.get(w).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> loc1 = locDict.get(word1);
        List<Integer> loc2 = locDict.get(word2);

        int idx1 = 0, idx2 = 0;
        int minDis = Integer.MAX_VALUE;
        while (idx1 < loc1.size() && idx2 < loc2.size()) {
            minDis = Math.min(minDis, Math.abs(loc1.get(idx1) - loc2.get(idx2)));
            if (loc1.get(idx1) < loc2.get(idx2)) {
                idx1++;
            } else {
                idx2++;
            }
        }
        return minDis;
    }
}
