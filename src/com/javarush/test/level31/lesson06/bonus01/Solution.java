package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) {
        try {
            args = new String[5];
            args[0] = "d:/result.mp4";
            args[1] = "d:/result.002";
            args[2] = "d:/result.003";
            args[3] = "d:/result.001";
            args[4] = "d:/result.004";
            String resultFileName = args[0];
            String[] partFiles = new String[args.length - 1];
            partFiles = Arrays.copyOfRange(args, 1, args.length);
            Arrays.sort(partFiles);
            long sms = System.currentTimeMillis();
            /* вариант с временным файлом...
            Path tmpFile = Files.createTempFile(Paths.get(resultFileName).getFileName().toString(), "");
            OutputStream out = Files.newOutputStream(tmpFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            for (String partName : partFiles) {
                out.write(Files.readAllBytes(Paths.get(partName)));
            }
            out.close();
            */
            //вариант без временного файла - намного быстрее
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (String partName : partFiles) {
                baos.write(Files.readAllBytes(Paths.get(partName)));
            }
            baos.close();
            //
            System.out.println(System.currentTimeMillis() - sms);
            sms = System.currentTimeMillis();
            //ZipInputStream zis = new ZipInputStream(new FileInputStream(tmpFile.toFile())); //для варианта с временным файлом
            ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray()));
            zis.getNextEntry();

/*          вариант альтернативный Files.copy - по скорости сопоставим
            FileOutputStream fos = new FileOutputStream(resultFileName);
            byte[] buf = new byte[512];
            int count;
            while ((count = zis.read(buf)) != -1) {
                fos.write(buf, 0, count);
            }
            fos.close();
*/
            Files.copy(zis, Paths.get(resultFileName), StandardCopyOption.REPLACE_EXISTING);

            System.out.println(System.currentTimeMillis() - sms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
