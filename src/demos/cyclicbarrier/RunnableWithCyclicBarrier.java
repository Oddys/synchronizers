package demos.cyclicbarrier;

import synchronizers.CyclicBarrier;

public class RunnableWithCyclicBarrier implements Runnable {
    private CyclicBarrier barrier;

    RunnableWithCyclicBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " reached the barrier");
            barrier.await();
            System.out.println(threadName + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
