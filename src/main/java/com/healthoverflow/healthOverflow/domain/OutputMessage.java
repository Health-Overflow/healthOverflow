package com.healthoverflow.healthOverflow.domain;

public class OutputMessage {
    private String userName;
    private String userImage;
    private String userMessage;

    public OutputMessage() {
    }

    public OutputMessage(String userName, String userImage, String userMessage) {
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
