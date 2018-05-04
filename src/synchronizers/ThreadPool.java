package synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private List<ThreadForPool> pool;
    public BlockingQueue<Runnable> tasks;
    private boolean isStopped;

    class ThreadForPool extends Thread {
        private BlockingQueue<Runnable> tasks;
        private boolean isStopped;

        ThreadForPool(BlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (!isStopped) {
                try {
                    tasks.take().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void setStopped() {
            isStopped = true;
            this.interrupt();
        }
    }

    public ThreadPool(int numOfThreads, int maxNumOfTasks) {
        pool = new ArrayList<>(numOfThreads);
        tasks = new ArrayBlockingQueue<>(maxNumOfTasks);
        for (int i = 0; i < numOfThreads; i++) {
            pool.add(new ThreadForPool(tasks));
        }

        for (Thread thread: pool) {
            thread.start();
        }
    }

    public synchronized void execute(Runnable task) {
        if (isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        try {
            tasks.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        isStopped = true;
        for (ThreadForPool thread : pool) {
            thread.setStopped();
        }
    }
}
