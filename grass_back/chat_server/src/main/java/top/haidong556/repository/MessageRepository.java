package top.haidong556.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.common.sqlsession.MySqlSessionFactory;
import top.haidong556.entity.Message;
import top.haidong556.mapper.MessageMapper;

import java.util.List;

public class MessageRepository {
    private MySqlSessionFactory sqlSessionFactory;
    private static MessageRepository instance;
    static {
        instance=new MessageRepository();
        instance.sqlSessionFactory=MySqlSessionFactory.getInstance();
    }

    public static MessageRepository getInstance(){
        return instance;
    }
    private MessageRepository(){}
    public List<Message> getAllMessagesByUserId(String userId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Message> messages = session.getMapper(MessageMapper.class).getMessagesByUserId(userId);
        return messages;
    }
    public boolean addMessage(Message message){
        SqlSession session = sqlSessionFactory.getSession(false);
        session.getMapper(MessageMapper.class).addMessage(message);
        return true;
    }
    public List<Message> getMessagesByConversationId(String conversationId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Message> messages = session.getMapper(MessageMapper.class).getMessagesByConversationId(conversationId);
        return messages;
    }
}
