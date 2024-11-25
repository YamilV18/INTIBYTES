package com.example.mschatsocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user;
    private String message;
    private long timestamp;
    private String roomId;

    public Message() {}

    public Message(String user, String message, long timestamp, String roomId) {
        this.user = user;
        this.message = message;
        this.timestamp = timestamp;
        this.roomId = roomId;
    }
    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }
}
