package myTests.fileTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

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
        /*String path = args[0];
        String resultFileAbsolutePath = args[1];*/

        // доп вставка ...
        {
            System.out.println(File.separator);             //  \
            //
            String name = "D:\\aa";
            File file = new File(name);
            System.out.println(file.getAbsolutePath());     //  D:\aa

            //
            name = "D:/aa";
            file = new File(name);
            System.out.println(file.getAbsolutePath());     //  D:\aa
            //
            name = "D:/takoy_papki-net\\aaa";
            file = new File(name);
            System.out.println(file.getAbsolutePath());     //  D:\takoy_papki-net\aaa
            //
            // для класса myTests.fileTest.Solution
            // полный путь к текущему классу: d:\IdeaProjects\JavaRushHomeWork\src\myTests\fileTest\
            // где:
            // d:\IdeaProjects\JavaRushHomeWork   - текущая папка - путь к проекту
            // d:\IdeaProjects\JavaRushHomeWork\src\  - текущая папка - путь к пакету
            // myTests\fileTest\  - имя пакета
            System.out.println(System.getProperty("user.dir"));     //  D:\IdeaProjects\JavaRushHomeWork
            System.out.println(new File("").getAbsolutePath());     //  D:\IdeaProjects\JavaRushHomeWork
            //
            name = "a/a.txt";                               //относиттельный путь. Относительно текущей папки
            file = new File(name);
            System.out.println(file.getAbsolutePath());     //  D:\IdeaProjects\JavaRushHomeWork\a\a.txt
            //
            System.out.println("=================");
            if (1==1) return;
        }
        //...

        String path = "d:/level1/";
        String resultFileAbsolutePath = "d:/level/result.txt";

        File file = new File(path);

        List<File> resultList = (List<File>) new ForkJoinPool().invoke(new SearchingFilesTask(file));
        resultList.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        File resultFile = new File(resultFileAbsolutePath);
        try {
            resultFile = Files.move(Paths.get(resultFileAbsolutePath), Paths.get(resultFile.getParentFile() + "/allFilesContent.txt"), StandardCopyOption.REPLACE_EXISTING).toFile();
            FileOutputStream fos = new FileOutputStream(resultFile);
            for (int i = 0 ; i<resultList.size();i++) {
                fos.write(Files.readAllBytes(resultList.get(i).toPath()));
                if (i<resultList.size()-1) fos.write('\n');
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SearchingFilesTask extends RecursiveTask <List<File>> {
        private File file;
        public SearchingFilesTask(File file) {
            this.file = file;
        }
        @Override
        protected List<File> compute() {
            List<File> result = new ArrayList<>();
            List<ForkJoinTask<List<File>>> tasks = new ArrayList<>();
            File[] fileArray = file.listFiles();
            if (fileArray != null) {
                for (File f : fileArray) {
                    tasks.add(new SearchingFilesTask(f).fork());
                }
                for (ForkJoinTask<List<File>> task : tasks) {
                    result.addAll(((SearchingFilesTask) task).join());
                }
                if (result.size() == 0) file.delete();
            } else {
                if (file.length()>50){
                    file.delete();
                } else {
                    if (file.getName().endsWith(".txt")) result.add(file);
                }
            }
            return result;
        }
    }
}
