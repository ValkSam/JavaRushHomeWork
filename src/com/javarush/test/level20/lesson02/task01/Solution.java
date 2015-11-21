package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = new File("d:\\q.txt");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.assets.get(0).setPrice(3d);
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            //  1
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(somePerson.equals(ivanov));

            //  2
            ivanov.assets.clear();
            ivanov.save(outputStream);
            outputStream.flush();
            somePerson.load(inputStream);
            System.out.println(somePerson.equals(ivanov));

            //  3
            ivanov.assets.add(new Asset("dog"));
            ivanov.assets.add(null);
            ivanov.assets.get(0).setPrice(5d);
            ivanov.save(outputStream);
            outputStream.flush();

            somePerson.load(inputStream);
            System.out.println(somePerson.equals(ivanov));

            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            boolean equal = true;
            equal = equal && (((Human)obj).name.equals(this.name));
            if ((((Human)obj).assets == null) && (this.assets == null)) return equal;
            if ((((Human)obj).assets == null) || (this.assets == null)) return false;
            equal = equal && (((Human)obj).assets.size() == this.assets.size());
            for (int i = 0; i < this.assets.size(); i++){
                if ((((Human)obj).assets.get(i) == null) && (this.assets.get(i) == null)) {
                    equal = equal && true;
                }
                else if (((((Human)obj).assets.get(i) == null) || (this.assets.get(i) == null))) {
                    return false;
                }
                else {
                    equal = equal && (((Human) obj).assets.get(i).getName().equals(this.assets.get(i).getName()));
                    equal = equal && (((Human) obj).assets.get(i).getPrice() == this.assets.get(i).getPrice());
                }
            }
            return equal;
        }

        public void save(OutputStream outputStream) throws Exception {
            if ("".equals(this.name)|this.name==null) return;
            OutputStream out = outputStream;
            out.write(this.name.getBytes());
            out.write('\r');
            for (Asset a : assets){
                if (a != null) {
                    out.write(a.getName().getBytes());
                    out.write(9);
                    out.write(String.valueOf(a.getPrice()).getBytes());
                    out.write('\r');
                }
                else {
                    out.write("null".getBytes());
                    out.write(9);
                    out.write("null".getBytes());
                    out.write('\r');
                }
            }
            out.write(1);
            out.write('\r');
        }

        public void load(InputStream inputStream) throws Exception {
            InputStream in = inputStream;
            int b;
            this.name = "";
            while ((in.available() > 0) && (b = in.read()) != '\r')  {
                this.name += (char)b;
            }
            assets.clear();
            while (in.available() > 0) {
                String line = "";
                while ((in.available() > 0) && (b = in.read()) != '\r') {
                    line += (char) b;
                }
                if (line.equals(String.valueOf((char) 1))) break;
                String name = line.split(String.valueOf((char) 9))[0];
                String price = line.split(String.valueOf((char) 9))[1];
                Asset asset =null;
                if (! "null".equals(price)) {
                    asset = new Asset(name);
                    asset.setPrice(Double.valueOf(price));
                }
                this.assets.add(asset);
            }
        }
    }
}
