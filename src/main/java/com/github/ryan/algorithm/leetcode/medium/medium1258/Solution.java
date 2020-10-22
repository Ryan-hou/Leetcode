package com.github.ryan.algorithm.leetcode.medium.medium1258;

import java.util.*;

public class Solution {

    private static class UF {

        Map<String, String> parent;
        // rank.get(str) 表示以str为根的集合所表示的树的层数
        Map<String, Integer> rank;

        public UF() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        public String find(String u) {
            if (!u.equals(parent.get(u))) {
                parent.put(u, find(parent.get(u)));
            }
            return parent.get(u);
        }

        public boolean isConnected(String u, String v) {
            parent.putIfAbsent(u, u);
            parent.putIfAbsent(v, v);
            return find(u).equals(find(v));
        }

        public void union(String u, String v) {
            String pu = find(u);
            String pv = find(v);
            if (pu == pv) {
                return;
            }

            rank.putIfAbsent(pu, 0);
            rank.putIfAbsent(pv, 0);
            if (rank.get(pu) < rank.get(pv)) {
                parent.put(pu, pv);
            } else if (rank.get(pv) < rank.get(pu)) {
                parent.put(pv, pu);
            } else {
                parent.put(pu, pv);
                rank.put(pv, rank.get(pv) + 1);
            }
        }
    }


    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        List<String> res = new ArrayList<>();
        if (synonyms.size() == 0) {
            res.add(text);
            return res;
        }

        UF uf = new UF();
        Set<String> set = new HashSet<>();
        for (List<String> syno : synonyms) {
            String p1 = syno.get(0);
            String p2 = syno.get(1);
            if (!uf.isConnected(p1, p2)) {
                uf.union(p1, p2);
            }
            set.add(p1);
            set.add(p2);
        }

        String[] texts = text.split(" ");
        Map<String, Set<String>> dict = new HashMap<>();
        for (String t : texts) {
            if (set.contains(t)) {
                dict.putIfAbsent(t, new HashSet<>());
                for (String str : set) {
                    if (uf.isConnected(t, str)) {
                        dict.get(t).add(str);
                    }
                }
            }
        }

        dfs(0, texts, "", dict, res);

        Collections.sort(res);
        return res;
    }

    private void dfs(int idx, String[] texts, String cur, Map<String, Set<String>> dict, List<String> res) {
        if (idx >= texts.length) {
            res.add(cur);
            return;
        }

        if (dict.containsKey(texts[idx])) {
            for (String str : dict.get(texts[idx])) {
                String next = cur + str + (idx == texts.length - 1 ? "" : " ");
                dfs(idx + 1, texts, next, dict, res);
            }
        } else {
            String next = cur + texts[idx] + (idx == texts.length - 1 ? "" : " ");
            dfs(idx + 1, texts, next, dict, res);
        }
    }

}
