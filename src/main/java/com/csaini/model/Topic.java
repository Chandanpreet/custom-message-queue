package com.csaini.model;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {
    String topicName;
    int id;
    HashMap<Integer, Message> topicData;
    AtomicInteger endOffset;

    public Topic(String topicName) {
        this.topicName = topicName;
        topicData = new HashMap<>();
        endOffset = new AtomicInteger(0);
    }

    public int getId() {
        return id;
    }

    public void pushToTopic(Message message) {
        topicData.put(endOffset.incrementAndGet(), message);
    }

    public Message getOffsetData(int offset) {
        return topicData.get(offset);
    }

    public int getEndOffset() {
        return endOffset.get();
    }
}
