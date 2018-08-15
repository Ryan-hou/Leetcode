package com.github.ryan.personal.data_structure.set_map;

import com.github.ryan.personal.data_structure.tree.avl.AVLTree;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className AVLSet
 * @date August 15,2018
 */
public class AVLSet<E extends Comparable<E>> implements Set<E> {

    // Dummy value to associate with an Object
    private static final Object PRESETN = new Object();

    private AVLTree<E, Object> avl;

    public AVLSet() {
        avl = new AVLTree<>();
    }

    @Override
    public void add(E e) {
        avl.add(e, PRESETN);
    }

    @Override
    public void remove(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return avl.contains(e);
    }

    @Override
    public int size() {
        return avl.size();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
