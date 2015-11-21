package myTests.testSync.Test1;

import java.util.*;

/**
 * Создается объект List, который используется в двух Runnable-объктах, живущих в разных потоках
 * В одном потоке устанавливается synchronized (list)
 * В другом потоке в это время используется list (пишется, читается)
 * Цель:
 * Показать, что синхронизация по объекту не означает его блокировку:
 * при синхронизаии по объекту, мы блокируем участок кода, который синхронизирован по этому объекту,
 * в случае, если в каком-то другом месте другой код ранее синхронизировлся по этому же объекту
 * Т.е. объект, по которому сделана синхронизация, выступает просто в роли семафора, и это никак не отражается на функциональности этого объекта
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
    public B (List list){
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("try to access list ...");
        //в потоке A list сейчас заблокирован, но можно спокойно им пользоваться (писать в него и читать из него),
        // т.е. доступ к нему не закрыт.
        list.add("2");
        System.out.println("  list accessed: " + list.get(0));
        System.out.println("  list accessed: " + list.get(1));
        //На выходе получим
        /*
        list blocked in A ...
        try to access list ...
          list accessed: 1
          list accessed: 2
        ... list released in A
        */
        //а не
        /*
        list blocked in A ...
        try to access list ...
        ... list released in A
          list accessed: 1
          list accessed: 2
        */
        //
        //дело в том, что блокируется доступ не к самому объекту,
        // а блокируется выполнение участка кода, у которого стоит признак синхронизации по конкретному объекту
        //Т.е. надо указать определеному участку кода, что он синхронизирован по такому-то объекту, и пока данный объект
        //используется другим кодом, который так же синхонизирован на данный объект, наш ко должен ждать
        //правильный вариант см. в Test2
    }
}