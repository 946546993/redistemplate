package lambda.lambdaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "as", "af");
        List<String> filtered = strings.stream().filter(string ->
                !strings.isEmpty()).collect(Collectors.toList());

        //使用流的方式，随机打印10个数
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        List<Integer> number = Arrays.asList(3,2,3,2,7,3,5);
        //获取对应数的平方
        //map 方法用于映射每个元素到对应的结果，
        // 以下代码片段使用 map 输出了元素对应的平方数：
        List<Integer> squaresList = number.stream().map(s -> s * s).
                distinct().collect(Collectors.toList());

        //filter 方法用于通过设置条件过滤出元素。
        // 以下代码片段使用filter 方法过滤出空字符串
        List<String> emptyStrings = Arrays.asList("","as","","vas","");
        int count =(int) emptyStrings.stream().filter(s -> s.isEmpty()).count();

        //sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对集合进行排序：
        //sorted(Comparator<>) 可以自定义一个比较器，规定如何排序
        List<Integer> sort = Arrays.asList(1,4,2,3);
        sort.stream().sorted().forEach(System.out::println);

        //parallelStream 是流并行处理程序的代替方法,
        // 可以理解为parallelStream为双管道,Stream为单管道，
        // 在效率上parallelStream更快
        List<Integer> parallel = Arrays.asList(3,23,4,12,123,4,5,4);
        long count1 = parallel.parallelStream().filter(integer -> integer < 100).count();

        //Collectors 类实现了很多归约操作，
        //例如将流转换成集合和聚合元素。Collectors可用于返回列表或字符串：
        List<String> collectorsList = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered1 =
                collectorsList.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered1);
        String mergedString =
                strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(""));
        System.out.println("合并字符串: " + mergedString);

    }
}
