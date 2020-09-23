package com.github.ryan.algorithm.leetcode.medium.medium1152;

import java.util.*;

public class Solution {

    private static class Event {
        String username;
        int timestamp;
        String website;
        public Event(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<Event> events = new ArrayList<>(username.length);
        for (int i = 0; i < username.length; i++) {
            events.add(new Event(username[i], timestamp[i], website[i]));
        }
        events.sort((e1, e2) -> e1.timestamp - e2.timestamp);
        Map<String, List<String>> user2Web = new HashMap<>();
        for (Event e : events) {
            user2Web.putIfAbsent(e.username, new ArrayList<>());
            user2Web.get(e.username).add(e.website);
        }

        Map<String, Integer> webseq2Freq = new HashMap<>();
        for (String user : user2Web.keySet()) {
            Set<String> seqs = listSeq4User(user2Web.get(user));
            for (String seq : seqs) {
                webseq2Freq.put(seq, webseq2Freq.getOrDefault(seq, 0) + 1);
            }
        }

        String ans = "";
        int max = 0;
        for (String seq : webseq2Freq.keySet()) {
            if (webseq2Freq.get(seq) < max) {
                continue;
            }

            if (webseq2Freq.get(seq) > max) {
                max = webseq2Freq.get(seq);
                ans = seq;
            } else {
                // webseq2Freq.get(seq) == max
                // return the lexicographically smallest such 3-sequence -> not lexicographically smallest of concatenated 3-sequence
                ans = (seq.compareTo(ans) < 0) ? seq : ans;
            }
        }
        return Arrays.asList(ans.split("#"));
    }


    private Set<String> listSeq4User(List<String> webList) {
        Set<String> res = new HashSet<>();
        if (webList.size() < 3) {
            return res;
        }
        dfs(webList, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(List<String> webList, int idx, List<String> tmp, Set<String> res) {
        if (tmp.size() == 3) {
            StringBuilder b = new StringBuilder();
            // # ascii is 35 -> a ascii is 97
            b.append(tmp.get(0)).append('#').append(tmp.get(1)).append('#').append(tmp.get(2));
            if (!res.contains(b.toString())) {
                res.add(b.toString());
            }
            return;
        }

        for (int i = idx; i < webList.size(); i++) {
            tmp.add(webList.get(i));
            dfs(webList, i + 1, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }

}
