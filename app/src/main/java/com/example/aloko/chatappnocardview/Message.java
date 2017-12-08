package com.example.aloko.chatappnocardview;

/**
 * Created by aloko on 9.07.2017.
 */

public class Message {
    private String messageText;
    private String sender;
    private String time;

    public Message() {
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
