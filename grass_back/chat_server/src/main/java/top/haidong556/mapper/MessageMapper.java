package top.haidong556.mapper;

import top.haidong556.entity.Message;

import java.util.List;

public interface MessageMapper {
public Message getMessageById();
public void addMessage(Message message);
public List<Message> getMessagesByConversationId(String conversationId);
public List<Message> getMessagesByUserId(String userId);

}
