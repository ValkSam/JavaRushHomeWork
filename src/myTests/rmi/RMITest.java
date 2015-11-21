package myTests.rmi;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Valk on 05.06.15.
 */
public class RMITest {

    public static void main(String args[]) throws Exception {
        /**/
        try {
            {
                int variant = 1;
                int port = 9099;
                String remoteServiceName = "asd"; //имя сервиса - любое уникальное имя
                TestServer service = new TestServerImpl();
                TestServer stub = (TestServer)UnicastRemoteObject.exportObject(service, 0); //создаем объект, который будет принимать на себя внешние вызовы;
                //Экспортируем удалённый объект и получаем stub, посредством которого клиент будет вызывать методы нашего объекта.
                //Второй параметр exportObject — порт, который будет использоваться для соеденения с удалённым объектом,
                //0 — выбор любого свободного порта

                //можно обойтись и без TestServer stub = (TestServer)UnicastRemoteObject.exportObject(service, 0);
                //но для этого TestServerImpl должен быть унаследован от UnicastRemoteObject. Тогда экспорт сделает конструктор
                //UnicastRemoteObject. Пример такого варианта в варианте 4, который для случая использования Naming.rebind(),
                //однако все верно и для registry.rebind()

                //stub нужно передать клиенту. Тут возможны совершенно разные варианты. Можно даже передать stub клиенту на дискете 3.5'' :)
                //Мы воспользуемся RMI-регистратором. Его можно как создать внутри нашей vm, так и использовать «внешний»,
                //представляемый утилитой rmiregistry.
                //Создание внутри vm:
                Registry registry = LocateRegistry.createRegistry(port); //создаем реестрс привязкой к порту 9099
                registry.rebind(remoteServiceName, stub); //регистрируем объект в реестре под именем сервиса
                //
                Thread clientThread = new Thread(new TestClient(variant));
                clientThread.start();
                //
                clientThread.join();
                UnicastRemoteObject.unexportObject(service, true);
            }
        } catch (Exception e) {
        }

        //остальные варианты используют Naming.bind вместо registry.bind
        //Методы Naming используют порт = 1099 (Registry.REGISTRY_PORT)
        int port = Registry.REGISTRY_PORT; //поэтому нужно использование именно его, другие не годятся
        Registry registry = LocateRegistry.createRegistry(port);

        try {
            {
                int variant = 2;
                String remoteServiceName = "asd"; //имя сервиса
                TestServer service = new TestServerImpl();
                Naming.rebind(remoteServiceName, service); //при таком варианте, созданный реестр на порт 1099 вроде как не используется (нет в параметрах)
                //на самом деле используется: внутри Naming.rebind вызывается Naming.getRegistry (не LocateRegistry.createRegistry)
                //(***)
                //передаем service, а не stub (stub вообще не создаем). Условием для этого является implements Serializable для класса service
                //
                //можно без implements Serializable, но тогда придется создать
                //TestServer stub = (TestServer)UnicastRemoteObject.exportObject(service, 0);
                //и передать в Naming.rebind stub или тот же service. В последнем случае выходит, что создаем stub, но явно его не используем
                //и получается, что создали stub, передали service, и Naming.rebind, получив service, увидем, что для него нет
                //implements Serializable, узнает о существовании (ищет ?) stub и использует его
                //Кстати, если service implements Serializable, то можно обойтись без stub и для первого варианта (port == 1099)
                //вот только не ясно - может будут бока?
                Thread clientThread = new Thread(new TestClient(variant));
                clientThread.start();
                //
                clientThread.join();
                //UnicastRemoteObject.unexportObject(service, true); //если в Naming.rebind используется Serializable service, без создания
                //stub, то нет необходимости в unexportObject
            }
        } catch (Exception e) {
        }

        try {
            {   //аналогично с 2099, но с использованием stub - т.е. без выкрутасов
                int variant = 3;
                String remoteServiceName = "asd"; //имя сервиса
                TestServer service = new TestServerImpl();
                TestServer stub = (TestServer) UnicastRemoteObject.exportObject(service, 0);
                Naming.rebind(remoteServiceName, stub);
                Thread clientThread = new Thread(new TestClient(variant));
                clientThread.start();
                //
                clientThread.join();
                UnicastRemoteObject.unexportObject(service, true);
            }
        } catch (Exception e) {
        }

        try {
            {   //вариант наследования сервера от UnicastRemoteObject
                int variant = 4;
                String remoteServiceName = "//localhost/asd"; //имя сервиса
                TestServer service = new TestServerImpl2();
                Naming.rebind(remoteServiceName, service);
                Thread clientThread = new Thread(new TestClient(variant));
                clientThread.start();
                //
                clientThread.join();
                UnicastRemoteObject.unexportObject(service, true); //это нужно, хотя явно вроде как не экспортировали - это
                //было сделано в конструкторе TestServerImpl2 (унаследованном от UnicastRemoteObject)
            }
        } catch (Exception e) {
        }


    }
}

