package myTests.Reflaction.Test1;

import java.lang.reflect.*;

/**
 * Created by Valk on 27.03.15.
 */
public class GenericTest {
    public static void main(String args[]) throws IllegalAccessException, InstantiationException {
        GenericClass<B> gc = new GenericClass<B>()  { /*это анонимный класс - наследник GenericClass*/ } ;

        Class<?> gcClass = gc.getClass();   //получаем класс объекта.
                                            //Зачем <?>: объявление класса Class:
                                            //public final class Class<T>. Поэтому ? в Class<?>
        Type type = gcClass.getGenericSuperclass();     //getGenericSuperclass() вернет класс, который будет наследником
                                                        //ParameterizedType или Class, которые оба наследники Type -
                                                        //поэтому объявлено Type type, т.к. Type - базовый класс (интерфейс)
                                                        //и для ParameterizedType, и для Class

        if (type instanceof ParameterizedType) {        //вернется класс, наследник ParameterizedType, если new GenericClass<B>() - вариант (1)
            System.out.println(type);       //результат: GenericClass<B>. Т.е. с уже конкретным типом (В). Этот результат - наследник ParameterizedType
            ParameterizedType ptype = (ParameterizedType) type; //приводим к ParameterizedType для доступа к методам,
                                            // реализованным в ParameterizedType, которых нет в Type
            Type typeArg = ptype.getActualTypeArguments()[0];   //получаем тип аргумента класса
            System.out.println(typeArg);    //результат: B
            B b = (B)((Class) typeArg).newInstance(); //теперь можно создать объект класса В
            System.out.println(b);
            //то же самое , но короче:
            B b2 = (B) ((Class)((ParameterizedType)gc.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            System.out.println(b2);
            //или
            @SuppressWarnings("unchecked")
            B b3 = ((Class<B>)((ParameterizedType)gc.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            System.out.println(b3);
        }
        else {                                      //вернется класс, наследник Class, если new GenericClass<B>() - вариант (2)
            System.out.println(type);       //результат: GenericClass. Т.е. без указания типа в аргументе класса. Этот результат - наследник Class
        }
        //
    }
}


class A{}
class B extends A{ public String toString(){return "B";}}
class C extends B{}

class GenericClass<T extends A> {}
class SubGenericClass<T extends A> extends GenericClass{}

