package demos.threadpool;

public class TestRunnableClass implements Runnable {
    private String name;

    public TestRunnableClass(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " starts executing task " + name);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(threadName + "'s sleep interrupted");
        }
        System.out.println(threadName + " finished executing task " + name);
    }
}
