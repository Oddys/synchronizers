package demos.threadpool;

import synchronizers.ThreadPool;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(3, 5);
        String[] names = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (String name: names) {
            pool.execute(new TestRunnableClass(name));
        }
        while (!pool.tasks.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.stop();
    }
}
