package com.github.ryan.algorithm.leetcode.medium.medium901;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {
    private Deque<Integer> priceStack;
    private Deque<Integer> spanStack;

    public StockSpanner() {
        priceStack = new ArrayDeque<>();
        spanStack = new ArrayDeque<>();
    }

    public int next(int price) {
        int span = 1;
        while (!priceStack.isEmpty() && priceStack.peek() <= price) {
            priceStack.pop();
            span += spanStack.pop();
        }
        priceStack.push(price);
        spanStack.push(span);
        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
