package com.github.ryan.algorithm.leetcode.medium.medium341;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 19,2019
 */
public class NestedIterator implements Iterator<Integer> {

    private interface NestedInteger {
        // @return true
        // if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();

    }

    private List<Integer> res;
    private int idx;

    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            throw new NullPointerException("nestedList is null.");
        }
        res = new ArrayList<>();
        idx = 0;
        Deque<NestedInteger> stack = new ArrayDeque<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }

        while (!stack.isEmpty()) {
            NestedInteger ni = stack.pop();
            if (ni.isInteger()) {
                res.add(ni.getInteger());
            } else {
                List<NestedInteger> tmp = ni.getList();
                for (int i = tmp.size() - 1; i >= 0; i--) {
                    stack.push(tmp.get(i));
                }
            }
        }
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return res.get(idx++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return idx < res.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
