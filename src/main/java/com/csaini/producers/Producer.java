package com.csaini.producers;

import com.csaini.model.Topic;

public interface Producer {
    void produce(Topic topic, String event);
}
