package com.github.ryan.personal.data_structure.concurrent.blocking_queue;

/**
 * SynchronousQueue是一个没有数据缓冲的BlockingQueue，
 * 生产者线程对其的插入操作put必须等待消费者的移除操作take，反过来也一样。
 *
 * 不像ArrayBlockingQueue或LinkedListBlockingQueue，SynchronousQueue内部并没有数据缓存空间，
 * 你不能调用peek()方法来看队列中是否有数据元素，因为数据元素只有当你试着取走的时候才可能存在，
 * 不取走而只想偷窥一下是不行的
 *
 * 可以这样来理解：生产者和消费者互相等待对方，握手，然后一起离开
 *
 * 该类使用阻塞算法实现：
 * 阻塞算法实现通常在内部采用一个锁来保证多个线程中的put()和take()方法是串行执行的。
 * 采用锁的开销是比较大的，还会存在一种情况是线程A持有线程B需要的锁，B必须一直等待A释放锁，
 * 即使A可能一段时间内因为B的优先级比较高而得不到时间片运行
 * 所以在高性能的应用中我们常常希望规避锁的使用
 */
public class NativeSynchronousQueue<E> implements BlockingQueue<E> {

    private boolean putting = false;
    private E item = null;

    @Override
    public synchronized void put(E e) throws InterruptedException {
        if (item == null) throw new NullPointerException();

        while (putting) {
            wait();
        }

        putting = true;
        item = e;
        notifyAll();
        while (item != null) {
            wait();
        }
        putting = false;
        notifyAll();
    }

    @Override
    public synchronized E take() throws InterruptedException {
        while (item == null) {
            // while循环避免虚假唤醒
            wait();
        }
        E e = item;
        item = null;
        notifyAll();
        return e;
    }
}
