package com.github.ryan.data_structure.set_map;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className SetTest
 * @date August 13,2018
 */
public class SetTest {

    private static double testSet(Set<String> set, String filename) {
        long startTime = System.nanoTime();

        System.out.println(filename);
        List<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.size());
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String filename = "pride-and-prejudice.txt";

        Set<String> bstSet = new BSTSet<>();
        double bstSetTime = testSet(bstSet, filename);
        System.out.println("BSTSet: " + bstSetTime + "s");

        System.out.println();

        Set<String> linkedListSet = new LinkedListSet<>();
        double linkedListSetTime = testSet(linkedListSet, filename);
        System.out.println("LinkedListSet: " + linkedListSetTime + "s");

        System.out.println();

        Set<String> avlSet = new AVLSet<>();
        double avlSetTime = testSet(avlSet, filename);
        System.out.println("AVLSet: " + avlSetTime + "s");
    }
}
