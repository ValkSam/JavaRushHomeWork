package com.javarush.test.level08.lesson08.task01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{

    public static HashSet<String> createSet()
    {
        return new HashSet(Arrays.asList("Лавка Лавливать Лавовый Лавочка Левочка Лавочник Лавочница Лавочный Лавр Лавра Лавровишневый Лавровишня Лавровый Лаврский Лавчонка Лаг Лагерный Лагерь Лаглинь Лагуна".split(" ")));
    }
}
