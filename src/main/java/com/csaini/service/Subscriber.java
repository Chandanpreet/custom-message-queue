package com.csaini.service;

import com.csaini.model.Message;
import com.csaini.model.Topic;

import java.util.Collections;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Subscriber implements Runnable{
    private final Broker broker;
    private Topic topic;
    private final TreeMap<Integer, Message> messageMap;
    private final int delay;
    private volatile AtomicInteger lastKey;

    public Subscriber(Topic topic, int delay) {
        broker = Broker.getInstance();
        messageMap = new TreeMap<>(Collections.reverseOrder());
        this.topic = topic;
        this.delay = delay;
        lastKey = new AtomicInteger(0);
    }

    @Override
    public void run() {
        while(true) {
            int topicOffset = topic.getEndOffset();
            if (topicOffset>0  && (messageMap.isEmpty() || lastKey.get() < topicOffset)) {
                System.out.println("Consumer consumed data (live): ");
                while (topicOffset != lastKey.get()) {
                    lastKey.incrementAndGet();
                    messageMap.put(lastKey.get(), topic.getOffsetData(lastKey.get()));
                    System.out.println(messageMap.get(lastKey.get()).getMessage());
                }
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public TreeMap<Integer, Message> getData() {
        return messageMap;
    }
}
