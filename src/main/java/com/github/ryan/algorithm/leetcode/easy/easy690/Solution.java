package com.github.ryan.algorithm.leetcode.easy.easy690;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // Employee info
    private class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        // key -> id of employee, value -> id of direct subordinates
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        // key -> id of employee, value -> importance value of this employee
        Map<Integer, Integer> importanceMap = new HashMap<>();
        for (Employee e : employees) {
            levelMap.put(e.id, e.subordinates);
            importanceMap.put(e.id, e.importance);
        }

        return getInternal(levelMap, importanceMap, id);
    }

    private int getInternal(Map<Integer, List<Integer>> levelMap, Map<Integer, Integer> importanceMap, int id) {
        if (levelMap.get(id) == null || levelMap.get(id).size() < 1) {
            return importanceMap.get(id);
        }

        int res = importanceMap.get(id);
        for (Integer subId : levelMap.get(id)) {
            res += getInternal(levelMap, importanceMap, subId);
        }
        return res;
    }


}