interface TestServer extends Remote {
    void printSomething(String str) throws RemoteException;     //ВАЖНО:  throws RemoteException

    void printSomething(String str, int count) throws RemoteException;
}

class TestServerImpl implements TestServer, Serializable { //насчет Serializable смотри коммент в main (***)
    public void printSomething(String str) throws RemoteException {
        System.out.println(str);
    }

    public void printSomething(String str, int count) {
        for (int i = 0; i < count; i++) System.out.printf("%s ", str);
        System.out.println();
    }
}

class TestServerImpl2 extends UnicastRemoteObject implements TestServer { //сервер наследует от UnicastRemoteObject
    protected TestServerImpl2() throws RemoteException {
        super();
        /*
        Конструктор класса UnicastRemoteObject экспортирует объект, чтобы сделать его доступным для приема удаленных вызовов.
        Экспорт объекта дает возможность удаленному объекту ожидать соединений с клиентами на анонимном порте
        (т.е. порте, выбираемом компьютером, на котором выполняется удаленный объект)
        http://www.intuit.ru/studies/courses/633/489/lecture/11079?page=2
        */
    }

    public void printSomething(String str) throws RemoteException {
        System.out.println(str);
    }

    public void printSomething(String str, int count) {
        for (int i = 0; i < count; i++) System.out.printf("%s ", str);
        System.out.println();
    }
}

class TestClient implements Runnable {
    private int variant;

    public TestClient(int variant) {
        this.variant = variant;
    }

    @Override
    public void run() {
        if (variant == 1) {
            try {
                String remoteServiceName = "asd"; //"rmi://localhost/BillingService";
                Registry registry = LocateRegistry.getRegistry(null, 9099); //получаем реестр (т.к. не указан URL, то поиск производится на локальном ПК)
                //если надо искать на удаленном ПК, то в getRegistry указывается URL
                TestServer server = (TestServer) registry.lookup(remoteServiceName); //ВАЖНО: приводить над к типу интерфейс, а не класса. Так не верно: ((TestServerImpl))
                server.printSomething("одно слово " + variant);
                server.printSomething(variant + "", 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ((variant == 2) || (variant == 3) || (variant == 4)) {
            try {
                String remoteServiceName = "//localhost/asd"; //почему так - смотри ниже (хотя в данном случае можно просто "asd")
                TestServer server = (TestServer) Naming.lookup(remoteServiceName); //внутри Naming.lookup происходит поиск реестра, для
                // которого вызывается родной lookup. И тут вроде как не понятно: где ищется реестр - в Naming.lookup передается только имя сервиса,
                // а URL не указывается и нет варианта Naming.lookup, в котором предусмотрен соответствующий параметр.
                // На самом деле URL шифруется в remoteServiceName. Точнее МОЖЕТ шифроваться. Если не шифруется, то поиск происходит локально.
                //Если надо зашифровать в имени URL, то имя должно иметь формат:
                //[rmi:]//хост:порт/ИмяУдаленногоОбъекта  (http://www.intuit.ru/studies/courses/633/489/lecture/11079?page=2)
                server.printSomething("одно слово " + variant);
                server.printSomething(variant + "", 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}