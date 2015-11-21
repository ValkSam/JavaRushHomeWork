package myTests;

/*
  Пример 1
  http://easy-code.ru/lesson/java-interfaces
 */
public class SomeInterfaceFactory{

    private static SomeInterface impl = null;

    private SomeInterfaceFactory(){
    }

    public static SomeInterface getSomeInterface(){
        if (impl == null){
            impl = new SomeInterfaceImpl();
        }
        return impl;
    }

    private static class SomeInterfaceImpl implements SomeInterface{
        //спрятав SomeInterfaceImpl (сделав его private), обеспечили невозможность создать объект SomeInterfaceImpl обычным способом
        //создать можно только через метод getSomeInterface, который создает объект, только, если такой объект не создан.
        // Если такой объект ранее уже был создан, то просто вернет ранее созданый.
        //Таким образом можно будет создать только один экземпляр объекта класса SomeInterfaceImpl
        //static нужен так как вызывается из static getSomeInterface(). А getSomeInterface()сделан как static , чтобы не создавать
        //объект SomeInterfaceFactory ради создания SomeInterfaceImpl
        private int x = 0;
        public int doSomething(){
            return x;
        }
        public void setX (int x){
            this.x = x;
        }
    }
}