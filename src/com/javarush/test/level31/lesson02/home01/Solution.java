package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution {
    public static void main(String[] args) {
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File file = new File(path);
        List<File> resultList = SearchFiles(file);
        Collections.sort(resultList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        File resultFile = new File(resultFileAbsolutePath);
        try {
            resultFile = Files.move(Paths.get(resultFileAbsolutePath), Paths.get(resultFile.getParentFile() + "/allFilesContent.txt"), StandardCopyOption.REPLACE_EXISTING).toFile();
            FileOutputStream fos = new FileOutputStream(resultFile);
            for (int i = 0; i < resultList.size(); i++) {
                fos.write(Files.readAllBytes(resultList.get(i).toPath()));
                if (i < resultList.size() - 1) fos.write('\n');
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<File> SearchFiles(File file) {
        List<File> result = new ArrayList<>();
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            for (File f : fileArray) {
                result.addAll(SearchFiles(f));
            }
            if (result.size() == 0) file.delete();
        } else {
            if (file.length() > 50) {
                file.delete();
            } else {
                if (file.getName().endsWith(".txt")) result.add(file);
            }
        }
        return result;
    }
}
