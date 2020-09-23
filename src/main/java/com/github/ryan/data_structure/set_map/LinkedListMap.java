package com.github.ryan.data_structure.set_map;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedListMap
 * @date August 13,2018
 *
 * 增／删／改／查：时间复杂度O(n)
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private Node<K, V> dummyNode;
    private int size;

    public LinkedListMap() {
        dummyNode = new Node<>();
        size = 0;
    }


    @Override
    // 保存k－v键值对，key已经存在的话，更新对应的value值
    public void put(K key, V value) {
        Node<K, V> node = getNode(key);
        if (node == null) {
//            Node<K, V> newNode = new Node<>(key, value);
//            newNode.next = dummyNode.next;
//            dummyNode.next = newNode;
            dummyNode.next = new Node<>(key, value, dummyNode.next);

            size++;
        } else {
            node.value = value;
        }
    }

    // 返回值null可能表示不存在该键值对，也可能表示该key对应的value值为null
    @Override
    public V remove(K key) {
        // prev为待删除节点的前一个节点
        Node<K, V> prev = dummyNode;

        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }


        if (prev.next != null) {
            Node<K, V> delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    // 返回值null可能表示该Key不存在，也可能表示该Key对应的value值为null
    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            throw new NoSuchElementException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<K, V> getNode(K key) {
        Node<K, V> cur = dummyNode.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }


    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            Map<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }

            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
    }
}
