package myTests.QueueTest.ArrayBlockingQueueTest;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Valk on 25.04.15.
 */
public class Consumer implements Runnable {
    protected BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            int i = 0;
            while (true) {
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }

}
