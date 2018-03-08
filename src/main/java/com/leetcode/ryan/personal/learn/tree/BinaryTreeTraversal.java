package com.leetcode.ryan.personal.learn.tree;

import com.leetcode.ryan.personal.component.TreeNode;
import com.leetcode.ryan.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BinaryTreeTraversal
 * @date March 08,2018
 */
@Slf4j
public class BinaryTreeTraversal {

    // -------  使用递归（系统栈）-------------------
    // 根-左-右
    public static void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // 左-根-右
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // 左-右-根
    public static void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    // -------  使用迭代（模拟系统栈）-------------------
    // 根-左-右：往栈里放，先放右子树再放左子树，出栈的时候就是先左子树后右子树
    public static void preorderStack(TreeNode root) {
        if (root == null)  return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void preorderStack2(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                // 根
                System.out.print(root.val + " ");

                if (root.right != null) {
                    stack.push(root.right);
                }
                root = root.left;

            } else {
                root = stack.pop();
            }
        }
    }

    // 左-根-右
    public static void inorderStack(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                // 左
                root = root.left;
            } else {
                // 根
                root = stack.pop();
                System.out.print(root.val + " ");
                // 右
                root = root.right;
            }
        }
    }

    // 左-右-根 （可以按照根-右-左的顺序遍历(模仿根-左-右的迭代实现)，记录遍历结果，然后reverse）
    /**
     * 要保证根结点在左孩子和右孩子访问之后才能访问，因此对于任一结点P，先将其入栈。
     * 如果P不存在左孩子和右孩子，则可以直接访问它；
     * 或者P存在左孩子或者右孩子，但是其左孩子和右孩子都已被访问过了，则同样可以直接访问该结点。
     * 若非上述两种情况，则将P的右孩子和左孩子依次入栈，
     * 这样就保证了每次取栈顶元素的时候，左孩子在右孩子前面被访问，
     * 左孩子和右孩子都在根结点前面被访问。
      * @param root
     */
    public static void postorderStack(TreeNode root) {

        if (root == null) return;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur; // 当前节点
        TreeNode pre = null; // 前一次访问的节点
        stack.push(root);

        while (!stack.isEmpty()) {
            cur = stack.peek();

            if ((cur.left == null && cur.right == null)
                || (pre != null && (pre == cur.left || pre == cur.right))) {
                // 如果当前节点没有孩子节点或者孩子节点都已被访问
                System.out.print(cur.val + " ");
                stack.pop();
                pre = cur;
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


    // ---------------- BFS: 层序遍历 --------------
    // 使用队列先进先出的特性
    public static void levelorder(TreeNode root) {
        if (root == null ) return;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }

    }


    public static void main(String[] args) {
        int[] nodes = {3, 4, 6, 1, 0, 0, 8};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);

        // preorder: 3,4,1,6,8
        System.out.println("preorder recursively: ");
        preorder(root);
        System.out.println();
        System.out.println("preorder iteratively: ");
        preorderStack2(root);
        System.out.println();

        // inorder: 1,4,3,6,8
        System.out.println("inorder recursively: ");
        inorder(root);
        System.out.println();
        System.out.println("inorder iteratively: ");
        inorderStack(root);
        System.out.println();

        // postorder: 1,4,8,6,3
        System.out.println("postorder recursively: ");
        postorder(root);
        System.out.println();
        System.out.println("postorder iteratively: ");
        postorderStack(root);
        System.out.println();

        // levelorder: 3,4,6,1,8
        System.out.println("levelorder: ");
        levelorder(root);
    }

}
