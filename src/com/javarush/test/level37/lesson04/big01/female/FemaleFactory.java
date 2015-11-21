package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Valk on 13.07.15.
 */
public class FemaleFactory implements AbstractFactory {
    @Override
    public Human getPerson(int age){
        if (age <=12) return new KidGirl();
        if (age <=19) return new TeenGirl();
        return new Woman();
    }
}
