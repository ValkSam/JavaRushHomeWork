package myTests.algorithm.Recursion.banknotePicking;

/**
 * Created by Valk on 30.04.15.
 * Жадный алгоритм. https://ru.wikipedia.org/wiki/%D0%96%D0%B0%D0%B4%D0%BD%D1%8B%D0%B9_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC
 */

import java.util.*;
import java.lang.*;

public class Rextester {
    public static void main(String args[])
    {
        Map<Integer, Integer> originalMap = new HashMap<Integer, Integer>(){
            private static final long serialVersionUID = 1L;
            {
                put(1, 1);
                put(5, 2);
                put(7, 1);
                put(11, 2);
            }};

        Integer aimSum = 13;

        TreeMap<Integer, Integer> map = new TreeMap<>(originalMap);

        Integer summa = 0;
        for (Map.Entry<Integer, Integer> pair : getBanknoteSet(aimSum, map, new TreeMap<Integer, Integer>()).entrySet()){
            summa += pair.getKey()*pair.getValue();
            System.out.println(pair.getKey()+" - "+pair.getValue());
        }
        System.out.println(summa);



    }

    private static Map<Integer, Integer> getBanknoteSet(int restSum, TreeMap<Integer, Integer> availableSet, Map<Integer, Integer> currentResultSet){
        for (Map.Entry<Integer, Integer> pair : availableSet.descendingMap().entrySet()){ //с максимальных купюр к минимальным
            int denomination = pair.getKey(); //текущая купюра

            if (restSum == denomination) { //если текущая купюра закрывает сумму к выдаче
                addToCurrentResultSet(denomination, currentResultSet); //добавляем ее в результат
                return currentResultSet;

            } else if (restSum > denomination) { //если текущая купюра не закрывает сумму к выдаче
                TreeMap<Integer, Integer> newAvailableSet = new TreeMap<Integer, Integer> (availableSet.headMap(denomination, true));
                getNewAvailableSet(denomination, newAvailableSet); //корректируем новый набор доступных купюр (уменьшаем количество или удаляем полностью выбранный номинал)

                Map<Integer, Integer> newCurrentResultSet = new TreeMap<Integer, Integer>(currentResultSet); //полученный результат не трогаем - возможно придется к нему вернуться
                addToCurrentResultSet(denomination, newCurrentResultSet); //проект нового результата (к текущему результату добавляем текущую купюру)

                Map<Integer, Integer> result = getBanknoteSet(restSum - denomination, newAvailableSet, newCurrentResultSet); //погружаемся в новую ветку

                if (result != null) return result; //если всплыли с результатом - выходим
                //иначе - перейдем к новому номиналу

            } else if (restSum < denomination) {
                //если текущая купюра с избытком перекрывает сумму к выдаче (перебор) - переходим к следующему номиналу
            }
        }
        return null; //прошлись по всем номиналам и не нашли подходящего
    }

    private static void addToCurrentResultSet(int denomination, Map<Integer, Integer> currentResultSet){
        Integer amount = currentResultSet.get(denomination);
        amount = amount == null ? 1 : ++amount;
        currentResultSet.put(denomination,amount);
    }

    private static void getNewAvailableSet(int denomination, TreeMap<Integer, Integer> newAvailableSet) {
        Integer amount = newAvailableSet.get(denomination) - 1;
        if (amount == 0) {
            newAvailableSet.remove(denomination);
        } else {
            newAvailableSet.put(denomination, amount);
        }
    }



}
