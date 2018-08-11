package com.github.ryan.personal.data_structure.hashtable;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className HashTable
 * @date August 10,2018
 */
public class HashTable<K, V> {

    // 底层数组，数组元素为TreeMap(红黑树)，使用TreeMap作为查找表同时处理冲突
    // 假设总共M个地址(M个数组元素)，如果放入哈希表的元素是N个：
    // 如果每个地址是链表：平均时间复杂度 O(N/M); 如果每个地址是平衡二叉树:O(log(N/M))
    // 对照HashMap: transient Node<K,V>[] table;
    private TreeMap<K, V>[] hashtable;

    private int M;
    private int size;

    private static final int UPPER_TOL = 10; // N/M
    private static final int LOWER_TOL = 2;
    private static final int INITAIL_CAPACITY = 7;

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        // 初始化每一个数组元素，即TreeMap类型的查找表
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(INITAIL_CAPACITY);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= M * UPPER_TOL) {
                resize(2*M);
            }
        }
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new NoSuchElementException(key + " doesn't exist!");
        }
        map.put(key, value);
    }


    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < M * LOWER_TOL && M / 2 >= INITAIL_CAPACITY) {
                resize(M / 2);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashtable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashtable[i] = new TreeMap<>();
        }

        // 记录旧的M值并更新
        int oldM = M;
        this.M = newM; // 更新后用于重新计算Key在数组的下标位置

        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (Map.Entry<K, V> entry : map.entrySet()) {
                newHashtable[hash(entry.getKey())].put(entry.getKey(), entry.getValue());
            }
        }
        this.hashtable = newHashtable;
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
}
