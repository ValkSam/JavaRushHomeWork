package myTests.plug;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Valk on 18.07.15.
 */
public class JarClassGetter implements ClassGetter {
    private Class clazz;
    @Override
    public Class getClazz() {
        return null;
    }

    public JarClassGetter(String fileName, String className) throws ClassGettingException {
        File file = new File(fileName);
        if (!file.exists())  throw new ClassGettingException("Файл не найден: "+fileName);
        if (file.isFile()){
            if (! fileName.endsWith(".jar")) throw new ClassGettingException("Некорректный тип файла: "+fileName);
        } else {
            String simpleClassName = className.split("\\.")[className.split("\\.").length-1];
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return (pathname.isFile() && pathname.getName().endsWith(".jar") && simpleClassName.equals(pathname.getName()));
                }
            });
            if (files.length == 0) throw new ClassGettingException("Файл не найден: "+file.getAbsolutePath()+File.separator+simpleClassName+".jar");
            file = files[0];
        }


    }
}
