package myTests.cas.level28.lesson06.home01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Valk on 13.05.15.
 */
public class MyThread extends Thread {

    private static volatile AtomicInteger priority = new AtomicInteger(0);

    public MyThread() {
        setPrior(null);
    }

    public MyThread(Runnable target) {
        super(target);
        setPrior(null);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setPrior(group);
    }

    public MyThread(String name) {
        super(name);
        setPrior(null);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setPrior(group);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setPrior(null);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setPrior(group);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setPrior(group);
    }

    private void setPrior(ThreadGroup group) {
        int currP;
        int newP;
        int maxP = group == null ? 11 : group.getMaxPriority();
        do {
            currP = priority.get();
            newP = currP < 10 ? currP+1 : 1;
            try {
                Thread.sleep((int) (Math.random() * 20 + 2));
            } catch (InterruptedException e) {
            }
        } while (!priority.compareAndSet(currP, newP));
        setPriority(Math.min(newP, maxP));
    }
}
