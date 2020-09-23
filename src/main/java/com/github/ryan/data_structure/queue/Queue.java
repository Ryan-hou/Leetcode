package com.github.ryan.data_structure.queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Queue
 * @date August 07,2018
 */
public interface Queue<E> {

    int size();

    boolean isEmpty();

    void offer(E e);

    E poll();

    E peek();
}
