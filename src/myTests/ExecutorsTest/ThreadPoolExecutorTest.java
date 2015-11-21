package myTests.ExecutorsTest;

import java.util.concurrent.*;

/**
 * Created by Valk on 11.04.15.
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MILLISECONDS, blockingQueue);
        RunnableClass runnableTask = new RunnableClass(1);
        threadPoolExecutor.submit(runnableTask);
        threadPoolExecutor.shutdown();
    }
}


