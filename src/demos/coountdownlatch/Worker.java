package demos.coountdownlatch;

import synchronizers.CountDownLatch;

public class Worker implements Runnable {
    private CountDownLatch latch;

    Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " starts");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " counts down");
        latch.countDown();
    }
}
