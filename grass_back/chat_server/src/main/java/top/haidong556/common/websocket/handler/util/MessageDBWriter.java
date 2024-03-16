package top.haidong556.common.websocket.handler.util;

import redis.clients.jedis.Jedis;
import top.haidong556.common.redis.JedisPool;
import top.haidong556.entity.Message;
import top.haidong556.repository.MessageRepository;

public class MessageDBWriter {

    private JedisPool jedisPool;
    private MessageRepository messageRepository;

    MessageDBWriter(){
        jedisPool = JedisPool.getInstance();
        messageRepository=MessageRepository.getInstance();
    }

    public boolean writeMessage(Message message){

        Jedis jedis = jedisPool.getJedis();


        return true;
    }


}
