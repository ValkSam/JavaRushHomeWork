package com.javarush.test.level26.lesson02.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        if (array.length == 0) return array;
        Arrays.sort(array);
        final int median; //IntellijIDEA пропустит и без final, но при условии, что median нигде не будет меняться
        if (array.length % 2 == 0) {
            median = (array[array.length / 2 - 1] + array[array.length / 2]) / 2;
        } else {
            median = array[(array.length - 1) / 2];
        }
        // median++; например, такой код заставит IntellijIDEA ругаться, если не указать final
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int dist = Math.abs(o1 - median) - Math.abs(o2 - median);
                if (dist == 0) dist = o1.compareTo(o2);
                return dist;
            }
        });
        return array;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(sort(new Integer[]{1,42,3,9,12,5,89,14,75,6,43,23,34,67,55,22,95,28,17,14,67})));
    }
}
