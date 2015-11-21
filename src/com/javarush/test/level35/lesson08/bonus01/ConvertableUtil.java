package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <K>Map convert(List<? extends Convertable<K>> list) {
        Map<K,? super Convertable<K>> result = new HashMap<>();
        for (Convertable<K> item : list){
            result.put(item.getKey(),item);
        }
        return result;
    }
}
