package myTests.testSync.Test3_1_volatile;

/*
есть клас, котрый заполняет список, переданный ему в конструкторе
и есть класс, который читает список, переданный ему в конструкторе
при этом передается один и тот же список.
Т.е. два класса, каждый в двух потоках, пишут и читаю из одного списка
//
есть вариант с использованием wait/notify
package myTests.testSync.Test4_volatile;
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Valk on 22.03.15.
 */
public class Transfer_one_object_between_other_class {
    public static volatile List<String> altDrop = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("!===========");
        (new Thread(new altConsumer())).start();
        (new Thread(new altConsumer())).start();
        Thread.sleep(300); //чтобы гарантированно подойти к "while (drop.size() == 0) {}" (см. комментарий "зачем volatile.... ")
        //
        Thread thread2 = new Thread(new altProducer());
        thread2.start();
        Thread thread3 = new Thread(new altProducer());
        thread3.start();
        Thread.sleep(3000);
        System.out.println("end");
    }
}

class altProducer implements Runnable {
    private volatile List<String> drop;
    List<String> messages = Arrays.asList(
            "msg - 1",
            "msg - 2",
            "msg - 3",
            "msg - 4",
            "msg - 5",
            "msg - 6",
            "msg - 7");

        public void run() {
        for (String s : messages) {
            synchronized (Transfer_one_object_between_other_class.altDrop) {
                String msg = Thread.currentThread().getName() + " " + s + " - " + Transfer_one_object_between_other_class.altDrop.size();
                Transfer_one_object_between_other_class.altDrop.add(msg);
                System.out.println("put " + msg);
            }
            try {
                Thread.sleep(0 + (int) (100 * Math.random()));
            } catch (InterruptedException e) {
            }
        }
        String msg = Thread.currentThread().getName() + " " + "DONE";
        Transfer_one_object_between_other_class.altDrop.add("DONE");
        System.out.println("put " + msg);
    }
}

class altConsumer implements Runnable {
    //private List<String> drop;
    //зачем volatile. Если без volatile, то, если  dropGet на
    //while (drop.size() == 0) {}
    //попадем раньше, чем будет добавлена хотя бы одна запись в drop - будет гонять этот цикл бесконечно,
    //не видя, что длина drop уже не нулевая. Будет вывод:
    /*
    put msg - 1
    put msg - 2
    put msg - 3
    put msg - 4
    put msg - 5
    */
    private volatile List<String> drop;

    public void run() {
        String msg = null;
        while (!((msg = dropGet()).equals("DONE"))) {
            System.out.println("      " + Thread.currentThread().getName() + " " + msg);
        }
        System.out.println("      " + Thread.currentThread().getName() + " " + msg);
        System.out.println("===== end altConsumer");
    }

    private String dropGet() {
        String result = "";
        while (Transfer_one_object_between_other_class.altDrop.size() == 0) {
        }
        try {
            Thread.sleep(50 + (int) (100 * Math.random()));
        } catch (InterruptedException e) {
        }
        synchronized (Transfer_one_object_between_other_class.altDrop) {
            if (Transfer_one_object_between_other_class.altDrop.size() != 0) {
                result = Transfer_one_object_between_other_class.altDrop.get(0);
                Transfer_one_object_between_other_class.altDrop.remove(0);
            }
        }
        return result;
    }
}
