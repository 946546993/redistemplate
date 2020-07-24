package redis;

import com.lc.Starter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Starter.class)
public class StringTest {
    @Resource
    private RedisTemplate redisTemplate;
    private StringRedisTemplate stringRedisTemplate;
    //如果提示需要关闭保护模式，再redis conf 文件中设置protocd mode no ，注释掉默认的ip地址，已经关闭防火墙，开启防火墙的端口号为：6379（你默认的redis端口号） 。如果还是提示报错，就使用：
    //./redis-server ../redis conf &    就是使用配置文件启动，并在window下使用
    //telnet 192.168.241.132（你的虚拟机ip地址） 6379（你的redis端口号）
    //进行测试是否可以正常连接
    /**
     * String类型的设置和取值
     * 偏移量：你从底几位开始设置值
     */
    @Test
    public void StringTest1() {
        //默认从第0位开始设置
        redisTemplate.opsForValue().set("name", "张三");
        System.out.println(redisTemplate.opsForValue().get("name"));

        //设置偏移量，说白了就是从第几位元素开始设置
        redisTemplate.opsForValue().set("key", "hello world");
        redisTemplate.opsForValue().set("key", "redis", 6);
        System.out.println("设置偏移量" + redisTemplate.opsForValue().get("key"));

    }


    /**
     * append和setAndGet
     */
    @Test
    public void StringTest2() {
        //getAndSet设置键的字符串值并返回其旧值
        System.out.println(redisTemplate.opsForValue().getAndSet("name2", "王五"));

        //Append 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾.
        // 如果键不存在，则它被创建并设置为空字符串，因此APPEND在这种特殊情况下将类似于SET。
        redisTemplate.opsForValue().append("name3", "1");
        System.out.println(redisTemplate.opsForValue().get("name3"));

        //自增1
        redisTemplate.opsForValue().increment("name",1);
        //自减1
        redisTemplate.opsForValue().increment("name",-1);
        //查看当前的key是什么类型
        redisTemplate.type("name");
    }

    /**
     * 设置过期时间
     */
    @Test
    public void StringTest3() {
        //设置的是10秒失效，十秒之内查询有结果，十秒之后返回为null，也可以设置小时，月，年等。
        //如果没有设置有效期，即使内存用完，redis 自动回收机制也是看设置了有效期的，
        // 不会动没有设定有效期的，如果清理后内存还是满的，就不再接受写操作。

        //针对String类型进行的set重写，设置过期时间方法
        redisTemplate.opsForValue().set("name4", "name4", 5, TimeUnit.SECONDS);

        //使用expire进行设置过期时间
        redisTemplate.expire("name4",10, TimeUnit.SECONDS);
    }

    /**
     * 获取元素长度
     */
    @Test
    public void StringTest4(){
        System.out.println(redisTemplate.opsForValue().get("name"));
        //如果元素不是key-value形式的，则报错，如果键不存在则返回0
        System.out.println(redisTemplate.opsForValue().size("name"));
    }

    /**
     * 元素删除
     */
    @Test
    public void StringTest5(){
        redisTemplate.delete("name");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }


}
