package com.github.ryan.jianzhi.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className TreeCategory
 * @date October 08,2018
 */
public class TreeCategory {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     */
    public int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 输入描述:
     * 二叉树的镜像定义：源二叉树
     * 8
     * /  \
     * 6   10
     * / \  / \
     * 5 7 9 11
     * 镜像二叉树
     * 8
     * /  \
     * 10  6
     * / \  / \
     * 11 97  5
     */
    public void mirror(TreeNode root) {
        if (root == null) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。
     * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        // t1!=null && t2!=null
        return t1.val == t2.val
                && isSymmetrical(t1.left, t2.right)
                && isSymmetrical(t1.right, t2.left);
    }

    /**
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        // 使用递归，top-down方式，计算treeDepth的过程存在重复
        // 可以使用 bottom-up 的思路优化：
        // 从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；
        // 如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次
        return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （
     * 5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4
     */
    // BST中序遍历为有序的
    public TreeNode KthNode(TreeNode pRoot, int k) {
        List<TreeNode> res = new ArrayList<>();
        inOrder(pRoot, res);
        if (k == 0 || res.size() < k) return null;

        return res.get(k - 1);
    }

    private void inOrder(TreeNode root, List<TreeNode> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root);
        inOrder(root.right, res);
    }

    private int index = 0;

    public TreeNode KthNode2(TreeNode pRoot, int k) {
        if (pRoot != null) {
            TreeNode lnode = KthNode2(pRoot.left, k);
            if (lnode != null) return lnode;
            if (++index == k) return pRoot;
            TreeNode rnode = KthNode2(pRoot.right, k);
            if (rnode != null) return rnode;
        }
        return null;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null) return true;
        if (sequence.length == 0) return false; // 在本题测试用例下为一个corner case

        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    // 递归函数语义：返回数组sequence[s...e]是否是某BST的后续遍历结果
    // 后续遍历 左-右-根，BST 左 < 根 < 右
    private boolean verifySequenceOfBST(int[] sequence, int s, int e) {
        if (s >= e) return true;

        // s < e
        int root = sequence[e];
        int pivot = s; // sequence[pivot]为右子树的第一个节点
        while (pivot < e && sequence[pivot] < root) {
            pivot++;
        }
        for (int i = pivot; i < e; i++) {
            if (sequence[i] < root) return false;
        }

        return verifySequenceOfBST(sequence, s, pivot - 1)
                && verifySequenceOfBST(sequence, pivot, e - 1);
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     */
    // 法一：使用递归
    // 中序遍历：左-根-右
    // 语义：返回已root为根的BST构成的有序双链表的头结点
    private TreeNode leftLast = null; // 左子树构成的双链表的最后一个节点

    public TreeNode Convert(TreeNode root) {
        if (root == null) return null;

        TreeNode leftRoot = Convert(root.left);
        if (leftRoot != null) {
            leftLast.right = root;
            root.left = leftLast;
        } else {
            leftRoot = root;
        }
        leftLast = root;

        TreeNode rightRoot = Convert(root.right);
        if (rightRoot != null) {
            root.right = rightRoot;
            rightRoot.left = root;
        }
        return leftRoot;
    }

    // 使用迭代--中序遍历
    public TreeNode Convert2(TreeNode p) {

        Deque<TreeNode> s = new ArrayDeque<>();
        TreeNode root = p;
        TreeNode ret = null;
        TreeNode prev = null; // 中序遍历访问到的节点的前一个节点
        while (root != null || !s.isEmpty()) {
            if (root != null) {
                // left放在栈顶
                s.push(root);
                root = root.left;
            } else {
                TreeNode node = s.pop();
                if (prev == null) {
                    ret = node;
                    prev = node;
                } else {
                    prev.right = node;
                    node.left = prev;
                    prev = node;
                }
                root = node.right;
            }
        }
        return ret;
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
     * 则重建二叉树并返回。
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(0, pre.length - 1, pre, 0, in.length - 1, in);
    }

    // 返回spre为根，in[sin...ein]构成的二叉树
    private TreeNode reConstructBinaryTree(int spre, int epre, int[] pre, int sin, int ein, int[] in) {
        if (spre > epre || sin > ein) return null;

        TreeNode root = new TreeNode(pre[spre]);
        for (int i = sin; i <= ein; i++) {
            // i为中序序列中spre(即根)的位置
            if (pre[spre] == in[i]) {
                root.left = reConstructBinaryTree(spre + 1, spre + i - sin, pre, sin, i - 1, in);
                root.right = reConstructBinaryTree(spre + i - sin + 1, epre, pre, i + 1, ein, in);
                break;
            }
        }
        return root;
    }

    /**
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */
    // 法一：层序遍历的基础上增加层数的判断
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;

        Deque<Pair> q = new ArrayDeque<>();
        q.add(Pair.make(pRoot, 0));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            TreeNode cur = pair.node;
            int level = pair.level;

            if (res.size() <= level) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(cur.val);
                res.add(level, list);
            } else {
                if (level % 2 == 0) {
                    res.get(level).add(cur.val);
                } else {
                    res.get(level).add(0, cur.val);
                }
            }

            if (cur.left != null) {
                q.add(Pair.make(cur.left, level + 1));
            }
            if (cur.right != null) {
                q.add(Pair.make(cur.right, level + 1));
            }
        }
        return res;
    }

    // 法二：使用递归，省去构建Pair的代码，第几层可以通过递归函数的参数来维护
    public ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        travel(pRoot, res, 0);
        return res;
    }

    // 递归语义：遍历以root为根，第level层的二叉树，结果保存到res
    private void travel(TreeNode root, ArrayList<ArrayList<Integer>> res, int level) {
        if (root == null) return;

        if (res.size() <= level) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
        } else {
            if ((level & 1) == 0) {
                // 偶数层
                res.get(level).add(root.val);
            } else {
                res.get(level).add(0, root.val);
            }
        }

        travel(root.left, res, level + 1);
        travel(root.right, res, level + 1);
    }

    // 使用双端队列：怎么使用？何时操作队尾，何时操作队首需要想清楚
    public ArrayList<ArrayList<Integer>> Print3(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res  = new ArrayList<>();
        if (pRoot == null) return res;

        Deque<TreeNode> dq = new ArrayDeque<>(); // 双端队列
        dq.add(pRoot);
        int level = 0;
        int numOfChild = 1;
        while (!dq.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            // 处理一层
            for (int i = 0; i < numOfChild; i++) {
                if ((level & 1) == 0) {
                    // 偶数层：队首入队，队尾出队，先左子树后右子树
                    TreeNode cur = dq.pollLast();
                    list.add(cur.val);
                    if (cur.left != null) {
                        dq.addFirst(cur.left);
                    }
                    if (cur.right != null) {
                        dq.addFirst(cur.right);
                    }
                } else {
                    // 奇数层：队首出队，队尾入队，先右子树后左子树
                    TreeNode cur = dq.pollFirst();
                    list.add(cur.val);
                    if (cur.right != null) {
                        dq.addLast(cur.right);
                    }
                    if (cur.left != null) {
                        dq.addLast(cur.left);
                    }
                }
            }

            res.add(list);
            level++;
            numOfChild = dq.size();
        }
        return res;
    }

    private static class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }

        static Pair make(TreeNode node, int level) {
            return new Pair(node, level);
        }
    }

}
