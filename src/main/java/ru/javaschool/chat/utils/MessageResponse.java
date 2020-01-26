package ru.javaschool.chat.utils;

import java.util.ArrayList;
import java.util.List;

//возращается после sendMessage
public class MessageResponse {
    private List<MessageStatus> statuses = new ArrayList<>();
    private long messageID;

    public void setStatuses(List<MessageStatus> status) {
        this.statuses = status;
    }

    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }

    public List<MessageStatus> getStatuses() {
        return statuses;
    }

    public long getMessageID() {
        return messageID;
    }
}
