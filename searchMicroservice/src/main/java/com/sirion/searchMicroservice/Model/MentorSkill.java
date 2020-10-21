package com.sirion.searchMicroservice.Model;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "MentorSkillTable")
public class MentorSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long mentorId;

    @Column
    long skillId;

    @ColumnDefault("2.5")
    float rating;

    @ColumnDefault("0")
    long trainingsDelivered;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getTrainingsDelivered() {
        return trainingsDelivered;
    }

    public void setTrainingsDelivered(long trainingsDelivered) {
        this.trainingsDelivered = trainingsDelivered;
    }
}
