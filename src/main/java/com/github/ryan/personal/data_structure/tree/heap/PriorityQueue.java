package com.github.ryan.personal.data_structure.tree.heap;

import com.github.ryan.personal.data_structure.queue.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className PriorityQueue
 * @date August 14,2018
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeap<>(capacity);
    }


    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void offer(E e) {
        maxHeap.add(e);
    }

    @Override
    public E poll() {
        return maxHeap.extractMax();
    }

    @Override
    public E peek() {
        return maxHeap.peekMax();
    }
}
