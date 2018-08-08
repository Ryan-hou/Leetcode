package com.github.ryan.personal.data_structure.queue;

import java.util.NoSuchElementException;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LoopQueue
 * 循环队列，解决：
 * 1）相比ArrayQueue，出队操作(poll)时间复杂度降为O(1)
 * 2）出队后的空间可以继续被利用(循环)
 *
 * @date August 07,2018
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    /**
     * Invariant:
     * 队空时：front == tail
     * 队满时：(tail + 1) % data.length == front
     * 浪费一个存储空间用来区分队空和队满
     */
    private int front; // 指向队首(下一个待出队元素的下标位置)
    private int tail; // 指向队尾元素的下一个位置，即下一次入队操作放置元素的位置

    // 可以通过front,tail来计算，如果多增加该变量，可以通过size来判断队空和队满
    // 从而不用故意浪费一个存储空间
    // private int size;


    // 在ArrayDeque的实现中，capacity为2的幂
    // 这样取模运算可以优化为：(tail + 1) & (data.length - 1)
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1]; // 由于浪费了一个空间，需要多分配一个空间
        front = 0;
        tail = 0;
        // size = 0;
    }

    public LoopQueue() {
        this(10);
    }


    @Override
    public int size() {
        return tail >= front ?
                tail - front : data.length - (front - tail);
    }

    public int capacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    // 均摊时间复杂度: O(1)
    @Override
    public void offer(E e) {
        if ((tail + 1) % data.length == front) {
            resize(capacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't deque from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;

        if (size() == capacity() / 4 && capacity() / 2 != 0) {
            resize(capacity() / 2);
        }
        return ret;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size(); i++) {
            newData[i] = data[(i + front) % data.length];
        }
        // or use memory copy
        // int r = data.length - front; // number of elements to the right of p
        // System.arraycopy(data, front, newData, 0, r);
        // System.arraycopy(data, 0, newData, r, front);

        int size = size();
        data = newData;
        front = 0;
        //tail = size(); // size计算需要放到front＝0之前
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n", size(), capacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();

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
