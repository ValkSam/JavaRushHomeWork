package myTests.ClassesTest;

/*
 http://habrahabr.ru/post/62602/ - о видмости методов пр и разных схемах
 */
public class ClassesTest {
    // внтренний и вложенный классы ...
    //http://easy-code.ru/lesson/java-nested-classes
    //http://java-study.ru/samouchitel/32-metods
    int f1 = 1;
    static int f2 = 1;

    public class InnerClass{
       int getF1(){
            return f1;
        }
    }
    public static class NestedClass{
        int getF2(){
            return f2;
        }
    }
    //... внтренний и вложенный классы

    public Double sum(Double a, Double b){
        return (a+b);
    }
    static Double sum1(Double a, Double b){
        return (a+b);
    }

    public static void main(String[] arr){
        System.out.println(new ClassesTest().sum(1.0,2.2));
        System.out.println(sum1(2.0, 2.2));
    }
}
