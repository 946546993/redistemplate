package lambda.lambdaTest;

import java.util.Arrays;
import java.util.List;

public class JavaTest {
    //函数式接口
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    //Supplier屎jdk1.8的接口，这里和lambda一起使用
    public static JavaTest create(final Supplier<JavaTest> supplier) {
        return supplier.get();
    }

    public static void collide(final JavaTest javaTest) {
        System.out.println("Collide" + javaTest.toString());
    }


    public void follow(final JavaTest another) {
        System.out.println("Follow the" + another.toString());
    }

    public void repair() {
        System.out.println("Repair" + this.toString());
    }

    public static void main(String[] args) {
        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        JavaTest javaTest = JavaTest.create(JavaTest::new);
        JavaTest javaTest1 = JavaTest.create(JavaTest::new);
        JavaTest javaTest2 = JavaTest.create(JavaTest::new);
        JavaTest javaTest3 = new JavaTest();

        List<JavaTest> list = Arrays.asList(javaTest, javaTest1, javaTest2, javaTest3);
        System.out.println("===================构造器引用========================");
        //静态方法引用：它的语法是Class::static_method，实例如下：
        list.forEach(JavaTest::collide);
        System.out.println("===================静态方法引用========================");
        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        list.forEach(JavaTest::repair);
        System.out.println("==============特定类的任意对象的方法引用================");
        //特定对象的方法引用：它的语法是instance::method实例如下：-
        final JavaTest police = JavaTest.create(JavaTest::new);
        list.forEach(police::follow);
        System.out.println("===================特定对象的方法引用===================");

    }
}
