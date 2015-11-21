package com.javarush.test.level26.lesson15.big01;



import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Valk on 26.04.15.
 */
public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        int sum = 0;
        for (Map.Entry<Integer, Integer> pair : denominations.entrySet()) sum += pair.getKey()*pair.getValue();
        return sum;
    }

    public boolean hasMoney(){
        return (denominations.size() != 0)&&(getTotalAmount() != 0);
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException, ConcurrentModificationException {
        Map<Integer, Integer> result;
        result = getBanknoteSet(expectedAmount, new TreeMap<Integer, Integer>(denominations), new TreeMap<Integer, Integer>());
        if (result == null) throw new NotEnoughMoneyException();
        for (Map.Entry<Integer, Integer> pair : result.entrySet()){
            int denomination = pair.getKey();
            int newAmount = denominations.get(denomination)-pair.getValue();
            denominations.put(denomination, newAmount);
        }
        return result;
    }

    private Map<Integer, Integer> getBanknoteSet(int restSum, TreeMap<Integer, Integer> availableSet, Map<Integer, Integer> currentResultSet) throws ConcurrentModificationException{
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

    private void addToCurrentResultSet(int denomination, Map<Integer, Integer> currentResultSet){
        Integer amount = currentResultSet.get(denomination);
        amount = amount == null ? 1 : ++amount;
        currentResultSet.put(denomination,amount);
    }

    private void getNewAvailableSet(int denomination, TreeMap<Integer, Integer> newAvailableSet) {
        Integer amount = newAvailableSet.get(denomination) - 1;
        if (amount == 0) {
            newAvailableSet.remove(denomination);
        } else {
            newAvailableSet.put(denomination, amount);
        }
    }

}
