package myTests.ExecutorsTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * Created by Valk on 08.04.15.
 */
public class CallableAndRunnableTest {
    static Thread mainThread;

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        /*
        {
            Thread thread = null;
            System.out.println("0. Пример Runnable-объекта в отдельном потоке. Демо того, что происходит, если в потоке ошибка - мы не можем это перехватить");
            RunnableClass runnable = new RunnableClass(1, true); //параметр true провоцирует ошибку в Runnable-объекте
            try {
                System.out.println("запустили Runnable-объект ...");
                Thread.sleep(100);
                thread = new Thread(runnable);
                thread.start();
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("Что-то произошло: в Runnable-объекте");
                //т.к. Runnable не выбрасывает ошибку, то этого сообщения мы не увидим
            }
            System.out.println();
            if (thread.getState() != Thread.State.TERMINATED) {
                System.out.println("MAIN ждет завершения потока с Runnable-объектом");
                thread.join();
            }
            System.out.println("MAIN продолжает свой ход");
        }

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
            Future result = service.submit(callableTask); //закпуск в отдельном потоке
            double j = 0;
            for (int i = 0; i < 20000000; i++) {
                j = i * 3 / 7 * Math.random();
                //цикл, чтобы сделать паузу, до вызова get(), достаточную, чтобы задача успела запуститься и показать нить в статусе RUNNABLE
                //после вызова get(), увидим статус main равный WAITING
            }
            System.out.println(j);
            System.out.println("результат задачи: " + result.get()); //get() заставляет ждать завершения - main в паузе. Можем это видеть в консоли.
            System.out.println();
            //
            result = service.submit(new CallableClass<ArrayList<String>>(2));
            try {
                System.out.println("результат задачи: " + result.get(100, TimeUnit.MILLISECONDS)); //get(время) заставляет ждать указанное время, а потом или результат задачи или ее прерывание
            } catch (Exception e) {
                System.out.println("задача не успела завершиться в заданное время. Но она жива и работает. Пробуем прервать");
                result.cancel(true);
                try {
                    System.out.println("Результат задачи: " + result.get());
                } catch (CancellationException ee) {
                    System.out.println("Результат задачи не смогли получить, т.к. она прервана. \nХотя, как видим по консоли, продолжает работать (продолжаем выводиь состояние main)");
                }
            }

            service.shutdown();
            System.out.println("Ждем, пока задача закончит работать и снова попробуем узнать ее результат ...");
            service.awaitTermination(1, TimeUnit.DAYS); //ждем пока закончаться задачи после shutdown. Т.е. без shutdown, даже если по факту задача отработала, застрянем тут до истечения указанного времени
            System.out.println("задача закончила работу. Пробуем узнать результат теперь ...");
            try {
                System.out.println("Результат задачи: " + result.get());
            } catch (CancellationException ee) {
                System.out.println("Результат задачи все равно не смогли получить, т.к. она прервана ");
            }

            System.out.println("MAIN продолжает свой ход");
            System.out.println();
        }

        {
            System.out.println("4. Пример получения результата выполнения Callable-задачи");
            CallableAndRunnableTest.mainThread = Thread.currentThread();
            System.out.println(" ");
            ExecutorService service = Executors.newCachedThreadPool();
            Callable callableTask = new CallableClass<ArrayList<String>>(1);
            System.out.println("запуск задачи 1");
            Future result = service.submit(callableTask); //закпуск в отдельном потоке
            System.out.println("Ждем, пока задача закончит работать и попробуем узнать ее результат ...");
            while (! result.isDone()){}
            System.out.println("Результат задачи: " + result.get());
            System.out.println("MAIN продолжает свой ход");
        }*/
        /*
        {
            System.out.println("5. Пример ошибки выполнения Runnable-задачи");
            CallableAndRunnableTest.mainThread = Thread.currentThread();
            System.out.println(" ");
            ExecutorService service = Executors.newCachedThreadPool();
            Runnable runnableTask = new RunnableClass(1, true);
            System.out.println("запуск задачи 1");
            Future result = service.submit(runnableTask); //запуск в отдельном потоке
            System.out.println("Ждем, пока задача закончит работать и попробуем узнать ее результат ...");
            while (!result.isDone()) {
            }
            System.out.println(result.isDone()); //при возникновении ошибки в задаче isDone() == true.
            System.out.println("==================================");
            try {
                System.out.println("Результат задачи: " + result.get()); //возникшая в потоке ошибка никак себя не проявляет, пока не запросим .get()
            } catch (Exception e) {
                System.out.println("В задаче возникла ошибка. Если появилось это сообщение - значит перехватили ее.");
                System.out.println(e.getMessage());
            }
            System.out.println("MAIN продолжает свой ход");
        }

        {
            System.out.println("6. Пример ошибки выполнения Callable-задачи");
            CallableAndRunnableTest.mainThread = Thread.currentThread();
            System.out.println(" ");
            ExecutorService service = Executors.newCachedThreadPool();
            Callable callableTask = new CallableClass<ArrayList<String>>(1, true);
            System.out.println("запуск задачи 1");
            Future result = service.submit(callableTask); //закпуск в отдельном потоке
            System.out.println("Ждем, пока задача закончит работать и попробуем узнать ее результат ...");
            while (!result.isDone()) {
            }
            System.out.println(result.isDone()); //при возникновении ошибки в задаче isDone() == true.
            System.out.println("==================================");
            try {
                System.out.println("Результат задачи: " + result.get()); //возникшая в потоке ошибка никак себя не проявляет, пока не запросим .get()
            } catch (Exception e) {
                System.out.println("В задаче возникла ошибка. Если появилось это сообщение - значит перехватили ее.");
                System.out.println(e.getMessage());
            }
            System.out.println("MAIN продолжает свой ход");
        }
        */

