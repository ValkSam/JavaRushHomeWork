package com.javarush.test.level17.lesson10.home07;

import java.util.HashMap;
import java.util.Map;

/* ApplicationContext
ApplicationContext будет доступен множеству нитей.
Сделать так, чтобы данные не терялись: подумай, какое ключевое слово необходимо поставить и где.
*/

public class ApplicationContext<GenericsBean extends Bean> {
    private volatile Map<String, GenericsBean> container = new HashMap<String, GenericsBean>();
    //Map<Name, some class implemented Bean interface>


    protected ApplicationContext() {
        parseAllClassesAndInterfaces();
    }

    public synchronized GenericsBean getByName(String name) {
          return container.get(name);
    }

    public synchronized GenericsBean removeByName(String name) {
        return container.remove(name);
    }

    protected void parseAllClassesAndInterfaces(){};
}