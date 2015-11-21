package myTests.Reflaction.Test1;

import java.lang.reflect.*;

/**
 * Created by Valk on 27.03.15.
 */
public class GenericTest {
    public static void main(String args[]) throws IllegalAccessException, InstantiationException {
        GenericClass<B> gc = new GenericClass<B>()  { /*��� ��������� ����� - ��������� GenericClass*/ } ;

        Class<?> gcClass = gc.getClass();   //�������� ����� �������.
                                            //����� <?>: ���������� ������ Class:
                                            //public final class Class<T>. ������� ? � Class<?>
        Type type = gcClass.getGenericSuperclass();     //getGenericSuperclass() ������ �����, ������� ����� �����������
                                                        //ParameterizedType ��� Class, ������� ��� ���������� Type -
                                                        //������� ��������� Type type, �.�. Type - ������� ����� (���������)
                                                        //� ��� ParameterizedType, � ��� Class

        if (type instanceof ParameterizedType) {        //�������� �����, ��������� ParameterizedType, ���� new GenericClass<B>() - ������� (1)
            System.out.println(type);       //���������: GenericClass<B>. �.�. � ��� ���������� ����� (�). ���� ��������� - ��������� ParameterizedType
            ParameterizedType ptype = (ParameterizedType) type; //�������� � ParameterizedType ��� ������� � �������,
                                            // ������������� � ParameterizedType, ������� ��� � Type
            Type typeArg = ptype.getActualTypeArguments()[0];   //�������� ��� ��������� ������
            System.out.println(typeArg);    //���������: B
            B b = (B)((Class) typeArg).newInstance(); //������ ����� ������� ������ ������ �
            System.out.println(b);
            //�� �� ����� , �� ������:
            B b2 = (B) ((Class)((ParameterizedType)gc.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            System.out.println(b2);
            //���
            @SuppressWarnings("unchecked")
            B b3 = ((Class<B>)((ParameterizedType)gc.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            System.out.println(b3);
        }
        else {                                      //�������� �����, ��������� Class, ���� new GenericClass<B>() - ������� (2)
            System.out.println(type);       //���������: GenericClass. �.�. ��� �������� ���� � ��������� ������. ���� ��������� - ��������� Class
        }
        //
    }
}


class A{}
class B extends A{ public String toString(){return "B";}}
class C extends B{}

class GenericClass<T extends A> {}
class SubGenericClass<T extends A> extends GenericClass{}

