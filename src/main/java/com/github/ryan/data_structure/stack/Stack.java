package com.github.ryan.data_structure.stack;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Stack
 * @date August 07,2018
 */
public interface Stack<E> {

    int size();

    boolean isEmpty();

    void push(E e);

    E peek();

    E pop();
}
