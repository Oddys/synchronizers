package demos.coountdownlatch;

import synchronizers.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        int maxCount = 10;
        CountDownLatch latch = new CountDownLatch(maxCount);

        for (int i = 0; i < maxCount; i++) {
            new Thread(new Worker(latch)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main");
    }
}
