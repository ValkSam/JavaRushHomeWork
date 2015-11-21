package com.javarush.test.level35.lesson04.task01;

import java.util.ArrayList;
import java.util.List;

/* Знакомство с дженериками
Параметризируйте классы SomeClass и Solution следующим образом:
        1. SomeClass должен работать с типами, которые наследуются от Number;
        2. Solution должен работать с типами, которые наследуются от List, который в свою очередь параметризируется типом SomeClass.
*/
public class Solution<T extends List<Solution.SomeClass>> {
    private T list;
    public Solution(T list) {
        this.list = list;
    }

    public T getList() {
        return list;
    }

    public static class SomeClass<T extends Number> {
        private T value;
        public SomeClass(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        List<SomeClass> list = new ArrayList<>();
        Integer i = 100;
        list.add(new SomeClass<>(i));
        Long l = 200L;
        list.add(new SomeClass<>(l));
        Double f = 300.0;
        list.add(new SomeClass<>(f));
        byte b = (byte)127;
        list.add(new SomeClass<>(b));
        Solution<List<SomeClass>> solution = new Solution<>(list);
        System.out.println(solution.getList().get(0).getValue());
        System.out.println(solution.getList().get(1).getValue());
        System.out.println(solution.getList().get(2).getValue());
        System.out.println(solution.getList().get(3).getValue());
    }
}
