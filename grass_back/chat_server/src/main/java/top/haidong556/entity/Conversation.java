package top.haidong556.entity;

import java.util.Date;

public class Conversation {
    private long conversationId;
    private long conversationUserId;
    private long conversationCreateTime;
    private long conversationModifyTime;

    public Conversation(){}

    public long getConversationId() {
        return conversationId;
    }

    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }

    public long getConversationUserId() {
        return conversationUserId;
    }

    public void setConversationUserId(long conversationUserId) {
        this.conversationUserId = conversationUserId;
    }

    public long getConversationCreateTime() {
        return conversationCreateTime;
    }

    public void setConversationCreateTime(long conversationCreateTime) {
        this.conversationCreateTime = conversationCreateTime;
    }

    public long getConversationModifyTime() {
        return conversationModifyTime;
    }

    public void setConversationModifyTime(long conversationModifyTime) {
        this.conversationModifyTime = conversationModifyTime;
    }

    public Conversation(long conversationId, long conversationUserId, long conversationCreateTime, long conversationModifyTime) {
        this.conversationId = conversationId;
        this.conversationUserId = conversationUserId;
        this.conversationCreateTime = conversationCreateTime;
        this.conversationModifyTime = conversationModifyTime;
    }




}
