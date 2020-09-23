package com.github.ryan.data_structure.set_map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Set
 * @date August 13,2018
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int size();

    boolean isEmpty();
}
