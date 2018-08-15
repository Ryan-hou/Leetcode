package com.github.ryan.personal.data_structure.tree.avl;

import com.github.ryan.personal.data_structure.set_map.FileOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className AVLTree
 * @date August 14,2018
 */
public class AVLTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的BST中添加元素(key, value),递归算法
    // 返回插入新节点后二分搜索树的根
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
            // node.key.compareTo(key) == 0
            node.value = value;
        }

        // 更新height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR
        // 先左旋为LL，再右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        // 先右旋为RR，再左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }


    // 返回值为null可能表示该key不存在，也可能表示该key对应的value值为null
    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node<K, V> node = getNode(root, key);
        if (node == null) {
            throw new NoSuchElementException(key + " doesn't exist!");
        }

        node.value = value;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {

        Node<K, V> node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 从以node为根的二分搜索树中删除键为key的节点
    // 并返回删除节点后二分搜索树的根
    private Node<K, V> remove(Node<K, V> node, K key) {

        if (node == null) {
            return null;
        }

        Node<K, V> retNode;

        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            // return node;
            retNode = node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            // return node;
            retNode = node;
        } else {
            // key.compareTo(node.key) == 0

            // 待删除节点左子树为空
            if (node.left == null) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                // return rightNode;
                retNode = rightNode;
            }

            // 待删除节点右子树为空
            else if (node.right == null) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                // return leftNode;
                retNode = leftNode;
            }

            // 待删除节点左右子树都不为空
            else {
                // 找到比待删除节点大的最小节点(后继节点)，即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点
                Node<K, V> successor = minimum(node.right);
                // successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                // return successor;
                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        // 更新height
        retNode.height = 1 + Math.max(height(retNode.left), height(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR
        // 先左旋为LL，再右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        // 先右旋为RR，再左旋
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 判断该二叉树是否是一颗二分搜索树(使用BST中序遍历)
    public boolean isBST() {
        List<K> keys = new ArrayList<>();

        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<K, V> node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node<K, V> node, List<K> keys) {
        if (node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
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

    // 返回Node节点的高度
    private int height(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    // 获得节点node的平衡因子
    private int getBalanceFactor(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }


    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                             x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)              z     y
    //     / \       - - - - - - - ->          / \   / \
    //    z   T3                         T1  T2 T3 T4
    //   / \
    //  T1   T2
    private Node<K, V> rightRotate(Node<K, V> y) {
        Node<K, V> x = y.left;
        Node<K, V> T3 = x.right;

        // 右旋
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                            x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)             y    z
    //     / \   - - - - - - - ->        / \   / \
    //   T2  z                      T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node<K, V> leftRotate(Node<K, V> y) {
        Node<K, V> x = y.right;
        Node<K, V> T2 = x.left;

        // 左旋
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
        int height; // 节点的高度，叶子节点为1

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }


    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.size);
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST: " + map.isBST());
            System.out.println("is Balanced: " + map.isBalanced());


            for (String word : words) {
                map.remove(word);
                if (!map.isBalanced() || !map.isBST()) {
                    throw new IllegalStateException("Error!");
                }
            }

            System.out.println("AVLTree test completed.");
        }
    }
}
