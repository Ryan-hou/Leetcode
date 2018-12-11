package com.github.ryan.leetcode.algorithm.medium.medium138;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 11,2018
 */
public class Solution {

    // Definition for singly-linked list with a random pointer.
    private static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    // Visited dictionary to hold old node reference as "key" and new node reference
    // as the "value"
    private Map<RandomListNode, RandomListNode> visited = new HashMap<>();

    public RandomListNode getClonedNode(RandomListNode node) {
        if (node == null) return null;
        if (this.visited.containsKey(node)) {
            // If it's in the visited dictionary then return the
            // new node reference from the dictionary
            return this.visited.get(node);
        } else {
            // Otherwise create a new node, add to the dictionary and return it
            this.visited.put(node, new RandomListNode(node.label));
            return this.visited.get(node);
        }
    }

    // 时间复杂度 O(n) 空间复杂度 O(n)
    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) return null;
        RandomListNode oldNode = head;
        RandomListNode newNode = new RandomListNode(oldNode.label);
        this.visited.put(oldNode, newNode);

        // Iterate on the linked list until all nodes are cloned
        while (oldNode != null) {
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            // Move one step ahead in the linked list.
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visited.get(head);
    }

}
