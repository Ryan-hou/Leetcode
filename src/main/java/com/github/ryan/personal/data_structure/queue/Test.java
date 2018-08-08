package com.github.ryan.personal.data_structure.queue;

import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Test
 * @date August 08,2018
 */
public class Test {

    // 测试使用q运行opCount个入队和出队操作所需要的时间，单位秒
    // 运行多次取平均值，数据更加合理(最终数据和jdk版本，JVM，操作系统，平台硬件等都有关系)
    public static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random r = new Random();
        for (int i = 0; i < opCount; i++) {
           q.offer(r.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            // ArrayQueue的时间复杂度为O(n),LoopQueue为O(1)
            q.poll();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }


    /**
     * {@link com.github.ryan.personal.algorithm.test.DataSizeTest}：
     * 参考时间复杂度相关性能数据
     */
    public static void main(String[] args) {

        int opCount = 100000;

        Queue<Integer> arrayQueue = new ArrayQueue<>();
        double arrayQueueTime = testQueue(arrayQueue, opCount);
        System.out.printf("ArrayQueue, time: %s s%n", arrayQueueTime);
        // ArrayQueue, time: 5.231732624 s

        Queue<Integer> loopQueue = new LoopQueue<>();
        double loopQueueTime = testQueue(loopQueue, opCount);
        System.out.printf("LoopQueue, time: %s s%n", loopQueueTime);
        // LoopQueue, time: 0.024493552 s
    }
}
