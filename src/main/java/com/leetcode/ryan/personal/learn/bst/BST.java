package com.leetcode.ryan.personal.learn.bst;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

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

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 中序遍历(BST中序遍历即为由小到大的排序顺序)
    public void inOrder() {
        inOrder(root);
    }

    // 后序遍历(可用于把BST的每个节点置为空)
    public void postOrder() {
        postOrder(root);
    }

    // 寻找最大值
    public K maximum() {
        assert count != 0;
        Node<K, V> maxNode = maximum(root);
        return maxNode.key;
    }

    // 寻找最小值
    public K minimum() {
        assert count != 0;
        Node<K, V> minNode = minimum(root);
        return minNode.key;
    }

    // 从BST中删除最小值所在的节点
    public void removeMin() {
        if (root != null) {
            root = removeMin(root);
        }
    }

    // 从BST中删除最大值所在的节点
    public void removeMax() {
        if (root != null) {
            root = removeMax(root);
        }
    }

    // 从二叉树中删除键值为key的节点
    public void remove(K key) {
        root = remove(root, key);
    }

    // 删除掉以node为根的BST中键值为key的节点
    // 返回删除节点后新的BST的根
    Node remove(Node<K, V> node, K key) {

        if (node == null) {
            return null;
        }

        if (((Comparable<K>) key).compareTo(node.key) < 0) {
            // key < node.key
            node.left = remove(node.left, key);
            return node;
        } else if (((Comparable<K>) key).compareTo(node.key) > 0) {
            // key > node.key
            node.right = remove(node.right, key);
            return node;
        } else {
            // key == node.key

            if (node.left == null) {
                Node rightNode = node.right;
                node = null;
                count--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node = null;
                count--;
                return leftNode;
            }

            // node.left != null && node.right != null
            /**
             * Hubbard Deletion：
             * 删除左右都有孩子的节点d：
             * 找到 s=min(d.right),s是d的后继（即根据BST的性质，找到d的右子树的最小值的节点）
             * 或者 p=max(d.left), p是d前驱
             */
            Node successor = new Node(minimum(node.right));
            count++;

            successor.right = removeMin(node.right);
            successor.left = node.left;
            node = null;
            count--;

            return successor;
        }


    }

    // 删除以node为根的BST中的最小节点
    // 返回删除节点后新的BST的根
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node = null;
            count--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 删除以node为根的BST中的最大节点
    // 返回删除节点后新的BST的根
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node =  null;
            count--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 在以node为根的二叉搜索树中，返回最大键值的节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 在以node为根的二叉搜索树中，返回最小键值的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public void destory(Node root) {
        if (root == null) {
            return;
        }

        destory(root.left);
        destory(root.right);
        root = null;
        count--;
    }

    // 层序遍历
    public void levelOrder() {
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.key + "  ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }

    // 对以node为根的二叉树进行前序遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.key + "  ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 对以node为根的二叉树进行中序遍历
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.key + "  ");
        inOrder(node.right);
    }

    // 对以node为根的二叉树进行后序遍历
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.key + "  ");
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

        public Node(Node<K, V> node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }


    // -------------------------- test code -----------------------------

    public static void main(String[] args) {
        //BST<Integer, Integer> bst = testTraversal();
        //testMinAndMax(bst);
        testRemoveKey();
    }

    private static BST<Integer, Integer> testTraversal() {

        BST<Integer, Integer> bst = new BST<>();
        Random r = new Random();
        int n = 5;
        for (int i = 0; i < n; i++) {
            int key = Math.abs(r.nextInt()) % 100;
            int value = key;
            bst.insert(key, value);
        }

        bst.inOrder();
        System.out.println();
        bst.levelOrder();
        return bst;
    }

    private static void testRemoveKey() {
        BST<Integer, Integer> bst = new BST<>();
        for (int i = 0; i < 10; i++) {
            bst.insert(i, i);
        }
        bst.inOrder();
        System.out.println();
        bst.remove(5);
        bst.inOrder();
        System.out.println();
        bst.remove(9);
        bst.inOrder();
    }

    private static void testMinAndMax(BST<Integer, Integer> bst) {
        Node minimum = bst.minimum(bst.root);
        System.out.println();
        System.out.println("Min node = " + minimum.key);
        Node maximum = bst.maximum(bst.root);
        System.out.println("Max node = " + maximum.key);
        bst.removeMin();
        minimum = bst.minimum(bst.root);
        System.out.println("New Min node = " + minimum.key);
    }


    private static void testBST() {
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
