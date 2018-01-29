package com.leetcode.ryan.personal.component;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Command
 * @date January 29,2018
 */
public class Command {

    public String s;
    public TreeNode treeNode;

    public Command(String s, TreeNode treeNode) {
        this.s = s;
        this.treeNode = treeNode;
    }

    public enum CommandStrEnum {

        PRINT(1, "print"), GO(2, "go");

        int val;
        String name;

        CommandStrEnum(int val, String name) {
            this.val = val;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
