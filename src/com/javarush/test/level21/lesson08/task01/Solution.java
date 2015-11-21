package com.javarush.test.level21.lesson08.task01;

import java.util.*;

/* Глубокое клонирование карты
Клонируйтие объект класса Solution используя глубокое клонирование.
Данные в карте users также должны клонироваться.
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        //
        //solution.userList.add(new User(1, "user1"));
        //solution.userList.add(new User(2, "user2"));
        solution.userList.add("user1");
        solution.userList.add("user2");
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            //clone.userList.get(0).name = "qqqq"; //будет изменяться в solution и в clone;
            clone.userList.set(0,"qqqq"); //для String проблем не будет, т.к. при set создастся новый объект

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    //protected Solution clone() throws CloneNotSupportedException {
    public Solution clone() throws CloneNotSupportedException { //Допустимо изменить модификатор доступа и сузить тип:
        System.out.println("сработал переопределенный clone");
        Solution result = (Solution)super.clone();
        result.users = new LinkedHashMap<>();
        for (Map.Entry<String, User> pair : this.users.entrySet()) {
            User user = pair.getValue().clone();
            result.users.put(pair.getKey(), user);
        }
        //result.userList = (ArrayList<User>)this.userList.clone(); //объекты-элементы не будут клонированы - просто скопированы ссылки
        result.userList = (ArrayList<String>)this.userList.clone();

        result.userArr = this.userArr.clone(); //так же объекты-элементы масыва не будут клонированы

        return result;
    }

    protected Map<String, User> users = new LinkedHashMap<>();
    //protected ArrayList<User> userList = new ArrayList<>();
    protected ArrayList<String> userList = new ArrayList<>();
    protected User[] userArr = {new User(1,"name1"), new User(2,"name2")};

    protected final String name = "name";


    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        protected User clone() throws CloneNotSupportedException {
            return (User)super.clone();
        }
    }
}
