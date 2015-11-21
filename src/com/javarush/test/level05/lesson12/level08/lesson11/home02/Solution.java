package com.javarush.test.level08.lesson11.home02;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Множество всех животных
1. Внутри класса Solution создать public static классы Cat, Dog.
2. Реализовать метод createCats, котороый должен возвращать множество с 4 котами.
3. Реализовать метод createDogs, котороый должен возвращать множество с 3 собаками.
4. Реализовать метод join, котороый должен возвращать объединенное множество всех животных - всех котов и собак.
5. Реализовать метод removeCats, котороый должен удалять из множества pets всех котов, которые есть в множестве cats.
6. Реализовать метод printPets, котороый должен выводить на экран всех животных, которые в нем есть. Каждое животное с новой строки
*/

public class Solution
{
    public static void main(String[] args)
    {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();
        Set<Object> pets = join(cats, dogs);
        printPets(pets);
        removeCats(pets, cats);
        printPets(pets);
    }
    public static Set<Cat> createCats()
    {
        HashSet<Cat> result = new HashSet<Cat>();
        result.addAll(Arrays.asList(new Cat("cat1"), new Cat("cat2"), new Cat("cat3"), new Cat("cat4")));
        return result;
    }
    public static Set<Dog> createDogs()
    {
        return new HashSet<Dog>(Arrays.asList(new Dog("dog1"), new Dog("dog2"), new Dog("dog3")));
    }
    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs)
    {
        Set<Object> result = new HashSet<Object>(cats);
        result.addAll(dogs);
        return result;
    }
    public static void removeCats(Set<Object> pets, Set<Cat> cats)
    {
        for (Iterator iterator = pets.iterator(); iterator.hasNext();){
            if (iterator.next() instanceof Cat) iterator.remove();
        }

    }
    public static void printPets(Set<Object> pets)
    {
        for (Object pet : pets) System.out.println(pet);
    }
    public static class Cat{
        private String name;
        public Cat(String name){
            this.name = name;
        }
    }
    public static class Dog{
        private String name;
        public Dog(String name){
            this.name = name;
        }
    }
}
