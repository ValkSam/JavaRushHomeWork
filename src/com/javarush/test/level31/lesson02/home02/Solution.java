package com.javarush.test.level31.lesson02.home02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> list = getFileTree("d:/level1");
        for (String str:list){
            System.out.println(str);
        }
    }
    public static List<String> getFileTree(String root) throws IOException, InterruptedException {
        LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>();
        List<String> list = new ArrayList<>();

        queue.put(new File(root));
        while (queue.size() > 0) {
            File file = queue.take();

            if (file.isFile()) {
                list.add(file.getAbsolutePath());
            } else {
                File[] fileList = file.listFiles();
                for (File f : fileList) {
                    queue.put(f);
                }
            }
        }

        return list;

    }
}
