package com.leetcode.ryan.algorithm.medium.medium331;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 14,2017
 */
@Slf4j
public class Solution {

    /**
     * 思路:
     * When u iterate through the preorder traversal string, for each char:
     * case 1: u see a number c, means u begin to expand a new tree rooted with c,
     *  u push it to stack
     * case 2.1: u see a #, while top of stack is a number, u know this # is a left null child, put it
     *  there as a mark for next coming node k to know it is being the right child.
     * case 2.2: u see a #, while top of stack is #, u know u meet this # as right null child, u now
     *  cancel the sub tree(rooted as t, for example) with these two-# children. But wait, after the
     *  cancellation, u continue to check top of stack is whether # or a number:
     *  --- if a number, say u, you know u just cancelled a node t which is left child of u. You need to
     *  leave a # mark to the top of stack. So that next node know it is a right child.
     *  --- if a #, you know u just cancelled a tree whose root,t, is the right child of u. So you continue to
     *  cancel sub tree of u, and the process goes on and on.
     * @param preorder
     * @return
     */
    private static boolean isValidSerialization(String preorder) {
        if (preorder == null) return false;

        Deque<String> stack = new ArrayDeque<>();
        String[] strs = preorder.split(",");
        for (int pos = 0, length = strs.length; pos < length; pos++) {
            String curr = strs[pos];
            while ("#".equals(curr) && !stack.isEmpty() && "#".equals(stack.peek())) {
                stack.pop();
                // corner case: ##,###
                if (stack.isEmpty()) return false;
                stack.pop();
            }
            stack.push(curr);
        }
        return stack.size() == 1 && "#".equals(stack.peek());
    }

    /**
     * 思路:
     * If we treat null's as leaves, then the binary tree will always be full. A full
     * binary tree has a good property that # of leaves = # of nonleaves + 1.
     * Since we are given a pre_order serialization, we just need to find the shortest prefix of
     * the serialization sequence satisfying the property above. If such prefix does not exist, then
     * the serialization is definitely invalid; otherwise, the serialization is valid if and only if
     * the prefix is the entire sequence.
     * @param preorder
     * @return
     */
    private static boolean isValidSerialization2(String preorder) {
        int nonleaves = 0, leaves = 0, i = 0;
        String[] nodes = preorder.split(",");
        for (int length = nodes.length; i < length && (nonleaves + 1 != leaves); i++) {
            if ("#".equals(nodes[i])) leaves++;
            else nonleaves++;
        }
        return nonleaves + 1 == leaves && i == nodes.length;
    }

    public static void main(String[] args) {
        String  preOrder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        log.info("Is valid serialization? {}", isValidSerialization(preOrder));
        log.info("Is valid serialization? {}", isValidSerialization2(preOrder));
    }
}
