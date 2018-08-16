package com.github.ryan.personal.data_structure.tree.rbt;

import com.github.ryan.personal.data_structure.set_map.FileOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className RBTree
 * 左倾红黑树／remove操作相比add操作更复杂，暂不实现
 *
 * @date August 15,2018
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K, V> root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向红黑树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
        // 最终根节点为黑色
        root.color = BLACK;
    }

    // 向以node为根的红黑树中插入元素(key, value),递归算法
    // 返回插入新节点后红黑树的根
    private Node<K, V> add(Node<K, V> node, K key, V value) {

        if (node == null) {
            size++;
            return new Node<>(key, value); // 默认插入红色节点
        }

        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else {
            // node.key.compareTo(key) == 0
            node.value = value;
        }

        // 维护红黑树性质
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }


    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    // 返回值null可能表示不存在该key，也可能表示该key对应的value为null
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            throw new NoSuchElementException(key + " doesn't exist!");
        }

        node.value = newValue;
    }

    // 返回以node为根的红黑树中最小值所在的节点
    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    // 返回以node为根的红黑树中，key所在的节点
    private Node<K, V> getNode(Node<K, V> node, K key) {

        if (node == null) {
            return null;
        }

        if (node.key.equals(key)) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return getNode(node.left, key);
        } else {
            // node.key.compareTo(key) < 0
            return getNode(node.right, key);
        }
    }

    // 判断节点node的颜色
    private boolean isRed(Node<K, V> node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    // 融合2-3树2节点
    //   node                   x
    //  /   \     左旋转         /  \
    // T1   x   --------->       node   T3
    //     / \              / \
    //    T2 T3            T1  T2
    private Node<K, V> leftRotate(Node<K, V> node) {

        Node<K, V> x = node.right;

        // 左旋
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 融合2-3树的3节点

    //     node                 x
    //    /   \     右旋转       /  \
    //   x    T2   ------->      y   node
    //  / \                     /  \
    // y  T1                   T1  T2
    private Node<K, V> rightRotate(Node<K, V> node) {

        Node<K, V> x = node.left;

        // 右旋
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 融合2-3树3节点
    // 颜色翻转
    private void flipColors(Node<K, V> node) {

        // 树的形状在调用方代码里保证
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
        boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;

            // 新增的节点需要融合2-3树的2节点或者3节点
            // 因此始终为红色节点
            color = RED;
        }
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words:" + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words:" + map.size());
            System.out.println("Frequency of PRIDE:" + map.get("pride"));
            System.out.println("Frequency of PREJUDICE:" + map.get("prejudice"));

        }

        System.out.println();
    }
}
