package com.javarush.test.level25.lesson11.task02;

/* Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
Обеспечьте переуступку кванта времени для последовательных выводов текста в консоль
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        new Thread(new YieldRunnable(1)).start();
        new Thread(new YieldRunnable(2)).start();
        new Thread(new YieldRunnable(3)).start();
        new Thread(new YieldRunnable(4)).start();
        new Thread(new YieldRunnable(5)).start();
        new Thread(new YieldRunnable(6)).start();
    }
}
