package com.github.ryan.personal.data_structure.queue;

import com.github.ryan.personal.data_structure.array.Array;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className ArrayQueue
 * 动态数组的起始下标作为队首，末尾下标作为队尾
 * offer/peek 时间复杂度O(1)
 * poll 时间复杂度O(n),需要改进
 *
 * @date August 07,2018
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public int capacity() {
        return array.capacity();
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void offer(E e) {
        array.addLast(e);
    }

    @Override
    public E poll() {
        return array.removeFirst();
    }

    @Override
    public E peek() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.size(); i++) {
            res.append(array.get(i));
            if (i != array.size() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.offer(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.poll();
                System.out.println(queue);
            }
        }
    }
}
