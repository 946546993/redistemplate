package redis;

import com.lc.Starter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class ZSetTest {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * Boolean add(K key, V value, double score);
     *  Long add(K key, Set<TypedTuple<V>> tuples);
     */
    @Test
    public void ZSetTest1(){
        //新增一个有序集合，存在的话为false，不存在的话为true
        System.out.println(redisTemplate.opsForZSet().add("zset1","zset-1",1.0));

        //新增一个有序集合
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
    }


    /**
     *  Long rank(K key, Object o);
     *  Set<V> range(K key, long start, long end);
     *  Set<V> range(K key, long min, long max);
     *  Long count(K key, double min, double max);
     *  Double score(K key, Object o);
     */
    @Test
    public void ZSetTest2(){
        //通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));

        //返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().rank("zset1","zset-2"));

        //获取分数段区间内的元素
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5));

        //通过分数返回有序集合指定区间内的成员个数
        System.out.println(redisTemplate.opsForZSet().count("zset1",0,5));

        //获取指定成员的score值
        System.out.println(redisTemplate.opsForZSet().score("zset1","zset-1"));

    }


    /**
     * Long remove(K key, Object... values);
     * Long removeRange(K key, long start, long end);
     */
    @Test
    public void ZSetTest3(){
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        //从有序集合中移除一个或者多个元素
        System.out.println(redisTemplate.opsForZSet().remove("zset1","zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));

        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
        //移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
        System.out.println(redisTemplate.opsForZSet().removeRange("zset2",1,2));
        System.out.println(redisTemplate.opsForZSet().range("zset2",0,-1));
    }

    /**
     * Cursor<TypedTuple<V>> scan(K key, ScanOptions options);
     */
    @Test
    public void ZSetTest4(){
        //遍历
        //ZSetOperations.TypedTuple就是redis 的有序集合
        Cursor<ZSetOperations.TypedTuple<Object>> scan = redisTemplate.opsForZSet().scan("zset1", ScanOptions.NONE);
        while(scan.hasNext()){
            System.out.println(scan.next());
        }

    }

}
