package com.javarush.test.level35.lesson10.bonus01;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
        Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
        Путь не обязательно содержит / в конце.
        НЕ все классы наследуются от интерфейса Animal.
        НЕ все классы имеют публичный конструктор без параметров.
        Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
        Добавить созданные объекты в результирующий сет и вернуть.
        Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals("D:/bb/");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        try {
            if (!pathToAnimals.endsWith("/")&&!pathToAnimals.endsWith("\\")) pathToAnimals = pathToAnimals+"/";
            URLClassLoader loader = new URLClassLoader(new URL[]{new URL("file:"+pathToAnimals)});
            File[] fileList = new File(pathToAnimals).listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return (pathname.isFile())&&(pathname.getName().endsWith(".class"));
                }
            });
            for (File currFile : fileList){
                Class clazz = null;
                try {
                    String name = currFile.getName().split("\\.class")[0];
                    clazz = loader.loadClass(name);
                } catch (NoClassDefFoundError error) { //NoClassDefFoundError
                    error.printStackTrace();
                }
                try {
                    Animal instance = (Animal) clazz.newInstance();
                    result.add(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                    /*или нет конструктора без параметров или не Animal*/
                }
            }
        } catch (MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


}
