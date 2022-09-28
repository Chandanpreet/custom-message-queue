package com.csaini.service;

import com.csaini.model.Message;
import com.csaini.model.Topic;
import com.csaini.consumers.Consumer;
import com.csaini.producers.Producer;

public class Broker {

    volatile Producer producer;
    volatile Consumer consumer;
    volatile Topic topic;
    volatile static Broker INSTANCE;

    public static Broker getInstance() {
        if (INSTANCE == null) {
            synchronized (Broker.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Broker();
                }
            }
        }
        return INSTANCE;
    }

    private Broker() {}

    public void registerProducer(Producer producer) {
        this.producer = producer;
    }

    public void deRegisterProducer(Producer producer) {
        this.producer = null;
    }

    public void subscribeConsumerToTopic(Consumer consumer, Topic topic) {
        this.consumer = consumer;
        this.topic = topic;
    }

    public void addTopic(Topic topic) {
        this.topic  = topic;
    }

    public void pushToTopic(Message message) {
        this.topic.pushToTopic(message);
    }

}
