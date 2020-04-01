package com.github.ryan.leetcode.algorithm.medium.medium284;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class PeekingIterator implements Iterator<Integer> {

    private int idx; // index of next element
    private List<Integer> data;

    public PeekingIterator(Iterator<Integer> iterator) {
        if (iterator == null) {
            throw new IllegalArgumentException("iterator can't be null!");
        }
        // initialize any member here.
        this.idx = 0;
        this.data = new ArrayList<>();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            this.data.add(num);
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasNext()) {
            throw new IllegalStateException("No data to peek!");
        }
        return data.get(idx);
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return data.get(idx++);
    }

    @Override
    public boolean hasNext() {
        return idx < data.size();
    }
}
