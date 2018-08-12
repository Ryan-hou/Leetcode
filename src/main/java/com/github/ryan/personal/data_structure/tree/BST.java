package com.github.ryan.personal.data_structure.tree;

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

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            // e.compareTo(node.e) > 0
            return contains(node.right, e);
        }
    }

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
        // System.out.println(bst);

        System.out.println("Inorder: ");
        bst.inOrder();
        System.out.println();

        System.out.println("Postorder: ");
        bst.postOrder();
        System.out.println();
    }
}
