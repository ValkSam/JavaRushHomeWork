package com.javarush.test.level17.lesson10.bonus01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
            try {
                String param = args[0];
                if ("-c".equals(param)) {
                    if (args.length < 4) return;
                    String name = args[1];
                    for (int i = 2; i<args.length - 2; i++) name = name+" "+args[i];
                    if ("м".equals(args[args.length - 2])) {
                        allPeople.add(Person.createMale(name, new SimpleDateFormat("dd/MM/yyyy").parse(args[args.length - 1])));
                        System.out.println(allPeople.size() - 1);
                    }
                    if ("ж".equals(args[args.length - 2])) {
                        allPeople.add(Person.createFemale(name, new SimpleDateFormat("dd/MM/yyyy").parse(args[args.length - 1])));
                        System.out.println(allPeople.size() - 1);
                    }

                } else if ("-u".equals(param)) {
                    if (args.length != 5) return;
                    Integer id = Integer.valueOf(args[1]);
                    allPeople.get(id).setName(args[2]);
                    allPeople.get(id).setSex("м".equals(args[3])? Sex.MALE : Sex.FEMALE);
                    allPeople.get(id).setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse(args[4]));

                } else if ("-d".equals(param)) {
                    if (args.length != 2) return;
                    Integer id = Integer.valueOf(args[1]);
                    allPeople.get(id).setName(null);
                    allPeople.get(id).setSex(null);
                    allPeople.get(id).setBirthDay(null);

                } else if ("-i".equals(param)) {
                    if (args.length != 2) return;
                    Integer id = Integer.valueOf(args[1]);
                    System.out.println(allPeople.get(id).getName() + " " + (allPeople.get(id).getSex() == Sex.MALE ? "м" : "ж") + " " + (new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(allPeople.get(id).getBirthDay())));
                }
            } catch (Exception e) {
            }
    }
}
