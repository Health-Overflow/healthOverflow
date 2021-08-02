package com.healthoverflow.healthOverflow.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class UserMsg {
    private String userName;
    private String userImage;
    private String userMessage;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    public UserMsg() {
    }

    public UserMsg(String userName, String userImage, String userMessage) {
        this.userName = userName;
        this.userImage = userImage;
        this.userMessage = userMessage;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUserMessage() {
        return userMessage;
    }

//    public Long getId() {
//        return id;
//    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
