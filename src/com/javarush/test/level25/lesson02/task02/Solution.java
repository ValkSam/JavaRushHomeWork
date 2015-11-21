package com.javarush.test.level25.lesson02.task02;

import java.util.*;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            wheels = new ArrayList<Wheel>();
            int[] markWheels = new int[4];
            String[] wheelNames = loadWheelNamesFromDB();
            if (wheelNames.length < 4) throw new IllegalArgumentException();
            for (int i = 0; i<4; i++){
                int idx = Wheel.valueOf(wheelNames[i]).ordinal();
                if (markWheels[idx] != 0) throw new IllegalArgumentException();
                markWheels[idx] = 1;
                wheels.add(Wheel.valueOf(wheelNames[i]));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }


}
