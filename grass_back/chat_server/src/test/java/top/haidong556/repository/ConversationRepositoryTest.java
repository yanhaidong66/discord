package top.haidong556.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import top.haidong556.entity.Conversation;

import static org.junit.jupiter.api.Assertions.*;

class ConversationRepositoryTest {
    private static ConversationRepository conversationRepository;
    @BeforeAll
    static void init(){
        conversationRepository=ConversationRepository.getInstance();
    }

    @Test
    void addConversation() {
        Conversation c=new Conversation(1,1L,System.currentTimeMillis(),System.currentTimeMillis()+11);
        conversationRepository.addConversation(c);
    }

    @Test
    void getConversationsByUserId() {
    }
}