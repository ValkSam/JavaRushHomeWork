package myTests.testSync.Test3_volatile;

/*
есть клас, котрый заполняет список, переданный ему в конструкторе
и есть класс, который читает список, переданный ему в конструкторе
при этом передается один и тот же список.
Т.е. два класса, каждый в двух потоках, пишут и читаю из одного списка
//
есть вариант с использованием wait/notify
package myTests.testSync.Test4_volatile;
*/

/*
Пример в package myTests.testSync.Test3_1_volatile
отличается следующим:
  volatile переменная вынесена как общая для использующих ее классов - т.е. переменная вынесена из классов и
  они обращаются к ней как к внешней переменной.
  При этом, если убрать volatile, то каждый поток создаст в своем кеше ее копию и работать не будет.
  //
  в текущем примере списки сразу хранятся внутри классов (являются приват полем), но объединены через ссылку на внешний список
  (внешний список передается как параметр в конструкторе). При этом приват поля работают синхронной за счет объявления
  каждого из них volatile
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Valk on 22.03.15.
 */
public class Transfer_one_object_between_other_class {
    public static void main(String[] args) throws Exception {
        System.out.println("!===========");
        List<String> altDrop = new ArrayList<>();
        (new Thread(new altConsumer(altDrop))).start();
        (new Thread(new altConsumer(altDrop))).start();
        Thread.sleep(300); //чтобы гарантированно подойти к "while (drop.size() == 0) {}" (см. комментарий "зачем volatile.... ")
        //
        Thread thread2 = new Thread(new altProducer(altDrop));
        thread2.start();
        Thread thread3 = new Thread(new altProducer(altDrop));
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

    public altProducer(List<String> d) {
        this.drop = d;
    }

    public void run() {
        for (String s : messages) {
            synchronized (drop) {
                String msg = Thread.currentThread().getName() + " " + s + " - " + drop.size();
                drop.add(msg);
                System.out.println("put " + msg);
            }
            try {
                Thread.sleep(0 + (int) (100 * Math.random()));
            } catch (InterruptedException e) {
            }
        }
        String msg = Thread.currentThread().getName() + " " + "DONE";
        drop.add("DONE");
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

    public altConsumer(List<String> d) {
        this.drop = d;
    }

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
        while (drop.size() == 0) {
            //synchronized (drop){} - это можно вместо volatile, т.к.
        }
        try {
            Thread.sleep(50 + (int) (100 * Math.random()));
        } catch (InterruptedException e) {
        }
        synchronized (drop) {
            if (drop.size() != 0) {
                result = drop.get(0);
                drop.remove(0);
            }
        }
        return result;
    }
}
