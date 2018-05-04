package demos.reentrantlock;

import synchronizers.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(new TwiceLockedRunnableClass(lock), "A").start();
        new Thread(new TwiceLockedRunnableClass(lock), "B").start();
        new Thread(new TwiceLockedRunnableClass(lock), "C").start();
    }
}
