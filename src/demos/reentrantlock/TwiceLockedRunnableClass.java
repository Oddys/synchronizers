package demos.reentrantlock;

import synchronizers.ReentrantLock;

public class TwiceLockedRunnableClass implements Runnable {
    private ReentrantLock lock;

    public TwiceLockedRunnableClass(ReentrantLock lock) {
        this.lock = lock;
    }

    public void inner() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " Inner starts");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Inner ends");
        lock.unlock();
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " starts");
        inner();
        System.out.println(Thread.currentThread().getName() + " ends");
        lock.unlock();
    }
}
