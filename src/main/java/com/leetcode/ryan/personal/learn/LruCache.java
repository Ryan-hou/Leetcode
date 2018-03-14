package com.leetcode.ryan.personal.learn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * 模拟 LinkedHashMap实现 LRU缓存：
 * 封装 HashMap 和 双链表
 * @className: LruCache
 * @date March 14,2018
 */
public class LruCache<K, V> {

    private final int MAX_CACHE_SIZE;
    private LruNode<K, V> head;
    private LruNode<K, V> tail;

    private Map<K, LruNode<K, V>> cache;

    public LruCache(int maxCacheSize) {
        MAX_CACHE_SIZE = maxCacheSize;
        cache = new HashMap<>((int) Math.ceil(maxCacheSize / 0.75f) + 1);
    }

    public V get(K key) {
        LruNode<K, V> node = getLruNode(key);
        if (node == null) {
            return null;
        } else {
            // moveToHead(node);
            unlinkNode(node);
            addNewHead(node);
            return node.value;
        }
    }

    public void put(K key, V value) {
        LruNode<K, V> node = getLruNode(key);
        if (node == null) {
            if (cache.size() >= MAX_CACHE_SIZE) {
                cache.remove(tail.key);
                removeTail();
            }
            node = new LruNode<>();
            node.key = key;
            node.value = value;
            addNewHead(node);

        } else {
           node.value = value;
           unlinkNode(node);
           addNewHead(node);
        }

        cache.put(key, node);
    }

    public V remove(K key) {
        LruNode<K, V> node = getLruNode(key);
        if (node == null) {
           return null;
        }

        V value = node.value;
        unlinkNode(node);
        cache.remove(key);
        return value;
    }

    // 双链表删除节点
    private void unlinkNode(LruNode<K, V> node) {
        final LruNode<K, V> pre = node.pre;
        final LruNode<K, V> next = node.next;

        if (pre == null) {
            head = next;
        } else {
            pre.next = next;
            node.pre = null;
        }

        if (next == null) {
            tail = pre;
        } else {
            next.pre = pre;
            node.next = null;
        }
    }


//    // 核心方法：供put/get方法调用，对应了元素的增/改/查
//    // 删除节点，并放到链首，违背了SRP原则，实际上一个函数做了两件事，拆开为：
//    // unlinkNode/addNewHead 两个方法
//    private void moveToHead(LruNode<K, V> node) {
//        if (head == null || tail == null) {
//            head = tail = node;
//            return;
//        }
//        if (head == node) {
//            return;
//        }
//
//        if (node.pre != null) {
//            node.pre.next = node.next;
//        }
//        if (node.next != null) {
//            node.next.pre = node.pre;
//        }
//        if (node == tail) {
//            tail = tail.pre;
//        }
//
//        node.next = head;
//        head.pre = node;
//        head = node;
//        node.pre = null;
//    }

    private void addNewHead(LruNode<K, V> node) {
        assert node != null;

        if (head == null || tail == null) {
            head = tail = node;
            return;
        }
        if (head == node) {
            return;
        }

        node.next = head;
        head.pre = node;
        head = node;
        node.pre = null;
    }

    private void removeTail() {
        if (tail != null) {
            tail = tail.pre;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
        }
    }

    private LruNode<K, V> getLruNode(K key) {
        return cache.get(key);
    }

    private static class LruNode<K, V> {
        K key;
        V value;
        LruNode<K, V> pre;
        LruNode<K, V> next;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        LruNode<K, V> node = head;
        builder.append("LruCache{");
        while (node != null) {
            builder.append(String.format(" %s:%s ", node.key, node.value));
            node = node.next;
        }
        builder.append("}");
        return builder.toString();
    }

    // -------- LinkedHashMap最为LRU缓存使用 ---------
    public static <K, V> Map<K, V> lruCache(final int maxSize) {
        return new LinkedHashMap<K, V>( (int) Math.ceil(maxSize / 0.75f) + 1, 0.75f, true) {
          @Override
          protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
              return size() > maxSize;
          }
        };
    }

    public static void main(String[] args) {
        LruCache<Integer, String> cache = new LruCache<>(3);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        // c,b,a
        System.out.println(cache);
        cache.put(4, "d");
        // d,c,b
        System.out.println(cache);
        cache.get(3);
        cache.put(5, "e");
        // e,c,d
        System.out.println(cache);
        cache.remove(4);
        // e,c
        System.out.println(cache);
    }


}
