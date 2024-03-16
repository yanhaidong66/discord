package top.haidong556.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.haidong556.entity.Message;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {
    private static MessageRepository messageRepository;
    @BeforeAll
    static void init(){
        messageRepository=MessageRepository.getInstance();
    }


    @Test
    void testGetAllMessagesByUserId() {
        List<Message> messageList=messageRepository.getAllMessagesByUserId("1");
        for(Message m:messageList){
            System.out.println(m);
        }
    }

    @Test
    void addMessage() {
        Message m=new Message(1,1, System.currentTimeMillis(),1,2,"1 to 2 message",true);
        messageRepository.addMessage(m);
    }

    @Test
    void getMessagesByConversationId() {
        List<Message> messageList=messageRepository.getMessagesByConversationId("1");
        for(Message m:messageList){
            System.out.println(m);
        }

    }
}