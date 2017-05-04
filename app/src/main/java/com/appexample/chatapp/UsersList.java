package com.appexample.chatapp;


public class UsersList {

    public String Users;
    private String LastChat;
    private String Time;
    public int img_1;
    public int img_2;

    public UsersList(){

    }

    public UsersList(String Users, String LastChat, String Time, int img_1, int img_2) {
        this.Users = Users;
        this.LastChat = LastChat;
        this.Time = Time;
        this.img_1 = img_1;
        this.img_2 = img_2;
    }

    public String getUsers() {
        return Users;
    }

    public String getLastChat() {
        return LastChat;
    }

    public String getTime() {
        return Time;
    }
}
