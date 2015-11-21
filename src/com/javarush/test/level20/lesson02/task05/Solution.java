package com.javarush.test.level20.lesson02.task05;

import java.io.*;

/* И еще раз о синхронизации
Реализуйте логику записи в файл и чтения из файла для класса Object
Метод load должен инициализировать объект данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            OutputStream outputStream = new FileOutputStream("d:\\q.txt");
            InputStream inputStream = new FileInputStream("d:\\q.txt");

            Object object = new Object();
            object.string1 = new String();   //string #1
            object.string2 = new String();   //string #2
            object.save(outputStream);
            outputStream.flush();

            Object loadedObject = new Object();
            loadedObject.string1 = new String(); //string #3
            loadedObject.string2 = new String(); //string #4


            loadedObject.load(inputStream);
            //check here that the object variable equals to loadedObject - проверьте тут, что object и loadedObject равны
            if (loadedObject.string1 != null) loadedObject.string1.print();
            if (loadedObject.string2 != null) loadedObject.string2.print();
            System.out.println(object.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Object {
        public String string1;
        public String string2;

        public void save(OutputStream outputStream) throws Exception {
            writeField(string1, outputStream);
            writeField(string2, outputStream);
        }

        private void writeField(String obj, OutputStream out) throws Exception{
            if (obj == null){
                out.write(0);
                out.write("\\r\\n".getBytes());
                return;
            }
            PrintStream o = System.out;
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            PrintStream newo = new PrintStream(buf);
            System.setOut(newo);
            obj.print(); //в конце строки '\n', а не '\r\n' (если выполнял в rextester)
            System.setOut(o);
            out.write(buf.toString().replaceAll("string #","").getBytes());
        }

        public void load(InputStream inputStream) throws Exception {
            synchronized (Solution.class) {
                int origCountStrings = countStrings;
                StringBuilder s;
                s = readField(inputStream);
                if (s != null) {
                    countStrings = Integer.valueOf(s.toString())-1;
                    this.string1 = new String();
                } else {
                    this.string1 = null;
                }
                s = readField(inputStream);
                if (s != null) {
                    countStrings = Integer.valueOf(s.toString())-1;
                    this.string2 = new String();
                } else {
                    this.string2 = null;
                }
                countStrings = origCountStrings;
            }
        }

        private StringBuilder readField(InputStream in) throws Exception{
            StringBuilder str = readStr(in);
            if (str.toString().charAt(0) == (char)0) {
                str = null;
            }
            return str;
        }

        private StringBuilder readStr(InputStream in) throws Exception {
            StringBuilder str = new StringBuilder();
            int b = 0;
            while ((b = in.read()) != -1) {
                if ((b == '\r') || (b == '\n')) break;
                str.append((char) b);
            }
            if (b == '\r') in.read(); //на случай если строка закончилась '\r\n', а не '\n' - вычищаем '\n'
            return str;
        }

        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;


            boolean equal = true;

            PrintStream o = System.out;
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            PrintStream newo = new PrintStream(buf);
            System.setOut(newo);

            StringBuilder obj1 = new StringBuilder();
            StringBuilder obj2 = new StringBuilder();
            StringBuilder this1 = new StringBuilder();
            StringBuilder this2 = new StringBuilder();


            if ((obj.string1 == null) && (this.string1 == null)) {
                obj1.append("");
                this1.append("");
            }
            else if ((obj.string1 == null) || (this.string1 == null)) {
                System.setOut(o);
                return false;
            }
            else {
                obj.string1.print();
                obj1.append(buf.toString());
                buf.reset();
                this.string1.print();
                this1.append(buf.toString());
                buf.reset();
            }

            if ((obj.string2 == null) && (this.string2 == null)) {
                obj2.append("");
                this2.append("");
            }
            else if ((obj.string2 == null) || (this.string2 == null)) {
                System.setOut(o);
                return false;
            }
            else {
                obj.string2.print();
                obj2.append(buf.toString());
                buf.reset();
                this.string2.print();
                this2.append(buf.toString());
                buf.reset();
            }

            System.setOut(o);

            equal = equal && (obj1.toString().equals(this1.toString()));
            equal = equal && (obj2.toString().equals(this2.toString()));

            return equal;
        }
    }

    public static int countStrings;

    public static class String {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }
}
