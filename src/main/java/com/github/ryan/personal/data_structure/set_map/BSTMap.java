package com.github.ryan.personal.data_structure.set_map;

import java.util.NoSuchElementException;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className BSTMap
 * @date August 13,2018
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }


    @Override
    public V remove(K key) {

        Node<K, V> node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除以node为根的二分搜索树中的节点key
    // 返回删除节点后的二分搜索树的根
    private Node<K, V> remove(Node<K, V> node, K key) {

        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // node.key.compareTo(key) == 0;

            // 待删除节点左子树为空
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空
            if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空

            // 找到比待删除节点大的最小节点(后继节点)，即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点
            Node<K, V> successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    // 返回值null可能表示不存在该键值对，也可能表示该key对应的value为null
    @Override
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node<K, V> node = getNode(root, key);
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


    // 向二分搜索树中添加新的元素(key,value)
    // 如果存在该key，更新对应的value值
    @Override
    public void put(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value),递归算法
    // 返回插入新节点后的二分搜索树的根
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else {
            // node.key.compareTo(key) == 0;
            node.value = value;
        }

        return node;
    }

    // 返回以node为根的二分搜索树中，key所在的节点
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

    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node<K, V> removeMin(Node<K, V> node) {

        if (node.left == null) {
            Node<K, V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.right = removeMin(node.left);
        return node;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
}
