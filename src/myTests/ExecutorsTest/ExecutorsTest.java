package myTests.ExecutorsTest;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Valk on 07.04.15.
 * Тестирование FixedThreadPool
 * - создаем пул из двух нитей
 * - ставим в очередь 10 задач:
 *          видим, что в очередь ставятся сразу все задачи, т.е. размер пула и занятость потоков на собственно постановку в очередь не влияет
 *          выполнение первых задач начинается сразу после попадания в очередь, остальных - по мере освобождения нитей
 * - вызываем service.shutdown(), чтобы закрыть очередь и убить нити, после выполнения всех задач.
 *          если не вызвать service.shutdown(), то нити будут существовать и после завершения всех задач и после завершения нити main
 * - ставим нить main на паузу на заданное время или до завершения всех задач в очереди
 * - после окончания паузы пробуем добавить в очередь еще одну задачу
 *          видим, что нельзя добавить, т.к. очередь закрыта (хоть и продолжает работать) (service.shutdown())
 * - принудительно прерываем выполение очереди задач (уже выполняющиеся задач - свою работу закончат) - service.shutdownNow()
 * - смотрим список задач из очереди, которые так и не попали на выполнение
 */
public class ExecutorsTest implements Runnable {
    int i;
    public ExecutorsTest(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("  запустилась "+i+" "+Thread.currentThread().getName());
        try {Thread.sleep(3000);} catch (InterruptedException e){}
        System.out.println("  закончилась "+i);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2); //только 2 потока могут работать одновременно
        for (int i = 0; i < 10; i++){
            System.out.println("ставим в очередь " + i);
            service.submit(new ExecutorsTest(i)); ////в очередь ставятся все, не дожидаясь отработки друг друга
            try {Thread.sleep(10);} catch (InterruptedException e){}
        }
        System.out.println("все поставлены в очереди"); //в очередь ставятся все, не дожидаясь отработки друг друга
        System.out.println();

        service.shutdown(); //команда пулу закончиться, после завершения всех задач (но не тормозим main - main идет своим ходом)
                            //если не вызывать shutdown,  то service никогда не закончится

        int sec = 6;
        System.out.println();
        System.out.println("main стоит " + sec + " секунд ... ");

        service.awaitTermination(sec, TimeUnit.SECONDS); //указание для main ждать пока: не закончатся все задачи service или не пройдет sec секунд

        System.out.println("... main идет дальше");
        System.out.println();
        try {
            service.submit(new ExecutorsTest(11));
        } catch (Exception e) {
            System.out.println("нельзя добавлять после shutdown()");
            System.out.println();
        }

        System.out.println("Тут грубо прерываем. Кто уже работает (2 задачи) - доработают, кто еще не запускался - уже не запустятся");
        System.out.println("эти уже не запустятся:");
        for (Runnable task : service.shutdownNow()){
            System.out.println(task+" не запустится");
        }
        System.out.println();

        System.out.println("КОНЕЦ main "); //main закончил, но service еще живет - ждет завершения всех задач
    }
}
