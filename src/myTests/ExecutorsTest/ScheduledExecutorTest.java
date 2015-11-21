package myTests.ExecutorsTest;

import java.util.concurrent.*;

/**
 * Created by Valk on 07.04.15.
 */
public class ScheduledExecutorTest implements Runnable{
    int i;
    int workTime;

    public ScheduledExecutorTest(int i, int workTime) {
        this.i = i;
        this.workTime = workTime;
    }

    public void run() {
        System.out.println("запустился "+i+" "+Thread.currentThread().getName());
        try {Thread.sleep(workTime);} catch (InterruptedException e){}
        System.out.println("закончил "+i);
    }

    public static void main(String[] args) throws InterruptedException {
        {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
            for (int i = 1; i <= 5; i++) {
                System.out.println("ставим в очередь " + i);
                service.schedule(new ScheduledExecutorTest(i, 1000), i * 3, TimeUnit.SECONDS); ////в очередь ставятся все, не дожидаясь отработки друг друга
            }
            System.out.println("все поставлены в очереди"); //в очередь ставятся все, не дожидаясь отработки друг друга
            System.out.println();
            System.out.println("Наблюдаем как запускаются с интервалом >=3 сек. Время выполнения каждой - 1 сек: ");
            service.shutdown();
            service.awaitTermination(600, TimeUnit.SECONDS); //ждем. Пставили 600 секунд, но дождемся раньше - когда отработают задачи
        }
        System.out.println();
        System.out.println("А теперь смотрим как ведет, если время интервал запуска задач меньше, чем их выполнение. " +
                "\nПри этом кол-во одновременно выполняющихся задач только 2 ");
        System.out.println();
        {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
            for (int i = 1; i <= 5; i++) {
                System.out.println("ставим в очередь " + i);
                service.schedule(new ScheduledExecutorTest(i, 3000), 5, TimeUnit.SECONDS); ////в очередь ставятся все, не дожидаясь отработки друг друга
                //время запуска - через 5 секунд пратически одновременно. Но место хватит только 2
                //те, кому места не хватило - стоят в очереди и запустятся СРАЗУ, как только появится место
            }
            System.out.println("все поставлены в очереди"); //в очередь ставятся все, не дожидаясь отработки друг друга
            System.out.println();
            System.out.println("Наблюдаем как через 5 секунд запускаются все сразу (но потока только 2). Время выполнения каждой - 3 сек: ");
            service.shutdown();
            service.awaitTermination(600, TimeUnit.SECONDS); //ждем. Поставили 600 секунд, но дождемся раньше - когда отработают задачи
        }
        System.out.println("КОНЕЦ main "); //main закончил
    }

}
