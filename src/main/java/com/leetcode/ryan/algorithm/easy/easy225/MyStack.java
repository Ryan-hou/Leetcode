package com.leetcode.ryan.algorithm.easy.easy225;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: MyStack
 * @date July 10,2017
 */
public class MyStack {

    private static final Logger logger = LoggerFactory.getLogger(MyStack.class);

    private Deque<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        // Rotating the queue to behave like a stack
        for (int i = 1; i < queue.size(); i++) {
            queue.offer(queue.remove());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.remove();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        logger.info("The first element from stack is {}", stack.pop());
        logger.info("The stack is empty? {}", stack.empty());
        logger.info("The second element from stack is {}", stack.pop());
        logger.info("The stack is empty? {}", stack.empty());
    }
}
