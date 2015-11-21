package com.javarush.test.level37.lesson04.big01.male;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by Valk on 12.07.15.
 */
public class MaleFactory implements AbstractFactory {
    @Override
    public Human getPerson(int age){
        if (age <=12) return new KidBoy();
        if (age <=19) return new TeenBoy();
        return new Man();

    }
}
