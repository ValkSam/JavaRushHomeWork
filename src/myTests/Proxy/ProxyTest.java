package myTests.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by Valk on 03.06.15.
 */
public class ProxyTest {
    public static void main(String[] args) {
        FirstSimpleClass scOriginal = new FirstSimpleClass(); //объект класса, реализующего интерфейс SimpleClass
        ClassLoader loader = scOriginal.getClass().getClassLoader(); //загрузчик класса
        Class<?>[] interfaces = scOriginal.getClass().getInterfaces(); //интерфейсы класса

        //обработчик, на который заворачиваются вызовы методов интерфейса SimpleClass
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(scOriginal);

        SimpleClass sc = (SimpleClass) Proxy.newProxyInstance(loader, interfaces, myInvocationHandler); // Важно:
        //должно быть приведение к интерфейсу, не классу: (SimpleClass). Так нельзя: (FirstSimpleClass)

        //вызываем методы через прокси-объект
        sc.method_1(1); //фактически будет вызван метод MyInvocationHandler.invoke()
        sc.method_2(2, "qwerty");
    }

    static class MyInvocationHandler implements InvocationHandler{
        private SimpleClass sc; //оригинальнй объект для возможности вызвать его методы

        public MyInvocationHandler(SimpleClass sc) {
            this.sc = sc;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //индикативный вывод
            System.out.println();
            System.out.printf("%s - %s - %s", proxy.getClass().getSimpleName(), method.getName(), Arrays.asList(args));
            System.out.println();
            //
            switch(method.getName()){
                case "method_1":{
                    args[0] = Integer.valueOf(args[0].toString())*100; //в качестве доп. функциональности метода, увеличиваем в 100 раз значение аргумента
                    method.invoke(sc, args); //вызываем оригнальный метод
                    break;
                }
                case "method_2":{
                    args[1] = args[1]+"-asdf"; //меняем значение второго аргумента метода
                    method.invoke(sc, args); //вызываем оригнальный метод
                    break;
                }
            }
            //
            return null;
        }
    }
}

interface SimpleClass{
    void method_1(int i);

    void method_2(int i, String s);
}

class FirstSimpleClass implements SimpleClass {
    public void method_1(int i){
        System.out.printf("simpleClass.method_1 i = %s", i);
        System.out.println();
    }

    public void method_2(int i, String s){
        System.out.printf("simpleClass.method_2 i = %s, s = %s", i, s);
        System.out.println();
    }
}
