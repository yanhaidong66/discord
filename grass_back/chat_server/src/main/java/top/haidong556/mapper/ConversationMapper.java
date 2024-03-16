package top.haidong556.mapper;

import top.haidong556.entity.Conversation;

import java.util.List;

public interface ConversationMapper {
    public void addConversation(Conversation conversation);
    public List<Conversation> getConversationsByUserId(String userId);
}
