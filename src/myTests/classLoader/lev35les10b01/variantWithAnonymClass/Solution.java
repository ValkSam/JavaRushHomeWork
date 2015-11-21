package myTests.classLoader.lev35les10b01.variantWithAnonymClass;

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
        Set<? extends Animal> allAnimals = getAllAnimals("D://aa/");
        System.out.println(allAnimals);
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

        for (File currFile : fList) {
            if (currFile.isFile()) {
                final File cf = currFile;
                try {
                    Class clazz = new ClassLoader() {
                        @Override
                        protected Class<?> findClass(String name) throws ClassNotFoundException {
                            try {
                                byte[] buf = Files.readAllBytes(cf.toPath());
                                return defineClass(null, buf, 0, buf.length);
                            } catch (Exception e) {
                                return super.findClass(name);
                            }
                        }
                    }.loadClass("");
                    Animal instance = (Animal) clazz.newInstance();
                    result.add(instance);
                } catch (Exception ignored) {/*или нет конструктора без параметров или не Animal*/}
            }
        }
        return result;
    }


}
