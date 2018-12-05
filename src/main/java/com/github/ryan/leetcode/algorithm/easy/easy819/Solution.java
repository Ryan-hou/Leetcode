package com.github.ryan.leetcode.algorithm.easy.easy819;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 05,2018
 */
@Slf4j
public class Solution {

    public String mostCommonWord(String paragraph, String[] banned) {
        assert banned != null && paragraph != null;

        Set<String> bannedSet = new HashSet<>();
        for (String s : banned) {
            bannedSet.add(s);
        }

        Map<String, Integer> notbannedMap = new HashMap<>();
        String[] split = paragraph.split("[^A-Za-z]");
        for (String s : split) {
            s = s.toLowerCase();
            if (!s.trim().equals("") && !bannedSet.contains(s)) {
                notbannedMap.put(s, notbannedMap.getOrDefault(s, 0) + 1);
            }
        }

        int maxCount = 0;
        String res = "";
        for (Map.Entry<String, Integer> entry : notbannedMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                res = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};

        log.info("mostCommonWord = {}", new Solution().mostCommonWord(paragraph, banned));
    }

}
