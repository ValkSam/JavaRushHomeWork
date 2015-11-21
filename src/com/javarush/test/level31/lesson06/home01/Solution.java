package com.javarush.test.level31.lesson06.home01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipName = args[1];

        String fn = Paths.get(fileName).getFileName().toString();

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipName));
        Map<ZipEntry, byte[]> entrys = new HashMap<>();
        for (ZipEntry zipEntry = zipIn.getNextEntry(); zipEntry != null; zipEntry = zipIn.getNextEntry()) {
            //byte[] buf = new byte[zipEntry.getSize() == -1 ? 0 : (int) zipEntry.getSize()]; //так нельзя т.к. для записей,
            // добавленных так (***) (т.е. для соданнных (new) entry, а не считанных через getNextEntry() ), zipEntry.getSize() будет возвращать -1
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int count;
            while ((count = zipIn.read(buf)) != -1) {
                baos.write(buf, 0, count);
            }
            entrys.put(zipEntry, baos.toByteArray());
        }

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipName));
        for (Map.Entry<ZipEntry, byte[]> pair : entrys.entrySet()) {
            String entryName = pair.getKey().getName();
            if (!Paths.get(entryName).getFileName().toString().equals(fn)) {
                zipOut.putNextEntry(pair.getKey()); //(***)
                zipOut.write(pair.getValue());
                zipOut.closeEntry();
            }
        }

        byte[] buf = Files.readAllBytes(Paths.get(fileName));
        zipOut.putNextEntry(new ZipEntry("new/" + fn));
        zipOut.write(buf);
        zipOut.closeEntry();
        zipOut.close();

    }
}
