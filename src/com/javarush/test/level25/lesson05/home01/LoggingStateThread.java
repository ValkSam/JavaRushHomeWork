package com.javarush.test.level25.lesson05.home01;

/**
 * Created by Valk on 06.04.15.
 */
public class LoggingStateThread extends Thread {
    private Thread.State lastState = null;
    private Thread target;

    public LoggingStateThread(Thread target) {
        super();
        this.target = target;
        setDaemon(true);
        lastState = target.getState();
        System.out.println(lastState); //NEW : target еще не запустился, т.к. контсруктор LoggingStateThread
        // еще не отработал, а значит мы все еще в главном потоке и до target.start(); еще не дошли
    }

    @Override
    public void run() {
        while (lastState != State.TERMINATED) {
            Thread.State currState = target.getState();
            if (currState != lastState) {
                lastState = currState;
                System.out.println(currState);
                if (State.WAITING.equals(currState)) {
                    synchronized (target) {
                        target.notify();
                    }
                }
            }
        }
    }
}
