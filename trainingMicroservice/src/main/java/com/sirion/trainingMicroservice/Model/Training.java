package com.sirion.trainingMicroservice.Model;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

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

    @ColumnDefault("2.5")
    float rating;

    //approved--true
    @Column(columnDefinition = "boolean default false")
    boolean approved;

    //completed--false
    @Column(columnDefinition = "boolean default true")
    boolean underProgress;

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
