package top.haidong556.common.redis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.*;

class JedisPoolTest {
    private static JedisPool jedisPool;
    @BeforeAll
    static void beforeAll(){
        jedisPool = JedisPool.getInstance();

    }

    @Test
    @DisplayName("test get Jedis")
    void getJedis() {
        Jedis j= jedisPool.getJedis();
        j.set("aaa","jjj");
        String t=j.get("aaa");
        assertEquals("jjj",t);
        System.out.println(t);
    }
}