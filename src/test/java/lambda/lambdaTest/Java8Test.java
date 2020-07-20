package lambda.lambdaTest;

import java.util.ArrayList;
import java.util.List;

public class Java8Test {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.forEach(System.out::println);
    }
}
