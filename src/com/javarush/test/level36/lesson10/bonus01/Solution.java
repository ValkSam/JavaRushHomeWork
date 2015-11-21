package com.javarush.test.level36.lesson10.bonus01;

import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является имя подпакета, который находится внутри данного пакета, например, "data/second".
        Имя подпакета может содержать File.separator.
        В этом подпакете находятся только java классы.
        Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
        Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
        Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        String s = "src/" + this.getClass().getPackage().getName().replaceAll("[.]", "/") + "/" + packageName;
        File[] files = new File(s).listFiles();
        hiddenClasses.clear();
        for (File file : files) {
            mClassLoader classLoader = new mClassLoader(this.getClass().getClassLoader(), file);
            //String className = file.getName().split(".class")[0];
            //String className = this.getClass().getPackage().getName() + "." + packageName.replaceAll("/", ".") + "." + file.getName().split(".class")[0];
            //hiddenClasses.add(classLoader.loadClass(className));
        }
    }

    /*неизвестная ошибка на сервере*/
    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses) {
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                try {
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return null;
    }

    private class mClassLoader extends ClassLoader {
        private File file;

        public mClassLoader(ClassLoader parent, File file) {
            super(parent);
            this.file = file;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] buf = Files.readAllBytes(file.toPath());
                return defineClass(name, buf, 0, buf.length);
            } catch (Exception e) {
                return super.findClass(name);
            }
        }
    }

    ;
}
