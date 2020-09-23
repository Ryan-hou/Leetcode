package com.github.ryan.data_structure.tree.rbt;

import com.github.ryan.data_structure.set_map.BSTMap;
import com.github.ryan.data_structure.tree.avl.AVLTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className RBTTest
 * @date August 16,2018
 */
public class RBTTest {

    public static void main(String[] args) {

        int n = 2000000;
        List<Integer> testData = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            //testData.add(i);
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        // Test BST
        long startTime = System.nanoTime();

        BSTMap<Integer, Integer> bst = new BSTMap<>();
        for (Integer x: testData)
            bst.put(x, null);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time + " s");

        // Test AVL
        startTime = System.nanoTime();

        AVLTree<Integer, Integer> avl = new AVLTree<>();
        for (Integer x : testData) {
            avl.add(x, null);
        }

        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVL: " + time + " s");

        // Test RBTree
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbt = new RBTree<>();
        for (Integer x : testData) {
            rbt.add(x, null);
        }

        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;

        System.out.println("RBTree: " + time + " s");
    }

}
