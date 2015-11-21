package com.javarush.test.level04.lesson16.home03;

/* Посчитать сумму чисел
Вводить с клавиатуры числа и считать их сумму. Если пользователь ввел -1, вывести на экран сумму и завершить программу.  -1 должно учитываться в сумме.
*/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        Scanner in = new Scanner(System.in);
        int summa = 0;
        int v = 0;
        while (true){
            try {
                v = in.nextInt();
                summa+= v;
            }
            catch (InputMismatchException e){
                System.out.println("Необходимо вводить числа ... ");
            }
            in.nextLine();
            if (v == -1) {
                System.out.println(summa);
                return;
            }
        }
    }
}
