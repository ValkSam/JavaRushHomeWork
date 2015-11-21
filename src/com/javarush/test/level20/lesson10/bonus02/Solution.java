package com.javarush.test.level20.lesson10.bonus02;

import java.util.Arrays;

/* Алгоритмы-прямоугольники
1. Дан двумерный массив N*N, который содержит несколько прямоугольников.
2. Различные прямоугольники не соприкасаются и не накладываются.
3. Внутри прямоугольник весь заполнен 1.
4. В массиве:
4.1) a[i, j] = 1, если элемент (i, j) принадлежит какому-либо прямоугольнику
4.2) a[i, j] = 0, в противном случае
5. getRectangleCount должен возвращать количество прямоугольников.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {0, 1, 1, 0, 1, 1},
                {0, 1, 1, 0, 1, 1},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 1},
                {1, 1, 0, 0, 0, 1},
                {1, 1, 0, 1, 0, 1},
        };


        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int result = 0;
        int N = a[0].length;
        for (int i = 0 ; i < N; i++){
            for (int j = 0; j < N; j++) {
                if (a[i][j] == 1) {
                    markRect(a, i, j);
                    result++;
                }
            }
        }

        /*
        for (int i = 0 ; i < N; i++){
                    System.out.println(Arrays.toString(a[i]));
            }
        */

        return result;
    }
    private static void markRect (byte[][] a, int m, int n){
        int N = a[0].length;
        for (int i = m ; i < N; i++){
            for (int j = n; j < N; j++) {
                if (a[i][j] == 0){
                    break;
                }
                a[i][j] = 2;
            }
            if (a[i][n] == 0){
                break;
            }
        }
    }
}
