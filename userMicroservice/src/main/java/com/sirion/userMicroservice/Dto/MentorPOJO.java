package com.sirion.userMicroservice.Dto;

public class MentorPOJO {

    String username;


    public MentorPOJO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
