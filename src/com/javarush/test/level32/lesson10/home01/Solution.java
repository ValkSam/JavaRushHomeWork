package com.javarush.test.level32.lesson10.home01;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/* К серверу по RMI
Реализуйте логику метода run в CLIENT_THREAD. В нем будет имитироваться клиентская часть, которая коннектится к серверу.
1) Из registry получите сервис с именем UNIC_BINDING_NAME
2) Вызовите метод у полученного сервиса, передайте любой непустой аргумент
3) Выведите в консоль результат вызова метода
4) Обработайте исключения
Метод main не участвует в тестировании
*/
public class Solution {
    public static final String UNIC_BINDING_NAME = "double.string";
    public static Registry registry;

    //pretend we start rmi client as CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                //Registry registry = LocateRegistry.getRegistry(2099); //можно локальный реестр получить. Если с другой машины, то надо его создать
                //Registry registry = LocateRegistry.getRegistry("localhost", 2099); или так
                DoubleString service = (DoubleString)registry.lookup(UNIC_BINDING_NAME);
                System.out.println(service.doubleString("asd-"));
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }

        }
    });

    public static void main(String[] args) {
        //pretend we start rmi server as main thread
        Remote stub = null;
        final DoubleStringImpl service = new DoubleStringImpl();
        try {
            registry = LocateRegistry.createRegistry(2099);
            stub = UnicastRemoteObject.exportObject(service, 0);
            registry.bind(UNIC_BINDING_NAME, stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        //start client
        CLIENT_THREAD.start();
        try {Thread.sleep(1000);} catch (InterruptedException e){}
        try {
            UnicastRemoteObject.unexportObject(service, false);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }
}
