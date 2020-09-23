package com.github.ryan.algorithm.leetcode.hard.hard126;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date March 31,2019
 */
@Slf4j
public class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList == null || wordList.size() < 1) return res;

        Set<String> dict = new HashSet<>(wordList);
        // key: word -> value: next word set from this word in this dict
        Map<String, Set<String>> nodeNeighbors = new HashMap<>();
        // key: word -> value: distance from start node to this word
        Map<String, Integer> distanceMap = new HashMap<>();

        bfs(beginWord, endWord, dict, nodeNeighbors, distanceMap);
        dfs(beginWord, endWord, nodeNeighbors, distanceMap, new ArrayList<>(), res);
        return res;
    }


    // bfs: find shortest path and all information in the shortest distance
    private void bfs(String beginWord, String endWord, Set<String> dict,
                     Map<String, Set<String>> nodeNeighbors, Map<String, Integer> distanceMap) {

        distanceMap.put(beginWord, 0);
        Queue<String> q = new ArrayDeque<>(); // use in bfs
        q.add(beginWord);

        while (!q.isEmpty()) {
            boolean found = false;
            int count = q.size();
            // process one level
            for (int i = 0; i < count; i++) {
                String word = q.poll();
                int curDistance = distanceMap.get(word);
                Set<String> neighbors = findNeighbors(word, dict);
                nodeNeighbors.put(word, neighbors);
                for (String neighbor : neighbors) {
                    // don't forget this if condition !
                    if (!distanceMap.containsKey(neighbor)) { // check if visited
                        distanceMap.put(neighbor, curDistance + 1);
                        if (endWord.equals(neighbor)) {
                            found = true;
                        } else {
                            q.add(neighbor);
                        }
                    }

                }
            }

            if (found) { break; } // found shortest path, quick return
        }
    }

    // dfs: find all shortest path
    private void dfs(String cur, String endWord,
                     Map<String, Set<String>> nodeNeighbors, Map<String, Integer> distanceMap,
                     List<String> solution, List<List<String>> res) {

        solution.add(cur);
        if (cur.equals(endWord)) {
            res.add(new ArrayList<>(solution));
        } else {
            Set<String> neighbors = nodeNeighbors.get(cur);
            if (neighbors != null) {
                for (String next : neighbors) {
                    if (distanceMap.get(cur) + 1 == distanceMap.get(next)) {
                        dfs(next, endWord, nodeNeighbors, distanceMap, solution, res);
                    }
                }
            }
        }
        solution.remove(solution.size() - 1);
    }

    private Set<String> findNeighbors(String word, Set<String> dict) {
        Set<String> neighbors = new HashSet<>();

        for (int i = 0; i < word.length(); i++) {
            char[] chs = word.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chs[i] = ch;
                String newStr = new String(chs);
                if (dict.contains(newStr)) {
                    neighbors.add(newStr);
                }
            }
            neighbors.remove(word);
        }
        return neighbors;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> words = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        // ["hit","hot","dot","dog","cog"],
        // ["hit","hot","lot","log","cog"]
        log.info("All paths = {}", new Solution().findLadders(beginWord, endWord, words));
    }

}
