package top.haidong556.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import top.haidong556.common.redis.JedisPool;
import top.haidong556.entity.Message;

public class RedisRepository {

    public static boolean writeMessage(Message message) throws JsonProcessingException {
        JedisPool jedisPool=JedisPool.getInstance();
        Jedis jedis = jedisPool.getJedis();
        // key= 数据库名：表名：主键id
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(message);
        String key="db_chat:"+"t_message:"+message.getMessageId();
        jedis.set(key,value);
        jedis.close();
        return true;
    }
    public static Message getMessage(long messageId) throws JsonProcessingException {
        String key="db_chat:"+"t_message:"+messageId;
        Jedis jedis = JedisPool.getInstance().getJedis();
        String value = jedis.get(key);
        if(value==null){
            return null;
        }
        ObjectMapper objectMapper=new ObjectMapper();
        Message message = objectMapper.readValue(value, Message.class);
        return message;

    }

}
