package com.sirion.trainingMicroservice.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@Table(name = "TrainingTable")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    long userId;

    @Column
    long mentorId;

    @Column
    long skillId;

    @Column(columnDefinition = "real default 2.5")
    float rating;

    @Column(columnDefinition = "timestamp without time zone default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date startDate;

    @Column(columnDefinition = "timestamp without time zone default current_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date endDate;

    //approved--true
    @Column(columnDefinition = "boolean default false")
    boolean approved;

    //completed--false
    @Column(columnDefinition = "boolean default true")
    boolean underProgress;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMentorId() {
        return mentorId;
    }

    public void setMentorId(long mentorId) {
        this.mentorId = mentorId;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isUnderProgress() {
        return underProgress;
    }

    public void setUnderProgress(boolean underProgress) {
        this.underProgress = underProgress;
    }
}
