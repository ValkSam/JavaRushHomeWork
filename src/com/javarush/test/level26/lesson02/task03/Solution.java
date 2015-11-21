package com.javarush.test.level26.lesson02.task03;

import java.util.ArrayList;
import java.util.Comparator;

/* Убежденному убеждать других не трудно.
В таблице есть колонки, по которым можно сортировать.
Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
Напишите public static компаратор CustomizedComparator, который будет:
1. в конструкторе принимать список компараторов
2. сортировать данные в порядке, соответствующем последовательности компараторов.
Все переданные компараторы сортируют дженерик тип Т
В конструктор передается как минимум один компаратор
*/
public class Solution {
    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;
        public CustomizedComparator (Comparator<T> ... comparators) throws RuntimeException {
            if (comparators.length == 0) throw new RuntimeException("Не указан ни один компаратор");
            this.comparators = comparators;
        }
        @Override
        public int compare(T o1, T o2) {
            for (Comparator comp : comparators){
                int result = comp.compare(o1, o2);
                if (result != 0) return result;
            }
            return 0;
        }
    }

    static class Record {
        String fam;
        String name;
        int age;
        int height;
        int gender;

        @Override
        public String toString() {
            return "\nRecord{" +
                    "fam='" + fam + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    ", gender=" + gender +
                    "}";
        }
    }



    public static void main(String[] args) {
        Comparator<Record> byFam = new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.fam.compareTo(o2.fam);
            }
        };
        Comparator<Record> byName = new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.name.compareTo(o2.name);
            }
        };
        Comparator<Record> byHeight = new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.height-o2.height;
            }
        };

        ArrayList<Record> records = new ArrayList<Record>(){{
            add(new Record(){{
                fam = "Ivanov";
                name = "Petr";
                age = 20;
                height = 100;
            }});
            add(new Record(){{
                fam = "Ivanov";
                name = "Ivan";
                age = 10;
                height = 110;
            }});
            add(new Record(){{
                fam = "Ivanov";
                name = "Ivan";
                age = 30;
                height = 90;
            }});
            add(new Record(){{
                fam = "Petrov";
                name = "Petr";
                age = 40;
                height = 100;
            }});
            add(new Record(){{
                fam = "Petrov";
                name = "Ivan";
                age = 50;
                height = 120;
            }});
            add(new Record(){{
                fam = "Antonov";
                name = "Ivan";
                age = 60;
                height = 120;
            }});
        }};

        //records.sort(new CustomizedComparator<Record>(byFam, byName, byHeight));
        records.sort(new CustomizedComparator<Record>());
        System.out.println(records);
    }

}
