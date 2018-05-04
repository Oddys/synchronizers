package synchronizers;

public class CyclicBarrier {
    private final int parties;
    private int partiesCount;

    public CyclicBarrier(int parties) {
        if (parties < 1) {
            throw new IllegalArgumentException();
        }
        this.parties = parties;
    }

    public synchronized void await() throws InterruptedException {
            partiesCount++;
            if (partiesCount < parties) {
                wait();
            } else {
                partiesCount = 0;
                notifyAll();
            }
    }

    /**
     * Primitive reset implementation
     * Notifies all waiting threads, resets counter. Notified threads don't
     * throw BrokenBarrierException
     */
    public synchronized void reset() {
        partiesCount = 0;
        notifyAll();
    }

    /**
     * Returns the number of parties currently waiting at the barrier.
     * @return the number of parties currently blocked in await()
     */
    public synchronized int getNumberWaiting() {
        return partiesCount;
    }
}
