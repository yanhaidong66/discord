package top.haidong556.entity;

import java.util.Date;

public class Message {
    private long messageId;
    private long messageConversationId;
    private long messageCreateTime;
    private long messageSenderId;
    private long messageReceiverId;
    private String MessageContent

;
    private boolean messageRead;

    public Message(){};

    public Message(long messageId, long messageConversationId, long messageCreateTime, long messageSenderId, long messageReceiverId, String MessageContent

, boolean messageRead) {
        this.messageId = messageId;
        this.messageConversationId = messageConversationId;
        this.messageCreateTime = messageCreateTime;
        this.messageSenderId = messageSenderId;
        this.messageReceiverId = messageReceiverId;
        this.MessageContent = MessageContent;
        this.messageRead = messageRead;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getMessageConversationId() {
        return messageConversationId;
    }

    public void setMessageConversationId(long messageConversationId) {
        this.messageConversationId = messageConversationId;
    }

    public long getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(long messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public long getMessageSenderId() {
        return messageSenderId;
    }

    public void setMessageSenderId(long messageSenderId) {
        this.messageSenderId = messageSenderId;
    }

    public long getMessageReceiverId() {
        return messageReceiverId;
    }

    public void setMessageReceiverId(long messageReceiverId) {
        this.messageReceiverId = messageReceiverId;
    }

    public String getMessageContent() {
        return MessageContent

;
    }

    public void setMessageContent(String MessageContent) {
        this.MessageContent = MessageContent

;
    }

    public boolean isMessageRead() {
        return messageRead;
    }

    public void setMessageRead(boolean messageRead) {
        this.messageRead = messageRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageConversationId=" + messageConversationId +
                ", messageCreateTime=" + messageCreateTime +
                ", messageSenderId=" + messageSenderId +
                ", messageReceiverId=" + messageReceiverId +
                ", MessageContent='" + MessageContent + '\'' +
                ", messageRead=" + messageRead +
                '}';
    }
}
