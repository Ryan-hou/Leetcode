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

// 小bug：在HashTable<K, V>中我们对K不要求可比性，但是底层使用了TreeMap<K,V>，K需要满足Comparable
// 在jdk8 HashMap<K,V>中,K不要求可比性，因为底层最初使用链表，链表过长时才会转为红黑树，所以这里有一个
// 前提，只有K为Comparable类型时，才能转为红黑树，否则将继续保持链表结构
public class HashTable<K, V> {

    // 底层数组，数组元素为TreeMap(红黑树)，使用TreeMap作为查找表同时处理冲突
    // 假设总共M个地址(M个数组元素)，如果放入哈希表的元素是N个：
    // 如果每个地址是链表：平均时间复杂度 O(N/M); 如果每个地址是平衡二叉树:O(log(N/M))
    // 对照HashMap: transient Node<K,V>[] table;
    private TreeMap<K, V>[] hashtable;

    private int M;
    private int capacityIndex = 0;
    // 数组容量素数表
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189,
            805306457, 1610612741};
    private int size;

    private static final int UPPER_TOL = 10; // N/M
    private static final int LOWER_TOL = 2;
    private static final int INITAIL_CAPACITY = 7;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        // 初始化每一个数组元素，即TreeMap类型的查找表
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

//    public HashTable() {
//        this(INITAIL_CAPACITY);
//    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     *  HashMap的实现中使用长度为2的整数次幂的数组长度。这样做的好处是：计算一个元素的位置更快速，使用位运算就可以了。
     *  相比取模，尤其是对素数取模的运算，要快很多。但是缺点是：容易哈希冲突。
     *  为了解决这个问题，HashMap在哈希函数上做文章。HashMap不是直接使用用户传来的数据的hashCode值直接 % length，
     *  而是对用户传来的数据的hashCode再进行一次哈希运算，以求得到的哈希值分布尽量平均。
     *  为此，HashMap中有一个hash(key)的方法。可以仔细阅读一下其中的注释，描述了这个设计：
     */
    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash to lower.
     * Because the table uses power-of-two masking, sets of hashes that vary
     * only in bits above the current mask will always collide.
     * (Among known examples are sets of Float keys holding consecutive
     * whole numbers in small table.)
     * So we apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and quality
     * of bit-spreading. Because many common sets of hashes are
     * already reasonably distributed (So don't benefit from spreading),
     * and because we use trees to handle large sets of collisions in bins,
     * we just XOR some shifted bits in the cheapest possible way to reduce
     * systematic lossage, as well as to incorporate impact of highest bits
     * that would otherwise never be used in index calculations because of
     * table bounds.
     */
    // jdk8:HashMap
    static final int hash1(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
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

//            if (size >= M * UPPER_TOL) {
//                // newCap = oldCap << 1（HashMap扩容）
//                resize(2*M);
//            }
            if (size >= M * UPPER_TOL && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
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

//            if (size < M * LOWER_TOL && M / 2 >= INITAIL_CAPACITY) {
//                resize(M / 2);
//            }
            if (size < M * LOWER_TOL && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
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
