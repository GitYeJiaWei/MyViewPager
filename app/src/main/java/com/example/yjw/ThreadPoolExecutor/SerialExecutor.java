package com.example.yjw.ThreadPoolExecutor;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author YJW
 * @create 2018/8/6
 * @Describe 多线程按照顺序执行
 */
public class SerialExecutor {
    final Queue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public void addrun(Runnable r) {
        tasks.add(r);
    }

    public  void execute(final Runnable r) {
        try {
            r.run();
        } finally {
            scheduleNext();
        }
    }

   /* public synchronized void execute(final Runnable r) {
        tasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }*/

    protected void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            this.execute(active);
        }
    }
}
