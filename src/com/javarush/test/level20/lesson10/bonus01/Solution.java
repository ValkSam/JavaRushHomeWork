package com.javarush.test.level20.lesson10.bonus01;

//есть такой алгоритм:
//http://acmp.ru/article.asp?id_text=198
//


import java.util.*;
/* Алгоритмы-числа
Число S состоит из M чисел, например, S=370 и M(количество цифр)=3
Реализовать логику метода getNumbers, который должен среди натуральных чисел меньше N (long)
находить все числа, удовлетворяющие следующему критерию:
число S равно сумме его цифр, возведенных в M степень
getNumbers должен возвращать все такие числа в порядке возрастания
Пример искомого числа:
370 = 3*3*3 + 7*7*7 + 0*0*0
8208 = 8*8*8*8 + 2*2*2*2 + 0*0*0*0 + 8*8*8*8
На выполнение дается 10 секунд и 50 МБ памяти.
*/
public class Solution {
    public static int[] getNumbers(int N) {
        int count = 0;
        int[] rArr = new int[String.valueOf(N).length()];
        for (int i = 0 ; i<rArr.length ; i++) rArr[i] = (int)Math.pow(10,i+1);
        int[][] pArr = new int[10][String.valueOf(N).length()]; //массив степеней
        for (int i = 0 ; i<10 ; i++){
            for (int j = 0 ; j < pArr[i].length ; j++){
                pArr[i][j] = (int)Math.pow(i,j+1);
            }
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        String numStr;
        byte[] numArr;
        int numLen;
        int lowNum;
        int zeroCount;
        for (int i = 1 ; i<10 ; i++){ //для прохождения теста - начать с 1
            list.add(i);
        }
        for (Integer i = 10 ; i<N ; i++){
            numStr = i.toString();
            numArr = numStr.getBytes();
            lowNum = numArr[numArr.length-1]-48;
            Arrays.sort(numArr);
            numLen = numArr.length;
            long currSumm = 0;
            for (int j = numLen-1; j>=0; j--){
                //currSumm += Math.pow(numArr[j]-48, numLen);
                currSumm += pArr[numArr[j]-48][numLen-1];  //экономия 16 сек на 10^8 (9 против 22 сек)
                if (currSumm > i) break;
            }
// System.out.println(i+ " "+ currSumm+" "+(currSumm - i));
            if (currSumm == i){
                list.add(i);
                if (lowNum == 0) { //если подходящее число заканчивается на 0, то след тоже подойдет, т.к. прирост числа будет =1 и прирост суммы тоже будет = 1
                    list.add(i+1);
                }
                //в этом десятке все последующие суммы квадратов будут только больше - переходим на след десяток
                i = i + 10 - lowNum - 1; //-1 т.к. будет +1 в начале след цикла
            }
            else if (currSumm > i) {
                //в этом десятке все последующие суммы квадратов будут еще больше - переходим на след десяток/сотню и т.д.
                if (lowNum == 0) {
                    //если находимся на начале разряда (десятков/сотен/...), то все, что в рамках данного
                    // разряда до следующего уровня, сумма будет еще больше. Т.к. N-е число в от начала данного разряда
                    // будет  давать прирост числа на N единиц, а прирост суммы будет (N)^numLen
                    //zeroCount = numStr.length() - numStr.replaceAll("0+$","").length(); //кол-во нулей в конце
                    zeroCount = 0; //экономия 6 сек для 10^9
                    for (int j = numLen-1 ; j>=0 ; j--) {
                        if (numStr.charAt(j) != '0') break;
                        zeroCount++;
                    }
                }
                else {
                    zeroCount = 0;
                }
                if (zeroCount == 0){
                    i = i + (10 - lowNum) - 1; //-1 т.к. будет +1 в начале след цикла
                }
                else {
                    //int r = (int)Math.pow(10, zeroCount+1);
                    int r = rArr[zeroCount]; //выиграша по времени нет
                    i = ((int)(i/r)+1)*r - 1; //в начало следующего порядка  //-1 т.к. будет +1 в начале след цикла
                }
            }
            else { //(currSumm < i)
                //сейчас мы в начале нового десятка, т.к. если были другие варианты (== и >), то они отловлены и индекс смещен на начало десятка
                //i = i + (int)Math.exp(Math.log(-((currSumm - i)))/numLen); //смещаем индекс на целую часть корня numLen-степени
                long dif = i - currSumm; //экономия 5 сек для 10^9
                int j;
                for (j = 0 ; j<10 ; j++){
                    if (pArr[j][numLen-1] > dif) break;
                }
                i = i + (j-1);
            }
        }
        int[] result = new int[list.size()];
        Integer[] arr = list.toArray(new Integer[list.size()]);
        for (int i = 0; i< arr.length ; i++){
            result[i] = arr[i];
        }
        System.out.println(count);
        return result;
    }
    public static void main(String[] args) {
        long fm1 = Runtime.getRuntime().freeMemory();
        long t1 = (new Date()).getTime();
        //(int)Math.pow(10,7) //12sec
        //(int)Math.pow(10,8) //6sec
        //(int)Math.pow(10,9) //44sec
        //Integer.MAX_VALUE)
        System.out.println(Arrays.toString(getNumbers((int)Math.pow(10,8))));
        //System.out.println(Arrays.toString(getNumbers(1000)));
        //String numStr = String.valueOf(456700000);
/*        for (int i = 0 ; i < 63000000; i++){
            int zeroCount = 0;
            for (int k = numStr.length()-1 ; k>=0 ; k--) {
                if (numStr.charAt(k) != '0') break;
                zeroCount++;
            }
            //int zeroCount = numStr.length() - numStr.replaceAll("0+$","").length();
            //if (zeroCount<0) System.out.println(numStr);
        }
/*
        for (int i = 0 ; i < 6300000; i++){
            int ii =  (int)Math.exp(Math.log(-((30000 - i)))/8);
            if (ii<0) System.out.println(ii);
        }
*/
        long fm2 = Runtime.getRuntime().freeMemory();
        long t2 = (new Date()).getTime();
        System.out.println((fm1-fm2)/1024/1024);
        System.out.println((t2-t1)/1000);
    }
}
