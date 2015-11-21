package myTests.classLoader.lev35les10b01;

import com.javarush.test.level35.lesson10.bonus01.Animal;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
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
        //Set<? extends Animal> allAnimals = getAllAnimals("D://IdeaProjects/JavaRushHomeWork/out/production/JavaRushHomeWork/com/javarush/test/level35/lesson10/bonus01/data/");
        Set<? extends Animal> allAnimals = getAllAnimals("for_lev35les10b01/"); //абс путь D:\IdeaProjects\JavaRushHomeWork\for_lev35les10b01
        System.out.println(allAnimals);
        //com.javarush.test.level35.lesson10.bonus01.Animal - полное имя класса использовано т.к. классы скомпилированы в com.javarush.test.level35.lesson10.bonus01
        //и именно к этому Animal они должны приводится - не к местному
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        File[] fList;
        File file = new File(pathToAnimals);

        if (file.isDirectory()) {
            fList = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".class");
                }
            });
        } else {
            fList = new File[]{file};
        }

        mClassLoader loader = new mClassLoader();

        for (File currFile : fList) {
            if (currFile.isFile()) {
                try {
                    loader.setFile(currFile);
                    String className = ""; // указываем пустое имя, чтобы спровоцировать исключение NoClassDefFoundError в mClassLoader
                    //String className = currFile.getName().split(".class")[0];
                    //String className = packageName + currFile.getName().split(".class")[0]; //определяем полное имя класса, полагая, что классы принадлежат пакету Animal+".data."
                    try {
                        Class clazz = loader.loadClass(className);
                        Animal instance = (Animal) clazz.newInstance();
                        result.add(instance);
                    } catch (Exception ignored) {/*или нет конструктора без параметров или не Animal*/
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static class mClassLoader extends ClassLoader {
        private File file;

        public void setFile(File file) {
            this.file = file;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            System.out.println("Сработал переопределнный findClass()");
            try {
                byte[] buf = Files.readAllBytes(file.toPath());
                try {
                    return defineClass(null, buf, 0, buf.length);
                } catch (NoClassDefFoundError error) {
                    //Это попытка извлекать имя класса из сообщения исключения. Лишнее - указание null в качестве имени класса решает проблему
                    String s = error.getMessage();
                    if (s.contains("wrong name:")) {
                        name = s.split("wrong name:")[1].split("\\)")[0].replaceAll("/", ".").replaceAll("\\\\", ".").trim();
                        return defineClass(name, buf, 0, buf.length);
                    }
                    return null;
                }
            } catch (Exception e) {
                return super.findClass(name);
            }
        }
    }
}
