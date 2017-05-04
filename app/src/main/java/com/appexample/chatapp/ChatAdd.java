package com.appexample.chatapp;


public class ChatAdd {

    private String message;
    private String id;

    public ChatAdd() {
    }

    public ChatAdd(String message, String id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

