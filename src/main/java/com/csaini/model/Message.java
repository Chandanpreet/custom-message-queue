package com.csaini.model;

public class Message {
    int epoch;
    String message;

    public Message(int epoch, String event) {
        this.epoch = epoch;
        this.message = event;
    }

    public String getMessage() {
        return "id: " + epoch + " message: " + message;
    }
}
