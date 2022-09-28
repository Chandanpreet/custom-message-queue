package com.csaini;

import com.csaini.consumers.Consumer;
import com.csaini.consumers.ConsumerImp;
import com.csaini.model.Topic;
import com.csaini.producers.Producer;
import com.csaini.producers.ProducerImp;
import com.csaini.service.Broker;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Broker broker = Broker.getInstance();

        Topic topicA = new Topic("A");
        broker.addTopic(topicA);
        Producer producerA = new ProducerImp();
        producerA.produce(topicA, "Hello");
        Consumer consumer = new ConsumerImp(2000);
        consumer.subscribeToTopic(topicA, 2000);    // consumer polls after 2sec

        for (int i=0; i<10; i++) {
            producerA.produce(topicA, "Hello " + i);
            Thread.sleep(1000);                     // producer sends 1 event every sec
        }

        consumer.showConsumedData(topicA);
    }
}
