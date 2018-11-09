package com.github.ryan.personal.data_structure.concurrent.blocking_queue;

import java.util.concurrent.Semaphore;

/**
 * 经典同步队列实现采用了三个信号量，代码很简单，比较容易理解
 * 在多核机器上，上面方法的同步代价仍然较高，操作系统调度器需要上千个时间片来阻塞或唤醒线程，
 * 而上面的实现即使在生产者put()时已经有一个消费者在等待的情况下，阻塞和唤醒的调用仍然需要
 */
public class SemaphoreSynchronousQueue<E> implements BlockingQueue<E> {

    private E item = null;
    private Semaphore sync = new Semaphore(0);
    private Semaphore send = new Semaphore(1);
    private Semaphore recv = new Semaphore(0);

    @Override
    public void put(E e) throws InterruptedException {
        send.acquire();
        item = e;
        recv.release();
        sync.acquire();
    }

    @Override
    public E take() throws InterruptedException {
        recv.acquire();
        E ret = item;
        sync.release();
        send.release();
        return ret;
    }
}
