package com.github.ryan.data_structure.set_map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Map
 * @date August 13,2018
 */
public interface Map<K, V> {

    void put(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int size();

    boolean isEmpty();
}
