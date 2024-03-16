package top.haidong556.common.websocket.handler.util;

import top.haidong556.repository.MessageRepository;

public class UserMessagePuller {
    private MessageRepository messageRepository;
    public UserMessagePuller(){
        messageRepository=MessageRepository.getInstance();
    }
}
