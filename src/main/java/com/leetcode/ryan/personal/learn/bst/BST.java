package com.leetcode.ryan.personal.learn.bst;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BST
 * @date February 09,2018
 */
public class BST<K, V> {

    private Node root;
    private int count;


    public BST() {
      root = null;
      count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    public boolean contain(K key) {
        return contain(root, key);
    }

    public V search(K key) {
        return search(root, key);
    }

    // 在以node为根节点的BST中搜索key对应的value
    private V search(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.equals(key)) {
            return (V) node.value;
        } else if (((Comparable<K>) key).compareTo((K) node.key) > 0) {
            // key > node.key
            return search(node.right, key);
        } else {
            // key < node.key
            return search(node.left, key);
        }
    }

    // 查看以node为根的二叉搜索树中是否包含键值为key的节点
    private boolean contain(Node node, K key) {

        if (node == null) {
            return false;
        }

        if (node.key.equals(key)) {
            return true;
        } else if (((Comparable<K>) key).compareTo((K) node.key) > 0) {
            // key > node.key
            return contain(node.right, key);
        } else {
            // key < node.key
            return contain(node.left, key);
        }
    }

    // 向以node为根的二叉搜索树中，插入节点(key,value)
    // 返回插入新节点后的二叉搜索树的根
    private Node insert(Node node, K key, V value) {

        // 递归出口
        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (node.key.equals(key)) {
            node.value = value;
        } else if (((Comparable<K>)key).compareTo((K) node.key) > 0) {
            //key > node.key
            node.right = insert(node.right, key, value);
        } else {
            //key < node.key
            node.left = insert(node.left, key, value);
        }

        return node;
    }


    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        String fileName = "test" + File.separator + "bible.txt";
        List<String> words = new ArrayList<>(500000);

        if (FileOps.readFile(fileName, words)) {
            System.out.println("There are totally " + words.size() + " words in " + fileName);
            System.out.println();

            // test BST
            long sTime = System.currentTimeMillis();
            BST<String, Integer> bst = new BST<>();

            for (String word : words) {
                Integer res = bst.search(word);
                if (res == null) {
                    bst.insert(word, 1);
                } else {
                    bst.insert(word, bst.search(word) + 1);
                }
            }

            System.out.println("'god' : " + bst.search("god"));
            long eTime = System.currentTimeMillis();
            System.out.println("BST, use " + (eTime - sTime) + " ms.");

        }
    }
}
