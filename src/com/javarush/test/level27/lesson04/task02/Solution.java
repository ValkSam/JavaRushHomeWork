package com.javarush.test.level27.lesson04.task02;

/* Второй вариант дедлока
В методе secondMethod в синхронизированных блоках расставьте локи так,
чтобы при использовании класса Solution нитями образовывался дедлок
*/
public class Solution {
    private final Object lock = new Object();

    public synchronized void firstMethod() {
        try {Thread.sleep(100);} catch (InterruptedException e){} //этот sleep провоцирует deadLock
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock) {
            synchronized (this) {
                doSomething();
            }
        }
    }

    private void doSomething() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    solution.firstMethod();
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    solution.secondMethod();
                }
            }
        }).start();
    }
}