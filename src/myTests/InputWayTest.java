package myTests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 http://stackoverflow.com/questions/5032356/using-scanner-nextline
*/
public class InputWayTest {
    public static void main (String[] arg)throws IOException {
        /*
        System.out.println((int)'1');               //49
        System.out.println(Integer.valueOf('1'));   //49
        //System.out.println((int)"1");             //ошибка
        System.out.println(Integer.valueOf("1"));   //1
        System.out.println((char)49); //1
        */
        //
        /*
        Scanner scanner = new Scanner(System.in);
        int intV = scanner.nextInt(); //при таком варианте, если ввести 123<Enter>, то: считается "123" и в потоке останется <Enter>
        //scanner.nextLine(); //(*1)
        String strV = scanner.nextLine(); //считается <Enter> без ожидания ввода
        System.out.println(intV); //вывод 123
        System.out.println(">"+strV+"<"); // вывод ><
        //чтобы заставить scanner.nextLine() ожидать нового ввода, надо перед ним вызвать scanner.nextLine() (*1);
        */

        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int intV1 = reader.read(); //считается число, но останется в потоке <Enter>
        // (точнее все, что после числа, например, если введено "1 2"<Enter>, то останется) " 2"<Enter>
        reader.readLine(); // без этой строки след. строка кода без ожидания считает <Enter>
        int intV2 = Integer.valueOf(reader.readLine()); //если ввести "2"
        System.out.println(intV1); //если введено "1", выведется 49 (код "1")
        System.out.println(intV2); //если введено "2", выведется 2
        */
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int intV1 = reader.read(); //вводим "123", получаем 49 (код '1') - считывается код первого char (не string). На выходе 49
        reader.readLine();
        int intV2 = (char)reader.read(); //вводим "123", получаем 49 (код '1') - считывается код первого char (не string) - 49.
        // 49 преобразовуется в char '1'. Преобразование char в int дает код char. На выходе 49
        reader.readLine();
        String strV1 = String.valueOf((char)reader.read()); //вводим "123", получаем 49 (код '1') - считывается код первого char (не string) - 49.
        // 49 преобразовуется в char '1'. Преобразование char в string дает char в формате строки. На выходе "1"
        System.out.println(intV1);
        System.out.println(intV2);
        System.out.println(strV1);
        */
        //*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] bufC = new char[4];
        int intV1 = reader.read(bufC); //считывает коды вводимых символов из потока в количестве размера массива
        System.out.println(intV1); //4
        for (int i = 0; i < bufC.length; i++) {
            System.out.println(bufC[i]); //если ввести "123456" будет выведено 1234
        }
        //*/
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream fileOut = new FileOutputStream(reader.readLine());
        StringBuilder text = new StringBuilder();
        //String eol = "";
        while (true){
            String str = reader.readLine();
            text.append(str);
            text.append("\r\n");
            if ("exit".equals(str)) break;
        }
        fileOut.write (text.toString().getBytes());
        fileOut.close();
        */

    }
}
