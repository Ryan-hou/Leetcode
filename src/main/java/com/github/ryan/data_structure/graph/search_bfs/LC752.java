package com.github.ryan.data_structure.graph.search_bfs;

import java.util.*;

/**
 * 图论建模 -> 状态表示顶点，状态转移表示边，顶点使用字符串来表示状态
 */
public class LC752 {

    public int openLock(String[] deadends, String target) {
        Set<String> deadDict = new HashSet<>();
        for (String s : deadends) {
            deadDict.add(s);
        }

        final String initState = "0000";
        if (deadDict.contains(initState)) {
            return -1;
        }
        if (initState.equals(target)) {
            return 0;
        }

        // BFS
        // String 表示的状态为图中的顶点
        Queue<String> q = new ArrayDeque<>();
        /**
         * key 表示状态即图中的顶点
         * visited.get(key) 的值为从初始状态到该状态的操作步骤数(操作步骤实际为抽象的路径)
         * 不存在该key则说明该key表示的顶点还没有被访问
         */
        Map<String, Integer> visited = new HashMap<>();
        q.offer(initState);
        visited.put(initState, 0);
        while (!q.isEmpty()) {
            String curs = q.poll();

            // 计算当前状态可达的下一个状态
            List<String> nexts = new ArrayList<>();
            char[] curArr = curs.toCharArray();
            for (int i = 0; i < curArr.length; i++) {
                // 记录修改前的字符，用于重置字符数组
                char o = curArr[i];

                curArr[i] = Character.forDigit((curArr[i] - '0' + 1) % 10, 10);
                nexts.add(new String(curArr));
                curArr[i] = o;

                curArr[i] = Character.forDigit((curArr[i] - '0' + 9) % 10, 10);
                nexts.add(new String(curArr));
                curArr[i] = o;
            }

            for (String next : nexts) {
                // 状态转移和状态检测分开处理
                if (!deadDict.contains(next)
                        && !visited.containsKey(next)) {
                    q.offer(next);
                    visited.put(next, visited.get(curs) + 1);
                    if (next.equals(target)) {
                        return visited.get(next);
                    }
                }
            }
        } // end while
        return -1;
    }
}
