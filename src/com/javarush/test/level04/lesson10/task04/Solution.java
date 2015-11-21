package com.javarush.test.level04.lesson10.task04;

/* S-квадрат
Вывести на экран квадрат из 10х10 букв S используя цикл while.
Буквы в одной строке не разделять.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        final int N = 10;
        int i = 1;
        while (i<=N) {
            {
                int j = 1;
                while (j <= N) {
                    System.out.print("S");
                    j++;
                }
            }
            System.out.println();
            i++;
        }

    }
}
