package myTests.algorithm.Recursion.bestProfitList;

import java.util.*;

/**
 * Created by Valk on 06.05.15.
 * package com.javarush.test.level27.lesson15.big01.ad; - рабочий алгоритм с исправлениями
 */
public class Rextester
{
    public static void main(String args[])
    {
        //List<Integer> list = new ArrayList<>();
        int aimSumm;


        aimSumm = 10; //9;
        List<Record> list = new ArrayList<>();
        list.add(new Record(6, 5));
        list.add(new Record(5, 4));
        list.add(new Record(4, 3));
        list.add(new Record(1, 3));


        /*
        aimSumm = 3; //9;
        List<Record> list = new ArrayList<>();
        list.add(new Record(6, 5));
        list.add(new Record(5, 4));
        list.add(new Record(4, 2));
        list.add(new Record(2, 1));
        */

        List<Record> resultList = new ArrayList<>();
        System.out.println(getMaxProfitList(aimSumm, list, resultList));
        System.out.println(resultList);
    }


    private static Record getMaxProfitList(int restLimit, List<Record> restList, List<Record> resultList){
        Collections.sort(restList, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return Integer.compare(o2.profit, o1.profit);
            }
        });

        Record bestResult = null;
        int maxProfit = Integer.MIN_VALUE; //максимальная прибыль
        int maxProfitIdx = -1; //индекс элемента, который обеспечивает максимальную прибыль
        int genTime = 0; //общее время по отобранным элементам

        for (int i = 0; i<restList.size(); i++){
            Record currValue = restList.get(i);

            if (restLimit - currValue.time >= 0) {//если взятие  текущего елемента, не превысит лимит времени , то обрабатываем его ...

                if (currValue.profit > maxProfit) {
                    maxProfit = currValue.profit; //инициируем maxProfit
                    maxProfitIdx = i;
                    bestResult = new Record(maxProfit, currValue.time); //кандидат на лучший результат в текущем переборе
                }

                List<Record> newRestList = new ArrayList<>(restList.subList(i+1, restList.size())); //погружаться будем в список с оставшимися элементами
                //(изначально элементы упорядочены убыванию поля profit)

                List<Record> tmpResultList = new ArrayList<>(); //список-кандидат
                Record result = null;
                if (restLimit - currValue.time != 0) { //если лимит исчерпан - нет смысла нырять
                    result = getMaxProfitList(restLimit - currValue.time, newRestList, tmpResultList);
                }

                if ((result != null)&&(currValue.profit+result.profit > maxProfit)) { //если текущий элемент вместе с другими собранными в рамках лимита элементами дает лучшую прибыль ...
                    maxProfit = currValue.profit+result.profit; //... запоминаем новое максимальное значение прибыли
                    maxProfitIdx = i;    //... запоминаем индекс элемента, который на данный момент дал лучший результат
                    genTime = currValue.time+result.time; //... запоминаем суммарное время
                    resultList.clear();  //... собранный при погружении список определяем как результат, который будет выдаваться наверх
                    resultList.addAll(tmpResultList);

                    bestResult = new Record(maxProfit, genTime);
                }
            }
        }
        if (bestResult != null) {
            resultList.add(0, restList.get(maxProfitIdx));
        }
        return bestResult;
    }

    private static class Record{
        private int profit;
        private int time;
        public Record(int profit, int time){
            this.profit = profit;
            this.time = time;
        }
        public String toString(){
            return profit+" ("+time+")";
        }
    }
}
