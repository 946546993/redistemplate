package lambda.lambdaTest;

public class JavaTest2 {
    public static void main(String[] args) {
        /**
         * 函数式接口(FunctionalInterface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。
         *
         * * 接口有且仅有一个抽象方法
         *
         * * 允许定义静态方法
         *
         * * 允许定义默认方法
         *
         * * 允许java.lang.Object中的public方法
         */
    }
    // 正确的函数式接口
    @FunctionalInterface
    public interface TestInterface {
        // 抽象方法
         void sub();
        // java.lang.Object中的public方法
        boolean equals(Object var1);
        // 默认方法
        default void defaultMethod(){
        }
        // 静态方法
         static void staticMethod(){
        }
    }
    // 错误的函数式接口(有多个抽象方法)
//    @FunctionalInterface
//    public interface TestInterface2 {
//        void add();
//        void sub();
//    }


}
