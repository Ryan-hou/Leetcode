package com.github.ryan.data_structure.concurrent.lock.spin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className TicketLock
 * @date December 17,2018
 */
public class TicketLock {

    /**
     * 思路：
     * 每当有线程获取锁的时候，就给该线程分配一个递增的id，我们称之为排队号
     * 同时，锁对应一个服务号，每当有线程释放锁，服务号就会递增
     * 此时如果服务号与某个线程排队号一致，那么该线程就获得锁
     * 由于排队号是递增的，所以就保证了最先请求获取锁的线程可以最先获取到锁，就实现了公平性。
     * <p>
     * 存在的问题：
     * 多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum
     * 每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，降低系统整体的性能
     */
    private final AtomicInteger serviceNum = new AtomicInteger();
    private final AtomicInteger ticketNum = new AtomicInteger();
    // 存储每个线程的排队号
    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<>();

    public void lock() {
        int myTicket = ticketNum.getAndIncrement();
        LOCAL.set(myTicket);
        while (myTicket != serviceNum.get()) {
            // 自旋
        }
    }

    public void unlock() {
        int myTicket = LOCAL.get();
        serviceNum.compareAndSet(myTicket, myTicket + 1);
    }
}
