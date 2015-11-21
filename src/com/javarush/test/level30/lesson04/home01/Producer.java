package com.javarush.test.level30.lesson04.home01;

import java.util.concurrent.TransferQueue;

/**
 * Created by Valk on 26.05.15.
 */
public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i < 10; i++) {
            System.out.format("Элемент 'ShareItem-%s' добавлен\n", i);
            queue.offer(new ShareItem(String.format("ShareItem-%s", i), i));
            try {Thread.sleep(100);} catch (InterruptedException e){}
            if (queue.hasWaitingConsumer()) System.out.println("Consumer в ожидании!");
            if (Thread.currentThread().isInterrupted()) break;
        }

    }
}
