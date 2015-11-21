package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1978

Подсказка: воспользуйтесь классом Calendar
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner scan;
        public PersonScannerAdapter(Scanner scan){
            this.scan = scan;
        }
        @Override
        public Person read() throws IOException {
            String lastName = scan.next();
            String firstName = scan.next();
            String middleName = scan.next();
            int day = scan.nextInt();
            int month = scan.nextInt();
            int year = scan.nextInt();
            Calendar calendar = new GregorianCalendar(year, --month, day);
            Date birthDate = calendar.getTime();
            return new Person (firstName, middleName, lastName, birthDate);
        }

        @Override
        public void close() throws IOException {
            scan.close();
        }
    }
    public static void main(String[] args) throws Exception{
        String str = "Иванов Иван Иванович 31 12 1978";
        System.out.println(new PersonScannerAdapter(new Scanner(str)).read().toString());
    }
}
