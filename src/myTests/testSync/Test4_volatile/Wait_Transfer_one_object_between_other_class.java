package myTests.testSync.Test4_volatile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Valk on 22.03.15.
 в развитие package myTests.testSync.Test3_volatile

 */
public class Wait_Transfer_one_object_between_other_class {
    public static void main(String[] args) throws Exception
    {
        System.out.println("!!===========");
        List<String> altDrop = new ArrayList<>();
        (new Thread(new altConsumer(altDrop))).start();
        (new Thread(new altConsumer(altDrop))).start();
        try {Thread.sleep(300);} catch (InterruptedException e){} //пауза, чтобы гарантрованно успели подойти к
        //в dropGet на
        //while (drop.size() == 0) {} - см. примечание ниже для
        // private List<String> drop; в altConsumer
        Thread thread2 = new Thread(new altProducer(altDrop));
        thread2.start();
        Thread thread3 = new Thread(new altProducer(altDrop));
        thread3.start();
        Thread.sleep(10000);
        System.out.println("end");
    }
}

class altProducer
        implements Runnable
{
    private volatile List<String> drop;
    List<String> messages = Arrays.asList(
            "msg - 1",
            "msg - 2",
            "msg - 3",
            "msg - 4",
            "msg - 5",
            "msg - 6",
            "msg - 7");

    public altProducer(List<String> d) { this.drop = d; }

    public void run() {
        for (String s : messages) {
            synchronized (drop) {
                String msg = Thread.currentThread().getName()+" "+s+" - "+drop.size();
                drop.add(msg);
                System.out.println("put " + msg);
                if (drop.size() == 1) {
                    drop.notifyAll();
                }
            }
            try {Thread.sleep(0+(int)(100*Math.random()));} catch (InterruptedException e){}
        }
        String msg = Thread.currentThread().getName()+" "+"DONE";
        drop.add("DONE");
        System.out.println("put " + msg);
    }
}

class altConsumer
        implements Runnable
{
    private volatile List<String> drop; //по тестам - работает без volatile(в отличие от
    // package myTests.testSync.Test3_volatile ), но вопрос - насколько стабильно
    public altConsumer(List<String> d) { this.drop = d; }

    public void run() {
        String msg = null;
        try {
            while (!((msg = dropGet()).equals("DONE"))) {
                System.out.println("      " + Thread.currentThread().getName() + " " + msg);
                //try {Thread.sleep(1);} catch (InterruptedException e){}
            }
        } catch (Exception e) {}
        System.out.println("      "+Thread.currentThread().getName()+" "+msg );
        System.out.println("===== end altConsumer");
    }

    private String dropGet() throws Exception{
        String result;
        while (true) {
            try {Thread.sleep(50+(int)(100*Math.random()));} catch (InterruptedException e){}
            synchronized (drop) {
                if (drop.size() == 0) {
                    drop.wait();
                }
            }
            synchronized (drop) {
                if (drop.size() != 0) {
                    result = drop.get(0);
                    try {
                        Thread.sleep(0 + (int) (0 * Math.random()));
                    } catch (InterruptedException e) {
                    }
                    drop.remove(0);
                    return result;
                }
            }
        }
    }
}
