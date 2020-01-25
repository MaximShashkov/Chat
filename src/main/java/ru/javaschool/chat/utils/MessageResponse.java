package ru.javaschool.chat.utils;

import java.util.ArrayList;
import java.util.List;

//возращается после sendMessage
public class MessageResponse {
    private List<MessageStatus> status=new ArrayList<>();
    private long messageID;

    public void setStatus(List<MessageStatus> status) {
        this.status = status;
    }

    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }
}
