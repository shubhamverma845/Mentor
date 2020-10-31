package com.sirion.userMicroservice.Dto;

public class UserDto {

    String firstName;
    String lastName;
    String username;
    String password;
    String lORm;
    String contactNo;


    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String username, String password, String lORm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.lORm = lORm;
    }


    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getlORm() {
        return lORm;
    }

    public void setlORm(String lORm) {
        this.lORm = lORm;
    }
}
