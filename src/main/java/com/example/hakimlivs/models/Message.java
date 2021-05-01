package com.example.hakimlivs.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private boolean success;
    private String message;

    public Message(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}