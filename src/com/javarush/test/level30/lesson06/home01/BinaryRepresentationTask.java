package com.javarush.test.level30.lesson06.home01;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Valk on 26.05.15.
 */
public class BinaryRepresentationTask extends RecursiveTask {
    private int value;

    public BinaryRepresentationTask(int value) {
        this.value = value;
    }

    @Override
    protected String compute() {
        int a = value % 2;
        int b = value / 2;
        String result = String.valueOf(a);
        if (b > 0) {
            return new BinaryRepresentationTask(b).fork().join() + result;
        }
        return result;
    }
}
