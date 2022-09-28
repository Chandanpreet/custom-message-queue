package com.csaini.producers;

import com.csaini.model.Message;
import com.csaini.model.Topic;
import com.csaini.service.Broker;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerImp implements Producer{
    Broker broker;
    Set<Topic> topics;
    AtomicInteger i;

    public ProducerImp() {
        topics = new HashSet<>();
        broker = Broker.getInstance();
        i = new AtomicInteger(0);
    }

    public void addProducerTopic(Topic topic) {
        topics.add(topic);
    }

    public void removeProducerTopic(Topic topic) {
        topics.remove(topic);
    }

    @Override
    public void produce(Topic topic, String event) {
        Message m = new Message(i.incrementAndGet(), event);
        broker.pushToTopic(m);
        System.out.println("Producer pushed message: "  + m.getMessage());
    }
}
