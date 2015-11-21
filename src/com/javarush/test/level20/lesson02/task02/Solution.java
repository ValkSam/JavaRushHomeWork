package com.javarush.test.level20.lesson02.task02;
/*
более полный текст в
http://rextester.com/edit/GAI68401
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            OutputStream outputStream = new FileOutputStream("d:\\q.txt");
            InputStream inputStream = new FileInputStream("d:\\q.txt");

            //1
            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setFirstName("Иван");
            user.setLastName("Ivanov");
            user.setBirthDate(new Date());
            user.setMale(true);
            user.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user);

            user = new User();
            user.setFirstName("Петр");
            user.setLastName("Petrov");
            user.setBirthDate(new Date(new Date().getTime()+500000000));
            user.setMale(true);
            user.setCountry(User.Country.RUSSIA);
            javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();
            //2

            JavaRush javaRush1 = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            user = new User();
            user.setFirstName("Иван 1");
            user.setLastName("Ivanov 1");
            user.setBirthDate(new Date());
            user.setMale(false);
            user.setCountry(User.Country.UKRAINE);
            javaRush1.users.add(user);

            user = new User();
            user.setFirstName("Петр 1");
            user.setLastName("Petrov 1");
            user.setBirthDate(new Date(new Date().getTime()+1000000000));
            user.setMale(false);
            user.setCountry(User.Country.OTHER);
            javaRush1.users.add(user);

            javaRush1.save(outputStream);
            outputStream.flush();

            //1
            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            System.out.println(loadedObject.users.size());
            for (User u : loadedObject.users){
                System.out.print(u.getFirstName());
                System.out.print(" ");
                System.out.print(u.getLastName());
                System.out.print(" ");
                System.out.print(u.getBirthDate());
                System.out.print(" ");
                System.out.print(u.isMale());
                System.out.print(" ");
                System.out.print(u.getCountry().getDisplayedName());
                System.out.println();
            }

            System.out.println("==================");
            System.out.println(javaRush.equals(loadedObject));
            System.out.println();

            //2
            loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            System.out.println(loadedObject.users.size());

            for (User u : loadedObject.users){
                System.out.print(u.getFirstName());
                System.out.print(" ");
                System.out.print(u.getLastName());
                System.out.print(" ");
                System.out.print(u.getBirthDate());
                System.out.print(" ");
                System.out.print(u.isMale());
                System.out.print(" ");
                System.out.print(u.getCountry().getDisplayedName());
                System.out.println();
            }

            System.out.println("==================");
            System.out.println(javaRush1.equals(loadedObject));
            System.out.println();


            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            writeField(users, outputStream);
            outputStream.write('\r');
        }

        public void load(InputStream inputStream) throws Exception {
            if (inputStream.available() == 0) return;
            this.users = readField(this.users, inputStream);
        }

        private List<User> readField(List<User> list, InputStream in) throws Exception{
            list.clear();
            String line = readStr(in);
            if ((line.length()==1)&&(line.charAt(0) == (char)0)) {
                return null;
            }
            while ((! line.equals("")) && (line.charAt(0) != (char)1)) {
                String[] strA = line.split(String.valueOf((char) 9));
                User user = null;
                if (! (((char)0)+"").equals(line)){
                    user = new User();
                    user.setFirstName(strA[0]);
                    user.setLastName(strA[1]);
                    user.setBirthDate(new Date(Long.valueOf(strA[2])));
                    user.setMale(strA[3].charAt(0) == (char)3);
                    if ("Ukraine".equals(strA[4])) {
                        user.setCountry(User.Country.UKRAINE);
                    }
                    else if ("Russia".equals(strA[4])) {
                        user.setCountry(User.Country.RUSSIA);
                    }
                    else {
                        user.setCountry(User.Country.OTHER);
                    }
                }
                list.add(user);
                line = readStr(in);
            }
            return list;
        }

        private String readStr(InputStream in) throws Exception {
            StringBuilder str = new StringBuilder();
            int b;
            while ((in.available() > 0) && (b = in.read()) != '\r')  {
                str.append((char)b);
            }
            return decodeToNative(str.toString());
        }


        private void writeField(Object obj, OutputStream out) throws Exception{
            if (obj == null){
                out.write(0);
            }
            if (obj instanceof String){
                out.write(encodeToASCII((String)obj).getBytes());
            }
            if (obj instanceof Date){
                out.write(String.valueOf(((Date)obj).getTime()).getBytes());
            }
            if (obj instanceof User.Country){
                out.write(((User.Country)obj).getDisplayedName().getBytes());
            }
            if (obj instanceof ArrayList){
                if (((ArrayList)obj).size() != 0){
                    if (((ArrayList)obj).get(0) instanceof User){
                        for (User a : users){
                            if (a != null) {
                                writeField(a.getFirstName(), out);
                                out.write(9);
                                writeField(a.getLastName(), out);
                                out.write(9);
                                writeField(a.getBirthDate(), out);
                                out.write(9);
                                writeField(a.isMale(), out);
                                out.write(9);
                                writeField(a.getCountry(), out);
                                out.write('\r');
                            }
                            else {
                                writeField(null, out);
                                out.write('\r');
                            }
                        }
                    }
                }
                out.write(1);
            }
        }
        private void writeField(boolean b, OutputStream out) throws Exception{
            out.write(b?3:2);
        }

        private String encodeToASCII(String str){
            char[] strA = str.toCharArray();
            StringBuilder result = new StringBuilder();
            for (char ch : strA) {
                if ((int)ch <= 255){
                    result.append(ch);
                }
                else {
                    result.append("\\u"+String.format("%04X", (int)ch));
                }
            }
            return result.toString();
        }

        private String decodeToNative(String str){
            String[] strU = str.split("\\\\u");
            StringBuilder result = new StringBuilder();
            result.append(strU[0]);
            for (int i = 1; i<strU.length; i++){
                result.append((char) Integer.parseInt(strU[i].substring(0,4),16) + strU[i].substring(4));
            }
            return result.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;
            boolean equal = true;
            if ((((JavaRush)obj).users == null) && (this.users == null)) return equal;
            if ((((JavaRush)obj).users == null) || (this.users == null)) return false;
            equal = equal && (((JavaRush)obj).users.size() == this.users.size());
            if (! equal) return false;
            for (int i = 0; i < this.users.size(); i++){
                if ((((JavaRush)obj).users.get(i) == null) && (this.users.get(i) == null)) {
                    equal = equal && true;
                }
                else if (((((JavaRush)obj).users.get(i) == null) || (this.users.get(i) == null))) {
                    return false;
                }
                else {
                    equal = equal && (((JavaRush) obj).users.get(i).getFirstName().equals(this.users.get(i).getFirstName()));
                    equal = equal && (((JavaRush) obj).users.get(i).getLastName().equals(this.users.get(i).getLastName()));
                    equal = equal && (((JavaRush) obj).users.get(i).getBirthDate().getTime() == this.users.get(i).getBirthDate().getTime());
                    equal = equal && (((JavaRush) obj).users.get(i).isMale() == this.users.get(i).isMale());
                    equal = equal && (((JavaRush) obj).users.get(i).getCountry() == this.users.get(i).getCountry());
                }
            }
            return equal;
        }

    }
}
