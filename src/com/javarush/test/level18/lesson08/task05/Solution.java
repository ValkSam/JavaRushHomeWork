package com.javarush.test.level18.lesson08.task05;

import java.util.List;

/* Таблица
Измените класс TableInterfaceWrapper так, чтобы он стал Wrapper-ом для ATableInterface.
Метод setModel должен вывести в консоль количество элементов в новом листе перед обновлением модели
Метод getHeaderText должен возвращать текст в верхнем регистре - используйте метод toUpperCase()
*/

public class Solution {
    public class TableInterfaceWrapper implements ATableInterface {
        ATableInterface table;
        public TableInterfaceWrapper(ATableInterface table){this.table = table;}
        public void setModel(List rows){
            System.out.println(rows.size());
            table.setModel(rows);
        }
        public String getHeaderText(){
            return table.getHeaderText().toUpperCase();
        }
        public void setHeaderText(String newHeaderText){
            table.setHeaderText(newHeaderText);
        }

    }

    public interface ATableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }
}