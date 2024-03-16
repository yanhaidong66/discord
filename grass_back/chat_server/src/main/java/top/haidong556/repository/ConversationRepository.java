package top.haidong556.repository;

import org.apache.ibatis.session.SqlSession;
import top.haidong556.common.sqlsession.MySqlSessionFactory;
import top.haidong556.entity.Conversation;
import top.haidong556.mapper.ConversationMapper;

import java.util.List;

public class ConversationRepository {
    private MySqlSessionFactory sqlSessionFactory;
    private static ConversationRepository instance;
    static {
        instance=new ConversationRepository();
        instance.sqlSessionFactory=MySqlSessionFactory.getInstance();
    }
    private ConversationRepository(){}

    public static ConversationRepository getInstance(){
        return instance;
    }

    public boolean addConversation(Conversation conversation){
        SqlSession session = sqlSessionFactory.getSession(false);
        session.getMapper(ConversationMapper.class).addConversation(conversation);
        session.commit();
        return true;
    }
    public List<Conversation> getConversationsByUserId(String userId){
        SqlSession session = sqlSessionFactory.getSession(false);
        List<Conversation> conversations = session.getMapper(ConversationMapper.class).getConversationsByUserId(userId);
        return conversations;
    }

}
