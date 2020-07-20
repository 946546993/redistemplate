package redis;

import com.lc.Starter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class Settest {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * Long add(K key, V... values);
     * Set<V> members(K key);
     */
    @Test
    public void setTest() {
        //无序集合中添加元素，返回添加个数.也可以直接在add里面添加多个值
        //具有set 的特性
        System.out.println(redisTemplate.opsForSet().add("set1", "aaa", "bbb"));

        //返回集合中的所有成员
        System.out.println(redisTemplate.opsForSet().members("set1"));
    }

    /**
     *  Long remove(K key, Object... values);
     *  V pop(K key);
     */
    @Test
    public void setTest2() {
        //移除集合中一个或多个成员
        redisTemplate.opsForSet().remove("set1", "aaa");
        System.out.println("移除aaa：" + redisTemplate.opsForSet().members("set1"));


        //移除并返回集合中的一个随机元素（就是随机删除一个元素，然后把这个删除的元素进行展示）
        System.out.println("移除了："+redisTemplate.opsForSet().pop("set1"));
    }

    /**
     * Boolean move(K key, V value, K destKey);
     */
    @Test
    public void setTest3(){
        //将 member 元素从 source 集合移动到 destination 集合（setTest到setTest2）
       redisTemplate.opsForSet().move("set1","bbb","set2");
        System.out.println("set1:"+redisTemplate.opsForSet().members("set1"));
        System.out.println("set2:"+redisTemplate.opsForSet().members("set2"));
    }

    /**
     * Cursor<V> scan(K key, ScanOptions options);
     */
    @Test
    public void setTest4(){
        //遍历set
        Cursor<Set<Object>> scan = redisTemplate.opsForSet().scan("set1", ScanOptions.NONE);
        while(scan.hasNext()){
            System.out.println(scan.next());
        }
    }
}
