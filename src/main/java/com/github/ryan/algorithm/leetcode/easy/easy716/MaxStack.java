package com.github.ryan.algorithm.leetcode.easy.easy716;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
@Slf4j
public class MaxStack {

    private static class Node {
        int value;
        int idx;
        // lazy remove technique
        boolean invalid;
        Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
            invalid = false;
        }
    }

    private Deque<Node> stack;
    private PriorityQueue<Node> maxHeap;
    private int idx;


    /** initialize your data structure here. */
    public MaxStack() {
        idx = 0;
        stack = new ArrayDeque<>();
        maxHeap = new PriorityQueue<>((n1, n2)
                -> n1.value != n2.value ? n2.value - n1.value : n2.idx - n1.idx);
    }

    public void push(int x) {
        Node n = new Node(x, idx++);
        stack.push(n);
        maxHeap.offer(n);
    }

    public int pop() {
        while (!stack.isEmpty() && stack.peek().invalid) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        Node cur = stack.pop();
        cur.invalid = true;
        return cur.value;
    }

    public int top() {
        while (!stack.isEmpty() && stack.peek().invalid) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        return stack.peek().value;
    }

    public int peekMax() {
        while (!maxHeap.isEmpty() && maxHeap.peek().invalid) {
            maxHeap.poll();
        }
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        return maxHeap.peek().value;
    }

    public int popMax() {
        while (!maxHeap.isEmpty() && maxHeap.peek().invalid) {
            maxHeap.poll();
        }
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("stack is empty");
        }
        Node cur = maxHeap.poll();
        cur.invalid = true;
        return cur.value;
    }

    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        log.info("top = {}", stack.top());
        log.info("popMax = {}", stack.popMax());
        log.info("top = {}", stack.top());
        log.info("peekMax = {}", stack.peekMax());
    }
}
