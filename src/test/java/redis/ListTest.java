package redis;

import com.lc.Starter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class ListTest {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * Long leftPush(K key, V value);（rightPush(K key,V value)）
     * List<V> range()
     */
    @Test
    public void listTest1() {
        //将所有指定的值插入存储在键的列表的头部。如果键不存在，
        // 则在执行推送操作之前将其创建为空列表。（从左边插入）返回的值是插入集合后的长度
        // 还有一个rightPush从右边插入，从列头还是列尾
        redisTemplate.opsForList().leftPush("list1", "aaa");
        redisTemplate.opsForList().leftPush("list1", "bbb");
        System.out.println(redisTemplate.opsForList().leftPush("list1", "ccc"));

        //range()是获取所有的值从第一个long开始，到第二个下标位long的值
        // （如果写>0 的值就是获取所有的值）
        System.out.println(redisTemplate.opsForList().range("list1", 0, -1));
    }

    /**
     * Long leftPushAll(K key, V... values);
     */
    @Test
    public void listTest2() {
        String[] s = {"aaa", "bbb"};
        List<String> list = new ArrayList<>();
        list.add("ccc");
        //批量把一个数组插入到列表中。还有一个rightPushAll()
        redisTemplate.opsForList().leftPushAll("list2", s);
        redisTemplate.opsForList().leftPushAll("list2", list);
        System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
    }

    /**
     * void set(K key, long index, V value);
     */
    @Test
    public void listTest3() {
        System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
        //在列表中index的位置设置value值
        redisTemplate.opsForList().set("list2", 1, "a11");
    }

    /**
     * Long remove(K key, long count, Object value);
     */
    @Test
    public void listTest4() {
        //从存储在键中的列表中删除等于值的元素的第一个计数事件。
        //计数参数以下列方式影响操作：
        //count> 0：删除等于从头到尾移动的值的元素。
        //count <0：删除等于从尾到头移动的值的元素。
        //count = 0：删除等于value的所有元素。
        System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
        redisTemplate.opsForList().remove("list2", 0, "aaa");
        System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
    }

    /**
     * V leftPop(K key);
     */
    @Test
    public void listTest5() {
        System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
        //弹出最左边的元素，弹出之后该值在列表中将不复存在，还有一个rightPop（K key）
        redisTemplate.opsForList().leftPop("list2");

        //设置5秒后自动弹出
        redisTemplate.opsForList().leftPop("list2", 5, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
    }


    /**
     * V index(K key, long index);
     */
    @Test
    public void listTest6() {
        //获取下标是index 的值
        System.out.println(redisTemplate.opsForList().index("list2", 1));
    }

    /**
     * Long size(K key)
     */
    @Test
    public void listTest7() {
        //返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。
        // 当key存储的值不是列表时返回错误。
        System.out.println(redisTemplate.opsForList().size("list2"));
    }


}
