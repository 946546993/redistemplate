package lambda.lambdaTest;

public class lambdaTest3 {
    public static void main(String args[]) {
        String first = "";
//        Comparator<String> comparator = (first, second) ->
//                System.out.println(Integer.compare(first.length(),
//                                second.length()));
        //编译会出错
        //在Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
        //Variable 'first' is already defined in the scope
//        comparator.com("aaaaa","bb");
    }
    public interface Comparator<T> {
        void com1(String c,String d);
        void com(String a,String b);
    }
}