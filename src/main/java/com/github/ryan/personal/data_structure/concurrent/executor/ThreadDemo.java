package com.github.ryan.personal.data_structure.concurrent.executor;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className ThreadDemo
 * @date November 12,2018
 */
public class ThreadDemo {

    private class ExceptionTask implements Runnable {
        @Override
        public void run() {
            System.out.println("exception task start ......");
            int i = 1 / 0;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        try {
            // new Thread(new ThreadDemo().new ExceptionTask()).start();
            Thread t1 = new Thread(new ThreadDemo().new ExceptionTask());
            t1.setUncaughtExceptionHandler((t, e) -> {
                System.out.println("Main thread's child thread exception!!");
                // From ThreadGroup: void uncaughtException(Thread t, Throwable e) {}
                System.out.print("Exception in thread \""
                        + t.getName() + "\" ");
                // System.out -> stdout
                // System.err -> stderr
                // System.in -> stdin
                e.printStackTrace();
            });
            t1.start();
        } catch (RuntimeException e) {
            // 本质上来说，分一个线程，意味着不必等
            // 异常应由所在的线程处理，别的线程没有义务或上下文，来处理你的异常
            // 就好像这里的代码，新线程里出异常，原线程可能已经跑完了
            System.out.println("Never execute here !!!");
        }
    }

}
