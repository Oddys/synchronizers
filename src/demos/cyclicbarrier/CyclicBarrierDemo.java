package demos.cyclicbarrier;

import synchronizers.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int numOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numOfThreads);

        for (int i = 0; i < numOfThreads; i++) {
            new Thread(new RunnableWithCyclicBarrier(barrier)).start();
        }

        /* Repeat to show that cyclic barrier is reusable */
        for (int i = 0; i < numOfThreads; i++) {
            new Thread(new RunnableWithCyclicBarrier(barrier)).start();
        }

        /* Demonstrate that reset() works */
        /* Set num of threads greater than barrier's parties */
        for (int i = 0; i < numOfThreads + 1; i++) {
            new Thread(new RunnableWithCyclicBarrier(barrier)).start();
            /* Reset after the first thread has come to barrier */
            if (i == 0) {
                try {
                    Thread.sleep(100);     // Wait so that new thread can run
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(barrier.getNumberWaiting());
                barrier.reset();
            }
        }
    }
}