        {
            System.out.println("7. Пример ThreadPoll паттерна");
            CallableAndRunnableTest.mainThread = Thread.currentThread();
            System.out.println(" ");
            ExecutorService service = Executors.newFixedThreadPool(2);
            List<Callable<ArrayList<String>>> tasks = new ArrayList<Callable<ArrayList<String>>>() {{
                add(new CallableClass<ArrayList<String>>(1));
                add(new CallableClass<ArrayList<String>>(2));
                add(new CallableClass<ArrayList<String>>(3));
                add(new CallableClass<ArrayList<String>>(4));
                add(new CallableClass<ArrayList<String>>(5));
                add(new CallableClass<ArrayList<String>>(6));
            }};
            List<Future<ArrayList<String>>> results = service.invokeAll(tasks);
            System.out.println("Все задачи поставлены в очередь и выполнены");
            System.out.println();
            //
            for (Callable callable : tasks) {
                service.submit(callable);
            }
            service.shutdown(); //задачи, котрые поставлены в очередь будут выполнены полностью. После их выполнения потоки закроются.
            //без shutdown потоки будут существовать даже после завершения всех задач.
            //service.shutdownNow(); //shutdownNow даст завершиться уже запущенным задачам и закроет потоки, отбросив задачи, находящиеся в очереди
            System.out.println("Все задачи поставлены в очередь. Выполнение будет идти независимо от основного потока");
            //
            System.out.println("MAIN продолжает свой ход");
            while (! service.isTerminated()){}
        }

        System.out.println("КОНЕЦ main ");

    }

}

class CallableClass<T extends List<String>> implements Callable {
    int i;
    boolean exception;

    public CallableClass(int i) {
        this.i = i;
    }

    public CallableClass(int i, boolean exception) {
        this.i = i;
        this.exception = exception;
    }

    @Override
    public T call() throws Exception {
        System.out.println("  запустилась " + i + " " + Thread.currentThread().getName() + " в нити " + Thread.currentThread().getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println("нить main в состоянии " + CallableAndRunnableTest.mainThread.getState()); //смотрим сосотяние нити main
            try {
                Thread.sleep(200);
                if (exception) i = 1 / 0;
            } catch (InterruptedException e) {
            }
        }
        List result = new ArrayList<>();
        for (int k = i; k <= i + 2; k++) result.add(k + " " + this.getClass().getSimpleName());
        System.out.println("  закончилась " + i);
        Thread.sleep(3000);
        return (T) (result);
    }
}

class RunnableClass implements Runnable {
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
        System.out.println("  запустилась " + i + " " + Thread.currentThread().getName() + " в нити " + Thread.currentThread().getName());
        if (exception) i = 1 / 0;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("закончилась " + i);
    }
}
