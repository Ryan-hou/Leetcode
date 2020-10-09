package com.github.ryan.data_structure.graph.network_flows;

import com.github.ryan.data_structure.graph.base.WeightedGraph;

/**
 * 普林斯顿计算机系棒球比赛课程作业:
 * https://www.cs.princeton.edu/courses/archive/spr03/cos226/assignments/baseball.html
 *
 * 最大流算法建模 -> 分配问题
 * 问题关键：其他队互相打完比赛以后，能否都最多 76 场胜利？
 * 也就是要求建模后的网络流的最大流能否比其他队相互之间的比赛总数要大
 * 如果最大流大于等于比赛总数，那么底特律存在夺冠机会
 */
public class Baseball {

    public static void main(String[] args) {
        WeightedGraph network = new WeightedGraph("baseball.txt", true);
        MaxFlow maxFlow = new MaxFlow(network, 0, 10);
        // Max flow is 26 < 27 -> 底特律不存在夺冠机会
        System.out.println("Max flow: " + maxFlow.getMaxFlow());
    }

}
