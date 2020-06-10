package com.github.ryan.leetcode.algorithm.easy.easy339;

import java.util.List;

public class Solution {


     // This is the interface that allows for creating nested lists.
     // You should not implement it, or speculate about its implementation
     public interface NestedInteger {
         // Constructor initializes an empty nested list.
         // NestedInteger();

         // Constructor initializes a single integer.
         // NestedInteger(int value);

         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         boolean isInteger();

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         Integer getInteger();

         // Set this NestedInteger to hold a single integer.
         void setInteger(int value);

         // Set this NestedInteger to hold a nested list and adds a nested integer to it.
         void add(NestedInteger ni);

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         List<NestedInteger> getList();
     }


    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() < 1) return 0;

        int res = 0;
        for (NestedInteger ni : nestedList) {
            res += sum(ni, 1);
        }
        return res;
    }

    public int sum(NestedInteger ni, int depth) {
        if (ni.isInteger()) {
            return ni.getInteger() * depth;
        }

        int sum = 0;
        List<NestedInteger> list = ni.getList();
        for (NestedInteger i : list) {
            sum += sum(i, depth + 1);
        }
        return sum;
    }

}
