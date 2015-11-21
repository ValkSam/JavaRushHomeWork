package com.javarush.test.level33.lesson05.home03;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/* Десериализация JSON объекта
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.4.3

В метод convertFromJsonToNormal первым параметром приходит имя файла, который содержит один ДЖЕЙСОН объект.
Вторым параметром приходит имя класса, объект которого находится в файле.
Метод convertFromJsonToNormal должен вычитать объект из файла, преобразовать его из JSON и вернуть его.
*/
public class Solution {

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ByteArrayInputStream baos = new ByteArrayInputStream(Files.readAllBytes(Paths.get(fileName)));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(baos, clazz);
    }

    public static void main(String[] args) throws IOException {
        Cat cat = convertFromJsonToNormal("d:/q.txt", Cat.class);
        System.out.println(cat);
    }
}

class Cat {
    @JsonProperty("wildAnimal")
    public String name;

    public int age;

    @JsonProperty("over")
    public int weight;

    Cat() {
    }
}
