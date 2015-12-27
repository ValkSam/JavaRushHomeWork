package myTests.ClassesTest;

/**
 * Created by Valk on 03.05.15.
 * 1. Видимость private членов внури внешнего класса
 * 2. Наследование от внутренних класов
 */
public class ClassesTest2
{
    static public int e = 6;
    static private int f = 7;

    public int g = 8;
    private int h = 9;


    //private члены внури внешнего класса видны всем внутренним объектам

    static class NestedA{
        private static int a = 1;
        public static int aa = 2;
        static {
            System.out.println(111);
            System.out.println(ClassesTest2.f);
            System.out.println(new ClassesTest2().h);
            System.out.println(ClassesTest2.NestedB.bb);
            System.out.println(new ClassesTest2.NestedB().b);
        }
    }

    static class NestedB{
        private int b = 3;
        private static int bb = 33;
    }

    class InnerC{
        private int c = 4;
        {
            System.out.println(ClassesTest2.NestedA.a); //private член nested класса виден из других внутренних объетов этого же внешнего класса
            System.out.println(ClassesTest2.f); //private член внешнего класса тоже виден
            System.out.println(ClassesTest2.this.h); //private член объекта внешнего класса тоже виден
            //
            System.out.println(ClassesTest2.this.new InnerD().d); //private член другого объекта внешнего класса тоже виден
        }
    }

    class InnerD{
        private int d = 5;
    }

    public static void main(String args[])
    {
        new ClassesTest2(); //static классы не будут при этом инициализированы, признаком чего будет отсутствие вывода "111"
        System.out.println("========");
        ClassesTest2.NestedA.aa = 22; //использование объекта static класса вызовет его инициализацию - видим вывод "111"
        new ClassesTest2().new InnerC(); //демо: видимости private членов
        //new B(new ClassesTest2());
        new B();
    }
}

class B extends ClassesTest2.InnerD{ //пример наследования inner класса вне внешнего класса
    public B(ClassesTest2 a) {  //... соответсвенно и это обязательно
        a.super(); //это обязательно,  т.к. inner объект ClassesTest2.InnerD должен хранить ссылку на объект внешнего класса, а это способ ее передать ...
        System.out.println("******1******");
    }
    public B() {  //... можно и так ...
        new ClassesTest2().super(); //...можно и так передать ссылку на внешний объект
        System.out.println("******2******");
    }
}

class C extends ClassesTest2.NestedB{ //пример наследования nested класса вне внешнего класса
    public C() {  //... соответсвенно нет необходимости передавать в конструкторе экземпляр внешнего класса - такой конструктор можно опустить
        super(); //nested объект ClassesTest2.NestedB не должен хранить ссылку на внешний класс  ...
    }
}
