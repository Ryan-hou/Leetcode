package com.github.ryan.algorithm.leetcode.medium.medium144;

import com.github.ryan.algorithm.personal.component.Command;
import com.github.ryan.algorithm.personal.component.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 18,2017
 */
@Slf4j
public class Solution {

    /**
     * Recursive solution
     * @param root
     * @return
     */
    private static List<Integer> preorderTraversalOne(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doPreOrderTraversal(root, result);
        return result;
    }

    private static void doPreOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        doPreOrderTraversal(root.left, result);
        doPreOrderTraversal(root.right, result);
    }

    /**
     * Iterative solution
     * @param root
     * @return
     */
    private static List<Integer> preorderTraversalTwo(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                result.add(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                root = root.left;
            } else {
                root = stack.pop();
            }
        }
        return result;
    }

    /**
     * 模拟系统栈来把递归算法转为迭代
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Command> stack = new ArrayDeque<>();
        stack.push(new Command(Command.CommandStrEnum.GO.getName(), root));
        while (!stack.isEmpty()) {

            Command command = stack.pop();
            if (Command.CommandStrEnum.PRINT.getName().equals(command.s)) {
                res.add(command.treeNode.val);
            } else {
                assert (Command.CommandStrEnum.GO.getName().equals(command.s));

                // 入栈顺序与操作顺序为逆序，这样在出栈时即为正确顺序
                // 先序遍历为：根－左－右，入栈即为右－左－根 即可
                if (command.treeNode.right != null) {
                    stack.push(new Command(Command.CommandStrEnum.GO.getName(), command.treeNode.right));
                }
                if (command.treeNode.left != null) {
                    stack.push(new Command(Command.CommandStrEnum.GO.getName(), command.treeNode.left));
                }
                stack.push(new Command(Command.CommandStrEnum.PRINT.getName(), command.treeNode));
            }

        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(4);
        TreeNode left12 = new TreeNode(6);
        TreeNode right1 = new TreeNode(5);
        TreeNode rigth12 = new TreeNode(7);
        root.left = left1;
        left1.right = left12;
        root.right = right1;
        right1.left = rigth12;

        List<Integer> integers = preorderTraversal(root);
        log.info("result = {}", integers);
    }
}
