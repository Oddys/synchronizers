package synchronizers;

public class Semaphore {
    private int max;
    private int count;

    public Semaphore(int max) {
        this.max = max;
    }

    public synchronized void acquire() {
            while (count == max) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            notify();
    }

    public synchronized void release() {
            while (count == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            notify();
    }
}
