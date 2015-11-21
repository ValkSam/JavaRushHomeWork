package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
public class Solution {
    public static void main(String[] args) throws Exception {
        short a = 49;
        int shift = 1;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a>>>shift));
        System.out.println(Integer.toBinaryString((byte)(a<<(16-shift))));
        a = (short)((a>>>shift)|(a<<(16-shift)));
        System.out.println(Integer.toBinaryString(a));
        /*
        args = "-e d:\\q.txt d:\\qq.txt".split(" ");
        for (int i = 1; i <= 2; i++) {
            //FileReader fi = new FileReader(args[1]); //читаем символы (эквивалент short)
            //FileWriter fo = new FileWriter(args[2]);
            FileInputStream fi = new FileInputStream(args[1]); //читаем байтами
            FileOutputStream fo = new FileOutputStream(args[2]);
            if ("-e".equals(args[0])) {
                //while (fi.ready()) {
                while (fi.available() > 0) {
                    fo.write(crypt(fi.read()));
                }
            } else if ("-d".equals(args[0])) {
                //while (fi.ready()) {
                while (fi.available() > 0) {
                    fo.write(unCrypt(fi.read()));
                }
            }
            fi.close();
            fo.close();
            args = "-d d:\\qq.txt d:\\q.txt".split(" ");
        }
        */

    }
    private static int crypt(int s){
        //return shortRotateRight(s, 15);
        return byteRotateRight(s, 1);
    }
    private static int unCrypt(Integer s){
        return byteRotateLeft(s, 1);
    }

    //a = a>>(shift) | ((a << (32 - shift)) & ~((-1)>>shift)));// цикло сдвиг вправо на 8 бит

    private static int shortRotateRight (int s, int n){
        s = Integer.rotateRight(s,n);                           //10000000 00000000 00000000 00011000   s // "1" ушла в старший разряд int
        s = (int) ((short)s|(s>>>16));                          //                  00000000 00011000   (short) s //обрезаем до short, но теряем "1" в старшем разряде формата int
                                                                //OR
                                                                //                  10000000 00000000   (s>>>16) //вытягиваем "1" в старший разряд short
                                                                //                  10000000 00011000   //получили перенесенную "1" в формате short
                                                                //00000000 00000000 10000000 00011000   s
        return s;
    }
    private static int shortRotateLeft (int s, int n){
        s = Integer.rotateLeft(s, n);                           //00000000 00000001 00000000 00110000   s //"1" ушла из формата short. Надо ее вернуть в младший разряд
        s = (int) ((short)(s>>>16))|(short)s;                   //                  00000000 00110000   (short) s //обрезаем до short, но теряем "1"
                                                                //OR
                                                                //                  00000000 00000001   (short)(s>>>16) // вернули "1" в младший разряд
                                                                //00000000 00000000 00000000 00110001   s
        return s;
    }
    private static int byteRotateRight (int s, int n){
        s = Integer.rotateRight(s,n);
        s = (int) ((byte)s|(s>>>24));
        return s;
    }
    private static int byteRotateLeft (int s, int n){
        s = Integer.rotateLeft(s, n);
        s = (int) ((byte)(s>>>8))|(byte)s;
        return s;
    }


}
