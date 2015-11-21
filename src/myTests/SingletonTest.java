package myTests;

/*

 */
public class SingletonTest {
    public static void main (String[] arg){
        //Для Пример 1 - работает
        SomeInterface obj1 = SomeInterfaceFactory.getSomeInterface(); //создастся экземпляр класса
        System.out.println(obj1.doSomething()); // покажет 0 (первоначальное значение)
        obj1.setX(2); //установим в 2
        SomeInterface obj2 = SomeInterfaceFactory.getSomeInterface(); //новый екземпляр не создастся
        System.out.println(obj2.doSomething()); //покажет 2 - т.е. действительно obj2 -это не отдельный объект, а ссылка на все тот же obj1
        //

        //
        //Для Примера 2 - пробуем то же самое, но не создавая обертки SomeInterfaceFactory
        SomeInterface obj3 = SomeInterfaceImpl.getSomeInterface();
        System.out.println(obj3.doSomething());
        obj3.setX(3);
        SomeInterface obj4 = SomeInterfaceImpl.getSomeInterface();
        System.out.println(obj4.doSomething());
        //все все работает аналогично с Примером1
        //но это мы явно не создавали экземпляра SomeInterfaceImpl. Попробуем ...
        // ВНИМАНИЕ (конструкор объявлен должен быть как public)
        //SomeInterface obj5 = new SomeInterfaceImpl(); //выбъет ошибку конструктор. Вроде тоже самое, но грубо
        //System.out.println(obj5.doSomething());
        //а можно так: объявить конструктор SomeInterfaceImpl() как private - тогда ошибка будет на этапе компиляции
        //получается, что можно сделать так и будет работать

    }
}
