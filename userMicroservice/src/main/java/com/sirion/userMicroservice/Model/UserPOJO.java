package com.sirion.userMicroservice.Model;

public class UserPOJO{

    String username;
    String firstname;
    String lastname;
    String lORm;


    public UserPOJO(String username, String firstname, String lastname, String lORm) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lORm = lORm;
    }

    public UserPOJO(User user) {
        this.username = user.getUsername();
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.lORm = user.getlORm();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getlORm() {
        return lORm;
    }

    public void setlORm(String lORm) {
        this.lORm = lORm;
    }
}
