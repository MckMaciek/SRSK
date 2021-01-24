package com.example.SRSK.model;

import java.util.Date;

public class ChatMessage {

    private String value;
    private String user;
    private String data;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public ChatMessage(String value, String user, String data) {
        this.value = value;
        this.user = user;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ChatMessage() {
    }
}
