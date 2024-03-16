package top.haidong556.common.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.entity.Message;

import static org.junit.jupiter.api.Assertions.*;

class MessageCodecTest {
    private static Message message;
    @BeforeAll
    static void init(){
        message=new Message(1,11,System.currentTimeMillis(),1111,111111,"this is test",false);
    }


    @Test
    void encode() {
        System.out.println("编码前："+message);
        System.out.println("编码后："+MessageCodec.encode(message));

    }

    @Test
    void decode() throws InvalidProtocolBufferException {
        byte[] bytes=MessageCodec.encode(message);
        System.out.println("解码前："+bytes);
        System.out.println("解码后："+MessageCodec.decode(bytes));

    }
}