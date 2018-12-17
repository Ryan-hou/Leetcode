package com.github.ryan.personal.data_structure.concurrent.lock.spin;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className CLHLock
 * @date December 17,2018
 */
public class CLHLock {
    /**
     * 思路：
     * CLH锁是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，
     * 它不断轮询前驱的状态，如L果发现前驱释放了锁就结束自旋，获得锁
     * 缺点：
     * CLHLock是不停的查询前驱变量， 导致不适合在NUMA 架构下使用（在这种结构下，每个线程分布在不同的物理内存区域）
     */

    private static final class CLHNode {
        volatile boolean isLocked = true; // 默认的lock状态为true
    }

    private volatile CLHNode tail; // 尾节点
    private static final ThreadLocal<CLHNode> LOCAL = new ThreadLocal<>();
    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
        // 新建节点并将节点与当前线程保存起来
        CLHNode node = new CLHNode();
        LOCAL.set(node);

        // 将新建的节点设置为尾部节点，并返回旧的节点（原子操作),这里旧的节点实际上就是当前节点的前驱节点
        CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            // 前驱节点不为null表示当锁被其他线程占用，通过不断轮询判断前驱节点的锁标志位等待前驱节点释放锁
            // decentralized, avoiding some memory contention.
            // 申请线程只在本地变量上自旋，它不断轮询前驱的状态
            // 对比 TicketLock: 多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum
            while (preNode.isLocked) {
            }
        }
        // else: 不存在前驱节点，表示该锁没有被其他线程占用，则当前线程获得锁

    }

    public void unlock() {
        // 获取当前线程对应的节点
        CLHNode node = LOCAL.get();
        // 如果tail节点等于node，则将tail节点更新为null，同时将node的lock状态职位false，表示当前线程释放了锁
        if (!UPDATER.compareAndSet(this, node, null)) {
            node.isLocked = false;
        }
        LOCAL.remove();
    }

}
