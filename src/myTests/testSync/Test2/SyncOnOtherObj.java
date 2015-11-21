package myTests.testSync.Test2;

import java.util.ArrayList;
import java.util.List;

/**
 * * Создается объект List, который используется в двух Runnable-объктах, живущих в разных потоках
 * В одном потоке устанавливается synchronized (list)
 * В другом потоке в это время используется list (пишется, читается).
 * Но в отличие от Test1, участок кода, который использует List, синхронизирован по list
 * Цель:
 * Показать, что синхронизация по объекту не означает его блокировку (это показал Test1), но если в другом потоке
 * будет синхронизирован по тому же самому объекту участок кода, т участок кода будет заблокирован
 * Created by Valk on 22.03.15.
 */
public class SyncOnOtherObj {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        A a = new A(list);
        B b = new B(list);
        new Thread(a).start();
        try {Thread.sleep(100);} catch (InterruptedException e){} //пауз, чтобы успеть положить в list в потоке A
        new Thread(b).start();
        try {Thread.sleep(5000);} catch (InterruptedException e){}
    }
}


class A implements Runnable {
    private List<String> list;
    public A (List list){
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){
            System.out.println("list blocked in A ... ");
            list.add("1");
            try {Thread.sleep(1000);} catch (InterruptedException e){}
        }
        System.out.println("... list released in A");
    }
}

class B implements Runnable {
    private List<String> list;

    public B(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("try to access list ...");
        //в потоке A list сейчас заблокирован (т.е. участок кода в Потоке1 синхронизрован на объекте list
        //и пока этот участок кода не отработает, мы будем ждать
        // т.е. доступ к другому коду, который также синхронизирован на list, - закрыт.
        synchronized (list) {
            list.add("2");
            System.out.println("  list accessed: " + list.get(0));
            System.out.println("  list accessed: " + list.get(1));
        }
        //На выходе получим
        /*
        list blocked in A ...
        try to access list ...
        ... list released in A
          list accessed: 1
          list accessed: 2
        */

    }
}