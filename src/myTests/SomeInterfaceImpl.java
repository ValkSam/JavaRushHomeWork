package myTests;

/*
 Пример 2
 */
public class SomeInterfaceImpl implements SomeInterface{
    private static SomeInterface impl = null;

    private int x = 0;

    private SomeInterfaceImpl(){
            if (impl != null){
                throw new NullPointerException("объект уже создан"); //предотвратить создание объекта только так можно - через ошибку
                // но ловить ошибку придется снаружи, здесь нельзя т.к. не будет прерван конструктор.
            }
    }

    public static SomeInterface getSomeInterface(){
        if (impl == null){
            impl = new SomeInterfaceImpl();
        }
        return impl;
    }

    public int doSomething(){
        return x;
    }
    public void setX (int x){
        this.x = x;
    }
}