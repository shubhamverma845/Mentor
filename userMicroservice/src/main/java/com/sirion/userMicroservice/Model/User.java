package com.sirion.userMicroservice.Model;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@DynamicInsert
@Table(name = "UserTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column
    String password;

    @NotNull
    @Column
    String firstName;

    @NotNull
    @Column
    String username;

    @NotNull
    @Column
    String lastName;

    @ColumnDefault("true")
    boolean active;

    @NotNull
    @Column
    String lORm;

    public User() {
    }

    public User(@NotNull String password,
                @NotNull String firstName,
                @NotNull String username,
                @NotNull String lastName,
                @NotNull String lORm) {
        this.password = password;
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.lORm = lORm;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getlORm() {
        return lORm;
    }

    public String getRole(){
        return this.getlORm().equals("m") ? "MENTOR":"LEARNER";
    }

    public void setlORm(String lORm) {
        this.lORm = lORm;
    }
}