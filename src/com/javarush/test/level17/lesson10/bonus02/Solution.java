package com.javarush.test.level17.lesson10.bonus02;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    private static synchronized void ActionC (String[] args) throws Exception{
        ArrayList<String[]> paramsStr = getParamsStr(args);
        for (int i = 0; i < paramsStr.size(); i++) {
            String[] pGrArray = paramsStr.get(i);
            if (pGrArray.length < 3) return;
            String name = pGrArray[0];
            for (int j = 1; j < pGrArray.length - 2; j++) name = name + " " + pGrArray[j];
            if ("м".equals(pGrArray[pGrArray.length - 2])) {
                allPeople.add(Person.createMale(name, new SimpleDateFormat("dd/MM/yyyy").parse(pGrArray[pGrArray.length - 1])));
                System.out.println(allPeople.size() - 1);
            }
            if ("ж".equals(pGrArray[pGrArray.length - 2])) {
                allPeople.add(Person.createFemale(name, new SimpleDateFormat("dd/MM/yyyy").parse(pGrArray[pGrArray.length - 1])));
                System.out.println(allPeople.size() - 1);
            }
        }
    }

    private static synchronized void ActionU (String[] args) throws Exception {
        ArrayList<String[]> paramsStr = getParamsStr(args);
        for (int i = 0; i < paramsStr.size(); i++) {
            String[] pGrArray = paramsStr.get(i);
            if (pGrArray.length < 4) return;
            Integer id = Integer.valueOf(pGrArray[0]);
            String name = pGrArray[1];
            for (int j = 2; j < pGrArray.length - 2; j++) name = name + " " + pGrArray[j];
            allPeople.get(id).setName(name);
            allPeople.get(id).setSex("м".equals(pGrArray[pGrArray.length - 2]) ? Sex.MALE : Sex.FEMALE);
            allPeople.get(id).setBirthDay(new SimpleDateFormat("dd/MM/yyyy").parse(pGrArray[pGrArray.length - 1]));
        }
    }

    private static synchronized void ActionD (String[] args) throws Exception {
        if (args.length < 2) return;
        for (int i = 1; i < args.length; i++) {
            Integer id = Integer.valueOf(args[i]);
            allPeople.get(id).setName(null);
            allPeople.get(id).setSex(null);
            allPeople.get(id).setBirthDay(null);
        }
    }

    private static void ActionI (String[] args) throws Exception {
        if (args.length < 2) return;
        String[] idArr = Arrays.copyOfRange(args, 1, args.length);
        Arrays.sort(idArr);
        for (int i = 0; i < idArr.length; i++) {
            Integer id = Integer.valueOf(idArr[i]);
            System.out.println(allPeople.get(id).getName() + " "
                    + (allPeople.get(id).getSex() == Sex.MALE ? "м" : "ж") + " "
                    + (new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(allPeople.get(id).getBirthDay())));
        }
    }

    private static ArrayList<String[]> getParamsStr(String[] args) throws Exception{
        ArrayList<String[]> pGroup = new ArrayList<String[]>();
        Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        String line = "";
        for (int i = 1; i<args.length; i++){
            line+= args[i];
            if (pattern.matcher(args[i]).matches()) {
                pGroup.add(line.split(" "));
                line = "";
            }
            else line+= " ";
        }
        return pGroup;
    }

    public static void main(String[] args) throws Exception{
            if (args.length<2) return;
            String param = args[0];
            if ("-c".equals(param)) {
                ActionC (args);
            } else if ("-u".equals(param)) {
                ActionU(args);
            } else if ("-d".equals(param)) {
                ActionD(args);
            } else if ("-i".equals(param)) {
                ActionI(args);
            }
    }
}
