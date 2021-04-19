package com.example.hakimlivs.models;

import lombok.Data;

public @Data class Message {
    private boolean success;
    private String message;

    public Message(){}

    public Message(boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
