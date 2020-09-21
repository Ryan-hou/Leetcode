package com.github.ryan.personal.data_structure.tree.bst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className BST
 * 不考虑重复元素
 *
 * @date August 12,2018
 */
public class BST<E extends Comparable<E>> {

    private Node<E> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加元素e
    public void add(E e) {
//        if (root == null) {
//            root = new Node<>(e);
//            size++;
//        } else {
//            add(root, e);
//        }
        root = add(root, e);
    }

    // 该递归写法比较啰嗦，转换递归函数的定义进行简化（null也是二叉树）
    // 向以node为根的二分搜索树中插入元素e，递归算法
//    private void add(Node<E> node, E e) {
//        // 递归出口
//        if (e.equals(root.e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) {
//            node.left = new Node<>(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) {
//            node.right = new Node<>(e);
//            size++;
//            return;
//        }
//
//        if (e.compareTo(node.e) < 0) {
//            add(node.left, e);
//        } else { // e.compareTo(node.e) > 0
//            add(node.right, e);
//        }
//    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node<E> add(Node<E> node, E e) {

        // 递归终止条件--向空二叉树插入节点e，返回插入节点后二分搜索树的根
        if (node == null) {
            size++;
            return new Node<>(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    // 查看以node为根的二分搜索树中是否存在元素e
    private boolean contains(Node<E> node, E e) {
        // 递归出口
        if (node == null)
            return false;

        if (node.e.compareTo(e) == 0) {
            return true;
        } else if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        } else {
            // node.e.compareTo(e) < 0
            return contains(node.right, e);
        }
    }

    public E minimum() {
        if (size == 0) {
            throw new NoSuchElementException("BST is empty");
        }
        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node<E> minimum(Node<E> node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public E maximum() {
        if (size == 0) {
            throw new NoSuchElementException("BST is empty");
        }
        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node<E> maximum(Node<E> node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除以node为根的二分搜索树的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node<E> removeMin(Node<E> node) {
        if (node.left == null) {
            Node<E> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除以node为根的二分搜索树的最大节点
    // 返回删除节点后新的二分搜索树的gen
    private Node<E> removeMax(Node<E> node) {
        if (node.right == null) {
            Node<E> leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 最复杂的操作：从二分搜索树中删除元素为e的节点
    // 三种情况：带删除节点只有左子树，只有右子树，左右子树都有
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除以node为根的二分搜索树中元素为e的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node<E> remove(Node<E> node, E e) {
        if (node == null) {
            return null;
        }

        if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (node.e.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // node.e == e

            // 待删除节点左子树为空
            if (node.left == null) {
                Node<E> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空
            if (node.right == null) {
                Node<E> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空
            // 找到比待删除节点大的最小节点，即待删除节点的右子树的最小节点（即后继节点，相似思路也可以使用前驱节点）
            // 用这个节点顶替待删除节点的位置
            Node<E> successor = minimum(node.right);
            successor.right = removeMin(node.right); // 注意在removeMin中size已经减一了
            // size++;
            successor.left = node.left;
            node.left = node.right = null;

            return successor;
        }
    }

    // ------------------------- BFS --------------------------------------
    // 广度优先遍历--更快的找到问题的解(比如无权图最短路径)

    // 层序遍历
    public void levelOrder() {
        Queue<Node<E>> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> cur = q.poll();
            System.out.print(cur.e + "  ");
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    // ----------------------- DFS -------------------------------------------

    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树，递归算法
    // 根-左-右
    private void preOrder(Node<E> node) {
        // 递归出口
        if (node == null) {
            return;
        }

        System.out.print(node.e + "  ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树，递归算法
    // 左-根-右
    // BST中序遍历为有序结果
    private void inOrder(Node<E> node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.e + "  ");
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    // 后续遍历以node为根的二分搜索树，递归算法
    // 左-右-根
    // 先处理左右孩子，最后处理自身，这种性质可以用来释放二叉树的内存
    private void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + "  ");
    }

    // 二分搜索树非递归前序遍历
    // 使用栈模拟系统栈，保存调用位置
    public void preOrderNR() {
        if (root == null) return;

        Deque<Node<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> cur = stack.poll();
            System.out.print(cur.e + "  ");

            // 先入栈右子树再入栈左子树
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    // 二分搜索非递归中序遍历
    public void inOrderNR() {
        Deque<Node<E>> stack = new ArrayDeque<>();
        Node<E> root = this.root;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                // 左
                root = root.left;
            } else {
                Node<E> cur = stack.pop();
                // 根
                System.out.print(cur.e + "  ");
                // 右
                root = cur.right;
            }
        }
    }

    // 二分搜索非递归后序遍历
    public void postOrderNR() {
        if (root == null) return;

        Deque<Node<E>> stack = new ArrayDeque<>();
        Node<E> prev = null; // 前一次访问的节点
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> cur = stack.peek();
            if ((cur.left == null && cur.right == null)
                    || (prev != null && (cur.left == prev || cur.right == prev))) {
                // 当前节点不存在左右孩子节点或者孩子节点已经被访问时，访问该节点
                System.out.print(cur.e + "  ");
                prev = cur;
                stack.pop();
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth，描述二叉树的字符串
    // 采用先序遍历的方式
    private void generateBSTString(Node<E> node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }


    private static class Node<E> {
        E e;
        Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
//        BST<Integer> bst = new BST<>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
//        for(int num: nums)
//            bst.add(num);
//
//        //////////////////////////////
//        //      5     //
//        //    /   \    //
//        //   3    6   //
//        //  / \     \   //
//        // 2  4     8 //
//        /////////////////////////////
//        System.out.println("Preoder: ");
//        bst.preOrder();
//        System.out.println();
//        bst.preOrderNR();
//        System.out.println();
//        // System.out.println(bst);
//
//        System.out.println("Inorder: ");
//        bst.inOrder();
//        System.out.println();
//        bst.inOrderNR();
//        System.out.println();
//
//        System.out.println("Postorder: ");
//        bst.postOrder();
//        System.out.println();
//        bst.postOrderNR();
//        System.out.println();
//
//        System.out.println("Levelorder: ");
//        bst.levelOrder();
//        System.out.println();


//        testRemoveMin();
//        testRemoveMax();

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num: nums)
            bst.add(num);

        //////////////////////////////
        //      5     //
        //    /   \    //
        //   3    6   //
        //  / \     \   //
        // 2  4     8 //
        /////////////////////////////
        System.out.println("Preoder: ");
        bst.preOrder();
        System.out.println();

        System.out.println("Remove node 5");
        bst.remove(5);
        bst.preOrder();
        System.out.println();
    }

    private static void testRemoveMin() {

        BST<Integer> bst = new BST<>();
        Random random = new Random();
        int n = 1000;

        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        List<Integer> nums = new ArrayList<>(n);
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }

        System.out.println("nums size: " + nums.size());
        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new IllegalStateException("Error!");
            }
        }
        System.out.println("removeMin test completed.");
    }

    private static void testRemoveMax() {
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        int n = 1000;

        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        List<Integer> nums = new ArrayList<>(n);
        while (!bst.isEmpty()) {
            nums.add(bst.removeMax());
        }

        System.out.println("nums size: " + nums.size());
        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) < nums.get(i)) {
                throw new IllegalStateException("Error!");
            }
        }
        System.out.println("removeMax test completed.");
    }
}
