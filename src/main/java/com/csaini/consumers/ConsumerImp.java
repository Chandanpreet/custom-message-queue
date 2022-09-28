package com.csaini.consumers;

import com.csaini.model.Message;
import com.csaini.model.Topic;
import com.csaini.service.Subscriber;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class ConsumerImp implements Consumer{
    Set<Topic> topics;
    Subscriber subscriber;
    int delay;

    public void addConsumerTopic(Topic topic) {
        topics.add(topic);
    }

    public void removeConsumerTopic(Topic topic) {
        topics.remove(topic);
    }

    public ConsumerImp(int delay) {
        topics = new HashSet<>();
        this.delay = delay;
    }

    @Override
    public void subscribeToTopic(Topic topic, int delay) {
        subscriber = new Subscriber(topic, delay);
        Thread t = new Thread(subscriber);
        t.start();
    }

    @Override
    public void showConsumedData(Topic topic) {
        TreeMap<Integer, Message> map = subscriber.getData();
        System.out.println("Consumer consumed data: ");
        for(var e : map.entrySet())  {
            System.out.println(e.getKey() + ": " + e.getValue().getMessage());
        }
    }
}
