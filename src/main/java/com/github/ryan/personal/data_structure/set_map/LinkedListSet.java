package com.github.ryan.personal.data_structure.set_map;

import com.github.ryan.personal.data_structure.linkedlist.LinkedList;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedListSet
 * 增／删／查：时间复杂度 O(n)
 *
 * @date August 13,2018
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!contains(e)) {
            // 在LinkedList的实现中没有记录尾节点位置
            // 从链表头部插入时间复杂度为O(1)
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
