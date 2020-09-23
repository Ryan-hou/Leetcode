package com.github.ryan.algorithm.leetcode.medium.medium641;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 02,2019
 */
public class MyCircularDeque {

    private final int[] data;
    private int capacity; // capacity of the queue
    /**
     * Use variable count to save the number of elements:
     * 1. Save a space in circular queue
     * 2. It's much easier to decide whether the queue
     * is full/empty or not
     */
    private int count; // number of elements in the queue

    // The index of the element at the head of the deque
    // or an arbitrary number equal to tail if the deque is empty.
    private int front;
    // The index at which the next element would be added to the tail of the deque
    private int rear;


    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        data = new int[k];
        capacity = k;
        count = 0;
        front = rear = 0;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        data[rear] = value;
        count++;
        rear = (rear + 1) % capacity;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        // int oldValue = data[front]
        front = (front + 1) % capacity;
        count--;
        return true;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) return false;

        if (front - 1 < 0) {
            front = capacity - 1;
        } else {
            front = front - 1;
        }
        data[front] = value;
        count++;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;

        if (rear - 1 < 0) {
            rear = capacity - 1;
        } else {
            rear = rear - 1;
        }
        // int oldValue = data[rear]
        count--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) return -1;
        return data[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) return -1;
        return rear == 0 ? data[capacity - 1] : data[rear - 1];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
