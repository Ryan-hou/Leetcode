package com.github.ryan.algorithm.leetcode.medium.medium449;

import com.github.ryan.algorithm.personal.component.TreeNode;

public class Codec {

    private StringBuilder b;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        b = new StringBuilder();
        preorder(b, root);
        return b.toString();
    }

    private void preorder(StringBuilder b, TreeNode node) {
        if (node == null) return;
        b.append(node.val + ",");
        preorder(b, node.left);
        preorder(b, node.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] vals = data.split("\\,");
        TreeNode dummy = new TreeNode(0);
        dummy.left = antiPreorder(vals, 0, vals.length);
        return dummy.left;
    }

    // construct BST from vals[l...r)
    private TreeNode antiPreorder(String[] vals, int l, int r) {
        if (l >= r) return null;

        int rootVal = Integer.valueOf(vals[l]);
        TreeNode root = new TreeNode(rootVal);
        int i = l + 1;
        while (i < r && Integer.valueOf(vals[i]) < rootVal) {
            i++;
        }
        root.left = antiPreorder(vals, l + 1, i);
        root.right = antiPreorder(vals, i, r);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
