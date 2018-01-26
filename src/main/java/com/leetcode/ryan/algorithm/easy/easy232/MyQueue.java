package com.leetcode.ryan.algorithm.easy.easy232;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: MyQueue
 * @date July 10,2017
 */
public class MyQueue {

    private static final Logger logger = LoggerFactory.getLogger(MyQueue.class);

    private Deque<Integer> inputStack;
    private Deque<Integer> outputStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inputStack = new ArrayDeque<>();
        outputStack = new ArrayDeque<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inputStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return outputStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outputStack.isEmpty())
            while (!inputStack.isEmpty())
                // 遍历inputStack到outputStack,顺序反转,
                // 刚好作为队列头部,执行出队操作
                outputStack.push(inputStack.pop());
        return outputStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        logger.info("First element pop from queue is {}", myQueue.pop());
        logger.info("The queue is empty? {}", myQueue.empty());
        logger.info("Second element pop from queue is {}", myQueue.pop());
        logger.info("The queue is empty? {}", myQueue.empty());
    }
}
