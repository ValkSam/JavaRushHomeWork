package myTests.ExecutorsTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Valk on 08.04.15.
 */
public class CallableAndRunnableTest {
    static Thread mainThread;
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        {
            System.out.println("1. Пример конвертации (оборачивания) Runnable-объекта в Callable-объект");
            System.out.println("Создаем Callable-объект, который, после своего запуска запустит Runnable задачу и вернет Result 1");
            System.out.println("    (новая нить создана не будет - Runnable-объект будет работать в нити main)");
            Callable<String> callable = Executors.callable(new RunnableClass(1), "Rusult 1");
            String result = null;
            try {
                System.out.println("запустили Callable-объект ...");
                result = callable.call();
                System.out.println("Callable-объект вернул: " + result);
            } catch (Exception e) {
                System.out.println("Что-то произошло: Callable-объект вернул: " + result);
            }
            System.out.println("MAIN продолжает свой ход");
            System.out.println();
        }

        {
            System.out.println("2. Пример конвертации (оборачивания) Runnable-объекта в Callable-объект");
            System.out.println("Создаем Callable-объект, который, после своего запуска запустит Runnable задачу и вернет null, т.к. в Runnable произошла ошибка");
            System.out.println("    (новая нить создана не будет - Runnable-объект будет работать в нити main)");
            Callable<String> callable = Executors.callable(new RunnableClass(1, true), "Rusult 1");
            String result = null;
            try {
                System.out.println("запустили Callable-объект ...");
                result = callable.call();
                System.out.println("Callable-объект вернул: " + result);
            } catch (Exception e) {
                System.out.println("Что-то произошло: Callable-объект вернул: " + result);
            }
            System.out.println("MAIN продолжает свой ход");
            System.out.println();
        }

        {
            System.out.println("3. Пример получения результата выполнения Callable-задачи");
            CallableAndRunnableTest.mainThread = Thread.currentThread();
            System.out.println(" ");
            ExecutorService service = Executors.newCachedThreadPool();
            Callable callableTask = new CallableClass<ArrayList<String>>(1);
            System.out.println("запуск задачи 1");
            Future result = service.submit(callableTask);
            double j = 0;
            for (int i = 0; i < 20000000; i++) {
                j = i*3/7*Math.random();
                //цикл, чтобы сделать паузу, до вызова get(), достаточную, чтобы задача успела запуститься и показать нить в статусе RUNNABLE
                //после вызова get(), увидим статус main равный WAITING
            }
            System.out.println(j);
            System.out.println("результат задачи: "+result.get()); //get() заставляет ждать завершения - main в паузе. Можем это видеть в консоли.
            System.out.println();
            result = service.submit(new CallableClass<ArrayList<String>>(2));
            try {
                System.out.println("результат задачи: "+result.get(100,TimeUnit.MILLISECONDS)); //get(время) заставляет ждать указанное время, а потом или результат задачи или ее прерывание
            } catch (TimeoutException e) {
                System.out.println("задача не успела завершиться в заданное время. Но она жива и работает. Пробуем прервать");
                result.cancel(true);
                try{
                    System.out.println("Результат задачи: "+result.get());
                } catch(CancellationException ee) {
                    System.out.println("Результат задачи не смогли получить, т.к. она прервана. \nХотя, как видим по консоли, продолжает работать (продолжаем выводиь состояние main)");
                }
            }
            service.shutdown();
            System.out.println("Ждем, пока задача закончит работать и снова попробуем узнать ее результат ...");
            service.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("задача закончила работу. Пробуем узнать результат теперь ...");
            try{
                System.out.println("Результат задачи: "+result.get());
            } catch(CancellationException ee) {
                System.out.println("Результат задачи все равно не смогли получить, т.к. она прервана ");
            }

            System.out.println("КОНЕЦ main ");
        }

    }

}

class CallableClass<T extends List<String>> implements Callable{
    int i;

    public CallableClass(int i) {
        this.i = i;
    }

    @Override
    public T call() throws Exception {
        System.out.println("  запустилась "+i+" "+Thread.currentThread().getName()+" в нити "+Thread.currentThread().getName());
        for (int i = 1; i<=4; i++) {
            System.out.println("нить main в состоянии "+CallableAndRunnableTest.mainThread.getState()); //смотрим сосотяние нити main
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        List result = new ArrayList<>();
        result.add(i + " " + this.getClass().getSimpleName());
        System.out.println("  закончилась " + i);
        return (T)(result);
    }
}

class RunnableClass implements Runnable{
    int i;
    boolean exception;

    public RunnableClass(int i) {
        this.i = i;
    }

    public RunnableClass(int i, boolean exception) {
        this.i = i;
        this.exception = exception;
    }

    @Override
    public void run() {
        System.out.println("  запустилась "+i+" "+Thread.currentThread().getName()+" в нити "+Thread.currentThread().getName());
        int i = 0;
        if (exception) i = 1/i;
        try {Thread.sleep(3000);} catch (InterruptedException e){}
        System.out.println("закончилась "+i);
    }
}
