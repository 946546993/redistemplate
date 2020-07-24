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
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class HashTest {

    @Resource
    private RedisTemplate redisTemplate;



    /**
     * void put(H key, HK hashKey, HV value);
     * void putAll(H key, Map<? extends HK, ? extends HV> m);
     *  Map<HK, HV> entries(H key);
     */
    @Test
    public void hashTest1() {

        //设置散列hashKey的值
        redisTemplate.opsForHash().put("hash1", "name", "张三");

        Map<String, Object> map = new HashMap<>();
        map.put("sex", "男");
        map.put("age", "1");
        //使用m中提供的多个散列字段设置到key对应的散列表中
        redisTemplate.opsForHash().putAll("hash1", map);

        //entries(H key) 获取所有的值
        System.out.println(redisTemplate.opsForHash().entries("hash1"));
    }


    /**
     * Set<HV> keys(H key)
     * List<HV> values(H key);
     * HashValues get(H key,Object hashkey);
     */
    @Test
    public void hashTest2() {
        //获取key所对应的散列表的key    即获取所有的key
        System.out.println("所有的key" + redisTemplate.opsForHash().keys("hash1"));

        //获取整个哈希存储的值根据密钥   即获取所有key对应的values
        System.out.println("所有的value" + redisTemplate.opsForHash().values("hash1"));

        //从键中的哈希获取给定hashKey的值
        System.out.println("从hash1中取出key为name的value" + redisTemplate.opsForHash().get("hash1", "name"));
    }

    /**
     * Boolean hashKey(H Key , Object hashKey);
     * Long delete(H Key,Object ...HashKeys);
     * Long size(H key);
     */
    @Test
    public void hashTest3() {

        //确定哈希hashKey是否存在
        System.out.println(redisTemplate.opsForHash().hasKey("hash1", "name"));

        System.out.println("删除前：" + redisTemplate.opsForHash().entries("hash1"));
        //删除给定的哈希hashKeys    也可以传入一个数组
        redisTemplate.opsForHash().delete("hash1","sex");
        System.out.println("删除后："+redisTemplate.opsForHash().entries("hash1"));

        //获取指定hashkey 所对应的散列表的大小长度
        System.out.println(redisTemplate.opsForHash().size("hash1"));
    }


    /**
     * Cursor<Map.Entry<HK, HV>>   scan(H key, ScanOptions options);
     */
    @Test
    public void hashTest4(){
        //使用Cursor在key的hash中迭代，相当于迭代器。
        Cursor<Map.Entry<Object,Object>> scan = redisTemplate.opsForHash().scan("hash1", ScanOptions.NONE);
        while(scan.hasNext()){
            Map.Entry<Object, Object> next = scan.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
    }

}
