package myTests.ExecutorsTest;

import java.util.concurrent.*;

/**
 * Created by Valk on 08.04.15.
 * Тестирование CachedThreadPool
 * (см. после Тестирование FixedThreadPool)
 * - создаем пул нитей (сколько их будет - не известно. Должно быть столько, сколько необходимо для выполнения всех невыполненных задач в очереди
 * - ставим в очередь 10 задач. Ставим с интервалом, так чтобы пока ставим, некоторые, ранее поставленные, успели завершиться:
 *          видим, что, если для новой задачи не хватает нити - нить создается.
 *          Если для выполнения новой задачи можно использовать уже освободившуюся нить - будет использована освободившаяся нить
 *          В итоге для выполнения 10 задач, понадобилось 4 нити
 */
public class CachedThreadPoolTest implements Runnable {
    int i;
    public CachedThreadPoolTest(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("  запустилась "+i+" "+Thread.currentThread().getName());
        try {Thread.sleep(2000);} catch (InterruptedException e){}
        System.out.println("  закончилась "+i);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool(); //Будет создано столько потоков, сколько надо для работы задач
        for (int i = 0; i < 10; i++){
            try {Thread.sleep(1000);} catch (InterruptedException e){}
            System.out.println("ставим в очередь "+i);
            service.submit(new ExecutorsTest(i)); //в очередь ставим с интервалом, чтобы по ходу постановки в очередь,
                                                  // некоторые, ранее добавленные, успели завершиться
        }
        System.out.println("все поставлены в очереди"); //в очередь ставятся все, не дожидаясь отработки друг друга
        System.out.println();

        service.shutdown(); //указание закончиться, после завершения всех задач (но не тормозим main - main идет своим ходом)
        //если не вызывать shutdown,  то service никогда не закончится

        System.out.println("КОНЕЦ main "); //main закончил, но service еще живет - ждет завершения всех задач
    }
}
