package top.haidong.oauth.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
public class MyJedisFactoryTest {
    @Test
    public void jedis(){
        Jedis jedis= MyJedisFactory.getJedis();
        jedis.set("test","context");
        Assertions.assertEquals("context",jedis.get("test"));
    }
}
