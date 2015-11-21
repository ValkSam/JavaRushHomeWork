package com.javarush.test.level26.lesson02.task02;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/* Был бы ум - будет и успех.
Солдаты всегда строились, строятся и будут строиться по убыванию роста.
Отсортируйте солдат, предварительно подумав, что лучше использовать при таких условиях - Comparable или Comparator.
*/
public class Solution {
    public static void main(String[] args) {
        /* для варианта , когда class Soldier БЕЗ implements Comparable
        Set<Soldier> soldiers = new TreeSet<>(new Comparator<Soldier>() {
            @Override
            public int compare(Soldier o1, Soldier o2) {
                int rang = o2.height-o1.height;
                if (rang==0) rang = rang + o1.name.compareTo(o2.name);
                return rang;
            }
        });*/
        Set<Soldier> soldiers = new TreeSet<>();
        soldiers.add(new Soldier("Ivanov", 170));
        soldiers.add(new Soldier("Petrov", 180));
        soldiers.add(new Soldier("Petrova", 180));
        soldiers.add(new Soldier("Petrovich", 180));
        soldiers.add(new Soldier("Sidorov", 175));

        for (Soldier soldier : soldiers) {
            System.out.println(soldier.name);
        }
    }

    //public static class Soldier{ //если без implements Comparable<Soldier>, то в  TreeSet надо передать Comparator
    public static class Soldier implements Comparable<Soldier>{
        private String name;
        private int height;

        public Soldier(String name, int height) {
            this.name = name;
            this.height = height;
        }


        public int compareTo(Soldier o) {
            int rang = o.height-this.height;
            if (rang==0) rang = rang + this.name.compareTo(o.name);
            return rang;
        }
    }
}
