package com.sirion.skillsMicroservice.Model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "TechnologiesTable")

public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    String techName;

    @Column
    long duration;

    @Column
    String preReq;

    @Column(columnDefinition = "boolean default true")
    boolean status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPreReq() {
        return preReq;
    }

    public void setPreReq(String preReq) {
        this.preReq = preReq;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
