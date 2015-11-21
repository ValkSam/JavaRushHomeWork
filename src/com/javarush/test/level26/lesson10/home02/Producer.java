package com.javarush.test.level26.lesson10.home02;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Valk on 25.04.15.
 */
public class Producer implements Runnable{
    protected ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }


    @Override
    public void run() {
        try {
            for (int i=1;;i++){
                map.put(String.valueOf(i), String.format("Some text for %s",i));
                Thread.sleep(500);
            }
        }
        catch (InterruptedException e){
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
