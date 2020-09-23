package com.github.ryan.data_structure.tree.segment_tree;

/**
 * 区间融合器，由用户自定义区间信息如何融合
 * @param <E>
 */
@FunctionalInterface
public interface Merger<E> {
    E merge(E a, E b);
}
