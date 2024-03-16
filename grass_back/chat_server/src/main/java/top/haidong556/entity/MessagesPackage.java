package top.haidong556.entity;

import java.util.List;

public class MessagesPackage {
    private List<Message> messages;
    private int messageNum;
    private long timestamp;
    private String type;

    public MessagesPackage(){}

    @Override
    public String toString() {
        return "MessagesPackage" +
                "\n"+"{" +
                "\n"+"messageNum=" + messageNum +
                "\n"+", timestamp=" + timestamp +
                "\n"+", type='" + type  +
                "\n"+"messages=" + messages.toString() +
                "\n"+'}';
    }

    public MessagesPackage(List<Message> messages, int messageNum, long timestamp, String type) {
        this.messages = messages;
        this.messageNum = messageNum;
        this.timestamp = timestamp;
        this.type = type;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
