package com.sirion.searchMicroservice.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.util.Date;


@Entity
@DynamicInsert
@Table(name = "MentorsTable")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    String username;

    @Column(columnDefinition = "timestamp without time zone default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date regDate;

    @Column(columnDefinition = "bigint default 0")
    long yearsOfExperience;

    @Column(columnDefinition = "boolean default true")
    boolean active;


    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(long yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
