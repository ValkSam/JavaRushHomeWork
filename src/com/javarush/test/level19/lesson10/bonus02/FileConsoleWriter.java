package com.javarush.test.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.*;


public class FileConsoleWriter extends FileWriter {
    public FileConsoleWriter(String fileName) throws IOException {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        super(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        super(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) {
        super(fd);
    }


    @Override
    public void write(int c) throws IOException {
        System.out.print((char)c);
        super.write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off ; i<off+len; i++) System.out.print(cbuf[i]);
        super.write(cbuf, off, len);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        System.out.print(str.substring(off, off + len));
        super.write(str, off, len);
    }

    public static void main(String[] args) throws Exception{

        //FileWriter fo = new FileWriter("d:\\q.txt");
        int i = 50000;
        //System.out.println(Integer.toBinaryString(i));
        //char ch = (char)i;
        //System.out.println("ch : "+ch);
        //int m = (int)ch;
        //System.out.println("m : "+m);
        //fo.write(i);
        //fo.close();


        FileConsoleWriter fo = new FileConsoleWriter("d:\\q.txt");
        //int i = 131071;
        //String str = "1234567890";
        //char[] cb = new char[] {'1','2','3','4','5','6','7','8','9','0'};
        fo.write(i);
        //fo.write(str);
        //fo.write(str,1,5);
        //fo.write(cb);
        //fo.write(cb,1,5);
        fo.close();
        System.out.println();

        FileOutputStream fo1 = new FileOutputStream("d:\\qq.txt");
        DataOutputStream foo = new DataOutputStream(fo1);
        foo.writeInt(i);
        foo.close();

        {
            FileReader fi1 = new FileReader("d:\\q.txt");
            int m = fi1.read();
            System.out.println(m);
        }

        {
            FileInputStream fi1 = new FileInputStream("d:\\q.txt");
            DataInputStream fii = new DataInputStream(fi1);
            short k = fii.readShort();
            System.out.println(k);
            System.out.println();
            fii.close();
        }
        {
            FileInputStream fi1 = new FileInputStream("d:\\qq.txt");
            DataInputStream fii = new DataInputStream(fi1);
            int j = fii.readInt();
            System.out.println(j);
            fii.close();
        }

        /*byte i4 = (byte)((i&0xFF000000)>>>24);
        byte i3 = (byte)((i&0x00FF0000)>>>16);
        byte i2 = (byte)((i&0x0000FF00)>>>8);
        byte i1 = (byte)((i&0x000000FF));
        FileOutputStream fo2 = new FileOutputStream("d:\\qqq.txt");
        fo2.write(i4);
        fo2.write(i3);
        fo2.write(i2);
        fo2.write(i1);
        fo2.close();
        */





    }
}
