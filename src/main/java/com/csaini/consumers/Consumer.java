package com.csaini.consumers;

import com.csaini.model.Topic;

public interface Consumer {
    void subscribeToTopic(Topic topic, int delay);
    void showConsumedData(Topic topic);
}
