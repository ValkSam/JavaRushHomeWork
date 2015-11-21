package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V obj = cache.get(key);
        if (obj == null) {
            obj = (V) clazz.getDeclaredConstructor(key.getClass()).newInstance(key);
            put(obj); // == cache.put(key, obj);
        }
        return obj;
    }

    public boolean put(V obj) {
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
            K key = (K)method.invoke(obj);
            if (key == null) return false;
            cache.put(key, obj);
            for (Field field : obj.getClass().getDeclaredFields()){ // тут мы сбрасываем твердую ссылку на объект-ключ внутри объекта-значения
                if (field.getType().getName().equals(key.getClass().getTypeName())) {
                    field.setAccessible(true);
                    field.set(obj, null);
                }
            }
            //насколько по жизни приемлем такой вариант (т.е. "портить" объект, обнуляя поле - зависит от контекста. )
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
