package ru.javaschool.chat.utils;

//Такой объект поступает в реквесте
public class MessageRequest {
    private long authorID;
    private long chatID;
    private String textMessage;

    public long getAuthorID() {
        return authorID;
    }

    public long getChatID() {
        return chatID;
    }

    public String getTextMessage() {
        return textMessage;
    }
}
