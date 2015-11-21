package myTests;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Valk on 22.03.15.
 */
public class testBlockingQueue {
    public static void main(String[] args) throws Exception
    {
        BlockingQueue<String> drop = new ArrayBlockingQueue(1, true);
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}


class Producer
        implements Runnable
{
    private BlockingQueue<String> drop;
    List<String> messages = Arrays.asList(
            "msg 1",
            "msg 2",
            "msg 3",
            "msg 4",
            "msg 5");

    public Producer(BlockingQueue<String> d) { this.drop = d; }

    public void run()
    {
        try
        {
            for (String s : messages) {
                System.out.println("put "+s);
                drop.put(s);
                Thread.sleep(100);
            }
            drop.put("DONE");
        }
        catch (InterruptedException intEx)
        {
            System.out.println("Interrupted! " +
                    "Last one out, turn out the lights!");
        }
    }
}

class Consumer
        implements Runnable
{
    private BlockingQueue<String> drop;
    public Consumer(BlockingQueue<String> d) { this.drop = d; }

    public void run()
    {
        try
        {
            String msg = null;
            while (!((msg = drop.take()).equals("DONE"))) {
                System.out.println(msg);
                Thread.sleep(300);
            }
        }
        catch (InterruptedException intEx)
        {
            System.out.println("Interrupted! " +
                    "Last one out, turn out the lights!");
        }
    }
}
//*************************
