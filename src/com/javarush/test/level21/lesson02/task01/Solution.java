package com.javarush.test.level21.lesson02.task01;

/* Определяем адрес сети
1) Даны IP-адрес и маска подсети, необходимо вычислить адрес сети - метод getNetAddress.
Используйте операцию поразрядной конъюнкции (логическое И).
Пример:
IP-адрес:       11000000 10101000 00000001 00000010 (192.168.1.2)
Маска подсети:  11111111 11111111 11111110 00000000 (255.255.254.0)
Адрес сети:     11000000 10101000 00000000 00000000 (192.168.0.0)
2) Реализовать метод print, который выведет в консоль данные в двоичном коде
3) Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[4];
        for (int i = 0 ; i<ip.length; i++){
            result[i] = (byte)(ip[i]&mask[i]);
        }
        return result;
    }

    public static void print(byte[] bytes) {
        System.out.print(String.format("%8s",(Integer.toBinaryString(bytes[0]&0xFF))).replaceAll(" ","0")+" ");
        System.out.print(String.format("%8s",(Integer.toBinaryString(bytes[1]&0xFF))).replaceAll(" ","0")+" ");
        System.out.print(String.format("%8s",(Integer.toBinaryString(bytes[2]&0xFF))).replaceAll(" ","0")+" ");
        System.out.println(String.format("%8s",(Integer.toBinaryString(bytes[3]&0xFF))).replaceAll(" ","0")+" ");
    }
}
